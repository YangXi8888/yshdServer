package gov.jslt.taxevent.comm;

import com.ctp.core.bpo.CssBaseBpoVO;

public class WBwykkZscsVO extends CssBaseBpoVO {

	public WBwykkZscsVO() {
		super();
	}

	// 服务器名;
	private String localhostname;

	// 主键（无意义）;
	private String uuid;

	public String getLocalhostname() {
		return localhostname;
	}

	public String getUuid() {
		return uuid;
	}

	public void setLocalhostname(String localhostname) {
		status.put("LOCALHOSTNAME", localhostname);
		this.localhostname = localhostname;
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
