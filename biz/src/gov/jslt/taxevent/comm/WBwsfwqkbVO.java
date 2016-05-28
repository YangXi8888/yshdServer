package gov.jslt.taxevent.comm;

import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class WBwsfwqkbVO extends CssBaseBpoVO {

	public WBwsfwqkbVO() {
		super();
		this.setSjly("1");
	}

	// 备用字段;
	private String byzd;

	// 0非CA，1CA;
	private String carz;

	// 管理机关代码;
	private String gljgdm;

	// 迁移标志;
	private String isprocess;

	// 发起流程时，回写流程实例号;
	private String lcslh;

	// 流水号;
	private String lsh;

	// 联系电话;
	private String lxdh;

	// 联系人;
	private String lxr;

	// 受理机关代码;
	private String slswjgdm;

	// 受理机关名称;
	private String slswjgmc;

	// 受理人员代码;
	private String slswrydm;

	// 受理人员名称;
	private String slswrymc;

	// 申请类型代码;
	private String sqlxdm;

	// 申请时间;
	private Calendar sqsj;

	// 涉税类型，0预申请1网上办结;
	private String sslx;

	// 涉税申请大类;
	private String sssqdl;

	// 涉税申请小类;
	private String sssqxl;

	// 涉税项目代码;
	private String ssxmdm;

	// 涉税项目名称;
	private String ssxmmc;

	// 税务管理码;
	private String swglm;

	// 流程未启动退回时间;
	private Calendar thsj;

	// 项目编码;
	private String xmbm;

	// 意见;
	private String yj;

	// 征收项目;
	private String zsxm;

	// 0 已暂存 1 已提交 2 已迁移 3 审核通过 4审核不通过(非初审) 5 审核中 6退回（初审） 7退回(非初审) 8迁移中 9初审通过
	// 10审核不通过(初审) 11已归档;
	private String zt;

	// sqsj 的String 的类型;
	private String str_sqsj;

	// thsj 的String 的类型;
	private String str_thsj;

	private String sjly = "1";

	public String getByzd() {
		return byzd;
	}

	public String getSjly() {
		return sjly;
	}

	public void setSjly(String sjly) {
		status.put("SJLY", sjly);
		this.sjly = sjly;
	}

	public String getCarz() {
		return carz;
	}

	public String getGljgdm() {
		return gljgdm;
	}

	public String getIsprocess() {
		return isprocess;
	}

	public String getLcslh() {
		return lcslh;
	}

	public String getLsh() {
		return lsh;
	}

	public String getLxdh() {
		return lxdh;
	}

	public String getLxr() {
		return lxr;
	}

	public String getSlswjgdm() {
		return slswjgdm;
	}

	public String getSlswjgmc() {
		return slswjgmc;
	}

	public String getSlswrydm() {
		return slswrydm;
	}

	public String getSlswrymc() {
		return slswrymc;
	}

	public String getSqlxdm() {
		return sqlxdm;
	}

	public Calendar getSqsj() {
		return sqsj;
	}

	public String getSslx() {
		return sslx;
	}

	public String getSssqdl() {
		return sssqdl;
	}

	public String getSssqxl() {
		return sssqxl;
	}

	public String getSsxmdm() {
		return ssxmdm;
	}

	public String getSsxmmc() {
		return ssxmmc;
	}

	public String getSwglm() {
		return swglm;
	}

	public Calendar getThsj() {
		return thsj;
	}

	public String getXmbm() {
		return xmbm;
	}

	public String getYj() {
		return yj;
	}

	public String getZsxm() {
		return zsxm;
	}

	public String getZt() {
		return zt;
	}

	public void setByzd(String byzd) {
		status.put("BYZD", byzd);
		this.byzd = byzd;
	}

	public void setCarz(String carz) {
		status.put("CARZ", carz);
		this.carz = carz;
	}

	public void setGljgdm(String gljgdm) {
		status.put("GLJG_DM", gljgdm);
		this.gljgdm = gljgdm;
	}

	public void setIsprocess(String isprocess) {
		/**
		 * 数据库ISPROCESS为Long类型; 类型转换 String ->Long
		 */
		if (null != isprocess && isprocess.length() > 0) {
			status.put("ISPROCESS", new Long(isprocess));
		}
		this.isprocess = isprocess;
	}

	public void setLcslh(String lcslh) {
		status.put("LCSLH", lcslh);
		this.lcslh = lcslh;
	}

	public void setLsh(String lsh) {
		status.put("LSH", lsh);
		this.lsh = lsh;
	}

	public void setLxdh(String lxdh) {
		status.put("LXDH", lxdh);
		this.lxdh = lxdh;
	}

	public void setLxr(String lxr) {
		status.put("LXR", lxr);
		this.lxr = lxr;
	}

	public void setSlswjgdm(String slswjgdm) {
		status.put("SLSWJGDM", slswjgdm);
		this.slswjgdm = slswjgdm;
	}

	public void setSlswjgmc(String slswjgmc) {
		status.put("SLSWJGMC", slswjgmc);
		this.slswjgmc = slswjgmc;
	}

	public void setSlswrydm(String slswrydm) {
		status.put("SLSWRYDM", slswrydm);
		this.slswrydm = slswrydm;
	}

	public void setSlswrymc(String slswrymc) {
		status.put("SLSWRYMC", slswrymc);
		this.slswrymc = slswrymc;
	}

	public void setSqlxdm(String sqlxdm) {
		status.put("SQLX_DM", sqlxdm);
		this.sqlxdm = sqlxdm;
	}

	public void setSqsj(Calendar sqsj) {
		status.put("SQ_SJ", sqsj);
		this.sqsj = sqsj;
	}

	public void setSslx(String sslx) {
		status.put("SSLX", sslx);
		this.sslx = sslx;
	}

	public void setSssqdl(String sssqdl) {
		status.put("SSSQ_DL", sssqdl);
		this.sssqdl = sssqdl;
	}

	public void setSssqxl(String sssqxl) {
		status.put("SSSQ_XL", sssqxl);
		this.sssqxl = sssqxl;
	}

	public void setSsxmdm(String ssxmdm) {
		status.put("SSXMDM", ssxmdm);
		this.ssxmdm = ssxmdm;
	}

	public void setSsxmmc(String ssxmmc) {
		status.put("SSXMMC", ssxmmc);
		this.ssxmmc = ssxmmc;
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

	public void setThsj(Calendar thsj) {
		status.put("TH_SJ", thsj);
		this.thsj = thsj;
	}

	public void setXmbm(String xmbm) {
		status.put("XMBM", xmbm);
		this.xmbm = xmbm;
	}

	public void setYj(String yj) {
		status.put("YJ", yj);
		this.yj = yj;
	}

	public void setZsxm(String zsxm) {
		status.put("ZSXM", zsxm);
		this.zsxm = zsxm;
	}

	public void setZt(String zt) {
		status.put("ZT", zt);
		this.zt = zt;
	}

	// //////////////////////////////////////以下为【Calendar的字段转换为String的部分】///////////////////////////////////////////////
	/*
	 * yyyy-MM-dd HH:mm:ss 第0种 yyyy/MM/dd HH:mm:ss 第1种 yyyy年MM月dd日HH时mm分ss秒 第2种
	 * yyyy-MM-dd 第3种 yyyy/MM/dd 第4种 y-MM-dd 第5种 yy/MM/dd 第6种 yyyy年MM月dd日 第7种
	 * HH:mm:ss 第8种 yyyyMMddHHmmss 第9种 yyyyMMdd 第10种 yyyy.MM.dd 第11种 yy.MM.dd
	 * 第12种
	 */
	// sqsj 日期类转换 sqsj 的String 的类型 Set方法
	public void setStr_sqsj(String str_sqsj) {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (str_sqsj != "")
			this.setSqsj(DateUtils.parseFormatDate(str_sqsj, indexFomat));
		else
			this.setSqsj(null);
	}

	// sqsj 日期类转换 sqsj 的String 的类型 Get方法
	public String getStr_sqsj() {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (null != this.sqsj)
			return DateUtils.toDateStr(this.sqsj, indexFomat);
		;
		return "";
	}

	// thsj 日期类转换 thsj 的String 的类型 Set方法
	public void setStr_thsj(String str_thsj) {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (str_thsj != "")
			this.setThsj(DateUtils.parseFormatDate(str_thsj, indexFomat));
		else
			this.setThsj(null);
	}

	// thsj 日期类转换 thsj 的String 的类型 Get方法
	public String getStr_thsj() {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (null != this.thsj)
			return DateUtils.toDateStr(this.thsj, indexFomat);
		;
		return "";
	}

	// //////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
}
