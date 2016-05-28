package gov.jslt.taxcore.taxblh.comm;

import gov.jslt.taxevent.comm.GeneralCons;

import java.sql.Connection;
import java.sql.SQLException;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;

public class MainBLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		String delMethod = req.getDealMethod();
		if ("queryDate".equals(delMethod)) {
			return queryDate(req, con);
		}
		return null;
	}

	protected ResponseEvent queryDate(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		responseEvent.respMapParam.put("curDate",
				ZBCoreHelper.getDateTime(con, "YYYY-MM-DD"));
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_CODE);
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
