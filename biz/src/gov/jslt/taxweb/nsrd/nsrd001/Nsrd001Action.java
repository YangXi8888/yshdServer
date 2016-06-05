package gov.jslt.taxweb.nsrd.nsrd001;

import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.JsonResData;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ctp.core.bizdelegate.BizDelegate;
import com.ctp.core.event.BaseRequestEvent;
import com.ctp.core.event.ResponseEvent;

public class Nsrd001Action extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JsonReqData jsonReqData = new JsonReqData();
		if (null != request.getParameter("jsonData")
				&& !"".equals(request.getParameter("jsonData"))) {
			JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(request
					.getParameter("jsonData"));
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setRootClass(jsonReqData.getClass());
			jsonReqData = (JsonReqData) JSONSerializer.toJava(jsonObject,
					jsonConfig);
			if ("initPage".equals(jsonReqData.getHandleCode())) {
				initPage(request, response, jsonReqData);
			} else if ("sendData".equals(jsonReqData.getHandleCode())) {
				sendData(request, response, jsonReqData);
			}
		}
		return null;
	}

	private void initPage(HttpServletRequest request,
			HttpServletResponse response, JsonReqData jsonReqData)
			throws IOException {
		String swglm = (String) jsonReqData.getData().get("swglm");
		// 1.封装请求参数
		HashMap<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("swglm", swglm);
		// 2.构造远程调用
		BaseRequestEvent reqEvent = new BaseRequestEvent(
				jsonReqData.getBlhName(), "CHANEL_WEB", "");
		reqEvent.setDealMethod(jsonReqData.getHandleCode());
		reqEvent.setReqMapParam(reqMap);
		// 3.发送请求并接受请求
		ResponseEvent resEvent = (ResponseEvent) BizDelegate.delegate(reqEvent);
		// 4.处理返回参数
		JsonResData jsonResData = new JsonResData();
		Map<String, Object> map = new HashMap<String, Object>();
		if ("0".equals(resEvent.getRepCode())) {
			map.put("nsrMc", resEvent.respMapParam.get("nsrMc"));
			if (null != resEvent.respMapParam.get("yhList")) {
				List<Map<String, String>> yhList = (List<Map<String, String>>) resEvent.respMapParam
						.get("yhList");
				map.put("yhList", yhList);
			}
		}
		jsonResData.setCode(resEvent.getRepCode());
		jsonResData.setData(map);
		if (null == resEvent.getReponseMesg()
				|| "".equals(resEvent.getReponseMesg())) {
			jsonResData.setMsg(resEvent.getRepCode());
		} else {

			jsonResData.setMsg(resEvent.getReponseMesg());
		}
		response.setContentType("text/plain;charset=UTF-8");
		response.getWriter().print(
				JSONSerializer.toJSON(jsonResData).toString());
		response.getWriter().flush();
	}

	private void sendData(HttpServletRequest request,
			HttpServletResponse response, JsonReqData jsonReqData)
			throws IOException {
		String swglm = (String) jsonReqData.getData().get("swglm");
		String qyyhDm = (String) jsonReqData.getData().get("qyyhDm");
		// 1.封装请求参数
		HashMap<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("swglm", swglm);
		reqMap.put("qyyhDm", qyyhDm);
		// 2.构造远程调用
		BaseRequestEvent reqEvent = new BaseRequestEvent(
				jsonReqData.getBlhName(), "CHANEL_WEB", "");
		reqEvent.setDealMethod(jsonReqData.getHandleCode());
		reqEvent.setReqMapParam(reqMap);
		// 3.发送请求并接受请求
		ResponseEvent resEvent = (ResponseEvent) BizDelegate.delegate(reqEvent);
		// 4.处理返回参数
		JsonResData jsonResData = new JsonResData();
		if (null == resEvent.getReponseMesg()
				|| "".equals(resEvent.getReponseMesg())) {
			jsonResData.setMsg(resEvent.getRepCode());
		} else {
			jsonResData.setMsg(resEvent.getReponseMesg());
		}
		response.setContentType("text/plain;charset=UTF-8");
		response.getWriter().print(
				JSONSerializer.toJSON(jsonResData).toString());
		response.getWriter().flush();
	}

}
