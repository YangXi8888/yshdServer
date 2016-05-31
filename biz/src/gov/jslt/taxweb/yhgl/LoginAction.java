package gov.jslt.taxweb.yhgl;

import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.JsonResData;
import gov.jslt.taxweb.comm.GeneralForm;

import java.util.HashMap;

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
import com.ctp.core.log.LogWritter;

public class LoginAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {
		JsonResData resData = new JsonResData();
		JsonReqData jsonReqData = new JsonReqData();
		String callback = null;
		try {
			response.setContentType("text/plain;charset=UTF-8");
			GeneralForm form = (GeneralForm) actionform;
			callback = form.getCallback();
			JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(form
					.getJsonData());
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setRootClass(JsonReqData.class);
			jsonReqData = (JsonReqData) JSONSerializer.toJava(jsonObject,
					jsonConfig);

			ResponseEvent responseEvent = null;
			BaseRequestEvent baseRequest = new BaseRequestEvent(
					jsonReqData.getBlhName(), "", "");
			baseRequest.setDealMethod(jsonReqData.getHandleCode());
			HashMap<String, Object> reqMapParam = new HashMap<String, Object>();
			reqMapParam.put("JsonReqData", jsonReqData);
			reqMapParam.put("HttpServletRequest", request);
			baseRequest.setReqMapParam(reqMapParam);
			responseEvent = (ResponseEvent) BizDelegate.delegate(baseRequest);
			resData.setCode(responseEvent.getRepCode());
			if ("0".equals(responseEvent.getRepCode())) {
				if (null == responseEvent.getReponseMesg()
						|| "".equals(responseEvent.getReponseMesg())) {
					responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
				}
			}
			if (null == responseEvent.getReponseMesg()
					|| "".equals(responseEvent.getReponseMesg())) {
				resData.setMsg(responseEvent.getRepCode());
			} else {
				resData.setMsg(responseEvent.getReponseMesg());
			}
			resData.setData(responseEvent.getRespMapParam());
			if (null == callback) {
				response.getWriter().print(
						JSONSerializer.toJSON(resData).toString());
			} else {
				response.getWriter().print(
						callback + "("
								+ JSONSerializer.toJSON(resData).toString()
								+ ")");
			}
			response.getWriter().flush();
		} catch (Exception e) {
			resData.setCode(GeneralCons.ERROR_CODE_ZB9999);
			resData.setMsg("系统异常，错误原因：" + e.getMessage());
			try {
				if (null == callback) {
					response.getWriter().print(
							JSONSerializer.toJSON(resData).toString());

				} else {
					response.getWriter().print(
							callback + "("
									+ JSONSerializer.toJSON(resData).toString()
									+ ")");
				}
				response.getWriter().flush();
			} catch (Exception e1) {
				LogWritter.sysError("Action返回消息异常:" + e1.getMessage());
			}
		}
		return null;
	}

}
