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
	 * 登录密码
	 */
	private String passWord = "";

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
	 * 认证方式，多个方式以"|"分隔
	 */
	private String rzFs = "";
	
	/**
	 * 税务管理码
	 */
	private String swglm = "";
	
	
	/**
	 * 登记序号
	 */
	private String djxh = "";
	
	
	public String getSjHm() {
		return sjHm;
	}

	public String getSwglm() {
		return swglm;
	}

	public void setSwglm(String swglm) {
		this.swglm = swglm;
	}

	public String getDjxh() {
		return djxh;
	}

	public void setDjxh(String djxh) {
		this.djxh = djxh;
	}

	public void setSjHm(String sjHm) {
		this.sjHm = sjHm;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
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

	public String getRzFs() {
		return rzFs;
	}

	public void setRzFs(String rzFs) {
		this.rzFs = rzFs;
	}
	
	
}
