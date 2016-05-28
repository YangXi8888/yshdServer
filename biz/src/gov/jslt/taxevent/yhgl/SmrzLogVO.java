package gov.jslt.taxevent.yhgl;

import com.ctp.core.bpo.CssBaseBpoVO;

public class SmrzLogVO extends CssBaseBpoVO {

	public SmrzLogVO() {
		super();
	}

	// 返回报文;
	private String fhbw;

	// 认证结果0表示成功1表示失败;
	private String jgbz;

	// 认证报文;
	private String rzbw;

	// 认证方式;
	private String rzfsdm;

	// 认证日志主键;
	private String rzloguuid;

	// 用户UUID;
	private String uuid;

	public String getFhbw() {
		return fhbw;
	}

	public String getJgbz() {
		return jgbz;
	}

	public String getRzbw() {
		return rzbw;
	}

	public String getRzfsdm() {
		return rzfsdm;
	}

	public String getRzloguuid() {
		return rzloguuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setFhbw(String fhbw) {
		status.put("FHBW", fhbw);
		this.fhbw = fhbw;
	}

	public void setJgbz(String jgbz) {
		status.put("JGBZ", jgbz);
		this.jgbz = jgbz;
	}

	public void setRzbw(String rzbw) {
		status.put("RZBW", rzbw);
		this.rzbw = rzbw;
	}

	public void setRzfsdm(String rzfsdm) {
		status.put("RZFS_DM", rzfsdm);
		this.rzfsdm = rzfsdm;
	}

	public void setRzloguuid(String rzloguuid) {
		status.put("RZLOG_UUID", rzloguuid);
		this.rzloguuid = rzloguuid;
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
