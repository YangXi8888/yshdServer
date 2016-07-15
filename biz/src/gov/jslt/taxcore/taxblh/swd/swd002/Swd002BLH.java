package gov.jslt.taxcore.taxblh.swd.swd002;

import gov.jslt.taxcore.taxblh.comm.AESTool;
import gov.jslt.taxcore.taxblh.comm.CoreHelper;
import gov.jslt.taxcore.taxblh.comm.FileTool;
import gov.jslt.taxcore.taxbpo.nsrd.nsrd001.NsrCwbbBPO;
import gov.jslt.taxcore.taxbpo.nsrd.nsrd001.NsrJbxxBPO;
import gov.jslt.taxcore.taxbpo.nsrd.nsrd001.NsrSbfBPO;
import gov.jslt.taxcore.taxbpo.nsrd.nsrd001.NsrSfBPO;
import gov.jslt.taxcore.taxbpo.nsrd.nsrd001.NsrXzcfBPO;
import gov.jslt.taxevent.comm.FileVO;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.LoginVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrCwbbVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrJbxxVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrSbfVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrSfVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrXzcfVO;

import java.io.File;
import java.sql.Connection;
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

	protected ResponseEvent performTask(RequestEvent req, Connection conn) throws SQLException, TaxBaseBizException {
		String handleCode = req.getDealMethod();
		if ("queryData".equals(handleCode)) {
			return queryData(req, conn);
		} else if ("downLoadFile".equals(handleCode)) {
			return downLoadFile(req, conn);
		} else if ("downLoadAllFile".equals(handleCode)) {
			return downLoadAllFile(req, conn);
		}
		return null;
	}

	protected ResponseEvent queryData(RequestEvent reqEvent, Connection conn) throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData reqData = (JsonReqData) reqEvent.reqMapParam.get("JsonReqData");
		CachedRowSet rs;
		String sql = "SELECT T.ZB_UUID, T.NSR_MC, TO_CHAR(T.LR_SJ, 'YYYY-MM-DD HH24:MI:SS') FSRQ\n" + "  FROM T_YS_NSRFS_ZB T\n" + " WHERE "
				+ "    T.LR_SJ > TO_DATE(? || ' 00:00:01', 'YYYY-MM-DD HH24:MI:SS')\n"
				+ "   AND T.LR_SJ < TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') AND T.NSR_MC LIKE ?";
		ArrayList<Object> sqlParam = new ArrayList<Object>();
		sqlParam.add(reqData.getData().get("rqq"));
		sqlParam.add(reqData.getData().get("rqz"));
		if (null == reqData.getData().get("qyMc") || "".equals(reqData.getData().get("qyMc"))) {
			sqlParam.add("%");
		} else {
			sqlParam.add(reqData.getData().get("qyMc") + "%");
		}
		rs = QueryCssBPO.findAll(conn, sql, sqlParam);
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		Map<String, String> dataMap;
		while (rs.next()) {
			dataMap = new HashMap<String, String>();
			dataMap.put("ZB_UUID", rs.getString("ZB_UUID"));
			dataMap.put("NSR_MC", rs.getString("NSR_MC"));
			dataMap.put("FSRQ", rs.getString("FSRQ"));
			dataList.add(dataMap);
		}
		responseEvent.respMapParam.put("dataList", dataList);
		responseEvent.setReponseMesg("查询成功");
		return responseEvent;
	}

	protected ResponseEvent downLoadFile(RequestEvent reqEvent, Connection conn) throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		HttpServletRequest request = (HttpServletRequest) reqEvent.reqMapParam.get("HttpServletRequest");
		JsonReqData reqData = (JsonReqData) reqEvent.reqMapParam.get("JsonReqData");
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute(reqData.getYhwybz());
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(loginVO.getYhwybz());
		CachedRowSet rowSet = QueryCssBPO.findAll(conn, "SELECT WJMM FROM T_YS_WJMM WHERE UUID=?", sqlParams);
		if (rowSet.next()) {
			String wjMm = rowSet.getString("WJMM");
			String zbUuid = (String) reqData.getData().get("zbUuid");
			Map<String, List<Object>> dataMap = new HashMap<String, List<Object>>();
			List<Object> jbxxList = NsrJbxxBPO.queryByZbuuid(conn, zbUuid);
			List<Object> sfList = NsrSfBPO.queryList(conn, zbUuid);
			List<Object> sbfList = NsrSbfBPO.queryList(conn, zbUuid);
			List<Object> cwbblist = NsrCwbbBPO.queryList(conn, zbUuid);
			List<Object> xzcfList = NsrXzcfBPO.queryList(conn, zbUuid);
			dataMap.put("jbxxList", jbxxList);
			dataMap.put("sfList", sfList);
			dataMap.put("sbfList", sbfList);
			dataMap.put("cwbblist", cwbblist);
			dataMap.put("xzcfList", xzcfList);

			String tempFileName = System.getProperty("user.dir") + File.separator + ApplicationContext.singleton().getValueAsString("file.temp")
					+ File.separator + loginVO.getSjHm() + ".xls";
			CoreHelper.buildExcelStream(dataMap, tempFileName, wjMm);
			// CoreHelper.createExcelByMM(byteArrayOutputStream, wjMm, tempFileName);
			FileVO fileVO = new FileVO();
			fileVO.setFileContent(FileTool.getFileByte(tempFileName));
			fileVO.setFileName(((NsrJbxxVO) jbxxList.get(0)).getNsrmc() + ".xls");
			fileVO.setFileType(".xls");
			responseEvent.respMapParam.put(GeneralCons.FILE_VO, fileVO);
			responseEvent.setReponseMesg("单户下载成功");
		} else {
			responseEvent.setRepCode("ZB0005");
			responseEvent.setReponseMesg(CoreHelper.getJyztMc(conn, "ZB0005"));
		}

		return responseEvent;
	}

	protected ResponseEvent downLoadAllFile(RequestEvent reqEvent, Connection con) throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		HttpServletRequest request = (HttpServletRequest) reqEvent.reqMapParam.get("HttpServletRequest");
		JsonReqData reqData = (JsonReqData) reqEvent.reqMapParam.get("JsonReqData");
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute(reqData.getYhwybz());
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(loginVO.getYhwybz());
		CachedRowSet rowSet = QueryCssBPO.findAll(con, "SELECT WJMM FROM T_YS_WJMM WHERE UUID=?", sqlParams);
		if (rowSet.next()) {
			String wjMm = rowSet.getString("WJMM");
			Map<String, List<Object>> dataMap = new HashMap<String, List<Object>>();
			List<Object> jbxxList = new ArrayList<Object>();
			List<Object> sfList = new ArrayList<Object>();
			List<Object> sbfList = new ArrayList<Object>();
			List<Object> cwbblist = new ArrayList<Object>();
			List<Object> xzcfList = new ArrayList<Object>();

			sqlParams = new ArrayList<Object>();
			sqlParams.add(reqData.getData().get("rqq"));
			sqlParams.add(reqData.getData().get("rqz"));

			if (null == reqData.getData().get("qyMc") || "".equals(reqData.getData().get("qyMc"))) {
				sqlParams.add("%");
			} else {
				sqlParams.add(AESTool.decrypt(reqData.getData().get("qyMc").toString(), loginVO.getYhwybz()) + "%");
			}

			CachedRowSet rs;
			String strSql = " SELECT  Y.* FROM T_YS_NSRFS_ZB T,T_YS_NSRFS_JBXX  Y   WHERE  "
					+ "  T.LR_SJ > TO_DATE(? || ' 00:00:01', 'YYYY-MM-DD HH24:MI:SS') "
					+ " AND T.LR_SJ < TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') AND T.NSR_MC LIKE ? " + " AND T.ZB_UUID=Y.ZB_UUID ";

			rs = QueryCssBPO.findAll(con, strSql, sqlParams);
			NsrJbxxVO vo = null;
			while (rs.next()) {
				vo = new NsrJbxxVO();
				vo.setDjzclxmc(rs.getString("DJZCLX_MC"));
				vo.setGbhymc(rs.getString("GBHY_MC"));
				vo.setNsrsbm(rs.getString("NSRSBM"));
				vo.setNsrmc(rs.getString("NSR_MC"));
				vo.setSwglm(String.valueOf(rs.getLong("SWGLM")));
				vo.setUuid(rs.getString("UUID"));
				vo.setXydj(rs.getString("XYDJ"));
				vo.setZbuuid(rs.getString("ZB_UUID"));
				vo.setZcdz(rs.getString("ZCDZ"));
				jbxxList.add(vo);
			}

			strSql = " SELECT  Y.* FROM T_YS_NSRFS_ZB T,T_YS_NSRFS_SF  Y   WHERE   " + "  T.LR_SJ > TO_DATE(? || ' 00:00:01', 'YYYY-MM-DD HH24:MI:SS') "
					+ " AND T.LR_SJ < TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') AND T.NSR_MC LIKE ? " + "  AND T.ZB_UUID=Y.ZB_UUID ";

			rs = QueryCssBPO.findAll(con, strSql, sqlParams);
			NsrSfVO sfVO = null;
			while (rs.next()) {
				sfVO = new NsrSfVO();
				sfVO.setNsrsbm(rs.getString("NSRSBM"));
				sfVO.setNsrmc(rs.getString("NSR_MC"));
				sfVO.setRkse(rs.getString("RKSE"));
				sfVO.setSsnd(rs.getString("SSND"));
				sfVO.setSwglm(String.valueOf(rs.getLong("SWGLM")));
				sfVO.setSz(rs.getString("SZ"));
				sfVO.setUuid(rs.getString("UUID"));
				sfVO.setZbuuid(rs.getString("ZB_UUID"));
				sfList.add(sfVO);
			}

			strSql = " SELECT  Y.* FROM T_YS_NSRFS_ZB T,T_YS_NSRFS_SBF  Y   WHERE  " + "  T.LR_SJ > TO_DATE(? || ' 00:00:01', 'YYYY-MM-DD HH24:MI:SS') "
					+ " AND T.LR_SJ < TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') AND T.NSR_MC LIKE ? " + "  AND T.ZB_UUID=Y.ZB_UUID ";

			rs = QueryCssBPO.findAll(con, strSql, sqlParams);
			NsrSbfVO sbfVO = null;
			while (rs.next()) {
				sbfVO = new NsrSbfVO();
				sbfVO.setNsrsbm(rs.getString("NSRSBM"));
				sbfVO.setNsrmc(rs.getString("NSR_MC"));
				sbfVO.setSjje(rs.getString("SJJE"));
				sbfVO.setSsnd(rs.getString("SSND"));
				sbfVO.setSwglm(String.valueOf(rs.getLong("SWGLM")));
				sbfVO.setUuid(rs.getString("UUID"));
				sbfVO.setXz(rs.getString("XZ"));
				sbfVO.setZbuuid(rs.getString("ZB_UUID"));
				sbfList.add(sbfVO);
			}

			strSql = " SELECT  Y.* FROM T_YS_NSRFS_ZB T,T_YS_NSRFS_CWBB  Y   WHERE   " + "  T.LR_SJ > TO_DATE(? || ' 00:00:01', 'YYYY-MM-DD HH24:MI:SS') "
					+ " AND T.LR_SJ < TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') AND T.NSR_MC LIKE ? " + "  AND T.ZB_UUID=Y.ZB_UUID ";

			rs = QueryCssBPO.findAll(con, strSql, sqlParams);
			NsrCwbbVO cwbbVO = null;
			while (rs.next()) {
				cwbbVO = new NsrCwbbVO();
				cwbbVO.setFzze(rs.getString("FZZE"));
				cwbbVO.setKjlr(rs.getString("KJLR"));
				cwbbVO.setNsrsbm(rs.getString("NSRSBM"));
				cwbbVO.setNsrmc(rs.getString("NSR_MC"));
				cwbbVO.setSsnd(rs.getString("SSND"));
				cwbbVO.setSszb(rs.getString("SSZB"));
				cwbbVO.setSwglm(String.valueOf(rs.getLong("SWGLM")));
				cwbbVO.setUuid(rs.getString("UUID"));
				cwbbVO.setZbgj(rs.getString("ZBGJ"));
				cwbbVO.setZbuuid(rs.getString("ZB_UUID"));
				cwbbVO.setZcze(rs.getString("ZCZE"));
				cwbbVO.setZyywcb(rs.getString("ZYYWCB"));
				cwbbVO.setZyywsr(rs.getString("ZYYWSR"));
				cwbblist.add(cwbbVO);
			}

			strSql = " SELECT  Y.* FROM T_YS_NSRFS_ZB T,T_YS_NSRFS_XZCF  Y   WHERE  " + "  T.LR_SJ > TO_DATE(? || ' 00:00:01', 'YYYY-MM-DD HH24:MI:SS') "
					+ " AND T.LR_SJ < TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') AND T.NSR_MC LIKE  ? " + "  AND T.ZB_UUID=Y.ZB_UUID ";

			rs = QueryCssBPO.findAll(con, strSql, sqlParams);
			NsrXzcfVO xzcfVO = null;
			while (rs.next()) {
				xzcfVO = new NsrXzcfVO();
				xzcfVO.setCfjdwh(rs.getString("CFJDWH"));
				xzcfVO.setCfje(String.valueOf(rs.getDouble("CFJE")));
				xzcfVO.setCfrq(rs.getString("CFRQ"));
				xzcfVO.setCfsy(CoreHelper.readCLOB(rs, "CFSY"));
				xzcfVO.setNsrsbm(rs.getString("NSRSBM"));
				xzcfVO.setNsrmc(rs.getString("NSR_MC"));
				xzcfVO.setSwglm(String.valueOf(rs.getLong("SWGLM")));
				xzcfVO.setUuid(rs.getString("UUID"));
				xzcfVO.setZbuuid(rs.getString("ZB_UUID"));
				xzcfVO.setZxwcrq(rs.getString("ZXWCRQ"));
				xzcfList.add(xzcfVO);
			}

			dataMap.put("jbxxList", jbxxList);
			dataMap.put("sfList", sfList);
			dataMap.put("sbfList", sbfList);
			dataMap.put("cwbblist", cwbblist);
			dataMap.put("xzcfList", xzcfList);

			String tempFileName = System.getProperty("user.dir") + File.separator + ApplicationContext.singleton().getValueAsString("file.temp")
					+ File.separator + loginVO.getSjHm() + ".xls";
			CoreHelper.buildExcelStream(dataMap, tempFileName, wjMm);
			// CoreHelper.createExcelByMM(byteArrayOutputStream, wjMm, tempFileName);
			FileVO fileVO = new FileVO();
			fileVO.setFileContent(FileTool.getFileByte(tempFileName));
			fileVO.setFileName(DBTimeServer.getDBTimesStr(con, 3) + ".xls");
			fileVO.setFileType(".xls");
			responseEvent.respMapParam.put(GeneralCons.FILE_VO, fileVO);
			responseEvent.setReponseMesg("下载全部文件成功");
		} else {
			responseEvent.setRepCode("ZB0005");
			responseEvent.setReponseMesg(CoreHelper.getJyztMc(con, "ZB0005"));
		}
		return responseEvent;
	}

	protected ResponseEvent validateData(RequestEvent req, Connection conn) throws Exception {
		return null;
	}

}
