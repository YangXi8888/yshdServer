package gov.jslt.taxevent.yhgl;

import com.ctp.core.bpo.CLOBObject;
import com.ctp.core.bpo.CssBaseVOWithLOB;

public class YhGxxxVO extends CssBaseVOWithLOB {

	public YhGxxxVO() {
		super();
	}

	// 头像图标;
	private CLOBObject icon;

	// QQ号;
	private String qq;

	// 用户ID;
	private String uuid;

	// 微博账号;
	private String wb;

	// 微信号;
	private String wx;

	// 支付宝账号;
	private String zfb;

	public CLOBObject getIcon() {
		return icon;
	}

	public String getQq() {
		return qq;
	}

	public String getUuid() {
		return uuid;
	}

	public String getWb() {
		return wb;
	}

	public String getWx() {
		return wx;
	}

	public String getZfb() {
		return zfb;
	}

	public void setIcon(CLOBObject icon) {
		clobBuffer.put("ICON", icon);
		this.icon = icon;
	}

	public void setQq(String qq) {
		status.put("QQ", qq);
		this.qq = qq;
	}

	public void setUuid(String uuid) {
		status.put("UUID", uuid);
		this.uuid = uuid;
	}

	public void setWb(String wb) {
		status.put("WB", wb);
		this.wb = wb;
	}

	public void setWx(String wx) {
		status.put("WX", wx);
		this.wx = wx;
	}

	public void setZfb(String zfb) {
		status.put("ZFB", zfb);
		this.zfb = zfb;
	}

	// //////////////////////////////////////以下为【clob的字段转换为String的部分】////////////////////////////////////////////////////
	// icon 的String类型 icon 转换Clob类型 Set方法
	public void setIconStr(String str_icon) {
		if (str_icon != "") {
			CLOBObject addclob = new CLOBObject();
			addclob.setContent(str_icon.toCharArray());
			this.setIcon(addclob);
		} else
			this.setIcon(null);
	}

	// icon Clob类转换 icon 的String 的类型 Get方法
	public String getIconStr() {
		if (null != this.icon)
			return icon.toString();
		return "";
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
