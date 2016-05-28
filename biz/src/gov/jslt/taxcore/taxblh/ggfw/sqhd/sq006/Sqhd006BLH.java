package gov.jslt.taxcore.taxblh.ggfw.sqhd.sq006;

import gov.jslt.taxevent.comm.GeneralCons;

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

public class Sqhd006BLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		String dealMethod = req.getDealMethod();
		if ("queryWblist".equals(dealMethod)) {
			return queryWblist(req, con);
		}
		return null;
	}

	protected ResponseEvent queryWblist(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT WB.WB_UID, WB.WB_MC, S.MC_J ,S.SWJG_DM  ");
		sb.append(" FROM DB_ZSBS.T_CS_ZB_WB WB, DB_WSBS.T_DM_GY_SWJG S ");
		sb.append(" WHERE WB.XY_BJ = '1' ");
		sb.append(" AND WB.SWJG_DM = S.SWJG_DM ");
		sb.append(" AND WB.WB_UID IS NOT NULL ");
		sb.append(" AND WB.WB_MC IS NOT NULL ");
		CachedRowSet rs = QueryCssBPO.findAll(con, sb.toString(), null);
		List<Map<String, String>> wbList = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("swjgdm", rs.getString("SWJG_DM").substring(0, 5));
			map.put("wbuid", rs.getString("WB_UID"));
			map.put("wbmc", rs.getString("WB_MC"));
			map.put("swjgmc", rs.getString("MC_J"));
			wbList.add(map);
		}
		responseEvent.respMapParam.put("wbList", wbList);
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	@Override
	protected ResponseEvent validateData(RequestEvent req, Connection con)
			throws Exception {
		return null;
	}

}
