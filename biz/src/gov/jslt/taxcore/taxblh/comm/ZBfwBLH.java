package gov.jslt.taxcore.taxblh.comm;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.bpo.QueryCssBPO;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.cssesb.esbevent.ESBRequestEvent;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.StringUtil;
import sun.jdbc.rowset.CachedRowSet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZBfwBLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		String delMethod = req.getDealMethod();
		if ("search".equals(delMethod)) {
			return search(req, con);
		} else if ("queryFbzl".equals(delMethod)) {// 附报资料
			return queryFbzl(req, con);
		} else if ("queryZcyj".equals(delMethod)) {// 政策依据
			return queryZcyj(req, con);
		} else if ("queryYwsxByXl".equals(delMethod)) {// 减免项目
			return queryYwsxByXl(req, con);
		} else if ("querySlyj".equals(delMethod)) {// 受理意见
			return querySlyj(req, con);
		} else if ("queryKey".equals(delMethod)) {// 受理意见
			return queryKey(req, con);
		} else if ("querySssq".equals(delMethod)) {// 查询本月涉税申请列表
			return querySssq(req, con);
		} else if ("querySbList".equals(delMethod)) {// 查询本月申报信息列表
			return querySbList(req, con);
		} else if ("queryData".equals(delMethod)) {// 查询本月申报信息及涉税信息
			return queryData(req, con);
		}
		return null;
	}

	protected ResponseEvent queryData(RequestEvent req, Connection con)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String swglm = dataMap.get("swglm").toString();
		String gljgdm = dataMap.get("gljgdm").toString();
		// 涉税申请列表查询
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" SELECT P.SQZL_MC, Z.XS_MC, TO_CHAR(T.SQ_SJ, 'YYYY-MM-DD') SQ_SJ ");
		sqlBuff.append(" FROM DB_WSBS.T_WB_WSFWQKB T,DB_WSBS.T_DM_WB_SSSQZT Z, ");
		sqlBuff.append(" (SELECT DISTINCT SQZL_DM, URL, MURL, SQZL_MC FROM DB_WSBS.T_DM_WB_SQZLDMB ");
		sqlBuff.append(" WHERE SQZL_DM IS NOT NULL AND XY_BJ = '1') P ");
		sqlBuff.append(" WHERE T.SQLX_DM = P.SQZL_DM AND T.ZT != '11' ");
		sqlBuff.append(" AND T.SWGLM = ? AND T.ZT = Z.ZT_DM ");
		sqlBuff.append(" AND T.SQ_SJ >= TRUNC(SYSDATE, 'MM') AND T.SQ_SJ <= TRUNC(LAST_DAY(SYSDATE), 'DD') ");
		sqlBuff.append(" ORDER BY T.SQ_SJ DESC ");
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(swglm);
		CachedRowSet rs = QueryCssBPO.findAll(con, sqlBuff.toString(),
				sqlParams);
		Map<String, Object> sqMap;
		List<Map<String, Object>> sqList = new ArrayList<Map<String, Object>>();
		while (rs.next()) {
			sqMap = new HashMap<String, Object>();
			sqMap.put("sqzl_mc", rs.getString("SQZL_MC"));
			sqMap.put("zt_mc", rs.getString("XS_MC"));
			sqMap.put("sq_sj", rs.getString("SQ_SJ"));
			sqList.add(sqMap);
		}
		responseEvent.respMapParam.put("sssqList", sqList);
		// 网报未申报列表查询
		String sql = " select distinct b.zsxm_dm zsxmDm,b.zspm_dm zspmDm,b.ssqq ssqs,b.ssqz,b.JDLY_DM  from DB_WSBS.t_wb_cbsjxx b    where b.swglm = ?   and b.gljg_dm like ? and b.lr_sj >sysdate-31";
		sqlParams = new ArrayList<Object>();
		sqlParams.add(swglm);
		sqlParams.add(gljgdm.substring(0, 5) + "%");
		rs = QueryCssBPO.findAll(con, sql, sqlParams);
		ArrayList<Map<String, Object>> WBwsbList = new ArrayList<Map<String, Object>>();
		Map<String, Object> WBwsbMap;
		while (rs.next()) {
			WBwsbMap = new HashMap<String, Object>();
			WBwsbMap.put("zsxmDm", rs.getString("zsxmDm"));
			WBwsbMap.put("zspmDm", rs.getString("zspmDm"));
			WBwsbMap.put("ssqs", rs.getString("ssqs"));
			WBwsbMap.put("ssqz", rs.getString("ssqz"));
			WBwsbMap.put("jdly", rs.getString("JDLY_DM"));
			WBwsbList.add(WBwsbMap);
		}
		// 已申报列表查询
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT P.PZZL_MC,TO_CHAR(T.LR_SJ, 'YYYY-MM-DD HH24:MI:SS') LR_SJ, ");
		sb.append(" T.YBTSEHDJ_JE,Z.XS_MC FROM DB_WSBS.T_WB_SBBQK T, DB_WSBS.T_DM_WB_PZDM P, DB_WSBS.T_DM_WB_ZT Z ");
		sb.append(" WHERE T.PZZL_DM = P.PZZL_DM AND T.ZT = Z.ZT_DM AND T.SWGLM = ? ");
		sb.append(" AND T.TB_RQ >= TRUNC(SYSDATE, 'MM') AND T.TB_RQ <= TRUNC(LAST_DAY(SYSDATE), 'DD') ");
		sqlParams.clear();
		sqlParams.add(swglm);
		rs = QueryCssBPO.findAll(con, sb.toString(), sqlParams);
		ArrayList<Map<String, Object>> ysbList = new ArrayList<Map<String, Object>>();
		Map<String, Object> ysbMap;
		while (rs.next()) {
			ysbMap = new HashMap<String, Object>();
			ysbMap.put("sbzl", rs.getString("PZZL_MC"));
			ysbMap.put("sbsj", rs.getString("LR_SJ"));
			ysbMap.put("sbje", rs.getString("YBTSEHDJ_JE"));
			ysbMap.put("sbzt", rs.getString("XS_MC"));
			ysbList.add(ysbMap);
		}
		responseEvent.respMapParam.put("ysbList", ysbList);

		// 调用ESB
		ESBRequestEvent esbRequestEvent = new ESBRequestEvent("ZBfwBLH", "", "");
		esbRequestEvent.setDealMethod("queryWsbList");
		esbRequestEvent.setReqMapParam(new HashMap());
		esbRequestEvent.reqMapParam.put("swglm", swglm);
		esbRequestEvent.reqMapParam.put("gljgdm", gljgdm);
		try {
			ResponseEvent esbresponseEvent = ZBCoreHelper.getEsbResponseEvent(
					esbRequestEvent, con);
			ArrayList<Map<String, Object>> ZGwsbList = (ArrayList<Map<String, Object>>) esbresponseEvent.respMapParam
					.get("currentList");
			ArrayList<Map<String, Object>> wsbList = new ArrayList<Map<String, Object>>();// 未申报集合
			Map<String, Object> mapZG = null;
			Map<String, Object> mapWB = null;
			boolean flag;
			for (int i = 0; i < ZGwsbList.size(); i++) {
				mapZG = (Map) ZGwsbList.get(i);
				flag = true;
				for (int j = 0; j < WBwsbList.size(); j++) {
					mapWB = (Map) WBwsbList.get(j);
					if (mapZG.get("zsxmDm").equals(mapWB.get("zsxmDm"))
							&& mapZG.get("zspmDm").equals(mapWB.get("zspmDm"))
							&& mapZG.get("ssqs").equals(mapWB.get("ssqs"))
							&& mapZG.get("ssqz").equals(mapWB.get("ssqz"))
							&& mapZG.get("jdly").equals(mapWB.get("jdly"))) {
						flag = false;
					}
				}
				if (flag == true) {
					wsbList.add(mapZG);
				}
			}
			responseEvent.respMapParam.put("wsbList", wsbList);
		} catch (Exception e) {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					ZBCoreHelper.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
					e.getMessage()));
			return responseEvent;
		}
		return responseEvent;
	}

	protected ResponseEvent querySbList(RequestEvent req, Connection con)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String swglm = dataMap.get("swglm").toString();
		String gljgdm = dataMap.get("gljgdm").toString();
		String sql = " select distinct b.zsxm_dm zsxmDm,b.zspm_dm zspmDm,b.ssqq ssqs,b.ssqz,b.JDLY_DM  from DB_WSBS.t_wb_cbsjxx b    where b.swglm = ?   and b.gljg_dm like ? and b.lr_sj >sysdate-31";
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(swglm);
		sqlParams.add(gljgdm.substring(0, 5) + "%");
		CachedRowSet rs = QueryCssBPO.findAll(con, sql, sqlParams);
		ArrayList<Map<String, Object>> WBwsbList = new ArrayList<Map<String, Object>>();
		Map<String, Object> WBwsbMap;
		while (rs.next()) {
			WBwsbMap = new HashMap<String, Object>();
			WBwsbMap.put("zsxmDm", rs.getString("zsxmDm"));
			WBwsbMap.put("zspmDm", rs.getString("zspmDm"));
			WBwsbMap.put("ssqs", rs.getString("ssqs"));
			WBwsbMap.put("ssqz", rs.getString("ssqz"));
			WBwsbMap.put("jdly", rs.getString("JDLY_DM"));
			WBwsbList.add(WBwsbMap);
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT P.PZZL_MC,TO_CHAR(T.LR_SJ, 'YYYY-MM-DD HH24:MI:SS') LR_SJ, ");
		sb.append(" T.YBTSEHDJ_JE,Z.XS_MC FROM DB_WSBS.T_WB_SBBQK T, DB_WSBS.T_DM_WB_PZDM P, DB_WSBS.T_DM_WB_ZT Z ");
		sb.append(" WHERE T.PZZL_DM = P.PZZL_DM AND T.ZT = Z.ZT_DM AND T.SWGLM = ? ");
		sb.append(" AND T.TB_RQ >= TRUNC(SYSDATE, 'MM') AND T.TB_RQ <= TRUNC(LAST_DAY(SYSDATE), 'DD') ");
		sqlParams.clear();
		sqlParams.add(swglm);
		rs = QueryCssBPO.findAll(con, sb.toString(), sqlParams);
		ArrayList<Map<String, Object>> ysbList = new ArrayList<Map<String, Object>>();
		Map<String, Object> ysbMap;
		while (rs.next()) {
			ysbMap = new HashMap<String, Object>();
			ysbMap.put("sbzl", rs.getString("PZZL_MC"));
			ysbMap.put("sbsj", rs.getString("LR_SJ"));
			ysbMap.put("sbje", rs.getString("YBTSEHDJ_JE"));
			ysbMap.put("sbzt", rs.getString("XS_MC"));
			ysbList.add(ysbMap);
		}
		responseEvent.respMapParam.put("ysbList", ysbList);

		// 调用ESB
		ESBRequestEvent esbRequestEvent = new ESBRequestEvent("ZBfwBLH", "", "");
		esbRequestEvent.setDealMethod("queryWsbList");
		esbRequestEvent.setReqMapParam(new HashMap());
		esbRequestEvent.reqMapParam.put("swglm", swglm);
		esbRequestEvent.reqMapParam.put("gljgdm", gljgdm);
		try {
			ResponseEvent esbresponseEvent = ZBCoreHelper.getEsbResponseEvent(
					esbRequestEvent, con);
			ArrayList<Map<String, Object>> ZGwsbList = (ArrayList<Map<String, Object>>) esbresponseEvent.respMapParam
					.get("currentList");
			ArrayList<Map<String, Object>> wsbList = new ArrayList<Map<String, Object>>();// 未申报集合
			Map<String, Object> mapZG = null;
			Map<String, Object> mapWB = null;
			boolean flag;
			for (int i = 0; i < ZGwsbList.size(); i++) {
				mapZG = (Map) ZGwsbList.get(i);
				flag = true;
				for (int j = 0; j < WBwsbList.size(); j++) {
					mapWB = (Map) WBwsbList.get(j);
					if (mapZG.get("zsxmDm").equals(mapWB.get("zsxmDm"))
							&& mapZG.get("zspmDm").equals(mapWB.get("zspmDm"))
							&& mapZG.get("ssqs").equals(mapWB.get("ssqs"))
							&& mapZG.get("ssqz").equals(mapWB.get("ssqz"))
							&& mapZG.get("jdly").equals(mapWB.get("jdly"))) {
						flag = false;
					}
				}
				if (flag == true) {
					wsbList.add(mapZG);
				}
			}
			responseEvent.respMapParam.put("wsbList", wsbList);
		} catch (Exception e) {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					ZBCoreHelper.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
					e.getMessage()));
			return responseEvent;
		}
		return responseEvent;
	}

	protected ResponseEvent querySssq(RequestEvent req, Connection con)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT P.SQZL_MC, Z.XS_MC, TO_CHAR(T.SQ_SJ, 'YYYY-MM-DD') SQ_SJ ");
		sql.append(" FROM DB_WSBS.T_WB_WSFWQKB T,DB_WSBS.T_DM_WB_SSSQZT Z, ");
		sql.append(" (SELECT DISTINCT SQZL_DM, URL, MURL, SQZL_MC FROM DB_WSBS.T_DM_WB_SQZLDMB ");
		sql.append(" WHERE SQZL_DM IS NOT NULL AND XY_BJ = '1') P ");
		sql.append(" WHERE T.SQLX_DM = P.SQZL_DM AND T.ZT != '11' ");
		sql.append(" AND T.SWGLM = ? AND T.ZT = Z.ZT_DM ");
		sql.append(" AND T.SQ_SJ >= TRUNC(SYSDATE, 'MM') AND T.SQ_SJ <= TRUNC(LAST_DAY(SYSDATE), 'DD') ");
		sql.append(" ORDER BY T.SQ_SJ DESC ");
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(dataMap.get("swglm"));
		CachedRowSet rs = QueryCssBPO.findAll(con, sql.toString(), sqlParams);
		Map<String, Object> sqMap;
		List<Map<String, Object>> sqList = new ArrayList<Map<String, Object>>();
		while (rs.next()) {
			sqMap = new HashMap<String, Object>();
			sqMap.put("sqzl_mc", rs.getString("SQZL_MC"));
			sqMap.put("zt_mc", rs.getString("XS_MC"));
			sqMap.put("sq_sj", rs.getString("SQ_SJ"));
			sqList.add(sqMap);
		}
		responseEvent.respMapParam.put("sssqList", sqList);
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	protected ResponseEvent queryKey(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT IV,KEY  ");
		sql.append(" FROM T_ZB_KEY A ");
		sql.append(" WHERE A.XY_BJ = '1' AND YXYF=TO_CHAR(SYSDATE,'YYYYMM')");
		CachedRowSet rs = QueryCssBPO.findAll(con, sql.toString(), null);
		Map<String, String> map;
		if (rs.next()) {
			responseEvent.respMapParam.put("IV", rs.getString("IV"));
			responseEvent.respMapParam.put("KEY", rs.getString("KEY"));
		}
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	protected ResponseEvent querySlyj(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String lsh = (String) dataMap.get("lsh");
		String lcslh = (String) dataMap.get("lcslh");
		String bbzt = (String) dataMap.get("bbzt");
		String sql = null;
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(lsh);
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT COUNT(*) AS N FROM DB_WSBS.T_WB_WSFWQKB WHERE LSH IN (?)",
						sqlParams);
		rs.next();
		if (0 == rs.getInt("N")) {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB0022);
			responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(con,
					GeneralCons.ERROR_CODE_ZB0022));
		} else {
			List<Map<String, String>> slyjList = new ArrayList<Map<String, String>>();
			Map<String, String> slyjMap;
			if ("6".equals(bbzt) || "10".equals(bbzt)) {
				sql = "SELECT T.SLSWJGDM,T.SLSWJGMC,T.SLSWRYMC,T.SLSWRYDM,TO_CHAR(T.TH_SJ, 'YYYY-MM-DD hh24:Mi:ss') AS THSJ,T.YJ FROM DB_WSBS.T_WB_WSFWQKB T WHERE T.LSH IN (?) ORDER BY T.TH_SJ ASC";
				sqlParams = new ArrayList<Object>();
				sqlParams.add(lsh);
				rs = QueryCssBPO.findAll(con, sql, sqlParams);
				while (rs.next()) {
					slyjMap = new HashMap<String, String>();
					slyjMap.put("blrymc", rs.getString("SLSWRYMC"));
					slyjMap.put("blswjgmc", rs.getString("SLSWJGMC"));
					slyjMap.put("lrsj", rs.getString("THSJ"));
					slyjMap.put("shspyj", rs.getString("YJ"));
					slyjMap.put("wfhdmc", "初审");
					slyjList.add(slyjMap);
				}
			} else {
				sqlParams = new ArrayList<Object>();
				if ("".equals(lcslh)) {
					sql = "SELECT T.HJMC,T.SWRY_MC,T.SWJG_MC,T.SPYJ,TO_CHAR(T.LR_SJ,'YYYY-MM-DD hh24:Mi:ss') AS LR_SJ FROM DB_WSBS.T_WSFW_SQSPYJB T WHERE T.LSH IN (?) ORDER BY T.LR_SJ ASC";
					sqlParams.add(lsh);
				} else {
					sql = "SELECT T.HJMC,T.SWRY_MC,T.SWJG_MC,T.SPYJ,TO_CHAR(T.LR_SJ,'YYYY-MM-DD hh24:Mi:ss') AS LR_SJ FROM DB_WSBS.T_WSFW_SQSPYJB T WHERE T.LSH IN(?,?) ORDER BY T.LR_SJ ASC";
					sqlParams.add(lsh);
					sqlParams.add(lcslh);
				}
				rs = QueryCssBPO.findAll(con, sql, sqlParams);
				while (rs.next()) {
					slyjMap = new HashMap<String, String>();
					slyjMap.put("blrymc", rs.getString("SWRY_MC")); // 办理人员名称
					slyjMap.put("blswjgmc", rs.getString("SWJG_MC"));// 办理税务机关员名称
					slyjMap.put("lrsj", rs.getString("LR_SJ"));// 录入时间
					slyjMap.put("shspyj", rs.getString("SPYJ"));// 审批依据
					slyjMap.put("wfhdmc", rs.getString("HJMC"));// 环节名称
					slyjList.add(slyjMap);
				}
			}
			responseEvent.respMapParam.put("slyjList", slyjList);
			responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
			responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		}
		return responseEvent;
	}

	protected ResponseEvent queryYwsxByXl(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(dataMap.get("SSSQXL_DM"));
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT DISTINCT A.YWSX_DM, A.YWSX_MC, S.SPFS_DM SP_BJ ");
		sql.append(" FROM DB_WSBS.T_DM_WB_SSSQXM A, DB_WSBS.T_DM_SXGL_YWSXXM S ");
		sql.append(" WHERE A.YWSX_DM = S.YWSX_DM ");
		sql.append(" AND S.YXBZ = '1' ");
		sql.append(" AND A.YWXL_DM = ? ");
		sql.append(" ORDER BY A.YWSX_DM ");
		CachedRowSet rs = QueryCssBPO.findAll(con, sql.toString(), paramList);
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("YWSX_DM", rs.getString("YWSX_DM"));
			map.put("YWSX_MC", rs.getString("YWSX_MC"));
			map.put("SP_BJ", rs.getString("SP_BJ"));
			dataList.add(map);
		}
		responseEvent.respMapParam.put("dataList", dataList);
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	protected ResponseEvent queryZcyj(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(dataMap.get("YWSX_DM"));
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT DISTINCT T.YWSX_DM, T.SXXM, T.WJMC ");
		sql.append(" FROM DB_WSBS.T_DM_SXGL_YWSXXM T, DB_WSBS.T_DM_SXGL_ZJDMDZB T1 ");
		sql.append(" WHERE T.YWSX_DM = T1.YWSX_DM ");
		sql.append(" AND T.SXXM = T1.SXXM ");
		sql.append(" AND T.YXBZ = '1' ");
		sql.append(" AND T.YWSX_DM = ? ");
		sql.append(" ORDER BY YWSX_DM, SXXM ASC ");
		CachedRowSet rs = QueryCssBPO.findAll(con, sql.toString(), paramList);
		List<Map<String, String>> zcyjList = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("YWSX_DM", rs.getString("YWSX_DM"));
			map.put("SXXM", rs.getString("SXXM"));
			map.put("WJMC", rs.getString("WJMC"));
			zcyjList.add(map);
		}
		responseEvent.respMapParam.put("zcyjList", zcyjList);
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	protected ResponseEvent queryFbzl(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(dataMap.get("XMDM"));
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT * FROM DB_WSBS.T_WSFW_FSZL X WHERE X.XMDM=? ORDER BY X.FBZL_DM",
						paramList);
		List<Map<String, String>> fbzlList = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("FBZL_DM", rs.getString("FBZL_DM"));
			map.put("MC", rs.getString("FBZL_MC"));
			fbzlList.add(map);
		}
		responseEvent.respMapParam.put("fbzlList", fbzlList);
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	//
	protected ResponseEvent search(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String swglm = (String) dataMap.get("swglm");
		String sqlxdm = (String) dataMap.get("sqlxdm");
		List<Map<String, String>> ZtList = new ArrayList<Map<String, String>>();
		Map map = null;
//		String sql = "SELECT B.ZT,TO_CHAR(B.SQ_SJ,'YYYY-MM-DD') SQ_SJ,B.LSH,B.LCSLH FROM DB_WSBS.T_WB_WSFWQKB B WHERE B.ZT !='0' and  B.SQLX_DM=? AND B.SWGLM=? AND B.LR_SJ > ADD_MONTHS(SYSDATE, -12) ORDER BY LR_SJ DESC";
		String sql = "SELECT B.ZT, S.XS_MC, TO_CHAR(B.SQ_SJ, 'YYYY-MM-DD') SQ_SJ, B.LSH, B.LCSLH FROM DB_WSBS.T_WB_WSFWQKB B, DB_WSBS.T_DM_WB_SSSQZT S WHERE B.ZT != '0' AND B.SQLX_DM = ? AND B.SWGLM = ? AND B.ZT = S.ZT_DM AND B.LR_SJ > ADD_MONTHS(SYSDATE, -12) ORDER BY B.LR_SJ DESC";
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(sqlxdm);
		paramList.add(Long.parseLong(swglm));
		CachedRowSet rs = QueryCssBPO.findAll(con, sql, paramList);
		while (rs.next()) {
			map = new HashMap();
			String t = rs.getString("zt");
//			if ("0".equals(t)) {
//				map.put("zt", "已暂存");
//			} else if ("1".equals(t)) {
//				map.put("zt", "已提交");
//			} else if ("2".equals(t)) {
//				map.put("zt", "已迁移");
//			} else if ("3".equals(t)) {
//				map.put("zt", "审核通过");
//			} else if ("4".equals(t)) {
//				map.put("zt", "审核不通过");
//			} else if ("5".equals(t)) {
//				map.put("zt", "审核中");
//			} else if ("6".equals(t)) {
//				map.put("zt", "已退回");
//			} else if ("7".equals(t)) {
//				map.put("zt", "已退回");
//			} else if ("8".equals(t)) {
//				map.put("zt", "迁移中");
//			} else if ("9".equals(t)) {
//				map.put("zt", "审核中");
//			} else if ("10".equals(t)) {
//				map.put("zt", "审核不通过");
//			} else if ("11".equals(t)) {
//				map.put("zt", "已归档");
//			} else {
//				map.put("zt", "状态错误");
//			}
			map.put("zt", rs.getString("XS_MC"));
			map.put("sqsj", rs.getString("sq_sj"));
			map.put("bbzt", t);
			map.put("lcslh", StringUtil.empty(rs.getString("LCSLH")));
			map.put("lsh", rs.getString("lsh"));
			ZtList.add(map);
		}
		ResponseEvent responseEvent = new ResponseEvent();
		responseEvent.respMapParam.put("ZtList", ZtList);
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
