package gov.jslt.taxcore.taxblh.bsfw.cxfw.cx002;

import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.MD5Helper;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.cssesb.esbevent.ESBRequestEvent;

public class Cxfw002BLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		String delMethod = req.getDealMethod();
		if ("gsxxcx".equals(delMethod)) {
			return gsxxcx(req, con);
		} else if ("qsxxcx".equals(delMethod)) {
			return qsxxcx(req, con);
		} else if ("ccsxxcx".equals(delMethod)) {
			return ccsxxcx(req, con);
		} else if ("queryNsqk".equals(delMethod)) {
			return queryNsqk(req, con);
		}
		return null;
	}

	private ResponseEvent queryNsqk(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		String zjlxDm = (String) jsonReqData.getData().get("zjlxDm");
		String zjHm = (String) jsonReqData.getData().get("zjHm");
		ESBRequestEvent reqEvent = new ESBRequestEvent("Cxfw002BLH", "", "");
		reqEvent.setSjb("");
		reqEvent.setDealMethod("queryNsqk");
		reqEvent.setReqMapParam(new HashMap<>());
		reqEvent.reqMapParam.put("zjlxDm", zjlxDm);
		reqEvent.reqMapParam.put("zjHm", zjHm);
		try {
			responseEvent = ZBCoreHelper.getEsbResponseEvent(reqEvent, con);
			Map<String, List<Map<String, String>>> mxData = new HashMap<String, List<Map<String, String>>>();// 折线图数据
			Map<String, List<Map<String, String>>> xmData = new HashMap<String, List<Map<String, String>>>();// 饼图数据
			Map<String, List<Map<String, String>>> selData = new HashMap<String, List<Map<String, String>>>();// 下拉框数据
			List<HashMap<String, String>> dmData = new ArrayList<HashMap<String, String>>();// 下拉框数据
			if (null != responseEvent.respMapParam.get("list")) {
				@SuppressWarnings("unchecked")
				List<HashMap<String, String>> returnList = (List<HashMap<String, String>>) responseEvent.respMapParam
						.get("list");

				HashMap<String, String> tempMap = null;
				for (int i = 0; i < returnList.size(); i++) {
					tempMap = returnList.get(i);
					String key = "";
					String lable = "";
					String skSsq = tempMap.get("skSsq");
					String ssq1 = skSsq.split("-")[0] + "|1";// 上半年key
					String ssq2 = skSsq.split("-")[0] + "|2";// 下半年key
					// 判断月份是否在上半年
					if (Integer.parseInt(skSsq.split("-")[1]) <= 6) {
						key = ssq1;
						lable = skSsq.split("-")[0] + "上半年 ";
					} else {
						key = ssq2;
						lable = skSsq.split("-")[0] + "下半年 ";
					}
					if (!mxData.containsKey(key)) {
						ArrayList<Map<String, String>> tempList = new ArrayList<Map<String, String>>();
						tempList.add(tempMap);
						mxData.put(key, tempList);
						// 年份下拉框数据
						if (!selData.containsKey("selItems")) {
							ArrayList<Map<String, String>> selItems = new ArrayList<Map<String, String>>();
							tempMap = new HashMap<String, String>();
							tempMap.put("value", key);
							tempMap.put("lable", lable);
							selItems.add(tempMap);
							selData.put("selItems", selItems);
						} else {
							tempMap = new HashMap<String, String>();
							tempMap.put("value", key);
							tempMap.put("lable", lable);
							((ArrayList<Map<String, String>>) selData
									.get("selItems")).add(tempMap);
						}
					} else {
						((ArrayList<Map<String, String>>) mxData.get(key))
								.add(tempMap);
					}
				}

				if (null != responseEvent.respMapParam.get("sdMxList")) {
					@SuppressWarnings("unchecked")
					List<HashMap<String, String>> sdMxList = (List<HashMap<String, String>>) responseEvent.respMapParam
							.get("sdMxList");

					List<Map<String, String>> tmpXmList = null;
					Map<String, String> tmpXmMap = null;
					for (int i = 0; i < sdMxList.size(); i++) {
						tmpXmMap = sdMxList.get(i);
						// 半年所有项目收入明细
						List<Map<String, String>> bnSrList = mxData
								.get(tmpXmMap.get("skSsq"));
						BigDecimal bnHjSr = new BigDecimal("0");// 半年合计收入
						BigDecimal bnHjSe = new BigDecimal("0");// 半年合计税额
						BigDecimal xmSrBl = new BigDecimal("0");// 所得项目收入比例
						BigDecimal xmSeBl = new BigDecimal("0");// 所得项目税额比例
						// 计算半年所有项目收入合计和税额合计
						for (int j = 0; j < bnSrList.size(); j++) {
							bnHjSr = bnHjSr.add(new BigDecimal(bnSrList.get(j)
									.get("hjSre")));
							bnHjSe = bnHjSe.add(new BigDecimal(bnSrList.get(j)
									.get("ybTse")));
						}
						// 半年项目合计除以半年所有项目合计得到比例
						if (!"0".equals(tmpXmMap.get("hjSre"))) {
							xmSrBl = new BigDecimal(tmpXmMap.get("hjSre"))
									.divide(bnHjSr, 2);
						}
						if (!"0".equals(tmpXmMap.get("ybTse"))) {
							xmSeBl = new BigDecimal(tmpXmMap.get("ybTse"))
									.divide(bnHjSe);
						}
						tmpXmMap.put("srBl", xmSrBl.setScale(2, 4).toString());
						tmpXmMap.put("seBl", xmSeBl.setScale(2, 4).toString());

						if (!xmData.containsKey(tmpXmMap.get("skSsq"))) {
							tmpXmList = new ArrayList<Map<String, String>>();
							tmpXmList.add(tmpXmMap);
							xmData.put(tmpXmMap.get("skSsq"), tmpXmList);
						} else {
							xmData.get(tmpXmMap.get("skSsq")).add(tmpXmMap);
						}
					}
				}
				responseEvent.respMapParam.put("mxData", mxData);
				responseEvent.respMapParam.put("xmData", xmData);
				responseEvent.respMapParam.put("selData", selData);
			}
			if (null != responseEvent.respMapParam.get("dmList")) {
				dmData = (ArrayList<HashMap<String, String>>) responseEvent.respMapParam
						.get("dmList");
				responseEvent.respMapParam.put("dmData", dmData);
			}
		} catch (Exception e) {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					ZBCoreHelper.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
					e.getMessage()));
			return responseEvent;
		}
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;

	}

	// 车船税信息查询
	protected ResponseEvent ccsxxcx(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String cxnf = dataMap.get("cxnf").toString();
		String sfzhm = dataMap.get("sfzhm").toString();
		String sfzlxdm = dataMap.get("sfzlxdm").toString();
		/*
		 * String sjhm = jsonReqData.getSjHm();// 手机号码 String passWord =
		 * jsonReqData.getPassWord(); Map<String, Object> sqlMap = new
		 * HashMap<String, Object>(); sqlMap.put("sjhm", sjhm);
		 * sqlMap.put("zjlx", sfzlxdm); sqlMap.put("zjhm", sfzhm);
		 * sqlMap.put("passWord", MD5Helper.MD5(passWord)); boolean hasUser =
		 * ZBCoreHelper.getUserInfo(con, sqlMap); if (!hasUser) {
		 * responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB0006);
		 * responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(con,
		 * GeneralCons.ERROR_CODE_ZB0006)); return responseEvent; }
		 */
		// 车船税信息查询
		ESBRequestEvent reqEvent = new ESBRequestEvent("Cxfw002BLH", "", "");
		reqEvent.setSjb("");
		reqEvent.setDealMethod("ccsxxcx");
		reqEvent.setReqMapParam(new HashMap<>());
		reqEvent.reqMapParam.put("qsrq", Integer.valueOf(cxnf) - 1 + "/12/31");
		reqEvent.reqMapParam.put("zzrq", Integer.valueOf(cxnf) + 1 + "/1/1");
		reqEvent.reqMapParam.put("sfzmc", jsonReqData.getXm());
		reqEvent.reqMapParam.put("sfzhm", sfzhm);
		try {
			responseEvent = ZBCoreHelper.getEsbResponseEvent(reqEvent, con);
		} catch (Exception e) {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					ZBCoreHelper.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
					e.getMessage()));
			return responseEvent;
		}
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	// 契税信息查询
	protected ResponseEvent qsxxcx(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String cxnf = dataMap.get("cxnf").toString();
		String sfzhm = dataMap.get("sfzhm").toString();
		String sfzlxdm = dataMap.get("sfzlxdm").toString();
		String sjhm = jsonReqData.getSjHm();// 手机号码
		String passWord = jsonReqData.getPassWord();
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put("sjhm", sjhm);
		sqlMap.put("zjlx", sfzlxdm);
		sqlMap.put("zjhm", sfzhm);
		sqlMap.put("passWord", MD5Helper.MD5(passWord));
		boolean hasUser = ZBCoreHelper.getUserInfo(con, sqlMap);
		if (!hasUser) {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB0006);
			responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(con,
					GeneralCons.ERROR_CODE_ZB0006));
			return responseEvent;
		}
		// 契税信息查询
		ESBRequestEvent reqEvent = new ESBRequestEvent("Cxfw002BLH", "", "");
		reqEvent.setSjb("");
		reqEvent.setDealMethod("qsxxcx");
		reqEvent.setReqMapParam(new HashMap<>());
		reqEvent.reqMapParam.put("qsrq", Integer.valueOf(cxnf) - 1 + "/12/31");
		reqEvent.reqMapParam.put("zzrq", Integer.valueOf(cxnf) + 1 + "/1/1");
		reqEvent.reqMapParam.put("sfzhm", sfzhm);
		reqEvent.reqMapParam.put("xm", jsonReqData.getXm());
		try {
			responseEvent = ZBCoreHelper.getEsbResponseEvent(reqEvent, con);
		} catch (Exception e) {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					ZBCoreHelper.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
					e.getMessage()));
			return responseEvent;
		}
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	protected ResponseEvent gsxxcx(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String sfzhm = dataMap.get("sfzhm").toString();
		String nf = dataMap.get("nf").toString();
		String sfzlxdm = dataMap.get("sfzlxdm").toString();
		String sfzhm_15 = sfzhm;
		if ("06".equals(sfzlxdm) && sfzlxdm.length() == 18) {
			sfzhm_15 = sfzhm.substring(0, 6) + sfzhm.substring(8, 17);
		}
		// 新版本已经增加加密校验，去掉原先用户校验
		// 调用ESB
		ESBRequestEvent esbRequestEvent = new ESBRequestEvent("Cxfw002BLH", "",
				"");
		esbRequestEvent.setDealMethod("gsxxcx");
		esbRequestEvent.setReqMapParam(new HashMap<>());
		esbRequestEvent.reqMapParam.put("sfzhm", sfzhm);
		esbRequestEvent.reqMapParam.put("nf", nf);
		esbRequestEvent.reqMapParam.put("sfzlxdm", sfzlxdm);
		esbRequestEvent.reqMapParam.put("sfzhm_15", sfzhm_15);
		try {
			ResponseEvent esbresponseEvent = ZBCoreHelper.getEsbResponseEvent(
					esbRequestEvent, con);
			responseEvent.respMapParam.put("gsxxList",
					esbresponseEvent.respMapParam.get("gsxxList"));
		} catch (Exception e) {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					ZBCoreHelper.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
					e.getMessage()));
			return responseEvent;
		}
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;

		// ArrayList<Object> sqlParams = new ArrayList<Object>();
		// sqlParams.add(new StoredProcParamObj(1, dataMap.get("nf"),
		// StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		// sqlParams.add(new StoredProcParamObj(2, dataMap.get("sfzlxdm"),
		// StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		// sqlParams.add(new StoredProcParamObj(3,
		// dataMap.get("sfzhm").toString()
		// .toUpperCase(), StoredProcParamObj.IN, java.sql.Types.VARCHAR));
		// StoredProcManager.callStoreProcess(con,
		// "{call P_ZB_CXFW002_GSXX(?,?,?)}", sqlParams);
		// CachedRowSet rs = QueryCssBPO
		// .findAll(
		// con,
		// "SELECT T.* FROM DB_ZSBS.T_LS_CXFW002_GSCX  T ORDER BY T.SF_SSQQ",
		// null);
		// List<Map<String, String>> gsxxList = new ArrayList<Map<String,
		// String>>();
		// Map<String, String> map;
		// while (rs.next()) {
		// map = new HashMap<String, String>();
		// map.put("ZSPM_MC", rs.getString("ZSPM_MC"));
		// map.put("SF_SSQQ", rs.getString("SF_SSQQ"));
		// map.put("SF_SSQZ", rs.getString("SF_SSQZ"));
		// map.put("SR_JE", rs.getString("SR_JE"));
		// map.put("YINGNSSD_JE", rs.getString("YINGNSSD_JE"));
		// map.put("SL", rs.getString("SL"));
		// map.put("YBTSEHDJ_JE", rs.getString("YBTSEHDJ_JE"));
		// gsxxList.add(map);
		// }
		// responseEvent.respMapParam.put("gsxxList", gsxxList);
		// rs = QueryCssBPO.findAll(con,
		// "SELECT T.* FROM DB_ZSBS.T_ZB_LS_JYZT T",
		// null);
		// rs.next();
		// responseEvent.setRepCode(rs.getString("JYZT_DM"));
		// responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
		// rs.getString("JYZT_MC"), rs.getString("SJNR")));
		// return responseEvent;
	}

	@Override
	protected ResponseEvent validateData(RequestEvent req, Connection con)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
