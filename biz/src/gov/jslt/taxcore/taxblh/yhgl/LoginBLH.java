package gov.jslt.taxcore.taxblh.yhgl;


import gov.jslt.taxcore.taxblh.comm.CoreHelper;
import gov.jslt.taxcore.taxblh.comm.MD5Helper;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.LoginVO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.bpo.QueryBPO;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.core.utility.dbtime.DBTimeServer;

public class LoginBLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		if ("login".equals(reqEvent.getDealMethod())) {
			return login(reqEvent, conn);
		}
		return null;
	}

	private ResponseEvent login(RequestEvent reqEvent, Connection conn)
			throws SQLException {
		ResponseEvent resEvent = new ResponseEvent();
		JsonReqData reqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		String sjHm = (String) reqData.getData().get("sjHm");
		String passWord = (String) reqData.getData().get("passWord");
		String yzm = (String) reqData.getData().get("yzm");
		HttpServletRequest request = (HttpServletRequest) reqEvent.reqMapParam
				.get("HttpServletRequest");

		// 1.校验验证码
		if (!yzm.equals(request.getSession().getAttribute("yzm"))) {
			resEvent.setRepCode("ZB0001");
			resEvent.setReponseMesg(CoreHelper.getJyztMc(conn, "ZB0001"));
			return resEvent;
		}

		LoginVO loginVO = new LoginVO();
		CachedRowSet rs;
		// 根据证件类型、证件号码和密码md5查询帐号信息
		ArrayList<String> sqlParams = new ArrayList<String>();
		sqlParams.add(sjHm);
		sqlParams.add(MD5Helper.MD5(passWord));
		rs = QueryBPO
				.findAll(
						conn,
						"SELECT A.SFZMLX_DM,A.SFZHM,A.XM,A.SJHM,A.UUID,A.YHLX_DM,A.QYYH_DM FROM  T_YS_LOGIN A WHERE A.SJHM=? AND A.LOGPASS_MD5=? AND XY_BJ='1'",
						sqlParams);
		if (rs.size() == 0) {
			resEvent.setRepCode("ZB0002");
			resEvent.setReponseMesg(CoreHelper.getJyztMc(conn, "ZB0002"));
			return resEvent;
		} else {
			rs.next();
			loginVO.setZjLx(rs.getString("SFZMLX_DM"));
			loginVO.setZjHm(rs.getString("SFZHM"));
			loginVO.setXm(rs.getString("XM"));
			loginVO.setSjHm(rs.getString("SJHM"));
			loginVO.setYhwybz(rs.getString("UUID"));
			loginVO.setYhLxDm(rs.getString("YHLX_DM"));
			loginVO.setQyYhDm(rs.getString("QYYH_DM"));
			resEvent.respMapParam.put("sysDate",
					DBTimeServer.getDBTimesStr(conn, 3));
			resEvent.setSessionID(loginVO.getYhwybz());
			request.getSession().setAttribute(rs.getString("UUID"), loginVO);
			resEvent.respMapParam.put("loginVO", loginVO);
			resEvent.setReponseMesg("登录成功");
		}
		return resEvent;
	}

    @Override
    protected ResponseEvent validateData(RequestEvent arg0, Connection arg1)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
