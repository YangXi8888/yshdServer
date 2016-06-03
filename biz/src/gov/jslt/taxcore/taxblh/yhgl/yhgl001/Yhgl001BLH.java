package gov.jslt.taxcore.taxblh.yhgl.yhgl001;

import gov.jslt.taxcore.taxblh.comm.CoreHelper;
import gov.jslt.taxcore.taxblh.comm.MD5Helper;
import gov.jslt.taxevent.comm.JsonReqData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.bpo.QueryBPO;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;

public class Yhgl001BLH extends BaseBizLogicHandler {
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
		ArrayList<String> sqlParams = new ArrayList<String>();
		sqlParams.add(reqData.getYhwybz());
		sqlParams.add(MD5Helper
				.MD5((String) reqData.getData().get("Y_LOGPASS")));
		CachedRowSet rs = QueryBPO
				.findAll(
						conn,
						"SELECT COUNT(A.UUID) AS N FROM  T_YS_LOGIN A WHERE A.UUID=? AND A.LOGPASS_MD5=? AND XY_BJ='1'",
						sqlParams);
		rs.next();
		if (rs.getInt("N") == 0) {
			resEvent.setRepCode("ZB0003");
			resEvent.setReponseMesg(CoreHelper.getJyztMc(conn, "ZB0003"));
		} else {
			PreparedStatement pt = conn
					.prepareStatement("UPDATE T_YS_LOGIN S SET S.LOGPASS_MD5 = ?,S.LOGPASS = ?,S.XG_SJ=SYSTIMESTAMP WHERE UUID =?");
			pt.setString(1,
					MD5Helper.MD5((String) reqData.getData().get("N_LOGPASS")));
			pt.setString(2, (String) reqData.getData().get("N_LOGPASS"));
			pt.setString(3, reqData.getYhwybz());
			pt.execute();
			pt.close();
			resEvent.setReponseMesg("登录密码修改成功!");
		}
		return resEvent;
	}

}
