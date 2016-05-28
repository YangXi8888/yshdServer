package gov.jslt.taxevent.comm;

import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class JgnsrVO extends CssBaseBpoVO {

	public JgnsrVO() {
		super();
	}

	// 注册类型代码;
	private String djzclxdm;

	// 地理位置（村）代码;
	private String dlwzcdm;

	// 地理位置（乡镇街道）;
	private String dlwzxzdm;

	// 纳税服务中心机关代码;
	private String fwjgdm;

	// 行业(小类)代码;
	private String gbhydm;

	// 管理机关代码;
	private String gljgdm;

	// 纳税人划分到管理区域，管理区域分给税收管理员;
	private String glqydm;

	// 管辖权限分类属性1;
	private String gxflsx1dm;

	// 管辖权限分类属性2;
	private String gxflsx2dm;

	// 管辖权限分类属性3;
	private String gxflsx3dm;

	// 稽查机关代码;
	private String jcjgdm;

	// 检查机关代码;
	private String jiancjgdm;

	// 经营行业代码;
	private String jyhydm;

	// 控股类型代码;
	private String kglxdm;

	// 记录数据录入人的所在机关;
	private String lrjgdm;

	// 录入人员;
	private String lrrydm;

	// 隶属关系;
	private String lsgxdm;

	// 纳税人识别码;
	private String nsrsbm;

	// 税务登记状态;
	private String nsrztdm;

	// 纳税人名称;
	private String nsrmc;

	// 生产经营地联系电话;
	private String scjydlxdh;

	// 实际经营地址;
	private String sjjydz;

	// 生产经营地址邮编;
	private String sjjydzyb;

	// 税款所属机关;
	private String skssjgdm;

	// 设立登记标记;
	private String swdjlxdm;

	// 税务管理码;
	private String swglm;

	// 使用工作流产生时的文书号，有些类型的登记不使用工作流则该字段可以为空;
	private String wsh;

	// 记录数据修改人的所在机关;
	private String xgjgdm;

	// 修改人员;
	private String xgrydm;

	// 注册地联系电话;
	private String zcdlxdh;

	// 注册地址;
	private String zcdz;

	// 注册地址邮编;
	private String zcdzyb;

	// 重点税源认定;
	private String zdsyrddm;

	// 征收机关代码;
	private String zsjgdm;

	public String getDjzclxdm() {
		return djzclxdm;
	}

	public String getDlwzcdm() {
		return dlwzcdm;
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

	public String getGlqydm() {
		return glqydm;
	}

	public String getGxflsx1dm() {
		return gxflsx1dm;
	}

	public String getGxflsx2dm() {
		return gxflsx2dm;
	}

	public String getGxflsx3dm() {
		return gxflsx3dm;
	}

	public String getJcjgdm() {
		return jcjgdm;
	}

	public String getJiancjgdm() {
		return jiancjgdm;
	}

	public String getJyhydm() {
		return jyhydm;
	}

	public String getKglxdm() {
		return kglxdm;
	}

	public String getLrjgdm() {
		return lrjgdm;
	}

	public String getLrrydm() {
		return lrrydm;
	}

	public String getLsgxdm() {
		return lsgxdm;
	}

	public String getNsrsbm() {
		return nsrsbm;
	}

	public String getNsrztdm() {
		return nsrztdm;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public String getScjydlxdh() {
		return scjydlxdh;
	}

	public String getSjjydz() {
		return sjjydz;
	}

	public String getSjjydzyb() {
		return sjjydzyb;
	}

	public String getSkssjgdm() {
		return skssjgdm;
	}

	public String getSwdjlxdm() {
		return swdjlxdm;
	}

	public String getSwglm() {
		return swglm;
	}

	public String getWsh() {
		return wsh;
	}

	public String getXgjgdm() {
		return xgjgdm;
	}

	public String getXgrydm() {
		return xgrydm;
	}

	public String getZcdlxdh() {
		return zcdlxdh;
	}

	public String getZcdz() {
		return zcdz;
	}

	public String getZcdzyb() {
		return zcdzyb;
	}

	public String getZdsyrddm() {
		return zdsyrddm;
	}

	public String getZsjgdm() {
		return zsjgdm;
	}

	public void setDjzclxdm(String djzclxdm) {
		status.put("DJZCLX_DM", djzclxdm);
		this.djzclxdm = djzclxdm;
	}

	public void setDlwzcdm(String dlwzcdm) {
		status.put("DLWZC_DM", dlwzcdm);
		this.dlwzcdm = dlwzcdm;
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

	public void setGlqydm(String glqydm) {
		status.put("GLQY_DM", glqydm);
		this.glqydm = glqydm;
	}

	public void setGxflsx1dm(String gxflsx1dm) {
		status.put("GXFLSX1_DM", gxflsx1dm);
		this.gxflsx1dm = gxflsx1dm;
	}

	public void setGxflsx2dm(String gxflsx2dm) {
		status.put("GXFLSX2_DM", gxflsx2dm);
		this.gxflsx2dm = gxflsx2dm;
	}

	public void setGxflsx3dm(String gxflsx3dm) {
		status.put("GXFLSX3_DM", gxflsx3dm);
		this.gxflsx3dm = gxflsx3dm;
	}

	public void setJcjgdm(String jcjgdm) {
		status.put("JCJG_DM", jcjgdm);
		this.jcjgdm = jcjgdm;
	}

	public void setJiancjgdm(String jiancjgdm) {
		status.put("JIANCJG_DM", jiancjgdm);
		this.jiancjgdm = jiancjgdm;
	}

	public void setJyhydm(String jyhydm) {
		status.put("JYHY_DM", jyhydm);
		this.jyhydm = jyhydm;
	}

	public void setKglxdm(String kglxdm) {
		status.put("KGLX_DM", kglxdm);
		this.kglxdm = kglxdm;
	}

	public void setLrjgdm(String lrjgdm) {
		status.put("LRJG_DM", lrjgdm);
		this.lrjgdm = lrjgdm;
	}

	public void setLrrydm(String lrrydm) {
		status.put("LRRY_DM", lrrydm);
		this.lrrydm = lrrydm;
	}

	public void setLsgxdm(String lsgxdm) {
		status.put("LSGX_DM", lsgxdm);
		this.lsgxdm = lsgxdm;
	}

	public void setNsrsbm(String nsrsbm) {
		status.put("NSRSBM", nsrsbm);
		this.nsrsbm = nsrsbm;
	}

	public void setNsrztdm(String nsrztdm) {
		status.put("NSRZT_DM", nsrztdm);
		this.nsrztdm = nsrztdm;
	}

	public void setNsrmc(String nsrmc) {
		status.put("NSR_MC", nsrmc);
		this.nsrmc = nsrmc;
	}

	public void setScjydlxdh(String scjydlxdh) {
		status.put("SCJYDLXDH", scjydlxdh);
		this.scjydlxdh = scjydlxdh;
	}

	public void setSjjydz(String sjjydz) {
		status.put("SJJYDZ", sjjydz);
		this.sjjydz = sjjydz;
	}

	public void setSjjydzyb(String sjjydzyb) {
		status.put("SJJYDZYB", sjjydzyb);
		this.sjjydzyb = sjjydzyb;
	}

	public void setSkssjgdm(String skssjgdm) {
		status.put("SKSSJG_DM", skssjgdm);
		this.skssjgdm = skssjgdm;
	}

	public void setSwdjlxdm(String swdjlxdm) {
		status.put("SWDJLX_DM", swdjlxdm);
		this.swdjlxdm = swdjlxdm;
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

	public void setWsh(String wsh) {
		status.put("WSH", wsh);
		this.wsh = wsh;
	}

	public void setXgjgdm(String xgjgdm) {
		status.put("XGJG_DM", xgjgdm);
		this.xgjgdm = xgjgdm;
	}

	public void setXgrydm(String xgrydm) {
		status.put("XGRY_DM", xgrydm);
		this.xgrydm = xgrydm;
	}

	public void setZcdlxdh(String zcdlxdh) {
		status.put("ZCDLXDH", zcdlxdh);
		this.zcdlxdh = zcdlxdh;
	}

	public void setZcdz(String zcdz) {
		status.put("ZCDZ", zcdz);
		this.zcdz = zcdz;
	}

	public void setZcdzyb(String zcdzyb) {
		status.put("ZCDZYB", zcdzyb);
		this.zcdzyb = zcdzyb;
	}

	public void setZdsyrddm(String zdsyrddm) {
		status.put("ZDSYRD_DM", zdsyrddm);
		this.zdsyrddm = zdsyrddm;
	}

	public void setZsjgdm(String zsjgdm) {
		status.put("ZSJG_DM", zsjgdm);
		this.zsjgdm = zsjgdm;
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
