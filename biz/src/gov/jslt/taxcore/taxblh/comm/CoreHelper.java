package gov.jslt.taxcore.taxblh.comm;

import gov.jslt.taxevent.comm.GeneralCons;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.bpo.QueryCssBPO;

public class CoreHelper {

	public static String getGUID(Connection con) throws SQLException {
		String guid = "";
		String getGuidSql = "select  F_XT_GET_GUID() as uuid from dual";
		CachedRowSet rs = QueryCssBPO.findAll(con, getGuidSql, null);
		rs.next();
		guid = rs.getString("uuid");

		return guid;
	}

	public static Map<String, String> getFtp(String gljgdm, Connection conn)
			throws SQLException {
		Map<String, String> map = new HashMap<String, String>();
		String sql = "SELECT S.CSZ,TO_CHAR(SYSDATE,'YYYYMMDD') AS RQ FROM T_XT_XTCS S WHERE S.CSBM ='ZSBSFTP' ";
		CachedRowSet rs = QueryCssBPO.findAll(conn, sql, null, null);
		if (rs.next()) {
			map.put("ml", gljgdm.substring(0, 5) + "/" + rs.getString("RQ"));
			String[] temp = rs.getString("CSZ").split(":");
			map.put("ftpIp", temp[0]);
			map.put("ftpUser", temp[1]);
			map.put("ftpPwd", temp[2]);
		}
		return map;
	}

	public static String getDateTime(Connection con, String format)
			throws SQLException {
		String dateTime = "";
		if (format == null) {
			format = "YYYY-MM-DD HH24:MI:SS";
		}
		String getGuidSql = "SELECT  TO_CHAR(SYSTIMESTAMP,'" + format
				+ "') AS  DATETIME FROM DUAL";
		CachedRowSet rs = QueryCssBPO.findAll(con, getGuidSql, null);
		rs.next();
		dateTime = rs.getString("DATETIME");
		return dateTime;
	}

	public static String getJyztMc(Connection con, String jyztDm)
			throws SQLException {
		if (null == jyztDm || "".equals(jyztDm)) {
			return GeneralCons.SUCCESS_MSG_ZB7777;
		} else {
			ArrayList sqlParams = new ArrayList();
			sqlParams.add(jyztDm);
			CachedRowSet rs = QueryCssBPO.findAll(con,
					"SELECT JYZT_MC FROM T_DM_YS_JYZT D WHERE JYZT_DM=?",
					sqlParams);
			if (rs.next()) {
				return rs.getString("JYZT_MC");
			} else {
				return GeneralCons.SUCCESS_MSG_ZB7777;
			}
		}
	}

	public static String empty(Object str) {
		if (null == str || "null".equals(str)) {
			return "";
		}
		return str.toString();
	}

	public static List<Map<String, String>> queryJsgs(String pzzlDm,
			String xtLx, Connection conn) throws SQLException {
		ArrayList<String> sqlParams = new ArrayList<String>();
		sqlParams.add(pzzlDm);
		sqlParams.add(xtLx);
		String sql = "select T.GS,T.PXZD,T.GSLX,T.TYPE from T_CS_WB_YMJSGS T WHERE PZZL_DM=? AND XY_BJ='1' AND XTLX=?  ORDER BY T.PXZD";
		CachedRowSet rs = QueryCssBPO.findAll(conn, sql, sqlParams);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("GS", rs.getString("GS"));
			map.put("PXZD", rs.getString("PXZD"));
			map.put("GSLX", rs.getString("GSLX"));
			map.put("TYPE", rs.getString("TYPE"));
			list.add(map);
		}
		return list;
	}

}
