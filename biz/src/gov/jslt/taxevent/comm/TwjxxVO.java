package gov.jslt.taxevent.comm;

import com.ctp.core.bpo.CssBaseBpoVO;

public class TwjxxVO extends CssBaseBpoVO {

	private static final long serialVersionUID = 1L;

	public TwjxxVO() {
		super();
	}

	// ;
	private String lsh;

	// ;
	private String lshwj;

	// ;
	private String scbj;

	// ;
	private String wjdx;

	// ;
	private String wjlx;

	// ;
	private String wjmc;

	// ;
	private String wjwz;

	// ;
	private String xh;

	public String getLsh() {
		return lsh;
	}

	public String getLshwj() {
		return lshwj;
	}

	public String getScbj() {
		return scbj;
	}

	public String getWjdx() {
		return wjdx;
	}

	public String getWjlx() {
		return wjlx;
	}

	public String getWjmc() {
		return wjmc;
	}

	public String getWjwz() {
		return wjwz;
	}

	public String getXh() {
		return xh;
	}

	public void setLsh(String lsh) {
		status.put("LSH", lsh);
		this.lsh = lsh;
	}

	public void setLshwj(String lshwj) {
		status.put("LSH_WJ", lshwj);
		this.lshwj = lshwj;
	}

	public void setScbj(String scbj) {
		/**
		 * 数据库SCBJ为Long类型; 类型转换 String ->Long
		 */
		if (null != scbj && scbj.length() > 0) {
			status.put("SCBJ", new Long(scbj));
		}
		this.scbj = scbj;
	}

	public void setWjdx(String wjdx) {
		status.put("WJDX", wjdx);
		this.wjdx = wjdx;
	}

	public void setWjlx(String wjlx) {
		/**
		 * 数据库WJLX为Long类型; 类型转换 String ->Long
		 */
		if (null != wjlx && wjlx.length() > 0) {
			status.put("WJLX", new Long(wjlx));
		}
		this.wjlx = wjlx;
	}

	public void setWjmc(String wjmc) {
		status.put("WJMC", wjmc);
		this.wjmc = wjmc;
	}

	public void setWjwz(String wjwz) {
		status.put("WJWZ", wjwz);
		this.wjwz = wjwz;
	}

	public void setXh(String xh) {
		status.put("XH", xh);
		this.xh = xh;
	}

	// //////////////////////////////////////以下为【Calendar的字段转换为String的部分】///////////////////////////////////////////////
	/*
	 * yyyy-MM-dd HH:mm:ss 第0种 yyyy/MM/dd HH:mm:ss 第1种 yyyy年MM月dd日HH时mm分ss秒 第2种
	 * yyyy-MM-dd 第3种 yyyy/MM/dd 第4种 y-MM-dd 第5种 yy/MM/dd 第6种 yyyy年MM月dd日 第7种
	 * HH:mm:ss 第8种 yyyyMMddHHmmss 第9种 yyyyMMdd 第10种 yyyy.MM.dd 第11种 yy.MM.dd
	 * 第12种
	 */
	// //////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
}
