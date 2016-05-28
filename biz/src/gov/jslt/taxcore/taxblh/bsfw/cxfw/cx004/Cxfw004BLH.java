package gov.jslt.taxcore.taxblh.bsfw.cxfw.cx004;

import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
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
import com.ctp.core.bpo.StoredProcManager;
import com.ctp.core.bpo.StoredProcParamObj;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;

public class Cxfw004BLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		String delMethod = req.getDealMethod();
		if ("sbfjnxx".equals(delMethod)) {
			return sbfjnxx(req, con);
		} else if ("sbfyzxx".equals(delMethod)) {
			return sbfyzxx(req, con);
		}
		return null;
	}

	// 社保费缴纳情况查询
	protected ResponseEvent sbfjnxx(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(new StoredProcParamObj(1, dataMap.get("swglm"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(2, dataMap.get("ssqs"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(3, dataMap.get("ssqz"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		StoredProcManager.callStoreProcess(con,
				"{call P_ZB_CXFW004_SBJNXX(?,?,?)}", sqlParams);
		CachedRowSet rs = QueryCssBPO.findAll(con,
				"SELECT T.* FROM DB_ZSBS.T_LS_CXFW004_SBJNXX T", null);
		List<Map<String, String>> jnxxList = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("SB_DM", rs.getString("SB_DM"));
			map.put("SBZSXM_MC", rs.getString("SBZSXM_MC"));
			map.put("SBZSPM_MC", rs.getString("SBZSPM_MC"));
			map.put("SSQ_QSRQ", rs.getString("SSQ_QSRQ"));
			map.put("SSQ_ZZRQ", rs.getString("SSQ_ZZRQ"));
			map.put("JFJS_JE", rs.getString("JFJS_JE"));
			map.put("JKQX", rs.getString("JKQX"));
			map.put("SJZT_MC", rs.getString("SJZT_MC"));
			jnxxList.add(map);
		}
		responseEvent.respMapParam.put("jnxxList", jnxxList);
		rs = QueryCssBPO.findAll(con, "SELECT T.* FROM DB_ZSBS.T_ZB_LS_JYZT T",
				null);
		rs.next();
		responseEvent.setRepCode(rs.getString("JYZT_DM"));
		responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
				rs.getString("JYZT_MC"), rs.getString("SJNR")));
		return responseEvent;
	}

	// 社保应征信息
	protected ResponseEvent sbfyzxx(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(new StoredProcParamObj(1, dataMap.get("swglm"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		StoredProcManager.callStoreProcess(con,
				"{call P_ZB_CXFW004_SBYZXX(?)}", sqlParams);
		CachedRowSet rs = QueryCssBPO.findAll(con,
				"SELECT T.* FROM DB_ZSBS.T_LS_CXFW004_SBYZXX T", null);
		List<Map<String, String>> yzxxList = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("SB_DM", rs.getString("SB_DM"));
			map.put("SBZSXM_MC", rs.getString("SBZSXM_MC"));
			map.put("SBZSPM_MC", rs.getString("SBZSPM_MC"));
			map.put("SSQ_QSRQ", rs.getString("SSQ_QSRQ"));
			map.put("SSQ_ZZRQ", rs.getString("SSQ_ZZRQ"));
			map.put("YJFE_JE", rs.getString("YJFE_JE"));
			map.put("JKQX", rs.getString("JKQX"));
			yzxxList.add(map);
		}
		responseEvent.respMapParam.put("yzxxList", yzxxList);
		rs = QueryCssBPO.findAll(con, "SELECT T.* FROM DB_ZSBS.T_ZB_LS_JYZT T",
				null);
		rs.next();
		responseEvent.setRepCode(rs.getString("JYZT_DM"));
		responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
				rs.getString("JYZT_MC"), rs.getString("SJNR")));
		return responseEvent;
	}

	@Override
	protected ResponseEvent validateData(RequestEvent req, Connection con)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
