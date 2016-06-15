package gov.jslt.taxcore.taxblh.comm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.bpo.QueryCssBPO;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;

import sun.jdbc.rowset.CachedRowSet;

public class CommBLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent requestEvent, Connection connection)
			throws SQLException, TaxBaseBizException {
		if ("queryQyYh".equals(requestEvent.getDealMethod())) {
			return queryQyYh(requestEvent, connection);
		}
		return null;
	}

	protected ResponseEvent queryQyYh(RequestEvent requestEvent, Connection connection)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		String sql = "SELECT QYYH_DM,QYYH_MC FROM  T_DM_YS_QYYH T WHERE XY_BJ='1'";
		CachedRowSet rs = QueryCssBPO.findAll(connection, sql, null);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("QYYH_DM", rs.getString("QYYH_DM"));
			map.put("QYYH_MC", rs.getString("QYYH_MC"));
			list.add(map);
		}
		responseEvent.respMapParam.put("list", list);
		return responseEvent;
	}

	@Override
	protected ResponseEvent validateData(RequestEvent arg0, Connection arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
