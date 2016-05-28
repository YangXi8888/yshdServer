package gov.jslt.taxcore.taxblh.ggfw.bsdh.bsdh001;

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

import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.bpo.QueryCssBPO;
import com.ctp.core.config.ApplicationContext;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;

public class Bsdh001BLH extends BaseBizLogicHandler {
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws TaxBaseBizException, SQLException {
		String delMethod = req.getDealMethod();
		if ("queryContent".equals(delMethod)) {
			return queryContent(req, con);
		}else if("queryBsrl".equals(delMethod)){
			return queryBsrl(req,con);
		}
		return null;
	}

	//办税日历
	private ResponseEvent queryBsrl(RequestEvent req, Connection con) 
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String dsqx_dm  = (String)dataMap.get("dsqx_dm");
		String sql = "SELECT B.DSQX_DM, B.YF, B.SBQ, B.NR FROM DB_ZSBS.T_ZB_BSRL B WHERE B.DSQX_DM = ?";
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(dsqx_dm);
		CachedRowSet rs = QueryCssBPO.findAll(con, sql, sqlParams);
		List<Map<String,String>> bsrlList = new ArrayList<Map<String,String>>();
		Map<String,String> bsrlMap;
		while(rs.next()){
			bsrlMap = new HashMap<String,String>();
			bsrlMap.put("dsqx_dm", rs.getString("DSQX_DM"));
			bsrlMap.put("yf", rs.getString("YF"));
			bsrlMap.put("sbq", rs.getString("SBQ"));
			bsrlMap.put("nr", rs.getString("NR"));
			bsrlList.add(bsrlMap);
		}
		responseEvent.respMapParam.put("bsrlList", bsrlList);
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);;
		return responseEvent;
	}

	protected ResponseEvent queryContent(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String id = (String) dataMap.get("id");
		String url = ZBCoreHelper.getColnum_bsrl(id);
		try {

			Document doc = Jsoup
					.connect(url)
					.timeout(
							Integer.parseInt(ApplicationContext.singleton()
									.getValueAsString("zsbs.web.timeout")))
					.get();
			responseEvent.respMapParam.put("title", doc.select(".title").get(0)
					.text());
			responseEvent.respMapParam.put("content", doc.select(".bt_content")
					.get(0).html());
		} catch (Exception e) {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			responseEvent
					.setReponseMesg(JyxxJgbHelper.queryJyxxx(ZBCoreHelper
							.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
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
