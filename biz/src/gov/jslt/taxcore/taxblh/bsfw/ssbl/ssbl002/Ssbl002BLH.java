package gov.jslt.taxcore.taxblh.bsfw.ssbl.ssbl002;

import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxcore.taxbpo.bsfw.ssbl.ssbl002.Ssbl002jmssqbBPO;
import gov.jslt.taxcore.taxbpo.bsfw.ssbl.ssbl002.Ssbl002jmssqmxBPO;
import gov.jslt.taxcore.taxbpo.comm.JgnsrPO;
import gov.jslt.taxcore.taxbpo.comm.WBwsfwqkbBPO;
import gov.jslt.taxevent.bsfw.ssbl.ssbl002.Ssbl002jmssqbVO;
import gov.jslt.taxevent.bsfw.ssbl.ssbl002.Ssbl002jmssqmxVO;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JgnsrVO;
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

public class Ssbl002BLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		String delMethod = req.getDealMethod();
		if ("querySsxmXl".equals(delMethod)) {// 减免类别
			return querySsxmXl(req, con);
		} else if ("queryJdxx".equals(delMethod)) {
			return queryJdxx(req, con);
		} else if ("insertJmssp".equals(delMethod)) {// 减免税审批申请
			return insertJmssp(req, con);
		} else if ("queryData".equals(delMethod)) {// 减免税申请查询
			return queryData(req, con);
		}
		return null;
	}

	protected ResponseEvent queryData(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(dataMap.get("LSH"));
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT A.JMXM_DM,  A.SQJMFD,      A.ZSXM_DM,        TO_CHAR(A.SQJMQX_QSRQ, 'YYYY-MM-DD') AS SQJMQX_QSRQ,        TO_CHAR(A.SQJMQX_ZZRQ, 'YYYY-MM-DD') AS SQJMQX_ZZRQ,        A.SL,        A.SQJM_JE,        A.JMLX_DM,        A.ZSPM_DM,  A.ZCYJXM,       C.MC AS ZSXM_MC,        B.MC AS ZSPM_MC,K.SSSQXL_DM AS JMLB_DM,K.MC AS JMLB_MC   FROM DB_WSBS.T_WB_WS_YH_JMSQMX A,        DB_WSBS.T_DM_GY_ZSPM      B,        DB_WSBS.T_DM_GY_ZSXM      C,DB_WSBS.T_DM_WB_SSSQXM    Z,DB_WSBS.T_DM_WB_SSSQXL    K  WHERE A.LSH = ?    AND A.ZSXM_DM = B.ZSXM_DM    AND A.ZSPM_DM = B.ZSPM_DM    AND B.ZSXM_DM = C.ZSXM_DM AND A.JMXM_DM = Z.YWSX_DM AND Z.YWXL_DM = K.SSSQXL_DM",
						paramList);
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("JMXM_DM", rs.getString("JMXM_DM"));// 减免项目代码
			map.put("ZSXM_DM", rs.getString("ZSXM_DM"));// 征收项目代码
			map.put("SQJMQX_QSRQ", rs.getString("SQJMQX_QSRQ"));// 申请减免期限（起始日期）
			map.put("SQJMQX_ZZRQ", rs.getString("SQJMQX_ZZRQ"));// 申请减免期限（终止日期）
			map.put("SL", rs.getString("SL"));// 税率
			map.put("SQJM_JE", rs.getString("SQJM_JE"));// 申请减免金额
			map.put("SQJMFD", rs.getString("SQJMFD"));// 申请减免比例
			map.put("JMLX_DM", rs.getString("JMLX_DM"));// 减免类型代码
			map.put("ZSPM_DM", rs.getString("ZSPM_DM"));// 征收品目代码
			map.put("ZSXM_MC", rs.getString("ZSXM_MC"));// 征收项目名称
			map.put("ZSPM_MC", rs.getString("ZSPM_MC"));// 征收品目名称
			map.put("ZCYJXM", rs.getString("ZCYJXM"));// 政策依据细目
			map.put("JMLB_DM", rs.getString("JMLB_DM"));// 减免类别代码
			map.put("JMLB_MC", rs.getString("JMLB_MC"));// 减免类别名称
			dataList.add(map);
		}
		responseEvent.respMapParam.put("dataList", dataList);

		WBwsfwqkbVO VO = WBwsfwqkbBPO.queryByPK(con, dataMap.get("LSH")
				.toString());
		paramList = new ArrayList<Object>();
		paramList.add(VO.getSsxmdm());
		rs = QueryCssBPO
				.findAll(
						con,
						"SELECT  A.SXMC AS YWSX_MC  FROM DB_WSBS.T_DM_SXGL_YWSXXM A  WHERE A.YWSX_DM =? ",
						paramList);

		rs.next();
		responseEvent.respMapParam.put("YWSX_MC", rs.getString("YWSX_MC"));// 业务事项名称

		paramList = new ArrayList<Object>();
		paramList.add(VO.getSsxmdm());
		paramList.add(dataList.get(0).get("ZCYJXM"));

		rs = QueryCssBPO
				.findAll(
						con,
						"SELECT X.WJMC FROM DB_WSBS.T_DM_SXGL_YWSXXM X WHERE X.YWSX_DM=? AND X.SXXM=?",
						paramList);

		rs.next();
		responseEvent.respMapParam.put("WJMC", rs.getString("WJMC"));// 政策依据名称

		Ssbl002jmssqbVO jmsVo = Ssbl002jmssqbBPO.queryByPK(con,
				dataMap.get("LSH").toString());
		responseEvent.respMapParam.put("SQJMLY", jmsVo.getSqjmly());// 申请理由

		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	protected ResponseEvent insertJmssp(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String lsh = ZBCoreHelper.getWsh(dataMap.get("swglm").toString());
		WBwsfwqkbVO vo = new WBwsfwqkbVO();// 网上服务情况表
		vo.setLsh(lsh);
		vo.setSqlxdm("SQ019");
		vo.setSssqdl((String) dataMap.get("JMLB"));
		vo.setSssqxl((String) dataMap.get("ywsxDm"));
		vo.setSwglm(dataMap.get("swglm").toString());
		vo.setLxdh(jsonReqData.getSjHm());
		vo.setLxr(jsonReqData.getXm());
		vo.setGljgdm(dataMap.get("gljgDm").toString());
		vo.setZt("9");
		vo.setCarz("0");
		vo.setSslx("0");
		vo.setSsxmdm((String) dataMap.get("ywsxDm"));
		vo.setSqsj(DBTimeServer.getDBTime(con));
		WBwsfwqkbBPO.insert(con, vo);

		JgnsrVO jgnsrVO = JgnsrPO.queryByPK(con, vo.getSwglm());
		Ssbl002jmssqbVO jmVo = new Ssbl002jmssqbVO();
		jmVo.setLsh(lsh);
		jmVo.setSwglm(vo.getSwglm());
		jmVo.setSqrmc(jgnsrVO.getNsrmc());
		jmVo.setZfbj("0");
		jmVo.setSpbabj("2");
		jmVo.setSqjmly(dataMap.get("SQJMLY").toString());
		Ssbl002jmssqbBPO.insert(con, jmVo);

		Ssbl002jmssqmxVO jmssqmxVo;// 减免地方税费申请审批表
		List<MorphDynaBean> listmx = (List<MorphDynaBean>) dataMap.get("mxxx");
		MorphDynaBean bean;
		if (listmx != null) {
			for (int i = 0; i < listmx.size(); i++) {
				jmssqmxVo = new Ssbl002jmssqmxVO();
				bean = listmx.get(i);
				jmssqmxVo.setLsh(lsh);
				jmssqmxVo.setMxxh(String.valueOf(i + 1));
				jmssqmxVo.setJmxmdm((String) dataMap.get("ywsxDm"));// 减免项目1
				jmssqmxVo.setGljgdm((String) dataMap.get("gljgdm"));// 管理机关代码
				jmssqmxVo.setZsxmdm((String) bean.get("ZSXM_DM"));// 征收项目代码
				jmssqmxVo.setZspmdm((String) bean.get("ZSPM_DM"));// 征收品目代码
				jmssqmxVo.setStr_sqjmqxqsrq((String) bean.get("SQJMQX_QSRQ"));// 申请减免期限始
				jmssqmxVo.setStr_sqjmqxzzrq((String) bean.get("SQJMQX_ZZRQ"));// 申请减免期限始
				jmssqmxVo.setJmlxdm((String) dataMap.get("JMLX_DM"));// 减免类型代码
				jmssqmxVo.setSqjmje((String) bean.get("SQJM_JE"));// 申请减免金额
				jmssqmxVo.setSqjmfd((String) bean.get("SQJMFD"));// 申请减免比例
				jmssqmxVo.setSl((String) bean.get("SL"));// 税率
				jmssqmxVo.setZcyj((String) dataMap.get("ywsxDm"));// //减免项目
				jmssqmxVo.setZcyjxm((String) dataMap.get("SXXM"));// 事项细目
				Ssbl002jmssqmxBPO.insert(con, jmssqmxVo);
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

	protected ResponseEvent queryJdxx(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(dataMap.get("swglm"));
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT DISTINCT Z.ZSPM_DM, Z.MC AS ZSPM_MC, K.ZSXM_DM, K.MC AS ZSXM_MC ");
		sql.append("  FROM DB_WSBS.T_RD_SZJDXX  X, ");
		sql.append(" DB_WSBS.T_DM_GY_ZSPM Z, ");
		sql.append(" DB_WSBS.T_DM_GY_ZSXM K ");
		sql.append(" WHERE X.SWGLM = ? ");
		sql.append(" AND X.ZSXM_DM != '90' ");
		sql.append(" AND (X.ZX_BJ != '1' OR X.ZX_BJ IS NULL) ");
		sql.append(" AND X.ZSXM_DM = Z.ZSXM_DM ");
		sql.append(" AND X.ZSXM_DM = K.ZSXM_DM ");
		sql.append(" AND X.ZSPM_DM = Z.ZSPM_DM ");
		sql.append(" AND Z.ZSXM_DM = K.ZSXM_DM ");
		sql.append(" UNION ");
		sql.append(" SELECT DISTINCT Z.ZSPM_DM, Z.MC AS ZSPM_MC, K.ZSXM_DM, K.MC AS ZSXM_MC ");
		sql.append(" FROM DB_WSBS.T_DM_GY_ZSPM Z, DB_WSBS.T_DM_GY_ZSXM K ");
		sql.append(" WHERE Z.ZSXM_DM = K.ZSXM_DM ");
		sql.append(" AND K.ZSXM_DM IN ('10', '61', '63') ");
		sql.append(" AND Z.XY_BJ = '1' ");
		sql.append(" AND Z.ZSPM_DM NOT IN ('7777', '8888', '9999') ");
		CachedRowSet rs = QueryCssBPO.findAll(con, sql.toString(), paramList);
		List<Map<String, String>> jdList = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("ZSPM_DM", rs.getString("ZSPM_DM"));
			map.put("ZSPM_MC", rs.getString("ZSPM_MC"));
			map.put("ZSXM_DM", rs.getString("ZSXM_DM"));
			map.put("ZSXM_MC", rs.getString("ZSXM_MC"));
			jdList.add(map);
		}
		responseEvent.respMapParam.put("jdList", jdList);
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	// 查询减免事项
	protected ResponseEvent querySsxmXl(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT sssqxl_dm,mc FROM db_wsbs.t_dm_wb_sssqxl x WHERE x.xy_bj = '1' AND x.sssqdl_dm = '05'",
						null);
		List<Map<String, String>> xlList = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("SSSQXL_DM", rs.getString("SSSQXL_DM"));
			map.put("MC", rs.getString("MC"));
			xlList.add(map);
		}
		responseEvent.respMapParam.put("xlList", xlList);
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	@Override
	protected ResponseEvent validateData(RequestEvent req, Connection con)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
