package gov.jslt.taxcore.taxblh.bsfw.ssbl.ssbl004;

import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxcore.taxbpo.bsfw.ssbl.ssbl004.WBpttsxxmxBPO;
import gov.jslt.taxcore.taxbpo.bsfw.ssbl.ssbl004.WBpttsxxzbBPO;
import gov.jslt.taxevent.bsfw.ssbl.ssbl004.WBpttsxxmxVO;
import gov.jslt.taxevent.bsfw.ssbl.ssbl004.WBpttsxxzbVO;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;

import java.sql.Connection;
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
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.core.utility.dbtime.DBTimeServer;

public class Ssbl004BLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		String dealMethod = req.getDealMethod();
		if ("queryData".equals(dealMethod)) {// 纳税人推送信息查询(未处理数据)
			return queryData(req, con);
		} else if ("queryTsList".equals(dealMethod)) {
			return queryTsList(req, con);// 查询推送列表
		} else if ("queryTsxxmx".equals(dealMethod)) {
			return queryTsxxmx(req, con);// 纳税人推送数据查询（已提交）
		} else if ("saveData".equals(dealMethod)) {
			return saveData(req, con);// 提交推送信息
		} else if ("queryFbzl".equals(dealMethod)) {
			return queryFbzl(req, con);// 查询附报资料
		}
		return null;
	}

	protected ResponseEvent queryFbzl(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add("01019004");
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

	protected ResponseEvent queryTsxxmx(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		List<Map<String, Map<String, String>>> jgnsrList = new ArrayList<Map<String, Map<String, String>>>();// 机关纳税人基础信息
		List<Map<String, Map<String, String>>> fddbrList = new ArrayList<Map<String, Map<String, String>>>();// 法定代表人信息
		List<Map<String, Map<String, String>>> cwfzrList = new ArrayList<Map<String, Map<String, String>>>();// 财务负责人信息
		List<Map<String, Map<String, String>>> bsryList = new ArrayList<Map<String, Map<String, String>>>();// 办税人员信息
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(dataMap.get("lsh"));
		String sql = "SELECT COUNT(1) AS RESULTCOUNT FROM DB_WSBS.T_XT_TSPT_TSXXMXB X WHERE X.LSH = ? AND X.SJX_YNR IS NOT NULL";
		CachedRowSet rs = QueryCssBPO.findAll(con, sql, paramList);
		Map<String, Map<String, String>> resultMap;
		Map<String, String> map;
		rs.next();
		if (0 < rs.getInt("RESULTCOUNT")) {// 存在明细数据
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT X.NRXG_BJ, X.SJX_YNR, X.SJX_XNR, T.COLNAME,T.TSSJX_MC, T.TABNAME,T.BSXG_TJ ");
			sb.append(" FROM DB_WSBS.T_XT_TSPT_TSXXMXB X, DB_WSBS.T_XT_TSPT_TSSJXPZB T ");
			sb.append(" WHERE X.LSH = ? ");
			sb.append(" AND X.TSSJX_DM = T.TSSJX_DM ");
			rs = QueryCssBPO.findAll(con, sb.toString(), paramList);
			Map<String, Map<String, String>> jgnsrMap = new HashMap<String, Map<String, String>>();
			Map<String, Map<String, String>> frMap = new HashMap<String, Map<String, String>>();
			Map<String, Map<String, String>> cwrMap = new HashMap<String, Map<String, String>>();
			Map<String, Map<String, String>> bsrMap = new HashMap<String, Map<String, String>>();
			while (rs.next()) {
				map = new HashMap<String, String>();
				map.put("NRXG_BJ", rs.getString("NRXG_BJ"));// 有无变化（0：无变化；1：有变化）
				map.put("SJX_YNR", rs.getString("SJX_YNR"));// 数据项原内容
				map.put("SJX_XNR", rs.getString("SJX_XNR"));// 数据项新内容
				if ("T_DJ_JGNSR".equals(rs.getString("TABNAME"))) {
					jgnsrMap.put(rs.getString("COLNAME"), map);// 当前数据列名
				} else {// 人员情况信息
					if ("RYLX_DM=05".equals(rs.getString("BSXG_TJ"))) {
						frMap.put(rs.getString("COLNAME"), map);// 当前数据列名
					} else if ("RYLX_DM=06".equals(rs.getString("BSXG_TJ"))) {
						cwrMap.put(rs.getString("COLNAME"), map);// 当前数据列名
					} else {
						bsrMap.put(rs.getString("COLNAME"), map);// 当前数据列名
					}
				}
			}
			// 机关纳税人基础信息
			jgnsrList.add(jgnsrMap);
			fddbrList.add(frMap);
			cwfzrList.add(cwrMap);
			bsryList.add(bsrMap);
		} else {// 无明细数据
			sql = "SELECT SWGLM FROM DB_WSBS.T_XT_TSPT_TSXXZB X WHERE X.LSH = ?";
			paramList = new ArrayList<Object>();
			paramList.add(dataMap.get("lsh"));
			rs = QueryCssBPO.findAll(con, sql, paramList);
			rs.next();
			String swglm = rs.getString("SWGLM");
			// 纳税人基础信息SQL
			sql = "SELECT X.NSR_MC, X.ZCDZ, X.ZCDZYB, X.SJJYDZ, X.SJJYDZYB FROM DB_WSBS.T_DJ_JGNSR X WHERE X.SWGLM = ?";
			paramList = new ArrayList<Object>();
			paramList.add(swglm);
			rs = QueryCssBPO.findAll(con, sql, paramList);
			if (rs.next()) {
				resultMap = new HashMap<String, Map<String, String>>();
				map = new HashMap<String, String>();
				map.put("SJX_YNR", rs.getString("NSR_MC"));
				map.put("NRXG_BJ", "0");
				map.put("SJX_XNR", "");
				resultMap.put("NSR_MC", map);
				map = new HashMap<String, String>();
				map.put("SJX_YNR", rs.getString("ZCDZ"));
				map.put("NRXG_BJ", "0");
				map.put("SJX_XNR", "");
				resultMap.put("ZCDZ", map);
				map = new HashMap<String, String>();
				map.put("SJX_YNR", rs.getString("ZCDZYB"));
				map.put("NRXG_BJ", "0");
				map.put("SJX_XNR", "");
				resultMap.put("ZCDZYB", map);
				map = new HashMap<String, String>();
				map.put("SJX_YNR", rs.getString("SJJYDZ"));
				map.put("NRXG_BJ", "0");
				map.put("SJX_XNR", "");
				resultMap.put("SJJYDZ", map);
				map = new HashMap<String, String>();
				map.put("SJX_YNR", rs.getString("SJJYDZYB"));
				map.put("NRXG_BJ", "0");
				map.put("SJX_XNR", "");
				resultMap.put("SJJYDZYB", map);
				jgnsrList.add(resultMap);
			}
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT A.XM,A.GJDQ_DM,C.MC AS GJDQ_MC,A.ZJLX_DM,D.MC AS ZJLX_MC, ");
			sb.append(" A.ZJH,A.GDDH,A.YD_DH,A.RYLX_DM,B.MC AS RYLX_MC ");
			sb.append(" FROM DB_WSBS.T_DJ_RYQKB A,DB_WSBS.T_DM_DJ_RYLX B, ");
			sb.append(" DB_WSBS.T_DM_GY_GJ C,DB_WSBS.T_DM_DJ_SFZJLX D ");
			sb.append(" WHERE A.RYLX_DM = B.RYLX_DM ");
			sb.append(" AND A.GJDQ_DM = C.GJDQ_DM ");
			sb.append(" AND A.ZJLX_DM = D.SFZJLX_DM ");
			sb.append(" AND ROWNUM = 1 ");
			sb.append(" AND A.SWGLM = ? ");
			sb.append(" AND A.RYLX_DM = ? ");
			paramList = new ArrayList<Object>();
			paramList.add(swglm);
			paramList.add("05");// 法定代表人（负责人或业主）
			rs = QueryCssBPO.findAll(con, sb.toString(), paramList);
			resultMap = findRyqk2(rs);
			fddbrList.add(resultMap);
			paramList = new ArrayList<Object>();
			paramList.add(swglm);
			paramList.add("06");// 财务负责人
			rs = QueryCssBPO.findAll(con, sb.toString(), paramList);
			resultMap = findRyqk2(rs);
			cwfzrList.add(resultMap);
			paramList = new ArrayList<Object>();
			paramList.add(swglm);
			paramList.add("07");// 办税人员
			rs = QueryCssBPO.findAll(con, sb.toString(), paramList);
			resultMap = findRyqk2(rs);
			bsryList.add(resultMap);
		}
		responseEvent.respMapParam.put("jgnsrList", jgnsrList);
		List<Map<String, String>> zjlxList = getZjList(con);// 证件类型信息
		responseEvent.respMapParam.put("zjlxList", zjlxList);
		List<Map<String, String>> gjdqList = getGjdqList(con);// 国籍地区信息
		responseEvent.respMapParam.put("gjdqList", gjdqList);
		responseEvent.respMapParam.put("jgnsrList", jgnsrList);
		responseEvent.respMapParam.put("fddbrList", fddbrList);
		responseEvent.respMapParam.put("cwfzrList", cwfzrList);
		responseEvent.respMapParam.put("bsryList", bsryList);
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	protected ResponseEvent queryTsList(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(dataMap.get("swglm"));
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT T.LSH,T.TSQK_SM,T.TSTX_NR,T.TSQR_ZT,J.TSQR_MC,D.MC_J AS TS_JG, ");
		sb.append(" TO_CHAR(T.TS_RQ,'YYYY-MM-DD') AS TS_RQ, ");
		sb.append(" TO_CHAR(T.YXRQ_QS,'YYYY-MM-DD') AS YXRQ_QS, ");
		sb.append(" TO_CHAR(T.YXRQ_ZZ,'YYYY-MM-DD') AS YXRQ_ZZ ");
		sb.append(" FROM DB_WSBS.T_XT_TSPT_TSXXZB T,DB_ZSBS.T_DM_ZB_JCXXZT J,DB_WSBS.T_DM_GY_SWJG D ");
		sb.append(" WHERE T.SWGLM = ? ");
		sb.append(" AND T.TSQR_ZT = J.TSQR_DM ");
		sb.append(" AND T.TSJG_DM = D.SWJG_DM AND T.TSQR_ZT <>'9'  AND T.TS_LX='0'  AND T.YXRQ_QS<=TRUNC(SYSDATE) ORDER BY TSQR_ZT,T.YXRQ_ZZ");
		CachedRowSet rs = QueryCssBPO.findAll(con, sb.toString(), paramList);
		List<Map<String, String>> tsList = new ArrayList<Map<String, String>>();// 机关纳税人基础信息
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("lsh", rs.getString("LSH"));// 流水号
			map.put("tsqksm", rs.getString("TSQK_SM"));// 推送情况声明
			map.put("tstxnr", rs.getString("TSTX_NR"));// 推送提醒内容
			map.put("ts_jg", rs.getString("TS_JG"));// 推送提醒内容
			map.put("tsqrzt", rs.getString("TSQR_ZT"));// 推送确认状态代码
			map.put("tsqrmc", rs.getString("TSQR_MC"));// 推送确认状态名称
			map.put("tsrq", rs.getString("TS_RQ"));// 推送日期
			map.put("yxrqqs", rs.getString("YXRQ_QS"));// 有效日期起
			map.put("yxrqzz", rs.getString("YXRQ_ZZ"));// 有效日期止
			tsList.add(map);
		}
		responseEvent.respMapParam.put("tsList", tsList);
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	protected ResponseEvent saveData(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String lsh;
		if (null != dataMap.get("lsh")
				&& !"null".equals(dataMap.get("lsh").toString())) {
			WBpttsxxmxBPO.deleteByPK(con, (String) dataMap.get("lsh"));
			WBpttsxxzbBPO.deleteByPK(con, (String) dataMap.get("lsh"));
			lsh = (String) dataMap.get("lsh");
		} else {
			lsh = ZBCoreHelper.getGUID(con);
		}
		// 获取录入日期/有效日期起/有效日期止

		// 填充数据
		WBpttsxxzbVO pttsxxzbVO = new WBpttsxxzbVO();// 平台推送信息主表
		pttsxxzbVO.setLsh(lsh);// 流水号
		pttsxxzbVO.setTssjydm("01");// 所属数据源代码(t_dm_xt_tspt_sjy)
		pttsxxzbVO.setSwglm((String) dataMap.get("swglm"));// 税务管理码
		pttsxxzbVO
				.setTsqksm("经纳税人确认、提交变更登记信息的，不再要求其提交纸质资料及其影印件，视同该信息已经办理税务登记信息变更手续。纳税人需要换发税务登记证件的，按规定到税务机关换发登记证件。");// 推送情况说明
		pttsxxzbVO.setTstxnr("纳税人基础信息确认表");// 推送提醒内容
		pttsxxzbVO.setTsrydm("3200admin");// 推送人员代码
		pttsxxzbVO.setTsjgdm("23200000000");// 推送机关代码
		pttsxxzbVO.setTsrq(DBTimeServer.getDBTime(con));// 推送时间
		pttsxxzbVO.setTslx("0");// 推送类型 0 网上办税厅 1 稽查调用 2 税收评定调用
		pttsxxzbVO.setSfbgbj("1");// 变更标记 0 不需要变更 1 需要变更
		pttsxxzbVO.setTsqrzt("3");// 状态 0 未提交确认信息 1 暂存修改中 2补正资料(暂存) 3 已提交确认信息
									// 4退回(审核不通过) 5初审通过 6审核通过 7已完成 9作废
		pttsxxzbVO.setQrlx("0");// 0 纳税人确认 1 税务人员确认
		pttsxxzbVO.setStr_yxrqqs(ZBCoreHelper.getBeforeDateTime(con,
				"YYYY-MM-DD"));// 推送有效日期起
		pttsxxzbVO.setStr_yxrqzz((DBTimeServer.getDBTime(con)
				.get(Calendar.YEAR) + 1) + "-12-31");// 推送有效日期止
		pttsxxzbVO.setLrrydm("ZSBS");// 录入人员代码
		WBpttsxxzbBPO.insert(con, pttsxxzbVO);// 新增平台推送信息主表数据
		// 推送信息明细
		List<MorphDynaBean> listmx = (List<MorphDynaBean>) dataMap.get("mxxx");
		ArrayList<Object> paramList;
		WBpttsxxmxVO pttsxxmxVO;// 平台推送信息明细
		MorphDynaBean bean;
		CachedRowSet rs;
		if (null != listmx) {
			for (int i = 0; i < listmx.size(); i++) {
				String sql = "SELECT T.tssjx_dm FROM DB_WSBS.T_XT_TSPT_TSSJXPZB T WHERE T.TABNAME = ? AND T.COLNAME = ? AND T.XY_BJ = '1'";
				paramList = new ArrayList<Object>();
				bean = listmx.get(i);
				paramList.add(bean.get("TABNAME"));
				paramList.add(bean.get("COLNAME"));
				if ("T_DJ_RYQKB".equals(bean.get("TABNAME"))) {
					sql += " AND T.BSXG_TJ = ? ";
					paramList.add(bean.get("BSXG_TJ"));
				}
				rs = QueryCssBPO.findAll(con, sql, paramList);
				rs.next();
				pttsxxmxVO = new WBpttsxxmxVO();
				pttsxxmxVO.setLsh(lsh);
				pttsxxmxVO.setTssjxdm(rs.getString("TSSJX_DM"));
				paramList = new ArrayList<Object>();
				paramList.add(lsh);
				paramList.add(pttsxxmxVO.getTssjxdm());
				CachedRowSet rs2 = QueryCssBPO
						.findAll(
								con,
								"SELECT NVL(MAX(T.MX_XH),0) AS XH FROM DB_WSBS.T_XT_TSPT_TSXXMXB T WHERE T.LSH=? AND T.TSSJX_DM=?",
								paramList);
				rs2.next();
				pttsxxmxVO.setMxxh(String.valueOf(rs2.getInt("XH") + 1));
				pttsxxmxVO.setCzlx("1");
				pttsxxmxVO.setNrxgbj(bean.get("NRXG_BJ").toString());
				pttsxxmxVO.setSjxynr(bean.get("SJX_YNR").toString());
				pttsxxmxVO.setSjxxnr(bean.get("SJX_XNR").toString());
				pttsxxmxVO.setLrrydm("ZSBS");
				WBpttsxxmxBPO.insert(con, pttsxxmxVO);
			}
		}
		dataMap.put("lsh", lsh);
		dataMap.put("ywsxDm", "01019004");
		try {
			ZBCoreHelper.upFile(dataMap, con);
		} catch (Exception e) {
			throw new TaxBaseBizException(e.getMessage());
		}
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	protected ResponseEvent queryData(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(dataMap.get("swglm"));
		// 纳税人基础信息SQL
		String sql = "SELECT X.NSR_MC, X.ZCDZ, X.ZCDZYB, X.SJJYDZ, X.SJJYDZYB FROM DB_WSBS.T_DJ_JGNSR X WHERE X.SWGLM = ?";
		CachedRowSet rs = QueryCssBPO.findAll(con, sql, paramList);
		List<Map<String, String>> jgnsrList = new ArrayList<Map<String, String>>();// 机关纳税人基础信息
		Map<String, String> map;
		if (rs.next()) {
			map = new HashMap<String, String>();
			map.put("NSR_MC", rs.getString("NSR_MC"));// 纳税人名称
			map.put("ZCDZ", rs.getString("ZCDZ"));// 注册地址
			map.put("ZCDZYB", rs.getString("ZCDZYB"));// 注册地邮政编码
			map.put("SJJYDZ", rs.getString("SJJYDZ"));// 生产经营地址
			map.put("SJJYDZYB", rs.getString("SJJYDZYB"));// 生产经营地邮政编码
			jgnsrList.add(map);
		}
		responseEvent.respMapParam.put("jgnsrList", jgnsrList);
		List<Map<String, String>> zjlxList = getZjList(con);// 证件类型信息
		responseEvent.respMapParam.put("zjlxList", zjlxList);
		List<Map<String, String>> gjdqList = getGjdqList(con);// 国籍地区信息
		responseEvent.respMapParam.put("gjdqList", gjdqList);
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT A.XM,A.GJDQ_DM,C.MC AS GJDQ_MC,A.ZJLX_DM,D.MC AS ZJLX_MC, ");
		sb.append(" A.ZJH,A.GDDH,A.YD_DH,A.RYLX_DM,B.MC AS RYLX_MC ");
		sb.append(" FROM DB_WSBS.T_DJ_RYQKB A,DB_WSBS.T_DM_DJ_RYLX B, ");
		sb.append(" DB_WSBS.T_DM_GY_GJ C,DB_WSBS.T_DM_DJ_SFZJLX D ");
		sb.append(" WHERE A.RYLX_DM = B.RYLX_DM ");
		sb.append(" AND A.GJDQ_DM = C.GJDQ_DM ");
		sb.append(" AND A.ZJLX_DM = D.SFZJLX_DM ");
		sb.append(" AND ROWNUM = 1 ");
		sb.append(" AND A.SWGLM = ? ");
		sb.append(" AND A.RYLX_DM = ? ");
		paramList = new ArrayList<Object>();
		paramList.add(dataMap.get("swglm"));
		paramList.add("05");// 法定代表人（负责人或业主）
		rs = QueryCssBPO.findAll(con, sb.toString(), paramList);
		List<Map<String, String>> fddbrList = new ArrayList<Map<String, String>>();// 法定代表人信息
		map = findRyqk(rs);
		fddbrList.add(map);
		responseEvent.respMapParam.put("fddbrList", fddbrList);
		paramList = new ArrayList<Object>();
		paramList.add(dataMap.get("swglm"));
		paramList.add("06");// 财务负责人
		rs = QueryCssBPO.findAll(con, sb.toString(), paramList);
		List<Map<String, String>> cwfzrList = new ArrayList<Map<String, String>>();// 财务负责人信息
		map = findRyqk(rs);
		cwfzrList.add(map);
		responseEvent.respMapParam.put("cwfzrList", cwfzrList);
		paramList = new ArrayList<Object>();
		paramList.add(dataMap.get("swglm"));
		paramList.add("07");// 办税人员
		rs = QueryCssBPO.findAll(con, sb.toString(), paramList);
		List<Map<String, String>> bsryList = new ArrayList<Map<String, String>>();// 办税人员信息
		map = findRyqk(rs);
		bsryList.add(map);
		responseEvent.respMapParam.put("bsryList", bsryList);
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	// 获取国籍地区信息列表
	private List<Map<String, String>> getGjdqList(Connection con)
			throws SQLException {
		String sql = "SELECT T.GJDQ_DM, T.MC AS GJDQ_MC FROM DB_WSBS.T_DM_GY_GJ T WHERE T.XY_BJ = '1' ORDER BY T.GJDQ_DM";
		CachedRowSet rs = QueryCssBPO.findAll(con, sql, null);
		List<Map<String, String>> gjdqList = new ArrayList<Map<String, String>>();// 国籍地区信息
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("gjdqdm", rs.getString("GJDQ_DM"));// 国际地区代码
			map.put("gjdqmc", rs.getString("GJDQ_MC"));// 国籍地区名称
			gjdqList.add(map);
		}
		return gjdqList;
	}

	@Override
	protected ResponseEvent validateData(RequestEvent req, Connection con)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	// 获取人员情况信息
	private Map<String, String> findRyqk(CachedRowSet rs) throws SQLException {
		Map<String, String> map = null;
		if (rs.next()) {
			map = new HashMap<String, String>();
			map.put("XM", rs.getString("XM"));// 姓名
			map.put("GJDQ_DM", rs.getString("GJDQ_DM"));// 国籍或地区代码
			map.put("GJDQ_MC", rs.getString("GJDQ_MC"));// 国籍或地区名称
			map.put("ZJLX_DM", rs.getString("ZJLX_DM"));// 证件类型代码
			map.put("ZJLX_MC", rs.getString("ZJLX_MC"));// 证件类型名称
			map.put("ZJH", rs.getString("ZJH"));// 证件号码
			map.put("GDDH", rs.getString("GDDH"));// 固定电话
			map.put("YD_DH", rs.getString("YD_DH"));// 移动电话
			map.put("RYLX_DM", rs.getString("RYLX_DM"));// 人员类型代码
			map.put("RYLX_MC", rs.getString("RYLX_MC"));// 人员类型名称
		}
		return map;
	}

	// 获取人员情况信息
	private Map<String, Map<String, String>> findRyqk2(CachedRowSet rs)
			throws SQLException {
		Map<String, Map<String, String>> resultMap = new HashMap<String, Map<String, String>>();
		Map<String, String> map = null;
		if (rs.next()) {
			map = new HashMap<String, String>();
			map.put("SJX_YNR", rs.getString("XM"));
			map.put("NRXG_BJ", "0");
			map.put("SJX_XNR", "");
			resultMap.put("XM", map);
			map = new HashMap<String, String>();
			map.put("SJX_YNR", rs.getString("GJDQ_DM"));
			map.put("NRXG_BJ", "0");
			map.put("SJX_XNR", "");
			resultMap.put("GJDQ_DM", map);
			map = new HashMap<String, String>();
			map.put("SJX_YNR", rs.getString("ZJLX_DM"));
			map.put("NRXG_BJ", "0");
			map.put("SJX_XNR", "");
			resultMap.put("ZJLX_DM", map);
			map = new HashMap<String, String>();
			map.put("SJX_YNR", rs.getString("ZJH"));
			map.put("NRXG_BJ", "0");
			map.put("SJX_XNR", "");
			resultMap.put("ZJH", map);
			map = new HashMap<String, String>();
			map.put("SJX_YNR", rs.getString("GDDH"));
			map.put("NRXG_BJ", "0");
			map.put("SJX_XNR", "");
			resultMap.put("GDDH", map);
			map = new HashMap<String, String>();
			map.put("SJX_YNR", rs.getString("YD_DH"));
			map.put("NRXG_BJ", "0");
			map.put("SJX_XNR", "");
			resultMap.put("YD_DH", map);

		}
		return resultMap;
	}

	// 获取证件信息列表
	private List<Map<String, String>> getZjList(Connection con)
			throws SQLException {
		String sql = "SELECT S.SFZJLX_DM AS ZJLX_DM, S.MC AS ZJLX_MC FROM DB_WSBS.T_DM_DJ_SFZJLX S WHERE S.XY_BJ = '1'";
		CachedRowSet rs = QueryCssBPO.findAll(con, sql, null);
		List<Map<String, String>> zjlxList = new ArrayList<Map<String, String>>();// 证件类型信息
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("zjlxdm", rs.getString("ZJLX_DM"));// 证件类型代码
			map.put("zjlxmc", rs.getString("ZJLX_MC"));// 证件类型名称
			zjlxList.add(map);
		}
		return zjlxList;
	}

	public static void main(String[] args) {
		System.out.println(Calendar.getInstance().get(Calendar.YEAR) + 1
				+ "-12-31");
	}

}
