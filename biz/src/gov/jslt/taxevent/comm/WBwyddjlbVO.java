package gov.jslt.taxevent.comm;

import com.ctp.core.bpo.CssBaseBpoVO;

public class WBwyddjlbVO extends CssBaseBpoVO {

	public WBwyddjlbVO() {
		super();
	}

	// 订单号;
	private String ddh;

	// 交易时间;
	private String jysj;

	// 凭证序号;
	private String pzxh;

	public String getDdh() {
		return ddh;
	}

	public String getJysj() {
		return jysj;
	}

	public String getPzxh() {
		return pzxh;
	}

	public void setDdh(String ddh) {
		status.put("DDH", ddh);
		this.ddh = ddh;
	}

	public void setJysj(String jysj) {
		status.put("JYSJ", jysj);
		this.jysj = jysj;
	}

	public void setPzxh(String pzxh) {
		status.put("PZ_XH", pzxh);
		this.pzxh = pzxh;
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
