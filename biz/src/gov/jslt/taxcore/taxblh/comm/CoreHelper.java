package gov.jslt.taxcore.taxblh.comm;

import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.nsrd.nsrd001.NsrCwbbVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrJbxxVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrSbfVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrSfVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrXzcfVO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.bpo.CLOBObject;
import com.ctp.core.bpo.QueryCssBPO;
import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.core.log.LogWritter;
import com.jxcell.View;

public class CoreHelper {

	public static String getGUID(Connection con) throws SQLException {
		String guid = "";
		String getGuidSql = "select  F_XT_GET_GUID() as uuid from dual";
		CachedRowSet rs = QueryCssBPO.findAll(con, getGuidSql, null);
		rs.next();
		guid = rs.getString("uuid");

		return guid;
	}

	public static Map<String, String> getFtp(String gljgdm, Connection conn) throws SQLException {
		Map<String, String> map = new HashMap<String, String>();
		String sql = "SELECT S.CSZ,TO_CHAR(SYSDATE,'YYYYMMDD') AS RQ FROM T_XT_XTCS S WHERE S.CSBM ='ZSBSFTP' ";
		CachedRowSet rs = QueryCssBPO.findAll(conn, sql, null, null);
		if (rs.next()) {
			map.put("ml", gljgdm.substring(0, 5) + "/" + rs.getString("RQ"));
			String[] temp = rs.getString("CSZ").split(":");
			map.put("ftpIp", temp[0]);
			map.put("ftpUser", temp[1]);
			map.put("ftpPwd", temp[2]);
		}
		return map;
	}

	public static String getDateTime(Connection con, String format) throws SQLException {
		String dateTime = "";
		if (format == null) {
			format = "YYYY-MM-DD HH24:MI:SS";
		}
		String getGuidSql = "SELECT  TO_CHAR(SYSTIMESTAMP,'" + format + "') AS  DATETIME FROM DUAL";
		CachedRowSet rs = QueryCssBPO.findAll(con, getGuidSql, null);
		rs.next();
		dateTime = rs.getString("DATETIME");
		return dateTime;
	}

	public static String getJyztMc(Connection con, String jyztDm) throws SQLException {
		if (null == jyztDm || "".equals(jyztDm)) {
			return GeneralCons.SUCCESS_MSG_ZB7777;
		} else {
			ArrayList<String> sqlParams = new ArrayList<String>();
			sqlParams.add(jyztDm);
			CachedRowSet rs = QueryCssBPO.findAll(con, "SELECT JYZT_MC FROM T_DM_YS_JYZT D WHERE JYZT_DM=?", sqlParams);
			if (rs.next()) {
				return rs.getString("JYZT_MC");
			} else {
				return GeneralCons.SUCCESS_MSG_ZB7777;
			}
		}
	}

	public static String empty(Object str) {
		if (null == str || "null".equals(str)) {
			return "";
		}
		return str.toString();
	}

	public static List<Map<String, String>> queryJsgs(String pzzlDm, String xtLx, Connection conn) throws SQLException {
		ArrayList<String> sqlParams = new ArrayList<String>();
		sqlParams.add(pzzlDm);
		sqlParams.add(xtLx);
		String sql = "select T.GS,T.PXZD,T.GSLX,T.TYPE from T_CS_WB_YMJSGS T WHERE PZZL_DM=? AND XY_BJ='1' AND XTLX=?  ORDER BY T.PXZD";
		CachedRowSet rs = QueryCssBPO.findAll(conn, sql, sqlParams);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("GS", rs.getString("GS"));
			map.put("PXZD", rs.getString("PXZD"));
			map.put("GSLX", rs.getString("GSLX"));
			map.put("TYPE", rs.getString("TYPE"));
			list.add(map);
		}
		return list;
	}

	public static String clobToString(Clob clob) throws SQLException {
		if (null == clob) {
			return "";
		}
		Reader is = clob.getCharacterStream();
		BufferedReader br = new BufferedReader(is);
		StringBuffer strBuf = new StringBuffer();
		try {
			String s = br.readLine();
			while (s != null) {
				strBuf.append(s);
				s = br.readLine();
			}
		} catch (IOException e) {
			LogWritter.sysError(e.getMessage());
		}
		return strBuf.toString();
	}

	public static void buildExcelStream(Map<String, List<Object>> dataMap, String tempFileName, String wjMm) {
		View m_view = new View();
		File file = new File(tempFileName);
		try {
			FileOutputStream outputStream = new FileOutputStream(file);
			m_view.getLock();
			m_view.setNumSheets(5);
			m_view.setSheetName(0, "基本信息");
			m_view.setSheetName(1, "税费信息");
			m_view.setSheetName(2, "社保信息");
			m_view.setSheetName(3, "财务报表信息");
			m_view.setSheetName(4, "处罚信息");

			// 纳税人基本信息
			m_view.setSheet(0);
			m_view.setTextAsValue(0, 0, "社会统一信用代码"); // (行，列，值)
			m_view.setTextAsValue(0, 1, "纳税人名称");
			m_view.setTextAsValue(0, 2, "登记注册类型");
			m_view.setTextAsValue(0, 3, "国标行业");
			m_view.setTextAsValue(0, 4, "注册地址");
			m_view.setTextAsValue(0, 5, "信用等级");
			m_view.setTextAsValue(0, 6, "征收方式");
			m_view.setColWidth(0, 20 * 256);
			m_view.setColWidth(1, 40 * 256);
			m_view.setColWidth(2, 20 * 256);
			m_view.setColWidth(3, 20 * 256);
			m_view.setColWidth(4, 50 * 256);
			m_view.setColWidth(5, 20 * 256);
			m_view.setColWidth(6, 20 * 256);
			List<Object> jbxxList = dataMap.get("jbxxList");
			for (int i = 0; i < jbxxList.size(); i++) {
				NsrJbxxVO jbxxVO = (NsrJbxxVO) jbxxList.get(i);
				m_view.setTextAsValue(i + 1, 0, jbxxVO.getNsrsbm());
				m_view.setTextAsValue(i + 1, 1, jbxxVO.getNsrmc());
				m_view.setTextAsValue(i + 1, 2, jbxxVO.getDjzclxmc());
				m_view.setTextAsValue(i + 1, 3, jbxxVO.getGbhymc());
				m_view.setTextAsValue(i + 1, 4, jbxxVO.getZcdz());
				m_view.setTextAsValue(i + 1, 5, jbxxVO.getXydj());
				m_view.setTextAsValue(i + 1, 6, jbxxVO.getZsfsmc());
			}

			// 税费信息
			m_view.setSheet(1);
			m_view.setTextAsValue(0, 0, "社会统一信用代码");
			m_view.setTextAsValue(0, 1, "纳税人名称");
			m_view.setTextAsValue(0, 2, "所属年度");
			m_view.setTextAsValue(0, 3, "税种");
			m_view.setTextAsValue(0, 4, "入库税额");
			m_view.setColWidth(0, 30 * 256);
			m_view.setColWidth(1, 40 * 256);
			m_view.setColWidth(2, 30 * 256);
			m_view.setColWidth(3, 30 * 256);
			m_view.setColWidth(4, 30 * 256);
			List<Object> sfList = dataMap.get("sfList");
			for (int i = 0; i < sfList.size(); i++) {
				NsrSfVO sfVO = (NsrSfVO) sfList.get(i);
				m_view.setTextAsValue(i + 1, 0, sfVO.getNsrsbm());
				m_view.setTextAsValue(i + 1, 1, sfVO.getNsrmc());
				m_view.setTextAsValue(i + 1, 2, sfVO.getSsnd());
				m_view.setTextAsValue(i + 1, 3, sfVO.getSz());
				m_view.setTextAsValue(i + 1, 4, sfVO.getRkse());
			}

			// 社保费信息
			m_view.setSheet(2);
			m_view.setTextAsValue(0, 0, "社会统一信用代码");
			m_view.setTextAsValue(0, 1, "纳税人名称");
			m_view.setTextAsValue(0, 2, "所属年度");
			m_view.setTextAsValue(0, 3, "险种");
			m_view.setTextAsValue(0, 4, "实缴金额");
			m_view.setColWidth(0, 30 * 256);
			m_view.setColWidth(1, 40 * 256);
			m_view.setColWidth(2, 30 * 256);
			m_view.setColWidth(3, 30 * 256);
			m_view.setColWidth(4, 30 * 256);
			List<Object> sbfList = dataMap.get("sbfList");
			for (int i = 0; i < sbfList.size(); i++) {
				NsrSbfVO sbfVO = (NsrSbfVO) sbfList.get(i);
				m_view.setTextAsValue(i + 1, 0, sbfVO.getNsrsbm());
				m_view.setTextAsValue(i + 1, 1, sbfVO.getNsrmc());
				m_view.setTextAsValue(i + 1, 2, sbfVO.getSsnd());
				m_view.setTextAsValue(i + 1, 3, sbfVO.getXz());
				m_view.setTextAsValue(i + 1, 4, sbfVO.getSjje());
			}

			// 财务报表内容
			m_view.setSheet(3);
			m_view.setTextAsValue(0, 0, "社会统一信用代码");
			m_view.setTextAsValue(0, 1, "纳税人名称");
			m_view.setTextAsValue(0, 2, "所属年度");
			m_view.setTextAsValue(0, 3, "资产总额");
			m_view.setTextAsValue(0, 4, "负债总额");
			m_view.setTextAsValue(0, 5, "实收资本");
			m_view.setTextAsValue(0, 6, "资本公积");
			m_view.setTextAsValue(0, 7, "主营业务收入");
			m_view.setTextAsValue(0, 8, "主营业务成本");
			m_view.setTextAsValue(0, 9, "会计利润");
			m_view.setColWidth(0, 20 * 256);
			m_view.setColWidth(1, 40 * 256);
			m_view.setColWidth(2, 20 * 256);
			m_view.setColWidth(3, 20 * 256);
			m_view.setColWidth(4, 20 * 256);
			m_view.setColWidth(5, 20 * 256);
			m_view.setColWidth(6, 20 * 256);
			m_view.setColWidth(7, 20 * 256);
			m_view.setColWidth(8, 20 * 256);
			m_view.setColWidth(9, 20 * 256);
			List<Object> cwbbList = dataMap.get("cwbblist");
			for (int i = 0; i < cwbbList.size(); i++) {
				NsrCwbbVO cwbbVO = (NsrCwbbVO) cwbbList.get(i);
				m_view.setTextAsValue(i + 1, 0, cwbbVO.getNsrsbm());
				m_view.setTextAsValue(i + 1, 1, cwbbVO.getNsrmc());
				m_view.setTextAsValue(i + 1, 2, cwbbVO.getSsnd());
				m_view.setTextAsValue(i + 1, 3, cwbbVO.getZcze());
				m_view.setTextAsValue(i + 1, 4, cwbbVO.getFzze());
				m_view.setTextAsValue(i + 1, 5, cwbbVO.getSszb());
				m_view.setTextAsValue(i + 1, 6, cwbbVO.getZbgj());
				m_view.setTextAsValue(i + 1, 7, cwbbVO.getZyywsr());
				m_view.setTextAsValue(i + 1, 8, cwbbVO.getZyywcb());
				m_view.setTextAsValue(i + 1, 9, cwbbVO.getKjlr());
			}

			// 处罚信息
			m_view.setSheet(4);
			m_view.setTextAsValue(0, 0, "社会统一信用代码");
			m_view.setTextAsValue(0, 1, "纳税人名称");
			m_view.setTextAsValue(0, 2, "处罚决定文号");
			m_view.setTextAsValue(0, 3, "处罚事由");
			m_view.setTextAsValue(0, 4, "处罚日期");
			m_view.setTextAsValue(0, 5, "处罚金额");
			m_view.setColWidth(0, 30 * 256);
			m_view.setColWidth(1, 40 * 256);
			m_view.setColWidth(2, 30 * 256);
			m_view.setColWidth(3, 40 * 256);
			m_view.setColWidth(4, 30 * 256);
			m_view.setColWidth(5, 30 * 256);
			List<Object> xzcfList = dataMap.get("xzcfList");
			for (int i = 0; i < xzcfList.size(); i++) {
				NsrXzcfVO xzcfVO = (NsrXzcfVO) xzcfList.get(i);
				m_view.setTextAsValue(i + 1, 0, xzcfVO.getNsrsbm());
				m_view.setTextAsValue(i + 1, 1, xzcfVO.getNsrmc());
				m_view.setTextAsValue(i + 1, 2, xzcfVO.getCfjdwh());
				m_view.setTextAsValue(i + 1, 3, xzcfVO.getCfsyStr());
				m_view.setTextAsValue(i + 1, 4, xzcfVO.getCfrq());
				m_view.setTextAsValue(i + 1, 5, xzcfVO.getCfje());
			}
			m_view.releaseLock();
			m_view.write(outputStream, wjMm);
		} catch (Exception e) {
			LogWritter.sysError("生成Excel异常：" + e.getMessage());
		}

	}

	public static CLOBObject readCLOB(ResultSet rs, String col) throws TaxBaseBizException {
		CLOBObject obj = null;
		BufferedWriter out = null;
		BufferedReader in = null;
		CharArrayWriter writer = null;
		try {
			if (rs.getClob(col) == null) {
				return null;
			}

			obj = new CLOBObject();

			writer = new CharArrayWriter();
			out = new BufferedWriter(writer);

			Clob clob = rs.getClob(col);
			in = new BufferedReader(clob.getCharacterStream());
			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
			}

			out.flush();

			obj.setContent(writer.toCharArray());
		} catch (Exception e) {
			LogWritter.sysError(e.getMessage());
			throw new TaxBaseBizException();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
				if (writer != null)
					writer.close();
			} catch (Exception e) {
				LogWritter.sysError(e.getMessage());
				throw new TaxBaseBizException();
			}
		}
		return obj;
	}

	public static void createExcelByMM(ByteArrayOutputStream byteArrayOutputStream, String wjMm, String tempFileName) throws TaxBaseBizException {
		try {

			File file = new File(tempFileName);

			file.createNewFile();

			DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
			byteArrayOutputStream.writeTo(dataOutputStream);
			byteArrayOutputStream.flush();
			byteArrayOutputStream.close();
			dataOutputStream.flush();
			dataOutputStream.close();

			EncryptDecryptUtil.encrypt(file, wjMm);

		} catch (IOException e) {
			LogWritter.sysError(e.getMessage());
			throw new TaxBaseBizException(e.getMessage());
		}
	}

}
