package gov.jslt.taxcore.taxblh.yhgl.yhgl003;

import gov.jslt.taxcore.taxblh.comm.CoreHelper;
import gov.jslt.taxcore.taxblh.comm.MD5Helper;
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
import com.ctp.core.bpo.QueryBPO;
import com.ctp.core.bpo.QueryCssBPO;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;

public class Yhgl003BLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		if ("initPage".equals(reqEvent.getDealMethod())) {
			return initPage(reqEvent, conn);
		} else if ("submitForm".equals(reqEvent.getDealMethod())) {
			return submitForm(reqEvent, conn);
		} else if ("queryData".equals(reqEvent.getDealMethod())) {
			return queryData(reqEvent, conn);
		} else if ("zxUser".equals(reqEvent.getDealMethod())) {
			return zxUser(reqEvent, conn);
		}
		return null;
	}

	private ResponseEvent zxUser(RequestEvent reqEvent, Connection conn)
			throws SQLException {
		ResponseEvent resEvent = new ResponseEvent();
		JsonReqData reqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		String sql = "UPDATE T_YS_LOGIN SET XY_BJ='0' WHERE UUID=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, (String) reqData.getData().get("uuid"));
		pstmt.executeUpdate();
		pstmt.close();

		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(reqData.getData().get("rqq"));
		sqlParams.add(reqData.getData().get("rqz"));
		sql = "SELECT T.UUID,T.XM,T.SJHM,A.YHLX_MC,B.QYYH_MC,TO_CHAR(T.LR_SJ,'YYYY-MM-DD HH24:MI:SS') LR_SJ FROM  T_YS_LOGIN T,T_DM_YS_YHLX A,T_DM_YS_QYYH B WHERE  T.YHLX_DM=A.YHLX_DM AND T.XY_BJ='1' AND T.QYYH_DM=B.QYYH_DM(+) AND T.LR_SJ > TO_DATE(? || ' 00:00:01', 'YYYY-MM-DD HH24:MI:SS') AND T.LR_SJ < TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') ORDER BY LR_SJ";
		CachedRowSet rs = QueryBPO.findAll(conn, sql, sqlParams);
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		Map<String, String> dataMap;
		while (rs.next()) {
			dataMap = new HashMap<String, String>();
			dataMap.put("UUID", rs.getString("UUID"));
			dataMap.put("XM", rs.getString("XM"));
			dataMap.put("SJHM", rs.getString("SJHM"));
			dataMap.put("YHLX_MC", rs.getString("YHLX_MC"));
			dataMap.put("QYYH_MC", rs.getString("QYYH_MC"));
			dataMap.put("LR_SJ", rs.getString("LR_SJ"));
			dataList.add(dataMap);
		}
		resEvent.respMapParam.put("dataList", dataList);
		resEvent.setReponseMesg("注销成功");
		return resEvent;
	}

	private ResponseEvent queryData(RequestEvent reqEvent, Connection conn)
			throws SQLException {
		ResponseEvent resEvent = new ResponseEvent();
		JsonReqData reqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(reqData.getData().get("rqq"));
		sqlParams.add(reqData.getData().get("rqz"));
		String sql = "SELECT T.UUID,T.XM,T.SJHM,A.YHLX_MC,B.QYYH_MC,TO_CHAR(T.LR_SJ,'YYYY-MM-DD HH24:MI:SS') LR_SJ FROM  T_YS_LOGIN T,T_DM_YS_YHLX A,T_DM_YS_QYYH B WHERE  T.YHLX_DM=A.YHLX_DM  AND T.XY_BJ='1' AND T.QYYH_DM=B.QYYH_DM(+) AND T.LR_SJ > TO_DATE(? || ' 00:00:01', 'YYYY-MM-DD HH24:MI:SS') AND T.LR_SJ < TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') ORDER BY LR_SJ";
		CachedRowSet rs = QueryBPO.findAll(conn, sql, sqlParams);
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		Map<String, String> dataMap;
		while (rs.next()) {
			dataMap = new HashMap<String, String>();
			dataMap.put("UUID", rs.getString("UUID"));
			dataMap.put("XM", rs.getString("XM"));
			dataMap.put("SJHM", rs.getString("SJHM"));
			dataMap.put("YHLX_MC", rs.getString("YHLX_MC"));
			dataMap.put("QYYH_MC", rs.getString("QYYH_MC"));
			dataMap.put("LR_SJ", rs.getString("LR_SJ"));
			dataList.add(dataMap);
		}
		resEvent.respMapParam.put("dataList", dataList);
		resEvent.setReponseMesg("查询成功");
		return resEvent;
	}

	private ResponseEvent initPage(RequestEvent reqEvent, Connection conn)
			throws SQLException {
		ResponseEvent resEvent = new ResponseEvent();
		String sql = "SELECT T.QYYH_DM, T.QYYH_MC FROM T_DM_YS_QYYH T WHERE T.XY_BJ = '1'";
		CachedRowSet rs = QueryCssBPO.findAll(conn, sql, null);
		List<Map<String, String>> qyYhList = new ArrayList<Map<String, String>>();
		Map<String, String> tmpMap = null;
		while (rs.next()) {
			tmpMap = new HashMap<String, String>();
			tmpMap.put("id", rs.getString("QYYH_DM"));
			tmpMap.put("text", rs.getString("QYYH_MC"));
			qyYhList.add(tmpMap);
		}
		resEvent.respMapParam.put("qyYhList", qyYhList);
		return resEvent;
	}

	/**
	 * @param reqEvent
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	private ResponseEvent submitForm(RequestEvent reqEvent, Connection conn)
			throws SQLException {
		ResponseEvent resEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		String uuid = CoreHelper.getGUID(conn);
		String sjHm = (String) jsonReqData.getData().get("sjHm");
		String pwd = (String) jsonReqData.getData().get("pwd");
		String xm = (String) jsonReqData.getData().get("xm");
		String zjlxDm = "";// (String) jsonReqData.getData().get("zjlxDm");
		String zjHm = "";// (String) jsonReqData.getData().get("zjHm");
		String yhlxDm = (String) jsonReqData.getData().get("yhlxDm");
		String qyyhDm = (String) jsonReqData.getData().get("qyyhDm");
		String sql = "SELECT * FROM T_YS_LOGIN WHERE SJHM=?";
		ArrayList<String> sqlParams = new ArrayList<String>();
		sqlParams.add(sjHm);
		CachedRowSet rs = QueryCssBPO.findAll(conn, sql, sqlParams);
		if (rs.next()) {
			resEvent.setRepCode("ZB0006");
			resEvent.setReponseMesg(CoreHelper.getJyztMc(conn, "ZB0006"));
			return resEvent;
		}
		if ("02".equals(yhlxDm)) {
			qyyhDm = "";
		}
		sql = "INSERT INTO T_YS_LOGIN (UUID,SJHM,LOGPASS_MD5, LOGPASS,XM,SFZMLX_DM,SFZHM,YHLX_DM,QYYH_DM,XY_BJ,LRRY_DM,LR_SJ,XG_SJ)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,'admin',SYSDATE,NULL)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, uuid);
		pstmt.setString(2, sjHm);
		pstmt.setString(3, MD5Helper.MD5(pwd));
		pstmt.setString(4, pwd);
		pstmt.setString(5, xm);
		pstmt.setString(6, zjlxDm);
		pstmt.setString(7, zjHm);
		pstmt.setString(8, yhlxDm);
		pstmt.setString(9, qyyhDm);
		pstmt.setString(10, "1");
		pstmt.execute();
		pstmt.close();
		resEvent.setReponseMesg("添加成功");
		return resEvent;
	}

	@Override
	protected ResponseEvent validateData(RequestEvent arg0, Connection arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
