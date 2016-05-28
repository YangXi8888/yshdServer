package gov.jslt.taxcore.taxblh.bsfw.ssbl.ssbl005;

import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxcore.taxbpo.bsfw.ssbl.ssbl005.Ssbl005sxfjbmxBPO;
import gov.jslt.taxcore.taxbpo.bsfw.ssbl.ssbl005.Ssbl005sxfjbzbBPO;
import gov.jslt.taxcore.taxbpo.comm.WBwsfwqkbBPO;
import gov.jslt.taxevent.bsfw.ssbl.ssbl005.Ssbl005sxfjbmxVO;
import gov.jslt.taxevent.bsfw.ssbl.ssbl005.Ssbl005sxfjbzbVO;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.StringUtil;
import gov.jslt.taxevent.comm.WBwsfwqkbVO;

import java.math.BigDecimal;
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
import com.ctp.core.log.LogWritter;
import com.ctp.core.utility.dbtime.DBTimeServer;
import com.ctp.cssesb.esbevent.ESBRequestEvent;

public class Ssbl005BLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		String delMethod = req.getDealMethod();
		if ("getJbxx".equals(delMethod)) {
			return getJbxx(req, con);
		} else if ("submitSqbxx".equals(delMethod)) {
			return submitSqbxx(req, con);
		} else if ("search".equals(delMethod)) {
			return search(req, con);
		} else if ("queryData".equals(delMethod)) {
			return queryData(req, con);
		}
		return null;
	}

	protected ResponseEvent getJbxx(RequestEvent req, Connection con)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String swglm = (String) dataMap.get("swglm").toString();
		try {
			Map resmap = ZBCoreHelper.getisNew(con, "SQ077", swglm);
			if ("0".equals(resmap.get("temp"))) {
				responseEvent.setRepCode("ZB0013");
				responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(con,
						"ZB0013"));
				return responseEvent;
			}
			LogWritter.sysError("开始调用ESB.......................");
			ESBRequestEvent baseRequest = new ESBRequestEvent("Ssbl005BLH", "",
					"");
			baseRequest.setReqMapParam(new HashMap<>());
			baseRequest.setDealMethod("getJbxx");
			baseRequest.reqMapParam.put("swglm", (String) dataMap.get("swglm")
					.toString());
			responseEvent = ZBCoreHelper.getEsbResponseEvent(baseRequest, con);
		} catch (Exception e) {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					ZBCoreHelper.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
					e.getMessage()));
			return responseEvent;
		}
		return responseEvent;
	}

	// 手续费保存
	protected ResponseEvent submitSqbxx(RequestEvent req, Connection conn)
			throws SQLException {
		ResponseEvent reEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String swglm = (String) dataMap.get("swglm");
		String yddm = (String) dataMap.get("yd_dm");
		String mc = (String) dataMap.get("yh_mc");
		String yhzh = (String) dataMap.get("yh_zh");
		String lxr = jsonReqData.getXm();
		String lxdh = jsonReqData.getSjHm();
		String gljgdm = (String) dataMap.get("gljgDm");
		String lsh = ZBCoreHelper.getWsh(swglm);
		WBwsfwqkbVO qkvo = new WBwsfwqkbVO();
		qkvo.setLsh(lsh);
		qkvo.setSqlxdm("SQ077");
		qkvo.setSssqdl("0110");
		qkvo.setSssqxl("01100070");
		qkvo.setSwglm(swglm);
		qkvo.setZt("9");
		qkvo.setSslx("0");
		qkvo.setCarz("0");
		qkvo.setLxdh(lxdh);
		qkvo.setLxr(lxr);
		qkvo.setGljgdm(gljgdm);
		qkvo.setSsxmdm("01100070");
		qkvo.setSqsj(DBTimeServer.getDBTime(conn));
		WBwsfwqkbBPO.insert(conn, qkvo);

		Ssbl005sxfjbzbVO zbvo = new Ssbl005sxfjbzbVO();
		String pzdm = ZBCoreHelper.getGUID(conn);
		zbvo.setPzdm(pzdm);
		zbvo.setLsh(lsh);
		zbvo.setSlr(swglm);
		zbvo.setSlsj(DBTimeServer.getDBTime(conn));
		zbvo.setYhdm(yddm);
		zbvo.setMc(mc);
		zbvo.setYhzh(yhzh);
		Ssbl005sxfjbzbBPO.insert(conn, zbvo);// 插入外网手续费主表信息
		List<MorphDynaBean> listmx = (List<MorphDynaBean>) dataMap
				.get("listmxxx");
		MorphDynaBean bean;
		if (listmx != null) {
			for (int i = 0; i < listmx.size(); i++) {
				Ssbl005sxfjbmxVO mxvo = new Ssbl005sxfjbmxVO();
				bean = listmx.get(i);
				mxvo.setLsh(lsh);
				mxvo.setSwglm(zbvo.getSlr());
				mxvo.setPzdm(zbvo.getPzdm());
				mxvo.setMxxh(String.valueOf(i + 1));
				mxvo.setJkxh((String) bean.get("jkxh"));
				mxvo.setJklsmxhxh((String) bean.get("jkmxxh"));
				mxvo.setDzsphxh((String) bean.get("dzsph"));
				mxvo.setLybz((String) bean.get("lybz"));
				mxvo.setPzdm(pzdm);
				mxvo.setStr_sfssqqsrq((String) bean.get("ssqq"));
				mxvo.setStr_sfssqzzrq((String) bean.get("sszq"));
				mxvo.setZsxmdm((String) bean.get("zsxmdm"));
				mxvo.setZspmdm((String) bean.get("zspmdm"));
				mxvo.setSkje((String) bean.get("se"));
				mxvo.setStr_gsrq((String) bean.get("rq"));
				mxvo.setLqbl((String) bean.get("lqbl"));
				mxvo.setSkzhid((String) bean.get("skzhid"));
				mxvo.setYsfpbldm((String) bean.get("ysfpbldm"));
				mxvo.setSkssjgdm((String) bean.get("skssjgdm"));
				Ssbl005sxfjbmxBPO.insert(conn, mxvo);// 插入外网手续费明细表信息
			}
		}
		reEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		reEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return reEvent;
	}

	protected ResponseEvent search(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String swglm = (String) dataMap.get("swglm");
		ResponseEvent responseEvent = new ResponseEvent();
		Map<String, String> map;
		List ZtList = (List) ZBCoreHelper.getZt(con, swglm, "SQ077");
		responseEvent.respMapParam.put("ZtList", ZtList);
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	protected ResponseEvent queryData(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		List<Map<String, String>> mxList = new ArrayList<Map<String, String>>();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		ResponseEvent responseEvent = new ResponseEvent();
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(dataMap.get("lsh").toString());
		Map<String, String> map;
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT T.DZSPH_XH,T.ZSPM_DM,TO_CHAR(T.GS_RQ,'YYYY-MM-DD') GS_RQ ,T.SK_JE,T.LQBL,PM.MC_J FROM  DB_WSBS.T_WB_KT_SXFLQMXXX T,DB_WSBS.T_DM_GY_ZSPM PM  WHERE T.LSH = ? AND PM.ZSPM_DM=T.ZSPM_DM AND PM.ZSXM_DM=T.ZSXM_DM ORDER BY GS_RQ",
						paramList);
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("dzsph_xh", rs.getString("dzsph_xh"));
			map.put("gs_rq", rs.getString("gs_rq"));
			map.put("sk_je", rs.getString("sk_je"));
			map.put("lqbl", rs.getString("lqbl"));
			map.put("MC_J", rs.getString("MC_J"));
			mxList.add(map);
		}

		responseEvent.respMapParam.put("mxList", mxList);
		responseEvent.respMapParam.put("rkRqq", mxList.get(0).get("gs_rq"));
		responseEvent.respMapParam.put("rkRqz", mxList.get(mxList.size() - 1)
				.get("gs_rq"));

		paramList = new ArrayList<Object>();
		paramList.add(dataMap.get("lsh").toString());

		rs = QueryCssBPO
				.findAll(
						con,
						"SELECT  T.YH_DM,T.YH_ZH,T.MC FROM DB_WSBS.T_WB_KT_SXFLQZBXX T  WHERE T.LSH = ?",
						paramList);
		if (rs.next()) {
			responseEvent.respMapParam.put("yh_dm", rs.getString("yh_dm"));
			responseEvent.respMapParam.put("yh_zh", rs.getString("yh_zh"));
			responseEvent.respMapParam.put("mc", rs.getString("mc"));
		}
		paramList = new ArrayList<Object>();
		paramList.add(dataMap.get("lsh").toString());

		rs = QueryCssBPO
				.findAll(
						con,
						"SELECT NVL(SUM(X.SK_JE),0) AS RKJEHJ 		  FROM DB_WSBS.T_WB_KT_SXFLQMXXX X 		 WHERE X.LSH = ? 		   AND X.LY_BZ = '0' ",
						paramList);

		rs.next();
		responseEvent.respMapParam.put("rkjeHj", rs.getString("rkjeHj"));

		paramList = new ArrayList<Object>();
		paramList.add(dataMap.get("lsh").toString());

		rs = QueryCssBPO
				.findAll(
						con,
						"SELECT NVL(SUM(X.SK_JE),0) AS TKJEHJ 		  FROM DB_WSBS.T_WB_KT_SXFLQMXXX X 		 WHERE X.LSH = ? 		   AND X.LY_BZ = '1' ",
						paramList);

		rs.next();

		responseEvent.respMapParam.put("tkjeHj", rs.getString("tkjeHj"));

		paramList = new ArrayList<Object>();
		paramList.add(dataMap.get("lsh").toString());

		rs = QueryCssBPO
				.findAll(
						con,
						"SELECT NVL(SUM(X.SK_JE),0) AS SKJEHJ 		  FROM DB_WSBS.T_WB_KT_SXFLQMXXX X 		 WHERE X.LSH = ?  ",
						paramList);

		rs.next();

		responseEvent.respMapParam.put("skjeHj", rs.getString("SKJEHJ"));

		paramList = new ArrayList<Object>();
		paramList.add(dataMap.get("lsh").toString());

		rs = QueryCssBPO
				.findAll(
						con,
						"SELECT ROUND(SUM(X.SK_JE * X.LQBL / 100), 2) AS RKSXFJE		  FROM DB_WSBS.T_WB_KT_SXFLQMXXX X 		 WHERE X.LSH =?		   AND X.LY_BZ = '0'",
						paramList);

		rs.next();
		BigDecimal rkSxfJe = new BigDecimal(rs.getString("RKSXFJE"));

		paramList = new ArrayList<Object>();
		paramList.add(dataMap.get("lsh").toString());
		rs = QueryCssBPO
				.findAll(
						con,
						"SELECT ROUND(SUM(X.SK_JE * X.LQBL / 100), 2) AS TKSXFJE		  FROM DB_WSBS.T_WB_KT_SXFLQMXXX X 		 WHERE X.LSH =?		   AND X.LY_BZ = '1'",
						paramList);

		rs.next();
		BigDecimal tkSxfJe = new BigDecimal(StringUtil.emptyToZe(rs
				.getString("TKSXFJE")));

		responseEvent.respMapParam.put("sxfHjJe", rkSxfJe.subtract(tkSxfJe)
				.doubleValue());

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
