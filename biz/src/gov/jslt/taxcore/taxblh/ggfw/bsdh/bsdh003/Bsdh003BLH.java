package gov.jslt.taxcore.taxblh.ggfw.bsdh.bsdh003;

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
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;

public class Bsdh003BLH extends BaseBizLogicHandler {
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws TaxBaseBizException, SQLException {
		String delMethod = req.getDealMethod();
		if ("queryContent".equals(delMethod)) {
			return queryContent(req, con);
		}
		return null;
	}

	protected ResponseEvent queryContent(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String DS_DM = (String) dataMap.get("DS_DM");
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(DS_DM);
		try {
			CachedRowSet rs = QueryCssBPO
					.findAll(
							con,
							"SELECT T.*,Y.DS_MC FROM DB_ZSBS.T_ZB_BDBSDT T,DB_ZSBS.T_DM_ZB_DSDM Y where t.DS_DM= ?  AND T.DS_DM=Y.DS_DM",
							sqlParams);
			List<Map<String, String>> bsdtList = new ArrayList<Map<String, String>>();
			Map<String, String> map;
			while (rs.next()) {
				map = new HashMap<String, String>();
				map.put("BSFWTMC", rs.getString("BSFWTMC"));
				map.put("BSYB", rs.getString("YB"));
				map.put("BSDH", rs.getString("DH"));
				map.put("BSDZ", rs.getString("DZ"));
				map.put("DS_DM", rs.getString("DS_DM"));
				map.put("DS_MC", rs.getString("DS_MC"));
				bsdtList.add(map);
			}
			responseEvent.respMapParam.put("bsdtList", bsdtList);
		} catch (Exception e) {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					ZBCoreHelper.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
					e.getMessage()));
			return responseEvent;
		}
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	@Override
	protected ResponseEvent validateData(RequestEvent arg0, Connection arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
