package gov.jslt.taxcore.taxblh.yhgl;

import gov.jslt.taxcore.taxblh.comm.DxTools;
import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxcore.taxbpo.yhgl.YhGxxxBPO;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.JsonResData;
import gov.jslt.taxevent.comm.LoginVO;
import gov.jslt.taxevent.comm.MD5Helper;
import gov.jslt.taxevent.comm.UUIDGenerator;
import gov.jslt.taxevent.yhgl.YhGxxxVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.bpo.QueryBPO;
import com.ctp.core.bpo.QueryCssBPO;
import com.ctp.core.bpo.StoredProcManager;
import com.ctp.core.bpo.StoredProcParamObj;
import com.ctp.core.config.ApplicationContext;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.cssesb.esbevent.ESBRequestEvent;

public class Yh001BLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		String delMethod = req.getDealMethod();
		if ("hqYzm".equals(delMethod)) {// 用户注册，获取验证码
			return hqYzm(req, con);
		} else if ("yhYz".equals(delMethod)) {// 用户校验
			return yhYz(req, con);
		} else if ("mmxg".equals(delMethod)) {// 密码修改
			return mmxg(req, con);
		} else if ("queryGsgg".equals(delMethod)) {
			return queryGsgg(req, con);
		} else if ("queryGsggByParam".equals(delMethod)) {
			return queryGsggByParam(req, con);
		} else if ("queryGsggContext".equals(delMethod)) {
			return queryGsggContext(req, con);
		} else if ("yhZc".equals(delMethod)) {// 用户注册
			return yhZc(req, con);
		} else if ("findPwd".equals(delMethod)) {// 找回密码
			return findPwd(req, con);
		} else if ("queryData".equals(delMethod)) {// 获取绑定的企业信息
			return queryData(req, con);
		} else if ("updateData".equals(delMethod)) {// 修改纳税人默认企业信息
			return updateData(req, con);
		} else if ("sendMsg".equals(delMethod)) {// 修改注册姓名，发送验证码
			return sendMsg(req, con);
		} else if ("updateMc".equals(delMethod)) {// 修改注册姓名
			return updateMc(req, con);
		} else if ("getYhxx".equals(delMethod)) {
			return getYhxx(req, con);
		} else if ("uploadIcon".equals(delMethod)) {
			return uploadIcon(req, con);
		} else if ("getYzm".equals(delMethod)) {
			return getYzm(req, con);
		} else if ("updateSjHm".equals(delMethod)) {// 修改手机号码
			return updateSjHm(req, con);
		}
		return null;
	}

	private ResponseEvent getYzm(RequestEvent reqEvent, Connection conn)
			throws SQLException {
		ResponseEvent resEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		String sjHm = jsonReqData.getSjHm();
		String sjYzm = RandomStringUtils.randomNumeric(4);
		// 1.校验新手机号是否被注册
		ArrayList<String> sqlParams = new ArrayList<String>();
		sqlParams.add(jsonReqData.getYhwybz());
		sqlParams.add(sjHm);
		CachedRowSet rs = QueryBPO
				.findAll(
						conn,
						"SELECT COUNT(*) AS NUM FROM T_ZB_LOGIN WHERE UUID <> ? AND SJHM = ?",
						sqlParams);
		rs.next();
		if (rs.getInt("NUM") > 0) {
			resEvent.setRepCode("ZB0040");
			resEvent.setReponseMesg(ZBCoreHelper.getJyztMc(conn, "ZB0040"));
			return resEvent;
		}

		// 2.发送验证码
		JsonResData jsonResData = DxTools.sendYzm(conn, sjHm, sjYzm,
				DxTools.getYzmMb("进行自然人实名办税平台手机号码变更操作", sjYzm, "10"));
		// 3.记录验证码
		if ("0".equals(jsonResData.getCode())) {
			PreparedStatement pstmt = conn
					.prepareStatement("DELETE FROM DB_ZSBS.T_ZB_YZM WHERE SJHM = ?");
			pstmt.setString(1, sjHm);
			pstmt.executeUpdate();
			pstmt = conn
					.prepareStatement("INSERT INTO DB_ZSBS.T_ZB_YZM (UUID, SJHM, YZM, YXSJZ, LR_SJ) VALUES (?, ?, ?, SYSDATE + ((1 / 24 / 60) * 10), SYSTIMESTAMP)");
			pstmt.setString(1, ZBCoreHelper.getGUID(conn));
			pstmt.setString(2, sjHm);
			pstmt.setString(3, sjYzm);
			pstmt.executeUpdate();
			pstmt.close();
		}
		resEvent.setRepCode(jsonResData.getCode());
		resEvent.setReponseMesg(jsonResData.getMsg());
		return resEvent;
	}

	private ResponseEvent updateSjHm(RequestEvent reqEvent, Connection conn)
			throws SQLException {
		ResponseEvent resEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		String sjHm = jsonReqData.getSjHm();
		String sjYzm = (String) jsonReqData.getData().get("sjYzm");
		String sjHm2 = (String) jsonReqData.getData().get("sjHm2");
		String sjYzm2 = (String) jsonReqData.getData().get("sjYzm2");

		// 1.校验原手机验证码
		String sql = "SELECT COUNT(A.YZM) AS NUM FROM T_ZB_YZM A WHERE A.SJHM=? AND A.YZM = ? AND A.YXSJZ > SYSDATE";
		ArrayList<String> sqlParams = new ArrayList<String>();
		sqlParams.add(sjHm);
		sqlParams.add(sjYzm);
		CachedRowSet rs = QueryBPO.findAll(conn, sql, sqlParams);
		rs.next();
		if (rs.getInt("NUM") == 0) {
			resEvent.setRepCode("ZB0038");
			resEvent.setReponseMesg(ZBCoreHelper.getJyztMc(conn, "ZB0038"));
			return resEvent;
		}

		// 2.校验新手机验证码
		sqlParams = new ArrayList<String>();
		sqlParams.add(sjHm2);
		sqlParams.add(sjYzm2);
		rs = QueryBPO.findAll(conn, sql, sqlParams);
		rs.next();
		if (rs.getInt("NUM") == 0) {
			resEvent.setRepCode("ZB0039");
			resEvent.setReponseMesg(ZBCoreHelper.getJyztMc(conn, "ZB0039"));
			return resEvent;
		}

		// 保存新手机号码
		PreparedStatement pstmt = conn
				.prepareStatement("UPDATE T_ZB_LOGIN A SET A.SJHM = ? WHERE UUID = ?");
		pstmt.setString(1, sjHm2);
		pstmt.setString(2, jsonReqData.getYhwybz());
		pstmt.execute();
		pstmt.close();

		return resEvent;
	}

	private ResponseEvent uploadIcon(RequestEvent req, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		String iconStr = (String) jsonReqData.getData().get("iconStr");
		YhGxxxVO yhGxxxVO = new YhGxxxVO();
		yhGxxxVO.setUuid(jsonReqData.getYhwybz());
		yhGxxxVO.setIconStr(iconStr);
		yhGxxxVO.setQq("");
		yhGxxxVO.setWx("");
		yhGxxxVO.setZfb("");
		yhGxxxVO.setWb("");
		YhGxxxBPO.deleteByPK(conn, jsonReqData.getYhwybz());
		YhGxxxBPO.insert(conn, yhGxxxVO);
		return responseEvent;
	}

	private ResponseEvent getYhxx(RequestEvent req, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		String yhwybz = jsonReqData.getYhwybz();
		HttpServletRequest request = (HttpServletRequest) req.reqMapParam
				.get("HttpServletRequest");
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute(yhwybz);
		// 操作历史
		String sql = "SELECT '您在 ' || TO_CHAR(A.LR_SJ, 'YYYY-MM-DD HH24:MI:SS') || ' ' || C.CZFL_MC AS CZINFO FROM T_ZB_CZLOG A, T_ZB_LOGIN B, T_DM_ZB_CZFL C"
				+ " WHERE A.UUID = B.UUID AND A.CZFL_DM = C.CZFL_DM AND B.SFZMLX_DM = ? AND B.SFZHM = ? ORDER BY A.LR_SJ DESC";
		ArrayList<String> sqlParams = new ArrayList<String>();
		sqlParams.add(loginVO.getZjLx());
		sqlParams.add(loginVO.getZjHm());
		CachedRowSet rs = QueryBPO.findAll(conn, sql, sqlParams);
		List<String> czInfo = new ArrayList<String>();
		while (rs.next()) {
			czInfo.add(rs.getString("CZINFO"));
		}
		Map<String, String> rzData = new HashMap<String, String>();
		// 认证方式名称
		if (!"".equals(loginVO.getRzFs())) {
			String[] rzfs = loginVO.getRzFs().split("\\|");
			for (int i = 0; i < rzfs.length; i++) {
				sqlParams = new ArrayList<String>();
				sqlParams.add(rzfs[i]);
				sql = "SELECT RZFS_DM, RZFS_MC FROM T_DM_ZB_RZFS WHERE RZFS_DM =? AND XY_BJ = '1'";
				rs = QueryBPO.findAll(conn, sql, sqlParams);
				if (rs.next()) {
					rzData.put(rs.getString("RZFS_DM"), rs.getString("RZFS_MC"));
				}
			}
		}

		// 用户个性信息
		YhGxxxVO yhGxxxVO = YhGxxxBPO.queryByPK(conn, jsonReqData.getYhwybz());
		responseEvent.respMapParam.put("yhGxxxVO", yhGxxxVO);
		responseEvent.respMapParam.put("czInfo", czInfo);
		responseEvent.respMapParam.put("rzData", rzData);
		return responseEvent;

	}

	protected ResponseEvent updateMc(RequestEvent req, Connection con)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(dataMap.get("zjlx"));
		sqlParams.add(dataMap.get("zjhm"));
		sqlParams.add(jsonReqData.getSjHm());
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT COUNT(L.UUID) NUM FROM DB_ZSBS.T_ZB_LOGIN L WHERE L.SFZMLX_DM = ? AND L.SFZHM = ? AND L.SJHM = ? AND L.XY_BJ = '1'",
						sqlParams);
		rs.next();
		if (0 < rs.getInt("NUM")) {
			// 校验验证码正确性
			sqlParams = new ArrayList<Object>();
			sqlParams.add(jsonReqData.getSjYzm());
			sqlParams.add(jsonReqData.getSjHm());
			rs = QueryCssBPO
					.findAll(
							con,
							"SELECT COUNT(Y.YZM) NUM FROM DB_ZSBS.T_ZB_YZM Y WHERE Y.YZM = ? AND Y.SJHM = ? AND Y.YXSJZ > SYSDATE",
							sqlParams);
			rs.next();
			if (0 < rs.getInt("NUM")) {
				PreparedStatement pstmt = con
						.prepareStatement("UPDATE DB_ZSBS.T_ZB_LOGIN SET XM = ? WHERE SJHM = ? AND SFZMLX_DM = ? AND SFZHM = ? AND XY_BJ = '1'");
				pstmt.setString(1, jsonReqData.getXm());
				pstmt.setString(2, jsonReqData.getSjHm());
				pstmt.setString(3, dataMap.get("zjlx").toString());
				pstmt.setString(4, dataMap.get("zjhm").toString());
				pstmt.executeUpdate();
				pstmt.close();
				responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
				responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
			} else {
				responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB0016);
				responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(con,
						GeneralCons.ERROR_CODE_ZB0016));
			}
		} else {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB0029);
			responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(con,
					GeneralCons.ERROR_CODE_ZB0029));
		}
		return responseEvent;
	}

	protected ResponseEvent sendMsg(RequestEvent req, Connection con)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(dataMap.get("zjlx"));
		sqlParams.add(dataMap.get("zjhm"));
		sqlParams.add(jsonReqData.getSjHm());
		String yzm = RandomStringUtils.randomNumeric(4);
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT COUNT(L.UUID) NUM FROM DB_ZSBS.T_ZB_LOGIN L WHERE L.SFZMLX_DM = ? AND L.SFZHM = ? AND L.SJHM = ? AND L.XY_BJ = '1'",
						sqlParams);
		rs.next();
		if (0 < rs.getInt("NUM")) {
			PreparedStatement pstmt = con
					.prepareStatement("DELETE FROM DB_ZSBS.T_ZB_YZM WHERE SJHM = ?");
			pstmt.setString(1, jsonReqData.getSjHm());
			pstmt.executeUpdate();
			pstmt = con
					.prepareStatement("INSERT INTO DB_ZSBS.T_ZB_YZM (UUID, SJHM, YZM, YXSJZ, LR_SJ) VALUES (?, ?, ?, SYSDATE + ((1 / 24 / 60) * 10), SYSTIMESTAMP)");
			pstmt.setString(1, ZBCoreHelper.getGUID(con));
			pstmt.setString(2, jsonReqData.getSjHm());
			pstmt.setString(3, yzm);
			pstmt.executeUpdate();
			pstmt.close();
			// 调用发送短信ESB
			ESBRequestEvent reqEvent = new ESBRequestEvent("ZBdxyzBLH", "", "");
			reqEvent.setSjb("");
			reqEvent.setDealMethod("query");
			reqEvent.setReqMapParam(new HashMap<>());
			reqEvent.reqMapParam.put("sjhm", jsonReqData.getSjHm());
			reqEvent.reqMapParam.put("zjlx", dataMap.get("zjlx"));
			reqEvent.reqMapParam.put("zjhm", dataMap.get("zjhm"));
			reqEvent.reqMapParam.put("gljgdm", dataMap.get("gljgdm"));
			reqEvent.reqMapParam.put("yzm", "您正在进行修改注册姓名操作，本次验证码为：" + yzm
					+ "，有效时间为10分钟");
			try {
				responseEvent = ZBCoreHelper.getEsbResponseEvent(reqEvent, con);
				responseEvent.setRepCode(responseEvent.respMapParam.get("code")
						.toString());
				responseEvent.setReponseMesg(responseEvent.respMapParam.get(
						"msg").toString());
			} catch (Exception e) {
				responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
				responseEvent
						.setReponseMesg(JyxxJgbHelper.queryJyxxx(ZBCoreHelper
								.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
								e.getMessage()));
				return responseEvent;
			}
		} else {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB0029);
			responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(con,
					GeneralCons.ERROR_CODE_ZB0029));
		}
		return null;
	}

	protected ResponseEvent updateData(RequestEvent req, Connection con)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		// 设置选中企业首选标记为1
		PreparedStatement pstmt = con
				.prepareStatement("UPDATE DB_ZSBS.T_ZB_LOGIN_BDXX SET SX_BJ = '1' WHERE UUID = ? AND MX_XH = ? AND XY_BJ = '1'");
		pstmt.setString(1, dataMap.get("uuid").toString());
		pstmt.setString(2, dataMap.get("mx_xh").toString());
		pstmt.executeUpdate();
		// 设置其他企业首选标记为0
		pstmt = con
				.prepareStatement("UPDATE DB_ZSBS.T_ZB_LOGIN_BDXX SET SX_BJ = '0' WHERE UUID = ? AND MX_XH <> ? AND XY_BJ = '1'");
		pstmt.setString(1, dataMap.get("uuid").toString());
		pstmt.setString(2, dataMap.get("mx_xh").toString());
		pstmt.executeUpdate();
		pstmt.close();
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	protected ResponseEvent queryData(RequestEvent req, Connection con)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(dataMap.get("zjlx"));
		sqlParams.add(dataMap.get("zjhm"));
		sqlParams.add(jsonReqData.getSjHm());
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT L.UUID FROM DB_ZSBS.T_ZB_LOGIN L WHERE L.SFZMLX_DM = ? AND L.SFZHM = ? AND L.SJHM = ? AND L.XY_BJ = '1'",
						sqlParams);
		if (rs.next()) {
			String uuid = rs.getString("UUID");
			sqlParams = new ArrayList<Object>();
			sqlParams.add(uuid);
			rs = QueryCssBPO
					.findAll(
							con,
							"SELECT * FROM DB_ZSBS.T_ZB_LOGIN_BDXX LB WHERE LB.UUID = ? AND LB.XY_BJ = '1'",
							sqlParams);
			Map<String, String> qyInfoMap;
			List<Map<String, String>> qyInfoList = new ArrayList<Map<String, String>>();
			while (rs.next()) {
				qyInfoMap = new HashMap<String, String>();
				qyInfoMap.put("UUID", rs.getString("UUID"));
				qyInfoMap.put("MX_XH", rs.getString("MX_XH"));
				qyInfoMap.put("SWGLM", rs.getString("SWGLM"));
				qyInfoMap.put("BD_RQ", rs.getString("BD_RQ"));
				qyInfoMap.put("DJLX_DM", rs.getString("DJLX_DM"));
				qyInfoMap.put("MC", rs.getString("MC"));
				qyInfoMap.put("EJYH_LOGINID", rs.getString("EJYH_LOGINID"));
				qyInfoMap.put("NSRSBH", rs.getString("NSRSBH"));
				qyInfoMap.put("SX_BJ", rs.getString("SX_BJ"));
				qyInfoList.add(qyInfoMap);
			}
			responseEvent.respMapParam.put("qyInfoList", qyInfoList);
			responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
			responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		} else {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB0029);
			responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(con,
					GeneralCons.ERROR_CODE_ZB0029));
		}
		return responseEvent;
	}

	protected ResponseEvent queryGsggContext(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(new StoredProcParamObj(1, jsonReqData.getData().get(
				"swglm"), StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(2,
				jsonReqData.getData().get("xh"), StoredProcParamObj.IN,
				java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(3, jsonReqData.getData().get(
				"mxXh"), StoredProcParamObj.IN, java.sql.Types.NUMERIC));
		StoredProcManager.callStoreProcess(con,
				"{call P_ZB_YH001_QUERYGSGGCONTEXT(?,?,?)}", sqlParams);
		CachedRowSet rs = QueryCssBPO.findAll(con,
				"SELECT T.* FROM DB_WSBS.T_LS_XXTSJLB_NEW T", null);
		Map<String, String> map;
		rs.next();
		map = new HashMap<String, String>();
		map.put("XH", rs.getString("XH"));
		map.put("MXXH", rs.getString("MXXH"));
		map.put("TITLE", rs.getString("TITLE"));
		map.put("CONTENT", rs.getString("CONTENT"));
		map.put("TSJG", rs.getString("TSJG"));
		map.put("YXYF", rs.getString("YXYF"));
		map.put("JSJG", rs.getString("JSJG"));
		responseEvent.respMapParam.put("contentInfo", map);
		rs = QueryCssBPO.findAll(con, "SELECT T.* FROM  DB_ZSBS.T_LS_FJFILE T",
				null);
		List list = new ArrayList<>();
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("BIZID", rs.getString("BIZID"));
			map.put("XH", rs.getString("XH"));
			map.put("WJM", rs.getString("WJM"));
			map.put("URL",
					ApplicationContext.singleton().getValueAsString(
							"zsbs.server.www")
							+ "/WBfjxzAction.do?BIZID="
							+ rs.getString("BIZID")
							+ "&XH=" + rs.getString("XH") + "");
			list.add(map);
		}
		responseEvent.respMapParam.put("fileInfo", list);
		rs = QueryCssBPO.findAll(con, "SELECT T.* FROM DB_ZSBS.T_ZB_LS_JYZT T",
				null);
		rs.next();
		responseEvent.setRepCode(rs.getString("JYZT_DM"));
		responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
				rs.getString("JYZT_MC"), rs.getString("SJNR")));
		return responseEvent;
	}

	protected ResponseEvent queryGsggByParam(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(new StoredProcParamObj(1, jsonReqData.getData().get(
				"swglm"), StoredProcParamObj.IN, java.sql.Types.NUMERIC));
		sqlParams.add(new StoredProcParamObj(2, jsonReqData.getData().get(
				"gljgDm"), StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(3, jsonReqData.getData().get(
				"yxYf"), StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(4, jsonReqData.getData()
				.get("gjz"), StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		StoredProcManager.callStoreProcess(con,
				"{call P_ZB_YH001_QUERYGSGGBYPARAM(?,?,?,?)}", sqlParams);
		CachedRowSet rs = QueryCssBPO.findAll(con,
				"SELECT T.* FROM DB_WSBS.T_LS_XXTSJLB_NEW T", null);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("XH", rs.getString("XH"));
			map.put("MXXH", rs.getString("MXXH"));
			map.put("TITLE", rs.getString("TITLE"));
			list.add(map);
		}
		responseEvent.respMapParam.put("list", list);
		rs = QueryCssBPO.findAll(con, "SELECT T.* FROM DB_ZSBS.T_ZB_LS_JYZT T",
				null);
		rs.next();
		responseEvent.setRepCode(rs.getString("JYZT_DM"));
		responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
				rs.getString("JYZT_MC"), rs.getString("SJNR")));
		return responseEvent;
	}

	protected ResponseEvent queryGsgg(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(new StoredProcParamObj(1, jsonReqData.getData().get(
				"swglm"), StoredProcParamObj.IN, java.sql.Types.NUMERIC));
		sqlParams.add(new StoredProcParamObj(2, jsonReqData.getData().get(
				"gljgDm"), StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		StoredProcManager.callStoreProcess(con,
				"{call P_ZB_YH001_QUERYGSGG(?,?)}", sqlParams);
		CachedRowSet rs = QueryCssBPO.findAll(con,
				"SELECT T.* FROM DB_WSBS.T_LS_XXTSJLB_NEW T", null);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		while (rs.next()) {
			if (list.size() == 3) {
				break;
			}
			map = new HashMap<String, String>();
			map.put("XH", rs.getString("XH"));
			map.put("MXXH", rs.getString("MXXH"));
			map.put("TITLE", rs.getString("TITLE"));
			list.add(map);
		}
		responseEvent.respMapParam.put("list", list);
		rs = QueryCssBPO.findAll(con, "SELECT T.* FROM DB_ZSBS.T_ZB_LS_JYZT T",
				null);
		rs.next();
		responseEvent.setRepCode(rs.getString("JYZT_DM"));
		responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
				rs.getString("JYZT_MC"), rs.getString("SJNR")));
		return responseEvent;
	}

	protected ResponseEvent yhYz(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");

		String platform = jsonReqData.getPlatform();
		if (!StringUtils.upperCase(platform).equals("IOS")) {// 登录系统不是IOS
			// 信息校验
			String md5 = jsonReqData.getMd5();
			String cert = jsonReqData.getCert();
			Map<String, String> map = new HashMap<String, String>();
			map.put("md5", md5);
			map.put("cert", cert);
			if (!ZBCoreHelper.xxjy(con, map)) {
				responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB0023);
				responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(con,
						GeneralCons.ERROR_CODE_ZB0023));
				return responseEvent;
			}
		}
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(new StoredProcParamObj(1, jsonReqData.getSjHm(),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams
				.add(new StoredProcParamObj(2, MD5Helper.MD5(jsonReqData
						.getPassWord()), StoredProcParamObj.IN,
						java.sql.Types.VARCHAR));
		StoredProcManager.callStoreProcess(con, "{call P_ZB_YH001_YHYZ(?,?)}",
				sqlParams);
		CachedRowSet rs = QueryCssBPO.findAll(con,
				"SELECT T.* FROM DB_ZSBS.T_LS_LOGIN_BDXX T", null);
		List<Map<String, String>> qyList = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("UUID", rs.getString("UUID"));
			map.put("SWGLM", rs.getString("SWGLM"));
			map.put("BD_RQ", rs.getString("BD_RQ"));
			map.put("DJLX_DM", rs.getString("DJLX_DM"));
			map.put("MC", rs.getString("MC"));
			map.put("EJYH_LOGINID", rs.getString("EJYH_LOGINID"));
			map.put("NSRSBH", rs.getString("NSRSBH"));
			map.put("SX_BJ", rs.getString("SX_BJ"));

			map.put("GLJG_DM", rs.getString("GLJG_DM"));
			map.put("JCJG_DM", rs.getString("JCJG_DM"));
			map.put("FWJG_DM", rs.getString("FWJG_DM"));
			map.put("ZSJG_DM", rs.getString("ZSJG_DM"));
			map.put("JIANCJG_DM", rs.getString("JIANCJG_DM"));
			map.put("SKSSJG_DM", rs.getString("SKSSJG_DM"));
			qyList.add(map);
		}
		responseEvent.respMapParam.put("lastLoginDate",
				ZBCoreHelper.getDateTime(con, "YYYY-MM-DD"));
		responseEvent.respMapParam.put("qyList", qyList);
		rs = QueryCssBPO.findAll(con, "SELECT T.* FROM DB_ZSBS.T_LS_LOGIN T",
				null);
		if (rs.next()) {
			responseEvent.respMapParam.put("SJHM", rs.getString("SJHM"));
			responseEvent.respMapParam.put("LOGPASS", rs.getString("LOGPASS"));
			responseEvent.respMapParam.put("LOGPASS_MD5",
					rs.getString("LOGPASS_MD5"));
			responseEvent.respMapParam.put("SFZMLX_DM",
					rs.getString("SFZMLX_DM"));
			responseEvent.respMapParam.put("SFZHM", rs.getString("SFZHM"));
			responseEvent.respMapParam.put("UUID", rs.getString("UUID"));
			responseEvent.respMapParam.put("XM", rs.getString("XM"));
		}
		rs = QueryCssBPO.findAll(con, "SELECT T.* FROM DB_ZSBS.T_ZB_LS_JYZT T",
				null);
		rs.next();
		responseEvent.setRepCode(rs.getString("JYZT_DM"));
		responseEvent.setReponseMesg(rs.getString("JYZT_MC"));
		return responseEvent;
	}

	protected ResponseEvent findPwd(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(jsonReqData.getSjHm());
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT L.LOGPASS, L.SFZMLX_DM, L.SFZHM FROM T_ZB_LOGIN L WHERE L.SJHM = ? AND L.XY_BJ = 1",
						sqlParams);
		if (rs.next()) {
			// 通过短信将密码发送给手机
			ESBRequestEvent reqEvent = new ESBRequestEvent("ZBdxyzBLH", "", "");
			reqEvent.setSjb("");
			reqEvent.setDealMethod("query");
			reqEvent.setReqMapParam(new HashMap<>());
			reqEvent.reqMapParam.put("sjhm", jsonReqData.getSjHm());
			reqEvent.reqMapParam.put("zjlx", rs.getString("SFZMLX_DM"));
			reqEvent.reqMapParam.put("zjhm", rs.getString("SFZHM"));
			reqEvent.reqMapParam.put("gljgdm", dataMap.get("gljgdm"));
			reqEvent.reqMapParam
					.put("yzm", "您正在申请找回密码，您的原密码为：" + rs.getString("LOGPASS")
							+ "，请牢记您的密码！");
			try {
				responseEvent = ZBCoreHelper.getEsbResponseEvent(reqEvent, con);
				responseEvent.setRepCode(responseEvent.respMapParam.get("code")
						.toString());
				responseEvent.setReponseMesg(responseEvent.respMapParam.get(
						"msg").toString());
			} catch (Exception e) {
				responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
				responseEvent
						.setReponseMesg(JyxxJgbHelper.queryJyxxx(ZBCoreHelper
								.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
								e.getMessage()));
				return responseEvent;
			}
		} else {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB0027);
			responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(con,
					GeneralCons.ERROR_CODE_ZB0027));
		}
		return responseEvent;
	}

	// 获取验证码
	protected ResponseEvent hqYzm(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		String yzm = RandomStringUtils.randomNumeric(4);
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(new StoredProcParamObj(1, dataMap.get("zjlx"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(2, dataMap.get("zjhm"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(3, dataMap.get("gljgdm"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(4, yzm, StoredProcParamObj.IN,
				java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(5, null, StoredProcParamObj.OUT,
				java.sql.Types.VARCHAR));
		ArrayList<Object> returnList = (ArrayList<Object>) StoredProcManager
				.callStoreProcess(con, "{call P_ZB_YH001_HQYZM(?,?,?,?,?)}",
						sqlParams);
		CachedRowSet rs = QueryCssBPO.findAll(con,
				"SELECT T.* FROM DB_ZSBS.T_ZB_LS_JYZT T", null);
		rs.next();
		if ("0".equals(rs.getString("JYZT_DM"))) {
			// 发送验证码
			ESBRequestEvent reqEvent = new ESBRequestEvent("ZBdxyzBLH", "", "");
			reqEvent.setSjb("");
			reqEvent.setDealMethod("query");
			reqEvent.setReqMapParam(new HashMap<>());
			reqEvent.reqMapParam.put("sjhm", returnList.get(0));
			reqEvent.reqMapParam.put("zjlx", dataMap.get("zjlx"));
			reqEvent.reqMapParam.put("zjhm", dataMap.get("zjhm"));
			reqEvent.reqMapParam.put("gljgdm", dataMap.get("gljgdm"));
			reqEvent.reqMapParam.put("yzm", "您正在申请掌办注册，本次验证码为：" + yzm
					+ "，有效时间为10分钟");
			try {
				responseEvent = ZBCoreHelper.getEsbResponseEvent(reqEvent, con);
				responseEvent.setRepCode(responseEvent.respMapParam.get("code")
						.toString());
				responseEvent.setReponseMesg(responseEvent.respMapParam.get(
						"msg").toString());
			} catch (Exception e) {
				responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
				responseEvent
						.setReponseMesg(JyxxJgbHelper.queryJyxxx(ZBCoreHelper
								.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
								e.getMessage()));
				return responseEvent;
			}
		} else {
			responseEvent.setRepCode(rs.getString("JYZT_DM"));
			responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					rs.getString("JYZT_MC"), rs.getString("SJNR")));
		}
		return responseEvent;
	}

	protected ResponseEvent mmxg(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(new StoredProcParamObj(1, jsonReqData.getSjHm(),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(2, jsonReqData.getPassWord(),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams
				.add(new StoredProcParamObj(3, MD5Helper.MD5(jsonReqData
						.getPassWord()), StoredProcParamObj.IN,
						java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(4, dataMap.get("newpassword"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(5, MD5Helper.MD5((String) dataMap
				.get("newpassword")), StoredProcParamObj.IN,
				java.sql.Types.VARCHAR));
		StoredProcManager.callStoreProcess(con,
				"{call P_ZB_YH001_XGMM(?,?,?,?,?)}", sqlParams);
		CachedRowSet rs = QueryCssBPO.findAll(con,
				"SELECT T.* FROM DB_ZSBS.T_LS_LOGIN_BDXX T", null);
		rs = QueryCssBPO.findAll(con, "SELECT T.* FROM DB_ZSBS.T_ZB_LS_JYZT T",
				null);
		rs.next();

		// 记录修改密码记录
		if ("0".equals(rs.getString("JYZT_DM"))) {
			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO DB_ZSBS.T_ZB_CZLOG (CZLOG_UUID, UUID, CZFL_DM, LR_SJ) VALUES (?,(SELECT T.UUID FROM DB_ZSBS.T_ZB_LOGIN T WHERE T.SJHM = ?),?,SYSDATE)");
			pstmt.setString(1, ZBCoreHelper.getGUID(con));
			pstmt.setString(2, jsonReqData.getSjHm());
			pstmt.setString(3, "00001");
			pstmt.execute();
			pstmt.close();
		}

		responseEvent.setRepCode(rs.getString("JYZT_DM"));
		responseEvent.setReponseMesg(rs.getString("JYZT_MC"));
		return responseEvent;
	}

	// 用户注册
	protected ResponseEvent yhZc(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(new StoredProcParamObj(1, jsonReqData.getSjHm(),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(2, jsonReqData.getPassWord(),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams
				.add(new StoredProcParamObj(3, MD5Helper.MD5(jsonReqData
						.getPassWord()), StoredProcParamObj.IN,
						java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(4, dataMap.get("zjlx"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(5, dataMap.get("zjhm"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(6, UUIDGenerator.getUUID(),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(7, jsonReqData.getXm(),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(8, jsonReqData.getSjYzm(),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		StoredProcManager.callStoreProcess(con,
				"{call P_ZB_YH001_YHZC(?,?,?,?,?,?,?,?)}", sqlParams);
		CachedRowSet rs = QueryCssBPO.findAll(con,
				"SELECT T.* FROM DB_ZSBS.T_ZB_LS_JYZT T", null);
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
