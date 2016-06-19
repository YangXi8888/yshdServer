package gov.jslt.taxcore.taxblh.yhd.yhd002;

import gov.jslt.taxcore.taxblh.comm.CoreHelper;
import gov.jslt.taxcore.taxblh.comm.FileTool;
import gov.jslt.taxcore.taxbpo.comm.YhscjlbBPO;
import gov.jslt.taxcore.taxbpo.comm.YhscjlbVO;
import gov.jslt.taxevent.comm.FileVO;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.UploadFile;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.bpo.QueryBPO;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;

public class Yhd002BLH extends BaseBizLogicHandler {

	protected ResponseEvent performTask(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		if ("upLoadFile".equals(reqEvent.getDealMethod())) {
			return upLoadFile(reqEvent, conn);
		} else if ("queryData".equals(reqEvent.getDealMethod())) {
			return queryData(reqEvent, conn);
		} else if ("downLoadFile".equals(reqEvent.getDealMethod())) {
			return downLoadFile(reqEvent, conn);
		} else if ("deleteFile".equals(reqEvent.getDealMethod())) {
			return deleteFile(reqEvent, conn);
		}
		return null;
	}

	protected ResponseEvent queryData(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData reqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(reqData.getYhwybz());
		sqlParams.add(reqData.getData().get("rqq"));
		sqlParams.add(reqData.getData().get("rqz"));
		String sql = "SELECT T.SCJL_ID,\n"
				+ "       B.XM,\n"
				+ "       T.WJM,\n"
				+ "       T.WJDX,\n"
				+ "       TO_CHAR(T.LR_SJ, 'YYYY-MM-DD HH24:MI:SS') SCRQ\n"
				+ "  FROM T_YS_YHSCJLB T, T_YS_LOGIN B\n"
				+ " WHERE T.UUID = B.UUID\n"
				+ "   AND T.UUID = ?\n"
				+ "   AND T.LR_SJ > TO_DATE(? || ' 00:00:01', 'YYYY-MM-DD HH24:MI:SS')\n"
				+ "   AND T.LR_SJ < TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')";

		CachedRowSet rs = QueryBPO.findAll(conn, sql, sqlParams);
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		Map<String, String> dataMap;
		while (rs.next()) {
			dataMap = new HashMap<String, String>();
			dataMap.put("SCJL_ID", rs.getString("SCJL_ID"));
			dataMap.put("XM", rs.getString("XM"));
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

	protected ResponseEvent deleteFile(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData reqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		String scjlId = String.valueOf(reqData.getData().get("scjlId"));
		YhscjlbVO yhscjlbVO = YhscjlbBPO.queryByPK(conn, scjlId);
		if (null == yhscjlbVO) {
			responseEvent.setRepCode("ZB0004");
			responseEvent.setReponseMesg(CoreHelper.getJyztMc(conn, "ZB0004"));
			return responseEvent;
		}
		YhscjlbBPO.deleteByPK(conn, scjlId);
		responseEvent.setReponseMesg("删除成功");
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

	protected ResponseEvent upLoadFile(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		List<UploadFile> uploadFiles = (List<UploadFile>) reqEvent.reqMapParam
				.get("UploadFiles");
		if (null == uploadFiles || uploadFiles.size() == 0) {
			responseEvent.setRepCode("ZB0005");
			responseEvent.setReponseMesg(CoreHelper.getJyztMc(conn, "ZB0005"));
			return responseEvent;
		}
		YhscjlbVO yhscjlbVO = null;
		for (int i = 0; i < uploadFiles.size(); i++) {
			if (null == uploadFiles.get(i)) {
				continue;
			}
			if (uploadFiles.get(i).getFile().getFileSize() > 10485760) {
				responseEvent.setRepCode("ZB0007");
				responseEvent.setReponseMesg(CoreHelper.getJyztMc(conn,
						"ZB0007"));
				return responseEvent;
			}
			yhscjlbVO = new YhscjlbVO();
			yhscjlbVO.setScjlid(CoreHelper.getGUID(conn));
			yhscjlbVO.setUuid(jsonReqData.getYhwybz());
			yhscjlbVO.setWjm(uploadFiles.get(i).getFile().getFileName());

			yhscjlbVO.setWjdx(String.valueOf(uploadFiles.get(i).getFile()
					.getFileSize()));
			yhscjlbVO.setWjnrByte(FileTool.getFileContent(uploadFiles.get(i)
					.getFile()));
			YhscjlbBPO.insert(conn, yhscjlbVO);
		}
		responseEvent.setReponseMesg("上传成功");
		return responseEvent;
	}

	protected ResponseEvent validateData(RequestEvent req, Connection conn)
			throws Exception {
		return null;
	}

}
