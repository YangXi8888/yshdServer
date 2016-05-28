package gov.jslt.taxcore.taxblh.bsfw.ssbl.ssbl001;

import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxcore.taxbpo.bsfw.ssbl.ssbl001.WBdjGrrzqkBPO;
import gov.jslt.taxevent.bsfw.ssbl.ssbl001.WBdjGrrzqkVO;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.WBfwHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.bean.MorphDynaBean;
import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.bpo.QueryBPO;
import com.ctp.core.bpo.QueryCssBPO;
import com.ctp.core.bpo.StoredProcManager;
import com.ctp.core.bpo.StoredProcParamObj;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.cssesb.esbevent.ESBRequestEvent;

public class Ssbl001BLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		String delMethod = req.getDealMethod();
		if ("zrrydj".equals(delMethod)) {
			return zrrydj(req, con);
		} else if ("intform".equals(delMethod)) {
			return intform(req, con);
		} else if ("search".equals(delMethod)) {
			return search(req, con);
		} else if ("zrrydjDecrypt".equals(delMethod)) {
			return zrrydjDecrypt(req, con);
		} else if ("zrrCheck".equals(delMethod)) {
			return zrrCheck(req, con);
		} else if ("queryZrrInfo".equals(delMethod)) {
			return queryZrrInfo(req, con);
		} else if ("submitZrrDj".equals(delMethod)) {
			return submitZrrDj(req, con);
		}
		return null;
	}

	private ResponseEvent submitZrrDj(RequestEvent reqEvent, Connection conn)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		String swglm = String.valueOf(jsonReqData.getData().get("swglm"));
		String zjlxDm = String.valueOf(jsonReqData.getData().get("zjlxDm"));
		String zjHm = String.valueOf(jsonReqData.getData().get("zjHm"));
		String zwMc = jsonReqData.getXm();
		String sjHm = jsonReqData.getSjHm();
		String jntxDz = String.valueOf(jsonReqData.getData().get("jntxDz"));
		String xzxqDm = String.valueOf(jsonReqData.getData().get("xzxqDm"));

		ESBRequestEvent esbRequestEvent = new ESBRequestEvent("Ssbl001BLH", "",
				"");
		esbRequestEvent.setSjb("");
		esbRequestEvent.setDealMethod("submitZrrDj");
		esbRequestEvent.setReqMapParam(new HashMap<>());
		esbRequestEvent.reqMapParam.put("swglm", swglm);
		esbRequestEvent.reqMapParam.put("zjlxDm", zjlxDm);
		esbRequestEvent.reqMapParam.put("zjHm", zjHm);
		esbRequestEvent.reqMapParam.put("zwMc", zwMc);
		esbRequestEvent.reqMapParam.put("jntxDz", jntxDz);
		esbRequestEvent.reqMapParam.put("sjHm", sjHm);
		esbRequestEvent.reqMapParam.put("xzxqDm", xzxqDm);
		if ("".equals(swglm)) {
			ArrayList<String> sqlParams = new ArrayList<String>();
			sqlParams.add(xzxqDm);
			CachedRowSet rs = QueryBPO
					.findAll(
							conn,
							"SELECT XZQHXQ_DM, GLJG_DM, JCJG_DM, FWJG_DM, ZSJG_DM, JIANCJG_DM, SKSSJG_DM FROM T_CS_ZB_ZRRJG WHERE XZQHXQ_DM = ?",
							sqlParams);
			if (rs.size() > 0) {
				rs.next();
				esbRequestEvent.reqMapParam.put("gljgDm",
						rs.getString("GLJG_DM"));
				esbRequestEvent.reqMapParam.put("skssjgDm",
						rs.getString("SKSSJG_DM"));
				esbRequestEvent.reqMapParam.put("jiancjgDm",
						rs.getString("JIANCJG_DM"));
				esbRequestEvent.reqMapParam.put("zsjgDm",
						rs.getString("ZSJG_DM"));
				esbRequestEvent.reqMapParam.put("fwjgDm",
						rs.getString("FWJG_DM"));
				esbRequestEvent.reqMapParam.put("jcjgDm",
						rs.getString("JCJG_DM"));
			} else {
				responseEvent.setRepCode("ZB0042");
				responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(conn,
						"ZB0042"));
				return responseEvent;
			}
		}
		try {
			responseEvent = ZBCoreHelper.getEsbResponseEvent(esbRequestEvent,
					conn);
			if ("0".equals(responseEvent.getRepCode())
					&& null != responseEvent.respMapParam.get("swglm")) {
				swglm = (String) responseEvent.respMapParam.get("swglm");
				PreparedStatement pstmt = conn
						.prepareStatement("DELETE FROM DB_ZSBS.T_ZB_ZRRMRDJ WHERE UUID = ?");
				pstmt.setString(1, jsonReqData.getYhwybz());
				pstmt.execute();
				pstmt = conn
						.prepareStatement("INSERT INTO DB_ZSBS.T_ZB_ZRRMRDJ (UUID, LR_SJ, XG_SJ, SWGLM, DJXH) VALUES (?, SYSTIMESTAMP, NULL,?,NULL)");
				pstmt.setString(1, jsonReqData.getYhwybz());
				pstmt.setString(2, swglm);
				pstmt.execute();
				pstmt.close();
			}
		} catch (Exception e) {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			responseEvent
					.setReponseMesg(JyxxJgbHelper.queryJyxxx(ZBCoreHelper
							.getJyztMc(conn, GeneralCons.ERROR_CODE_ZB9999), e
							.getMessage()));
			return responseEvent;
		}

		return responseEvent;
	}

	private ResponseEvent queryZrrInfo(RequestEvent reqEvent, Connection conn)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		String zjlxDm = (String) jsonReqData.getData().get("zjlxDm");
		String zjHm = (String) jsonReqData.getData().get("zjHm");
		String swglm = "";
		// 1.查询默认参数表是否有记录
		ArrayList<String> sqlParams = new ArrayList<String>();
		sqlParams.add(jsonReqData.getYhwybz());
		CachedRowSet rs = QueryBPO.findAll(conn,
				"SELECT UUID, SWGLM, DJXH FROM T_ZB_ZRRMRDJ WHERE UUID = ?",
				sqlParams);
		if (rs.next()) {
			swglm = rs.getString("SWGLM");
		}
		// 2.查询登记数据
		ESBRequestEvent esbRequestEvent = new ESBRequestEvent("Ssbl001BLH", "",
				"");
		esbRequestEvent.setSjb("");
		esbRequestEvent.setDealMethod("queryZrrInfo");
		esbRequestEvent.setReqMapParam(new HashMap<>());
		esbRequestEvent.reqMapParam.put("zjlxDm", zjlxDm);
		esbRequestEvent.reqMapParam.put("zjHm", zjHm);
		esbRequestEvent.reqMapParam.put("swglm", swglm);
		try {
			responseEvent = ZBCoreHelper.getEsbResponseEvent(esbRequestEvent,
					conn);
		} catch (Exception e) {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			responseEvent
					.setReponseMesg(JyxxJgbHelper.queryJyxxx(ZBCoreHelper
							.getJyztMc(conn, GeneralCons.ERROR_CODE_ZB9999), e
							.getMessage()));
			return responseEvent;
		}
		Map<String, String> tempMap = null;
		// 行政区划（地市）代码
		rs = QueryBPO
				.findAll(
						conn,
						"SELECT A.XZQHDS_DM, A.MC_J XZQHDS_MC FROM DB_WSBS.T_DM_GY_XZQHDS A WHERE A.XY_BJ = '1' ORDER BY A.XZQHDS_DM",
						null);
		List<Map<String, String>> xzdsData = new ArrayList<Map<String, String>>();
		while (rs.next()) {
			tempMap = new HashMap<String, String>();
			tempMap.put("xzdsDm", rs.getString("XZQHDS_DM"));
			tempMap.put("xzdsMc", rs.getString("XZQHDS_MC"));
			xzdsData.add(tempMap);
		}
		responseEvent.respMapParam.put("xzdsData", xzdsData);
		// 行政区划（县、区）代码
		rs = QueryBPO
				.findAll(
						conn,
						"SELECT A.XZQHXQ_DM, A.MC_J XZQHXQ_MC, A.XZQHDS_DM FROM DB_WSBS.T_DM_GY_XZQHXQ A WHERE A.XY_BJ = '1' ORDER BY A.XZQHXQ_DM",
						null);
		List<Map<String, String>> xzxqData = new ArrayList<Map<String, String>>();
		while (rs.next()) {
			tempMap = new HashMap<String, String>();
			tempMap.put("xzxqDm", rs.getString("XZQHXQ_DM"));
			tempMap.put("xzxqMc", rs.getString("XZQHXQ_MC"));
			tempMap.put("xzdsDm", rs.getString("XZQHDS_DM"));
			xzxqData.add(tempMap);
		}
		responseEvent.respMapParam.put("xzxqData", xzxqData);
		return responseEvent;
	}

	protected ResponseEvent zrrCheck(RequestEvent req, Connection con)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		String zjhm = dataMap.get("zjHm").toString();
		String zjlx = dataMap.get("zjLx").toString();
		if ("06".equals(zjlx)) {
			if (zjhm.length() == 18) {// 查询是否做过18位身份证登记
				sqlParams = new ArrayList<Object>();
				sqlParams.add(zjhm.toUpperCase());
				sqlParams.add(zjhm.toLowerCase());
				sqlParams.add(zjlx);
				CachedRowSet rs = QueryCssBPO
						.findAll(
								con,
								"SELECT COUNT(Z.SWGLM) NUM FROM DB_WSBS.T_DJ_ZRRDJB Z WHERE Z.ZJ_HM IN (?,?) AND Z.ZJLX_DM = ? AND Z.NSRZT_DM != '55'",
								sqlParams);
				rs.next();
				if (0 < rs.getInt("NUM")) {
					responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB0026);
					responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(con,
							GeneralCons.ERROR_CODE_ZB0026));
					return responseEvent;
				} else {// 查询是否做给15位身份证登记
					String zjhm_15 = zjhm.substring(0, 6)
							+ zjhm.substring(8, 17);
					sqlParams = new ArrayList<Object>();
					sqlParams.add(zjhm_15.toUpperCase());
					sqlParams.add(zjhm_15.toLowerCase());
					sqlParams.add(zjlx);
					rs = QueryCssBPO
							.findAll(
									con,
									"SELECT COUNT(Z.SWGLM) NUM FROM DB_WSBS.T_DJ_ZRRDJB Z WHERE Z.ZJ_HM IN (?,?) AND Z.ZJLX_DM = ? AND Z.NSRZT_DM != '55'",
									sqlParams);
					rs.next();
					if (0 < rs.getInt("NUM")) {
						responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB0031);
						responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(
								con, GeneralCons.ERROR_CODE_ZB0031));
						return responseEvent;
					}
				}
			} else {
				responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB0030);
				responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(con,
						GeneralCons.ERROR_CODE_ZB0030));
				return responseEvent;
			}
		} else {
			sqlParams = new ArrayList<Object>();
			sqlParams.add(zjhm.toUpperCase());
			sqlParams.add(zjhm.toLowerCase());
			sqlParams.add(zjlx);
			CachedRowSet rs = QueryCssBPO
					.findAll(
							con,
							"SELECT COUNT(Z.SWGLM) NUM FROM DB_WSBS.T_DJ_ZRRDJB Z WHERE Z.ZJ_HM IN (?,?) AND Z.ZJLX_DM = ? AND Z.NSRZT_DM != '55'",
							sqlParams);
			rs.next();
			if (0 < rs.getInt("NUM")) {
				responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB0026);
				responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(con,
						GeneralCons.ERROR_CODE_ZB0026));
				return responseEvent;
			}
		}
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	// 自然人预登记
	protected ResponseEvent zrrydj(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String lsh = WBfwHelper.getLsh("003");
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(new StoredProcParamObj(1, lsh, StoredProcParamObj.IN,
				java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(2, dataMap.get("zjLx"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(3, dataMap.get("zjHm"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(4, dataMap.get("jnZz"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(5, dataMap.get("txDz"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(6, dataMap.get("ydDh"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(7, dataMap.get("gdDh"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(8, dataMap.get("mm"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(9, dataMap.get("xq"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(10, dataMap.get("zwMc"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(11, dataMap.get("ywMc"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(12, null, StoredProcParamObj.OUT,
				java.sql.Types.VARCHAR));
		ArrayList<Object> returnList = (ArrayList<Object>) StoredProcManager
				.callStoreProcess(
						con,
						"{call P_ZB_SSBL001_SAVEZRRXX(?,?,?,?,?,?,?,?,?,?,?,?)}",
						sqlParams);
		CachedRowSet rs = QueryCssBPO.findAll(con,
				"SELECT T.* FROM DB_ZSBS.T_ZB_LS_JYZT T", null);
		rs.next();
		// 业务表保存成功后上传文件
		if ("0".equals(rs.getString("JYZT_DM"))) {
			MorphDynaBean rzqkBean = (MorphDynaBean) dataMap.get("rzqk");
			Object rzdwmc = rzqkBean.get("rzdwmc_zh");
			if (null != rzdwmc && !"".equals(rzdwmc.toString())) {
				// 保存个人任职情况
				WBdjGrrzqkVO vo = new WBdjGrrzqkVO();
				vo.setXh(ZBCoreHelper.getGUID(con));
				vo.setLsh(lsh);
				vo.setRzdwmc(rzdwmc.toString());
				vo.setZyrzbj("1");
				WBdjGrrzqkBPO.insert(con, vo);
			}
			try {
				// 此三项为必需字段
				dataMap.put("gljgDm", "23200");
				dataMap.put("lsh", lsh);
				dataMap.put("ywsxDm", "01010030");
				ZBCoreHelper.upFile(dataMap, con);
				if (null != returnList.get(0)) {
					// 发送短信提示信息
					ESBRequestEvent reqEvent = new ESBRequestEvent("ZBdxyzBLH",
							"", "");
					reqEvent.setSjb("");
					reqEvent.setDealMethod("query");
					reqEvent.setReqMapParam(new HashMap<>());
					reqEvent.reqMapParam.put("sjhm", dataMap.get("ydDh")
							.toString());
					reqEvent.reqMapParam.put("zjlx", dataMap.get("zjLx")
							.toString());
					reqEvent.reqMapParam.put("zjhm", dataMap.get("zjHm")
							.toString());
					reqEvent.reqMapParam.put("gljgdm", returnList.get(0)
							.toString());
					reqEvent.reqMapParam.put("yzm", "您本次自然人登记查询流水号为：" + lsh
							+ "，密码为：" + dataMap.get("mm"));
					responseEvent = ZBCoreHelper.getEsbResponseEvent(reqEvent,
							con);
				}
			} catch (Exception e) {
				throw new TaxBaseBizException(e.getMessage());
			}
		}
		responseEvent.respMapParam.put("lsh", lsh);
		responseEvent.setRepCode(rs.getString("JYZT_DM"));
		responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
				rs.getString("JYZT_MC"), rs.getString("SJNR")));
		return responseEvent;
	}

	// 自然人预登记(上传附件做加解密)
	protected ResponseEvent zrrydjDecrypt(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String lsh = WBfwHelper.getLsh("003");
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		String sql = "SELECT COUNT(Z.SWGLM) NUM FROM DB_WSBS.T_DJ_ZRRDJB Z WHERE Z.ZJ_HM IN (?,?) AND Z.ZJLX_DM = ? AND Z.NSRZT_DM != '55'";
		sqlParams.add(dataMap.get("zjHm").toString().toLowerCase());
		sqlParams.add(dataMap.get("zjHm").toString().toUpperCase());
		sqlParams.add(dataMap.get("zjLx"));
		CachedRowSet crs = QueryCssBPO.findAll(con, sql, sqlParams);
		crs.next();
		if (crs.getInt("NUM") > 0) {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB0026);
			responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(con,
					GeneralCons.ERROR_CODE_ZB0026));
			return responseEvent;
		}
		sqlParams = new ArrayList<Object>();
		sqlParams.add(new StoredProcParamObj(1, lsh, StoredProcParamObj.IN,
				java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(2, dataMap.get("zjLx"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(3, dataMap.get("zjHm"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(4, dataMap.get("jnZz"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(5, dataMap.get("txDz"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(6, dataMap.get("ydDh"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(7, dataMap.get("gdDh"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(8, dataMap.get("mm"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(9, dataMap.get("xq"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(10, dataMap.get("zwMc"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(11, dataMap.get("ywMc"),
				StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(12, null, StoredProcParamObj.OUT,
				java.sql.Types.VARCHAR));
		ArrayList<Object> returnList = (ArrayList<Object>) StoredProcManager
				.callStoreProcess(
						con,
						"{call P_ZB_SSBL001_SAVEZRRXX(?,?,?,?,?,?,?,?,?,?,?,?)}",
						sqlParams);
		CachedRowSet rs = QueryCssBPO.findAll(con,
				"SELECT T.* FROM DB_ZSBS.T_ZB_LS_JYZT T", null);
		rs.next();
		// 业务表保存成功后上传文件
		if ("0".equals(rs.getString("JYZT_DM"))) {
			MorphDynaBean rzqkBean = (MorphDynaBean) dataMap.get("rzqk");
			Object rzdwmc = rzqkBean.get("rzdwmc_zh");
			if (null != rzdwmc && !"".equals(rzdwmc.toString())) {
				// 保存个人任职情况
				WBdjGrrzqkVO vo = new WBdjGrrzqkVO();
				vo.setXh(ZBCoreHelper.getGUID(con));
				vo.setLsh(lsh);
				vo.setRzdwmc(rzdwmc.toString());
				vo.setZyrzbj("1");
				WBdjGrrzqkBPO.insert(con, vo);
			}
			try {
				// 此三项为必需字段
				dataMap.put("gljgDm", "23200");
				dataMap.put("lsh", lsh);
				dataMap.put("ywsxDm", "01010030");
				ZBCoreHelper.upFileDecrypt(dataMap, con);
				if (null != returnList.get(0)) {
					// 发送短信提示信息
					ESBRequestEvent reqEvent = new ESBRequestEvent("ZBdxyzBLH",
							"", "");
					reqEvent.setSjb("");
					reqEvent.setDealMethod("query");
					reqEvent.setReqMapParam(new HashMap<>());
					reqEvent.reqMapParam.put("sjhm", dataMap.get("ydDh")
							.toString());
					reqEvent.reqMapParam.put("zjlx", dataMap.get("zjLx")
							.toString());
					reqEvent.reqMapParam.put("zjhm", dataMap.get("zjHm")
							.toString());
					reqEvent.reqMapParam.put("gljgdm", returnList.get(0)
							.toString());
					reqEvent.reqMapParam.put("yzm", "您本次自然人登记查询流水号为：" + lsh
							+ "，密码为：" + dataMap.get("mm"));
					responseEvent = ZBCoreHelper.getEsbResponseEvent(reqEvent,
							con);
					responseEvent.respMapParam.put("lsh", lsh);
					responseEvent.setRepCode(responseEvent.respMapParam.get(
							"code").toString());
					responseEvent.setReponseMesg(responseEvent.respMapParam
							.get("msg").toString());
				}
			} catch (Exception e) {
				throw new TaxBaseBizException(e.getMessage());
			}
		} else {
			responseEvent.setRepCode(rs.getString("JYZT_DM"));
			responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					rs.getString("JYZT_MC"), rs.getString("SJNR")));
		}
		return responseEvent;
	}

	// 自然人预登记初始化前台
	protected ResponseEvent intform(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		List<Map<String, String>> zjlxList = new ArrayList<Map<String, String>>();
		List<Map<String, String>> dsList = new ArrayList<Map<String, String>>();
		List<Map<String, String>> xqList = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		CachedRowSet rs = QueryCssBPO.findAll(con,
				"SELECT T.* FROM DB_WSBS.T_DM_DJ_SFZJLX T WHERE T.XY_BJ='1'",
				null);
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("ZJLX_DM", rs.getString("SFZJLX_DM"));
			map.put("ZJLX_MC", rs.getString("MC_J"));
			zjlxList.add(map);
		}

		rs = QueryCssBPO
				.findAll(
						con,
						"select T.XZQHDS_DM,T.MC_J from db_wsbs.T_DM_GY_XZQHDS T WHERE T.XY_BJ='1' ORDER BY T.XZQHDS_DM",
						null);
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("XZQHDS_DM", rs.getString("XZQHDS_DM"));
			map.put("DSMC", rs.getString("MC_J"));
			dsList.add(map);
		}

		rs = QueryCssBPO
				.findAll(
						con,
						"select T.XZQHXQ_DM,T.MC_J,T.XZQHDS_DM from db_wsbs.T_DM_GY_XZQHXQ T WHERE T.XY_BJ='1' and t.xzqhxq_dm  not like '%00' AND T.XZQHXQ_DM IN (SELECT X.XZQHXQ_DM FROM DB_WSBS.T_DM_WB_DJSLJG X WHERE X.XY_BJ = '1' AND X.JGLX = 'DJ') ORDER BY T.XZQHXQ_DM",
						null);
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("XZQHXQ_DM", rs.getString("XZQHXQ_DM"));
			map.put("XQMC", rs.getString("MC_J"));
			map.put("XZQHDS_DM", rs.getString("XZQHDS_DM"));
			xqList.add(map);
		}

		responseEvent.respMapParam.put("zjlxList", zjlxList);
		responseEvent.respMapParam.put("dsList", dsList);
		responseEvent.respMapParam.put("xqList", xqList);
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	// 通过lsh查询
	protected ResponseEvent search(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String lsh = (String) dataMap.get("lsh");
		String mm = (String) dataMap.get("pwd");
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(lsh);
		paramList.add(mm);
		ResponseEvent responseEvent = new ResponseEvent();
		Map<String, String> map;
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT ZRRDJ.Zwmc, FWSQB.ZT FROM db_wsbs.T_WB_DJ_ZRRDJB ZRRDJ, db_wsbs.T_WB_WSFWQKB FWSQB  WHERE ZRRDJ.LSH = ? AND ZRRDJ.MM = ? AND ZRRDJ.LSH = FWSQB.LSH",
						paramList);
		if (rs.next()) {
			responseEvent.respMapParam.put("zt", rs.getString("zt"));
			responseEvent.respMapParam.put("mc", rs.getString("Zwmc"));
			responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
			responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		} else {
			responseEvent.setRepCode("ZB0012");
			responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(con, "ZB0012"));
		}
		return responseEvent;
	}

	@Override
	protected ResponseEvent validateData(RequestEvent req, Connection con)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
