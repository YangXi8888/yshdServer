package gov.jslt.taxevent.bsfw.ssbl.ssbl005;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class Ssbl005sxfjbzbVO extends CssBaseBpoVO {

public Ssbl005sxfjbzbVO() {
super();
}

    //发放手续费税务管理人员代码;
    private String ffrydm;

    //发放手续费日期;
    private Calendar ffsj;

    //退税核减手续费金额合计;
    private String hjlqje;

    //暂时保留待定;
    private String hjbj;

    //领取手续费金额合计;
    private String lqje;

    //录入税务人员代码;
    private String lrrydm;

    //流水号;
    private String lsh;

    //;
    private String mc;

    //手续费凭据机打号码;
    private String pzdm;

    //审核税务管理人员代码;
    private String shrydm;

    //审核时间;
    private Calendar shsj;

    //申请领取手续费纳税人税务管理码;
    private String slr;

    //申请领取时间;
    private Calendar slsj;

    //上期核减领取金额（作废申请后回退手续费核减信息中的上期剩余核减金额使用）;
    private String sqsyhjje;

    //手续费申请文书号;
    private String sqwsh;

    //修改人员代码;
    private String xgrydm;

    //;
    private String yhdm;

    //;
    private String yhzh;

    //;
    private String zphm;

    //10表示未领取11不可领取20待领取21不可领取30已领取;
    private String ztdm;

    //ffsj 的String 的类型;
    private  String  str_ffsj;

    //shsj 的String 的类型;
    private  String  str_shsj;

    //slsj 的String 的类型;
    private  String  str_slsj;


 public String getFfrydm() {
   return ffrydm;
}

 public Calendar getFfsj() {
   return ffsj;
}

 public String getHjlqje() {
   return hjlqje;
}

 public String getHjbj() {
   return hjbj;
}

 public String getLqje() {
   return lqje;
}

 public String getLrrydm() {
   return lrrydm;
}

 public String getLsh() {
   return lsh;
}

 public String getMc() {
   return mc;
}

 public String getPzdm() {
   return pzdm;
}

 public String getShrydm() {
   return shrydm;
}

 public Calendar getShsj() {
   return shsj;
}

 public String getSlr() {
   return slr;
}

 public Calendar getSlsj() {
   return slsj;
}

 public String getSqsyhjje() {
   return sqsyhjje;
}

 public String getSqwsh() {
   return sqwsh;
}

 public String getXgrydm() {
   return xgrydm;
}

 public String getYhdm() {
   return yhdm;
}

 public String getYhzh() {
   return yhzh;
}

 public String getZphm() {
   return zphm;
}

 public String getZtdm() {
   return ztdm;
}


  public void  setFfrydm(String ffrydm) {
    status.put("FFRY_DM", ffrydm);
    this.ffrydm=ffrydm;
}

  public void  setFfsj(Calendar ffsj) {
    status.put("FF_SJ", ffsj);
    this.ffsj=ffsj;
}

  public void  setHjlqje(String hjlqje) {
	/** 数据库HJLQ_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=hjlqje&&hjlqje.length()>0 ) {
    	status.put("HJLQ_JE", new Double(hjlqje));
    }
    this.hjlqje=hjlqje;
}

  public void  setHjbj(String hjbj) {
    status.put("HJ_BJ", hjbj);
    this.hjbj=hjbj;
}

  public void  setLqje(String lqje) {
	/** 数据库LQ_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=lqje&&lqje.length()>0 ) {
    	status.put("LQ_JE", new Double(lqje));
    }
    this.lqje=lqje;
}

  public void  setLrrydm(String lrrydm) {
    status.put("LRRY_DM", lrrydm);
    this.lrrydm=lrrydm;
}

  public void  setLsh(String lsh) {
    status.put("LSH", lsh);
    this.lsh=lsh;
}

  public void  setMc(String mc) {
    status.put("MC", mc);
    this.mc=mc;
}

  public void  setPzdm(String pzdm) {
    status.put("PZ_DM", pzdm);
    this.pzdm=pzdm;
}

  public void  setShrydm(String shrydm) {
    status.put("SHRY_DM", shrydm);
    this.shrydm=shrydm;
}

  public void  setShsj(Calendar shsj) {
    status.put("SH_SJ", shsj);
    this.shsj=shsj;
}

  public void  setSlr(String slr) {
	/** 数据库SLR为Long类型;
	 	类型转换 String  ->Long */
    if(null!=slr&&slr.length()>0 ) {
    	status.put("SLR", new Long(slr));
    }
    this.slr=slr;
}

  public void  setSlsj(Calendar slsj) {
    status.put("SL_SJ", slsj);
    this.slsj=slsj;
}

  public void  setSqsyhjje(String sqsyhjje) {
	/** 数据库SQSYHJ_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=sqsyhjje&&sqsyhjje.length()>0 ) {
    	status.put("SQSYHJ_JE", new Double(sqsyhjje));
    }
    this.sqsyhjje=sqsyhjje;
}

  public void  setSqwsh(String sqwsh) {
    status.put("SQ_WSH", sqwsh);
    this.sqwsh=sqwsh;
}

  public void  setXgrydm(String xgrydm) {
    status.put("XGRY_DM", xgrydm);
    this.xgrydm=xgrydm;
}

  public void  setYhdm(String yhdm) {
    status.put("YH_DM", yhdm);
    this.yhdm=yhdm;
}

  public void  setYhzh(String yhzh) {
    status.put("YH_ZH", yhzh);
    this.yhzh=yhzh;
}

  public void  setZphm(String zphm) {
    status.put("ZP_HM", zphm);
    this.zphm=zphm;
}

  public void  setZtdm(String ztdm) {
    status.put("ZT_DM", ztdm);
    this.ztdm=ztdm;
}

////////////////////////////////////////以下为【Calendar的字段转换为String的部分】/////////////////////////////////////////////// 
/*
	yyyy-MM-dd HH:mm:ss 第0种
	yyyy/MM/dd HH:mm:ss	第1种
	yyyy年MM月dd日HH时mm分ss秒 第2种
	yyyy-MM-dd 第3种
	yyyy/MM/dd 第4种
	y-MM-dd 第5种
	yy/MM/dd 第6种
	yyyy年MM月dd日 第7种
	HH:mm:ss 第8种
	yyyyMMddHHmmss 第9种
	yyyyMMdd 第10种
	yyyy.MM.dd 第11种
	yy.MM.dd 第12种
*/
  //ffsj  日期类转换 ffsj 的String 的类型 Set方法
  public void  setStr_ffsj(String str_ffsj ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_ffsj!="")
		this.setFfsj(DateUtils.parseFormatDate(str_ffsj,indexFomat));
	else
		this.setFfsj(null);
}

  //ffsj  日期类转换 ffsj 的String 的类型 Get方法
  public String  getStr_ffsj(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.ffsj)
		return DateUtils.toDateStr(this.ffsj,indexFomat);;
	return "";
}

  //shsj  日期类转换 shsj 的String 的类型 Set方法
  public void  setStr_shsj(String str_shsj ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_shsj!="")
		this.setShsj(DateUtils.parseFormatDate(str_shsj,indexFomat));
	else
		this.setShsj(null);
}

  //shsj  日期类转换 shsj 的String 的类型 Get方法
  public String  getStr_shsj(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.shsj)
		return DateUtils.toDateStr(this.shsj,indexFomat);;
	return "";
}

  //slsj  日期类转换 slsj 的String 的类型 Set方法
  public void  setStr_slsj(String str_slsj ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_slsj!="")
		this.setSlsj(DateUtils.parseFormatDate(str_slsj,indexFomat));
	else
		this.setSlsj(null);
}

  //slsj  日期类转换 slsj 的String 的类型 Get方法
  public String  getStr_slsj(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.slsj)
		return DateUtils.toDateStr(this.slsj,indexFomat);;
	return "";
}

////////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
  private String rksjq;
  private String rksjz;
  private String swglm;
  private String nsrmc;
  private String swjgdm;
public String getSwjgdm() {
	return swjgdm;
}

public void setSwjgdm(String swjgdm) {
	this.swjgdm = swjgdm;
}

public String getRksjq() {
	return rksjq;
}

public void setRksjq(String rksjq) {
	this.rksjq = rksjq;
}

public String getRksjz() {
	return rksjz;
}

public void setRksjz(String rksjz) {
	this.rksjz = rksjz;
}

public String getSwglm() {
	return swglm;
}

public void setSwglm(String swglm) {
	this.swglm = swglm;
}

public String getNsrmc() {
	return nsrmc;
}

public void setNsrmc(String nsrmc) {
	this.nsrmc = nsrmc;
}
}

