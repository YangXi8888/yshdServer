package gov.jslt.taxcore.taxblh.bsfw.ssbl.ssbl003;

import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxcore.taxbpo.bsfw.ssbl.ssbl003.WBjmcztdsqbBPO;
import gov.jslt.taxcore.taxbpo.bsfw.ssbl.ssbl003.WBjmcztdsqmxBPO;
import gov.jslt.taxcore.taxbpo.comm.WBwsfwqkbBPO;
import gov.jslt.taxevent.bsfw.ssbl.ssbl003.WBjmcztdsqbVO;
import gov.jslt.taxevent.bsfw.ssbl.ssbl003.WBjmcztdsqmxVO;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.WBwsfwqkbVO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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

public class Ssbl003BLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		String dealMethod = req.getDealMethod();
		if ("queryData".equals(dealMethod)) {// 涉税事项申请查询
			return queryData(req, con);
		} else if ("initData".equals(dealMethod)) {// 初始化数据
			return initData(req, con);
		} else if ("insertSssxsq".equals(dealMethod)) {// 涉税事项申请提交
			return insertSssxsq(req, con);
		}
		return null;
	}

	protected ResponseEvent initData(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(dataMap.get("swglm"));
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT DISTINCT Z.ZSPM_DM, Z.MC AS ZSPM_MC, K.ZSXM_DM, K.MC AS ZSXM_MC ,Z.SL AS DWSE ");
		sb.append("  FROM DB_WSBS.T_RD_SZJDXX  X, ");
		sb.append(" DB_WSBS.T_DM_GY_ZSPM Z, ");
		sb.append(" DB_WSBS.T_DM_GY_ZSXM K ");
		sb.append(" WHERE X.SWGLM = ? ");
		sb.append(" AND X.ZSXM_DM = '15' ");
		sb.append(" AND (X.ZX_BJ != '1' OR X.ZX_BJ IS NULL) ");
		sb.append(" AND X.ZSXM_DM = Z.ZSXM_DM ");
		sb.append(" AND X.ZSXM_DM = K.ZSXM_DM ");
		sb.append(" AND X.ZSPM_DM = Z.ZSPM_DM ");
		sb.append(" AND Z.ZSXM_DM = K.ZSXM_DM ");
		CachedRowSet rs = QueryCssBPO.findAll(con, sb.toString(), paramList);
		List<Map<String, String>> zhxxList = new ArrayList<Map<String, String>>();// 征收信息列表
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("zsxm_mc", rs.getString("ZSXM_MC"));// 申请减免税种名称
			map.put("zsxm_dm", rs.getString("ZSXM_DM"));// 申请减免税种代码
			map.put("zspm_mc", rs.getString("ZSPM_MC"));// 申请减免税目名称
			map.put("zspm_dm", rs.getString("ZSPM_DM"));// 申请减免税目代码
			map.put("dwse", rs.getString("DWSE"));// 单位税额
			zhxxList.add(map);
		}
		if (zhxxList.size() == 0) {
			responseEvent.respMapParam.put("jdTs", "没有查询到有效的城镇土地使用税鉴定,无法申请!");
		} else {
			responseEvent.respMapParam.put("zhxxList", zhxxList);// 征收信息列表
		}
		String sql = "SELECT j.jmlx_dm,j.mc FROM db_wsbs.t_dm_gy_jmlx j WHERE j.xy_bj = '1'";
		rs = QueryCssBPO.findAll(con, sql, null);
		List<Map<String, String>> jmlxList = new ArrayList<Map<String, String>>();// 减免类型列表
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("jmlxdm", rs.getString("jmlx_dm"));
			map.put("jmlxmc", rs.getString("mc"));
			jmlxList.add(map);
		}
		responseEvent.respMapParam.put("jmlxList", jmlxList);
		sql = "SELECT X.YWSX_DM,X.SXXM, X.WJMC FROM DB_WSBS.T_DM_SXGL_YWSXXM X WHERE X.YWSX_DM='05050010'";
		rs = QueryCssBPO.findAll(con, sql, null);
		List<Map<String, String>> zcyjList = new ArrayList<Map<String, String>>();// 政策依据列表
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("zcyjxm", rs.getString("SXXM"));
			map.put("zcyjmc", rs.getString("WJMC"));
			map.put("ywsxdm", rs.getString("YWSX_DM"));
			zcyjList.add(map);
		}
		responseEvent.respMapParam.put("zcyjList", zcyjList);
		sql = "SELECT JMLX_DM, JMLX_MC FROM T_DM_ZB_CZTDJMLX WHERE XY_BJ = '1'";
		rs = QueryCssBPO.findAll(con, sql, null);
		List<Map<String, String>> sqjmlxList = new ArrayList<Map<String, String>>();// 申请减免类型列表
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("jmlxdm", rs.getString("JMLX_DM"));
			map.put("jmlxmc", rs.getString("JMLX_MC"));
			sqjmlxList.add(map);
		}
		responseEvent.respMapParam.put("sqjmlxList", sqjmlxList);// 申请减免类型列表
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
		paramList.add(dataMap.get("lsh"));
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT A.JMLX_DM,D.JMXM,TO_CHAR(A.JMRQQ, 'YYYY-MM-DD') AS JMRQQ, ");
		sb.append(" TO_CHAR(A.JMRQZ, 'YYYY-MM-DD') AS JMRQZ, ");
		sb.append(" A.ZDMJ,A.YSMJ,A.DWSE,A.NYNSE,A.SQJMMJ,A.SQJMSE, ");
		sb.append(" A.ZCYJXM,C.MC AS ZSXM_MC,B.MC AS ZSPM_MC,E.JMLX_MC AS SQJMLX_MC,F.MC AS JMLX_MC ");
		sb.append(" FROM DB_WSBS.T_WB_WS_YH_JMCZTDSQMX A,DB_WSBS.T_DM_GY_ZSPM B,DB_WSBS.T_DM_GY_ZSXM C,DB_WSBS.T_WB_WS_YH_JMCZTDSQB D,T_DM_ZB_CZTDJMLX E,DB_WSBS.T_DM_GY_JMLX F");
		sb.append(" WHERE A.LSH = ? ");
		sb.append(" AND A.ZSXM_DM = B.ZSXM_DM ");
		sb.append(" AND A.ZSPM_DM = B.ZSPM_DM ");
		sb.append(" AND B.ZSXM_DM = C.ZSXM_DM ");
		sb.append(" AND A.LSH = D.LSH ");
		sb.append(" AND A.JMLX = E.JMLX_DM ");
		sb.append(" AND A.JMLX_DM = F.JMLX_DM ");
		sb.append(" AND E.XY_BJ = '1' ");
		sb.append(" AND F.XY_BJ = '1' ");
		CachedRowSet rs = QueryCssBPO.findAll(con, sb.toString(), paramList);
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("jmxm", rs.getString("JMXM"));// 减免项目
			map.put("jmlxdm", rs.getString("JMLX_DM"));// 减免类型代码
			map.put("sqjmlxmc", rs.getString("SQJMLX_MC"));// 申请减免类型名称
			map.put("jmlxmc", rs.getString("JMLX_MC"));// 减免类型名称
			map.put("jmrqq", rs.getString("JMRQQ"));// 减免日期起
			map.put("jmrqz", rs.getString("JMRQZ"));// 减免日期止
			map.put("zdmj", rs.getString("ZDMJ"));// 占地面积
			map.put("ysmj", rs.getString("YSMJ"));// 应税面积
			map.put("dwse", rs.getString("DWSE"));// 单位税额
			map.put("nynse", rs.getString("NYNSE"));// 年应纳税额
			map.put("sqjmmj", rs.getString("SQJMMJ"));// 申请减免面积
			map.put("sqjmse", rs.getString("SQJMSE"));// 申请减免税额
			map.put("zcyjxm", rs.getString("ZCYJXM"));// 政策依据细目
			map.put("zsxmmc", rs.getString("ZSXM_MC"));// 征收项目名称
			map.put("zspmmc", rs.getString("ZSPM_MC"));// 征收品目名称
			dataList.add(map);
		}
		responseEvent.respMapParam.put("dataList", dataList);
		WBwsfwqkbVO VO = WBwsfwqkbBPO.queryByPK(con, dataMap.get("lsh")
				.toString());
		paramList = new ArrayList<Object>();
		paramList.add(VO.getSsxmdm());
		rs = QueryCssBPO
				.findAll(
						con,
						"SELECT  A.SXMC AS YWSX_MC  FROM DB_WSBS.T_DM_SXGL_YWSXXM A  WHERE A.YWSX_DM =? ",
						paramList);

		rs.next();
		responseEvent.respMapParam.put("ywsxmc", rs.getString("YWSX_MC"));// 业务事项名称
		paramList = new ArrayList<Object>();
		paramList.add(VO.getSsxmdm());
		paramList.add(dataList.get(0).get("zcyjxm"));

		rs = QueryCssBPO
				.findAll(
						con,
						"SELECT X.WJMC FROM DB_WSBS.T_DM_SXGL_YWSXXM X WHERE X.YWSX_DM=? AND X.SXXM=?",
						paramList);

		rs.next();
		responseEvent.respMapParam.put("zcyjmc", rs.getString("WJMC"));// 政策依据名称

		WBjmcztdsqbVO cztdsqbVO = WBjmcztdsqbBPO.queryByPK(con,
				dataMap.get("lsh").toString());

		responseEvent.respMapParam.put("sqjmly", cztdsqbVO.getSqjmly());// 申请理由
		responseEvent.respMapParam.put("sqrmc", cztdsqbVO.getSqrmc()); // 纳税人名称
		responseEvent.respMapParam.put("jmrqq", cztdsqbVO.getStr_jmrqq());// 减免日期起
		responseEvent.respMapParam.put("jmrqz", cztdsqbVO.getStr_jmrqz());// 减免日期止
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	protected ResponseEvent insertSssxsq(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String swglm = (String) dataMap.get("swglm");
		String lsh = ZBCoreHelper.getWsh(swglm);
		String jmxmdm = (String) dataMap.get("ywsxDm");
		WBwsfwqkbVO vo = new WBwsfwqkbVO();// 网上服务情况表
		vo.setLsh(lsh);
		vo.setSqlxdm("SQ020");// 城镇土地减免申请
		vo.setSssqdl("0505");
		vo.setSssqxl(jmxmdm);
		vo.setSwglm(swglm);
		vo.setLxdh(jsonReqData.getSjHm());
		vo.setLxr(jsonReqData.getXm());
		vo.setGljgdm(dataMap.get("gljgDm").toString());
		vo.setZt("9");
		vo.setCarz("0");
		vo.setSslx("0");
		vo.setSsxmdm(jmxmdm);
		vo.setSqsj(DBTimeServer.getDBTime(con));
		WBwsfwqkbBPO.insert(con, vo);// 新增网上办税服务情况数据
		WBjmcztdsqbVO jmcztdsqbVo = new WBjmcztdsqbVO();// 减免税申请表
		jmcztdsqbVo.setLsh(lsh);
		jmcztdsqbVo.setSwglm(swglm);
		jmcztdsqbVo.setSqjmly(dataMap.get("sqjmly").toString());// 申请减免理由
		jmcztdsqbVo.setSqrmc(dataMap.get("nsrMc").toString());// 纳税人名称
		jmcztdsqbVo.setSpbabj("02");
		jmcztdsqbVo.setJmxm(jmxmdm);// 减免项目
		jmcztdsqbVo.setStr_jmrqq(dataMap.get("jmrqq").toString());// 减免日期起
		jmcztdsqbVo.setStr_jmrqz(dataMap.get("jmrqz").toString());// 减免日期止
		WBjmcztdsqbBPO.insert(con, jmcztdsqbVo);// 新增减免税申请数据
		WBjmcztdsqmxVO jmcztdsqmxVo;// 减免税申请（适用城镇土地使用税困难减免）
		List<MorphDynaBean> listmx = (List<MorphDynaBean>) dataMap.get("mxxx");
		MorphDynaBean bean;
		if (null != listmx) {
			for (int i = 0; i < listmx.size(); i++) {
				jmcztdsqmxVo = new WBjmcztdsqmxVO();
				bean = listmx.get(i);
				jmcztdsqmxVo.setLsh(lsh);
				jmcztdsqmxVo.setMxxh(String.valueOf(i + 1));
				jmcztdsqmxVo.setZsxmdm((String) bean.get("zsxmdm"));// 征收项目代码
				jmcztdsqmxVo.setZspmdm((String) bean.get("zspmdm"));// 征收品目代码
				jmcztdsqmxVo.setZdmj((String) bean.get("zdmj"));// 占地面积
				jmcztdsqmxVo.setYsmj((String) bean.get("ysmj"));// 应税面积
				jmcztdsqmxVo.setDwse((String) bean.get("dwse"));// 单位税额
				jmcztdsqmxVo.setNynse((String) bean.get("nynse"));// 年应纳税额
				jmcztdsqmxVo.setSqjmmj((String) bean.get("sqjmmj"));// 申请减免面积
				jmcztdsqmxVo.setSqjmse((String) bean.get("sqjmse"));// 申请减免税额
				jmcztdsqmxVo.setGljgdm((String) dataMap.get("gljgdm"));// 管理机关代码
				jmcztdsqmxVo.setStr_jmrqq((String) dataMap.get("jmrqq"));// 减免日期起
				jmcztdsqmxVo.setStr_jmrqz((String) dataMap.get("jmrqz"));// 减免日期止
				jmcztdsqmxVo.setZcyj((String) dataMap.get("zcyj"));// 政策依据（业务事项代码）
				jmcztdsqmxVo.setZcyjxm((String) dataMap.get("zcyjxm"));// 政策依据细目
				jmcztdsqmxVo.setJmlxdm((String) bean.get("jmlxdm"));// 减免类型代码
				jmcztdsqmxVo.setJmlx((String) dataMap.get("sqjmlx"));// 申请减免类型代码
				WBjmcztdsqmxBPO.insert(con, jmcztdsqmxVo);
			}
		}
		dataMap.put("lsh", lsh);
		try {
			ZBCoreHelper.upFile(dataMap, con);
		} catch (Exception e) {
			throw new TaxBaseBizException(e.getMessage());
		}
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
