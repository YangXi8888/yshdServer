package gov.jslt.taxcore.taxblh.bsfw.cxfw.cx005;

import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.core.log.LogWritter;
import com.ctp.cssesb.esbevent.ESBRequestEvent;

public class Cxfw005BLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		String delMethod = req.getDealMethod();
		if ("nsxypj".equals(delMethod)) {
			return queryMxxx(req, con);
		}
		return null;
	}

	protected ResponseEvent queryMxxx(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		LogWritter.sysError("开始调用ESB.......................");
		ESBRequestEvent baseRequest = new ESBRequestEvent("Cxfw005BLH", "", "");
		baseRequest.setReqMapParam(new HashMap<>());
		baseRequest.setDealMethod("queryMxxx");
		baseRequest.reqMapParam.put("swglm", (String) dataMap.get("swglm")
				.toString());
		baseRequest.reqMapParam.put("ssnd", (String) dataMap.get("ssnd")
				.toString());
		try {
			responseEvent = ZBCoreHelper.getEsbResponseEvent(baseRequest, con);
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
	protected ResponseEvent validateData(RequestEvent req, Connection con)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
