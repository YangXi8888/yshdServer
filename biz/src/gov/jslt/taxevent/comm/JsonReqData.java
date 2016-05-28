package gov.jslt.taxevent.comm;

import java.util.HashMap;
import java.util.Map;

public class JsonReqData {
	/**
	 * 手机号码
	 */
	private String sjHm = "";
	/**
	 * 登录密码
	 */
	private String passWord = "";
	/**
	 * BLH名称
	 */
	private String blhName = "";
	/**
	 * BLH执行方法
	 */
	private String handleCode = "";

	/**
	 * 验证码
	 */
	private String sjYzm = "";
	/**
	 * 姓名
	 */
	private String xm = "";
	/**
	 * 操作系统
	 */
	private String platform = "";
	/**
	 * 安卓MD5校验码
	 */
	private String md5 = "";

	/**
	 * 安卓证书
	 */
	private String cert = "";

	/**
	 * 用户唯一标识
	 */
	private String yhwybz = "";

	/**
	 * 业务数据密文
	 */
	private String bizDataMw = "";

	/**
	 * 业务数据
	 */
	private Map<String, Object> data = new HashMap<String, Object>();

	public String getBizDataMw() {
		return bizDataMw;
	}

	public void setBizDataMw(String bizDataMw) {
		this.bizDataMw = bizDataMw;
	}

	public String getYhwybz() {
		return yhwybz;
	}

	public void setYhwybz(String yhwybz) {
		this.yhwybz = yhwybz;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

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

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getSjHm() {
		return sjHm;
	}

	public void setSjHm(String sjHm) {
		this.sjHm = sjHm;
	}

	public String getSjYzm() {
		return sjYzm;
	}

	public void setSjYzm(String sjYzm) {
		this.sjYzm = sjYzm;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getCert() {
		return cert;
	}

	public void setCert(String cert) {
		this.cert = cert;
	}

}
