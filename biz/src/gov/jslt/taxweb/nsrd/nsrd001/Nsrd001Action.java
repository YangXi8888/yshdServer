package gov.jslt.taxweb.nsrd.nsrd001;

import gov.jslt.taxevent.comm.JsonResData;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ctp.core.bizdelegate.BizDelegate;
import com.ctp.core.event.BaseRequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.thoughtworks.xstream.XStream;

public class Nsrd001Action extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String swglm = request.getParameter("swglm");
		// 1.封装请求参数
		HashMap<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("swglm", swglm);
		// 2.构造远程调用
		BaseRequestEvent reqEvent = new BaseRequestEvent("Nsrd001BLH",
				"CHANEL_WEB", "");
		reqEvent.setDealMethod("sendMsg");
		reqEvent.setReqMapParam(reqMap);
		// 3.发送请求并接受请求
		ResponseEvent resEvent = (ResponseEvent) BizDelegate.delegate(reqEvent);
		// 4.处理返回参数
		if ("0".equals(resEvent.getRepCode())) {

		}
		if (null == resEvent.getReponseMesg()
				|| "".equals(resEvent.getReponseMesg())) {
			resEvent.setReponseMesg(resEvent.getRepCode());
		}
		return super.execute(mapping, form, request, response);
	}

	/**
	 * 模拟网报封装大集中请求参数
	 * 
	 * @param reqMap
	 */
	private Map<String, Object> wsbsReqParamForJslt() {
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("blhName", "PaoWangBLH");
		reqMap.put("swglm", "320100001100411");
		reqMap.put("dealMethod", "paoWang");
		reqMap.put("bizKey", "bizKey");
		reqMap.put("djXh", "11111111111111");

		// 拼装测试报文
		Map bizXml = new HashMap<String, Object>();
		bizXml.put("swglm", "320100001100411");
		com.thoughtworks.xstream.XStream xStream = new XStream();
		reqMap.put("content", xStream.toXML(bizXml).toString());
		return reqMap;

	}

}
