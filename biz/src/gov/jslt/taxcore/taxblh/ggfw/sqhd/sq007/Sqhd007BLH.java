package gov.jslt.taxcore.taxblh.ggfw.sqhd.sq007;

import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxevent.comm.GeneralCons;
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
import com.ctp.core.log.LogWritter;
import com.ctp.cssesb.esbevent.ESBRequestEvent;

public class Sqhd007BLH extends BaseBizLogicHandler {
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws TaxBaseBizException, SQLException {
		String delMethod = req.getDealMethod();
		if ("yyServices".equals(delMethod)) {
			return yyServices(req, con);
		}if ("getQy".equals(delMethod)) {
			return getQy(req, con); 
		}
		
		return null;
	}

	protected ResponseEvent yyServices(RequestEvent req, Connection con)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
	    Map<String, String> map;
	    map = new HashMap<String, String>();
	    map.put("sjhm",(String) dataMap.get("sjhm"));
	    map.put("xm",(String) dataMap.get("xm"));
	    map.put("zjlx",(String) dataMap.get("zjlx"));
	    map.put("yysx",(String) dataMap.get("yysx"));
	    map.put("ds_dm",(String) dataMap.get("ds_dm"));
	    map.put("qy_dm",(String) dataMap.get("qy_dm"));
	    LogWritter.sysError("开始调用ESB.......................");
	    try {
	    ESBRequestEvent baseRequest = new ESBRequestEvent(
				"Sqhd007BLH", "", "");
	    baseRequest.setReqMapParam(new HashMap<>());
		baseRequest.setDealMethod("yybl");
		baseRequest.reqMapParam.put("sjhm", (String) dataMap.get("sjhm"));
		baseRequest.reqMapParam.put("xm", (String) dataMap.get("xm"));
		baseRequest.reqMapParam.put("zjlx", (String) dataMap.get("zjlx"));
		baseRequest.reqMapParam.put("zjhm", (String) dataMap.get("zjhm"));
		baseRequest.reqMapParam.put("yysx", (String) dataMap.get("yysx"));
		baseRequest.reqMapParam.put("ds_dm", (String) dataMap.get("ds_dm"));
		baseRequest.reqMapParam.put("qy_dm", (String) dataMap.get("qy_dm"));
	    responseEvent =ZBCoreHelper.getEsbResponseEvent(baseRequest,con);
	    String code =(String) responseEvent.respMapParam.get("code");
	    responseEvent.respMapParam.put("code", code);	
	    } catch (Exception e) {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					ZBCoreHelper.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
					e.getMessage()));
			return responseEvent;
		}
		return responseEvent;
	}
	
	
	//初始化，取区域
	protected ResponseEvent getQy(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(new StoredProcParamObj(1,dataMap.get("ds_dm"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		StoredProcManager.callStoreProcess(con, "{call P_ZB_SQHD007_GETQY(?)}",
				sqlParams);
		CachedRowSet rs = QueryCssBPO.findAll(con,
				"SELECT T.* FROM DB_ZSBS.T_LS_SQHD007_GETQY T", null);
		List<Map<String, String>> qyxxList = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("SWJG_MC", rs.getString("SWJG_MC"));
			map.put("SWJG_DM", rs.getString("SWJG_DM"));
			qyxxList.add(map);
		}
		responseEvent.respMapParam.put("qyxxList", qyxxList);	
		rs = QueryCssBPO.findAll(con, "SELECT T.* FROM DB_ZSBS.T_ZB_LS_JYZT T",
				null);
		rs.next();
		responseEvent.setRepCode(rs.getString("JYZT_DM"));
		responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
				rs.getString("JYZT_MC"), rs.getString("SJNR")));
		return responseEvent;
	}

	@Override
	protected ResponseEvent validateData(RequestEvent arg0, Connection arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
