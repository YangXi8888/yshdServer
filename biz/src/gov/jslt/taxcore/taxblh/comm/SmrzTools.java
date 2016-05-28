package gov.jslt.taxcore.taxblh.comm;

import gov.jslt.taxcore.taxbpo.yhgl.SmrzLogBPO;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonResData;
import gov.jslt.taxevent.comm.MD5Helper;
import gov.jslt.taxevent.yhgl.SmrzLogVO;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;

import com.ctp.core.log.LogWritter;
import com.ctp.core.utility.dbtime.DBTimeServer;

/**
 * 实名认证
 * 
 * 
 */
public class SmrzTools {
	public static String SERVER_IP_YL = "180.166.112.24";
	public static int SERVER_PORT_YL = 11003;

	/**
	 * 中国银联实名认证
	 * 
	 * @param xm
	 *            姓名
	 * @param sjHm
	 *            手机号码
	 * @param zjlxDm
	 *            证件类型代码
	 * @param zjHm
	 *            证件号码
	 * @param ylKh
	 *            银联卡号
	 * @param conn
	 *            SQL Connection
	 * @return
	 * @throws SQLException
	 */
	public static JsonResData rzByYl(String xm, String sjHm, String zjHm,
			String ylKh, String userId, Connection conn) {
		JsonResData jsonResData = new JsonResData();
		LogWritter.sysError("开始拼装银联实名认证请求报文:" + "xm:" + xm + ";sjHm:" + sjHm
				+ ";zjHm:" + zjHm + ";ylKh:" + ylKh + ";userId:" + userId);
		Socket socket = null;
		try {
			String bw_jym = "1100";// 交易码
			String bw_wdh = addKgForLeft("F00R", 16);// 网点号,16位左补空格
			String bw_czyh = addKgForLeft("", 16);// 操作员号,16位，左补空格
			String bw_lsh = addKgForLeft("", 10);// 流水号，10位，左补空格，如果没有就填10个空格
			String bw_sj = DBTimeServer.getDBTimesStr(conn, 9);// 时间，14位，YYYYMMDDHHMMSS
			String bw_sjh = addKgForLeft(sjHm, 11);// 手机号,11位，左补空格
			String bw_zjh = addKgForLeft(zjHm, 32);// 证件号32位，左补空格
			String bw_ylKh = addKgForLeft(ylKh, 32);// 卡号，32位，左补空格
			String bw_xm = addKgForRight(xm, 32);// 姓名,32位，右补空格
			String bw_bz = addKgForRight("", 50);
			String desData = bw_jym + bw_wdh + bw_czyh + bw_lsh + bw_sj
					+ bw_sjh + bw_zjh + bw_ylKh + bw_xm + bw_bz;
			LogWritter.sysError("加密前报文：" + desData.getBytes("GBK").length);
			LogWritter.sysError("加密前报文：" + desData);
			String Md5Data = MD5Helper.MD5(desData + "UmsTZDS001");
			String reqData = String.format("%04d%s%s", 249, desData, Md5Data);
			LogWritter.sysError("加密后报文：" + reqData);
			LogWritter.sysError("加密后报文：" + reqData.getBytes("GBK").length);
			String resData = null;
			socket = new Socket(SERVER_IP_YL, SERVER_PORT_YL);
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			byte[] sendByte = reqData.getBytes("GBK");
			out.write(sendByte);
			out.flush();
			InputStreamReader reader = new InputStreamReader(in, "GBK");// 接收字符编码
			StringBuffer sb = new StringBuffer(1024);
			char[] buff = new char[1024];
			int length = 0;
			while ((length = reader.read(buff)) != -1) {
				sb.append(buff, 0, length);
			}
			out.close();
			in.close();
			resData = sb.toString();
			// 接收到银联返回结果
			System.out.println("银联实名认证返回结果：" + resData);
			LogWritter.sysError("银联实名认证返回结果：" + resData);
			String code = "00".equals(resData.substring(8, 10)) ? "0" : "1";// 响应码
			String msg = resData.substring(10, 56).trim();// 错误说明
			System.out.println("银联实名认证返回响应码：" + code);
			LogWritter.sysError("银联实名认证返回响应码：" + code);
			jsonResData.setCode(code);
			jsonResData.setMsg(msg);
			// 记录认证日志
			SmrzLogVO smrzLogVO = new SmrzLogVO();
			smrzLogVO.setRzloguuid(ZBCoreHelper.getGUID(conn));
			smrzLogVO.setRzfsdm("01");
			smrzLogVO.setRzbw(desData);
			smrzLogVO.setFhbw(resData);
			smrzLogVO.setJgbz(code);
			smrzLogVO.setUuid(userId);
			SmrzLogBPO.insert(conn, smrzLogVO);
		} catch (UnknownHostException e) {
			jsonResData.setCode(GeneralCons.ERROR_CODE_ZB9999);
			jsonResData.setMsg(e.getMessage());
			LogWritter.sysError("银联实名认证异常：" + e.getMessage());
		} catch (IOException e) {
			jsonResData.setCode(GeneralCons.ERROR_CODE_ZB9999);
			jsonResData.setMsg(e.getMessage());
			LogWritter.sysError("银联实名认证异常：" + e.getMessage());
		} catch (SQLException e) {
			jsonResData.setCode(GeneralCons.ERROR_CODE_ZB9999);
			jsonResData.setMsg(e.getMessage());
			LogWritter.sysError("银联实名认证异常：" + e.getMessage());
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					LogWritter.sysError("银联实名认证异常：" + e.getMessage());
				}
			}
		}
		return jsonResData;
	}

	/**
	 * 指定字符串左边添加空格
	 * 
	 * @param str
	 * @param ws
	 *            指定返回的字符串位数
	 * @param bmGs
	 *            编码格式
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String addKgForLeft(String str, int ws, String bmGs)
			throws UnsupportedEncodingException {
		if (str.getBytes(bmGs).length < ws) {
			int lg = ws - str.getBytes(bmGs).length;
			for (int i = 0; i < lg; i++) {
				str = " " + str;
			}
		}
		return str;
	}

	/**
	 * 指定字符串右边添加空格
	 * 
	 * @param str
	 * @param ws
	 *            指定返回的字符串位数
	 * @param bmGs
	 *            编码格式
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String addKgForRight(String str, int ws, String bmGs)
			throws UnsupportedEncodingException {
		if (str.getBytes(bmGs).length < ws) {
			int lg = ws - str.getBytes(bmGs).length;
			for (int i = 0; i < lg; i++) {
				str = str + " ";
			}
		}
		return str;
	}

	private static String addKgForLeft(String str, int ws)
			throws UnsupportedEncodingException {
		if (str.getBytes("GBK").length < ws) {
			int lg = ws - str.getBytes("GBK").length;
			for (int i = 0; i < lg; i++) {
				str = " " + str;
			}
		}
		return str;
	}

	private static String addKgForRight(String str, int ws)
			throws UnsupportedEncodingException {
		if (str.getBytes("GBK").length < ws) {
			int lg = ws - str.getBytes("GBK").length;
			for (int i = 0; i < lg; i++) {
				str = str + " ";
			}
		}
		return str;
	}
}
