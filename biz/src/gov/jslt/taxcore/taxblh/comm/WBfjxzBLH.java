package gov.jslt.taxcore.taxblh.comm;

import gov.jslt.taxcore.taxbpo.comm.WBfjfileBPO;
import gov.jslt.taxevent.comm.WBfjfileVO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import gov.jslt.taxevent.comm.DateUtil;

public class WBfjxzBLH extends BaseBizLogicHandler {

	protected ResponseEvent performTask(RequestEvent req, Connection conn)
			throws SQLException, TaxBaseBizException {
		// TODO Auto-generated method stub
		Map map = (HashMap) req.getReqMapParam();
		if (((String) map.get("operation")).equals("queryMxnr")) {
			return queryXzwj(req, conn);
		}
		return null;
	}

	protected ResponseEvent validateData(RequestEvent arg0, Connection arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	protected ResponseEvent queryXzwj(RequestEvent req, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent reEvent = new ResponseEvent();
		Map map = (HashMap) req.getReqMapParam();
		String xh = (String) map.get("xh");
		String bizid = (String) map.get("bizid");
		WBfjfileVO fjvo = new WBfjfileVO();
		fjvo = WBfjfileBPO.queryByPK(conn, bizid, xh);
		map = new HashMap();
		map.put("fjvo", fjvo);
		reEvent.setRespMapParam((HashMap) map);
		return reEvent;
	}
}
