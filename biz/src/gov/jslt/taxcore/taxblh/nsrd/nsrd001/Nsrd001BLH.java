package gov.jslt.taxcore.taxblh.nsrd.nsrd001;

import gov.jslt.taxcore.taxblh.comm.CoreHelper;
import gov.jslt.taxcore.taxblh.comm.JMSSender;
import gov.jslt.taxcore.taxblh.comm.StringUtil;
import gov.jslt.taxcore.taxbpo.nsrd.nsrd001.NsrCwbbBPO;
import gov.jslt.taxcore.taxbpo.nsrd.nsrd001.NsrJbxxBPO;
import gov.jslt.taxcore.taxbpo.nsrd.nsrd001.NsrSbfBPO;
import gov.jslt.taxcore.taxbpo.nsrd.nsrd001.NsrSfBPO;
import gov.jslt.taxcore.taxbpo.nsrd.nsrd001.NsrXzcfBPO;
import gov.jslt.taxcore.taxbpo.nsrd.nsrd001.NsrZbBPO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrCwbbVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrJbxxVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrSbfVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrSfVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrXzcfVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrZbVO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.bpo.QueryBPO;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import com.thoughtworks.xstream.XStream;

public class Nsrd001BLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		if ("initPage".equals(reqEvent.getDealMethod())) {
			return initPage(reqEvent, conn);
		} else if ("sendData".equals(reqEvent.getDealMethod())) {
			return sendData(reqEvent, conn);
		}
		return null;
	}

	private ResponseEvent initPage(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent resEvent = new ResponseEvent();
		ArrayList<String> sqlParams = new ArrayList<String>();
		sqlParams.add((String) reqEvent.reqMapParam.get("swglm"));
		String sql = "SELECT SWGLM, NSRSBM,NSR_MC FROM T_DJ_JGNSR WHERE SWGLM =?";
		CachedRowSet rs = QueryBPO.findAll(conn, sql, sqlParams);
		if (rs.next()) {
			resEvent.respMapParam.put("nsrMc", rs.getString("NSR_MC"));
			resEvent.respMapParam.put("nsrSbm", rs.getString("NSRSBM"));
		}
		sql = "SELECT T.QYYH_DM, T.QYYH_MC FROM T_DM_YS_QYYH T WHERE XY_BJ = '1'";
		rs = QueryBPO.findAll(conn, sql, null);
		List<Map<String, String>> yhList = new ArrayList<>();
		Map<String, String> map = null;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("qyyhDm", rs.getString("QYYH_DM"));
			map.put("qyyhMc", rs.getString("QYYH_MC"));
			yhList.add(map);
		}
		resEvent.respMapParam.put("yhList", yhList);
		resEvent.setReponseMesg("处理成功");
		return resEvent;

	}

	private ResponseEvent sendData(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent resEvent = new ResponseEvent();
		// String swglm = "320100002106161";
		String swglm = (String) reqEvent.reqMapParam.get("swglm");
		String nsrSbm = (String) reqEvent.reqMapParam.get("nsrSbm");
		String nsrMc = (String) reqEvent.reqMapParam.get("nsrMc");
		String qyyhDm = (String) reqEvent.reqMapParam.get("qyyhDm");
		// 1.封装JMS请求参数
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("blhName", "Nsrd001BLH");
		reqMap.put("dealMethod", "queryData");
		reqMap.put("swglm", swglm);
		reqMap.put("bizKey", "");
		reqMap.put("djXh", "");
		// 2.拼装业务报文
		Map<String, Object> bizXml = new HashMap<String, Object>();
		bizXml.put("swglm", swglm);
		XStream xStream = new XStream();
		reqMap.put("content", xStream.toXML(bizXml).toString());
		// 3.发送JMS消息并接收返回消息
		JMSSender jSender = new JMSSender();
		Map<String, String> returnMap = jSender.synSend(reqMap);
		Map<String, String> tmpMap = null;// 用于处理jms返回结果的临时变量
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> jmsResMap = jsonObject.fromObject(returnMap
				.get("cDATA"));

		// ************************************ 保存返回数据 **************************************************//

		String zbUuid = CoreHelper.getGUID(conn);
		// 纳税人发送银行主表
		NsrZbVO nsrZbVO = new NsrZbVO();
		nsrZbVO.setZbuuid(zbUuid);
		nsrZbVO.setSwglm(swglm);
		nsrZbVO.setNsrmc(nsrMc);
		nsrZbVO.setNsrsbm(nsrSbm);
		nsrZbVO.setQyyhdm(qyyhDm);
		NsrZbBPO.insert(conn, nsrZbVO);
		// 基本信息
		Map<String, String> jbXxMap = (Map<String, String>) jmsResMap
				.get("jbXx");
		NsrJbxxVO jbxxVO = new NsrJbxxVO();
		jbxxVO.setUuid(CoreHelper.getGUID(conn));
		jbxxVO.setZbuuid(zbUuid);
		jbxxVO.setSwglm(jbXxMap.get("swglm"));
		jbxxVO.setNsrsbm(jbXxMap.get("nsrSbm"));
		jbxxVO.setNsrmc(jbXxMap.get("nsrMc"));
		jbxxVO.setDjzclxmc(jbXxMap.get("djzclxMc"));
		jbxxVO.setGbhymc(jbXxMap.get("gbhyMc"));
		jbxxVO.setZcdz(jbXxMap.get("zcDz"));
		jbxxVO.setXydj(StringUtil.empty(jbXxMap.get("xyDj")));
		NsrJbxxBPO.insert(conn, jbxxVO);
		// 税费记录
		List<Map<String, String>> sFXxList = (List<Map<String, String>>) jmsResMap
				.get("sfXx");
		NsrSfVO sfVO = null;
		for (int i = 0; i < sFXxList.size(); i++) {
			tmpMap = sFXxList.get(i);
			sfVO = new NsrSfVO();
			sfVO.setUuid(CoreHelper.getGUID(conn));
			sfVO.setZbuuid(zbUuid);
			sfVO.setSsnd(tmpMap.get("ssNd"));
			sfVO.setSwglm(tmpMap.get("swglm"));
			sfVO.setNsrsbm(tmpMap.get("nsrSbm"));
			sfVO.setNsrmc(tmpMap.get("nsrMc"));
			sfVO.setSz(tmpMap.get("sz"));
			sfVO.setRkse(tmpMap.get("rkSe"));
			NsrSfBPO.insert(conn, sfVO);
		}
		// 社保费
		List<Map<String, String>> sbfXxList = (List<Map<String, String>>) jmsResMap
				.get("sbfXx");
		NsrSbfVO sbfVO = null;
		for (int i = 0; i < sbfXxList.size(); i++) {
			tmpMap = sbfXxList.get(i);
			sbfVO = new NsrSbfVO();
			sbfVO.setUuid(CoreHelper.getGUID(conn));
			sbfVO.setZbuuid(zbUuid);
			sbfVO.setSsnd(tmpMap.get("ssNd"));
			sbfVO.setSwglm(tmpMap.get("swglm"));
			sbfVO.setNsrsbm(tmpMap.get("nsrSbm"));
			sbfVO.setNsrmc(tmpMap.get("nsrMc"));
			sbfVO.setXz(tmpMap.get("xz"));
			sbfVO.setSjje(tmpMap.get("sjJe"));
			NsrSbfBPO.insert(conn, sbfVO);
		}
		// 财务报表信息
		List<Map<String, String>> cwXxList = (List<Map<String, String>>) jmsResMap
				.get("cwXx");
		NsrCwbbVO cwbbVO = null;
		for (int i = 0; i < cwXxList.size(); i++) {
			tmpMap = cwXxList.get(i);
			cwbbVO = new NsrCwbbVO();
			cwbbVO.setUuid(CoreHelper.getGUID(conn));
			cwbbVO.setZbgj(zbUuid);
			cwbbVO.setSwglm(tmpMap.get("swglm"));
			cwbbVO.setNsrsbm(tmpMap.get("nsrSbm"));
			cwbbVO.setNsrmc(tmpMap.get("nsrMc"));
			cwbbVO.setSsnd(tmpMap.get("ssNd"));
			cwbbVO.setZcze(tmpMap.get("zcZe"));
			cwbbVO.setFzze(tmpMap.get("fzZe"));
			cwbbVO.setSszb(tmpMap.get("ssZb"));
			cwbbVO.setZbgj(tmpMap.get("zbGj"));
			cwbbVO.setZyywsr(tmpMap.get("zyywSr"));
			cwbbVO.setZyywcb(tmpMap.get("zyywCb"));
			cwbbVO.setKjlr(tmpMap.get("kjLr"));
			NsrCwbbBPO.insert(conn, cwbbVO);

		}
		// 处罚信息
		List<Map<String, String>> cfXxList = (List<Map<String, String>>) jmsResMap
				.get("cfXx");
		NsrXzcfVO xzcfVO = null;
		for (int i = 0; i < cfXxList.size(); i++) {
			tmpMap = cwXxList.get(i);
			xzcfVO = new NsrXzcfVO();
			xzcfVO.setUuid(CoreHelper.getGUID(conn));
			xzcfVO.setZbuuid(zbUuid);
			xzcfVO.setSwglm(tmpMap.get("swglm"));
			xzcfVO.setNsrsbm(tmpMap.get("nsrSbm"));
			xzcfVO.setNsrmc(tmpMap.get("nsrMc"));
			xzcfVO.setCfjdwh(tmpMap.get("cfjdWh"));
			xzcfVO.setCfsyStr(tmpMap.get("cfSy"));
			xzcfVO.setCfrq(tmpMap.get("cfSy"));
			xzcfVO.setZxwcrq(tmpMap.get("zxwcRq"));
			xzcfVO.setCfje(tmpMap.get("cfJe"));
			NsrXzcfBPO.insert(conn, xzcfVO);
		}
		resEvent.setReponseMesg("处理成功");
		return resEvent;
	}

	@Override
	protected ResponseEvent validateData(RequestEvent arg0, Connection arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
