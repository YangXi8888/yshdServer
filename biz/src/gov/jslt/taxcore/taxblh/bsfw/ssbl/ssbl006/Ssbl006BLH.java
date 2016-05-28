package gov.jslt.taxcore.taxblh.bsfw.ssbl.ssbl006;

import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxcore.taxbpo.bsfw.ssbl.ssbl006.WBksbgdjsqBPO;
import gov.jslt.taxcore.taxbpo.comm.WBwsfwqkbBPO;
import gov.jslt.taxevent.bsfw.ssbl.ssbl006.WBksbgdjsqVO;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.StringUtil;
import gov.jslt.taxevent.comm.WBwsfwqkbVO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.bpo.QueryCssBPO;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.core.utility.dbtime.DBTimeServer;
import com.f1j.ss.report.xml.cw;
import com.f1j.stdgui.ss.qk;

public class Ssbl006BLH extends BaseBizLogicHandler{

	@Override
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		String dealMethod = req.getDealMethod();
		if("initForm".equals(dealMethod)){
			return initForm(req,con);
		}else if("submitData".equals(dealMethod)){
			return submitData(req,con);
		}else if("queryData".equals(dealMethod)){
			return queryData(req,con);
		}
		return null;
	}
	
	protected ResponseEvent queryData(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException{
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam.get("JsonReqData");
		Map<String,Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams=new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT K.CFFZR_DH_X,K.CFFZR_SJ_X, ");
		sql.append(" K.BSY_XM_X,K.BSY_ZJZL_X,K.BSY_SFZH_X,K.BSY_DH_X, ");
		sql.append(" K.BSY_SJ_X,K.BSY_XM_X2,K.BSY_ZJZL_X2,K.BSY_SFZH_X2, ");
		sql.append(" K.BSY_DH_X2,K.BSY_SJ_X2,K.BSY_XM_X3,K.BSY_ZJZL_X3, ");
		sql.append(" K.BSY_SFZH_X3,K.BSY_DH_X3,K.BSY_SJ_X3,k.sq_ly ");
		sql.append(" FROM DB_WSBS.T_WSFW_KSBGDJSQ K WHERE K.LSH = ? ");
		sqlParams.add(dataMap.get("lsh"));
		CachedRowSet rs = QueryCssBPO.findAll(con, sql.toString(), sqlParams);
		if(rs.next()){
			responseEvent.respMapParam.put("cwfzrgddhx1", StringUtil.empty(rs.getString("CFFZR_DH_X")));//财务负责人1固定电话
			responseEvent.respMapParam.put("cwfzryddhx1", StringUtil.empty(rs.getString("CFFZR_SJ_X")));//财务负责人1移动电话
			responseEvent.respMapParam.put("bsryxmx1", StringUtil.empty(rs.getString("BSY_XM_X")));//办税人员1姓名
			responseEvent.respMapParam.put("bsryzjlxx1", StringUtil.empty(rs.getString("BSY_ZJZL_X")));//办税人员1证件类型
			responseEvent.respMapParam.put("bsryzjhx1", StringUtil.empty(rs.getString("BSY_SFZH_X")));//办税人员1证件号码
			responseEvent.respMapParam.put("bsrygddhx1", StringUtil.empty(rs.getString("BSY_DH_X")));//办税人员1固定电话
			responseEvent.respMapParam.put("bsryyddhx1", StringUtil.empty(rs.getString("BSY_SJ_X")));//办税人员1移动电话
			responseEvent.respMapParam.put("bsryxmx2", StringUtil.empty(rs.getString("BSY_XM_X2")));//办税人员2姓名
			responseEvent.respMapParam.put("bsryzjlxx2", StringUtil.empty(rs.getString("BSY_ZJZL_X2")));//办税人员2证件类型
			responseEvent.respMapParam.put("bsryzjhx2", StringUtil.empty(rs.getString("BSY_SFZH_X2")));//办税人员2证件号码
			responseEvent.respMapParam.put("bsrygddhx2", StringUtil.empty(rs.getString("BSY_DH_X2")));//办税人员2固定电话
			responseEvent.respMapParam.put("bsryyddhx2", StringUtil.empty(rs.getString("BSY_SJ_X2")));//办税人员2移动电话
			responseEvent.respMapParam.put("bsryxmx3", StringUtil.empty(rs.getString("BSY_XM_X3")));//办税人员3姓名
			responseEvent.respMapParam.put("bsryzjlxx3", StringUtil.empty(rs.getString("BSY_ZJZL_X3")));//办税人员3证件类型
			responseEvent.respMapParam.put("bsryzjhx3", StringUtil.empty(rs.getString("BSY_SFZH_X3")));//办税人员3证件号码
			responseEvent.respMapParam.put("bsrygddhx3", StringUtil.empty(rs.getString("BSY_DH_X3")));//办税人员3固定电话
			responseEvent.respMapParam.put("bsryyddhx3", StringUtil.empty(rs.getString("BSY_SJ_X3")));//办税人员3移动电话
			responseEvent.respMapParam.put("sqly", StringUtil.empty(rs.getString("sq_ly")));
			
			responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
			responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		}else{
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB0022);
			responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(con, GeneralCons.ERROR_CODE_ZB0022));
		}
		return responseEvent;
	}

	protected ResponseEvent submitData(RequestEvent req, Connection con) 
			throws SQLException, TaxBaseBizException{
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam.get("JsonReqData");
		Map<String,Object> dataMap = jsonReqData.getData();
		String lsh = ZBCoreHelper.getWsh(dataMap.get("swglm").toString());
		//申报情况
		WBwsfwqkbVO qkbVo = new WBwsfwqkbVO();
		qkbVo.setLsh(lsh);
		qkbVo.setLxdh(jsonReqData.getSjHm());
		qkbVo.setLxr(jsonReqData.getXm());
		qkbVo.setCarz("0");
		qkbVo.setSslx("0");
		qkbVo.setSqlxdm("SQQQQ");
		qkbVo.setSsxmdm("000000");
		qkbVo.setSwglm(dataMap.get("swglm").toString());
		qkbVo.setGljgdm(dataMap.get("gljgdm").toString());
		qkbVo.setZt("9");//提交
		qkbVo.setSqsj(DBTimeServer.getDBTime(con));
		WBwsfwqkbBPO.insert(con, qkbVo);
		//快速变更登记申请
		WBksbgdjsqVO ksbgdjsqVo = new WBksbgdjsqVO();
		ksbgdjsqVo.setLsh(lsh);
		ksbgdjsqVo.setSqly(dataMap.get("sqly").toString());//申请理由
		ksbgdjsqVo.setJbrxm(jsonReqData.getXm());//经办人姓名
		ksbgdjsqVo.setSwglm(dataMap.get("swglm").toString());
		ksbgdjsqVo.setNsrmc(dataMap.get("nsrmc").toString());//纳税人名称
		String sql = "SELECT A.SJJYDZ, A.SCJYDLXDH, A.SJJYDZYB, B.QYKJZD_DM, B.WZ FROM DB_WSBS.T_DJ_JGNSR A, DB_WSBS.T_DJ_JGNSRFB B WHERE A.SWGLM = ? AND A.SWGLM = B.SWGLM";
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(dataMap.get("swglm"));
		CachedRowSet rs = QueryCssBPO.findAll(con, sql, sqlParams);
		while(rs.next()){
			//经营地址
			ksbgdjsqVo.setJydzy(rs.getString("SJJYDZ"));
			ksbgdjsqVo.setJydzx(rs.getString("SJJYDZ"));
			//经营地联系电话
			ksbgdjsqVo.setJydzlxdhy(rs.getString("SCJYDLXDH"));
			ksbgdjsqVo.setJydzlxdhx(rs.getString("SCJYDLXDH"));
			//经营地邮政编码
			ksbgdjsqVo.setJydzyby(rs.getString("SJJYDZYB"));
			ksbgdjsqVo.setJydzybx(rs.getString("SJJYDZYB"));
			//适用会计制度
			ksbgdjsqVo.setKjzly(rs.getString("QYKJZD_DM"));
			ksbgdjsqVo.setKjzlx(rs.getString("QYKJZD_DM"));
			//会计电算化软件名称
			ksbgdjsqVo.setKjdshrjy("");
			ksbgdjsqVo.setKjdshrjx("");
			//公司网址
			ksbgdjsqVo.setGswzy(rs.getString("WZ"));
			ksbgdjsqVo.setGswzx(rs.getString("WZ"));
		}
		sql = "select distinct a.XM,a.zjlx_dm, a.ZJH, a.GDDH, a.YD_DH, a.email, a.RYLX_DM from DB_WSBS.t_Dj_Ryqkb a where a.swglm = ? and (RYLX_DM = ? OR RYLX_DM = ? OR RYLX_DM = ?)";
		sqlParams = new ArrayList<Object>();
		sqlParams.add(dataMap.get("swglm"));
		sqlParams.add("05");
		sqlParams.add("06");
		sqlParams.add("07");
		rs = QueryCssBPO.findAll(con, sql, sqlParams);
		int cwfzrNum = 0;//财务负责人计数器
		int bsryNum = 0;//办税人员计数器
		while(rs.next()){
			// 05:法定代表人（负责人或业主） 06:财务负责人 07:办税人员
			if("05".equals(rs.getString("RYLX_DM"))){
				// 企业负责人姓名
				ksbgdjsqVo.setQyfzrxmy(rs.getString("XM"));
				ksbgdjsqVo.setQyfzrxmx(rs.getString("XM"));
				// 企业负责人职务
				ksbgdjsqVo.setQyfzrzwy("");
				ksbgdjsqVo.setQyfzrzwx("");
				// 企业负责人证件种类
				ksbgdjsqVo.setQyfzrzjzly(rs.getString("zjlx_dm"));
				ksbgdjsqVo.setQyfzrzjzlx(rs.getString("zjlx_dm"));
				// 企业负责人证件号码
				ksbgdjsqVo.setQyfzrzjhmy(rs.getString("ZJH"));
				ksbgdjsqVo.setQyfzrzjhmx(rs.getString("ZJH"));
				// 企业负责人固定电话
				ksbgdjsqVo.setQyfzrgddhy(rs.getString("GDDH"));
				ksbgdjsqVo.setQyfzrgddhx(rs.getString("GDDH"));
				// 企业负责人移动电话
				ksbgdjsqVo.setQyfzryddhy(rs.getString("YD_DH"));
				ksbgdjsqVo.setQyfzryddhx(rs.getString("YD_DH"));
				// 企业负责人电子邮箱E-mail
				ksbgdjsqVo.setQyfzremaily(rs.getString("email"));
				ksbgdjsqVo.setQyfzremailx(rs.getString("email"));
			}else if ("06".equals(rs.getString("RYLX_DM"))) {
				if (cwfzrNum == 0) {
					// 财务负责人1姓名
					ksbgdjsqVo.setCffzrxmy(rs.getString("XM"));
					ksbgdjsqVo.setCffzrxmx(rs.getString("XM"));
					// 财务负责人1职务
					ksbgdjsqVo.setCffzrzwy("");
					ksbgdjsqVo.setCffzrzwx("");
					// 财务负责人1证件种类
					ksbgdjsqVo.setCffzrzjzly(rs.getString("zjlx_dm"));
					ksbgdjsqVo.setCffzrzjzlx(rs.getString("zjlx_dm"));
					// 财务负责人1身份证号
					ksbgdjsqVo.setCffzrsfzhy(rs.getString("ZJH"));
					ksbgdjsqVo.setCffzrsfzhx(rs.getString("ZJH"));
					// 财务负责人1电话
					ksbgdjsqVo.setCffzrdhy(rs.getString("GDDH"));
					ksbgdjsqVo.setCffzrdhx(dataMap.get("cwfzrgddhx1").toString());
					// 财务负责人1手机
					ksbgdjsqVo.setCffzrsjy(rs.getString("YD_DH"));
					ksbgdjsqVo.setCffzrsjx(dataMap.get("cwfzryddhx1").toString());
					// 财务负责人1电子邮箱E-mail
					ksbgdjsqVo.setCffzremaily(rs.getString("email"));
					ksbgdjsqVo.setCffzremailx(rs.getString("email"));
				} else if (cwfzrNum == 1) {
					// 财务负责人2姓名
					ksbgdjsqVo.setCffzrxmy2(rs.getString("XM"));
					ksbgdjsqVo.setCffzrxmx2(rs.getString("XM"));
					// 财务负责人2职务
					ksbgdjsqVo.setCffzrzwy2("");
					ksbgdjsqVo.setCffzrzwx2("");
					// 财务负责人2证件种类
					ksbgdjsqVo.setCffzrzjzly2(rs.getString("zjlx_dm"));
					ksbgdjsqVo.setCffzrzjzlx2(rs.getString("zjlx_dm"));
					// 财务负责人2身份证号
					ksbgdjsqVo.setCffzrsfzhy2(rs.getString("ZJH"));
					ksbgdjsqVo.setCffzrsfzhx2(rs.getString("ZJH"));
					// 财务负责人2电话
					ksbgdjsqVo.setCffzrdhy2(rs.getString("GDDH"));
					ksbgdjsqVo.setCffzrdhx2(rs.getString("GDDH"));
					// 财务负责人2手机
					ksbgdjsqVo.setCffzrsjy2(rs.getString("YD_DH"));
					ksbgdjsqVo.setCffzrsjx2(rs.getString("YD_DH"));
					// 财务负责人2电子邮箱E-mail
					ksbgdjsqVo.setCffzremaily2(rs.getString("email"));
					ksbgdjsqVo.setCffzremailx2(rs.getString("email"));
				}
				cwfzrNum++;
			} else if ("07".equals(rs.getString("RYLX_DM"))) {
				if (bsryNum == 0) {
					// 办税人员姓名
					ksbgdjsqVo.setBsyxmy(rs.getString("XM"));
					ksbgdjsqVo.setBsyxmx(dataMap.get("bsryxmx1").toString());
					// 办税人员职务
					ksbgdjsqVo.setBsyzwy("");
					ksbgdjsqVo.setBsyzwx("");
					// 办税人员证件种类
					ksbgdjsqVo.setBsyzjzly(rs.getString("zjlx_dm"));
					ksbgdjsqVo.setBsyzjzlx(rs.getString("zjlx_dm"));
					// 办税人员身份证号
					ksbgdjsqVo.setBsysfzhy(rs.getString("ZJH"));
					ksbgdjsqVo.setBsysfzhx(dataMap.get("bsryzjhx1").toString());
					// 办税人员固定电话
					ksbgdjsqVo.setBsydhy(rs.getString("GDDH"));
					ksbgdjsqVo.setBsydhx(dataMap.get("bsrygddhx1").toString());
					// 办税人员手机
					ksbgdjsqVo.setBsysjy(rs.getString("YD_DH"));
					ksbgdjsqVo.setBsysjx(dataMap.get("bsryyddhx1").toString());
					// 办税人员电子邮箱E-mail
					ksbgdjsqVo.setBsyemaily(rs.getString("email"));
					ksbgdjsqVo.setBsyemailx(rs.getString("email"));
				} else if (bsryNum == 1) {
					// 办税人员2姓名
					ksbgdjsqVo.setBsyxmy2(rs.getString("XM"));
					ksbgdjsqVo.setBsyxmx2(dataMap.get("bsryxmx2").toString());
					// 办税人员2职务
					ksbgdjsqVo.setBsyzwy2("");
					ksbgdjsqVo.setBsyzwx2("");
					// 办税人员2证件种类
					ksbgdjsqVo.setBsyzjzly2(rs.getString("zjlx_dm"));
					ksbgdjsqVo.setBsyzjzlx2(rs.getString("zjlx_dm"));
					// 办税人员2身份证号
					ksbgdjsqVo.setBsysfzhy2(rs.getString("ZJH"));
					ksbgdjsqVo.setBsysfzhx2(dataMap.get("bsryzjhx2").toString());
					// 办税人员2固定电话
					ksbgdjsqVo.setBsydhy2(rs.getString("GDDH"));
					ksbgdjsqVo.setBsydhx2(dataMap.get("bsrygddhx2").toString());
					// 办税人员2手机
					ksbgdjsqVo.setBsysjy2(rs.getString("YD_DH"));
					ksbgdjsqVo.setBsysjx2(dataMap.get("bsryyddhx2").toString());
					// 办税人员2电子邮箱E-mail
					ksbgdjsqVo.setBsyemaily2(rs.getString("email"));
					ksbgdjsqVo.setBsyemailx2(rs.getString("email"));
				} else if (bsryNum == 2) {
					// 办税人员3姓名
					ksbgdjsqVo.setBsyxmy3(rs.getString("XM"));
					ksbgdjsqVo.setBsyxmx3(dataMap.get("bsryxmx3").toString());
					// 办税人员3职务
					ksbgdjsqVo.setBsyzwy3("");
					ksbgdjsqVo.setBsyzwx3("");
					// 办税人员3证件种类
					ksbgdjsqVo.setBsyzjzly3(rs.getString("zjlx_dm"));
					ksbgdjsqVo.setBsyzjzlx3(rs.getString("zjlx_dm"));
					// 办税人员3身份证号
					ksbgdjsqVo.setBsysfzhy3(rs.getString("ZJH"));
					ksbgdjsqVo.setBsysfzhx3(dataMap.get("bsryzjhx3").toString());
					// 办税人员3固定电话
					ksbgdjsqVo.setBsydhy3(rs.getString("GDDH"));
					ksbgdjsqVo.setBsydhx3(dataMap.get("bsrygddhx3").toString());
					// 办税人员3手机
					ksbgdjsqVo.setBsysjy3(rs.getString("YD_DH"));
					ksbgdjsqVo.setBsysjx3(dataMap.get("bsryyddhx3").toString());
					// 办税人员3电子邮箱E-mail
					ksbgdjsqVo.setBsyemaily3(rs.getString("email"));
					ksbgdjsqVo.setBsyemailx3(rs.getString("email"));
				}
				bsryNum++;
			}
		}
		WBksbgdjsqBPO.insert(con, ksbgdjsqVo);//保存变更申请
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	protected ResponseEvent initForm(RequestEvent req, Connection con) 
			throws SQLException, TaxBaseBizException{
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam.get("JsonReqData");
		Map<String,Object> dataMap = jsonReqData.getData();
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		
		String sql = "select distinct a.XM,a.zjlx_dm, a.ZJH, a.GDDH, a.YD_DH, a.email, a.RYLX_DM from DB_WSBS.t_Dj_Ryqkb a where a.swglm = ? and (RYLX_DM = ? OR RYLX_DM = ?	)";
		sqlParams = new ArrayList<Object>();
		sqlParams.add(dataMap.get("swglm"));
		sqlParams.add("06");
		sqlParams.add("07");
		CachedRowSet rs = QueryCssBPO.findAll(con, sql, sqlParams);
		int cwfzrNum = 0;//财务负责人计数器
		int bsryNum = 0;//办税人员计数器
		while(rs.next()){
			// 05:法定代表人（负责人或业主） 06:财务负责人 07:办税人员
			if("06".equals(rs.getString("RYLX_DM"))){
				if(cwfzrNum==0){
					responseEvent.respMapParam.put("cwfzrgddhx1", StringUtil.empty(rs.getString("GDDH")));//财务负责人1固定电话
					responseEvent.respMapParam.put("cwfzryddhx1", StringUtil.empty(rs.getString("YD_DH")));//财务负责人1移动电话
				}
				cwfzrNum++;
			}else if("07".equals(rs.getString("RYLX_DM"))){
				if(bsryNum==0){
					responseEvent.respMapParam.put("bsryxmx1", StringUtil.empty(rs.getString("XM")));//办税人员1姓名
					responseEvent.respMapParam.put("bsryzjlxx1", StringUtil.empty(rs.getString("zjlx_dm")));//办税人员1证件号码
					responseEvent.respMapParam.put("bsryzjhx1", StringUtil.empty(rs.getString("ZJH")));//办税人员1证件号码
					responseEvent.respMapParam.put("bsrygddhx1", StringUtil.empty(rs.getString("GDDH")));//办税人员1固定电话
					responseEvent.respMapParam.put("bsryyddhx1", StringUtil.empty(rs.getString("YD_DH")));//办税人员1移动电话
				}else if(bsryNum==1){
					responseEvent.respMapParam.put("bsryxmx2", StringUtil.empty(rs.getString("XM")));//办税人员2姓名
					responseEvent.respMapParam.put("bsryzjlxx2", StringUtil.empty(rs.getString("zjlx_dm")));//办税人员2证件类型
					responseEvent.respMapParam.put("bsryzjhx2", StringUtil.empty(rs.getString("ZJH")));//办税人员2证件号码
					responseEvent.respMapParam.put("bsrygddhx2", StringUtil.empty(rs.getString("GDDH")));//办税人员2固定电话
					responseEvent.respMapParam.put("bsryyddhx2", StringUtil.empty(rs.getString("YD_DH")));//办税人员2移动电话
				}else if(bsryNum==2){
					responseEvent.respMapParam.put("bsryxmx3", StringUtil.empty(rs.getString("XM")));//办税人员3姓名
					responseEvent.respMapParam.put("bsryzjlxx3", StringUtil.empty(rs.getString("zjlx_dm")));//办税人员3证件类型
					responseEvent.respMapParam.put("bsryzjhx3", StringUtil.empty(rs.getString("ZJH")));//办税人员3证件号码
					responseEvent.respMapParam.put("bsrygddhx3", StringUtil.empty(rs.getString("GDDH")));//办税人员3固定电话
					responseEvent.respMapParam.put("bsryyddhx3", StringUtil.empty(rs.getString("YD_DH")));//办税人员3移动电话
				}
				bsryNum++;
			}
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

}
