package gov.jslt.taxcore.taxblh.yhgl;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.bpo.QueryBPO;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import gov.jslt.taxcore.taxblh.comm.DxTools;
import gov.jslt.taxcore.taxblh.comm.SmrzTools;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxevent.comm.JsonResData;
import gov.jslt.taxevent.comm.MD5Helper;
import org.apache.commons.lang3.RandomStringUtils;
import sun.jdbc.rowset.CachedRowSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReAuthentBLH extends BaseBizLogicHandler {

    @Override
    protected ResponseEvent performTask(RequestEvent reqEvent, Connection conn)
            throws SQLException, TaxBaseBizException {
        if ("getYzm".equals(reqEvent.getDealMethod())) {
            return getYzm(reqEvent, conn);
        } else if ("submitData".equals(reqEvent.getDealMethod())) {
            return submitData(reqEvent, conn);
        }
        return null;
    }

    private ResponseEvent submitData(RequestEvent reqEvent, Connection conn)
            throws SQLException {
        ResponseEvent resEvent = new ResponseEvent();
        String xm = (String) reqEvent.reqMapParam.get("xm");
        String zjlxDm = (String) reqEvent.reqMapParam.get("zjlxDm");
        String zjHm = (String) reqEvent.reqMapParam.get("zjHm");
        String ylKh = (String) reqEvent.reqMapParam.get("ylKh");
        String rzfsDm = (String) reqEvent.reqMapParam.get("rzfsDm");
        String passWord = (String) reqEvent.reqMapParam.get("passWord");
        String sjHm = (String) reqEvent.reqMapParam.get("sjHm");
        String sjYzm = (String) reqEvent.reqMapParam.get("sjYzm");

        // 1.校验验证码
        ArrayList<String> sqlParams = new ArrayList<String>();
        sqlParams.add(sjHm);
        sqlParams.add(sjYzm);
        CachedRowSet rs = QueryBPO
                .findAll(
                        conn,
                        "SELECT A.YZM AS NUM FROM T_ZB_YZM A WHERE A.SJHM=? AND A.YZM = ? AND A.YXSJZ > SYSDATE",
                        sqlParams);
        if (rs.size() == 0) {
            resEvent.setRepCode("ZB0001");
            resEvent.setReponseMesg(ZBCoreHelper.getJyztMc(conn, "ZB0001"));
            return resEvent;
        }

        // 2.验证证件号码或手机号码是否做过注册
        int result = isRegister(zjlxDm, zjHm, sjHm, conn);
        if (result == 0) {
            resEvent.setRepCode("ZB0035");
            resEvent.setReponseMesg(ZBCoreHelper.getJyztMc(conn, "ZB0035"));
            return resEvent;
        }

        String guid = ZBCoreHelper.getGUID(conn);
        // 3.银联实名认证
        JsonResData jsonResData = SmrzTools.rzByYl(xm, sjHm, zjHm, ylKh, guid,
                conn);
        if ("0".equals(jsonResData.getCode())) {
            PreparedStatement pstmt = conn
                    .prepareStatement("INSERT INTO DB_ZSBS.T_ZB_SMRZ (UUID, RZFS_DM, XY_BJ, LR_SJ, XG_SJ) VALUES (?, ?, ?, SYSTIMESTAMP, NULL)");
            pstmt.setString(1, guid);
            pstmt.setString(2, rzfsDm);
            pstmt.setString(3, "1");
            pstmt.execute();
            pstmt.close();
        } else {
            resEvent.setRepCode(jsonResData.getCode());
            resEvent.setReponseMesg(jsonResData.getMsg());
            return resEvent;
        }

        // 4.根据证件类型、证件号码和及手机号删除注册信息
        PreparedStatement pstmt = conn
                .prepareStatement("DELETE T_ZB_LOGIN A WHERE A.SFZMLX_DM=? AND A.SFZHM=? AND SJHM=? AND XY_BJ='1'");
        pstmt.setString(1, zjlxDm);
        pstmt.setString(2, zjHm);
        pstmt.setString(3, sjHm);
        pstmt.execute();

        // 5.保存注册信息
        String sql = "INSERT INTO T_ZB_LOGIN (SJHM, LOGPASS_MD5, LOGPASS, LRRY_DM, LR_SJ, XG_SJ, SFZMLX_DM, SFZHM, UUID, XM, XY_BJ)"
                + "VALUES (?, ?, ?, 'ZSBS', SYSTIMESTAMP, NULL, ?, ?, ?, ?, '1')";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, sjHm);
        pstmt.setString(2, MD5Helper.MD5(passWord));
        pstmt.setString(3, passWord);
        pstmt.setString(4, zjlxDm);
        pstmt.setString(5, zjHm);
        pstmt.setString(6, guid);
        pstmt.setString(7, xm);
        pstmt.execute();
        pstmt.close();
        resEvent.setReponseMesg("密码更改成功");
        return resEvent;
    }

    private ResponseEvent getYzm(RequestEvent reqEvent, Connection conn)
            throws SQLException {
        ResponseEvent resEvent = new ResponseEvent();
        String zjlxDm = (String) reqEvent.reqMapParam.get("zjlxDm");
        String zjHm = (String) reqEvent.reqMapParam.get("zjHm");
        String sjHm = (String) reqEvent.reqMapParam.get("sjHm");
        String sjYzm = RandomStringUtils.randomNumeric(4);

        // 1.校验是否做过注册
        int result = isRegister(zjlxDm, zjHm, sjHm, conn);
        if (result == 0) {
            resEvent.setRepCode("ZB0035");
            resEvent.setReponseMesg(ZBCoreHelper.getJyztMc(conn, "ZB0035"));
            return resEvent;
        }

        // 2.发送验证码
        JsonResData jsonResData = DxTools.sendYzm(conn, sjHm, sjYzm,
                DxTools.getYzmMb("进行自然人实名办税平台密码变更操作", sjYzm, "10"));

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

    /**
     * 校验是否做过注册
     *
     * @param zjlxDm
     * @param zjHm
     * @param sjHm
     * @param conn
     * @return 1已注册，0未注册
     * @throws SQLException
     */
    private int isRegister(String zjlxDm, String zjHm, String sjHm,
                           Connection conn) throws SQLException {
        ArrayList<String> sqlParams = new ArrayList<String>();
        sqlParams.add(zjlxDm);
        sqlParams.add(zjHm);
        sqlParams.add(sjHm);
        CachedRowSet rs = QueryBPO
                .findAll(
                        conn,
                        "SELECT UUID FROM T_ZB_LOGIN A WHERE A.SFZMLX_DM=? AND A.SFZHM=? AND SJHM=? AND XY_BJ='1'",
                        sqlParams);
        if (rs.size() > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    protected ResponseEvent validateData(RequestEvent arg0, Connection arg1)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
