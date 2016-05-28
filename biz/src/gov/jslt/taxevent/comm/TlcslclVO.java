package gov.jslt.taxevent.comm;

import com.ctp.core.bpo.CssBaseBpoVO;

public class TlcslclVO extends CssBaseBpoVO {

	private static final long serialVersionUID = 1L;

	public TlcslclVO() {
		super();
	}

	// ;
	private String cjsj;

	// ;
	private String clly;

	// ;
	private String czlsh;

	// ;
	private String fbzldm;

	// ;
	private String lcslclmc;

	// ;
	private String lcslh;

	// ;
	private String lsh;

	// ;
	private String scbj;

	// ;
	private String sczt;

	// ;
	private String ssclbh;

	// ;
	private String txm;

	public String getCjsj() {
		return cjsj;
	}

	public String getClly() {
		return clly;
	}

	public String getCzlsh() {
		return czlsh;
	}

	public String getFbzldm() {
		return fbzldm;
	}

	public String getLcslclmc() {
		return lcslclmc;
	}

	public String getLcslh() {
		return lcslh;
	}

	public String getLsh() {
		return lsh;
	}

	public String getScbj() {
		return scbj;
	}

	public String getSczt() {
		return sczt;
	}

	public String getSsclbh() {
		return ssclbh;
	}

	public String getTxm() {
		return txm;
	}

	public void setCjsj(String cjsj) {
		status.put("CJSJ", cjsj);
		this.cjsj = cjsj;
	}

	public void setClly(String clly) {
		status.put("CLLY", clly);
		this.clly = clly;
	}

	public void setCzlsh(String czlsh) {
		status.put("CZ_LSH", czlsh);
		this.czlsh = czlsh;
	}

	public void setFbzldm(String fbzldm) {
		status.put("FBZLDM", fbzldm);
		this.fbzldm = fbzldm;
	}

	public void setLcslclmc(String lcslclmc) {
		status.put("LCSLCL_MC", lcslclmc);
		this.lcslclmc = lcslclmc;
	}

	public void setLcslh(String lcslh) {
		status.put("LCSLH", lcslh);
		this.lcslh = lcslh;
	}

	public void setLsh(String lsh) {
		status.put("LSH", lsh);
		this.lsh = lsh;
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

	public void setSczt(String sczt) {
		/**
		 * 数据库SC_ZT为Long类型; 类型转换 String ->Long
		 */
		if (null != sczt && sczt.length() > 0) {
			status.put("SC_ZT", new Long(sczt));
		}
		this.sczt = sczt;
	}

	public void setSsclbh(String ssclbh) {
		status.put("SSCLBH", ssclbh);
		this.ssclbh = ssclbh;
	}

	public void setTxm(String txm) {
		status.put("TXM", txm);
		this.txm = txm;
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
