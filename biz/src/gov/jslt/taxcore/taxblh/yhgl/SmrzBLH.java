package gov.jslt.taxcore.taxblh.yhgl;

import gov.jslt.taxcore.taxblh.comm.SmrzTools;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.JsonResData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;

public class SmrzBLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		if ("submitData".equals(reqEvent.getDealMethod())) {
			return submitData(reqEvent, conn);
		}
		return null;
	}

	private ResponseEvent submitData(RequestEvent reqEvent, Connection conn)
			throws SQLException {
		ResponseEvent resEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		String xm = jsonReqData.getXm();
		String sjHm = jsonReqData.getSjHm();
		String zjHm = (String) jsonReqData.getData().get("zjHm");
		String rzfsDm = (String) jsonReqData.getData().get("rzfsDm");
		String ylKh = (String) jsonReqData.getData().get("ylKh");
		// 1.进行认证
		JsonResData jsonResData = SmrzTools.rzByYl(xm, sjHm, zjHm, ylKh,
				jsonReqData.getYhwybz(), conn);
		// 2.保存认证信息
		if ("0".equals(jsonResData.getCode())) {
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO DB_ZSBS.T_ZB_SMRZ (UUID, RZFS_DM, XY_BJ, LR_SJ, XG_SJ) VALUES (?, ?, ?, SYSTIMESTAMP, NULL)");
			pstmt.setString(1, jsonReqData.getYhwybz());
			pstmt.setString(2, rzfsDm);
			pstmt.setString(3, "1");
			pstmt.execute();
			pstmt.close();
		} else {
			resEvent.setRepCode(jsonResData.getCode());
			resEvent.setReponseMesg(jsonResData.getMsg());
			return resEvent;
		}
		resEvent.setReponseMesg(ZBCoreHelper.getJyztMc(conn, "ZB0041"));
		return resEvent;
	}

	@Override
	protected ResponseEvent validateData(RequestEvent arg0, Connection arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
