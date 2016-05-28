package gov.jslt.taxevent.comm;

import java.util.Calendar;

import com.ctp.core.bpo.CssBaseBpoVO;
import com.ctp.core.commutils.DateUtils;

public class TlcslVO extends CssBaseBpoVO {

	private static final long serialVersionUID = 1L;

	public TlcslVO() {
		super();
	}

	// ;
	private Calendar cjsjsj;

	// ;
	private String clly;

	// ;
	private String gdbj;

	// ;
	private Calendar gdsj;

	// ;
	private String gljgdm;

	// ;
	private String lcslh;

	// ;
	private String lsh;

	// ;
	private String nsrmc;

	// ;
	private String nsrsbh;

	// ;
	private String scbj;

	// ;
	private String ssrq;

	// ;
	private String sssxlsh;

	// ;
	private String swglm;

	// ;
	private String swrydm;

	// cjsjsj 的String 的类型;
	private String str_cjsjsj;

	// gdsj 的String 的类型;
	private String str_gdsj;

	public Calendar getCjsjsj() {
		return cjsjsj;
	}

	public String getClly() {
		return clly;
	}

	public String getGdbj() {
		return gdbj;
	}

	public Calendar getGdsj() {
		return gdsj;
	}

	public String getGljgdm() {
		return gljgdm;
	}

	public String getLcslh() {
		return lcslh;
	}

	public String getLsh() {
		return lsh;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public String getNsrsbh() {
		return nsrsbh;
	}

	public String getScbj() {
		return scbj;
	}

	public String getSsrq() {
		return ssrq;
	}

	public String getSssxlsh() {
		return sssxlsh;
	}

	public String getSwglm() {
		return swglm;
	}

	public String getSwrydm() {
		return swrydm;
	}

	public void setCjsjsj(Calendar cjsjsj) {
		status.put("CJSJ_SJ", cjsjsj);
		this.cjsjsj = cjsjsj;
	}

	public void setClly(String clly) {
		/**
		 * 数据库CLLY为Long类型; 类型转换 String ->Long
		 */
		if (null != clly && clly.length() > 0) {
			status.put("CLLY", new Long(clly));
		}
		this.clly = clly;
	}

	public void setGdbj(String gdbj) {
		/**
		 * 数据库GD_BJ为Long类型; 类型转换 String ->Long
		 */
		if (null != gdbj && gdbj.length() > 0) {
			status.put("GD_BJ", new Long(gdbj));
		}
		this.gdbj = gdbj;
	}

	public void setGdsj(Calendar gdsj) {
		status.put("GD_SJ", gdsj);
		this.gdsj = gdsj;
	}

	public void setGljgdm(String gljgdm) {
		status.put("GLJG_DM", gljgdm);
		this.gljgdm = gljgdm;
	}

	public void setLcslh(String lcslh) {
		status.put("LCSLH", lcslh);
		this.lcslh = lcslh;
	}

	public void setLsh(String lsh) {
		status.put("LSH", lsh);
		this.lsh = lsh;
	}

	public void setNsrmc(String nsrmc) {
		status.put("NSRMC", nsrmc);
		this.nsrmc = nsrmc;
	}

	public void setNsrsbh(String nsrsbh) {
		status.put("NSRSBH", nsrsbh);
		this.nsrsbh = nsrsbh;
	}

	public void setScbj(String scbj) {
		/**
		 * 数据库SC_BJ为Long类型; 类型转换 String ->Long
		 */
		if (null != scbj && scbj.length() > 0) {
			status.put("SC_BJ", new Long(scbj));
		}
		this.scbj = scbj;
	}

	public void setSsrq(String ssrq) {
		status.put("SSRQ", ssrq);
		this.ssrq = ssrq;
	}

	public void setSssxlsh(String sssxlsh) {
		status.put("SSSX_LSH", sssxlsh);
		this.sssxlsh = sssxlsh;
	}

	public void setSwglm(String swglm) {
		/**
		 * 数据库SWGLM为Long类型; 类型转换 String ->Long
		 */
		if (null != swglm && swglm.length() > 0) {
			status.put("SWGLM", new Long(swglm));
		}
		this.swglm = swglm;
	}

	public void setSwrydm(String swrydm) {
		status.put("SWRY_DM", swrydm);
		this.swrydm = swrydm;
	}

	// //////////////////////////////////////以下为【Calendar的字段转换为String的部分】///////////////////////////////////////////////
	/*
	 * yyyy-MM-dd HH:mm:ss 第0种 yyyy/MM/dd HH:mm:ss 第1种 yyyy年MM月dd日HH时mm分ss秒 第2种
	 * yyyy-MM-dd 第3种 yyyy/MM/dd 第4种 y-MM-dd 第5种 yy/MM/dd 第6种 yyyy年MM月dd日 第7种
	 * HH:mm:ss 第8种 yyyyMMddHHmmss 第9种 yyyyMMdd 第10种 yyyy.MM.dd 第11种 yy.MM.dd
	 * 第12种
	 */
	// cjsjsj 日期类转换 cjsjsj 的String 的类型 Set方法
	public void setStr_cjsjsj(String str_cjsjsj) {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (str_cjsjsj != "")
			this.setCjsjsj(DateUtils.parseFormatDate(str_cjsjsj, indexFomat));
		else
			this.setCjsjsj(null);
	}

	// cjsjsj 日期类转换 cjsjsj 的String 的类型 Get方法
	public String getStr_cjsjsj() {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (null != this.cjsjsj)
			return DateUtils.toDateStr(this.cjsjsj, indexFomat);
		;
		return "";
	}

	// gdsj 日期类转换 gdsj 的String 的类型 Set方法
	public void setStr_gdsj(String str_gdsj) {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (str_gdsj != "")
			this.setGdsj(DateUtils.parseFormatDate(str_gdsj, indexFomat));
		else
			this.setGdsj(null);
	}

	// gdsj 日期类转换 gdsj 的String 的类型 Get方法
	public String getStr_gdsj() {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (null != this.gdsj)
			return DateUtils.toDateStr(this.gdsj, indexFomat);
		;
		return "";
	}

	// //////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
}
