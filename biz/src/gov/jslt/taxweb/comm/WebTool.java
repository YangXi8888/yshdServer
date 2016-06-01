package gov.jslt.taxweb.comm;

public class WebTool {
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
}
