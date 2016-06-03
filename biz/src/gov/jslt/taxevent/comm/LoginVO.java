package gov.jslt.taxevent.comm;

import java.io.Serializable;

public class LoginVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 手机号码
	 */
	private String sjHm = "";

	/**
	 * 姓名
	 */
	private String xm = "";

	/**
	 * 用户唯一标识
	 */
	private String yhwybz = "";

	/**
	 * 证件类型
	 */
	private String zjLx = "";

	/**
	 * 证件号码
	 */
	private String zjHm = "";

	/**
	 * 用户类型代码
	 */
	private String yhLxDm = "";
	
	
	/**
	 * 签约银行代码
	 */
	private String qyYhDm = "";
	
	


	public String getSjHm() {
		return sjHm;
	}

	public String getQyYhDm() {
		return qyYhDm;
	}

	public void setQyYhDm(String qyYhDm) {
		this.qyYhDm = qyYhDm;
	}

	public void setSjHm(String sjHm) {
		this.sjHm = sjHm;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getYhwybz() {
		return yhwybz;
	}

	public void setYhwybz(String yhwybz) {
		this.yhwybz = yhwybz;
	}

	public String getZjLx() {
		return zjLx;
	}

	public void setZjLx(String zjLx) {
		this.zjLx = zjLx;
	}

	public String getZjHm() {
		return zjHm;
	}

	public void setZjHm(String zjHm) {
		this.zjHm = zjHm;
	}

	public String getYhLxDm() {
		return yhLxDm;
	}

	public void setYhLxDm(String yhLxDm) {
		this.yhLxDm = yhLxDm;
	}

}
