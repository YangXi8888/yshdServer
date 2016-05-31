package gov.jslt.taxweb.yhgl;

<<<<<<< HEAD
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.JsonResData;
import gov.jslt.taxweb.comm.GeneralForm;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

=======
import com.ctp.core.bizdelegate.BizDelegate;
import com.ctp.core.event.BaseRequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.log.LogWritter;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.JsonResData;
import gov.jslt.taxevent.comm.LoginVO;
import gov.jslt.taxweb.comm.WebTool;
>>>>>>> 7ee792ab3a6adce0f74f65660e1b3e05cfff1406
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginAction extends Action {

<<<<<<< HEAD
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
=======
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("登录界面.........................");
        LogWritter.sysError("登录界面.........................");
        JsonReqData jsonReqData = new JsonReqData();
        if (null != request.getParameter("jsonData")
                && !"".equals(request.getParameter("jsonData"))) {
            JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(request
                    .getParameter("jsonData"));
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.setRootClass(JsonReqData.class);
            jsonReqData = (JsonReqData) JSONSerializer.toJava(jsonObject,
                    jsonConfig);
            if ("getYzm".equals(jsonReqData.getHandleCode())) {
                getYzm(request, response, jsonReqData);
            } else if ("login".equals(jsonReqData.getHandleCode())) {
                login(request, response, jsonReqData);
            }
        }
        if (WebTool.IsMoblie(request)) {
            System.out.println("手机登录界面.........................");
            LogWritter.sysError("手机登录界面.........................");
            return mapping.findForward("toMoblieLogin");
        } else {
            System.out.println("PC登录界面.........................");
            LogWritter.sysError("PC登录界面.........................");
            return mapping.findForward("toPcLogin");
        }
    }

    private void getYzm(HttpServletRequest request,
                        HttpServletResponse response, JsonReqData jsonReqData)
            throws IOException {
        String sjHm = jsonReqData.getSjHm();
        String passWord = jsonReqData.getPassWord();
        HashMap<String, String> reqMap = new HashMap<String, String>();
        reqMap.put("sjHm", sjHm);
        reqMap.put("passWord", passWord);
        BaseRequestEvent reqEvent = new BaseRequestEvent("LoginBLH",
                "CHANEL_WEB", "");
        reqEvent.setDealMethod("getYzm");
        reqEvent.setReqMapParam(reqMap);
        ResponseEvent resEvent = (ResponseEvent) BizDelegate.delegate(reqEvent);
        JsonResData resData = new JsonResData();
        resData.setCode(resEvent.getRepCode());
        if (null == resEvent.getReponseMesg()
                || "".equals(resEvent.getReponseMesg())) {
            resData.setMsg(resEvent.getRepCode());
        } else {
            resData.setMsg(resEvent.getReponseMesg());
        }
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().print(JSONSerializer.toJSON(resData).toString());
        response.getWriter().flush();
    }

    private void login(HttpServletRequest request,
                       HttpServletResponse response, JsonReqData jsonReqData)
            throws IOException {
        String sjHm = jsonReqData.getSjHm();
        String passWord = jsonReqData.getPassWord();
        String sjYzm = jsonReqData.getSjYzm();
        HashMap<String, String> reqMap = new HashMap<String, String>();
        reqMap.put("sjHm", sjHm);
        reqMap.put("passWord", passWord);
        reqMap.put("sjYzm", sjYzm);

        // 构造远程调用
        BaseRequestEvent reqEvent = new BaseRequestEvent("LoginBLH",
                "CHANEL_WEB", "");
        reqEvent.setDealMethod("login");
        reqEvent.setReqMapParam(reqMap);
        ResponseEvent resEvent = (ResponseEvent) BizDelegate.delegate(reqEvent);
        JsonResData resData = new JsonResData();
        if ("0".equals(resEvent.getRepCode())) {
            LoginVO loginVO = (LoginVO) resEvent.respMapParam.get("loginVO");
            request.getSession().setAttribute(loginVO.getYhwybz(), loginVO);
            Map<String, Object> voMap = new HashMap<String, Object>();
            voMap.put("vo", loginVO);
            voMap.put("sysDate", resEvent.respMapParam.get("sysDate"));
            resData.setData(voMap);
        }
        resData.setCode(resEvent.getRepCode());
        if (null == resEvent.getReponseMesg()
                || "".equals(resEvent.getReponseMesg())) {
            resData.setMsg(resEvent.getRepCode());
        } else {
            resData.setMsg(resEvent.getReponseMesg());
        }
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().print(JSONSerializer.toJSON(resData).toString());
        response.getWriter().flush();
    }

>>>>>>> 7ee792ab3a6adce0f74f65660e1b3e05cfff1406
}
