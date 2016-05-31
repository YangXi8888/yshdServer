package gov.jslt.taxweb.comm;

import com.ctp.core.bizdelegate.BizDelegate;
import com.ctp.core.event.BaseRequestEvent;
import com.ctp.core.event.ResponseEvent;
import gov.jslt.taxevent.comm.WBfjfileVO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;

public class WBfjxzAction extends Action {

    private String BLHNAME = "WBfjxzBLH";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        if (request.getParameter("XH") != null) {
            String XH = request.getParameter("XH").trim();
            String BIZID = request.getParameter("BIZID").trim();
            BaseRequestEvent baseRequest = null;
            HashMap map = null;
            ResponseEvent res = null;
            baseRequest = new BaseRequestEvent(BLHNAME, "", "");
            map = new HashMap();
            map.put("operation", "queryMxnr");
            map.put("bizid", BIZID);
            map.put("xh", XH);
            baseRequest.setReqMapParam((HashMap) map);
            res = (ResponseEvent) BizDelegate.delegate(baseRequest);
            map = res.getRespMapParam();
            WBfjfileVO fjvo = (WBfjfileVO) map.get("fjvo");
            String fileName = fjvo.getWjm();
            String filetype = fileName.substring(fileName.lastIndexOf("."));
            String contentType = getContext(filetype);
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "attachment; filename=\""
                    + URLEncoder.encode(fileName, "UTF-8") + "\"");
            response.resetBuffer();
            OutputStream sos = response.getOutputStream();
            sos.write(fjvo.getWjnr().getContent());
            sos.flush();
            sos.close();
        }

        return null;
    }

    public static String getContext(String filetype) {
        String contentType = "";
        if (filetype == null || filetype.equals("")) {
            contentType = "application/octet-stream;charset=UTF-8";
        } else if (filetype.equalsIgnoreCase(".chm")) {
            contentType = "application/msword;charset=UTF-8";
        } else if (filetype.equalsIgnoreCase(".doc")) {
            contentType = "application/msword;charset=GBK";
        } else if (filetype.equalsIgnoreCase(".xls")) {
            contentType = "application/vnd.ms-excel;charset=UTF-8";
        } else if (filetype.equalsIgnoreCase(".ppt")) {
            contentType = "application/vnd.ms-powerpoint;charset=UTF-8";
        } else if (filetype.equalsIgnoreCase(".txt")) {
            contentType = "text/plain;charset=UTF-8";
        } else if (filetype.equalsIgnoreCase(".pdf")) {
            contentType = "application/pdf;charset=UTF-8";
        }
        return contentType;
    }
}
