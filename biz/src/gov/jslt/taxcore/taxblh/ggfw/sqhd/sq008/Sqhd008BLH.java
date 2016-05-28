package gov.jslt.taxcore.taxblh.ggfw.sqhd.sq008;

import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
import gov.jslt.taxcore.taxblh.comm.MyNJTool;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.StringUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.bpo.QueryCssBPO;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.core.log.LogWritter;

public class Sqhd008BLH extends BaseBizLogicHandler {

	@Override
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		String dealMethod = req.getDealMethod();
		if ("checkUser".equals(dealMethod)) {
			return checkUser(req, con);
		}
		return null;
	}

	private ResponseEvent checkUser(RequestEvent req, Connection con)
			throws SQLException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String reqUrl = "http://58.213.141.220:10036/njtax/checkUser";
		String idNumber = StringUtil.empty(dataMap.get("idNumber"));
		String phoneNumber = StringUtil.empty(dataMap.get("phoneNumber"));
		OutputStream out = null;
		// 获取加解密秘钥
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT X.CSZ FROM DB_WSBS.T_XT_XTCS X WHERE X.CSBM = 'ANDROID_SECRET'",
						null);
		if (rs.next()) {
			try {
				String aesKey = rs.getString("CSZ");// 获取加解密秘钥
				// 组装请求参数json
				String json = "{\"idNumber\":\"" + idNumber
						+ "\",\"phoneNumber\":\"" + phoneNumber + "\"}";
				// 请求信息加密
				String aesEncrypt = MyNJTool.aesEncrypt(json, aesKey);
				LogWritter.sysError("加密前的请求信息为：" + json);
				// 发送请求
				URL url = new URL(reqUrl);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setRequestProperty("CONTENT-TYPE",
						"TEXT/HTML; CHARSET=UTF-8");
				conn.setRequestProperty("ACCEPT", "*/*");
				conn.setRequestProperty("CONNECTION", "KEEP-ALIVE");
				conn.setRequestProperty("USER-AGENT",
						"MOZILLA/4.0 (COMPATIBLE; MSIE 6.0; WINDOWS NT 5.1; SV1)");
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				conn.connect();
				out = conn.getOutputStream();
				out.write(aesEncrypt.getBytes("UTF-8"));
				out.flush();
				if (200 != conn.getResponseCode()) {
					LogWritter.sysError("调用我的南京APP接口失败！RespCode："
							+ conn.getResponseCode() + "，RespMess："
							+ conn.getResponseMessage());
					responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
					responseEvent.setReponseMesg("调用我的南京APP接口失败！RespCode："
							+ conn.getResponseCode() + "，RespMess："
							+ conn.getResponseMessage());
					return responseEvent;
				}
				StringBuffer respBuffer = new StringBuffer();
				InputStream in = conn.getInputStream();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in, "UTF-8"));
				String line = null;
				while ((line = reader.readLine()) != null) {
					respBuffer.append(line);
				}
				// 响应信息解密
				String aesDecrypt = MyNJTool.aesDecrypt(respBuffer.toString(),
						aesKey);
				LogWritter.sysError("解密后的响应信息为：" + aesDecrypt);
				JSONObject jsonObject = (JSONObject) JSONSerializer
						.toJSON(aesDecrypt);
				if ("0".equals(jsonObject.getString("retStatus"))) {
					responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
					responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
				} else {
					responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
					responseEvent.setReponseMesg(jsonObject
							.getString("retMessage"));
				}
			} catch (Exception e) {
				responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
				responseEvent.setReponseMesg("调用checkUser接口出现异常："
						+ e.getMessage());
			}
		} else {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					ZBCoreHelper.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
					"获取加解密秘钥异常"));
		}
		return responseEvent;
	}

	@Override
	protected ResponseEvent validateData(RequestEvent req, Connection con)
			throws Exception {
		return null;
	}

	/**
	 * 根据返回码获取返回信息
	 * 
	 * @param code
	 * @return
	 */
	private String getMsg(String code) {
		String msg = null;
		if ("0".equals(code)) {
			msg = "我的南京用户";
		} else if ("1".equals(code)) {
			msg = "不是我的南京用户";
		} else if ("2".equals(code)) {
			msg = "身份证和手机号不匹配";
		} else if ("3".equals(code)) {
			msg = "AES 加密解密错误";
		} else if ("4".equals(code)) {
			msg = "Json 格式错误,无法解析";
		} else if ("5".equals(code)) {
			msg = "请求过程发生异常";
		} else if ("6".equals(code)) {
			msg = "请求参数中有空值";
		}
		return msg;
	}

}
