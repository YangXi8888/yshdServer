package gov.jslt.taxcore.taxblh.ggfw.sqhd.sq002;

import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.config.ApplicationContext;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import com.gargoylesoftware.htmlunit.BrowserVersion;

public class Sqhd002BLH extends BaseBizLogicHandler {
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
			if (url.indexOf("suzhou") != -1) {
				Document doc = Jsoup
						.connect(url)
						.timeout(
								Integer.parseInt(ApplicationContext.singleton()
										.getValueAsString("zsbs.web.timeout")))
						.get();
				Elements content = doc.select(".all_main");
				responseEvent.respMapParam.put("content", content.html());
			} else if (url.indexOf("nsrschool") != -1) {
				Document doc = Jsoup
						.connect(url)
						.timeout(
								Integer.parseInt(ApplicationContext.singleton()
										.getValueAsString("zsbs.web.timeout")))
						.get();
				responseEvent.respMapParam.put("content", doc.html());
			} else {
				Document doc = Jsoup
						.connect(url)
						.timeout(
								Integer.parseInt(ApplicationContext.singleton()
										.getValueAsString("zsbs.web.timeout")))
						.get();
				responseEvent.respMapParam
						.put("content",
								"<table width='100%' border='0' cellspacing='0' cellpadding='0'>"
										+ doc.getElementsByAttributeValue("id",
												"article").get(1).html()
												.replaceAll("\\访问次数：", "")
										+ "</table>");
			}
		} catch (Exception e) {
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

	protected ResponseEvent queryTitle(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		try {
			JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
					.get("JsonReqData");
			Map<String, Object> dataMap = jsonReqData.getData();
			String colname = (String) dataMap.get("id");
			int currentPage = Integer.parseInt(dataMap.get("CurrentPage")
					.toString());
			String url = ZBCoreHelper.getColnum_bsxt(colname);
			List<Map<String, String>> urlList = new ArrayList<Map<String, String>>();
			if ("23204".equals(colname)) {
				urlList = getCzTitleList(url, currentPage);
			} else if ("23205".equals(colname)) {
				urlList = getSzTitleList(url, currentPage);
			} else {
				urlList = getCommonTitleList(url, currentPage);
			}
			responseEvent.respMapParam.put("urlList", urlList);
			responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
			responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		} catch (Exception e) {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					ZBCoreHelper.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
					e.getMessage()));
			return responseEvent;
		}
		return responseEvent;

	}

	private List<Map<String, String>> getCzTitleList(String url, int currentPage)
			throws NumberFormatException, IOException {
		List<Map<String, String>> urlList = new ArrayList<Map<String, String>>();
		// 常州只查询一页
		if (currentPage == 1) {
			Document doc = Jsoup
					.connect(url)
					.timeout(
							Integer.parseInt(ApplicationContext.singleton()
									.getValueAsString("zsbs.web.timeout")))
					.get();
			Elements btLb = doc.select("td span[class=details]");
			Map<String, String> map = null;
			for (Element webElement : btLb) {
				map = new HashMap<String, String>();
				map.put("href", "http://218.93.18.190:81/nsrschool/"
						+ webElement.attr("onclick").split("\\'")[1]);
				map.put("title", webElement.text());
				urlList.add(map);
			}
		}
		return urlList;

	}

	private List<Map<String, String>> getSzTitleList(String url, int currentPage)
			throws NumberFormatException, InterruptedException {
		List<Map<String, String>> urlList = new ArrayList<Map<String, String>>();
//		HtmlUnitDriver driver = new HtmlUnitDriver(
//				BrowserVersion.INTERNET_EXPLORER_8);
//		try {
//			driver.setJavascriptEnabled(true);
//			driver.get(url);
//			if (driver.findElementsByCssSelector(".page-next").size() != 0) {
//				if (currentPage > Integer.parseInt(driver
//						.findElementByClassName("default_pgTotalPage")
//						.getText().trim())) {
//					return urlList;
//				}
//				if (currentPage != 1) {
//					for (int i = 1; i < currentPage; i++) {
//						driver.findElementsByCssSelector(".page-next").get(0)
//								.click();
//					}
//					if (currentPage > 3) {
//						Thread.sleep(Long.parseLong("1000"));
//					}
//				}
//			} else {
//				if (currentPage > 1) {
//					return urlList;
//				}
//			}
//			Document doc = Jsoup.parse(driver.getPageSource());
//			Elements btLb = doc
//					.select("td[class=service_height] a[href][target=_blank]");
//			Map<String, String> map = null;
//			for (Element webElement : btLb) {
//				if (null != webElement.attributes().get("style")
//						&& !"".equals(webElement.attributes().get("style"))
//						&& null != webElement.attributes().get("target")
//						&& !"".equals(webElement.attributes().get("target"))) {
//					map = new HashMap<String, String>();
//					map.put("href", "http://school.cmclouds.com"
//							+ webElement.attributes().get("href"));
//					map.put("title", webElement.text());
//					urlList.add(map);
//				}
//			}
//			driver.close();
//		} catch (Exception e) {
//			driver.close();
//			return urlList;
//		}
		return urlList;

	}

	private List<Map<String, String>> getCommonTitleList(String url,
			int currentPage) throws NumberFormatException, InterruptedException {
		HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME);
		List<Map<String, String>> urlList = new ArrayList<Map<String, String>>();
		try {
			driver.setJavascriptEnabled(true);
			driver.get(url);
			Map<String, String> map = null;
			if (driver.findElementsByCssSelector(".default_pgNext").size() != 0) {
				if (currentPage > Integer.parseInt(driver
						.findElementByClassName("default_pgTotalPage")
						.getText().trim())) {
					return urlList;
				}
				if (currentPage != 1) {
					for (int i = 1; i < currentPage; i++) {
						driver.findElementsByCssSelector(".default_pgNext")
								.get(0).click();
					}
					if (currentPage > 3) {
						Thread.sleep(Long.parseLong("1000"));
					}
				}
			} else {
				if (currentPage > 1) {
					return urlList;
				}
			}
			List<WebElement> btLb = driver
					.findElementsByCssSelector("a[class=bt_link]");
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
			return urlList;
		}
		return urlList;

	}

	@Override
	protected ResponseEvent validateData(RequestEvent arg0, Connection arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
