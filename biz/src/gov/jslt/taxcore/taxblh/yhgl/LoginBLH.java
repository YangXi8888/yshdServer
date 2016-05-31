package gov.jslt.taxcore.taxblh.yhgl;

<<<<<<< HEAD
import gov.jslt.taxcore.taxblh.comm.CoreHelper;
import gov.jslt.taxcore.taxblh.comm.MD5Helper;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.LoginVO;
=======
import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.bpo.QueryBPO;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.core.utility.dbtime.DBTimeServer;
import gov.jslt.taxcore.taxblh.comm.DxTools;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxevent.comm.JsonResData;
import gov.jslt.taxevent.comm.LoginVO;
import gov.jslt.taxevent.comm.MD5Helper;
import org.apache.commons.lang3.RandomStringUtils;
import sun.jdbc.rowset.CachedRowSet;
>>>>>>> 7ee792ab3a6adce0f74f65660e1b3e05cfff1406

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

<<<<<<< HEAD
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
=======
public class LoginBLH extends BaseBizLogicHandler {

    @Override
    protected ResponseEvent performTask(RequestEvent reqEvent, Connection conn)
            throws SQLException, TaxBaseBizException {
        if ("getYzm".equals(reqEvent.getDealMethod())) {
            return getYzm(reqEvent, conn);
        } else if ("login".equals(reqEvent.getDealMethod())) {
            return login(reqEvent, conn);
        }
        return null;
    }

    private ResponseEvent getYzm(RequestEvent reqEvent, Connection conn)
            throws SQLException {
        ResponseEvent resEvent = new ResponseEvent();
        String sjHm = (String) reqEvent.reqMapParam.get("sjHm");
        String passWord = (String) reqEvent.reqMapParam.get("passWord");
        String sjYzm = RandomStringUtils.randomNumeric(4);
        ArrayList<String> sqlParams = new ArrayList<String>();
        sqlParams.add(sjHm);
        sqlParams.add(MD5Helper.MD5(passWord));

        // 1. 确认手机号码和密码是否正确
        CachedRowSet rs = QueryBPO
                .findAll(
                        conn,
                        "SELECT COUNT(SJHM) AS NUM FROM T_ZB_LOGIN A WHERE A.SJHM = ? AND A.LOGPASS_MD5 = ? AND A.XY_BJ = '1'",
                        sqlParams);
        rs.next();
        if (rs.getInt("NUM") == 0) {
            resEvent.setRepCode("ZB0002");
            resEvent.setReponseMesg(ZBCoreHelper.getJyztMc(conn, "ZB0002"));
            return resEvent;
        }

        // 2.发送验证码
        JsonResData jsonResData = DxTools.sendYzm(conn, sjHm, sjYzm,
                DxTools.getYzmMb("登录自然人实名办税平台", sjYzm, "10"));

        // 3.记录验证码
        if ("0".equals(jsonResData.getCode())) {
            PreparedStatement pstmt = conn
                    .prepareStatement("DELETE FROM DB_ZSBS.T_ZB_YZM WHERE SJHM = ?");
            pstmt.setString(1, sjHm);
            pstmt.executeUpdate();
            pstmt = conn
                    .prepareStatement("INSERT INTO DB_ZSBS.T_ZB_YZM (UUID, SJHM, YZM, YXSJZ, LR_SJ) VALUES (?, ?, ?, SYSDATE + ((1 / 24 / 60) * 10), SYSTIMESTAMP)");
            pstmt.setString(1, ZBCoreHelper.getGUID(conn));
            pstmt.setString(2, sjHm);
            pstmt.setString(3, sjYzm);
            pstmt.executeUpdate();
            pstmt.close();
        }
        resEvent.setRepCode(jsonResData.getCode());
        resEvent.setReponseMesg(jsonResData.getMsg());
        return resEvent;
    }

    private ResponseEvent login(RequestEvent reqEvent, Connection conn)
            throws SQLException {
        ResponseEvent resEvent = new ResponseEvent();
        String sjHm = (String) reqEvent.reqMapParam.get("sjHm");
        String passWord = (String) reqEvent.reqMapParam.get("passWord");
        String sjYzm = (String) reqEvent.reqMapParam.get("sjYzm");
        LoginVO loginVO = new LoginVO();
        // 1.校验验证码
        ArrayList<String> sqlParams = new ArrayList<String>();
        sqlParams.add(sjHm);
        sqlParams.add(sjYzm);
        CachedRowSet rs = QueryBPO
                .findAll(
                        conn,
                        "SELECT COUNT(A.YZM) AS NUM FROM T_ZB_YZM A WHERE A.SJHM=? AND A.YZM = ? AND A.YXSJZ > SYSDATE",
                        sqlParams);
        rs.next();
        if (rs.getInt("NUM") == 0) {
            resEvent.setRepCode("ZB0001");
            resEvent.setReponseMesg(ZBCoreHelper.getJyztMc(conn, "ZB0001"));
            return resEvent;
        }
        // 2.根据证件类型、证件号码和密码md5查询帐号信息
        sqlParams = new ArrayList<String>();
        sqlParams.add(sjHm);
        sqlParams.add(MD5Helper.MD5(passWord));
        rs = QueryBPO
                .findAll(
                        conn,
                        "SELECT A.SFZMLX_DM,A.SFZHM,A.XM,A.SJHM,A.UUID FROM  T_ZB_LOGIN A WHERE A.SJHM=? AND A.LOGPASS_MD5=? AND XY_BJ='1'",
                        sqlParams);
        if (rs.size() == 0) {
            resEvent.setRepCode("ZB0002");
            resEvent.setReponseMesg(ZBCoreHelper.getJyztMc(conn, "ZB0002"));
            return resEvent;
        } else {
            rs.next();
            loginVO.setZjLx(rs.getString("SFZMLX_DM"));
            loginVO.setZjHm(rs.getString("SFZHM"));
            loginVO.setXm(rs.getString("XM"));
            loginVO.setSjHm(rs.getString("SJHM"));
            loginVO.setYhwybz(rs.getString("UUID"));
            sqlParams = new ArrayList<String>();
            sqlParams.add(loginVO.getYhwybz());
            rs = QueryBPO.findAll(conn,
                    "SELECT RZFS_DM FROM T_ZB_SMRZ WHERE UUID=? AND XY_BJ='1'",
                    sqlParams);
            if (rs.size() > 0) {
                String rzfs = "";
                while (rs.next()) {
                    rzfs += rs.getString("RZFS_DM") + "|";
                }
                loginVO.setRzFs(rzfs);
            }
            sqlParams = new ArrayList<String>();
            sqlParams.add(loginVO.getYhwybz());
            rs = QueryBPO
                    .findAll(
                            conn,
                            "SELECT SWGLM,DJXH FROM T_ZB_ZRRMRDJ WHERE UUID=?",
                            sqlParams);
            if (rs.next()) {
                loginVO.setDjxh(rs.getString("DJXH"));
                loginVO.setSwglm(rs.getString("SWGLM"));
            }
            resEvent.respMapParam.put("sysDate",
                    DBTimeServer.getDBTimesStr(conn, 3));
            resEvent.setSessionID(loginVO.getYhwybz());
            resEvent.respMapParam.put("loginVO", loginVO);
            resEvent.setReponseMesg("登录成功");
        }
        return resEvent;
    }
>>>>>>> 7ee792ab3a6adce0f74f65660e1b3e05cfff1406

    @Override
    protected ResponseEvent validateData(RequestEvent arg0, Connection arg1)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
