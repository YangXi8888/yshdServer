package gov.jslt.taxcore.taxblh.swd.swd002;

import gov.jslt.taxcore.taxblh.comm.CoreHelper;
import gov.jslt.taxcore.taxblh.comm.FileTool;
import gov.jslt.taxcore.taxblh.comm.ZipTool;
import gov.jslt.taxcore.taxbpo.comm.YhscjlbBPO;
import gov.jslt.taxcore.taxbpo.comm.YhscjlbVO;
import gov.jslt.taxevent.comm.FileVO;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.LoginVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.bpo.QueryCssBPO;
import com.ctp.core.config.ApplicationContext;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.core.utility.dbtime.DBTimeServer;

public class Swd002BLH extends BaseBizLogicHandler {

	protected ResponseEvent performTask(RequestEvent req, Connection conn)
			throws SQLException, TaxBaseBizException {
		String handleCode = req.getDealMethod();
		if ("queryData".equals(handleCode)) {
			return queryData(req, conn);
		} else if ("downLoadFile".equals(handleCode)) {
			return downLoadFile(req, conn);
		} else if ("deleteFile".equals(handleCode)) {
			return deleteFile(req, conn);
		} else if ("downLoadAllFile".equals(handleCode)) {
			return downLoadAllFile(req, conn);
		}
		return null;
	}

	protected ResponseEvent validateData(RequestEvent req, Connection conn)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	protected ResponseEvent downLoadAllFile(RequestEvent reqEvent,
			Connection conn) throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData reqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		HttpServletRequest request = (HttpServletRequest) reqEvent.reqMapParam
				.get("HttpServletRequest");
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute(
				reqData.getYhwybz());
		ArrayList<Object> sqlParam = new ArrayList<Object>();
		CachedRowSet rs;
		String sql = "SELECT T.SCJL_ID, B.XM, C.QYYH_MC,  T.WJM, T.WJDX, TO_CHAR(T.LR_SJ,'YYYY-MM-DD HH24:MI:SS') SCRQ   FROM T_YS_YHSCJLB T, T_YS_LOGIN B, T_DM_YS_QYYH C  WHERE T.UUID = B.UUID    AND B.QYYH_DM = C.QYYH_DM    AND T.LR_SJ > TO_DATE(TO_CHAR(TRUNC(SYSDATE, 'MM'), 'YYYY-MM-DD') || ' 00:00:01',  'YYYY-MM-DD HH24:MI:SS')";
		if (null != reqData.getData().get("rqq")
				&& !"".equals(reqData.getData().get("rqq"))) {
			sql = "SELECT T.SCJL_ID, B.XM, C.QYYH_MC,  T.WJM, T.WJDX, TO_CHAR(T.LR_SJ,'YYYY-MM-DD HH24:MI:SS') SCRQ   FROM T_YS_YHSCJLB T, T_YS_LOGIN B, T_DM_YS_QYYH C  WHERE T.UUID = B.UUID    AND B.QYYH_DM = C.QYYH_DM     AND T.LR_SJ > TO_DATE( ? || ' 00:00:01',  'YYYY-MM-DD HH24:MI:SS')   AND T.LR_SJ < TO_DATE( ? || ' 23:59:59',  'YYYY-MM-DD HH24:MI:SS')";
			sqlParam.add(reqData.getData().get("rqq"));
			sqlParam.add(reqData.getData().get("rqz"));
		}
		rs = QueryCssBPO.findAll(conn, sql, sqlParam);
		List<FileVO> dataList = new ArrayList<FileVO>();
		YhscjlbVO yhscjlbVO = null;
		while (rs.next()) {
			yhscjlbVO = YhscjlbBPO.queryByPK(conn, rs.getString("SCJL_ID"));
			FileVO fileVO = new FileVO();
			fileVO.setFileContent(yhscjlbVO.getWjnrByte());
			fileVO.setFileName(yhscjlbVO.getWjm());
			dataList.add(fileVO);
		}
		String tempFileName = System.getProperty("user.dir")
				+ ApplicationContext.singleton().getValueAsString(
						"zipfile.temp") + "/" + loginVO.getSjHm() + ".zip";
		ZipTool.zipCompress(dataList, tempFileName);
		FileVO zipFile = new FileVO();
		zipFile.setFileType(".zip");
		zipFile.setFileName(DBTimeServer.getDBTimesStr(conn, 3) + ".zip");
		zipFile.setFileContent(FileTool.getZipByte(tempFileName));
		responseEvent.respMapParam.put(GeneralCons.FILE_VO, zipFile);
		responseEvent.respMapParam.put("dataList", dataList);
		responseEvent.setReponseMesg("下载全部文件成功");
		return responseEvent;
	}

	protected ResponseEvent downLoadFile(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData reqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		YhscjlbVO yhscjlbVO = YhscjlbBPO.queryByPK(conn, (String) reqData
				.getData().get("scjlId"));
		if (null != yhscjlbVO) {
			FileVO fileVO = new FileVO();
			fileVO.setFileContent(yhscjlbVO.getWjnrByte());
			fileVO.setFileName(yhscjlbVO.getWjm());
			fileVO.setFileType(yhscjlbVO.getWjm().substring(
					yhscjlbVO.getWjm().lastIndexOf("."),
					yhscjlbVO.getWjm().length()));
			responseEvent.respMapParam.put(GeneralCons.FILE_VO, fileVO);
			responseEvent.setReponseMesg("下载单个文件成功");
		} else {
			responseEvent.setRepCode("ZB0004");
			responseEvent.setReponseMesg(CoreHelper.getJyztMc(conn, "ZB0004"));
		}
		return responseEvent;
	}

	protected ResponseEvent deleteFile(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData reqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		String sql = "SELECT   T.*   FROM T_YS_YHSCJLB T WHERE T.SCJL_ID=?";
		ArrayList<Object> sqlParam = new ArrayList<Object>();
		sqlParam.add(reqData.getData().get("scjlId"));
		CachedRowSet rs = QueryCssBPO.findAll(conn, sql, sqlParam);
		if (rs.next()) {
			PreparedStatement pt = conn
					.prepareStatement("DELETE FROM T_YS_YHSCJLB WHERE SCJL_ID=?");
			pt.setString(1, (String) reqData.getData().get("scjlId"));
			pt.execute();
			pt.close();
			responseEvent.setReponseMesg("删除成功");
		} else {
			responseEvent.setRepCode("ZB0004");
			responseEvent.setReponseMesg(CoreHelper.getJyztMc(conn, "ZB0004"));
		}
		return responseEvent;
	}

	protected ResponseEvent queryData(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData reqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		ArrayList<Object> sqlParam = new ArrayList<Object>();
		CachedRowSet rs;
		String sql = "SELECT T.SCJL_ID, B.XM, C.QYYH_MC,  T.WJM, T.WJDX, TO_CHAR(T.LR_SJ,'YYYY-MM-DD HH24:MI:SS') SCRQ   FROM T_YS_YHSCJLB T, T_YS_LOGIN B, T_DM_YS_QYYH C  WHERE T.UUID = B.UUID    AND B.QYYH_DM = C.QYYH_DM    AND T.LR_SJ > TO_DATE(TO_CHAR(TRUNC(SYSDATE, 'MM'), 'YYYY-MM-DD') || ' 00:00:01',  'YYYY-MM-DD HH24:MI:SS')";
		if (null != reqData.getData().get("rqq")
				&& !"".equals(reqData.getData().get("rqq"))) {
			sql = "SELECT T.SCJL_ID, B.XM, C.QYYH_MC,  T.WJM, T.WJDX, TO_CHAR(T.LR_SJ,'YYYY-MM-DD HH24:MI:SS') SCRQ   FROM T_YS_YHSCJLB T, T_YS_LOGIN B, T_DM_YS_QYYH C  WHERE T.UUID = B.UUID    AND B.QYYH_DM = C.QYYH_DM     AND T.LR_SJ > TO_DATE( ? || ' 00:00:01',  'YYYY-MM-DD HH24:MI:SS')   AND T.LR_SJ < TO_DATE( ? || ' 23:59:59',  'YYYY-MM-DD HH24:MI:SS')";
			sqlParam.add(reqData.getData().get("rqq"));
			sqlParam.add(reqData.getData().get("rqz"));
		}
		rs = QueryCssBPO.findAll(conn, sql, sqlParam);
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		Map<String, String> dataMap;
		while (rs.next()) {
			dataMap = new HashMap<String, String>();
			dataMap.put("SCJL_ID", rs.getString("SCJL_ID"));
			dataMap.put("XM", rs.getString("XM"));
			dataMap.put("QYYH_MC", rs.getString("QYYH_MC"));
			dataMap.put("WJM", rs.getString("WJM"));
			dataMap.put("WJDX",
					FileTool.bytes2kb(Long.decode(rs.getString("WJDX"))));
			dataMap.put("SCRQ", rs.getString("SCRQ"));
			dataList.add(dataMap);
		}
		responseEvent.respMapParam.put("dataList", dataList);
		responseEvent.setReponseMesg("查询成功");
		return responseEvent;
	}

}
