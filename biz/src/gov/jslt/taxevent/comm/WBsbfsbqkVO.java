package gov.jslt.taxevent.comm;

import java.util.Calendar;

import com.ctp.core.bpo.CssBaseBpoVO;
import com.ctp.core.commutils.DateUtils;

public class WBsbfsbqkVO extends CssBaseBpoVO {

	public WBsbfsbqkVO() {
		super();
	}

	// 打回时间;
	private Calendar dhsj;

	// 注册类型代码;
	private String djzclxdm;

	// 地理位置（乡镇街道）;
	private String dlwzxzdm;

	// ;
	private String fwjgdm;

	// 行业(小类)代码;
	private String gbhydm;

	// 管理机关代码;
	private String gljgdm;

	// 管理机关代码;
	private String hsjgdm;

	// 稽查机关代码;
	private String jcjgdm;

	// ;
	private String jiancjgdm;

	// 缴纳方式;
	private String jnfs;

	// 控股类型代码;
	private String kglxdm;

	// 开户银行;
	private String khyhmc;

	// 隶属关系;
	private String lsgxdm;

	// 联系电话;
	private String lxdh;

	// 纳税人识别码;
	private String nsrsbm;

	// 纳税人名称;
	private String nsrmc;

	// 凭证序号;
	private String pzxh;

	// '','0'未迁移;
	private String qyzt;

	// 迁移时间;
	private Calendar qysj;

	// '0'CA;
	private String rzfs;

	// 社保代码;
	private String sbdm;

	// 税费所属期起;
	private Calendar sfssqqsrq;

	// 税费所属期止;
	private Calendar sfssqzzrq;

	// ;
	private Calendar shjssj;

	// ;
	private String shry;

	// 审核时间;
	private Calendar shsj;

	// 税款所属机关;
	private String skssjgdm;

	// 税务管理码;
	private String swglm;

	// 填表日期;
	private Calendar tbrq;

	// 提交时间;
	private Calendar tjsj;

	// 银行账号;
	private String yhzh;

	// 注册地址;
	private String zcdz;

	// 注册类型;
	private String zclxmc;

	// 征收机关代码;
	private String zsjgdm;

	private String pzzlmc;

	// 状态;
	private String zt;

	// dhsj 的String 的类型;
	private String str_dhsj;

	// qysj 的String 的类型;
	private String str_qysj;

	// sfssqqsrq 的String 的类型;
	private String str_sfssqqsrq;

	// sfssqzzrq 的String 的类型;
	private String str_sfssqzzrq;

	// shjssj 的String 的类型;
	private String str_shjssj;

	// shsj 的String 的类型;
	private String str_shsj;

	// tbrq 的String 的类型;
	private String str_tbrq;

	// tjsj 的String 的类型;
	private String str_tjsj;
	// 应补退税额合计
	private String ybtsehdjje;

	public void setYbtsehdjje(String ybtsehdjje) {
		/**
		 * 数据库YBTSEHDJ_JE为Double类型; 类型转换 String -> Double
		 */
		if (null != ybtsehdjje && ybtsehdjje.length() > 0) {
			status.put("YBTSEHDJ_JE", new Double(ybtsehdjje));
		}
		this.ybtsehdjje = ybtsehdjje;
	}

	public String getYbtsehdjje() {
		return ybtsehdjje;
	}

	public String getPzzlmc() {
		return pzzlmc;
	}

	public void setPzzlmc(String pzzlmc) {
		this.pzzlmc = pzzlmc;
	}

	public Calendar getDhsj() {
		return dhsj;
	}

	public String getDjzclxdm() {
		return djzclxdm;
	}

	public String getDlwzxzdm() {
		return dlwzxzdm;
	}

	public String getFwjgdm() {
		return fwjgdm;
	}

	public String getGbhydm() {
		return gbhydm;
	}

	public String getGljgdm() {
		return gljgdm;
	}

	public String getHsjgdm() {
		return hsjgdm;
	}

	public String getJcjgdm() {
		return jcjgdm;
	}

	public String getJiancjgdm() {
		return jiancjgdm;
	}

	public String getJnfs() {
		return jnfs;
	}

	public String getKglxdm() {
		return kglxdm;
	}

	public String getKhyhmc() {
		return khyhmc;
	}

	public String getLsgxdm() {
		return lsgxdm;
	}

	public String getLxdh() {
		return lxdh;
	}

	public String getNsrsbm() {
		return nsrsbm;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public String getPzxh() {
		return pzxh;
	}

	public String getQyzt() {
		return qyzt;
	}

	public Calendar getQysj() {
		return qysj;
	}

	public String getRzfs() {
		return rzfs;
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

	public Calendar getShjssj() {
		return shjssj;
	}

	public String getShry() {
		return shry;
	}

	public Calendar getShsj() {
		return shsj;
	}

	public String getSkssjgdm() {
		return skssjgdm;
	}

	public String getSwglm() {
		return swglm;
	}

	public Calendar getTbrq() {
		return tbrq;
	}

	public Calendar getTjsj() {
		return tjsj;
	}

	public String getYhzh() {
		return yhzh;
	}

	public String getZcdz() {
		return zcdz;
	}

	public String getZclxmc() {
		return zclxmc;
	}

	public String getZsjgdm() {
		return zsjgdm;
	}

	public String getZt() {
		return zt;
	}

	public void setDhsj(Calendar dhsj) {
		status.put("DH_SJ", dhsj);
		this.dhsj = dhsj;
	}

	public void setDjzclxdm(String djzclxdm) {
		status.put("DJZCLX_DM", djzclxdm);
		this.djzclxdm = djzclxdm;
	}

	public void setDlwzxzdm(String dlwzxzdm) {
		status.put("DLWZXZ_DM", dlwzxzdm);
		this.dlwzxzdm = dlwzxzdm;
	}

	public void setFwjgdm(String fwjgdm) {
		status.put("FWJG_DM", fwjgdm);
		this.fwjgdm = fwjgdm;
	}

	public void setGbhydm(String gbhydm) {
		status.put("GBHY_DM", gbhydm);
		this.gbhydm = gbhydm;
	}

	public void setGljgdm(String gljgdm) {
		status.put("GLJG_DM", gljgdm);
		this.gljgdm = gljgdm;
	}

	public void setHsjgdm(String hsjgdm) {
		status.put("HSJG_DM", hsjgdm);
		this.hsjgdm = hsjgdm;
	}

	public void setJcjgdm(String jcjgdm) {
		status.put("JCJG_DM", jcjgdm);
		this.jcjgdm = jcjgdm;
	}

	public void setJiancjgdm(String jiancjgdm) {
		status.put("JIANCJG_DM", jiancjgdm);
		this.jiancjgdm = jiancjgdm;
	}

	public void setJnfs(String jnfs) {
		status.put("JNFS", jnfs);
		this.jnfs = jnfs;
	}

	public void setKglxdm(String kglxdm) {
		status.put("KGLX_DM", kglxdm);
		this.kglxdm = kglxdm;
	}

	public void setKhyhmc(String khyhmc) {
		status.put("KHYH_MC", khyhmc);
		this.khyhmc = khyhmc;
	}

	public void setLsgxdm(String lsgxdm) {
		status.put("LSGX_DM", lsgxdm);
		this.lsgxdm = lsgxdm;
	}

	public void setLxdh(String lxdh) {
		status.put("LXDH", lxdh);
		this.lxdh = lxdh;
	}

	public void setNsrsbm(String nsrsbm) {
		status.put("NSRSBM", nsrsbm);
		this.nsrsbm = nsrsbm;
	}

	public void setNsrmc(String nsrmc) {
		status.put("NSR_MC", nsrmc);
		this.nsrmc = nsrmc;
	}

	public void setPzxh(String pzxh) {
		status.put("PZ_XH", pzxh);
		this.pzxh = pzxh;
	}

	public void setQyzt(String qyzt) {
		status.put("QYZT", qyzt);
		this.qyzt = qyzt;
	}

	public void setQysj(Calendar qysj) {
		status.put("QY_SJ", qysj);
		this.qysj = qysj;
	}

	public void setRzfs(String rzfs) {
		status.put("RZFS", rzfs);
		this.rzfs = rzfs;
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

	public void setShjssj(Calendar shjssj) {
		status.put("SHJS_SJ", shjssj);
		this.shjssj = shjssj;
	}

	public void setShry(String shry) {
		status.put("SH_RY", shry);
		this.shry = shry;
	}

	public void setShsj(Calendar shsj) {
		status.put("SH_SJ", shsj);
		this.shsj = shsj;
	}

	public void setSkssjgdm(String skssjgdm) {
		status.put("SKSSJG_DM", skssjgdm);
		this.skssjgdm = skssjgdm;
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

	public void setTbrq(Calendar tbrq) {
		status.put("TB_RQ", tbrq);
		this.tbrq = tbrq;
	}

	public void setTjsj(Calendar tjsj) {
		status.put("TJ_SJ", tjsj);
		this.tjsj = tjsj;
	}

	public void setYhzh(String yhzh) {
		status.put("YH_ZH", yhzh);
		this.yhzh = yhzh;
	}

	public void setZcdz(String zcdz) {
		status.put("ZCDZ", zcdz);
		this.zcdz = zcdz;
	}

	public void setZclxmc(String zclxmc) {
		status.put("ZCLX_MC", zclxmc);
		this.zclxmc = zclxmc;
	}

	public void setZsjgdm(String zsjgdm) {
		status.put("ZSJG_DM", zsjgdm);
		this.zsjgdm = zsjgdm;
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
	// dhsj 日期类转换 dhsj 的String 的类型 Set方法
	public void setStr_dhsj(String str_dhsj) {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (str_dhsj != "")
			this.setDhsj(DateUtils.parseFormatDate(str_dhsj, indexFomat));
		else
			this.setDhsj(null);
	}

	// dhsj 日期类转换 dhsj 的String 的类型 Get方法
	public String getStr_dhsj() {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (null != this.dhsj)
			return DateUtils.toDateStr(this.dhsj, indexFomat);
		;
		return "";
	}

	// qysj 日期类转换 qysj 的String 的类型 Set方法
	public void setStr_qysj(String str_qysj) {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (str_qysj != "")
			this.setQysj(DateUtils.parseFormatDate(str_qysj, indexFomat));
		else
			this.setQysj(null);
	}

	// qysj 日期类转换 qysj 的String 的类型 Get方法
	public String getStr_qysj() {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (null != this.qysj)
			return DateUtils.toDateStr(this.qysj, indexFomat);
		;
		return "";
	}

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

	// shjssj 日期类转换 shjssj 的String 的类型 Set方法
	public void setStr_shjssj(String str_shjssj) {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (str_shjssj != "")
			this.setShjssj(DateUtils.parseFormatDate(str_shjssj, indexFomat));
		else
			this.setShjssj(null);
	}

	// shjssj 日期类转换 shjssj 的String 的类型 Get方法
	public String getStr_shjssj() {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (null != this.shjssj)
			return DateUtils.toDateStr(this.shjssj, indexFomat);
		;
		return "";
	}

	// shsj 日期类转换 shsj 的String 的类型 Set方法
	public void setStr_shsj(String str_shsj) {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (str_shsj != "")
			this.setShsj(DateUtils.parseFormatDate(str_shsj, indexFomat));
		else
			this.setShsj(null);
	}

	// shsj 日期类转换 shsj 的String 的类型 Get方法
	public String getStr_shsj() {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (null != this.shsj)
			return DateUtils.toDateStr(this.shsj, indexFomat);
		;
		return "";
	}

	// tbrq 日期类转换 tbrq 的String 的类型 Set方法
	public void setStr_tbrq(String str_tbrq) {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (str_tbrq != "")
			this.setTbrq(DateUtils.parseFormatDate(str_tbrq, indexFomat));
		else
			this.setTbrq(null);
	}

	// tbrq 日期类转换 tbrq 的String 的类型 Get方法
	public String getStr_tbrq() {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (null != this.tbrq)
			return DateUtils.toDateStr(this.tbrq, indexFomat);
		;
		return "";
	}

	// tjsj 日期类转换 tjsj 的String 的类型 Set方法
	public void setStr_tjsj(String str_tjsj) {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (str_tjsj != "")
			this.setTjsj(DateUtils.parseFormatDate(str_tjsj, indexFomat));
		else
			this.setTjsj(null);
	}

	// tjsj 日期类转换 tjsj 的String 的类型 Get方法
	public String getStr_tjsj() {
		// indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
		int indexFomat = 3;
		if (null != this.tjsj)
			return DateUtils.toDateStr(this.tjsj, indexFomat);
		;
		return "";
	}

}
