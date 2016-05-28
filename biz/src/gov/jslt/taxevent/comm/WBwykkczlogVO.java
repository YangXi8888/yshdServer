package gov.jslt.taxevent.comm;

import com.ctp.core.bpo.CssBaseBpoVO;

public class WBwykkczlogVO extends CssBaseBpoVO {

	public WBwykkczlogVO() {
		super();
	}

	// ;
	private String ddh;

	// ;
	private String logxx;

	// ;
	private String pzxh;

	// ;
	private String uuid;

	public String getDdh() {
		return ddh;
	}

	public String getLogxx() {
		return logxx;
	}

	public String getPzxh() {
		return pzxh;
	}

	public String getUuid() {
		return uuid;
	}

	public void setDdh(String ddh) {
		status.put("DDH", ddh);
		this.ddh = ddh;
	}

	public void setLogxx(String logxx) {
		status.put("LOGXX", logxx);
		this.logxx = logxx;
	}

	public void setPzxh(String pzxh) {
		status.put("PZ_XH", pzxh);
		this.pzxh = pzxh;
	}

	public void setUuid(String uuid) {
		status.put("UUID", uuid);
		this.uuid = uuid;
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
