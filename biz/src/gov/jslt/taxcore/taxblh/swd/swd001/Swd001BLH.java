package gov.jslt.taxcore.taxblh.swd.swd001;

import gov.jslt.taxcore.taxblh.comm.FileTool;
import gov.jslt.taxevent.comm.JsonReqData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.bpo.QueryCssBPO;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;

public class Swd001BLH extends BaseBizLogicHandler {

	protected ResponseEvent performTask(RequestEvent req, Connection conn)
			throws SQLException, TaxBaseBizException {
		String handleCode = req.getDealMethod();
		if ("queryData".equals(handleCode)) {
			return queryData(req, conn);
		} else if ("downLoadFile".equals(handleCode)) {
			return downLoadFile(req, conn);
		} else if ("deleteFile".equals(handleCode)) {
			return deleteFile(req, conn);
		}
		return null;
	}

	protected ResponseEvent validateData(RequestEvent req, Connection conn)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	protected ResponseEvent downLoadFile(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent reEvent = new ResponseEvent();
		JsonReqData reqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		ArrayList<Object> sqlParam = new ArrayList<Object>();
		CachedRowSet rs;
		String sql = "SELECT T.SCJL_ID, B.XM, C.QYYH_MC, T.WJML, T.WJM, T.WJDX, TO_CHAR(T.LR_SJ,'YYYY-MM-DD HH24:MI:SS') SCRQ   FROM T_YS_YHSCJLB T, T_YS_LOGIN B, T_DM_YS_QYYH C  WHERE T.UUID = B.UUID    AND B.QYYH_DM = C.QYYH_DM    AND T.LR_SJ > TO_DATE(TO_CHAR(TRUNC(SYSDATE, 'MM'), 'YYYY-MM-DD') || ' 00:00:01',  'YYYY-MM-DD HH24:MI:SS')";
		if (null != reqData.getData().get("rqq")
				&& !"".equals(reqData.getData().get("rqq"))) {
			sql = "SELECT T.SCJL_ID, B.XM, C.QYYH_MC, T.WJML, T.WJM, T.WJDX, TO_CHAR(T.LR_SJ,'YYYY-MM-DD HH24:MI:SS') SCRQ   FROM T_YS_YHSCJLB T, T_YS_LOGIN B, T_DM_YS_QYYH C  WHERE T.UUID = B.UUID    AND B.QYYH_DM = C.QYYH_DM     AND T.LR_SJ > TO_DATE( ? || ' 00:00:01',  'YYYY-MM-DD HH24:MI:SS')   AND T.LR_SJ < TO_DATE( ? || ' 23:59:59',  'YYYY-MM-DD HH24:MI:SS')";
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
			dataMap.put("WJML", rs.getString("WJML"));
			dataMap.put("WJM", rs.getString("WJM"));
			dataMap.put("WJDX",
					FileTool.bytes2kb(Long.decode(rs.getString("WJDX"))));
			dataMap.put("SCRQ", rs.getString("SCRQ"));
			dataList.add(dataMap);
		}
		reEvent.respMapParam.put("dataList", dataList);
		reEvent.setReponseMesg("下载成功");
		return reEvent;
	}

	protected ResponseEvent deleteFile(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent reEvent = new ResponseEvent();
		JsonReqData reqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		ArrayList<Object> sqlParam = new ArrayList<Object>();
		CachedRowSet rs;
		String sql = "SELECT T.SCJL_ID, B.XM, C.QYYH_MC, T.WJML, T.WJM, T.WJDX, TO_CHAR(T.LR_SJ,'YYYY-MM-DD HH24:MI:SS') SCRQ   FROM T_YS_YHSCJLB T, T_YS_LOGIN B, T_DM_YS_QYYH C  WHERE T.UUID = B.UUID    AND B.QYYH_DM = C.QYYH_DM    AND T.LR_SJ > TO_DATE(TO_CHAR(TRUNC(SYSDATE, 'MM'), 'YYYY-MM-DD') || ' 00:00:01',  'YYYY-MM-DD HH24:MI:SS')";
		if (null != reqData.getData().get("rqq")
				&& !"".equals(reqData.getData().get("rqq"))) {
			sql = "SELECT T.SCJL_ID, B.XM, C.QYYH_MC, T.WJML, T.WJM, T.WJDX, TO_CHAR(T.LR_SJ,'YYYY-MM-DD HH24:MI:SS') SCRQ   FROM T_YS_YHSCJLB T, T_YS_LOGIN B, T_DM_YS_QYYH C  WHERE T.UUID = B.UUID    AND B.QYYH_DM = C.QYYH_DM     AND T.LR_SJ > TO_DATE( ? || ' 00:00:01',  'YYYY-MM-DD HH24:MI:SS')   AND T.LR_SJ < TO_DATE( ? || ' 23:59:59',  'YYYY-MM-DD HH24:MI:SS')";
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
			dataMap.put("WJML", rs.getString("WJML"));
			dataMap.put("WJM", rs.getString("WJM"));
			dataMap.put("WJDX",
					FileTool.bytes2kb(Long.decode(rs.getString("WJDX"))));
			dataMap.put("SCRQ", rs.getString("SCRQ"));
			dataList.add(dataMap);
		}
		reEvent.respMapParam.put("dataList", dataList);
		reEvent.setReponseMesg("删除成功");
		return reEvent;
	}

	protected ResponseEvent queryData(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent reEvent = new ResponseEvent();
		JsonReqData reqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		ArrayList<Object> sqlParam = new ArrayList<Object>();
		CachedRowSet rs;
		String sql = "SELECT T.SCJL_ID, B.XM, C.QYYH_MC, T.WJML, T.WJM, T.WJDX, TO_CHAR(T.LR_SJ,'YYYY-MM-DD HH24:MI:SS') SCRQ   FROM T_YS_YHSCJLB T, T_YS_LOGIN B, T_DM_YS_QYYH C  WHERE T.UUID = B.UUID    AND B.QYYH_DM = C.QYYH_DM    AND T.LR_SJ > TO_DATE(TO_CHAR(TRUNC(SYSDATE, 'MM'), 'YYYY-MM-DD') || ' 00:00:01',  'YYYY-MM-DD HH24:MI:SS')";
		if (null != reqData.getData().get("rqq")
				&& !"".equals(reqData.getData().get("rqq"))) {
			sql = "SELECT T.SCJL_ID, B.XM, C.QYYH_MC, T.WJML, T.WJM, T.WJDX, TO_CHAR(T.LR_SJ,'YYYY-MM-DD HH24:MI:SS') SCRQ   FROM T_YS_YHSCJLB T, T_YS_LOGIN B, T_DM_YS_QYYH C  WHERE T.UUID = B.UUID    AND B.QYYH_DM = C.QYYH_DM     AND T.LR_SJ > TO_DATE( ? || ' 00:00:01',  'YYYY-MM-DD HH24:MI:SS')   AND T.LR_SJ < TO_DATE( ? || ' 23:59:59',  'YYYY-MM-DD HH24:MI:SS')";
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
			dataMap.put("WJML", rs.getString("WJML"));
			dataMap.put("WJM", rs.getString("WJM"));
			dataMap.put("WJDX",
					FileTool.bytes2kb(Long.decode(rs.getString("WJDX"))));
			dataMap.put("SCRQ", rs.getString("SCRQ"));
			dataList.add(dataMap);
		}
		reEvent.respMapParam.put("dataList", dataList);
		reEvent.setReponseMesg("查询成功");
		return reEvent;
	}

}
