package gov.jslt.taxcore.taxblh.ggfw.zczn.zczn002;

import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.config.ApplicationContext;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import com.gargoylesoftware.htmlunit.BrowserVersion;

public class Zczn002BLH extends BaseBizLogicHandler {
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws TaxBaseBizException, SQLException {
		String delMethod = req.getDealMethod();
		if ("queryTitle".equals(delMethod)) {
			return queryTitle(req, con);
		} else if ("queryContent".equals(delMethod)) {
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
		String url = (String) dataMap.get("url");
		try {
			Document doc = Jsoup
					.connect(url)
					.timeout(
							Integer.parseInt(ApplicationContext.singleton()
									.getValueAsString("zsbs.web.timeout")))
					.get();
			responseEvent.respMapParam.put("title", doc.getElementById("title")
					.text());
			responseEvent.respMapParam.put("fbsj", doc
					.getElementById("pubdate").text());
			responseEvent.respMapParam
					.put("content",
							doc.getElementById("zoom")
									.html()
									.replace("src=\"/",
											"src=\"http://pub.jsds.gov.cn/")
									.replace("href=\"/",
											"href=\"http://pub.jsds.gov.cn/"));
		} catch (Exception e) {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			responseEvent.setReponseMesg(ZBCoreHelper.getJyztMc(con,
					GeneralCons.ERROR_CODE_ZB9999));
			return responseEvent;
		}
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	protected ResponseEvent queryTitle(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String colname = (String) dataMap.get("colname");
		String colnum = ZBCoreHelper.getColnum_ssyb(colname);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(ApplicationContext.singleton().getValueAsString(
				"sjmh.server.www")
				+ "/col/");
		stringBuilder.append(colnum + "/index.html");
		List<Map<String, String>> urlList = new ArrayList<Map<String, String>>();
		HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME);
		try {
			driver.setJavascriptEnabled(true);
			driver.get(stringBuilder.toString());
			List<WebElement> btLb = driver
					.findElementsByCssSelector("td a[target=_blank][class=bt_link]");
			Map<String, String> map = null;
			for (WebElement webElement : btLb) {
				if (null != webElement.getAttribute("title")
						&& !"".equals(webElement.getAttribute("title"))) {
					map = new HashMap<String, String>();
					map.put("href", webElement.getAttribute("href"));
					map.put("title", webElement.getAttribute("title"));
					urlList.add(map);
				}
			}
			driver.close();
		} catch (Exception e) {
			driver.close();
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					ZBCoreHelper.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
					e.getMessage()));
			return responseEvent;
		}
		responseEvent.respMapParam.put("urlList", urlList);
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
