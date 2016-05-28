package gov.jslt.taxcore.taxblh.bsfw.sbns.sbns002;

import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxcore.taxbpo.bsfw.sbns.sbns002.WBsbfsbBPO;
import gov.jslt.taxcore.taxbpo.comm.WBsbfsbqkBPO;
import gov.jslt.taxcore.taxbpo.comm.ZBSbbqkBPO;
import gov.jslt.taxevent.bsfw.sbns.sbns002.WBsbfsbVO;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.WBsbfsbqkVO;
import gov.jslt.taxevent.comm.ZBSbbqkVO;

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
import com.ctp.core.bpo.QueryCssBPO;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.core.utility.dbtime.DBTimeServer;

public class Sbns002BLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		String delMethod = req.getDealMethod();
		if ("querySblb".equals(delMethod)) {
			return querySblb(req, con);
		} else if ("querySbmx".equals(delMethod)) {
			return querySbmx(req, con);
		} else if ("saveData".equals(delMethod)) {
			return saveData(req, con);
		} else if ("queryData".equals(delMethod)) {
			return queryData(req, con);
		} else if ("queryYsbLb".equals(delMethod)) {
			return queryYsbLb(req, con);
		}
		return null;
	}

	protected ResponseEvent queryData(RequestEvent req, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String pzXh = (String) dataMap.get("pzXh");
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(pzXh);
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		CachedRowSet rs = QueryCssBPO
				.findAll(
						conn,
						"SELECT A.YZ_XH,A.SB_DM,A.SBZSXM_DM,A.SBZSPM_DM,A.YJ_JE,A.SJ_JE,TO_CHAR(A.SFSSQ_QSRQ,'YYYY-MM-DD') AS SSQ_QSRQ,TO_CHAR(A.SFSSQ_ZZRQ,'YYYY-MM-DD') AS SSQ_ZZRQ,B.MC SBZSXM_MC, C.MC SBZSPM_MC   FROM DB_WSBS.T_WB_SBFSB A,        (SELECT * FROM DB_WSBS.T_DM_GY_ZSXMSB WHERE XY_BJ = '1') B,        (SELECT * FROM DB_WSBS.T_DM_GY_ZSPMSB WHERE XY_BJ = '1') C  WHERE  A.PZ_XH = ?    AND A.SBZSXM_DM = B.SBZSXM_DM    AND A.SBZSXM_DM = C.SBZSXM_DM    AND A.SBZSPM_DM = C.SBZSPM_DM",
						paramList);
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("XH", rs.getString("YZ_XH"));// 序号
			map.put("SB_DM", rs.getString("SB_DM"));// 社保代码
			map.put("SBZSXM_DM", rs.getString("SBZSXM_DM"));// 社保征收项目代码
			map.put("SBZSXM_MC", rs.getString("SBZSXM_MC"));// 社保征收项目名称
			map.put("SBZSPM_DM", rs.getString("SBZSPM_DM"));// 社保征收品目代码
			map.put("SBZSPM_MC", rs.getString("SBZSPM_MC"));// 社保征收品目名称
			map.put("YJ_JE", rs.getString("YJ_JE"));// 应缴额
			map.put("SJ_JE", rs.getString("SJ_JE"));// 实缴额
			map.put("SSQ_QSRQ", rs.getString("SSQ_QSRQ"));// 所属期起始日期
			map.put("SSQ_ZZRQ", rs.getString("SSQ_ZZRQ"));// 所属期终止日期
			dataList.add(map);
		}
		responseEvent.respMapParam.put("dataList", dataList);
		return responseEvent;
	}

	protected ResponseEvent querySbmx(RequestEvent req, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String sbDm = (String) dataMap.get("sbDm");
		String ssQq = (String) dataMap.get("ssQq");
		String ssQz = (String) dataMap.get("ssQz");
		String gljgDm = (String) dataMap.get("gljgDm");

		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(sbDm);
		paramList.add(ssQq);
		paramList.add(ssQz);
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		StringBuilder str = new StringBuilder();
		CachedRowSet rs = QueryCssBPO
				.findAll(
						conn,
						"SELECT A.XH,A.SB_DM,A.SBZSXM_DM,A.SBZSPM_DM,A.YJFE_JE,TO_CHAR(A.SSQ_QSRQ,'YYYY-MM-DD') AS SSQ_QSRQ,TO_CHAR(A.SSQ_ZZRQ,'YYYY-MM-DD') AS SSQ_ZZRQ,TO_CHAR(A.JKQX, 'YYYY-MM-DD') AS JKQX,(TRUNC(SYSDATE)-A.JKQX) AS CQTS, B.MC SBZSXM_MC, C.MC SBZSPM_MC   FROM DB_WSBS.T_GF_SBYZSJJDB A,        (SELECT * FROM DB_WSBS.T_DM_GY_ZSXMSB WHERE XY_BJ = '1') B,        (SELECT * FROM DB_WSBS.T_DM_GY_ZSPMSB WHERE XY_BJ = '1') C  WHERE SB_DM = ?    AND NVL(TO_CHAR(SSQ_QSRQ, 'YYYY-MM-DD'), '9999-99-99') = ?    AND NVL(TO_CHAR(SSQ_ZZRQ, 'YYYY-MM-DD'), '9999-99-99') = ?    AND A.SJZT = '0'    AND A.SBZSXM_DM = B.SBZSXM_DM    AND A.SBZSXM_DM = C.SBZSXM_DM    AND A.SBZSPM_DM = C.SBZSPM_DM",
						paramList);
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("XH", rs.getString("XH"));// 序号
			map.put("SB_DM", rs.getString("SB_DM"));// 社保代码
			map.put("SBZSXM_DM", rs.getString("SBZSXM_DM"));// 社保征收项目代码
			map.put("SBZSXM_MC", rs.getString("SBZSXM_MC"));// 社保征收项目名称
			map.put("SBZSPM_DM", rs.getString("SBZSPM_DM"));// 社保征收品目代码
			map.put("SBZSPM_MC", rs.getString("SBZSPM_MC"));// 社保征收品目名称
			map.put("YJ_JE", rs.getString("YJFE_JE"));// 应缴额
			map.put("SJ_JE", rs.getString("YJFE_JE"));// 实缴与应额一样
			map.put("JKQX", rs.getString("JKQX"));// 缴款期限
			map.put("CQTS", rs.getString("CQTS"));// 超期天数
			map.put("SSQ_QSRQ", rs.getString("SSQ_QSRQ"));// 所属期起始日期
			map.put("SSQ_ZZRQ", rs.getString("SSQ_ZZRQ"));// 所属期终止日期
			dataList.add(map);
			if (rs.getInt("CQTS") > 0) {
				str.append(rs.getString("SBZSPM_MC") + "已经超期"
						+ rs.getString("CQTS") + "天\r\n");
			}
		}
		if (str.toString().length() > 0) {
			responseEvent.respMapParam.put("cqTs", str.toString());
		}

		responseEvent.respMapParam.put("dataList", dataList);
		paramList = new ArrayList<Object>();
		paramList.add(gljgDm.substring(0, 7) + "0000'");
		rs = QueryCssBPO
				.findAll(
						conn,
						"SELECT CSZ FROM DB_WSBS.T_XT_XTCS WHERE CSBM='WB_SBF' AND SWJG_DM = ?",
						paramList);
		String csz = "1";
		while (rs.next()) {
			csz = rs.getString("CSZ");
		}
		responseEvent.respMapParam.put("csz", csz);
		return responseEvent;
	}

	protected ResponseEvent queryYsbLb(RequestEvent req, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String swglm = (String) dataMap.get("swglm");
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(swglm);
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		CachedRowSet rs = QueryCssBPO
				.findAll(
						conn,
						"SELECT A.PZ_XH, TO_CHAR(A.SFSSQ_QSRQ,'YYYY-MM-DD') AS SFSSQ_QSRQ, TO_CHAR(A.SFSSQ_ZZRQ,'YYYY-MM-DD') AS SFSSQ_ZZRQ,B.ZT_MC   FROM DB_WSBS.T_WB_SBFSBQK A,DB_WSBS.T_DM_WB_ZT B  WHERE TO_CHAR(A.LR_SJ, 'YYYY-MM') = TO_CHAR(SYSDATE, 'YYYY-MM')    AND SWGLM = ?    AND A.ZT=B.ZT_DM",
						paramList);
		CachedRowSet rs2;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("PZ_XH", rs.getString("PZ_XH"));
			map.put("ZT_MC", rs.getString("ZT_MC"));
			paramList = new ArrayList<Object>();
			paramList.add(rs.getString("PZ_XH"));
			rs2 = QueryCssBPO
					.findAll(
							conn,
							"SELECT SUM(T.SJ_JE) AS SJ_JE FROM DB_WSBS.T_WB_SBFSB T WHERE T.PZ_XH=?",
							paramList);
			rs2.next();
			map.put("SJ_JE", rs2.getString("SJ_JE"));
			map.put("SFSSQ_QSRQ", rs.getString("SFSSQ_QSRQ"));
			map.put("SFSSQ_ZZRQ", rs.getString("SFSSQ_ZZRQ"));
			dataList.add(map);
		}
		responseEvent.respMapParam.put("ysbList", dataList);
		return responseEvent;
	}

	protected ResponseEvent querySblb(RequestEvent req, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String swglm = (String) dataMap.get("swglm");
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(swglm);
		CachedRowSet rs = QueryCssBPO
				.findAll(
						conn,
						"SELECT DISTINCT A.SB_DM ,TO_CHAR(A.SSQ_QSRQ,'YYYY-MM-DD') AS SSQ_QSRQ,TO_CHAR(A.SSQ_ZZRQ,'YYYY-MM-DD') AS SSQ_ZZRQ FROM 		DB_WSBS.T_GF_SBYZSJJDB A ,DB_WSBS.T_GF_SBDJB B WHERE B.SWGLM = ? 		AND A.SB_DM = B.SB_DM AND A.SJZT = '0' AND B.WSSB_DM = '01' 		ORDER BY SSQ_QSRQ DESC",
						paramList);
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("SB_DM", rs.getString("SB_DM"));
			map.put("SSQ_QSRQ", rs.getString("SSQ_QSRQ"));
			map.put("SSQ_ZZRQ", rs.getString("SSQ_ZZRQ"));
			dataList.add(map);
		}
		responseEvent.respMapParam.put("wsbList", dataList);
		return responseEvent;
	}

	protected ResponseEvent saveData(RequestEvent req, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String sbDm = (String) dataMap.get("sbDm");
		String swglm = (String) dataMap.get("swglm");
		String ssQq = (String) dataMap.get("ssQq");
		String ssQz = (String) dataMap.get("ssQz");
		List<MorphDynaBean> dataList = (List<MorphDynaBean>) dataMap
				.get("dataList");
		StringBuffer sqlbuf = new StringBuffer();
		MorphDynaBean bean;
		ArrayList<Object> sqlParams = null;
		CachedRowSet rs = null;
		StringBuilder errorMsg = new StringBuilder();
		for (int i = 0; i < dataList.size(); i++) {
			bean = dataList.get(i);
			sqlParams = new ArrayList<Object>();
			sqlParams.add(bean.get("XH"));
			rs = QueryCssBPO
					.findAll(
							conn,
							"SELECT TO_CHAR(A.SSQ_QSRQ,'YYYY-MM-DD') AS SSQ_QSRQ, TO_CHAR(A.SSQ_ZZRQ,'YYYY-MM-DD') AS SSQ_ZZRQ, B.MC SBZSXM_MC, C.MC SBZSPM_MC   FROM DB_WSBS.T_GF_SBYZSJJDB A,        (SELECT * FROM DB_WSBS.T_DM_GY_ZSXMSB WHERE XY_BJ = '1') B,        (SELECT * FROM DB_WSBS.T_DM_GY_ZSPMSB WHERE XY_BJ = '1') C  WHERE A.XH = ?   AND A.SJZT = '1'    AND A.SBZSXM_DM = B.SBZSXM_DM    AND A.SBZSXM_DM = C.SBZSXM_DM    AND A.SBZSPM_DM = C.SBZSPM_DM",
							sqlParams);
			if (rs.next()) {
				errorMsg.append(rs.getString("SSQ_QSRQ")
						+ "&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("SSQ_ZZRQ")
						+ "&nbsp;&nbsp;&nbsp;&nbsp;"
						+ rs.getString("SBZSXM_MC")
						+ "&nbsp;&nbsp;&nbsp;&nbsp;"
						+ rs.getString("SBZSPM_MC"));
			}

		}
		if (errorMsg.length() > 0) {
			responseEvent.respMapParam.put(GeneralCons.CODE,
					GeneralCons.ERROR_CODE_ZB0014);
			responseEvent.respMapParam
					.put(GeneralCons.MSG, JyxxJgbHelper.queryJyxxx(ZBCoreHelper
							.getJyztMc(conn, GeneralCons.ERROR_CODE_ZB0014),
							errorMsg.toString()));
		}

		sqlParams = new ArrayList<Object>();
		sqlParams.add(swglm);
		sqlParams.add(sbDm);
		
		rs = QueryCssBPO
				.findAll(
						conn,
						"SELECT A.*, B.CSZ   FROM DB_WSBS.T_GF_SBDJB A, DB_WSBS.T_CS_WB_XTCS B, DB_WSBS.T_DM_GY_SWJG C  WHERE A.SWGLM = ?    AND A.SB_DM = ?    AND B.SWJGDM = C.SWJG_DM    AND B.SWJGDM IN (SELECT SWJG_DM                       FROM DB_WSBS.T_DM_GY_SWJG                     CONNECT BY PRIOR SJSWJG_DM = SWJG_DM                      START WITH SWJG_DM = A.ZSJG_DM)    AND B.CSBM = 'WB003'  ORDER BY C.JGJC DESC",
						sqlParams);

		rs.next();

		WBsbfsbqkVO zbvo = new WBsbfsbqkVO();
		zbvo.setPzxh(ZBCoreHelper.getGUID(conn));
		zbvo.setSwglm(swglm);
		zbvo.setSbdm(sbDm);
		zbvo.setNsrmc(rs.getString("DWMC"));
		zbvo.setNsrsbm(rs.getString("NSRSBM"));
		zbvo.setLxdh(rs.getString("DH"));
		zbvo.setZcdz(rs.getString("DZ"));
		zbvo.setStr_sfssqqsrq(ssQq);
		zbvo.setStr_sfssqzzrq(ssQz);
		zbvo.setGljgdm(rs.getString("GLJG_DM"));
		zbvo.setZsjgdm(rs.getString("ZSJG_DM"));
		zbvo.setHsjgdm(rs.getString("SKSSJG_DM"));
		zbvo.setJcjgdm(rs.getString("JCJG_DM"));
		zbvo.setSkssjgdm(rs.getString("SKSSJG_DM"));
		zbvo.setFwjgdm(rs.getString("FWJG_DM"));
		zbvo.setJiancjgdm(rs.getString("JIANCJG_DM"));
		zbvo.setDjzclxdm(rs.getString("ZCLX_DM"));
		zbvo.setLsgxdm(rs.getString("JC_DM"));
		zbvo.setDlwzxzdm(rs.getString("DLWZXZ_DM"));
		zbvo.setStr_tbrq(ZBCoreHelper.getDateTime(conn, "YYYY-MM-DD"));
		zbvo.setTjsj(DBTimeServer.getDBTime(conn));
		zbvo.setZt("1");
		//获取其他信息
		sqlbuf.append(" SELECT A.KGLX_DM, B.YH_ZH, C.MC YHMC, D.MC ZCLXMC, A.GBHY_DM GBHY ");
		sqlbuf.append(" FROM DB_WSBS.T_DJ_JGNSR A, (SELECT * FROM DB_WSBS.T_DJ_NSRYHZH ");
		sqlbuf.append(" WHERE YHZHLB_DM = '1' AND XY_BJ = '1' AND SH_BJ = '1' AND YX_ZZRQ IS NULL) B, ");
		sqlbuf.append(" (SELECT * FROM DB_WSBS.T_DM_GY_YH WHERE XY_BJ = '1') C, ");
		sqlbuf.append(" (SELECT * FROM DB_WSBS.T_DM_GY_DJZCLX) D ");
		sqlbuf.append(" WHERE A.SWGLM = B.SWGLM(+) AND B.YHHB_DM = C.YHHB_DM(+) ");
		sqlbuf.append(" AND B.YH_DM = C.YH_DM(+) AND A.DJZCLX_DM = D.DJZCLX_DM(+) ");
		sqlbuf.append(" AND A.SWGLM = B.SWGLM(+) ");
		sqlbuf.append(" AND A.SWGLM = ? ");
		sqlParams = new ArrayList<Object>();
		sqlParams.add(swglm);
		rs = QueryCssBPO.findAll(conn, sqlbuf.toString(), sqlParams);
		if(rs.next()){
			zbvo.setKglxdm(rs.getString("KGLX_DM"));
			zbvo.setYhzh(rs.getString("YH_ZH"));
			zbvo.setKhyhmc(rs.getString("YHMC"));
			zbvo.setZclxmc(rs.getString("ZCLXMC"));
			zbvo.setGbhydm(rs.getString("GBHY"));
			zbvo.setJnfs("网上申报");
		}
		WBsbfsbqkBPO.insert(conn, zbvo);

		// 记录掌上办税申报情况
		ZBSbbqkVO zbSbbqkVO = new ZBSbbqkVO();
		zbSbbqkVO.setPzxh(zbvo.getPzxh());
		zbSbbqkVO.setFwjgdm(zbvo.getFwjgdm());
		zbSbbqkVO.setGljgdm(zbvo.getGljgdm());
		zbSbbqkVO.setHsjgdm(zbvo.getHsjgdm());
		zbSbbqkVO.setJcjgdm(zbvo.getJcjgdm());
		zbSbbqkVO.setJiancjgdm(zbvo.getJiancjgdm());
		// 0自然人1企业
		zbSbbqkVO.setSjly("1");
		zbSbbqkVO.setSkssjgdm(zbvo.getSkssjgdm());
		zbSbbqkVO.setSwglm(zbvo.getSwglm());
		zbSbbqkVO.setZsjgdm(zbvo.getZsjgdm());
		ZBSbbqkBPO.deleteByPK(conn, zbSbbqkVO.getPzxh());
		ZBSbbqkBPO.insert(conn, zbSbbqkVO);

		String sql = "UPDATE DB_WSBS.T_GF_SBYZSJJDB SET SJZT='1' WHERE XH=?";
		PreparedStatement st = conn.prepareStatement(sql);
		// 保存明细表，同时更新应征数据信息表
		WBsbfsbVO sbfsbVO;
		for (int i = 0; i < dataList.size(); i++) {
			bean = dataList.get(i);
			sbfsbVO = new WBsbfsbVO();
			sbfsbVO.setSbdm(sbDm);
			sbfsbVO.setPzxh(zbvo.getPzxh());
			sbfsbVO.setSbmxxh(String.valueOf(i + 1));

			sqlParams = new ArrayList<Object>();
			sqlParams.add(bean.get("XH").toString());
			rs = QueryCssBPO
					.findAll(
							conn,
							"SELECT A.SBZSXM_DM,        A.SBZSPM_DM,        TO_CHAR(A.SSQ_QSRQ, 'YYYY-MM-DD') AS SSQ_QSRQ,        TO_CHAR(A.SSQ_ZZRQ, 'YYYY-MM-DD') AS SSQ_ZZRQ,        A.YJFE_JE   FROM DB_WSBS.T_GF_SBYZSJJDB A WHERE A.XH = ?    AND A.SJZT = '0'",
							sqlParams);
			rs.next();
			sbfsbVO.setSbzspmdm(rs.getString("SBZSPM_DM"));
			sbfsbVO.setSbzsxmdm(rs.getString("SBZSXM_DM"));
			sbfsbVO.setStr_sfssqqsrq(rs.getString("SSQ_QSRQ"));
			sbfsbVO.setStr_sfssqzzrq(rs.getString("SSQ_ZZRQ"));
			sbfsbVO.setSjje(rs.getString("YJFE_JE"));
			sbfsbVO.setYjje(rs.getString("YJFE_JE"));
			sbfsbVO.setYzxh((String) bean.get("XH"));
			sbfsbVO.setSwglm(swglm);
			WBsbfsbBPO.insert(conn, sbfsbVO);
			st.setString(1, sbfsbVO.getYzxh());
			st.executeUpdate();
		}
		st.close();
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg("提交成功!");
		return responseEvent;
	}

	@Override
	protected ResponseEvent validateData(RequestEvent req, Connection con)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
