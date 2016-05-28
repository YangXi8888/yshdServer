package gov.jslt.taxevent.comm;

import gov.jslt.taxevent.comm.DateUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.bpo.QueryCssBPO;

public class WBfwHelper {
	public static String getGUID(Connection con) throws SQLException {
		String guid = "";
		String getGuidSql = "select  F_XT_GET_GUID() as uuid from dual";
		CachedRowSet rs = QueryCssBPO.findAll(con, getGuidSql, new ArrayList());
		if (rs.next()) {
			guid = rs.getString("uuid");
		}
		return guid;
	}

	public static String getLsh(String ydjtype) {
		return ydjtype + DateUtil.getNowString("yyyyMMddHHmmssSSSS");

	}

	public static String getContext(String filetype) {
		String contentType = "";
		if (filetype == null || filetype.equals("")) {
			contentType = "application/octet-stream;charset=UTF-8";
		} else if (filetype.equalsIgnoreCase(".chm")) {
			contentType = "application/msword;charset=UTF-8";
		} else if (filetype.equalsIgnoreCase(".doc")) {
			contentType = "application/msword;charset=GBK";
		} else if (filetype.equalsIgnoreCase(".xls")) {
			contentType = "application/vnd.ms-excel;charset=UTF-8";
		} else if (filetype.equalsIgnoreCase(".ppt")) {
			contentType = "application/vnd.ms-powerpoint;charset=UTF-8";
		} else if (filetype.equalsIgnoreCase(".txt")) {
			contentType = "text/plain;charset=UTF-8";
		} else if (filetype.equalsIgnoreCase(".pdf")) {
			contentType = "application/pdf;charset=UTF-8";
		}
		return contentType;
	}

	public static void main(String args[]) {
		System.out.println(WBfwHelper.getLsh("004"));
	}
}
