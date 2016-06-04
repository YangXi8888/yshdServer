package gov.jslt.taxcore.taxblh.nsrd.nsrd001;

import gov.jslt.taxcore.taxblh.comm.JMSSender;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import com.thoughtworks.xstream.XStream;

public class Nsrd001BLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		if ("sendMsg".equals(reqEvent.getDealMethod())) {
			return sendMsg(reqEvent, conn);
		}
		return null;
	}

	private ResponseEvent sendMsg(RequestEvent reqEvent, Connection conn) {
		ResponseEvent resEvent = new ResponseEvent();
		String swglm = (String) reqEvent.reqMapParam.get("swglm");
		// 1.封装JMS请求参数
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("blhName", "Nsrd001BLH");
		reqMap.put("dealMethod", "getMsg");
		reqMap.put("swglm", swglm);
		reqMap.put("bizKey", "");
		reqMap.put("djXh", "");
		// 1.1拼装业务报文
		Map<String, Object> bizXml = new HashMap<String, Object>();
		bizXml.put("swglm", swglm);
		XStream xStream = new XStream();
		reqMap.put("content", xStream.toXML(bizXml).toString());
		// 2.发送JMS消息并接收返回消息
		JMSSender jSender = new JMSSender();
		Map<String, String> returnMap = jSender.synSend(reqMap);
		resEvent.setRespMapParam((HashMap) returnMap);
		return resEvent;
	}

	@Override
	protected ResponseEvent validateData(RequestEvent arg0, Connection arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
