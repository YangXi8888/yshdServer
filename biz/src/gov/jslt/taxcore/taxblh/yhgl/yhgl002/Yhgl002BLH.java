package gov.jslt.taxcore.taxblh.yhgl.yhgl002;

import gov.jslt.taxevent.comm.JsonReqData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;

public class Yhgl002BLH extends BaseBizLogicHandler {
	protected ResponseEvent performTask(RequestEvent req, Connection conn)
			throws SQLException, TaxBaseBizException {
		String handleCode = req.getDealMethod();
		if ("submitForm".equals(handleCode)) {
			return submitForm(req, conn);
		}
		return null;
	}

	protected ResponseEvent validateData(RequestEvent req, Connection conn)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	protected ResponseEvent submitForm(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent resEvent = new ResponseEvent();
		JsonReqData reqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		PreparedStatement pt = conn
				.prepareStatement("UPDATE T_YS_WJMM S SET S.WJMM = ?,S.XG_SJ=SYSTIMESTAMP WHERE UUID =?");
		pt.setString(1, (String) reqData.getData().get("N_LOGPASS"));
		pt.setString(2, reqData.getYhwybz());
		pt.execute();
		pt.close();
		resEvent.setReponseMesg("文件密码修改成功!");
		return resEvent;
	}

}
