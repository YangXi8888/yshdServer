package gov.jslt.taxweb;

import gov.jslt.taxevent.comm.AESTool;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.JsonResData;

import java.net.URLDecoder;
import java.net.URLEncoder;
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

public class ZBAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {
		JsonResData resData = new JsonResData();
		JsonReqData jsonReqData = new JsonReqData();
		String callback = null;
		try {
			response.setContentType("text/plain;charset=UTF-8");
			ZBForm form = (ZBForm) actionform;
			callback = form.getCallback();
			JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(form
					.getJsonData());
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setRootClass(JsonReqData.class);
			jsonReqData = (JsonReqData) JSONSerializer.toJava(jsonObject,
					jsonConfig);

			if (null == jsonReqData.getYhwybz()
					|| "".equals(jsonReqData.getYhwybz())
					|| null == jsonReqData.getBizDataMw()
					|| "".equals(jsonReqData.getBizDataMw())
					|| null == AESTool.decrypt(jsonReqData.getBizDataMw(),
							jsonReqData.getYhwybz())) {
				resData.setCode(GeneralCons.ERROR_CODE_ZB9999);
				resData.setMsg("系统异常，错误原因：认证不通过");
				response.getWriter().print(
						AESTool.encrypt(JSONSerializer.toJSON(resData)
								.toString(), jsonReqData.getYhwybz()));
				LogWritter.sysError("系统认证不通过....");
				response.getWriter().flush();
				return null;
			}

			ResponseEvent responseEvent = null;
			BaseRequestEvent baseRequest = new BaseRequestEvent(
					jsonReqData.getBlhName(), "", "");
			baseRequest.setDealMethod(jsonReqData.getHandleCode());
			HashMap<String, Object> reqMapParam = new HashMap<String, Object>();
			reqMapParam.put("JsonReqData", jsonReqData);
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
						AESTool.encrypt(URLEncoder.encode(JSONSerializer
								.toJSON(resData).toString(), "UTF-8"),
								jsonReqData.getYhwybz()));
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
							AESTool.encrypt(JSONSerializer.toJSON(resData)
									.toString(), jsonReqData.getYhwybz()));
				} else {
					response.getWriter().print(
							callback
									+ "("
									+ AESTool.encrypt(
											JSONSerializer.toJSON(resData)
													.toString(), jsonReqData
													.getYhwybz()) + ")");
				}
				response.getWriter().flush();
			} catch (Exception e1) {
				LogWritter.sysError("ZBAction返回消息异常:" + e1.getMessage());
			}
		}
		return null;
	}
}
