package gov.jslt.taxcore.taxblh.bsfw.cxfw.cx001;

import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
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

public class Cxfw001BLH  extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		String delMethod = req.getDealMethod();
		if ("jdxxcx".equals(delMethod)) {
			return jdxxcx(req, con);
		}if ("ysbxxcx".equals(delMethod)) {
			return ysbxxcx(req, con);
		}if ("nsrjbxxcx".equals(delMethod)) {
			return nsrjbxxcx(req, con);
		}
		return null;
	}

	//鉴定信息查询
	protected ResponseEvent jdxxcx(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(new StoredProcParamObj(1,dataMap.get("swglm"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		StoredProcManager.callStoreProcess(con, "{call P_ZB_CXFW001_JDXX(?)}",
				sqlParams);
		CachedRowSet rs = QueryCssBPO.findAll(con,
				"SELECT T.* FROM DB_ZSBS.T_LS_CXFW001_JDXX T", null);
		List<Map<String, String>> jdxxList = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("ZSXM_MC", rs.getString("ZSXM_MC"));
			map.put("ZSPM_MC", rs.getString("ZSPM_MC"));
			map.put("HD_QSRQ", rs.getString("HD_QSRQ"));
			map.put("HD_ZZRQ", rs.getString("HD_ZZRQ"));
			jdxxList.add(map);
		}
		responseEvent.respMapParam.put("jdxxList", jdxxList);	
		rs = QueryCssBPO.findAll(con, "SELECT T.* FROM DB_ZSBS.T_ZB_LS_JYZT T",
				null);
		rs.next();
		responseEvent.setRepCode(rs.getString("JYZT_DM"));
		responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
				rs.getString("JYZT_MC"), rs.getString("SJNR")));
		return responseEvent;
	}
	
	
	//已申报信息查询
	protected ResponseEvent ysbxxcx(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(new StoredProcParamObj(1,dataMap.get("swglm"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		StoredProcManager.callStoreProcess(con, "{call P_ZB_CXFW001_YSBXX(?)}",
				sqlParams);
		CachedRowSet rs = QueryCssBPO.findAll(con,
				"SELECT T.* FROM DB_ZSBS.T_LS_CXFW001_YSBXX T", null);
		List<Map<String, String>> ysbxxList = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("PZZL_MC", rs.getString("PZZL_MC"));
			map.put("LR_SJ", rs.getString("LR_SJ"));
			map.put("YBTSEHDJ_JE", rs.getString("YBTSEHDJ_JE"));
			map.put("ZT_MC", rs.getString("ZT_MC"));
			ysbxxList.add(map);
		}
		responseEvent.respMapParam.put("ysbxxList", ysbxxList);	
		rs = QueryCssBPO.findAll(con, "SELECT sum(t.YBTSEHDJ_JE) hjje FROM DB_ZSBS.T_LS_CXFW001_YSBXX T",null);
		rs.next();
		responseEvent.respMapParam.put("HJ", rs.getString("hjje"));
		rs = QueryCssBPO.findAll(con, "SELECT T.* FROM DB_ZSBS.T_ZB_LS_JYZT T",
				null);
		rs.next();
		responseEvent.setRepCode(rs.getString("JYZT_DM"));
		responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
				rs.getString("JYZT_MC"), rs.getString("SJNR")));
		return responseEvent;
	}
	
	
	//纳税人基本信息查询
	protected ResponseEvent nsrjbxxcx(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(new StoredProcParamObj(1,dataMap.get("swglm"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		StoredProcManager.callStoreProcess(con, "{call P_ZB_CXFW001_NSRJBXX(?)}",
				sqlParams);
		CachedRowSet rs = QueryCssBPO.findAll(con,
				"SELECT T.* FROM DB_ZSBS.T_LS_CXFW001_NSRJBXX T", null);
		Map<String, String> map;
		rs.next();
		responseEvent.respMapParam.put("SWDJLX_MC",ZBCoreHelper.empty(rs.getString("SWDJLX_MC")));
		responseEvent.respMapParam.put("NSRSBM", ZBCoreHelper.empty(rs.getString("NSRSBM")));
		responseEvent.respMapParam.put("NSR_MC", ZBCoreHelper.empty(rs.getString("NSR_MC")));
		responseEvent.respMapParam.put("DJZCLX_MC", ZBCoreHelper.empty(rs.getString("DJZCLX_MC")));
		responseEvent.respMapParam.put("PZSLJG_MC", ZBCoreHelper.empty(rs.getString("PZSLJG_MC")));
		responseEvent.respMapParam.put("ZZJGTYDM", ZBCoreHelper.empty(rs.getString("ZZJGTYDM")));
		responseEvent.respMapParam.put("PZWH", ZBCoreHelper.empty(rs.getString("PZWH")));
		responseEvent.respMapParam.put("KYSLRQ", ZBCoreHelper.empty(rs.getString("KYSLRQ")));
		responseEvent.respMapParam.put("SCJYQQ", ZBCoreHelper.empty(rs.getString("SCJYQQ")));
		responseEvent.respMapParam.put("SCJYQZ", ZBCoreHelper.empty(rs.getString("SCJYQZ")));
		responseEvent.respMapParam.put("ZZLB_MC", ZBCoreHelper.empty(rs.getString("ZZLB_MC")));
		responseEvent.respMapParam.put("ZZHM", ZBCoreHelper.empty(rs.getString("ZZHM")));
		responseEvent.respMapParam.put("ZCDZ", ZBCoreHelper.empty(rs.getString("ZCDZ")));
		responseEvent.respMapParam.put("ZCDZYB", ZBCoreHelper.empty(rs.getString("ZCDZYB")));
		responseEvent.respMapParam.put("ZCDLXDH", ZBCoreHelper.empty(rs.getString("ZCDLXDH")));
		responseEvent.respMapParam.put("SJJYDZ", ZBCoreHelper.empty(rs.getString("SJJYDZ")));
		responseEvent.respMapParam.put("SJJYDZYB", ZBCoreHelper.empty(rs.getString("SJJYDZYB")));
		responseEvent.respMapParam.put("SCJYDLXDH", ZBCoreHelper.empty(rs.getString("SCJYDLXDH")));
		responseEvent.respMapParam.put("HSFS_MC", ZBCoreHelper.empty(rs.getString("HSFS_MC")));
		responseEvent.respMapParam.put("DWXZ_MC", ZBCoreHelper.empty(rs.getString("DWXZ_MC")));
		responseEvent.respMapParam.put("CYRS", ZBCoreHelper.empty(rs.getString("CYRS")));
		responseEvent.respMapParam.put("WJRYRS", ZBCoreHelper.empty(rs.getString("WJRYRS")));
		responseEvent.respMapParam.put("WZ", ZBCoreHelper.empty(rs.getString("WZ")));
		responseEvent.respMapParam.put("QYKJZD_MC", ZBCoreHelper.empty(rs.getString("QYKJZD_MC")));
		responseEvent.respMapParam.put("JYFWZY", ZBCoreHelper.empty(rs.getString("JYFWZY")));	
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
