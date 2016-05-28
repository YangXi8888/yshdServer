package gov.jslt.taxcore.taxblh.ggfw.bsdh.bsdh004;

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

public class Bsdh004BLH extends BaseBizLogicHandler {
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws TaxBaseBizException, SQLException {
		String delMethod = req.getDealMethod();
		if ("queryData".equals(delMethod)) {
			return queryData(req, con);
		}if ("queryMxxx".equals(delMethod)) {
			return queryMxxx(req, con);
		}
		return null;
	}

	protected ResponseEvent queryData(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String id = (String) dataMap.get("id");
		String bsfwt = ZBCoreHelper.getBsfwt(id);		
		 LogWritter.sysError("开始调用ESB.......................");
		    try {
		    ESBRequestEvent baseRequest = new ESBRequestEvent(
					"Bsdh004BLH", "", "");
		    baseRequest.setReqMapParam(new HashMap<>());
			baseRequest.setDealMethod("queryData");
			baseRequest.reqMapParam.put("bsfwt",bsfwt );
		    responseEvent =ZBCoreHelper.getEsbResponseEvent(baseRequest,con);
		    } catch (Exception e) {
				responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
				responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
						ZBCoreHelper.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
						e.getMessage()));
				return responseEvent;
			}
		return responseEvent;
	}
	
	protected ResponseEvent queryMxxx(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String muid = String.valueOf( dataMap.get("muid")) ;	
		 LogWritter.sysError("开始调用ESB.......................");
		    try {
		    ESBRequestEvent baseRequest = new ESBRequestEvent(
					"Bsdh004BLH", "", "");
		    baseRequest.setReqMapParam(new HashMap<>());
			baseRequest.setDealMethod("queryMxxx");
			baseRequest.reqMapParam.put("muid",muid );
		    responseEvent =ZBCoreHelper.getEsbResponseEvent(baseRequest,con);	
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
