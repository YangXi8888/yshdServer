package gov.jslt.taxevent.comm;

import java.util.HashMap;
import java.util.Map;

public class JsonReqData {

	/**
	 * BLH名称
	 */
	private String blhName = "";
	/**
	 * BLH执行方法
	 */
	private String handleCode = "";

	/**
	 * 用户唯一标识
	 */
	private String yhwybz = "";
	
	
	/**
	 * 是否下载文件
	 */
	private String downLoadFile = "0";

	/**
	 * 业务数据
	 */
	private Map<String, Object> data = new HashMap<String, Object>();

	public String getBlhName() {
		return blhName;
	}

	public void setBlhName(String blhName) {
		this.blhName = blhName;
	}

	public String getHandleCode() {
		return handleCode;
	}

	public void setHandleCode(String handleCode) {
		this.handleCode = handleCode;
	}

	public String getYhwybz() {
		return yhwybz;
	}

	public void setYhwybz(String yhwybz) {
		this.yhwybz = yhwybz;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public String getDownLoadFile() {
		return downLoadFile;
	}

	public void setDownLoadFile(String downLoadFile) {
		this.downLoadFile = downLoadFile;
	}

}
