package gov.jslt.taxcore.taxblh.ggfw.sqhd.sq005;

import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxcore.taxbpo.comm.WBwsfwqkbBPO;
import gov.jslt.taxcore.taxbpo.ggfw.sqhd.sq005.YyfwgdBPO;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.StringUtil;
import gov.jslt.taxevent.comm.WBwsfwqkbVO;
import gov.jslt.taxevent.ggfw.sqhd.sq005.YyfwgdVO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.bpo.QueryCssBPO;
import com.ctp.core.bpo.StoredProcManager;
import com.ctp.core.bpo.StoredProcParamObj;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.core.utility.dbtime.DBTimeServer;
import com.f1j.ss.report.rs;

public class Sqhd005BLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		String dealMethod = req.getDealMethod();
		if ("queryWxlist".equals(dealMethod)) {
			return queryWxlist(req, con);
		} else if ("initForm".equals(dealMethod)) {
			return initForm(req, con);
		} else if ("saveData".equals(dealMethod)) {
			return saveData(req, con);
		} else if ("queryData".equals(dealMethod)) {
			return queryData(req, con);
		} else if ("queryXQ".equals(dealMethod)) {
			return queryXQ(req, con);
		} else if ("queryFWT".equals(dealMethod)) {
			return queryFWT(req, con);
		} else if ("queryZrrList".equals(dealMethod)) {
			return queryZrrList(req, con);
		} else if ("queryYyList".equals(dealMethod)) {
			return queryYyList(req, con);
		}
		return null;
	}

	/**
	 * 查询已预约列表
	 * 
	 * @param req
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	private ResponseEvent queryYyList(RequestEvent req, Connection con)
			throws SQLException {
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String sqlxdm = (String) dataMap.get("sqlxdm");
		String lxdh = (String) dataMap.get("lxdh");
		String lxr = (String) dataMap.get("lxr");
		List<Map<String, String>> ZtList = new ArrayList<Map<String, String>>();
		Map map = null;
		String sql = "SELECT B.ZT, S.XS_MC, TO_CHAR(B.SQ_SJ, 'YYYY-MM-DD') SQ_SJ, B.LSH, B.LCSLH FROM DB_WSBS.T_WB_WSFWQKB B, DB_WSBS.T_DM_WB_SSSQZT S WHERE B.ZT IN ('3', '4', '12') AND B.SQLX_DM = ? AND B.LXDH = ? AND B.LXR = ? AND B.ZT = S.ZT_DM AND B.LR_SJ > SYSDATE - 7 ORDER BY B.LR_SJ DESC";
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(sqlxdm);
		paramList.add(lxdh);
		paramList.add(lxr);
		CachedRowSet rs = QueryCssBPO.findAll(con, sql, paramList);
		while (rs.next()) {
			map = new HashMap();
			String t = rs.getString("zt");
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

	/**
	 * 查询预约服务信息
	 * 
	 * @param req
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	private ResponseEvent queryData(RequestEvent req, Connection con)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT T.LSH,T.YYRMC,T.LXDH,T.DWMC,T.ZCDZ,T.NSSBH,T.YYDS,DS.MC_J YYDS_MC,T.YYQX,XQ.MC_J YYQX_MC, ");
		sql.append(" T.YYMC,FWT.MC FWT_MC,T.YYSXDM,TO_CHAR(T.YYRQ, 'yyyy-MM-dd') YYRQ,SJ.FWSJ,T.YWMS,T.BZ ");
		sql.append(" FROM DB_WSBS.T_WB_YYFWSQB   T,DB_WSBS.T_DM_GY_XZQHDS DS,DB_WSBS.T_DM_GY_XZQHXQ XQ, ");
		sql.append(" DB_WSBS.T_DM_GY_YYFWDT FWT,DB_WSBS.T_DM_GY_YYBSSJ SJ ");
		sql.append(" WHERE T.LSH = ? AND T.YYDS = DS.XZQHDS_DM(+) ");
		sql.append(" AND T.YYQX = XQ.XZQHXQ_DM(+) AND T.YYMC = FWT.FWT_DM(+) AND T.YYSJ = SJ.CXDM ");
		sqlParams.add(dataMap.get("lsh"));
		CachedRowSet rs = QueryCssBPO.findAll(con, sql.toString(), sqlParams);
		String sxdm = null;
		if (rs.next()) {
			responseEvent.respMapParam.put("LSH", rs.getString("LSH")); // 预约流水号
			responseEvent.respMapParam.put("YYRMC", rs.getString("YYRMC"));// 预约人姓名
			responseEvent.respMapParam.put("LXDH", rs.getString("LXDH"));// 手机号码
			responseEvent.respMapParam.put("DWMC", rs.getString("DWMC"));// 单位名称
			responseEvent.respMapParam.put("ZCDZ", rs.getString("ZCDZ"));// 注册地址
			responseEvent.respMapParam.put("NSSBH", rs.getString("NSSBH"));// 纳税人识别号
			responseEvent.respMapParam.put("YYDS", rs.getString("YYDS"));// 预约版税服务厅（所在地市代码）
			responseEvent.respMapParam.put("YYDS_MC", rs.getString("YYDS_MC"));// 预约版税服务厅（所在地市名称）
			responseEvent.respMapParam.put("YYQX", rs.getString("YYQX"));// 预约版税服务厅（所在区县代码）
			responseEvent.respMapParam.put("YYQX_MC", rs.getString("YYQX_MC"));// 预约版税服务厅（所在区县名称）
			responseEvent.respMapParam.put("YYMC", rs.getString("YYMC"));// 预约版税服务厅代码
			responseEvent.respMapParam.put("FWT_MC", rs.getString("FWT_MC"));// 预约版税服务厅名称
			sxdm = rs.getString("YYSXDM");
			responseEvent.respMapParam.put("YYSXDM", sxdm);// 预约事项类型代码
			responseEvent.respMapParam.put("YYRQ", rs.getString("YYRQ"));// 预约日期
			responseEvent.respMapParam.put("FWSJ", rs.getString("FWSJ"));// 预约时间
			responseEvent.respMapParam.put("YWMS", rs.getString("YWMS"));// 具体业务描述
			responseEvent.respMapParam.put("BZ", rs.getString("BZ"));// 备注信息
		}
		responseEvent.respMapParam.put("sxlx", ZBCoreHelper.findYysxlx(con));// 预约事项类型列表
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	private ResponseEvent saveData(RequestEvent req, Connection con)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		String lsh = ZBCoreHelper.getGUID(con);
		// 预约服务工单
		YyfwgdVO vo = new YyfwgdVO();
		vo.setLsh(lsh);
		vo.setLyqd("04");
		vo.setYyrmc(StringUtil.empty(dataMap.get("yyrmc")));// 预约人姓名
		vo.setLxdh(StringUtil.empty(dataMap.get("sjhm")));// 预约人姓名
		vo.setDwmc(StringUtil.empty(dataMap.get("dwmc")));// 单位名称
		vo.setZcdz(StringUtil.empty(dataMap.get("zcdz")));// 注册地址
		vo.setNssbh(StringUtil.empty(dataMap.get("nsrsbh")));// 纳税人识别号
		vo.setYyds(StringUtil.empty(dataMap.get("yyds")));// 预约办税服务厅（所在地市）
		vo.setYyqx(StringUtil.empty(dataMap.get("yyqx")));// 预约办税服务厅（所在区县）
		vo.setYymc(StringUtil.empty(dataMap.get("yyfwt")));// 预约办税服务厅
		vo.setYysxdm(StringUtil.empty(dataMap.get("yysxdm")));// 预约事项代码
		vo.setStr_yyrq(StringUtil.empty(dataMap.get("yyrq")));// 预约日期
		vo.setYysj(StringUtil.empty(dataMap.get("yysj")));// 预约时间
		vo.setYwms(StringUtil.empty(dataMap.get("ywms")));// 业务描述
		vo.setBz(StringUtil.empty(dataMap.get("bzxx")));// 备注信息
		YyfwgdBPO.insert(con, vo);
		// 网上服务情况表
		WBwsfwqkbVO qkvo = new WBwsfwqkbVO();
		qkvo.setLsh(lsh);
		qkvo.setSqlxdm("SQ096");
		qkvo.setSsxmdm("01071002");
		qkvo.setSssqdl("0108");
		qkvo.setSssqxl("1002");
		qkvo.setSwglm(StringUtil.empty(dataMap.get("swglm")));// 税务管理码
		qkvo.setZt("12");
		qkvo.setSslx("0");
		qkvo.setCarz("0");
		qkvo.setLxdh(jsonReqData.getSjHm());// 注册号码
		qkvo.setLxr(jsonReqData.getXm());// 注册姓名
		qkvo.setGljgdm(StringUtil.empty(dataMap.get("gljgdm")));// 管理机关代码
		qkvo.setSqsj(DBTimeServer.getDBTime(con));
		WBwsfwqkbBPO.insert(con, qkvo);

		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	/**
	 * 初始化预约信息
	 * 
	 * @param req
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	private ResponseEvent initForm(RequestEvent req, Connection con)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		String lsh = StringUtil.empty(dataMap.get("lsh"));
		sqlParams.add(dataMap.get("swglm"));
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT T.NSRSBM, T.NSR_MC, T.ZCDZ, T.GLJG_DM FROM DB_WSBS.T_DJ_JGNSR T WHERE T.SWGLM = ?",
						sqlParams);

		if (rs.next()) {
			responseEvent.respMapParam.put("NSRSBM", rs.getString("NSRSBM"));// 纳税人识别号
			responseEvent.respMapParam.put("NSR_MC", rs.getString("NSR_MC"));// 单位名称
			responseEvent.respMapParam.put("ZCDZ", rs.getString("ZCDZ"));// 注册地址
			responseEvent.respMapParam.put("szds", rs.getString("GLJG_DM")// 所在地市
					.substring(1, 5));
		}
		responseEvent.respMapParam.put("szdsList",
				ZBCoreHelper.findDJDLWZS(con));// 所在地市列表
		responseEvent.respMapParam
				.put("bssjList", ZBCoreHelper.findYybssj(con));// 预约办税时间列表
		responseEvent.respMapParam
				.put("sxlxList", ZBCoreHelper.findYysxlx(con));// 预约事项类型列表

		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	protected ResponseEvent queryWxlist(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT WX.WX_UID, WX.WX_MC, S.MC_J,S.SWJG_DM ");
		sb.append(" FROM T_CS_ZB_WX WX, DB_WSBS.T_DM_GY_SWJG S ");
		sb.append(" WHERE WX.XY_BJ = '1' ");
		sb.append(" AND WX.SWJG_DM = S.SWJG_DM ");
		sb.append(" AND WX.WX_UID IS NOT NULL ");
		sb.append(" AND WX.WX_MC IS NOT NULL ");
		CachedRowSet rs = QueryCssBPO.findAll(con, sb.toString(), null);
		List<Map<String, String>> wxList = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("swjgdm", rs.getString("SWJG_DM").substring(0, 5));
			map.put("wxuid", rs.getString("WX_UID"));
			map.put("wxmc", rs.getString("WX_MC"));
			map.put("swjgmc", rs.getString("MC_J"));
			wxList.add(map);
		}
		responseEvent.respMapParam.put("wxList", wxList);
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
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
	 * 根据县区代码获取版税服务厅列表
	 * 
	 * @param req
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	private ResponseEvent queryFWT(RequestEvent req, Connection con)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String xzqhxq_dm = dataMap.get("xzqhxq_dm").toString();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(xzqhxq_dm);
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT T.FWT_DM, T.MC FROM DB_WSBS.T_DM_GY_YYFWDT T WHERE T.XY_BJ = '1' AND t.fwt_dm LIKE '%' || ? || '%' ORDER BY T.FWT_DM",
						sqlParams);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("FWT_DM", rs.getString("FWT_DM"));
			map.put("MC", rs.getString("MC"));
			list.add(map);
		}
		responseEvent.respMapParam.put("xqList", list);

		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	/**
	 * 获取自然人登记信息
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

	@Override
	protected ResponseEvent validateData(RequestEvent req, Connection con)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
