package gov.jslt.taxevent.bsfw.sbns.sbns002;

import java.util.Calendar;

import com.ctp.core.bpo.CssBaseBpoVO;
import com.ctp.core.commutils.DateUtils;

public class WBsbfsbVO extends CssBaseBpoVO {

	public WBsbfsbVO() {
		super();
	}

	// 补录标记(BL_BJ) 表示是已经在通用申报申报过 在这里补录的数据BL_BJ=‘1’
	// ；还是没有经过通用申报而直接在单项申报表录入的BL_BJ=‘0’，这样统计各申报表数据的时候可以避免重复统计。;
	private String blbj;

	// 凭证序号;
	private String pzxh;

	// 申报表顺序号(SBB_XH)表示是从第几张申报表补录的，如一个纳税人填写2张营业税申报表 在通用申报表一次录入，一个凭证序号
	// 而在补录的时候分2次补录，这个时候本字段用来填写是第几张。;
	private String sbbxh;

	// 申报明细序号;
	private String sbmxxh;

	// 社保费品目代码;
	private String sbzspmdm;

	// 社保费险种代码;
	private String sbzsxmdm;

	// 社保代码;
	private String sbdm;

	// 税款所属期起;
	private Calendar sfssqqsrq;

	// 税费所属期止;
	private Calendar sfssqzzrq;

	// '0'审核通过'1'一般规则不通过'2'强制规则不通过;
	private String shbj;

	// 实缴金额;
	private String sjje;

	// 纳税人税务管理码;
	private String swglm;

	// 应缴金额;
	private String yjje;

	// 社保应征数据鉴定中的序号;
	private String yzxh;

	// sfssqqsrq 的String 的类型;
	private String str_sfssqqsrq;

	// sfssqzzrq 的String 的类型;
	private String str_sfssqzzrq;

	public String getBlbj() {
		return blbj;
	}

	public String getPzxh() {
		return pzxh;
	}

	public String getSbbxh() {
		return sbbxh;
	}

	public String getSbmxxh() {
		return sbmxxh;
	}

	public String getSbzspmdm() {
		return sbzspmdm;
	}

	public String getSbzsxmdm() {
		return sbzsxmdm;
	}

	public String getSbdm() {
		return sbdm;
	}

	public Calendar getSfssqqsrq() {
		return sfssqqsrq;
	}

	public Calendar getSfssqzzrq() {
		return sfssqzzrq;
	}

	public String getShbj() {
		return shbj;
	}

	public String getSjje() {
		return sjje;
	}

	public String getSwglm() {
		return swglm;
	}

	public String getYjje() {
		return yjje;
	}

	public String getYzxh() {
		return yzxh;
	}

	public void setBlbj(String blbj) {
		status.put("BL_BJ", blbj);
		this.blbj = blbj;
	}

	public void setPzxh(String pzxh) {
		status.put("PZ_XH", pzxh);
		this.pzxh = pzxh;
	}

	public void setSbbxh(String sbbxh) {
		status.put("SBB_XH", sbbxh);
		this.sbbxh = sbbxh;
	}

	public void setSbmxxh(String sbmxxh) {
		/**
		 * 数据库SBMX_XH为Long类型; 类型转换 String ->Long
		 */
		if (null != sbmxxh && sbmxxh.length() > 0) {
			status.put("SBMX_XH", new Long(sbmxxh));
		}
		this.sbmxxh = sbmxxh;
	}

	public void setSbzspmdm(String sbzspmdm) {
		status.put("SBZSPM_DM", sbzspmdm);
		this.sbzspmdm = sbzspmdm;
	}

	public void setSbzsxmdm(String sbzsxmdm) {
		status.put("SBZSXM_DM", sbzsxmdm);
		this.sbzsxmdm = sbzsxmdm;
	}

	public void setSbdm(String sbdm) {
		status.put("SB_DM", sbdm);
		this.sbdm = sbdm;
	}

	public void setSfssqqsrq(Calendar sfssqqsrq) {
		status.put("SFSSQ_QSRQ", sfssqqsrq);
		this.sfssqqsrq = sfssqqsrq;
	}

	public void setSfssqzzrq(Calendar sfssqzzrq) {
		status.put("SFSSQ_ZZRQ", sfssqzzrq);
		this.sfssqzzrq = sfssqzzrq;
	}

	public void setShbj(String shbj) {
		status.put("SH_BJ", shbj);
		this.shbj = shbj;
	}

	public void setSjje(String sjje) {
		/**
		 * 数据库SJ_JE为Double类型; 类型转换 String -> Double
		 */
		if (null != sjje && sjje.length() > 0) {
			status.put("SJ_JE", new Double(sjje));
		}
		this.sjje = sjje;
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

	public void setYjje(String yjje) {
		/**
		 * 数据库YJ_JE为Double类型; 类型转换 String -> Double
		 */
		if (null != yjje && yjje.length() > 0) {
			status.put("YJ_JE", new Double(yjje));
		}
		this.yjje = yjje;
	}

	public void setYzxh(String yzxh) {
		status.put("YZ_XH", yzxh);
		this.yzxh = yzxh;
	}

	// //////////////////////////////////////以下为【Calendar的字段转换为String的部分】///////////////////////////////////////////////
	/*
	 * yyyy-MM-dd HH:mm:ss 第0种 yyyy/MM/dd HH:mm:ss 第1种 yyyy年MM月dd日HH时mm分ss秒 第2种
	 * yyyy-MM-dd 第3种 yyyy/MM/dd 第4种 y-MM-dd 第5种 yy/MM/dd 第6种 yyyy年MM月dd日 第7种
	 * HH:mm:ss 第8种 yyyyMMddHHmmss 第9种 yyyyMMdd 第10种 yyyy.MM.dd 第11种 yy.MM.dd
	 * 第12种
	 */
	// sfssqqsrq 日期类转换 sfssqqsrq 的String 的类型 Set方法
	public void setStr_sfssqqsrq(String str_sfssqqsrq) {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (str_sfssqqsrq != "")
			this.setSfssqqsrq(DateUtils.parseFormatDate(str_sfssqqsrq,
					indexFomat));
		else
			this.setSfssqqsrq(null);
	}

	// sfssqqsrq 日期类转换 sfssqqsrq 的String 的类型 Get方法
	public String getStr_sfssqqsrq() {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (null != this.sfssqqsrq)
			return DateUtils.toDateStr(this.sfssqqsrq, indexFomat);
		;
		return "";
	}

	// sfssqzzrq 日期类转换 sfssqzzrq 的String 的类型 Set方法
	public void setStr_sfssqzzrq(String str_sfssqzzrq) {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (str_sfssqzzrq != "")
			this.setSfssqzzrq(DateUtils.parseFormatDate(str_sfssqzzrq,
					indexFomat));
		else
			this.setSfssqzzrq(null);
	}

	// sfssqzzrq 日期类转换 sfssqzzrq 的String 的类型 Get方法
	public String getStr_sfssqzzrq() {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (null != this.sfssqzzrq)
			return DateUtils.toDateStr(this.sfssqzzrq, indexFomat);
		;
		return "";
	}

}
