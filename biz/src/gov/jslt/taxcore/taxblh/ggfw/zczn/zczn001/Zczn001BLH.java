package gov.jslt.taxcore.taxblh.ggfw.zczn.zczn001;

import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;

import java.net.URLEncoder;
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

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.config.ApplicationContext;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.core.log.LogWritter;

public class Zczn001BLH extends BaseBizLogicHandler {

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
		String id = (String) dataMap.get("id");
		String url = ApplicationContext.singleton().getValueAsString(
				"12366.server.www")
				+ "/sword?tid=ZskjsBLH_findFgContent&id=" + id;
		LogWritter.sysInfo("调用地址为:" + url);
		try {
			Document doc = Jsoup
					.connect(url)
					.timeout(
							Integer.parseInt(ApplicationContext.singleton()
									.getValueAsString("zsbs.web.timeout")))
					.get();
			responseEvent.respMapParam.put("title",
					doc.select("div.border_main div.tit").get(0).text());
			responseEvent.respMapParam.put("wsh", doc.select("div.info").get(0)
					.text());
			responseEvent.respMapParam.put("content", doc
					.getElementById("zoom").html());
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
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(ApplicationContext.singleton().getValueAsString(
				"12366.server.www")
				+ "/zrarpages/main/zsk/zlkjs/gjjs_main.jsp?");
		try {
			if (null != dataMap.get("iCurrentPage")
					&& !"".equals(dataMap.get("iCurrentPage"))) {
				stringBuilder.append("iCurrentPage="
						+ dataMap.get("iCurrentPage"));
			} else {
				dataMap.get("iCurrentPage=1");
			}
			if (null != dataMap.get("title")
					&& !"".equals(dataMap.get("title"))) {

				stringBuilder.append("&title="
						+ URLEncoder.encode((String) dataMap.get("title"),
								"UTF-8"));
			}

			if (null != dataMap.get("zlnr") && !"".equals(dataMap.get("zlnr"))) {
				stringBuilder.append("&zlnr="
						+ URLEncoder.encode((String) dataMap.get("zlnr"),
								"UTF-8"));
			}
			if (null != dataMap.get("zlzhczsjStart")
					&& !"".equals(dataMap.get("zlzhczsjStart"))) {
				stringBuilder.append("&zlzhczsjStart="
						+ (String) dataMap.get("zlzhczsjStart"));
			}

			if (null != dataMap.get("zlzhczsjEnd")
					&& !"".equals(dataMap.get("zlzhczsjEnd"))) {
				stringBuilder.append("&zlzhczsjEnd="
						+ (String) dataMap.get("zlzhczsjEnd"));
			}

			LogWritter.sysError("访问地址为:" + stringBuilder.toString());

			List<Map<String, String>> urlList = new ArrayList<Map<String, String>>();

			Document doc = Jsoup
					.connect(stringBuilder.toString())
					.timeout(
							Integer.parseInt(ApplicationContext.singleton()
									.getValueAsString("zsbs.web.timeout")))
					.get();

			if (doc.getElementsByAttributeValue("name", "page").size() == 0) {
				responseEvent.respMapParam.put("urlList", urlList);
				responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
				responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
				return responseEvent;
			}
			// 判断是否超过最大页数，如果超过返回空集合
			if (Integer.parseInt(dataMap.get("iCurrentPage").toString()) > doc
					.getElementsByAttributeValue("name", "page").get(0)
					.getElementsByTag("option").size()) {
				responseEvent.respMapParam.put("urlList", urlList);
				responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
				responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
				return responseEvent;
			}
			Elements btLb = doc.select("div.bd a");
			Map<String, String> map = null;
			for (Element webElement : btLb) {
				map = new HashMap<String, String>();
				map.put("href",
						webElement.attributes().get("href").split("'")[1]);
				map.put("title", webElement.attributes().get("title"));
				urlList.add(map);
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

	@Override
	protected ResponseEvent validateData(RequestEvent arg0, Connection arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
