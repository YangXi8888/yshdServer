package gov.jslt.taxcore.taxblh.bsfw.sbns.sbns003;

import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxcore.taxbpo.bsfw.sbns.sbns003.WBfbsbqkBPO;
import gov.jslt.taxcore.taxbpo.bsfw.sbns.sbns003.WBgrsdssbabBPO;
import gov.jslt.taxcore.taxbpo.bsfw.sbns.sbns003.WBjbxxbcjlxBPO;
import gov.jslt.taxcore.taxbpo.bsfw.sbns.sbns003.WBjbxxbjnlxBPO;
import gov.jslt.taxcore.taxbpo.bsfw.sbns.sbns003.WBjbxxbjwBPO;
import gov.jslt.taxcore.taxbpo.bsfw.sbns.sbns003.WBjbxxbnsrlxBPO;
import gov.jslt.taxcore.taxbpo.bsfw.sbns.sbns003.WBjbxxbtzBPO;
import gov.jslt.taxcore.taxbpo.bsfw.sbns.sbns003.WBjbxxbtzzlxBPO;
import gov.jslt.taxcore.taxbpo.bsfw.sbns.sbns003.WBjbxxbwzsBPO;
import gov.jslt.taxcore.taxbpo.bsfw.sbns.sbns003.WBjbxxbzbBPO;
import gov.jslt.taxcore.taxbpo.comm.WBsbbqkZrrBPO;
import gov.jslt.taxcore.taxbpo.comm.ZBSbbqkBPO;
import gov.jslt.taxevent.bsfw.sbns.sbns003.WBfbsbqkVO;
import gov.jslt.taxevent.bsfw.sbns.sbns003.WBgrsdssbabVO;
import gov.jslt.taxevent.bsfw.sbns.sbns003.WBjbxxbcjlxVO;
import gov.jslt.taxevent.bsfw.sbns.sbns003.WBjbxxbjnlxVO;
import gov.jslt.taxevent.bsfw.sbns.sbns003.WBjbxxbjwVO;
import gov.jslt.taxevent.bsfw.sbns.sbns003.WBjbxxbnsrlxVO;
import gov.jslt.taxevent.bsfw.sbns.sbns003.WBjbxxbtzVO;
import gov.jslt.taxevent.bsfw.sbns.sbns003.WBjbxxbtzzlxVO;
import gov.jslt.taxevent.bsfw.sbns.sbns003.WBjbxxbwzsVO;
import gov.jslt.taxevent.bsfw.sbns.sbns003.WBjbxxbzbVO;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.StringUtil;
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

public class Sbns003BLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		String dealMethod = req.getDealMethod();
		if ("queryZrrInfo".equals(dealMethod)) {
			return queryZrrInfo(req, con);// 未调用
		} else if ("queryZrrList".equals(dealMethod)) {
			return queryZrrList(req, con);
		} else if ("initFormB".equals(dealMethod)) {
			return initFormB(req, con);
		} else if ("queryXQ".equals(dealMethod)) {
			return queryXQ(req, con);
		} else if ("queryXZJD".equals(dealMethod)) {
			return queryXZJD(req, con);
		} else if ("queryZspmList".equals(dealMethod)) {
			return queryZspmList(req, con);// 未调用
		} else if ("saveData".equals(dealMethod)) {
			return saveData(req, con);
		} else if ("initFormA".equals(dealMethod)) {
			return initFormA(req, con);
		} else if ("querySbList".equals(dealMethod)) {
			return querySbList(req, con);
		} else if ("queryAaginURL".equals(dealMethod)) {
			return queryAaginURL(req, con);
		} else if ("queryData".equals(dealMethod)) {
			return queryData(req, con);
		} else if ("deleteSbInfo".equals(dealMethod)) {
			return deleteSbInfo(req, con);
		}
		return null;
	}

	/**
	 * 初始化基础信息（B主表）
	 * 
	 * @param req
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	private ResponseEvent initFormB(RequestEvent req, Connection con)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		// 获取纳税人信息
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT TO_CHAR(X.SBQXZ, 'yyyy-MM-dd') SBQXZ, ");
		sql.append(" TO_CHAR(X.SSQS, 'yyyy-MM-dd') SSQS, ");
		sql.append(" TO_CHAR(X.SSQZ, 'yyyy-MM-dd') SSQZ ");
		sql.append(" FROM DB_WSBS.T_WB_KSBLB X ");
		sql.append(" WHERE X.ZSXM_DM = '05' ");
		sql.append(" AND X.SBQXR_DM = 'Y01_90' ");
		sql.append(" AND X.GLJGDM = '23200000000' ");
		CachedRowSet rs = QueryCssBPO.findAll(con, sql.toString(), null);
		if (rs.next()) {
			responseEvent.respMapParam.put("sbqxz", rs.getString("SBQXZ"));
			responseEvent.respMapParam.put("ssqs", rs.getString("SSQS"));
			responseEvent.respMapParam.put("ssqz", rs.getString("SSQZ"));
			responseEvent.respMapParam.put("tbrq",
					ZBCoreHelper.getDateTime(con, "yyyy-MM-dd"));
		}
		String zrrSql = "SELECT Z.SWGLM,Z.NSRSBM,Z.GLJG_DM,Z.ZWMC,L.MC ZJMC,Z.ZJ_HM,Z.ZJLX_DM,Z.GJDQ_DM,G.MC GJDQMC FROM DB_WSBS.T_DJ_ZRRDJB Z,DB_WSBS.T_DM_GY_GJ G,DB_WSBS.T_DM_DJ_ZJLX L WHERE Z.SWGLM = ? AND Z.GJDQ_DM = G.GJDQ_DM AND Z.ZJLX_DM = L.ZJLX_DM";
		sqlParams.add(dataMap.get("swglm"));
		rs = QueryCssBPO.findAll(con, zrrSql, sqlParams);
		if (rs.next()) {
			responseEvent.respMapParam.put("swglm", rs.getString("SWGLM"));
			responseEvent.respMapParam.put("nsrsbm", rs.getString("NSRSBM"));
			responseEvent.respMapParam.put("gljg_dm", rs.getString("GLJG_DM"));
			responseEvent.respMapParam.put("zwmc", rs.getString("ZWMC"));
			responseEvent.respMapParam.put("zjmc", rs.getString("ZJMC"));
			responseEvent.respMapParam.put("zj_hm", rs.getString("ZJ_HM"));
			responseEvent.respMapParam.put("zjlx_dm", rs.getString("ZJLX_DM"));
			responseEvent.respMapParam.put("gjdq_dm", rs.getString("GJDQ_DM"));
			responseEvent.respMapParam.put("gjdqmc", rs.getString("GJDQMC"));
		}
		// 判断是否已登记
		String zbSql = "SELECT PZ_XH FROM (SELECT A.PZ_XH FROM DB_WSBS.T_WB_GRXX_B_ZB A WHERE A.RZDW_SWGLM = ? AND A.ZRR_SWGLM = ? AND EXISTS (SELECT 1 FROM DB_WSBS.T_WB_FBSBQK B WHERE B.PZ_XH = A.PZ_XH) ORDER BY A.LR_SJ DESC) WHERE ROWNUM = 1";
		sqlParams.clear();
		sqlParams.add(0);
		sqlParams.add(dataMap.get("swglm"));
		rs = QueryCssBPO.findAll(con, zbSql, sqlParams);
		if (rs.next()) {// 已登记
			String dj_pzxh = rs.getString("PZ_XH");
			// 主表
			WBjbxxbzbVO zbvo = WBjbxxbzbBPO.queryByPK(con, dj_pzxh);
			responseEvent.respMapParam.put("zbvo", zbvo);
			// 无住所
			WBjbxxbwzsVO wzsvo = WBjbxxbwzsBPO.queryByPK(con, dj_pzxh);
			responseEvent.respMapParam.put("wzsvo", wzsvo);
			// 单位信息
			WBjbxxbtzVO tzvo = WBjbxxbtzBPO.queryByPK(con, dj_pzxh);
			responseEvent.respMapParam.put("tzvo", tzvo);
			// 境外
			WBjbxxbjwVO jwvo = WBjbxxbjwBPO.queryByPK(con, dj_pzxh);
			responseEvent.respMapParam.put("jwvo", jwvo);
			// 纳税人类型
			List nsrlxList = WBjbxxbnsrlxBPO.getListByPK(con, dj_pzxh);
			responseEvent.respMapParam.put("nsrlxList", nsrlxList);
			// 投资者类型
			List tzzlxList = WBjbxxbtzzlxBPO.getListByPK(con, dj_pzxh);
			responseEvent.respMapParam.put("tzzlxList", tzzlxList);
			// 缴纳类型
			List jnlxList = WBjbxxbjnlxBPO.getListByPK(con, dj_pzxh);
			responseEvent.respMapParam.put("jnlxList", jnlxList);
			// 残疾类型
			List cjlxList = WBjbxxbcjlxBPO.getListByPK(con, dj_pzxh);
			responseEvent.respMapParam.put("cjlxList", cjlxList);
		}
		// 自然人职业列表
		responseEvent.respMapParam
				.put("zrrzyList", ZBCoreHelper.findZRRZY(con));
		// 国籍地区列表
		responseEvent.respMapParam.put("gjdqList", ZBCoreHelper.findGJ(con));
		// 地理位置市列表
		responseEvent.respMapParam.put("dlwzsList",
				ZBCoreHelper.findDJDLWZS(con));
		// 自然人登记职务列表
		responseEvent.respMapParam
				.put("zrrzwList", ZBCoreHelper.findZRRZW(con));
		// 自然人学历列表
		responseEvent.respMapParam
				.put("zrrxlList", ZBCoreHelper.findZRRXL(con));
		// 注册类型列表
		responseEvent.respMapParam
				.put("zclxList", ZBCoreHelper.findDJZCLX(con));
		// 经营行业小类列表
		responseEvent.respMapParam.put("jyhyxlList",
				ZBCoreHelper.findJYHYXL(con));

		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	/**
	 * 获取自然人登记详细信息
	 * 
	 * @param req
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	private ResponseEvent queryZrrInfo(RequestEvent req, Connection con)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT TO_CHAR(X.SBQXZ, 'yyyy-MM-dd') SBQXZ, ");
		sql.append(" TO_CHAR(X.SSQS, 'yyyy-MM-dd') SSQS, ");
		sql.append(" TO_CHAR(X.SSQZ, 'yyyy-MM-dd') SSQZ ");
		sql.append(" FROM DB_WSBS.T_WB_KSBLB X ");
		sql.append(" WHERE X.ZSXM_DM = '05' ");
		sql.append(" AND X.SBQXR_DM = 'Y01_90' ");
		sql.append(" AND X.GLJGDM = '23200000000' ");
		CachedRowSet rs = QueryCssBPO.findAll(con, sql.toString(), null);
		if (rs.next()) {
			responseEvent.respMapParam.put("sbqxz", rs.getString("SBQXZ"));
			responseEvent.respMapParam.put("ssqs", rs.getString("SSQS"));
			responseEvent.respMapParam.put("ssqz", rs.getString("SSQZ"));
			responseEvent.respMapParam.put("tbrq",
					ZBCoreHelper.getDateTime(con, "yyyy-MM-dd"));
		}
		String zrrSql = "SELECT Z.SWGLM,Z.NSRSBM,Z.GLJG_DM,Z.ZWMC,L.MC ZJMC,Z.ZJ_HM,G.MC GJDQMC FROM DB_WSBS.T_DJ_ZRRDJB Z,DB_WSBS.T_DM_GY_GJ G,DB_WSBS.T_DM_DJ_ZJLX L WHERE Z.SWGLM = ? AND Z.GJDQ_DM = G.GJDQ_DM AND Z.ZJLX_DM = L.ZJLX_DM";
		sqlParams.add(dataMap.get("swglm"));
		rs = QueryCssBPO.findAll(con, zrrSql, sqlParams);
		if (rs.next()) {
			responseEvent.respMapParam.put("swglm", rs.getString("SWGLM"));
			responseEvent.respMapParam.put("nsrsbm", rs.getString("NSRSBM"));
			responseEvent.respMapParam.put("gljg_dm", rs.getString("GLJG_DM"));
			responseEvent.respMapParam.put("zwmc", rs.getString("ZWMC"));
			responseEvent.respMapParam.put("zjmc", rs.getString("ZJMC"));
			responseEvent.respMapParam.put("zj_hm", rs.getString("ZJ_HM"));
			responseEvent.respMapParam.put("gjdqmc", rs.getString("GJDQMC"));
		}

		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	/**
	 * 获取自然人登记列表
	 * 
	 * @param req
	 * @param con
	 * @return
	 * @throws SQLException
	 * @throws TaxBaseBizException
	 */
	private ResponseEvent queryZrrList(RequestEvent req, Connection con)
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

	/**
	 * 根据地市代码获取县区列表
	 * 
	 * @param req
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	private ResponseEvent queryXQ(RequestEvent req, Connection con)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String xzqhds_dm = dataMap.get("xzqhds_dm").toString();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(xzqhds_dm);
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT T.XZQHXQ_DM, T.MC_J MC FROM DB_WSBS.T_DM_GY_XZQHXQ T WHERE T.XY_BJ = '1' AND T.XZQHDS_DM = ? ORDER BY T.MC_J",
						sqlParams);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("XZQHXQ_DM", rs.getString("XZQHXQ_DM"));
			map.put("MC", rs.getString("MC"));
			list.add(map);
		}
		responseEvent.respMapParam.put("xqList", list);

		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	/**
	 * 根据县区代码获取乡镇街道列表
	 * 
	 * @param req
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	private ResponseEvent queryXZJD(RequestEvent req, Connection con)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String xzqhxq_dm = dataMap.get("xzqhxq_dm").toString();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(xzqhxq_dm);
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT T.XZJD_DM, T.MC_J MC FROM DB_WSBS.T_DM_GY_XZJD T WHERE T.XY_BJ = '1' AND T.XZQHXQ_DM = ? ORDER BY T.MC_J",
						sqlParams);
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("XZJD_DM", rs.getString("XZJD_DM"));
			map.put("MC", rs.getString("MC"));
			list.add(map);
		}
		responseEvent.respMapParam.put("xzjdList", list);

		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	/**
	 * 获取征收品目列表
	 * 
	 * @param req
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	private ResponseEvent queryZspmList(RequestEvent req, Connection con)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String gljgdm = dataMap.get("gljgdm").toString();
		String sql = " SELECT X.MC ZSXMMC, X.ZSXM_DM ZSXMDM, T.MC ZSPMMC, T.ZSPM_DM ZSPMDM FROM DB_WSBS.T_DM_GY_ZSXM X, DB_WSBS.T_DM_GY_ZSPM T WHERE X.ZSXM_DM = ? AND X.ZSXM_DM = T.ZSXM_DM AND T.XY_BJ = '1' AND ((T.SJFW_DM LIKE '%' || ? || '%') OR (T.SJFW_DM LIKE '%' || ? || '%') OR (T.SJFW_DM LIKE '%' || ? || '%')) AND T.ZSPM_DM != '9999' ";
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add("05");
		sqlParams.add(gljgdm.substring(0, 7));
		sqlParams.add(gljgdm.substring(0, 5) + "XX");
		sqlParams.add(gljgdm.substring(0, 3) + "XX");
		CachedRowSet rs = QueryCssBPO.findAll(con, sql, sqlParams);
		List<Map<String, String>> zspmList = new ArrayList<Map<String, String>>();
		Map<String, String> zspmMap;
		while (rs.next()) {
			zspmMap = new HashMap<String, String>();
			zspmMap.put("zsxmmc", rs.getString("ZSXMMC"));
			zspmMap.put("zsxmdm", rs.getString("ZSXMDM"));
			zspmMap.put("zspmmc", rs.getString("ZSPMMC"));
			zspmMap.put("zspmdm", rs.getString("ZSPMDM"));
			zspmList.add(zspmMap);
		}
		responseEvent.respMapParam.put("zspmList", zspmList);

		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	/**
	 * 提交
	 * 
	 * @param req
	 * @param con
	 * @return
	 * @throws SQLException
	 * @throws TaxBaseBizException
	 */
	private ResponseEvent saveData(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String swglm = dataMap.get("swglm").toString();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
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
		sqlParams.add(dataMap.get("swglm"));
		CachedRowSet rs = QueryCssBPO.findAll(con, sql.toString(), sqlParams);
		if (rs.next()) {
			String pzxh = ZBCoreHelper.getGUID(con);
			// 主表信息
			WBjbxxbzbVO zbvo = new WBjbxxbzbVO();
			zbvo.setPzxh(pzxh);
			zbvo.setRzdwswglm("0");
			zbvo.setGljgdm(rs.getString("GLJG_DM"));
			zbvo.setZsjgdm(rs.getString("ZSJG_DM"));
			zbvo.setFwjgdm(rs.getString("FWJG_DM"));
			zbvo.setJcjgdm(rs.getString("JCJG_DM"));
			zbvo.setJiancjgdm(rs.getString("JIANCJG_DM"));
			zbvo.setSkssjgdm(rs.getString("SKSSJG_DM"));
			zbvo.setZrrswglm(rs.getString("SWGLM"));
			zbvo.setSfzlxdm(rs.getString("ZJLX_DM"));
			zbvo.setSfzhm(rs.getString("ZJ_HM"));
			// 页面获取
			zbvo.setXm(StringUtil.empty(dataMap.get("xm")));// 姓名
			zbvo.setRzdwmc(StringUtil.empty(dataMap.get("rzdwmc_zbvo")));// 任职受雇单位名称
			zbvo.setRzdwnsrsbm(StringUtil.empty(dataMap.get("rzdwnsrsbm")));// 纳税人识别号
			zbvo.setDzyx(StringUtil.empty(dataMap.get("dzyx")));// 电子邮箱
			zbvo.setLxdzsjdm(StringUtil.empty(dataMap.get("lxdzsjdm_zbvo")));// 省份(默认为32：江苏省)
			zbvo.setLxdzsqdm(StringUtil.empty(dataMap.get("lxdzsqdm_zbvo")));// 市区
			zbvo.setLxdzxjdm(StringUtil.empty(dataMap.get("lxdzxjdm_zbvo")));// 区县
			zbvo.setLxdzxzdm(StringUtil.empty(dataMap.get("lxdzxzdm_zbvo")));// 街道
			zbvo.setLxdz(StringUtil.empty(dataMap.get("lxdz_zbvo")));// 联系地址
			zbvo.setYzbm(StringUtil.empty(dataMap.get("yzbm_zbvo")));// 邮政编码
			zbvo.setSj(StringUtil.empty(dataMap.get("sj")));// 手机
			zbvo.setGh(StringUtil.empty(dataMap.get("gh")));// 固话
			zbvo.setZydm(StringUtil.empty(dataMap.get("zydm")));// 职业
			zbvo.setZwdm(StringUtil.empty(dataMap.get("zwdm")));// 职务
			zbvo.setXldm(StringUtil.empty(dataMap.get("xldm")));// 学历
			zbvo.setCjqk(StringUtil.empty(dataMap.get("cjqk")));// 残疾等级情况
			zbvo.setStr_sfssqqsrq(StringUtil.empty(dataMap.get("str_sfssqqsrq")));// 税费所属期起始日期
			zbvo.setStr_sfssqzzrq(StringUtil.empty(dataMap.get("str_sfssqzzrq")));// 税费所属期终止日期
			WBjbxxbzbBPO.insert(con, zbvo);// 插入数据

			// 境外信息
			WBjbxxbjwVO jwvo = new WBjbxxbjwVO();
			jwvo.setPzxh(pzxh);
			// 页面获取
			jwvo.setLxdzsjdm(StringUtil.empty(dataMap.get("lxdzsjdm_jwvo")));// 省份(默认为32：江苏省)
			jwvo.setLxdzsqdm(StringUtil.empty(dataMap.get("lxdzsqdm_jwvo")));// 市区
			jwvo.setLxdzxjdm(StringUtil.empty(dataMap.get("lxdzxjdm_jwvo")));// 区县
			jwvo.setLxdzxzdm(StringUtil.empty(dataMap.get("lxdzxzdm_jwvo")));// 街道
			jwvo.setLxdz(StringUtil.empty(dataMap.get("lxdz_jwvo")));// 联系地址
			jwvo.setYzdm(StringUtil.empty(dataMap.get("yzdm")));// 邮政编码
			WBjbxxbjwBPO.insert(con, jwvo);

			// 投资信息
			WBjbxxbtzVO tzvo = new WBjbxxbtzVO();
			tzvo.setPzxh(pzxh);
			// 页面获取
			tzvo.setBtzdwmc(StringUtil.empty(dataMap.get("btzdwmc")));// 名称
			tzvo.setBtzdwswglm(StringUtil.empty(dataMap.get("btzdwswglm")));// 扣缴义务人编码
			tzvo.setBtzdwdz(StringUtil.empty(dataMap.get("btzdwdz")));// 地址
			tzvo.setBtzdwyzbm(StringUtil.empty(dataMap.get("btzdwyzbm")));// 邮政编码
			tzvo.setBtzdwdjzclxdm(StringUtil.empty(dataMap.get("btzdwdjzclxdm")));// 登记注册类型
			tzvo.setBtzdwhydm(StringUtil.empty(dataMap.get("btzdwhydm")));// 行业
			tzvo.setBtzdwsdszsfsdm(StringUtil.empty(dataMap
					.get("btzdwsdszsfsdm")));// 所得税征收方式
			tzvo.setGstzje(StringUtil.empty(dataMap.get("gstzje")));// 公司股本（投资）总额
			tzvo.setGrtzje(StringUtil.empty(dataMap.get("grtzje")));// 个人股本（投资）额
			WBjbxxbtzBPO.insert(con, tzvo);

			// 无住所信息
			WBjbxxbwzsVO wzsvo = new WBjbxxbwzsVO();
			wzsvo.setPzxh(pzxh);
			// 页面获取
			wzsvo.setNsrsbm(StringUtil.empty(dataMap.get("nsrsbm")));// 纳税人识别号
			wzsvo.setGjdqdm(StringUtil.empty(dataMap.get("gjdqdm_wzsvo")));// 国籍（地区）
			wzsvo.setCsd(StringUtil.empty(dataMap.get("csd")));// 出生地
			wzsvo.setXb(StringUtil.empty(dataMap.get("xb")));// 性别
			wzsvo.setStr_csrq(StringUtil.empty(dataMap.get("str_csrq")));// 出生日期
			wzsvo.setLdjyzhm(StringUtil.empty(dataMap.get("ldjyzhm")));// 劳动就业证号码
			wzsvo.setSfxddyg(StringUtil.empty(dataMap.get("sfxddyg")));// 是否税收协定缔约国对方居民
			wzsvo.setJnzwdm(StringUtil.empty(dataMap.get("jnzwdm")));// 境内职务
			wzsvo.setJwzwdm(StringUtil.empty(dataMap.get("jwzwdm")));// 境外职务
			wzsvo.setStr_lhsj(StringUtil.empty(dataMap.get("str_lhsj")));// 来华时间
			wzsvo.setStr_rzqx(StringUtil.empty(dataMap.get("str_rzqx")));// 任职期限
			wzsvo.setStr_ljrq(StringUtil.empty(dataMap.get("str_ljrq")));// 预计离境时间
			wzsvo.setLjdd(StringUtil.empty(dataMap.get("ljdd")));// 预计离境地点
			wzsvo.setRzdwmc(StringUtil.empty(dataMap.get("rzdwmc_wzsvo")));// 境内任职受雇单位名称
			wzsvo.setRzdwswglm(StringUtil.empty(dataMap.get("rzdwswglm_wzsvo")));// 境内任职受雇单位扣缴义务人编码
			wzsvo.setRzdwdz(StringUtil.empty(dataMap.get("rzdwdz_wzsvo")));// 境内任职受雇单位地址
			wzsvo.setRzdwyzbm(StringUtil.empty(dataMap.get("rzdwyzbm_wzsvo")));// 境内任职受雇单位邮政编码
			wzsvo.setQydwmc(StringUtil.empty(dataMap.get("qydwmc")));// 境内受聘签约单位名称
			wzsvo.setQydwswglm(StringUtil.empty(dataMap.get("qydwswglm")));// 境内受聘签约单位扣缴义务人编码
			wzsvo.setQydwdz(StringUtil.empty(dataMap.get("qydwdz")));// 境内受聘签约单位地址
			wzsvo.setQydwyzbm(StringUtil.empty(dataMap.get("qydwyzbm")));// 境内受聘签约单位邮政编码
			wzsvo.setPqdwmc(StringUtil.empty(dataMap.get("pqdwmc")));// 境外派遣单位名称
			wzsvo.setPqdwdz(StringUtil.empty(dataMap.get("pqdwdz")));// 境外派遣单位地址
			wzsvo.setZfd(StringUtil.empty(dataMap.get("zfd")));// 支付地
			wzsvo.setJwzwgb(StringUtil.empty(dataMap.get("jwzwgb")));// 境外支付国国别（地区）
			WBjbxxbwzsBPO.insert(con, wzsvo);

			// 纳税人类型
			List<MorphDynaBean> nsrlxList = (List<MorphDynaBean>) dataMap
					.get("nsrlxList");
			WBjbxxbnsrlxVO nsrlxVO;
			if (null != nsrlxList) {
				for (int i = 0; i < nsrlxList.size(); i++) {
					nsrlxVO = new WBjbxxbnsrlxVO();
					nsrlxVO.setPzxh(pzxh);
					nsrlxVO.setMxxh(String.valueOf(i + 1));
					nsrlxVO.setNsrlxdm(nsrlxList.get(i).get("nsrlxdm")
							.toString());
					WBjbxxbnsrlxBPO.insert(con, nsrlxVO);
				}
			}

			// 投资者类型代码
			List<MorphDynaBean> tzzlxList = (List<MorphDynaBean>) dataMap
					.get("tzzlxList");
			WBjbxxbtzzlxVO tzzlxVO;
			if (null != tzzlxList) {
				for (int i = 0; i < tzzlxList.size(); i++) {
					tzzlxVO = new WBjbxxbtzzlxVO();
					tzzlxVO.setPzxh(pzxh);
					tzzlxVO.setMxxh(String.valueOf(i + 1));
					tzzlxVO.setTzzlxdm(tzzlxList.get(i).get("tzzlxdm")
							.toString());
					WBjbxxbtzzlxBPO.insert(con, tzzlxVO);
				}
			}

			// 三费一金缴纳代码
			List<MorphDynaBean> jnlxList = (List<MorphDynaBean>) dataMap
					.get("jnlxList");
			WBjbxxbjnlxVO jnlxVO;
			if (null != jnlxList) {
				for (int i = 0; i < jnlxList.size(); i++) {
					jnlxVO = new WBjbxxbjnlxVO();
					jnlxVO.setPzxh(pzxh);
					jnlxVO.setMxxh(String.valueOf(i + 1));
					jnlxVO.setJnlxdm(jnlxList.get(i).get("jnlxdm").toString());
					WBjbxxbjnlxBPO.insert(con, jnlxVO);
				}
			}

			// 残疾类型代码
			List<MorphDynaBean> cjlxList = (List<MorphDynaBean>) dataMap
					.get("cjlxList");
			WBjbxxbcjlxVO cjlxVO;
			if (null != cjlxList) {
				for (int i = 0; i < cjlxList.size(); i++) {
					cjlxVO = new WBjbxxbcjlxVO();
					cjlxVO.setPzxh(pzxh);
					cjlxVO.setMxxh(String.valueOf(i + 1));
					cjlxVO.setCjlxdm(cjlxList.get(i).get("cjlxdm").toString());
					WBjbxxbcjlxBPO.insert(con, cjlxVO);
				}
			}

			// 附表申报情况
			WBfbsbqkVO fbsbQkVO = new WBfbsbqkVO();
			fbsbQkVO.setPzxh(pzxh);
			fbsbQkVO.setPzzldm("31343");
			fbsbQkVO.setFbpzzldm("31350");
			fbsbQkVO.setSbbxh("1");
			WBfbsbqkBPO.insert(con, fbsbQkVO);

			// 个税自行申报
			List<MorphDynaBean> mxxxList = (List<MorphDynaBean>) dataMap
					.get("mxxx");
			MorphDynaBean bean;
			LogWritter.sysInfo("开始组装个人所得税VO......");
			WBgrsdssbabVO grsdsbabVO;
			if (null != mxxxList) {
				for (int i = 0; i < mxxxList.size(); i++) {
					bean = mxxxList.get(i);
					grsdsbabVO = new WBgrsdssbabVO();
					grsdsbabVO.setPzxh(pzxh);// 凭证序号
					grsdsbabVO.setSbmxxh(String.valueOf(i + 1));// 申报明细号
					grsdsbabVO.setSwglm(rs.getString("SWGLM"));// 税务管理码
					grsdsbabVO.setZsxmdm("05");// 征收项目代码
					grsdsbabVO.setZspmdm(bean.get("zspmdm").toString());// 征收品目代码
					grsdsbabVO.setRzsgdwmc(rs.getString("Rzdwmc").toString());// 任职受雇单位名称
					grsdsbabVO.setStr_sdqqsrq(StringUtil.empty(dataMap
							.get("str_sfssqqsrq")));// 税款所属期起
					grsdsbabVO.setStr_sdqzzrq(StringUtil.empty(dataMap
							.get("str_sfssqzzrq")));// 税款所属期止
					grsdsbabVO
							.setStr_sbqx(StringUtil.empty(dataMap.get("sbqx")));// 申报期限
					grsdsbabVO.setSreje(bean.get("sreje").toString());// 收入额
					grsdsbabVO.setMssdeje(bean.get("mssdeje").toString());// 免税所得
					grsdsbabVO.setJbylbxfje(bean.get("jbylbxfje").toString());// 基本养老费
					grsdsbabVO.setHbylbxfje(bean.get("hbylbxfje").toString());// 基本医疗保险费
					grsdsbabVO.setSybxfje(bean.get("sybxfje").toString());// 失业保险费
					grsdsbabVO.setZfgjjje(bean.get("zfgjjje").toString());// 住房公积金
					grsdsbabVO.setCcyzje(bean.get("ccyzje").toString());// 财产原值
					grsdsbabVO.setYxkcdsfje(bean.get("yxkcdsfje").toString());// 允许扣除的税费
					grsdsbabVO.setQtje(bean.get("qtje").toString());// 其它
					grsdsbabVO.setHjje(bean.get("hjje").toString());// 合计
					grsdsbabVO.setJcfyje(bean.get("jcfyje").toString());// 减除费用
					grsdsbabVO.setZykcdjzeje(bean.get("zykcdjzeje").toString());// 准予扣除的捐赠额
					grsdsbabVO.setYnssdeje(bean.get("ynssdeje").toString());// 应纳所得额
					grsdsbabVO.setSl(bean.get("sl").toString());// 税率
					grsdsbabVO.setSskcs(bean.get("sskcs").toString());// 速算扣除额
					grsdsbabVO.setYnseje(bean.get("ynseje").toString());// 应纳税额
					grsdsbabVO.setJmseje(bean.get("jmseje").toString());// 减免税额
					grsdsbabVO.setYjseje(bean.get("yjseje").toString());// 已缴税额
					grsdsbabVO.setYbtsseje(bean.get("ybtsseje").toString());// 应补（退）税额

					grsdsbabVO.setZwmc(rs.getString("NSR_MC"));// 纳税人中文名称
					grsdsbabVO.setGjdqdm(rs.getString("GJDQ_DM"));// 国家地区代码
					grsdsbabVO.setSfzjlx(rs.getString("ZJLX_DM"));// 身份证件类型
					grsdsbabVO.setSfzjhm(rs.getString("ZJ_HM"));// 证件号码
					grsdsbabVO.setNsrsbm(rs.getString("NSRSBM"));// 纳税人识别码
					grsdsbabVO
							.setZxsbqx(StringUtil.empty(dataMap.get("zxsbqx")));// 自行申报情形

					grsdsbabVO.setGljgdm(zbvo.getGljgdm());// 管理机关代码
					grsdsbabVO.setZsjgdm(zbvo.getZsjgdm());// 征收机关代码

					grsdsbabVO.setJcjgdm(zbvo.getJcjgdm());// 稽查机关代码
					grsdsbabVO.setJiancjgdm(zbvo.getJiancjgdm());// 检查机关代码
					grsdsbabVO.setFwjgdm(zbvo.getFwjgdm());// 纳税服务机关代码
					grsdsbabVO.setSkssjgdm(zbvo.getSkssjgdm());// 税款所属机关
					WBgrsdssbabBPO.insert(con, grsdsbabVO);
				}
			}
			// 申报情况
			WBsbbqkZrrVO sbbqkZrrVO = new WBsbbqkZrrVO();
			sbbqkZrrVO.setDjzclxdm(rs.getString("ZCLX_DM"));
			sbbqkZrrVO.setLsgxdm(rs.getString("LSGX_DM"));
			sbbqkZrrVO.setGbhydm(rs.getString("GBHY_DM"));
			// 凭证序号
			sbbqkZrrVO.setPzxh(pzxh);
			// 凭证种类代码
			sbbqkZrrVO.setPzzldm("31343");
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
			sbbqkZrrVO.setStr_dhrq(rs.getString("SCDHSJ_RQ"));
			sbbqkZrrVO.setZymc(rs.getString("ZYMC"));
			sbbqkZrrVO.setRzdwmc(rs.getString("RZDWMC"));
			sbbqkZrrVO.setRzdwswglm(rs.getString("RZDWSWGLM"));
			sbbqkZrrVO.setJzd(rs.getString("JNZZ"));
			sbbqkZrrVO.setLxdz(rs.getString("JNTXDZ"));
			sbbqkZrrVO.setYb(rs.getString("JNTXDZYB"));
			sbbqkZrrVO.setLxdh(rs.getString("YDDH"));
			// 填表日期
			sbbqkZrrVO.setStr_tbrq(StringUtil.empty(dataMap.get("tbrq")));
			// 税费所属期
			sbbqkZrrVO.setStr_sfssqqsrq(StringUtil.empty(dataMap
					.get("str_sfssqqsrq")));
			sbbqkZrrVO.setStr_sfssqzzrq(StringUtil.empty(dataMap
					.get("str_sfssqzzrq")));
			// 开户银行
			sbbqkZrrVO.setKhyhmc(rs.getString("YHMC"));
			// 银行账号
			sbbqkZrrVO.setYhzh(rs.getString("YH_ZH"));
			// 注册类型名称
			// sbbqkZrrVO.setZclxmc(loginVO.getZclxdm());
			// 注册类型代码
			sbbqkZrrVO.setDjzclxdm(rs.getString("ZCLX_DM"));
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
			sbbqkZrrVO.setFwjgdm(rs.getString("FWJG_DM"));
			sbbqkZrrVO.setJiancjgdm(rs.getString("JIANCJG_DM"));
			// 隶属关系
			sbbqkZrrVO.setLsgxdm(rs.getString("LSGX_DM"));
			// 本张报表应补退税额合计
			sbbqkZrrVO.setYbtsehdjje(StringUtil.empty(dataMap.get("ybtsehj")));
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

			sqlParams.clear();
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
				responseEvent.respMapParam.put("ZFBUrl",
						resultMap.get("ZFBUrl"));
			}
			responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
			responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		} else {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					ZBCoreHelper.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
					"自然人信息异常"));
		}
		return responseEvent;
	}

	/**
	 * 初始化主表信息
	 * 
	 * @param req
	 * @param con
	 * @return
	 * @throws SQLException
	 * @throws TaxBaseBizException
	 */
	private ResponseEvent initFormA(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String gljgdm = dataMap.get("gljgdm").toString();// 税务管理码
		String ssqq = dataMap.get("ssqq").toString();// 税款所属期起
		String ssqz = dataMap.get("ssqz").toString();// 税款所属期止
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		// 获取征收品目列表
		String zspmSql = " SELECT X.MC ZSXMMC, X.ZSXM_DM ZSXMDM, T.MC ZSPMMC, T.ZSPM_DM ZSPMDM FROM DB_WSBS.T_DM_GY_ZSXM X, DB_WSBS.T_DM_GY_ZSPM T WHERE X.ZSXM_DM = ? AND X.ZSXM_DM = T.ZSXM_DM AND T.XY_BJ = '1' AND ((T.SJFW_DM LIKE '%' || ? || '%') OR (T.SJFW_DM LIKE '%' || ? || '%') OR (T.SJFW_DM LIKE '%' || ? || '%')) AND T.ZSPM_DM != '9999' ORDER BY ZSPMDM ";
		sqlParams.add("05");
		sqlParams.add(gljgdm.substring(0, 7));
		sqlParams.add(gljgdm.substring(0, 5) + "XX");
		sqlParams.add(gljgdm.substring(0, 3) + "XX");
		CachedRowSet rs = QueryCssBPO.findAll(con, zspmSql, sqlParams);
		List<Map<String, Object>> zspmList = new ArrayList<Map<String, Object>>();
		Map<String, Object> zspmMap;
		while (rs.next()) {
			zspmMap = new HashMap<String, Object>();
			zspmMap.put("zsxmmc", rs.getString("ZSXMMC"));
			zspmMap.put("zsxmdm", rs.getString("ZSXMDM"));
			zspmMap.put("zspmmc", rs.getString("ZSPMMC"));
			zspmMap.put("zspmdm", rs.getString("ZSPMDM"));
			zspmList.add(zspmMap);
		}
		responseEvent.respMapParam.put("zspmList", zspmList);
		// 获取税率
		sqlParams.clear();
		// 判断所属期在9.1号前后
		String ssqSql = "select to_date( ? , 'yyyy-mm-dd') - to_date('2011-09-01', 'yyyy-mm-dd') as bj from dual";
		sqlParams.add(ssqq);
		CachedRowSet bjRs = QueryCssBPO.findAll(con, ssqSql, sqlParams);
		bjRs.next();
		double bj = Double.parseDouble((bjRs.getString("bj")));
		String sql = "SELECT *"
				+ " FROM (SELECT D.ZSPM_DM,"
				+ " 0 AS XX,"
				+ " 9999999999999999.99 AS SX,"
				+ " D.SL,"
				+ " 0 AS SSKC"
				+ " FROM DB_WSBS.T_DM_GY_ZSPM D"
				+ " WHERE D.ZSXM_DM = '05'"
				+ " AND (SJFW_DM LIKE '%2320618%' OR SJFW_DM LIKE '%23206XX%' OR"
				+ " SJFW_DM LIKE '232XX%') AND D.XY_BJ = '1'"
				+ " AND D.SL != 0 AND D.ZSPM_DM NOT IN ("
				+ " select zspm_jyxm_dm as ZSPM_DM from DB_WSBS.t_Cs_Sskcb cs"
				+ " where cs.gljg_dm In (Select Swjg_Dm From DB_WSBS.t_Dm_Gy_Swjg"
				+ " Connect By Prior Sjswjg_Dm = Swjg_Dm"
				+ " Start With Swjg_Dm = ?) And cs.Zsxm_Dm = '05'"
				+ " and (To_Date(?, 'yyyy-mm-dd hh24:mi:ss') between"
				+ " to_date(to_char(Nvl(cs.Yx_Qsrq, Date '1900-1-1'),"
				+ " 'yyyy-mm-dd') || ' 00:00:00',"
				+ " 'yyyy-mm-dd hh24:mi:ss') and"
				+ " to_date(to_char(Nvl(cs.Yx_Zzrq, Date '2099-1-1'),"
				+ " 'yyyy-mm-dd') || ' 23:59:59',"
				+ " 'yyyy-mm-dd hh24:mi:ss') or"
				+ " To_Date(?, 'yyyy-mm-dd hh24:mi:ss') between"
				+ " to_date(to_char(Nvl(cs.Yx_Qsrq, Date '1900-1-1'),"
				+ " 'yyyy-mm-dd') || ' 00:00:00',"
				+ " 'yyyy-mm-dd hh24:mi:ss') and"
				+ " to_date(to_char(Nvl(cs.Yx_Zzrq, Date '2099-1-1'),"
				+ " 'yyyy-mm-dd') || ' 23:59:59',"
				+ " 'yyyy-mm-dd hh24:mi:ss')))  UNION"
				+ " select zspm_jyxm_dm as ZSPM_DM, xx_je        as XX,"
				+ "  sx_je  as SX, sl  as SL,"
				+ "  sskcs_je  as SSKC from DB_WSBS.t_Cs_Sskcb cs"
				+ " where cs.gljg_dm In (Select Swjg_Dm From DB_WSBS.t_Dm_Gy_Swjg"
				+ " Connect By Prior Sjswjg_Dm = Swjg_Dm"
				+ " Start With Swjg_Dm = ?)  And cs.Zsxm_Dm = '05'"
				+ " and (To_Date(?, 'yyyy-mm-dd hh24:mi:ss') between"
				+ " to_date(to_char(Nvl(cs.Yx_Qsrq, Date '1900-1-1'),"
				+ " 'yyyy-mm-dd') || ' 00:00:00',"
				+ " 'yyyy-mm-dd hh24:mi:ss') and"
				+ " to_date(to_char(Nvl(cs.Yx_Zzrq, Date '2099-1-1'),"
				+ " 'yyyy-mm-dd') || ' 23:59:59',"
				+ " 'yyyy-mm-dd hh24:mi:ss') or"
				+ " To_Date(?, 'yyyy-mm-dd hh24:mi:ss') between"
				+ " to_date(to_char(Nvl(cs.Yx_Qsrq, Date '1900-1-1'),"
				+ " 'yyyy-mm-dd') || ' 00:00:00',"
				+ " 'yyyy-mm-dd hh24:mi:ss') and"
				+ " to_date(to_char(Nvl(cs.Yx_Zzrq, Date '2099-1-1'),"
				+ " 'yyyy-mm-dd') || ' 23:59:59',"
				+ " 'yyyy-mm-dd hh24:mi:ss'))) order by ZSPM_DM,XX";
		sqlParams.clear();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		if (0 < bj) {
			// 所属期起在2011-9-1之后按新税率算
			sqlParams.add(gljgdm);
			sqlParams.add(ssqq);
			sqlParams.add(ssqz);
			sqlParams.add(gljgdm);
			sqlParams.add(ssqq);
			sqlParams.add(ssqz);
			CachedRowSet rs1 = QueryCssBPO.findAll(con, sql, sqlParams);
			while (rs1.next()) {
				map = new HashMap<String, String>();
				map.put("zspmdm", rs1.getString("ZSPM_DM"));
				map.put("xx", rs1.getString("XX"));
				map.put("sx", rs1.getString("SX"));
				map.put("sl", rs1.getString("SL"));
				map.put("sskc", rs1.getString("SSKC"));
				list.add(map);
			}
		} else {
			// 所属期起在2011-9-1之前按旧税率算
			sqlParams.add(gljgdm);
			sqlParams.add("2011-07-01");
			sqlParams.add("2011-07-31");
			sqlParams.add(gljgdm);
			sqlParams.add("2011-07-01");
			sqlParams.add("2011-07-31");
			CachedRowSet rs2 = QueryCssBPO.findAll(con, sql, sqlParams);
			while (rs2.next()) {
				map = new HashMap<String, String>();
				map.put("zspmdm", rs2.getString("ZSPM_DM"));
				map.put("xx", rs2.getString("XX"));
				map.put("sx", rs2.getString("SX"));
				map.put("sl", rs2.getString("SL"));
				map.put("sskc", rs2.getString("SSKC"));
				list.add(map);
			}
		}
		// // 整合征收品目和税率
		// List<Map<String, String>> initList;
		// for (int i = 0; i < zspmList.size(); i++) {
		// initList = new ArrayList<Map<String, String>>();
		// for (int j = 0; j < list.size(); j++) {
		// if (list.get(j).get("zspmdm")
		// .equals(zspmList.get(i).get("zspmdm"))) {
		// initList.add(list.get(j));
		// }
		// }
		// zspmList.get(i).put("slList", initList);
		// }
		responseEvent.respMapParam.put("slList", list);
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	/**
	 * 查询已申报列表
	 * 
	 * @param req
	 * @param con
	 * @return
	 * @throws SQLException
	 * @throws TaxBaseBizException
	 */
	private ResponseEvent querySbList(RequestEvent req, Connection con)
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
		sql.append(" SELECT Z.PZ_XH, Z.ZSJG_DM, to_char(Z.TJ_SJ,'yyyy-MM-dd') TJ_SJ, Z.YBTSEHDJ_JE, Z.ZT, T.ZT_MC ");
		sql.append(" FROM DB_WSBS.T_WB_SBBQK_ZRR Z, DB_WSBS.T_DM_WB_ZT T ");
		sql.append(" WHERE Z.ZJLX_DM = ? ");
		sql.append(" AND Z.ZJ_HM = ? ");
		sql.append(" and z.lr_sj > to_date(?, 'yyyy-MM-dd') ");
		sql.append(" AND Z.PZZL_DM='31343' ");
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
			sbMap.put("zsjg_dm", rs.getString("ZSJG_DM"));
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
	 * 再次发起扣款
	 * 
	 * @param req
	 * @param con
	 * @return
	 * @throws SQLException
	 * @throws TaxBaseBizException
	 */
	private ResponseEvent queryAaginURL(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		dataMap.put("ddly", "01");
		String zsjgdm = dataMap.get("zsjgdm").toString();
		// 判断是否配置支付链接
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(zsjgdm.substring(0, 7) + "0000");
		CachedRowSet rs = QueryCssBPO
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
		dataMap.put("hasUrl", hasUrl);
		dataMap.put("hasZFBUrl", hasZFBUrl);
		Map<String, String> resultMap = ZBCoreHelper
				.queryAaginURL(dataMap, con);
		if (null == resultMap) {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB0024);
			responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(con,
					GeneralCons.ERROR_CODE_ZB0024));
		} else {
			responseEvent.respMapParam.put("url", resultMap.get("WYUrl"));
			responseEvent.respMapParam.put("url", resultMap.get("ZFBUrl"));
			responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
			responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		}
		return responseEvent;
	}

	/**
	 * 查看申报详细信息
	 * 
	 * @param req
	 * @param con
	 * @return
	 * @throws SQLException
	 * @throws TaxBaseBizException
	 */
	private ResponseEvent queryData(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		String pzxh = dataMap.get("pzxh").toString();
		// 获取主表信息
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT A.ZXSBQX,Z.MC_J,A.ZSPM_DM,A.SRE_JE,A.MSSDE_JE,A.JBYLBXF_JE,A.HBYLBXF_JE,A.SYBXF_JE, ");
		sql.append(" A.ZFGJJ_JE,A.CCYZ_JE,A.YXKCDSF_JE,A.QT_JE,A.HJ_JE,A.JCFY_JE,A.ZYKCDJZE_JE, ");
		sql.append(" A.YNSSDE_JE,A.JMSE_JE,A.YJSE_JE,A.SL,A.SSKCS,A.YNSE_JE,A.YBTSSE_JE, ");
		sql.append(" TO_CHAR(A.SDQ_ZZRQ,'yyyy-MM-dd') SDQ_ZZRQ ");
		sql.append(" FROM DB_WSBS.T_WB_GRSDSSBB_A A, DB_WSBS.T_DM_GY_ZSPM Z ");
		sql.append(" WHERE A.PZ_XH = ? AND A.ZSPM_DM = Z.ZSPM_DM AND Z.ZSXM_DM = '05' ");
		sqlParams.add(pzxh);
		CachedRowSet rs = QueryCssBPO.findAll(con, sql.toString(), sqlParams);
		List<Map<String, String>> sbmxList = new ArrayList<Map<String, String>>();
		Map<String, String> sbmxMap;
		while (rs.next()) {
			sbmxMap = new HashMap<String, String>();
			sbmxMap.put("zxsbqx", rs.getString("ZXSBQX"));
			sbmxMap.put("zspmmc", rs.getString("MC_J"));
			sbmxMap.put("zspmdm", rs.getString("ZSPM_DM"));
			sbmxMap.put("sre", rs.getString("SRE_JE"));// 收入额
			sbmxMap.put("mssd", rs.getString("MSSDE_JE"));// 免税所得
			sbmxMap.put("jbylbxf", rs.getString("JBYLBXF_JE"));// 基本养老保险费
			sbmxMap.put("hbylbxf", rs.getString("JBYLBXF_JE"));// 基本医疗保险费
			sbmxMap.put("sybxf", rs.getString("SYBXF_JE"));// 失业保险费
			sbmxMap.put("zfgjj", rs.getString("ZFGJJ_JE"));// 住房公积金
			sbmxMap.put("ccyz", rs.getString("CCYZ_JE"));// 财产原值
			sbmxMap.put("yxkcdsf", rs.getString("YXKCDSF_JE"));// 允许扣除的税费
			sbmxMap.put("qt", rs.getString("QT_JE"));// 其他
			sbmxMap.put("hj", rs.getString("HJ_JE"));// 合计
			sbmxMap.put("jcfy", rs.getString("JCFY_JE"));// 减除费用
			sbmxMap.put("zykcdjze", rs.getString("ZYKCDJZE_JE"));// 准予扣除的捐赠额
			sbmxMap.put("ynssde", rs.getString("YNSSDE_JE"));// 应纳税所得额
			sbmxMap.put("sl", rs.getString("SL"));// 税率
			sbmxMap.put("sskcs", rs.getString("SSKCS"));// 速算扣除数
			sbmxMap.put("ynse", rs.getString("YNSE_JE"));// 应纳税额
			sbmxMap.put("jmse", rs.getString("JMSE_JE"));// 减免税额
			sbmxMap.put("yjse", rs.getString("YJSE_JE"));// 已缴税额
			sbmxMap.put("ybtse", rs.getString("YBTSSE_JE"));// 应补退税额
			sbmxMap.put("ssqz", rs.getString("SDQ_ZZRQ"));// 税款所属期止
			sbmxList.add(sbmxMap);
		}
		responseEvent.respMapParam.put("sbmxList", sbmxList);// 主表申报信息集合
		sqlParams.clear();
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
		responseEvent.respMapParam.put("tbrq", tbrq);// 填表日期
		// 附表信息
		// 主表
		WBjbxxbzbVO zbvo = WBjbxxbzbBPO.queryByPK(con, pzxh);
		responseEvent.respMapParam.put("zbvo", zbvo);
		// 无住所
		WBjbxxbwzsVO wzsvo = WBjbxxbwzsBPO.queryByPK(con, pzxh);
		responseEvent.respMapParam.put("wzsvo", wzsvo);
		// 单位信息
		WBjbxxbtzVO tzvo = WBjbxxbtzBPO.queryByPK(con, pzxh);
		responseEvent.respMapParam.put("tzvo", tzvo);
		// 境外
		WBjbxxbjwVO jwvo = WBjbxxbjwBPO.queryByPK(con, pzxh);
		responseEvent.respMapParam.put("jwvo", jwvo);
		// 纳税人类型
		List nsrlxList = WBjbxxbnsrlxBPO.getListByPK(con, pzxh);
		responseEvent.respMapParam.put("nsrlxList", nsrlxList);
		// 投资者类型
		List tzzlxList = WBjbxxbtzzlxBPO.getListByPK(con, pzxh);
		responseEvent.respMapParam.put("tzzlxList", tzzlxList);
		// 缴纳类型
		List jnlxList = WBjbxxbjnlxBPO.getListByPK(con, pzxh);
		responseEvent.respMapParam.put("jnlxList", jnlxList);
		// 残疾类型
		List cjlxList = WBjbxxbcjlxBPO.getListByPK(con, pzxh);
		responseEvent.respMapParam.put("cjlxList", cjlxList);
		// 自然人职业列表
		responseEvent.respMapParam
				.put("zrrzyList", ZBCoreHelper.findZRRZY(con));
		// 国籍地区列表
		responseEvent.respMapParam.put("gjdqList", ZBCoreHelper.findGJ(con));
		// 地理位置市列表
		responseEvent.respMapParam.put("dlwzsList",
				ZBCoreHelper.findDJDLWZS(con));
		// 自然人登记职务列表
		responseEvent.respMapParam
				.put("zrrzwList", ZBCoreHelper.findZRRZW(con));
		// 自然人学历列表
		responseEvent.respMapParam
				.put("zrrxlList", ZBCoreHelper.findZRRXL(con));
		// 注册类型列表
		responseEvent.respMapParam
				.put("zclxList", ZBCoreHelper.findDJZCLX(con));
		// 经营行业小类列表
		responseEvent.respMapParam.put("jyhyxlList",
				ZBCoreHelper.findJYHYXL(con));
		// 自然人申报情况表
		responseEvent.respMapParam.put("zrrsbQkVo",
				WBsbbqkZrrBPO.queryByPK(con, pzxh));
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	private ResponseEvent deleteSbInfo(RequestEvent req, Connection con)
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

	@Override
	protected ResponseEvent validateData(RequestEvent req, Connection con)
			throws Exception {
		return null;
	}

}
