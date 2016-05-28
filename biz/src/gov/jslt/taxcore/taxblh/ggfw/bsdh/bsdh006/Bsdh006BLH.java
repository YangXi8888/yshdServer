package gov.jslt.taxcore.taxblh.ggfw.bsdh.bsdh006;

import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import cn.edu.hfut.dmic.contentextractor.ContentExtractor;
import cn.edu.hfut.dmic.contentextractor.News;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import com.gargoylesoftware.htmlunit.BrowserVersion;

public class Bsdh006BLH extends BaseBizLogicHandler {
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws TaxBaseBizException, SQLException {
		String delMethod = req.getDealMethod();
		if ("queryContent".equals(delMethod)) {
			return queryContent(req, con);
		}
		return null;
	}

	protected ResponseEvent queryContent(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String id = (String) dataMap.get("id");
		String url = ZBCoreHelper.getColnum_szsl(id);
		HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME);
		try {
			driver.setJavascriptEnabled(true);
			driver.get(url);
			responseEvent.respMapParam.put("title",
					driver.findElementByName("title").getAttribute("content"));
			driver.close();
			HttpRequest request = new HttpRequest(url);
			String html = request.getResponse().getHtmlByCharsetDetect();
			News news = ContentExtractor.getNewsByHtml(html, url);
			responseEvent.respMapParam.put("content", news.getContentElement()
					.html());
		} catch (Exception e) {
			driver.close();
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					ZBCoreHelper.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
					e.getMessage()));
			return responseEvent;
		}
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	@Override
	protected ResponseEvent validateData(RequestEvent arg0, Connection arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
