package gov.jslt.taxcore.taxblh.ggfw.sqhd.sq004;


import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.config.ApplicationContext;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;

public class Sqhd004BLH extends BaseBizLogicHandler {
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws TaxBaseBizException, SQLException {
		String delMethod = req.getDealMethod();
		if ("jzxx_hfgk".equals(delMethod)) {
			//局长信箱--回复公开
			return hfgk(req, con);
		}
		if ("jzxx_hfgkmx".equals(delMethod)) {
			//局长信箱---回复公开明细
			return hfgkmx(req, con);
		}
		if ("jzxx_bltj".equals(delMethod)) {
			//局长信箱--办理统计
			return bltj(req, con);
		}
		if ("jzxx_cxhf".equals(delMethod)) {
			//局长信箱--查询回复
			return cxhf(req, con);
		}		
		if("jzxx_wyxx".equalsIgnoreCase(delMethod)){
			//局长信箱--我要写信
			return do_save(req, con);
		}
		
		if ("nwwd_hfgk".equals(delMethod)) {
			//局长信箱--你问我答--回复列表
			return hfgk(req, con);
		}
		if ("nwwd_hfgkmx".equals(delMethod)) {
			//局长信箱--你问我答--回复列表明细
			return hfgkmx(req, con);
		}
		if ("nwwd_bltj".equals(delMethod)) {
			//局长信箱--你问我答--办理统计
			return hfgk(req, con);
		}
		if ("nwwd_cxhf".equals(delMethod)) {
			//局长信箱--你问我答--查询回复
			return cxhf(req, con);
		}		
		if ("nwwd_wyzx".equals(delMethod)) {
			//局长信箱--你问我答--我要咨询
			return do_save(req, con);
		}
		
		if ("lzfx_hfgk".equals(delMethod)) {
			//廉政行风--回复公开
			return hfgk(req, con);
		}
		if ("lzfx_hfgkmx".equals(delMethod)) {
			//廉政行风--回复公开明细
			return hfgkmx(req, con);
		}
		if ("lzfx_bltj".equals(delMethod)) {
			//廉政行风--办理统计
			return bltj(req, con);
		}
		if ("lzfx_cxhf".equals(delMethod)) {
			//廉政行风--查询回复
			return cxhf(req, con);
		}		
		if ("lzfx_wyts".equals(delMethod)) {
			//廉政行风--我要投诉
			return do_save(req, con);
		}
		
		if ("fpwz_hfgk".equals(delMethod)) {
			//发票违章--回复公开
			return hfgk(req, con);
		}
		if ("fpwz_hfgkmx".equals(delMethod)) {
			//发票违章--回复公开明细
			return hfgkmx(req, con);
		}
		if ("fpwz_bltj".equals(delMethod)) {
			//发票违章--办理统计
			return bltj(req, con);
		}
		if ("fpwz_cxhf".equals(delMethod)) {
			//发票违章--查询回复
			return cxhf(req, con);
		}		
		if ("fpwz_wyjb".equals(delMethod)) {
			//发票违章--我要举报
			return do_save(req, con);
		}
		
		if ("wfaj_hfgk".equals(delMethod)) {
			//违法案件--回复公开
			return hfgk(req, con);
		}
		if ("wfaj_hfgkmx".equals(delMethod)) {
			//违法案件--回复公开明细
			return hfgkmx(req, con);
		}
		if ("wfaj_bltj".equals(delMethod)) {
			//违法案件--办理统计
			return bltj(req, con);
		}
		if ("wfaj_cxhf".equals(delMethod)) {
			//违法案件--查询回复
			return cxhf(req, con);
		}
		if ("wfaj_wyjb".equals(delMethod)) {
			//违法案件--我要举报
			return do_save(req, con);
		}
		
		if ("nffw_hfgk".equals(delMethod)) {
			//纳税服务投诉--回复公开
			return hfgk(req, con);
		}
		if ("nffw_hfgkmx".equals(delMethod)) {
			//纳税服务投诉--回复公开明细
			return hfgk(req, con);
		}
		if ("nffw_bltj".equals(delMethod)) {
			//纳税服务投诉--办理统计
			return bltj(req, con);
		}
		if ("nffw_cxhf".equals(delMethod)) {
			//纳税服务投诉--查询回复
			return cxhf(req, con);
		}
		if ("nffw_nfts".equals(delMethod)) {
			//纳税服务投诉--纳服投诉
			return do_save(req, con);
		}
		return null;
	}
	
    
	protected ResponseEvent hfgk(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent resEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();		
		String currentPage = (String) dataMap.get("currentPage");
		//业务系统id 局长信箱sysid=001；  廉政行风投诉sysid=002；局长信箱--你问我答sysid=003；发票违章举报sysid=089；违法案件举报sysid=013；纳税服务投诉=086；
		String orderfield = (String)dataMap.get("orderfield");
		String sysid = (String) dataMap.get("sysid");
		ArrayList mxList = new ArrayList();       
		StringBuilder stringBuilder = new StringBuilder(); 					
	  	String result="";
	  	if("001".equals(sysid)){
	  		stringBuilder.append(ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/lm/interface/mobile/mailList.jsp?sysid="+sysid+"&linages=10&needpage="+currentPage+"&sess=0&groupid=0005");
	  		if(orderfield !=""){
	  			stringBuilder.append("&orderfield="+orderfield);
	  		}
	  	}else{
	  		stringBuilder.append(ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/lm/interface/mobile/mailList.jsp?sysid="+sysid+"&sess=0&linages=10&needpage="+currentPage);
	  		if(orderfield !=""){
	  			stringBuilder.append("&orderfield="+orderfield);
	  		}
	  	}
	  		
		try { 
			URL U = new URL(stringBuilder.toString());   
			URLConnection connection = U.openConnection();   
			   connection.connect();   		     
			   BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));   
			   String line;   
			   while ((line = reader.readLine())!= null)   
			   {   			  
				   result += line;   
			   }   
			   reader.close();
			   Document doc=DocumentHelper.parseText(result);
			   Element root=doc.getRootElement();

			   Iterator<Element> iterator = root.elementIterator(); 
			   HashMap resMap=new HashMap();			   			   
		       while(iterator.hasNext()){ 
		           Element e = iterator.next();  
		           if("i_pointnum".equals(e.getName())){
		        	  resMap.put(e.getName(), e.getText()); 
		        	  mxList.add(resMap);
		        	  resMap=new HashMap();		
		           }else{
		        	   resMap.put(e.getName(), e.getText());  
		           }		           		                     	   
		       }
		       
			   resEvent.respMapParam.put("List",mxList);
			   resEvent.setRepCode(GeneralCons.SUCCESS_CODE);
			   resEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
			} catch (Exception e) {  
				resEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
				resEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
						ZBCoreHelper.getJyztMc(conn, GeneralCons.ERROR_CODE_ZB9999),
						e.getMessage()));
			  } 			
		 		
		return resEvent;
	}
 
    
	protected ResponseEvent bltj(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent resEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String year=(String)dataMap.get("year");
		String sysid=(String)dataMap.get("sysid");
		HashMap resMap=new HashMap(); 		
  		String result="";
  		StringBuilder stringBuilder = new StringBuilder();
  		stringBuilder.append(ApplicationContext.singleton().getValueAsString(
				"sjmh.server.www")
				+ "/lm/interface/mobile/mailStat.jsp?sysid="+sysid+"&sess=0&vc_stat_groupid=0005&vc_stat_year="+year);
		try { 
		URL U = new URL(stringBuilder.toString());   
		URLConnection connection = U.openConnection();   
		   connection.connect();   		     
		   BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));   
		   String line;   
		   while ((line = reader.readLine())!= null)   
		   {   			  
			   result += line;   
		   }   
		   reader.close();
		   Document doc=DocumentHelper.parseText(result);
		   Element root=doc.getRootElement();
		   resEvent.respMapParam.put("wts",root.element("vc_sum").getTextTrim());//问题数
		   resEvent.respMapParam.put("hfs",root.element("vc_havereply").getTextTrim());//回复数
		   resEvent.respMapParam.put("bfb",root.element("vc_percent").getTextTrim());//百分比
		   resEvent.respMapParam.put("asfkl",root.element("vc_percentontime").getTextTrim());//按时反馈率
		   resEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		   resEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		} catch (Exception e) {  
			resEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			resEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					ZBCoreHelper.getJyztMc(conn, GeneralCons.ERROR_CODE_ZB9999),
					e.getMessage()));
		  } 
		return resEvent;
	}
	
    
	protected ResponseEvent cxhf(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent resEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String slbh=(String)dataMap.get("slbh");
		String cxm=(String)dataMap.get("cxm");	
		String sysid=(String)dataMap.get("sysid");
  		String result="";
  		StringBuilder stringBuilder = new StringBuilder();
  		stringBuilder.append(ApplicationContext.singleton().getValueAsString(
				"sjmh.server.www")
				+ "/lm/interface/mobile/searchMailDetails.jsp?sysid="+sysid+"&vc_mailpass="+cxm+"&vc_mailnumber="+slbh);
		try { 
		URL U = new URL(stringBuilder.toString());   
		URLConnection connection = U.openConnection();   
		   connection.connect();   		     
		   BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));   
		   String line;   
		   while ((line = reader.readLine())!= null)   
		   {   			  
			   result += line;   
		   }   
		   reader.close();
		   Document doc=DocumentHelper.parseText(result);
		   Element root=doc.getRootElement();
		   resEvent.respMapParam.put("vc_username",root.element("vc_username").getTextTrim());//姓名
		   resEvent.respMapParam.put("vc_relationphone",root.element("vc_relationphone").getTextTrim());//联系电话
		   resEvent.respMapParam.put("vc_mobilephone",root.element("vc_mobilephone").getTextTrim());//手机号码
		   resEvent.respMapParam.put("vc_relationaddr",root.element("vc_relationaddr").getTextTrim());//联系地址
		   resEvent.respMapParam.put("vc_zipcode",root.element("vc_zipcode").getTextTrim());//邮政编码
		   resEvent.respMapParam.put("vc_paperstype",root.element("vc_paperstype").getTextTrim());//证件类型
		   resEvent.respMapParam.put("vc_sex",root.element("vc_sex").getTextTrim());//性        别
		   resEvent.respMapParam.put("vc_papersnumber",root.element("vc_papersnumber").getTextTrim());//证件号码
		   if("0".equals(root.element("i_age").getTextTrim().trim())){
			   resEvent.respMapParam.put("i_age","");//年  龄 
		   }else{
			   resEvent.respMapParam.put("i_age",root.element("i_age").getTextTrim());//年  龄
		   }
		   resEvent.respMapParam.put("vc_targetgroup",root.element("vc_targetgroup").getTextTrim());//提交对象
		   resEvent.respMapParam.put("dt_sendtime",root.element("dt_sendtime").getTextTrim());//提交时间
		   resEvent.respMapParam.put("vc_mailpropertyname",root.element("vc_mailpropertyname").getTextTrim());//办件分类
		   resEvent.respMapParam.put("vc_mailnumber",root.element("vc_mailnumber").getTextTrim());//办件编号
		   resEvent.respMapParam.put("title",root.element("title").getTextTrim());//标        题
		   resEvent.respMapParam.put("vc_email",root.element("vc_email").getTextTrim());//EMAIL
		   resEvent.respMapParam.put("content",root.element("content").getTextTrim());//内  容
		   resEvent.respMapParam.put("state",root.element("state").getTextTrim());//处理状态
		   resEvent.respMapParam.put("replay",root.element("replay").getTextTrim());//答复内容
		   resEvent.respMapParam.put("replaytime",root.element("replaytime").getTextTrim());//答复时间
		   resEvent.respMapParam.put("vc_replyunit",root.element("vc_replyunit").getTextTrim());//答复单位
		   resEvent.respMapParam.put("b_wishpublic",root.element("b_wishpublic").getTextTrim());//愿意公开   
		   resEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		   resEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		} catch (Exception e) {  
			resEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			resEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					ZBCoreHelper.getJyztMc(conn, GeneralCons.ERROR_CODE_ZB9999),
					e.getMessage()));
		  } 
		return resEvent;
	}
	
	
	
	protected ResponseEvent hfgkmx(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent resEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String vc_id=(String)dataMap.get("vc_id");
  		String result="";
  		StringBuilder stringBuilder = new StringBuilder();
  		stringBuilder.append(ApplicationContext.singleton().getValueAsString(
				"sjmh.server.www")
				+ "/lm/interface/mobile/searchMailDetailsByid.jsp?vc_id="+vc_id);
		try { 
		URL U = new URL(stringBuilder.toString());   
		URLConnection connection = U.openConnection();   
		   connection.connect();   		     
		   BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));   
		   String line;   
		   while ((line = reader.readLine())!= null)   
		   {   			  
			   result += line;   
		   }   
		   reader.close();
		   Document doc=DocumentHelper.parseText(result);
		   Element root=doc.getRootElement();
		   resEvent.respMapParam.put("title",root.element("title").getTextTrim());//标题
		   resEvent.respMapParam.put("content",root.element("content").getTextTrim());//内容
		   resEvent.respMapParam.put("dt_sendtime",root.element("dt_sendtime").getTextTrim());//来信时间
		   resEvent.respMapParam.put("replay",root.element("replay").getTextTrim());//答复内容
		   resEvent.respMapParam.put("replaytime",root.element("replaytime").getTextTrim());//答复时间
		   resEvent.respMapParam.put("vc_replyunit",root.element("vc_replyunit").getTextTrim());//答复单位
		   resEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		   resEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		} catch (Exception e) {  
			resEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			resEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					ZBCoreHelper.getJyztMc(conn, GeneralCons.ERROR_CODE_ZB9999),
					e.getMessage()));
		  } 
		return resEvent;
	}

		protected ResponseEvent do_save(RequestEvent reqEvent, Connection conn)
					throws SQLException, TaxBaseBizException{
				ResponseEvent resEvent = new ResponseEvent();
				JsonReqData jsonReqData = (JsonReqData)reqEvent.reqMapParam.get("JsonReqData");
				Map<String , Object> dataMap = jsonReqData.getData();	
				HttpURLConnection connection;
				BufferedReader reader;
				String line;
				StringBuffer str = new StringBuffer();

				try {
					String ispublic=(String)dataMap.get("gk");//是否公开
					String title = (String)dataMap.get("bt");//标题
					String content=(String)dataMap.get("content");//内容
					String name=(String)dataMap.get("name");//姓名					
					String target=(String)dataMap.get("dw");//主要的涉及单位id					
					String TelNumber=(String)dataMap.get("tel");//联系电话
					String vc_mobilephone=(String)dataMap.get("phone");//手机号码
					String vc_email=(String)dataMap.get("email");//e-mail地址
					String sysid =(String)dataMap.get("sysid");//业务系统id
					
					String vc_targetgroupname =(String)dataMap.get("vc_targetgroupname");//提交对象单位名称
					String vc_paperstype =(String)dataMap.get("vc_paperstype");//证件类型
					String vc_papersnumber =(String)dataMap.get("vc_papersnumber");//证件号码										
					String vc_sex =(String)dataMap.get("vc_sex");//性别
					String i_age =(String)dataMap.get("i_age");//年龄
					String vc_zipcode =(String)dataMap.get("vc_zipcode");//邮政编码
					String vc_relationaddr =(String)dataMap.get("vc_relationaddr");//联系地址
					
					String result="";	
					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder.append(ApplicationContext.singleton().getValueAsString(
							"sjmh.server.www")
							+ "/lm/interface/mobile/mailWrite.jsp?");
					if (null != ispublic
							&& !"".equals(ispublic)) {
						stringBuilder.append("ispublic="
								+ ispublic);
					}
					if (null != title
							&& !"".equals(title)) {
						stringBuilder.append("&title="
								+ URLEncoder.encode(title,"UTF-8"));
					} 
					if (null != content
							&& !"".equals(content)) {
						stringBuilder.append("&content="
								+ URLEncoder.encode(content,"UTF-8"));
					} 
					if (null != target
							&& !"".equals(target)) {
						stringBuilder.append("&target="
								+ target);
					} 
					if (null != name
							&& !"".equals(name)) {
						stringBuilder.append("&name="
								+ URLEncoder.encode(name,"UTF-8"));
					} 
					if (null != TelNumber
							&& !"".equals(TelNumber)) {
						stringBuilder.append("&TelNumber="
								+ TelNumber);
					} 
					if (null != vc_mobilephone
							&& !"".equals(vc_mobilephone)) {
						stringBuilder.append("&vc_mobilephone="
								+ vc_mobilephone);
					} 
					if (null != vc_email
							&& !"".equals(vc_email)) {
						stringBuilder.append("&vc_email="
								+ vc_email);
					} 
					if (null != sysid
							&& !"".equals(sysid)) {
						stringBuilder.append("&sysid="
								+ sysid);
					}
					
					if (null != vc_targetgroupname
							&& !"".equals(vc_targetgroupname)) {
						stringBuilder.append("&vc_targetgroupname="
								+ URLEncoder.encode(vc_targetgroupname,"UTF-8"));
					} 
					if (null != vc_paperstype
							&& !"".equals(vc_paperstype)) {
						stringBuilder.append("&vc_paperstype="
								+ vc_paperstype);
					} 
					if (null != vc_papersnumber
							&& !"".equals(vc_papersnumber)) {
						stringBuilder.append("&vc_papersnumber="
								+ vc_papersnumber);
					} 
					if (null != vc_sex
							&& !"".equals(vc_sex)) {
						stringBuilder.append("&vc_sex="
								+ vc_sex);
					} 
					if (null != i_age
							&& !"".equals(i_age)) {
						stringBuilder.append("&i_age="
								+ i_age);
					} 
					if (null != vc_zipcode
							&& !"".equals(vc_zipcode)) {
						stringBuilder.append("&vc_zipcode="
								+ vc_zipcode);
					}
					if (null != vc_relationaddr
							&& !"".equals(vc_relationaddr)) {
						stringBuilder.append("&vc_relationaddr="
								+ vc_relationaddr);
					}
					URL url=new URL(stringBuilder.toString());
					connection = (HttpURLConnection) url.openConnection();
					connection.setDoOutput(true);
					connection.setUseCaches(false);
					connection.setReadTimeout(2000);
					connection.setConnectTimeout(2000);
					connection.setRequestMethod("POST");
					connection.setRequestProperty("Content-Type", "text/html; charset=utf-8");
					connection.setRequestProperty("accept", "text/xml");
					connection.connect();
					reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
					while ((line = reader.readLine()) != null) {
						str.append(line);
						result += line;   
					}
					System.out.println(str.toString());
					 reader.close();
					 Document doc=DocumentHelper.parseText(result);
					 Element root=doc.getRootElement();
					 if("true".equals(root.element("result").getTextTrim())){
						resEvent.respMapParam.put("id",root.element("id").getTextTrim());//受理编码
						resEvent.respMapParam.put("code",root.element("code").getTextTrim());//查询码
						resEvent.setRepCode(GeneralCons.SUCCESS_CODE);
						resEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);  
					   }
				} catch (Exception e) {
					resEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
					resEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
							ZBCoreHelper.getJyztMc(conn, GeneralCons.ERROR_CODE_ZB9999),
							e.getMessage()));
				}
				
				return resEvent;
			
		}
		
	@Override
	protected ResponseEvent validateData(RequestEvent arg0, Connection arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
