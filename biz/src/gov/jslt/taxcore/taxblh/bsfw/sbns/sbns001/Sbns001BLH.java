package gov.jslt.taxcore.taxblh.bsfw.sbns.sbns001;

import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxcore.taxbpo.bsfw.sbns.sbns001.WBgrsdsjbxxBPO;
import gov.jslt.taxcore.taxbpo.bsfw.sbns.sbns001.WBgs12wsbbBPO;
import gov.jslt.taxcore.taxbpo.comm.WBsbbqkZrrBPO;
import gov.jslt.taxcore.taxbpo.comm.ZBSbbqkBPO;
import gov.jslt.taxevent.bsfw.sbns.sbns001.WBgrsdsjbxxVO;
import gov.jslt.taxevent.bsfw.sbns.sbns001.WBgs12wsbbVO;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.WBsbbqkZrrVO;
import gov.jslt.taxevent.comm.ZBSbbqkVO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.bean.MorphDynaBean;
import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.bpo.QueryCssBPO;
import com.ctp.core.bpo.StoredProcManager;
import com.ctp.core.bpo.StoredProcParamObj;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.core.log.LogWritter;
import com.ctp.core.utility.dbtime.DBTimeServer;
import com.ctp.cssesb.esbevent.ESBRequestEvent;

public class Sbns001BLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		String delMethod = req.getDealMethod();
		if ("saveData".equals(delMethod)) {
			return saveData(req, con);
		} else if ("initData".equals(delMethod)) {
			return initData(req, con);
		} else if ("querySbList".equals(delMethod)) {
			return querySbList(req, con);
		} else if ("querySbmxList".equals(delMethod)) {
			return querySbmxList(req, con);
		} else if ("queryZrrList".equals(delMethod)) {
			return queryZrrList(req, con);
		} else if ("queryKkUrl".equals(delMethod)) {
			return queryKkUrl(req, con);
		} else if ("initDataNew".equals(delMethod)) {
			return initDataNew(req, con);
		} else if ("deleteSbInfo".equals(delMethod)) {
			return deleteSbInfo(req, con);// 删除扣款失败的申报信息
		}
		return null;
	}

	protected ResponseEvent deleteSbInfo(RequestEvent req, Connection con)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(dataMap.get("pzxh"));
		String sql = "SELECT COUNT(Z.PZ_XH) NUM FROM DB_WSBS.T_WB_SBBQK_ZRR Z WHERE Z.PZ_XH = ? AND Z.ZT = 'j'";
		CachedRowSet rs = QueryCssBPO.findAll(con, sql, sqlParams);
		if (rs.next()) {
			if (rs.getInt("NUM") > 0) {
				// 删除扣款失败申报信息
				PreparedStatement ps = con
						.prepareStatement("DELETE FROM DB_WSBS.T_WB_SBBQK_ZRR z WHERE z.pz_xh = ?");
				ps.setString(1, dataMap.get("pzxh").toString());
				ps.executeUpdate();
				ps.close();
				responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
				responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
			} else {
				responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB0028);
				responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(con,
						GeneralCons.ERROR_CODE_ZB0028));
			}
		} else {
			responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG_ZB7777);
		}
		return responseEvent;
	}

	protected ResponseEvent queryKkUrl(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		dataMap.put("ddly", "01");
		String url = ZBCoreHelper.getAaginURL(dataMap, con);
		if ("".equals(url)) {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB0024);
			responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(con,
					GeneralCons.ERROR_CODE_ZB0024));
		} else {
			responseEvent.respMapParam.put("url", url);
			responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
			responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		}
		return responseEvent;
	}

	protected ResponseEvent queryZrrList(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String zjlxdm = dataMap.get("zjlxdm").toString();
		String zjhm = dataMap.get("zjhm").toString();
		String zjhm_15 = null;
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(new StoredProcParamObj(1, zjlxdm, StoredProcParamObj.IN,
				java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(2, zjhm, StoredProcParamObj.IN,
				java.sql.Types.VARCHAR));
		if ("06".equals(zjlxdm) && zjhm.length() == 18) {
			zjhm_15 = zjhm.substring(0, 6) + zjhm.substring(8, 17);
		}
		sqlParams.add(new StoredProcParamObj(3, zjhm_15, StoredProcParamObj.IN,
				java.sql.Types.VARCHAR));
		List<Object> resultList = (List<Object>) StoredProcManager
				.callStoreProcess(con, "{call P_ZB_SBNS001_ZRRLBCX(?,?,?)}",
						sqlParams);
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT Z.SWGLM, Z.NSRSBM, Z.GLJG_DM, Z.GLJG_MC FROM DB_ZSBS.T_LS_ZRRDJXX Z",
						null);
		List<Map<String, String>> zrrxxList = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		CachedRowSet crs;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("swglm", rs.getString("SWGLM"));
			map.put("nsrsbm", rs.getString("NSRSBM"));
			map.put("gljgdm", rs.getString("GLJG_DM"));
			sqlParams = new ArrayList<Object>();
			sqlParams.add(rs.getString("GLJG_DM"));
			crs = QueryCssBPO
					.findAll(
							con,
							"SELECT S.MC FROM DB_WSBS.T_DM_GY_SWJG S WHERE S.SWJG_DM = SUBSTR(?, 0, 7) || '0000'",
							sqlParams);
			if (crs.next()) {
				map.put("gljgmc", crs.getString("MC"));
			} else {
				map.put("gljgmc", rs.getString("GLJG_MC"));
			}
			zrrxxList.add(map);
		}
		responseEvent.respMapParam.put("zrrxxList", zrrxxList);
		rs = QueryCssBPO.findAll(con, "SELECT T.* FROM DB_ZSBS.T_ZB_LS_JYZT T",
				null);
		rs.next();
		responseEvent.setRepCode(rs.getString("JYZT_DM"));
		responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
				rs.getString("JYZT_MC"), rs.getString("SJNR")));
		return responseEvent;
	}

	// 已申报明细查询
	protected ResponseEvent querySbmxList(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String pzxh = dataMap.get("pzxh").toString();// 凭证序号
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT Z.MC_J,G.ZSPM_DM,G.NSDEJN_JE,G.NSDEJW_JE,G.YINGNSSDE_JE,G.YINGNSE_JE, ");
		sql.append(" G.YINSE_JE,G.DKE_JE,G.JMSE_JE,G.BQYBTSE_JE,TO_CHAR(G.SFSSQ_ZZRQ,'yyyy-MM-dd') SFSSQ_ZZRQ ");
		sql.append(" FROM DB_WSBS.T_WB_GS12WSBB G, DB_WSBS.T_DM_GY_ZSPM Z WHERE G.PZ_XH = ? AND G.ZSPM_DM = Z.ZSPM_DM AND Z.ZSXM_DM = '05' ");
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(pzxh);
		CachedRowSet rs = QueryCssBPO.findAll(con, sql.toString(), sqlParams);
		List<Map<String, String>> sbmxList = new ArrayList<Map<String, String>>();
		Map<String, String> sbmxMap;
		while (rs.next()) {
			sbmxMap = new HashMap<String, String>();
			sbmxMap.put("zspmmc", rs.getString("MC_J"));// 征收品名简称
			sbmxMap.put("zspmdm", rs.getString("ZSPM_DM"));// 征收品目代码
			sbmxMap.put("jnsre", rs.getString("NSDEJN_JE"));// 境内收入额
			sbmxMap.put("jwsre", rs.getString("NSDEJW_JE"));// 境外收入额
			sbmxMap.put("ynssde", rs.getString("YINGNSSDE_JE"));// 应纳税所得额
			sbmxMap.put("ynse", rs.getString("YINGNSE_JE"));// 应纳税额
			sbmxMap.put("yjkse", rs.getString("YINSE_JE"));// 已缴（扣）税额
			sbmxMap.put("dkse", rs.getString("DKE_JE"));// 抵扣税额
			sbmxMap.put("jmse", rs.getString("JMSE_JE"));// 减免税额
			sbmxMap.put("ybtse", rs.getString("BQYBTSE_JE"));// 应补退税额
			sbmxMap.put("ssqz", rs.getString("SFSSQ_ZZRQ"));// 所属期止
			sbmxList.add(sbmxMap);
		}
		responseEvent.respMapParam.put("sbmxList", sbmxList);
		sqlParams = new ArrayList<Object>();
		sqlParams.add(pzxh);
		rs = QueryCssBPO
				.findAll(
						con,
						"SELECT TO_CHAR(A.TB_RQ, 'yyyy-MM-dd') TB_RQ FROM DB_WSBS.T_WB_SBBQK_ZRR A WHERE A.PZ_XH = ?",
						sqlParams);
		String tbrq = null;
		if (rs.next()) {
			tbrq = rs.getString("TB_RQ");
		}
		responseEvent.respMapParam.put("tbrq", tbrq);
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	// 已申报列表查询
	protected ResponseEvent querySbList(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String zjlxdm = dataMap.get("zjlxdm").toString();
		String zjhm = dataMap.get("zjhm").toString();
		String lrsj = (DBTimeServer.getDBTime(con).get(Calendar.YEAR) - 1)
				+ "-12-31";
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT Z.PZ_XH, to_char(Z.TJ_SJ,'yyyy-MM-dd') TJ_SJ, Z.YBTSEHDJ_JE, Z.ZT, T.ZT_MC ");
		sql.append(" FROM DB_WSBS.T_WB_SBBQK_ZRR Z, DB_WSBS.T_DM_WB_ZT T ");
		sql.append(" WHERE Z.ZJLX_DM = ? ");
		sql.append(" AND Z.ZJ_HM = ? ");
		sql.append(" and z.lr_sj > to_date(?, 'yyyy-MM-dd') ");
		sql.append(" AND Z.PZZL_DM='30036' ");
		sql.append(" AND Z.ZT !='0' ");
		sql.append(" AND Z.ZPZ_XH IS NULL ");
		sql.append(" AND T.ZT_DM = Z.ZT  ");
		sql.append(" AND T.XY_BJ = '1'  ");
		sql.append(" ORDER BY Z.LR_SJ DESC ");
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(zjlxdm);
		sqlParams.add(zjhm);
		sqlParams.add(lrsj);
		CachedRowSet rs = QueryCssBPO.findAll(con, sql.toString(), sqlParams);
		List<Map<String, String>> sbList = new ArrayList<Map<String, String>>();
		Map<String, String> sbMap;
		while (rs.next()) {
			sbMap = new HashMap<String, String>();
			sbMap.put("pzxh", rs.getString("PZ_XH"));
			sbMap.put("tjsj", rs.getString("TJ_SJ"));
			sbMap.put("ybtsehj", rs.getString("YBTSEHDJ_JE"));
			sbMap.put("ztdm", rs.getString("ZT"));
			sbMap.put("ztmc", rs.getString("ZT_MC"));
			sbList.add(sbMap);
		}
		responseEvent.respMapParam.put("sbList", sbList);

		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	/**
	 * 初始化12万申报数据（已弃用，新方法initDataNew）
	 * 
	 * @param req
	 * @param con
	 * @return
	 * @throws TaxBaseBizException
	 * @throws SQLException
	 */
	protected ResponseEvent initData(RequestEvent req, Connection con)
			throws TaxBaseBizException, SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		ResponseEvent resEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String zjlxdm = dataMap.get("zjlxdm").toString();
		String zjhm = dataMap.get("zjhm").toString();
		String zjhm_15 = null;

		// 申报期限及登记信息判断
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(new StoredProcParamObj(1, zjlxdm, StoredProcParamObj.IN,
				java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(2, zjhm, StoredProcParamObj.IN,
				java.sql.Types.VARCHAR));
		if ("06".equals(zjlxdm) && zjhm.length() == 18) {
			zjhm_15 = zjhm.substring(0, 6) + zjhm.substring(8, 17);
		}
		sqlParams.add(new StoredProcParamObj(3, zjhm_15, StoredProcParamObj.IN,
				java.sql.Types.VARCHAR));
		List<Object> resultList = (List<Object>) StoredProcManager
				.callStoreProcess(con, "{call P_ZB_SBNS001_SBQXCX(?,?,?)}",
						sqlParams);
		CachedRowSet rs = QueryCssBPO.findAll(con,
				"SELECT T.* FROM DB_ZSBS.T_ZB_LS_JYZT T", null);
		rs.next();
		if ("0".equals(rs.getString("JYZT_DM"))) {
			Integer sdnf = DBTimeServer.getDBTime(con).get(Calendar.YEAR) - 1;
			// 获取申报明细数据
			ESBRequestEvent reqEvent = new ESBRequestEvent("Sbns001BLH", "", "");
			reqEvent.setSjb("");
			reqEvent.setDealMethod("querySbmxData");
			reqEvent.setReqMapParam(new HashMap<>());
			reqEvent.reqMapParam.put("zjlxdm", zjlxdm);
			reqEvent.reqMapParam.put("zjhm", zjhm);
			reqEvent.reqMapParam.put("sdnf", sdnf);
			try {
				resEvent = ZBCoreHelper.getEsbResponseEvent(reqEvent, con);
			} catch (Exception e) {
				responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
				responseEvent
						.setReponseMesg(JyxxJgbHelper.queryJyxxx(ZBCoreHelper
								.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
								e.getMessage()));
				return responseEvent;
			}
			responseEvent.respMapParam.put("sdnf", sdnf);
			responseEvent.respMapParam.put("sbmxList",
					resEvent.respMapParam.get("sbmxList"));
			responseEvent.respMapParam.put("sbhzList",
					resEvent.respMapParam.get("sbhzList"));
			// 获取征收品目列表
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT Z.ZSPM_DM, Z.MC_J AS ZSPM_MC ");
			sql.append(" FROM DB_WSBS.T_DM_GY_ZSPM Z ");
			sql.append(" WHERE Z.ZSXM_DM = '05' ");
			sql.append(" AND Z.XY_BJ = '1' ");
			sql.append(" AND Z.ZSPM_DM IN ('0100','0201','0300', ");
			sql.append(" '0400','0500','0601','0602','0603','0604','0699','0701', ");
			sql.append(" '0801','0901','0902','0903','0904','0999','1000','9000') ");
			rs = QueryCssBPO.findAll(con, sql.toString(), null);
			List<Map<String, String>> zspmList = new ArrayList<Map<String, String>>();
			Map<String, String> zspmMap;
			while (rs.next()) {
				zspmMap = new HashMap<String, String>();
				zspmMap.put("zspm_dm", rs.getString("ZSPM_DM"));
				zspmMap.put("zspm_mc", rs.getString("ZSPM_MC"));
				zspmList.add(zspmMap);
			}
			if (null != resEvent.respMapParam.get("sbmxList")
					&& ((List<Map<String, String>>) resEvent.respMapParam
							.get("sbmxList")).size() > 0) {
				// 筛选征收品目
				for (int i = 0; i < zspmList.size(); i++) {
					for (Map<String, String> sbmxMap : (List<Map<String, String>>) resEvent.respMapParam
							.get("sbmxList")) {
						if (sbmxMap.get("zspm_dm").equals(
								zspmList.get(i).get("zspm_dm"))) {
							zspmList.remove(i);
							i--;
							break;
						}
					}
				}
			}
			responseEvent.respMapParam.put("zspmList", zspmList);
			// 获取登记信息
			rs = QueryCssBPO
					.findAll(
							con,
							"SELECT Z.SWGLM, Z.NSRSBM, Z.GLJG_DM, Z.GLJG_MC FROM DB_ZSBS.T_LS_ZRRDJXX Z",
							null);
			List<Map<String, String>> zrrxxList = new ArrayList<Map<String, String>>();
			Map<String, String> map;
			CachedRowSet crs;
			while (rs.next()) {
				map = new HashMap<String, String>();
				map.put("swglm", rs.getString("SWGLM"));
				map.put("nsrsbm", rs.getString("NSRSBM"));
				map.put("gljgdm", rs.getString("GLJG_DM"));
				sqlParams = new ArrayList<Object>();
				sqlParams.add(rs.getString("GLJG_DM"));
				crs = QueryCssBPO
						.findAll(
								con,
								"SELECT S.MC FROM DB_WSBS.T_DM_GY_SWJG S WHERE S.SWJG_DM = SUBSTR(?, 0, 7) || '0000'",
								sqlParams);
				if (crs.next()) {
					map.put("gljgmc", crs.getString("MC"));
				} else {
					map.put("gljgmc", rs.getString("GLJG_MC"));
				}
				zrrxxList.add(map);
			}
			responseEvent.respMapParam.put("zrrxxList", zrrxxList);
			sql = new StringBuffer();
			sql.append(" SELECT TO_CHAR(X.SBQXZ, 'yyyy-MM-dd') SBQXZ, ");
			sql.append(" TO_CHAR(X.SSQS, 'yyyy-MM-dd') SSQS, ");
			sql.append(" TO_CHAR(X.SSQZ, 'yyyy-MM-dd') SSQZ ");
			sql.append(" FROM DB_WSBS.T_WB_KSBLB X ");
			sql.append(" WHERE X.ZSXM_DM = '05' ");
			sql.append(" AND X.SBQXR_DM = 'Y01_90' ");
			sql.append(" AND X.GLJGDM = '23200000000' ");
			rs = QueryCssBPO.findAll(con, sql.toString(), null);
			if (rs.next()) {
				responseEvent.respMapParam.put("sbqxz", rs.getString("SBQXZ"));
				responseEvent.respMapParam.put("ssqs", rs.getString("SSQS"));
				responseEvent.respMapParam.put("ssqz", rs.getString("SSQZ"));
				responseEvent.respMapParam.put("tbrq",
						ZBCoreHelper.getDateTime(con, "yyyy-MM-dd"));
			}
		} else {
			responseEvent.setRepCode(rs.getString("JYZT_DM"));
			responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					rs.getString("JYZT_MC"), rs.getString("SJNR")));
		}
		return responseEvent;
	}

	protected ResponseEvent initDataNew(RequestEvent req, Connection con)
			throws TaxBaseBizException, SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		ResponseEvent resEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String zjlxdm = dataMap.get("zjlxdm").toString();
		String zjhm = dataMap.get("zjhm").toString();
		String zjhm_15 = null;

		// 申报期限及登记信息判断
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(new StoredProcParamObj(1, zjlxdm, StoredProcParamObj.IN,
				java.sql.Types.VARCHAR));
		sqlParams.add(new StoredProcParamObj(2, zjhm, StoredProcParamObj.IN,
				java.sql.Types.VARCHAR));
		if ("06".equals(zjlxdm) && zjhm.length() == 18) {
			zjhm_15 = zjhm.substring(0, 6) + zjhm.substring(8, 17);
		}
		sqlParams.add(new StoredProcParamObj(3, zjhm_15, StoredProcParamObj.IN,
				java.sql.Types.VARCHAR));
		List<Object> resultList = (List<Object>) StoredProcManager
				.callStoreProcess(con, "{call P_ZB_SBNS001_SBQXCX(?,?,?)}",
						sqlParams);
		CachedRowSet rs = QueryCssBPO.findAll(con,
				"SELECT T.* FROM DB_ZSBS.T_ZB_LS_JYZT T", null);
		rs.next();
		if ("0".equals(rs.getString("JYZT_DM"))) {
			Integer sdnf = DBTimeServer.getDBTime(con).get(Calendar.YEAR) - 1;
			// 获取申报明细数据
			ESBRequestEvent reqEvent = new ESBRequestEvent("Sbns001BLH", "", "");
			reqEvent.setSjb("");
			reqEvent.setDealMethod("querySbmxDataNew");
			reqEvent.setReqMapParam(new HashMap<>());
			reqEvent.reqMapParam.put("zjlxdm", zjlxdm);
			reqEvent.reqMapParam.put("zjhm", zjhm);
			reqEvent.reqMapParam.put("sdnf", sdnf);
			reqEvent.reqMapParam.put("xm", jsonReqData.getXm());
			try {
				resEvent = ZBCoreHelper.getEsbResponseEvent(reqEvent, con);
			} catch (Exception e) {
				responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
				responseEvent
						.setReponseMesg(JyxxJgbHelper.queryJyxxx(ZBCoreHelper
								.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
								e.getMessage()));
				return responseEvent;
			}
			responseEvent.respMapParam.put("sdnf", sdnf);
			responseEvent.respMapParam.put("sbmxList",
					resEvent.respMapParam.get("sbmxList"));
			responseEvent.respMapParam.put("sbhzList",
					resEvent.respMapParam.get("sbhzList"));
			// 获取征收品目列表
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT Z.ZSPM_DM, Z.MC_J AS ZSPM_MC ");
			sql.append(" FROM DB_WSBS.T_DM_GY_ZSPM Z ");
			sql.append(" WHERE Z.ZSXM_DM = '05' ");
			sql.append(" AND Z.XY_BJ = '1' ");
			sql.append(" AND Z.ZSPM_DM IN ('0100','0201','0300', ");
			sql.append(" '0400','0500','0601','0602','0603','0604','0699','0701', ");
			sql.append(" '0801','0901','0902','0903','0904','0999','1000','9000') ");
			rs = QueryCssBPO.findAll(con, sql.toString(), null);
			List<Map<String, String>> zspmList = new ArrayList<Map<String, String>>();
			Map<String, String> zspmMap;
			while (rs.next()) {
				zspmMap = new HashMap<String, String>();
				zspmMap.put("zspm_dm", rs.getString("ZSPM_DM"));
				zspmMap.put("zspm_mc", rs.getString("ZSPM_MC"));
				zspmList.add(zspmMap);
			}
			if (null != resEvent.respMapParam.get("sbmxList")
					&& ((List<Map<String, String>>) resEvent.respMapParam
							.get("sbmxList")).size() > 0) {
				// 筛选征收品目
				for (int i = 0; i < zspmList.size(); i++) {
					for (Map<String, String> sbmxMap : (List<Map<String, String>>) resEvent.respMapParam
							.get("sbmxList")) {
						if (sbmxMap.get("zspm_dm").equals(
								zspmList.get(i).get("zspm_dm"))) {
							zspmList.remove(i);
							i--;
							break;
						}
					}
				}
			}
			responseEvent.respMapParam.put("zspmList", zspmList);
			// 获取登记信息
			rs = QueryCssBPO
					.findAll(
							con,
							"SELECT Z.SWGLM, Z.NSRSBM, Z.GLJG_DM, Z.GLJG_MC FROM DB_ZSBS.T_LS_ZRRDJXX Z",
							null);
			List<Map<String, String>> zrrxxList = new ArrayList<Map<String, String>>();
			Map<String, String> map;
			CachedRowSet crs;
			while (rs.next()) {
				map = new HashMap<String, String>();
				map.put("swglm", rs.getString("SWGLM"));
				map.put("nsrsbm", rs.getString("NSRSBM"));
				map.put("gljgdm", rs.getString("GLJG_DM"));
				sqlParams = new ArrayList<Object>();
				sqlParams.add(rs.getString("GLJG_DM"));
				crs = QueryCssBPO
						.findAll(
								con,
								"SELECT S.MC FROM DB_WSBS.T_DM_GY_SWJG S WHERE S.SWJG_DM = SUBSTR(?, 0, 7) || '0000'",
								sqlParams);
				if (crs.next()) {
					map.put("gljgmc", crs.getString("MC"));
				} else {
					map.put("gljgmc", rs.getString("GLJG_MC"));
				}
				zrrxxList.add(map);
			}
			responseEvent.respMapParam.put("zrrxxList", zrrxxList);
			sql = new StringBuffer();
			sql.append(" SELECT TO_CHAR(X.SBQXZ, 'yyyy-MM-dd') SBQXZ, ");
			sql.append(" TO_CHAR(X.SSQS, 'yyyy-MM-dd') SSQS, ");
			sql.append(" TO_CHAR(X.SSQZ, 'yyyy-MM-dd') SSQZ ");
			sql.append(" FROM DB_WSBS.T_WB_KSBLB X ");
			sql.append(" WHERE X.ZSXM_DM = '05' ");
			sql.append(" AND X.SBQXR_DM = 'Y01_90' ");
			sql.append(" AND X.GLJGDM = '23200000000' ");
			rs = QueryCssBPO.findAll(con, sql.toString(), null);
			if (rs.next()) {
				responseEvent.respMapParam.put("sbqxz", rs.getString("SBQXZ"));
				responseEvent.respMapParam.put("ssqs", rs.getString("SSQS"));
				responseEvent.respMapParam.put("ssqz", rs.getString("SSQZ"));
				responseEvent.respMapParam.put("tbrq",
						ZBCoreHelper.getDateTime(con, "yyyy-MM-dd"));
			}
		} else {
			responseEvent.setRepCode(rs.getString("JYZT_DM"));
			responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					rs.getString("JYZT_MC"), rs.getString("SJNR")));
		}
		return responseEvent;
	}

	protected ResponseEvent saveData(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String pzxh = ZBCoreHelper.getGUID(con);

		// 获取申报信息
		StringBuffer sql = new StringBuffer();
		sql.append(" Select a.Swglm, a.Nsrsbm, a.glqy_dm, a.Zwmc Nsr_Mc, a.Gjdq_Dm, ");
		sql.append(" i.Mc Gjmc, a.Zjlx_Dm, h.Mc Zjlxmc, a.Zj_Hm, a.Scdhsj_Rq, ");
		sql.append(" a.Zy_Dm, d.Mc Zymc, g.zw_dm, j.mc zwmc, trim(g.Rzdw_Nsrsbm) ");
		sql.append(" Rzdwswglm, g.Rzdw_Mc Rzdwmc, l.mc rzdwhy, a.Jnzz, a.Jntxdz, ");
		sql.append(" a.Jntxdzyb, a.Yddh, a.Email, a.Dlwzxz_Dm, a.Dlwzc_Dm, a.Gljg_Dm, ");
		sql.append(" f.Mc Gljg_Mc, a.Zsjg_Dm, a.Jcjg_Dm, a.Skssjg_Dm, a.Fwjg_Dm, ");
		sql.append(" a.Jiancjg_Dm, a.Lsgx_Dm, b.Yh_Dm, b.Yh_Zh, c.Mc Yhmc, c.Yhhb_Dm, ");
		sql.append(" a.Nsrzt_Dm, a.Djlx_Dm, a.Sbfs_Dm, a.yjljsj_rq - a.scdhsj_rq + 1 ");
		sql.append(" Zhts, a.zclx_dm, a.gbhy_dm ");
		sql.append(" FROM DB_WSBS.T_DJ_ZRRDJB A, ");
		sql.append(" (SELECT * ");
		sql.append(" FROM DB_WSBS.T_DJ_NSRYHZH ");
		sql.append(" WHERE YHZHLB_DM = '1' ");
		sql.append(" AND XY_BJ = '1' ");
		sql.append(" AND SH_BJ = '1' ");
		sql.append(" AND YX_ZZRQ IS NULL) B, ");
		sql.append(" (SELECT * FROM DB_WSBS.T_DM_GY_YH WHERE XY_BJ = '1') C, ");
		sql.append(" (SELECT * FROM DB_WSBS.T_DM_DJ_ZY WHERE XY_BJ = '1') D, ");
		sql.append(" (SELECT * FROM DB_WSBS.T_DM_GY_SWJG WHERE XY_BJ = '1') F, ");
		sql.append(" (SELECT * FROM DB_WSBS.T_DJ_GRRZQK WHERE ZYRZ_BJ = '1') G, ");
		sql.append(" (SELECT * FROM DB_WSBS.T_DM_DJ_ZJLX WHERE XY_BJ = '1') H, ");
		sql.append(" (SELECT * FROM DB_WSBS.T_DM_GY_GJ WHERE XY_BJ = '1') I, ");
		sql.append(" (SELECT * FROM DB_WSBS.T_DM_DJ_ZW WHERE XY_BJ = '1') J, ");
		sql.append(" DB_WSBS.T_DJ_JGNSR K, ");
		sql.append(" (SELECT * FROM DB_WSBS.T_DM_GY_HYZXL) L ");
		sql.append(" WHERE A.NSRZT_DM = '25' ");
		sql.append(" AND A.SWGLM = B.SWGLM(+) ");
		sql.append(" AND B.YHHB_DM = C.YHHB_DM(+) ");
		sql.append(" AND B.YH_DM = C.YH_DM(+) ");
		sql.append(" AND A.ZY_DM = D.ZY_DM(+) ");
		sql.append(" AND A.GLJG_DM = F.SWJG_DM ");
		sql.append(" AND A.SWGLM = G.SWGLM(+) ");
		sql.append(" AND A.ZJLX_DM = H.ZJLX_DM(+) ");
		sql.append(" AND A.GJDQ_DM = I.GJDQ_DM(+) ");
		sql.append(" AND G.ZW_DM = J.ZW_DM(+) ");
		sql.append(" AND G.RZDW_NSRSBM = K.SWGLM(+) ");
		sql.append(" AND K.GBHY_DM = L.HYZXL_DM(+) ");
		sql.append(" AND A.SWGLM = ? ");
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(dataMap.get("swglm"));
		CachedRowSet rs = QueryCssBPO.findAll(con, sql.toString(), sqlParams);
		rs.next();

		// 12万申报明细信息
		List<MorphDynaBean> mxxxList = (List<MorphDynaBean>) dataMap
				.get("mxxx");
		WBgs12wsbbVO gs12wsbbVO;
		MorphDynaBean bean;
		LogWritter.sysInfo("开始组装12万VO...");
		if (null != mxxxList) {
			for (int i = 0; i < mxxxList.size(); i++) {
				gs12wsbbVO = new WBgs12wsbbVO();
				bean = mxxxList.get(i);
				// 凭证序号
				gs12wsbbVO.setPzxh(pzxh);
				// 申报明细序号
				gs12wsbbVO.setSbmxxh(String.valueOf(i + 1));
				// 税款所属期起
				gs12wsbbVO.setStr_sfssqqsrq(dataMap.get("ssqq").toString());
				// 税款所属期止
				gs12wsbbVO.setStr_sfssqzzrq(dataMap.get("ssqz").toString());
				// 纳税个人税务管理码
				gs12wsbbVO.setSwglm(dataMap.get("swglm").toString());
				// 纳税人身份证照类别
				gs12wsbbVO.setSfzmlxdm(rs.getString("ZJLX_DM"));
				// 纳税人身份证照号码
				gs12wsbbVO.setSfzmhm(rs.getString("ZJ_HM"));
				// 纳税人姓名
				gs12wsbbVO.setNsrmc(rs.getString("NSR_MC"));
				// 征收项目代码
				gs12wsbbVO.setZsxmdm("05");
				// 征收品目代码
				gs12wsbbVO.setZspmdm(bean.get("zspmdm").toString());
				// 申报期限
				gs12wsbbVO.setStr_sbqx(dataMap.get("sbqx").toString());
				// 境内年所得额
				gs12wsbbVO.setNsdejnje(jeCZStr(bean.get("jnsde")));
				// 境外年所得额
				gs12wsbbVO.setNsdejwje(jeCZStr(bean.get("jwsde")));
				// 年所得额合计
				gs12wsbbVO.setNsdehjje(jeCZStr(bean.get("jnsde")));
				// 应纳税所得额
				gs12wsbbVO.setYingnssdeje(jeCZStr(bean.get("ynssde")));
				// 应纳税额
				gs12wsbbVO.setYingnseje(jeCZStr(bean.get("ynse")));
				// 已缴（扣）税额
				gs12wsbbVO.setYinseje(jeCZStr(bean.get("yjkse")));
				// 抵扣税额
				gs12wsbbVO.setDkeje(jeCZStr(bean.get("dkse")));
				// 减免税额
				gs12wsbbVO.setJmseje(jeCZStr(bean.get("jmse")));
				gs12wsbbVO.setBqybtseje(jeCZStr(bean.get("ybtse")));
				WBgs12wsbbBPO.insert(con, gs12wsbbVO);
			}
		}

		// 个人所得税基本信息
		WBgrsdsjbxxVO grsdsjbxxVO = new WBgrsdsjbxxVO();
		grsdsjbxxVO.setPzxh(pzxh);
		grsdsjbxxVO.setSwglm(rs.getString("SWGLM"));
		grsdsjbxxVO.setNsrsbm(rs.getString("NSRSBM"));
		grsdsjbxxVO.setSfzjhm(rs.getString("ZJ_HM"));
		grsdsjbxxVO.setZwmc(rs.getString("NSR_MC"));
		grsdsjbxxVO.setJnzz(rs.getString("JNZZ"));
		grsdsjbxxVO.setSfzjlxmc(rs.getString("ZJLXMC"));
		grsdsjbxxVO.setStr_scdhsjrq(rs.getString("SCDHSJ_RQ"));
		grsdsjbxxVO.setGjmc(rs.getString("GJMC"));
		grsdsjbxxVO.setJcjzd(rs.getString("JNTXDZ"));
		grsdsjbxxVO.setYzbm(rs.getString("JNTXDZYB"));
		grsdsjbxxVO.setLxdh(rs.getString("YDDH"));
		grsdsjbxxVO.setZhjzts(rs.getString("ZHTS"));
		grsdsjbxxVO.setZw_mc(rs.getString("ZWMC"));
		grsdsjbxxVO.setZymc(rs.getString("ZYMC"));
		// 工作地址
		// grsdsjbxxVO.setGzdwdz(rs.getString(""));
		// 工作名称
		// grsdsjbxxVO.setGzdwmc(rs.getString(""));
		// 迁移状态
		// grsdsjbxxVO.setQyzt(rs.getString(""));
		WBgrsdsjbxxBPO.insert(con, grsdsjbxxVO);

		// 申报情况
		WBsbbqkZrrVO sbbqkZrrVO = new WBsbbqkZrrVO();
		// 注册类型代码
		sbbqkZrrVO.setDjzclxdm(rs.getString("ZCLX_DM"));
		// 隶属关系代码
		sbbqkZrrVO.setLsgxdm(rs.getString("LSGX_DM"));
		// 行业（小类）代码
		sbbqkZrrVO.setGbhydm(rs.getString("GBHY_DM"));
		// 凭证序号
		sbbqkZrrVO.setPzxh(pzxh);
		// 凭证种类代码
		sbbqkZrrVO.setPzzldm("30036");
		// 税务管理码
		sbbqkZrrVO.setSwglm(rs.getString("SWGLM"));
		// 纳税人识别码
		sbbqkZrrVO.setNsrsbm(rs.getString("NSRSBM"));
		// 纳税人名称
		sbbqkZrrVO.setNsrmc(rs.getString("NSR_MC"));
		sbbqkZrrVO.setZjlxdm(rs.getString("ZJLX_DM"));
		sbbqkZrrVO.setZjlxmc(rs.getString("ZJLXMC"));
		sbbqkZrrVO.setZjhm(rs.getString("ZJ_HM"));
		sbbqkZrrVO.setGjmc(rs.getString("GJMC"));
		sbbqkZrrVO.setRzdwmc(rs.getString("RZDWMC"));
		sbbqkZrrVO.setRzdwswglm(rs.getString("RZDWSWGLM"));
		sbbqkZrrVO.setRzdwhy(rs.getString("RZDWHY"));
		sbbqkZrrVO.setZwmc(rs.getString("ZWMC"));
		sbbqkZrrVO.setZymc(rs.getString("ZYMC"));
		sbbqkZrrVO.setZhts(rs.getString("ZHTS"));
		sbbqkZrrVO.setLxdz(rs.getString("JNTXDZ"));
		sbbqkZrrVO.setYb(rs.getString("JNTXDZYB"));
		sbbqkZrrVO.setLxdh(rs.getString("YDDH"));
		// 经营单位税务管理码
		// sbbqkZrrVO.setJydwswglm(rs.getString(""));
		// 经营单位纳税人名称
		// sbbqkZrrVO.setJydwnsrmc(rs.getString(""));
		// 填表日期
		sbbqkZrrVO.setStr_tbrq(dataMap.get("tbrq").toString());
		// 税费所属期起
		sbbqkZrrVO.setStr_sfssqqsrq(dataMap.get("ssqq").toString());
		// 税费所属期止
		sbbqkZrrVO.setStr_sfssqzzrq(dataMap.get("ssqz").toString());
		// 开户银行
		sbbqkZrrVO.setKhyhmc(rs.getString("YHMC"));
		// 银行账号
		sbbqkZrrVO.setYhzh(rs.getString("YH_ZH"));
		// 管理机关代码
		sbbqkZrrVO.setGljgdm(rs.getString("GLJG_DM"));
		// HSJG_DM
		sbbqkZrrVO.setHsjgdm(rs.getString("SKSSJG_DM"));
		// 征收机关代码
		sbbqkZrrVO.setZsjgdm(rs.getString("ZSJG_DM"));
		// 稽查机关代码
		sbbqkZrrVO.setJcjgdm(rs.getString("JCJG_DM"));
		// 税款所属机关
		sbbqkZrrVO.setSkssjgdm(rs.getString("SKSSJG_DM"));
		// 服务机关
		sbbqkZrrVO.setFwjgdm(rs.getString("FWJG_DM"));
		// 检查机关
		sbbqkZrrVO.setJiancjgdm(rs.getString("JIANCJG_DM"));
		// 本张报表应补退税额合计
		sbbqkZrrVO.setYbtsehdjje(dataMap.get("ybtsehj").toString());
		// 设置暂存或提交标志
		sbbqkZrrVO.setZt("1");
		sbbqkZrrVO.setTjsj(DBTimeServer.getDBTime(con));
		WBsbbqkZrrBPO.insert(con, sbbqkZrrVO);

		// 记录掌上办税申报情况
		ZBSbbqkVO zbSbbqkVO = new ZBSbbqkVO();
		zbSbbqkVO.setPzxh(sbbqkZrrVO.getPzxh());
		zbSbbqkVO.setFwjgdm(sbbqkZrrVO.getFwjgdm());
		zbSbbqkVO.setGljgdm(sbbqkZrrVO.getGljgdm());
		zbSbbqkVO.setHsjgdm(sbbqkZrrVO.getHsjgdm());
		zbSbbqkVO.setJcjgdm(sbbqkZrrVO.getJcjgdm());
		zbSbbqkVO.setJiancjgdm(sbbqkZrrVO.getJiancjgdm());
		// 0自然人1企业
		zbSbbqkVO.setSjly("0");
		zbSbbqkVO.setSkssjgdm(sbbqkZrrVO.getSkssjgdm());
		zbSbbqkVO.setSwglm(sbbqkZrrVO.getSwglm());
		zbSbbqkVO.setZsjgdm(sbbqkZrrVO.getZsjgdm());
		ZBSbbqkBPO.deleteByPK(con, zbSbbqkVO.getPzxh());
		ZBSbbqkBPO.insert(con, zbSbbqkVO);

		sqlParams = new ArrayList<Object>();
		sqlParams.add(sbbqkZrrVO.getZsjgdm().substring(0, 7) + "0000");
		 rs = QueryCssBPO
				.findAll(
						con,
						"SELECT C.SYLY FROM DB_WSBS.T_CS_WB_WYKK C WHERE C.SWJG_DM = ? AND C.XY_BJ = '1' AND C.SYLY IN ('1', '3')",
						sqlParams);
		boolean hasUrl = false;
		boolean hasZFBUrl = false;
		while (rs.next()) {
			if ("1".equals(rs.getString("SYLY"))) {
				hasUrl = true;
			} else if ("3".equals(rs.getString("SYLY"))) {
				hasZFBUrl = true;
			}
		}
		String url = null;
		Map<String, String> resultMap = null;
		if (new BigDecimal(sbbqkZrrVO.getYbtsehdjje()).doubleValue() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			String ddly = "01";
			map.put("pzxh", pzxh);
			map.put("ddly", ddly);
			map.put("hasUrl", hasUrl);
			map.put("hasZFBUrl", hasZFBUrl);
			resultMap = ZBCoreHelper.getCreateZrrOrderURL(map, con);
			responseEvent.respMapParam.put("url", resultMap.get("WYUrl"));
			responseEvent.respMapParam.put("ZFBUrl", resultMap.get("ZFBUrl"));
		}
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	@Override
	protected ResponseEvent validateData(RequestEvent req, Connection con)
			throws Exception {
		return null;
	}

	protected String jeCZStr(Object jeStr) {
		if (null == jeStr || "".equals(jeStr.toString())) {
			jeStr = "0.00";
		}
		return jeStr.toString();
	}

}
