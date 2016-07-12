package gov.jslt.taxcore.taxblh.swd.swd003;

import gov.jslt.taxevent.comm.JsonReqData;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

public class Swd003BLH extends BaseBizLogicHandler {

	protected ResponseEvent performTask(RequestEvent req, Connection conn)
			throws SQLException, TaxBaseBizException {
		String handleCode = req.getDealMethod();
		if ("queryData".equals(handleCode)) {
			return queryData(req, conn);
		} else if ("saveData".equals(handleCode)) {
			return saveData(req, conn);
		}
		return null;
	}

	protected ResponseEvent validateData(RequestEvent req, Connection conn)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	protected ResponseEvent saveData(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData reqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		String QYYH_DM = null;
		PreparedStatement pt;
		if (null == reqData.getData().get("QYYH_DM")
				|| "".equals(reqData.getData().get("QYYH_DM").toString())) {
			ArrayList<Object> sqlParam = new ArrayList<Object>();
			CachedRowSet rs;
			String sql = "SELECT CASE WHEN  MAX(QYYH_DM)+1 IS NULL THEN 1 ELSE MAX(QYYH_DM)+1 END  AS QYYH_DM FROM T_DM_YS_QYYH";
			rs = QueryCssBPO.findAll(conn, sql, sqlParam);
			rs.next();
			QYYH_DM = rs.getString("QYYH_DM");
			if (QYYH_DM.length() == 1) {
				QYYH_DM = "0" + QYYH_DM;
			}
		} else {
			QYYH_DM = reqData.getData().get("QYYH_DM").toString();
			pt = conn
					.prepareStatement("DELETE FROM  T_DM_YS_QYYH WHERE QYYH_DM=?");
			pt.setString(1, QYYH_DM);
			pt.execute();
		}
		pt = conn
				.prepareStatement("INSERT INTO T_DM_YS_QYYH(QYYH_DM,QYYH_MC,XY_BJ) VALUES(?,?,?)");
		pt.setString(1, QYYH_DM);
		pt.setString(2, reqData.getData().get("QYYH_MC").toString());
		pt.setString(3, reqData.getData().get("XY_BJ").toString());
		pt.execute();
		return responseEvent;
	}

	protected ResponseEvent queryData(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		ArrayList<Object> sqlParam = new ArrayList<Object>();
		CachedRowSet rs;
		String sql = "SELECT * FROM T_DM_YS_QYYH ORDER BY XY_BJ DESC";
		rs = QueryCssBPO.findAll(conn, sql, sqlParam);
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		Map<String, String> dataMap = null;
		while (rs.next()) {
			dataMap = new HashMap<String, String>();
			dataMap.put("QYYH_DM", rs.getString("QYYH_DM"));
			dataMap.put("QYYH_MC", rs.getString("QYYH_MC"));
			dataMap.put("XY_BJ", rs.getString("XY_BJ"));
			dataList.add(dataMap);
		}
		responseEvent.respMapParam.put("dataList", dataList);
		return responseEvent;
	}

}
