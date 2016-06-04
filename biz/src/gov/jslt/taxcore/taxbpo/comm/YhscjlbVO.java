package gov.jslt.taxcore.taxbpo.comm;

import com.ctp.core.bpo.BLOBObject;
import com.ctp.core.bpo.CssBaseVOWithLOB;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class YhscjlbVO extends CssBaseVOWithLOB {

	public YhscjlbVO() {
		super();
	}

	// 主键无意义;
	private String scjlid;

	// 用户ID;
	private String uuid;

	// 文件大小;
	private String wjdx;

	// 文件名;
	private String wjm;

	// 文件内容;
	private BLOBObject wjnr;

	public String getScjlid() {
		return scjlid;
	}

	public String getUuid() {
		return uuid;
	}

	public String getWjdx() {
		return wjdx;
	}

	public String getWjm() {
		return wjm;
	}

	public BLOBObject getWjnr() {
		return wjnr;
	}

	public void setScjlid(String scjlid) {
		status.put("SCJL_ID", scjlid);
		this.scjlid = scjlid;
	}

	public void setUuid(String uuid) {
		status.put("UUID", uuid);
		this.uuid = uuid;
	}

	public void setWjdx(String wjdx) {
		status.put("WJDX", wjdx);
		this.wjdx = wjdx;
	}

	public void setWjm(String wjm) {
		status.put("WJM", wjm);
		this.wjm = wjm;
	}

	public void setWjnr(BLOBObject wjnr) {
		blobBuffer.put("WJNR", wjnr);
		this.wjnr = wjnr;
	}

	// //////////////////////////////////////以下为【blob的字段转换为byte[]的部分】////////////////////////////////////////////////////
	// wjnr 的byte[]类型 wjnr 转换Blob类型 Set方法
	public void setWjnrByte(byte[] bytewjnr) {
		if (null != bytewjnr) {
			BLOBObject blobObj = new BLOBObject();
			blobObj.setContent(bytewjnr);
			this.setWjnr(blobObj);
		} else
			this.setWjnr(null);
	}

	// wjnr Blob类转换 wjnr Byte[] 的类型 Get方法
	public byte[] getWjnrByte() {
		if (null != this.wjnr)
			return wjnr.getContent();
		return null;
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
