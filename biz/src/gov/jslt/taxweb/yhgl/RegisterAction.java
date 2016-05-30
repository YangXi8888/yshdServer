package gov.jslt.taxweb.yhgl;

import com.ctp.core.bizdelegate.BizDelegate;
import com.ctp.core.event.BaseRequestEvent;
import com.ctp.core.event.ResponseEvent;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.JsonResData;
import gov.jslt.taxweb.comm.WebTool;
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

public class RegisterAction extends Action {

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
            jsonConfig.setRootClass(JsonReqData.class);
            jsonReqData = (JsonReqData) JSONSerializer.toJava(jsonObject,
                    jsonConfig);
            if ("getYzm".equals(jsonReqData.getHandleCode())) {
                getYzm(request, response, jsonReqData);
            } else if ("submitData".equals(jsonReqData.getHandleCode())) {
                submitData(request, response, jsonReqData);
            }
        }
        if (WebTool.IsMoblie(request)) {
            return mapping.findForward("toMoblieRegister");
        } else {
            return mapping.findForward("toPcRegister");
        }
    }

    private void getYzm(HttpServletRequest request,
                        HttpServletResponse response, JsonReqData jsonReqData)
            throws IOException {
        String zjlxDm = (String) jsonReqData.getData().get("zjlxDm");
        String zjHm = (String) jsonReqData.getData().get("zjHm");
        String sjHm = jsonReqData.getSjHm();
        HashMap<String, String> reqMap = new HashMap<String, String>();
        reqMap.put("zjlxDm", zjlxDm);
        reqMap.put("zjHm", zjHm);
        reqMap.put("sjHm", sjHm);
        BaseRequestEvent reqEvent = new BaseRequestEvent("RegisterBLH",
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

    private ResponseEvent submitData(HttpServletRequest request,
                                     HttpServletResponse response, JsonReqData jsonReqData)
            throws IOException {
        String xm = jsonReqData.getXm();
        String zjlxDm = (String) jsonReqData.getData().get("zjlxDm");
        String zjHm = (String) jsonReqData.getData().get("zjHm");
        String ylKh = (String) jsonReqData.getData().get("ylKh");
        String rzfsDm = (String) jsonReqData.getData().get("rzfsDm");
        String passWord = jsonReqData.getPassWord();
        String sjHm = jsonReqData.getSjHm();
        String sjYzm = jsonReqData.getSjYzm();
        HashMap<String, String> reqMap = new HashMap<String, String>();
        reqMap.put("xm", xm);
        reqMap.put("zjlxDm", zjlxDm);
        reqMap.put("zjHm", zjHm);
        reqMap.put("passWord", passWord);
        reqMap.put("sjHm", sjHm);
        reqMap.put("sjYzm", sjYzm);
        reqMap.put("ylKh", ylKh);
        reqMap.put("rzfsDm", rzfsDm);

        // 构造远程调用
        BaseRequestEvent reqEvent = new BaseRequestEvent("RegisterBLH",
                "CHANEL_WEB", "");
        reqEvent.setDealMethod("submitData");
        reqEvent.setReqMapParam(reqMap);
        ResponseEvent resEvent = (ResponseEvent) BizDelegate.delegate(reqEvent);
        // 封装返回数据
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
        return resEvent;
    }

}
