package gov.jslt.taxcore.taxblh.bsfw.cxfw.cx003;

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

public class Cxfw003BLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		String delMethod = req.getDealMethod();
		if ("fpcx".equals(delMethod)) {
			return fpcx(req, con);
		}
		return null;
	}

	// 发票信息查询
	protected ResponseEvent fpcx(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		Map<String, String> map;
		map = new HashMap<String, String>();
		map.put("fpdm", (String) dataMap.get("param2"));
		map.put("fphm", (String) dataMap.get("param3"));
		map.put("txm", (String) dataMap.get("param1"));
		LogWritter.sysError("发票信息查询开始调用ESB.......................");
		ESBRequestEvent baseRequest = new ESBRequestEvent("Cxfw003BLH", "", "");
		baseRequest.setReqMapParam(new HashMap<>());
		baseRequest.setDealMethod("fpcx");
		baseRequest.reqMapParam.put("fpdm", (String) dataMap.get("param2"));
		baseRequest.reqMapParam.put("fphm", (String) dataMap.get("param3"));
		baseRequest.reqMapParam.put("txm", (String) dataMap.get("param1"));
		LogWritter.sysError("发票代码:" + (String) dataMap.get("param2"));
		LogWritter.sysError("发票号码:" + (String) dataMap.get("param3"));
		LogWritter.sysError("发票条形码:" + (String) dataMap.get("param1"));
		try {
			responseEvent = ZBCoreHelper.getEsbResponseEvent(baseRequest, con);
		} catch (Exception e) {
			LogWritter.sysError("发票信息查询调用ESB出错:" + e.getMessage());
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					ZBCoreHelper.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
					e.getMessage()));
			return responseEvent;
		}
		LogWritter.sysError("发票信息查询调用ESB结束返回参数:"
				+ responseEvent.respMapParam.toString());
		return responseEvent;

	}

	@Override
	protected ResponseEvent validateData(RequestEvent req, Connection con)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
