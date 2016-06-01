package gov.jslt.taxweb.comm;

import gov.jslt.taxevent.comm.FileVO;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.JsonResData;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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

public class GeneralAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {
		JsonResData resData = new JsonResData();
		JsonReqData jsonReqData = new JsonReqData();
		String callback = null;
		try {

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

			if ("0".equals(jsonReqData.getDownLoadFile())) {
				response.setContentType("text/plain;charset=UTF-8");
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
			} else {
				response.setHeader("Set-Cookie", "fileDownload=true; path=/");
				FileVO fileVO = (FileVO) responseEvent.respMapParam
						.get(GeneralCons.FILE_VO);
				response.setContentType(WebTool.getContext(fileVO.getFileType()));
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + URLEncoder.encode(fileVO.getFileName(),"UTF-8") + "\"");
				response.resetBuffer();
				OutputStream sos = response.getOutputStream();
				sos.write(fileVO.getFileContent());
				sos.flush();
				sos.close();
			}
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
