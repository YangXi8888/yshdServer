package gov.jslt.taxevent.comm;

import java.util.Calendar;

import com.ctp.core.bpo.CssBaseBpoVO;
import com.ctp.core.commutils.DateUtils;

public class WBsbbqkVO extends CssBaseBpoVO {

	public WBsbbqkVO() {
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
	private String jdflag;

	// ;
	private String jiancjgdm;

	// 控股类型代码;
	private String kglxdm;

	// 开户银行;
	private String khyhmc;

	// 隶属关系;
	private String lsgxdm;

	// 纳税人识别码;
	private String nsrsbm;

	// 纳税人名称;
	private String nsrmc;

	// 凭证种类代码;
	private String pzzldm;

	// 凭证序号;
	private String pzxh;

	// 主凭证序号;
	private String zhupzxh;

	// '','0'未迁移;
	private String qyzt;

	// 迁移时间;
	private Calendar qysj;

	// '0'CA;
	private String rzfs;

	// ;
	private String sbzldm;

	// 税费所属期起;
	private Calendar sfssqqsrq;

	// 税费所属期止;
	private Calendar sfssqzzrq;

	// 审核人员;
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

	// ;
	private String xmflag;

	// ;
	private String xmm;

	// 本张报表应补退税额合计;
	private String ybtsehdjje;

	// 银行账号;
	private String yhzh;

	// 注册类型;
	private String zclxmc;

	// 征收机关代码;
	private String zsjgdm;

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

	// shsj 的String 的类型;
	private String str_shsj;

	// tbrq 的String 的类型;
	private String str_tbrq;

	// tjsj 的String 的类型;
	private String str_tjsj;

	private String tjry;

	private String I_ROOTBILLID;

	public String getI_ROOTBILLID() {
		return I_ROOTBILLID;
	}

	public void setI_ROOTBILLID(String i_rootbillid) {
		if (null != i_rootbillid && i_rootbillid.length() > 0) {
			status.put("I_ROOTBILLID", i_rootbillid);
		}
		this.I_ROOTBILLID = i_rootbillid;
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

	public String getJdflag() {
		return jdflag;
	}

	public String getJiancjgdm() {
		return jiancjgdm;
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

	public String getNsrsbm() {
		return nsrsbm;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public String getPzzldm() {
		return pzzldm;
	}

	public String getPzxh() {
		return pzxh;
	}

	public String getZhupzxh() {
		return zhupzxh;
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

	public String getSbzldm() {
		return sbzldm;
	}

	public Calendar getSfssqqsrq() {
		return sfssqqsrq;
	}

	public Calendar getSfssqzzrq() {
		return sfssqzzrq;
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

	public String getXmflag() {
		return xmflag;
	}

	public String getXmm() {
		return xmm;
	}

	public String getYbtsehdjje() {
		return ybtsehdjje;
	}

	public String getYhzh() {
		return yhzh;
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

	public String getTjry() {
		return tjry;
	}

	public void setTjry(String tjry) {
		status.put("TJ_RY", tjry);
		this.tjry = tjry;
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

	public void setJdflag(String jdflag) {
		status.put("JDFLAG", jdflag);
		this.jdflag = jdflag;
	}

	public void setJiancjgdm(String jiancjgdm) {
		status.put("JIANCJG_DM", jiancjgdm);
		this.jiancjgdm = jiancjgdm;
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

	public void setNsrsbm(String nsrsbm) {
		status.put("NSRSBM", nsrsbm);
		this.nsrsbm = nsrsbm;
	}

	public void setNsrmc(String nsrmc) {
		status.put("NSR_MC", nsrmc);
		this.nsrmc = nsrmc;
	}

	public void setPzzldm(String pzzldm) {
		status.put("PZZL_DM", pzzldm);
		this.pzzldm = pzzldm;
	}

	public void setPzxh(String pzxh) {
		status.put("PZ_XH", pzxh);
		this.pzxh = pzxh;
	}

	public void setZhupzxh(String zhupzxh) {
		status.put("ZHUPZ_XH", zhupzxh);
		this.zhupzxh = zhupzxh;
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

	public void setSbzldm(String sbzldm) {
		status.put("SBZL_DM", sbzldm);
		this.sbzldm = sbzldm;
	}

	public void setSfssqqsrq(Calendar sfssqqsrq) {
		status.put("SFSSQ_QSRQ", sfssqqsrq);
		this.sfssqqsrq = sfssqqsrq;
	}

	public void setSfssqzzrq(Calendar sfssqzzrq) {
		status.put("SFSSQ_ZZRQ", sfssqzzrq);
		this.sfssqzzrq = sfssqzzrq;
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

	public void setXmflag(String xmflag) {
		status.put("XMFLAG", xmflag);
		this.xmflag = xmflag;
	}

	public void setXmm(String xmm) {
		status.put("XMM", xmm);
		this.xmm = xmm;
	}

	public void setYbtsehdjje(String ybtsehdjje) {
		/**
		 * 数据库YBTSEHDJ_JE为Double类型; 类型转换 String -> Double
		 */
		if (null != ybtsehdjje && ybtsehdjje.length() > 0) {
			status.put("YBTSEHDJ_JE", new Double(ybtsehdjje));
		}
		this.ybtsehdjje = ybtsehdjje;
	}

	public void setYhzh(String yhzh) {
		status.put("YH_ZH", yhzh);
		this.yhzh = yhzh;
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

	// //////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
}
