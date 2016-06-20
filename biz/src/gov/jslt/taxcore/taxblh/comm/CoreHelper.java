package gov.jslt.taxcore.taxblh.comm;

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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ctp.core.bpo.CLOBObject;
import com.ctp.core.bpo.QueryCssBPO;
import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.core.log.LogWritter;

import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.nsrd.nsrd001.NsrCwbbVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrJbxxVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrSbfVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrSfVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrXzcfVO;
import sun.jdbc.rowset.CachedRowSet;

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
			ArrayList sqlParams = new ArrayList();
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

	public static ByteArrayOutputStream buildExcelStream(Map<String, List<Object>> dataMap)
			throws SQLException, TaxBaseBizException {
		// 创建excel
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = null;
		HSSFRow row = null;
		HSSFCell cell = null;

		// 头部样式
		HSSFFont headFont = wb.createFont();
		headFont.setFontName("黑体");
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		HSSFCellStyle headStyle = wb.createCellStyle();
		headStyle.setFont(headFont);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中

		// 纳税人基本信息
		List<Object> jbxxList = dataMap.get("jbxxList");
		// 创建列样式
		sheet = wb.createSheet();
		wb.setSheetName(0, "基本信息", (short) 1);// 设置seet标题，第3个参数为避免中文乱码
		sheet.createFreezePane(0, 1);// 冻结
		sheet.setColumnWidth((short) 0, (short) (20 * 256));
		sheet.setColumnWidth((short) 1, (short) (40 * 256));
		sheet.setColumnWidth((short) 2, (short) (20 * 256));
		sheet.setColumnWidth((short) 3, (short) (20 * 256));
		sheet.setColumnWidth((short) 4, (short) (50 * 256));
		sheet.setColumnWidth((short) 5, (short) (20 * 256));
		sheet.setColumnWidth((short) 6, (short) (20 * 256));
		// 纳税人基本信息头部
		row = sheet.createRow(0);
		cell = row.createCell((short) 0);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("社会统一信用代码");
		
		row = sheet.createRow(0);
		cell = row.createCell((short) 1);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("纳税人名称");

		cell = row.createCell((short) 2);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("登记注册类型");

		cell = row.createCell((short) 3);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("国标行业");

		cell = row.createCell((short) 4);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("注册地址");

		cell = row.createCell((short) 5);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("信用等级");

		cell = row.createCell((short) 6);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("征收方式");

		NsrJbxxVO jbxxVO;
		for (int i = 0; i < jbxxList.size(); i++) {
			jbxxVO = (NsrJbxxVO) jbxxList.get(i);
			// 纳税人基本信息内容
			row = sheet.createRow(i + 1);
			
			
			cell = row.createCell((short) 0);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(jbxxVO.getNsrsbm());
			
			
			
			
			

			cell = row.createCell((short) 1);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(jbxxVO.getNsrmc());

			cell = row.createCell((short) 2);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(jbxxVO.getDjzclxmc());

			cell = row.createCell((short) 3);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(jbxxVO.getGbhymc());

			cell = row.createCell((short) 4);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(jbxxVO.getZcdz());

			cell = row.createCell((short) 5);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(jbxxVO.getXydj());

			cell = row.createCell((short) 6);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(jbxxVO.getZsfsmc());
		}

		// 税费信息
		List<Object> sfList = dataMap.get("sfList");

		// 创建sheet
		sheet = wb.createSheet();
		wb.setSheetName(1, "税费信息", (short) 1);
		sheet.createFreezePane(0, 1);// 冻结
		sheet.setColumnWidth((short) 0, (short) (30 * 256));
		sheet.setColumnWidth((short) 1, (short) (40 * 256));
		sheet.setColumnWidth((short) 2, (short) (30 * 256));
		sheet.setColumnWidth((short) 3, (short) (30 * 256));
		sheet.setColumnWidth((short) 4, (short) (30 * 256));
		// 税费信息头部
		row = sheet.createRow(0);
		
		cell = row.createCell((short) 0);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("社会统一信用代码");
		
		cell = row.createCell((short) 1);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("纳税人名称");
		
		cell = row.createCell((short) 2);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("所属年度");
		
		cell = row.createCell((short) 3);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("税种");
		
		cell = row.createCell((short) 4);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("入库税额");
		// 税费信息内容
		for (int i = 0; i < sfList.size(); i++) {
			NsrSfVO sfVO = (NsrSfVO) sfList.get(i);
			row = sheet.createRow(i + 1);
			
			cell = row.createCell((short) 0);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(sfVO.getNsrsbm());
			
			cell = row.createCell((short) 1);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(sfVO.getNsrmc());
			
			cell = row.createCell((short) 2);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(sfVO.getSsnd());
			
			cell = row.createCell((short) 3);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(sfVO.getSz());
			
			cell = row.createCell((short) 4);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(sfVO.getRkse());
		}

		// 社保费信息
		List<Object> sbfList = dataMap.get("sbfList");
		// 创建sheet
		sheet = wb.createSheet();
		wb.setSheetName(2, "社保信息", (short) 1);
		sheet.createFreezePane(0, 1);// 冻结
		sheet.setColumnWidth((short) 0, (short) (30 * 256));
		sheet.setColumnWidth((short) 1, (short) (40 * 256));
		sheet.setColumnWidth((short) 2, (short) (30 * 256));
		sheet.setColumnWidth((short) 3, (short) (30 * 256));
		sheet.setColumnWidth((short) 4, (short) (30 * 256));
		// 社保信息头部
		row = sheet.createRow(0);
		cell = row.createCell((short) 0);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("社会统一信用代码");
		
		cell = row.createCell((short) 1);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("纳税人名称");
		
		
		cell = row.createCell((short) 2);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("所属年度");
		
		cell = row.createCell((short) 3);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("险种");
		
		cell = row.createCell((short) 4);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("实缴金额");
		// 社保信息内容
		for (int i = 0; i < sbfList.size(); i++) {
			NsrSbfVO sbfVO = (NsrSbfVO) sbfList.get(i);
			row = sheet.createRow(i + 1);
			
			cell = row.createCell((short) 0);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(sbfVO.getNsrsbm());
			
			cell = row.createCell((short) 1);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(sbfVO.getNsrmc());
			
			cell = row.createCell((short) 2);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(sbfVO.getSsnd());
			
			cell = row.createCell((short) 3);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(sbfVO.getXz());
			
			cell = row.createCell((short) 4);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(sbfVO.getSjje());
		}

		// 财务报表内容
		List<Object> cwbbList = dataMap.get("cwbblist");
		// 创建sheet
		sheet = wb.createSheet();
		wb.setSheetName(3, "财务报表信息", (short) 1);
		sheet.createFreezePane(0, 1);// 冻结
		sheet.setColumnWidth((short) 0, (short) (20 * 256));
		sheet.setColumnWidth((short) 1, (short) (40 * 256));
		sheet.setColumnWidth((short) 2, (short) (20 * 256));
		sheet.setColumnWidth((short) 3, (short) (20 * 256));
		sheet.setColumnWidth((short) 4, (short) (20 * 256));
		sheet.setColumnWidth((short) 5, (short) (20 * 256));
		sheet.setColumnWidth((short) 6, (short) (20 * 256));
		sheet.setColumnWidth((short) 7, (short) (20 * 256));
		sheet.setColumnWidth((short) 8, (short) (20 * 256));
		sheet.setColumnWidth((short) 9, (short) (20 * 256));
		// 财务报表头部
		row = sheet.createRow(0);
		

		cell = row.createCell((short) 0);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("社会统一信用代码");
		
		

		cell = row.createCell((short) 1);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("纳税人名称");
		
		
		cell = row.createCell((short) 2);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("所属年度");
		
		cell = row.createCell((short) 3);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("资产总额");
		
		cell = row.createCell((short) 4);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("负债总额");
		
		cell = row.createCell((short) 5);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("实收资本");
		
		cell = row.createCell((short) 6);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("资本公积");
		
		cell = row.createCell((short) 7);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("主营业务收入");
		
		cell = row.createCell((short) 8);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("主营业务成本");
		
		cell = row.createCell((short) 9);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("会计利润");
		// 财务报表内容
		for (int i = 0; i < cwbbList.size(); i++) {
			NsrCwbbVO cwbbVO = (NsrCwbbVO) cwbbList.get(i);
			row = sheet.createRow(i + 1);
			
			cell = row.createCell((short) 0);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(cwbbVO.getNsrsbm());
			
			cell = row.createCell((short) 1);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(cwbbVO.getNsrmc());
			
			cell = row.createCell((short) 2);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(cwbbVO.getSsnd());
			
			cell = row.createCell((short) 3);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(cwbbVO.getZcze());
			
			cell = row.createCell((short) 4);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(cwbbVO.getFzze());
			
			cell = row.createCell((short) 5);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(cwbbVO.getSszb());
			
			cell = row.createCell((short) 6);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(cwbbVO.getZbgj());
			
			cell = row.createCell((short) 7);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(cwbbVO.getZyywsr());
			
			cell = row.createCell((short) 8);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(cwbbVO.getZyywcb());
			
			cell = row.createCell((short) 9);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(cwbbVO.getKjlr());
		}

		// 处罚信息
		List<Object> xzcfList = dataMap.get("xzcfList");
		// 创建sheet
		sheet = wb.createSheet();
		wb.setSheetName(4, "处罚信息", (short) 1);
		sheet.createFreezePane(0, 1);// 冻结
		sheet.setColumnWidth((short) 0, (short) (30 * 256));
		sheet.setColumnWidth((short) 1, (short) (40 * 256));
		sheet.setColumnWidth((short) 2, (short) (30 * 256));
		sheet.setColumnWidth((short) 3, (short) (40 * 256));
		sheet.setColumnWidth((short) 4, (short) (30 * 256));
		sheet.setColumnWidth((short) 5, (short) (30 * 256));
		// 处罚信息头部
		row = sheet.createRow(0);
		
		cell = row.createCell((short) 0);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("社会统一信用代码");
		
		cell = row.createCell((short) 1);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("纳税人名称");
		
		cell = row.createCell((short) 2);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("处罚决定文号");
		
		cell = row.createCell((short) 3);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("处罚事由");
		
		cell = row.createCell((short) 4);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("处罚日期");
		
		cell = row.createCell((short) 5);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("处罚金额");
		// 处罚信息内容
		for (int i = 0; i < xzcfList.size(); i++) {
			NsrXzcfVO xzcfVO = (NsrXzcfVO) xzcfList.get(i);
			row = sheet.createRow(i + 1);
			
			cell = row.createCell((short) 0);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(xzcfVO.getNsrsbm());
			
			cell = row.createCell((short) 1);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(xzcfVO.getNsrmc());
			
			cell = row.createCell((short) 2);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(xzcfVO.getCfjdwh());
			
			cell = row.createCell((short) 3);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(xzcfVO.getCfsyStr());
			
			cell = row.createCell((short) 4);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(xzcfVO.getCfrq());
			
			cell = row.createCell((short) 5);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(xzcfVO.getCfje());
		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			wb.write(os);
		} catch (IOException e) {
			LogWritter.sysError(e.getMessage());
			throw new TaxBaseBizException(e.getMessage());
		}
		return os;
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

	public static void createExcelByMM(ByteArrayOutputStream byteArrayOutputStream, String wjMm, String tempFileName)
			throws TaxBaseBizException {
		try {

			File file = new File(tempFileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
			byteArrayOutputStream.writeTo(dataOutputStream);
			EncryptDecryptUtil.encrypt(tempFileName, wjMm);
		} catch (IOException e) {
			LogWritter.sysError(e.getMessage());
			throw new TaxBaseBizException(e.getMessage());
		}
	}

}
