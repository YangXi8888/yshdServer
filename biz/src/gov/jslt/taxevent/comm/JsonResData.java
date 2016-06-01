package gov.jslt.taxevent.comm;

import java.util.HashMap;
import java.util.Map;

public class JsonResData {

	/**
	 * 是否下载文件
	 */

	private String fileDownload = "false";
	private String msg = "";
	private String code = "";
	private Map<String, Object> data = new HashMap<String, Object>();

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

}
