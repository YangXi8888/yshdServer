package gov.jslt.taxcore.taxblh.comm;

import com.ctp.core.bpo.QueryCssBPO;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonResData;
import sun.jdbc.rowset.CachedRowSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.ArrayList;

public class DxTools {

    public static String getYzmMb(String czSm, String yzm, String yxsj) {
        String yzmMb = "【江苏地税】提醒您：您正在" + czSm + "，本次验证码为：" + yzm + "，有效时间"
                + yxsj + "分钟。";
        return yzmMb;
    }

    public static JsonResData sendMsg(String sjHm, String conTent) {
        JsonResData resData = new JsonResData();
        try {
            StringBuffer sendMsg = new StringBuffer(
                    "userId=tzdsj&password=1539EBF31987281E11E1A405B03427EA");
            sendMsg.append("&mobile=" + sjHm);
            sendMsg.append("&content=" + URLEncoder.encode(conTent, "GBK"));
            URL url = new URL(
                    "http://183.211.240.100:8088/TZ-SMS/servlet/WebSend?"
                            + sendMsg.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setReadTimeout(Integer.parseInt("10000"));
            conn.setConnectTimeout(Integer.parseInt("10000"));
            conn.setRequestMethod("GET");
            conn.setRequestProperty("CONTENT-TYPE", "TEXT/HTML; CHARSET=GBK");
            conn.setRequestProperty("ACCEPT", "TEXT/HTML");
            conn.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "GBK"));
            String line;
            StringBuffer str = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                str.append(line);
            }
            // rspCode=0&rspDesc=提交成功&msgId=2810796
            // 0或DELIVRD表示成功，其他失败
            String arr[] = str.toString().split("\\&");
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].indexOf("rspCode") != -1) {
                    if ("DELIVRD".equals(arr[i].split("\\=")[1].toUpperCase())) {
                        resData.setCode("0");
                    } else {
                        resData.setCode(arr[i].split("\\=")[1]);
                    }
                } else if (arr[i].indexOf("rspDesc") != -1) {
                    resData.setMsg(arr[i].split("\\=")[1]);
                }
            }
        } catch (Exception e) {
            resData.setCode(GeneralCons.ERROR_CODE_ZB9999);
            resData.setMsg("短信发送异常:" + e.getMessage());
        }
        return resData;
    }

    public static JsonResData sendYzm(Connection con, String sjHm, String yzm,
                                      String conTent) {
        JsonResData resData = new JsonResData();
        try {
            ArrayList<Object> sqlParams = new ArrayList<Object>();
            sqlParams.add(sjHm);
            sqlParams.add(yzm);
            CachedRowSet rs = QueryCssBPO
                    .findAll(
                            con,
                            " SELECT COUNT(Y.YZM) 	 AS N			FROM T_ZB_YZM Y 		WHERE Y.YZM = ?  AND Y.SJHM = ? 			 AND Y.YXSJZ > SYSDATE",
                            sqlParams);
            rs.next();
            if (rs.getInt("N") > 0) {
                resData.setCode("ZB0037");
                resData.setMsg(ZBCoreHelper.getJyztMc(con, "ZB0037"));
            } else {
                resData = sendMsg(sjHm, conTent);
            }
        } catch (Exception e) {
            resData.setCode(GeneralCons.ERROR_CODE_ZB9999);
            resData.setMsg("短信发送异常:" + e.getMessage());
        }
        return resData;
    }

}
