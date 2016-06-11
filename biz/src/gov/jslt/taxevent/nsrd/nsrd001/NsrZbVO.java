package gov.jslt.taxevent.nsrd.nsrd001;

import com.ctp.core.bpo.CssBaseBpoVO;

public class NsrZbVO extends CssBaseBpoVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NsrZbVO() {
		super();
	}

	// 纳税人识别码;
	private String nsrsbm;

	// 纳税人名称;
	private String nsrmc;

	// 签约银行代码;
	private String qyyhdm;

	// 税务管理码;
	private String swglm;

	// 主表UUID;
	private String zbuuid;

	public String getNsrsbm() {
		return nsrsbm;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public String getQyyhdm() {
		return qyyhdm;
	}

	public String getSwglm() {
		return swglm;
	}

	public String getZbuuid() {
		return zbuuid;
	}

	public void setNsrsbm(String nsrsbm) {
		status.put("NSRSBM", nsrsbm);
		this.nsrsbm = nsrsbm;
	}

	public void setNsrmc(String nsrmc) {
		status.put("NSR_MC", nsrmc);
		this.nsrmc = nsrmc;
	}

	public void setQyyhdm(String qyyhdm) {
		status.put("QYYH_DM", qyyhdm);
		this.qyyhdm = qyyhdm;
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

	public void setZbuuid(String zbuuid) {
		status.put("ZB_UUID", zbuuid);
		this.zbuuid = zbuuid;
	}

	// //////////////////////////////////////以下为【Calendar的字段转换为String的部分】///////////////////////////////////////////////
	/*
	 * yyyy-MM-dd HH:mm:ss 第0种 yyyy/MM/dd HH:mm:ss 第1种 yyyy年MM月dd日HH时mm分ss秒 第2种 yyyy-MM-dd 第3种 yyyy/MM/dd 第4种 y-MM-dd
	 * 第5种 yy/MM/dd 第6种 yyyy年MM月dd日 第7种 HH:mm:ss 第8种 yyyyMMddHHmmss 第9种 yyyyMMdd 第10种 yyyy.MM.dd 第11种 yy.MM.dd 第12种
	 */
	// //////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
}
