package gov.jslt.taxevent.comm;

import com.ctp.core.bpo.CssBaseBpoVO;

public class WBwykkDdxxVO extends CssBaseBpoVO {

	public WBwykkDdxxVO() {
		super();
	}

	// 收单机构代码（仅收单机构接入需要填写）;
	private String acqcode;

	// 后台回调商户URL;
	private String backendurl;

	// 编码方式;
	private String charset;

	// 折扣 单位：分;
	private String commoditydiscount;

	// 商品名称;
	private String commodityname;

	// 商品数量;
	private String commodityquantity;

	// 商品单价 单位：分;
	private String commodityunitprice;

	// 商品URL;
	private String commodityurl;

	// 用户IP;
	private String customerip;

	// 用户真实姓名;
	private String customername;

	// 订单号;
	private String ddh;

	// 订单来源（自然人01，企业02）;
	private String ddly;

	// 默认银行编号;
	private String defaultbanknumber;

	// 默认支付方式;
	private String defaultpaytype;

	// 前台回调商户URL;
	private String frontendurl;

	// 服务机关代码;
	private String fwjgdm;

	// 管理机关代码;
	private String gljgdm;

	// 核算机关代码;
	private String hsjgdm;

	// 稽查机关代码;
	private String jcjgdm;

	// 检查机关代码;
	private String jiancjgdm;

	// 商户简称;
	private String merabbr;

	// 商户类别（收单机构接入需要填写）;
	private String mercode;

	// 商户代码;
	private String merid;

	// 商户保留域;
	private String merreserved;

	// 纳税人识别码;
	private String nsrsbm;

	// 纳税人名称;
	private String nsrmc;

	// 交易金额 单位：分;
	private String orderamount;

	// 交易币种;
	private String ordercurrency;

	// 订单号（需要商户自己生成）;
	private String ordernumber;

	// 交易时间;
	private String ordertime;

	// 原始交易流水号;
	private String origqid;

	// 签名MD5;
	private String signature;

	// 税款所属机关代码;
	private String skssjgdm;

	// 税务管理码;
	private String swglm;

	// 运费 单位：分;
	private String transferfee;

	// 交易超时时间;
	private String transtimeout;

	// 交易类型;
	private String transtype;

	// 主键（无意义）;
	private String uuid;

	// 版本号;
	private String version;

	// 征收机关代码;
	private String zsjgdm;

	public String getAcqcode() {
		return acqcode;
	}

	public String getBackendurl() {
		return backendurl;
	}

	public String getCharset() {
		return charset;
	}

	public String getCommoditydiscount() {
		return commoditydiscount;
	}

	public String getCommodityname() {
		return commodityname;
	}

	public String getCommodityquantity() {
		return commodityquantity;
	}

	public String getCommodityunitprice() {
		return commodityunitprice;
	}

	public String getCommodityurl() {
		return commodityurl;
	}

	public String getCustomerip() {
		return customerip;
	}

	public String getCustomername() {
		return customername;
	}

	public String getDdh() {
		return ddh;
	}

	public String getDdly() {
		return ddly;
	}

	public String getDefaultbanknumber() {
		return defaultbanknumber;
	}

	public String getDefaultpaytype() {
		return defaultpaytype;
	}

	public String getFrontendurl() {
		return frontendurl;
	}

	public String getFwjgdm() {
		return fwjgdm;
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

	public String getMerabbr() {
		return merabbr;
	}

	public String getMercode() {
		return mercode;
	}

	public String getMerid() {
		return merid;
	}

	public String getMerreserved() {
		return merreserved;
	}

	public String getNsrsbm() {
		return nsrsbm;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public String getOrderamount() {
		return orderamount;
	}

	public String getOrdercurrency() {
		return ordercurrency;
	}

	public String getOrdernumber() {
		return ordernumber;
	}

	public String getOrdertime() {
		return ordertime;
	}

	public String getOrigqid() {
		return origqid;
	}

	public String getSignature() {
		return signature;
	}

	public String getSkssjgdm() {
		return skssjgdm;
	}

	public String getSwglm() {
		return swglm;
	}

	public String getTransferfee() {
		return transferfee;
	}

	public String getTranstimeout() {
		return transtimeout;
	}

	public String getTranstype() {
		return transtype;
	}

	public String getUuid() {
		return uuid;
	}

	public String getVersion() {
		return version;
	}

	public String getZsjgdm() {
		return zsjgdm;
	}

	public void setAcqcode(String acqcode) {
		status.put("ACQCODE", acqcode);
		this.acqcode = acqcode;
	}

	public void setBackendurl(String backendurl) {
		status.put("BACKENDURL", backendurl);
		this.backendurl = backendurl;
	}

	public void setCharset(String charset) {
		status.put("CHARSET", charset);
		this.charset = charset;
	}

	public void setCommoditydiscount(String commoditydiscount) {
		/**
		 * 数据库COMMODITYDISCOUNT为Long类型; 类型转换 String ->Long
		 */
		if (null != commoditydiscount && commoditydiscount.length() > 0) {
			status.put("COMMODITYDISCOUNT", new Long(commoditydiscount));
		}
		this.commoditydiscount = commoditydiscount;
	}

	public void setCommodityname(String commodityname) {
		status.put("COMMODITYNAME", commodityname);
		this.commodityname = commodityname;
	}

	public void setCommodityquantity(String commodityquantity) {
		/**
		 * 数据库COMMODITYQUANTITY为Long类型; 类型转换 String ->Long
		 */
		if (null != commodityquantity && commodityquantity.length() > 0) {
			status.put("COMMODITYQUANTITY", new Long(commodityquantity));
		}
		this.commodityquantity = commodityquantity;
	}

	public void setCommodityunitprice(String commodityunitprice) {
		/**
		 * 数据库COMMODITYUNITPRICE为Long类型; 类型转换 String ->Long
		 */
		if (null != commodityunitprice && commodityunitprice.length() > 0) {
			status.put("COMMODITYUNITPRICE", new Long(commodityunitprice));
		}
		this.commodityunitprice = commodityunitprice;
	}

	public void setCommodityurl(String commodityurl) {
		status.put("COMMODITYURL", commodityurl);
		this.commodityurl = commodityurl;
	}

	public void setCustomerip(String customerip) {
		status.put("CUSTOMERIP", customerip);
		this.customerip = customerip;
	}

	public void setCustomername(String customername) {
		status.put("CUSTOMERNAME", customername);
		this.customername = customername;
	}

	public void setDdh(String ddh) {
		status.put("DDH", ddh);
		this.ddh = ddh;
	}

	public void setDdly(String ddly) {
		status.put("DDLY", ddly);
		this.ddly = ddly;
	}

	public void setDefaultbanknumber(String defaultbanknumber) {
		status.put("DEFAULTBANKNUMBER", defaultbanknumber);
		this.defaultbanknumber = defaultbanknumber;
	}

	public void setDefaultpaytype(String defaultpaytype) {
		status.put("DEFAULTPAYTYPE", defaultpaytype);
		this.defaultpaytype = defaultpaytype;
	}

	public void setFrontendurl(String frontendurl) {
		status.put("FRONTENDURL", frontendurl);
		this.frontendurl = frontendurl;
	}

	public void setFwjgdm(String fwjgdm) {
		status.put("FWJG_DM", fwjgdm);
		this.fwjgdm = fwjgdm;
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

	public void setMerabbr(String merabbr) {
		status.put("MERABBR", merabbr);
		this.merabbr = merabbr;
	}

	public void setMercode(String mercode) {
		status.put("MERCODE", mercode);
		this.mercode = mercode;
	}

	public void setMerid(String merid) {
		status.put("MERID", merid);
		this.merid = merid;
	}

	public void setMerreserved(String merreserved) {
		status.put("MERRESERVED", merreserved);
		this.merreserved = merreserved;
	}

	public void setNsrsbm(String nsrsbm) {
		status.put("NSRSBM", nsrsbm);
		this.nsrsbm = nsrsbm;
	}

	public void setNsrmc(String nsrmc) {
		status.put("NSR_MC", nsrmc);
		this.nsrmc = nsrmc;
	}

	public void setOrderamount(String orderamount) {
		/**
		 * 数据库ORDERAMOUNT为Long类型; 类型转换 String ->Long
		 */
		if (null != orderamount && orderamount.length() > 0) {
			status.put("ORDERAMOUNT", new Long(orderamount));
		}
		this.orderamount = orderamount;
	}

	public void setOrdercurrency(String ordercurrency) {
		status.put("ORDERCURRENCY", ordercurrency);
		this.ordercurrency = ordercurrency;
	}

	public void setOrdernumber(String ordernumber) {
		status.put("ORDERNUMBER", ordernumber);
		this.ordernumber = ordernumber;
	}

	public void setOrdertime(String ordertime) {
		status.put("ORDERTIME", ordertime);
		this.ordertime = ordertime;
	}

	public void setOrigqid(String origqid) {
		status.put("ORIGQID", origqid);
		this.origqid = origqid;
	}

	public void setSignature(String signature) {
		status.put("SIGNATURE", signature);
		this.signature = signature;
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

	public void setTransferfee(String transferfee) {
		/**
		 * 数据库TRANSFERFEE为Long类型; 类型转换 String ->Long
		 */
		if (null != transferfee && transferfee.length() > 0) {
			status.put("TRANSFERFEE", new Long(transferfee));
		}
		this.transferfee = transferfee;
	}

	public void setTranstimeout(String transtimeout) {
		status.put("TRANSTIMEOUT", transtimeout);
		this.transtimeout = transtimeout;
	}

	public void setTranstype(String transtype) {
		status.put("TRANSTYPE", transtype);
		this.transtype = transtype;
	}

	public void setUuid(String uuid) {
		status.put("UUID", uuid);
		this.uuid = uuid;
	}

	public void setVersion(String version) {
		status.put("VERSION", version);
		this.version = version;
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
