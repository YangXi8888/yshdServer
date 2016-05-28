package gov.jslt.taxevent.bsfw.ssbl.ssbl005;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class Ssbl005sxfjbmxVO extends CssBaseBpoVO {

public Ssbl005sxfjbmxVO() {
super();
}

    //;
    private String dzsphxh;

    //归属日期;
    private Calendar gsrq;

    //;
    private String hjje;

    //缴款流水明细号;
    private String jklsmxhxh;

    //缴款序号;
    private String jkxh;

    //领取手续费比例;
    private String lqbl;

    //领取手续费金额;
    private String lqje;

    //录入税务人员代码;
    private String lrrydm;

    //流水号;
    private String lsh;

    //0表示入库；1表示退库;
    private String lybz;

    //凭据明细序号;
    private String mxxh;

    //手续费凭据机打号码;
    private String pzdm;

    //申报日起;
    private Calendar sfssqqsrq;

    //申报日止;
    private Calendar sfssqzzrq;

    //税款所属机关代码;
    private String skssjgdm;

    //收款国库;
    private String skzhid;

    //需要领取手续费的入库税款金额或退税对应的入库税款;
    private String skje;

    //;
    private String sphjje;

    //;
    private String splqje;

    //手续费申请文书号;
    private String sqwsh;

    //税务管理码;
    private String swglm;

    //修改人员代码;
    private String xgrydm;

    //预算分配比例代码;
    private String ysfpbldm;

    //;
    private String zspmdm;

    //征收项目代码;
    private String zsxmdm;

    //10表示未领取11不可领取20待领取21不可领取30已领取;
    private String ztdm;

    //gsrq 的String 的类型;
    private  String  str_gsrq;

    //sfssqqsrq 的String 的类型;
    private  String  str_sfssqqsrq;

    //sfssqzzrq 的String 的类型;
    private  String  str_sfssqzzrq;


 public String getDzsphxh() {
   return dzsphxh;
}

 public Calendar getGsrq() {
   return gsrq;
}

 public String getHjje() {
   return hjje;
}

 public String getJklsmxhxh() {
   return jklsmxhxh;
}

 public String getJkxh() {
   return jkxh;
}

 public String getLqbl() {
   return lqbl;
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

 public String getLybz() {
   return lybz;
}

 public String getMxxh() {
   return mxxh;
}

 public String getPzdm() {
   return pzdm;
}

 public Calendar getSfssqqsrq() {
   return sfssqqsrq;
}

 public Calendar getSfssqzzrq() {
   return sfssqzzrq;
}

 public String getSkssjgdm() {
   return skssjgdm;
}

 public String getSkzhid() {
   return skzhid;
}

 public String getSkje() {
   return skje;
}

 public String getSphjje() {
   return sphjje;
}

 public String getSplqje() {
   return splqje;
}

 public String getSqwsh() {
   return sqwsh;
}

 public String getSwglm() {
   return swglm;
}

 public String getXgrydm() {
   return xgrydm;
}

 public String getYsfpbldm() {
   return ysfpbldm;
}

 public String getZspmdm() {
   return zspmdm;
}

 public String getZsxmdm() {
   return zsxmdm;
}

 public String getZtdm() {
   return ztdm;
}


  public void  setDzsphxh(String dzsphxh) {
    status.put("DZSPH_XH", dzsphxh);
    this.dzsphxh=dzsphxh;
}

  public void  setGsrq(Calendar gsrq) {
    status.put("GS_RQ", gsrq);
    this.gsrq=gsrq;
}

  public void  setHjje(String hjje) {
	/** 数据库HJ_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=hjje&&hjje.length()>0 ) {
    	status.put("HJ_JE", new Double(hjje));
    }
    this.hjje=hjje;
}

  public void  setJklsmxhxh(String jklsmxhxh) {
	/** 数据库JKLSMXH_XH为Long类型;
	 	类型转换 String  ->Long */
    if(null!=jklsmxhxh&&jklsmxhxh.length()>0 ) {
    	status.put("JKLSMXH_XH", new Long(jklsmxhxh));
    }
    this.jklsmxhxh=jklsmxhxh;
}

  public void  setJkxh(String jkxh) {
    status.put("JK_XH", jkxh);
    this.jkxh=jkxh;
}

  public void  setLqbl(String lqbl) {
	/** 数据库LQBL为Double类型;
		类型转换 String  -> Double*/
    if(null!=lqbl&&lqbl.length()>0 ) {
    	status.put("LQBL", new Double(lqbl));
    }
    this.lqbl=lqbl;
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

  public void  setLybz(String lybz) {
    status.put("LY_BZ", lybz);
    this.lybz=lybz;
}

  public void  setMxxh(String mxxh) {
	/** 数据库MX_XH为Long类型;
	 	类型转换 String  ->Long */
    if(null!=mxxh&&mxxh.length()>0 ) {
    	status.put("MX_XH", new Long(mxxh));
    }
    this.mxxh=mxxh;
}

  public void  setPzdm(String pzdm) {
    status.put("PZ_DM", pzdm);
    this.pzdm=pzdm;
}

  public void  setSfssqqsrq(Calendar sfssqqsrq) {
    status.put("SFSSQ_QSRQ", sfssqqsrq);
    this.sfssqqsrq=sfssqqsrq;
}

  public void  setSfssqzzrq(Calendar sfssqzzrq) {
    status.put("SFSSQ_ZZRQ", sfssqzzrq);
    this.sfssqzzrq=sfssqzzrq;
}

  public void  setSkssjgdm(String skssjgdm) {
    status.put("SKSSJG_DM", skssjgdm);
    this.skssjgdm=skssjgdm;
}

  public void  setSkzhid(String skzhid) {
    status.put("SKZHID", skzhid);
    this.skzhid=skzhid;
}

  public void  setSkje(String skje) {
	/** 数据库SK_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=skje&&skje.length()>0 ) {
    	status.put("SK_JE", new Double(skje));
    }
    this.skje=skje;
}

  public void  setSphjje(String sphjje) {
	/** 数据库SPHJ_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=sphjje&&sphjje.length()>0 ) {
    	status.put("SPHJ_JE", new Double(sphjje));
    }
    this.sphjje=sphjje;
}

  public void  setSplqje(String splqje) {
	/** 数据库SPLQ_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=splqje&&splqje.length()>0 ) {
    	status.put("SPLQ_JE", new Double(splqje));
    }
    this.splqje=splqje;
}

  public void  setSqwsh(String sqwsh) {
    status.put("SQ_WSH", sqwsh);
    this.sqwsh=sqwsh;
}

  public void  setSwglm(String swglm) {
	/** 数据库SWGLM为Long类型;
	 	类型转换 String  ->Long */
    if(null!=swglm&&swglm.length()>0 ) {
    	status.put("SWGLM", new Long(swglm));
    }
    this.swglm=swglm;
}

  public void  setXgrydm(String xgrydm) {
    status.put("XGRY_DM", xgrydm);
    this.xgrydm=xgrydm;
}

  public void  setYsfpbldm(String ysfpbldm) {
    status.put("YSFPBL_DM", ysfpbldm);
    this.ysfpbldm=ysfpbldm;
}

  public void  setZspmdm(String zspmdm) {
    status.put("ZSPM_DM", zspmdm);
    this.zspmdm=zspmdm;
}

  public void  setZsxmdm(String zsxmdm) {
    status.put("ZSXM_DM", zsxmdm);
    this.zsxmdm=zsxmdm;
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
  //gsrq  日期类转换 gsrq 的String 的类型 Set方法
  public void  setStr_gsrq(String str_gsrq ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_gsrq!="")
		this.setGsrq(DateUtils.parseFormatDate(str_gsrq,indexFomat));
	else
		this.setGsrq(null);
}

  //gsrq  日期类转换 gsrq 的String 的类型 Get方法
  public String  getStr_gsrq(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.gsrq)
		return DateUtils.toDateStr(this.gsrq,indexFomat);;
	return "";
}

  //sfssqqsrq  日期类转换 sfssqqsrq 的String 的类型 Set方法
  public void  setStr_sfssqqsrq(String str_sfssqqsrq ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_sfssqqsrq!="")
		this.setSfssqqsrq(DateUtils.parseFormatDate(str_sfssqqsrq,indexFomat));
	else
		this.setSfssqqsrq(null);
}

  //sfssqqsrq  日期类转换 sfssqqsrq 的String 的类型 Get方法
  public String  getStr_sfssqqsrq(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.sfssqqsrq)
		return DateUtils.toDateStr(this.sfssqqsrq,indexFomat);;
	return "";
}

  //sfssqzzrq  日期类转换 sfssqzzrq 的String 的类型 Set方法
  public void  setStr_sfssqzzrq(String str_sfssqzzrq ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_sfssqzzrq!="")
		this.setSfssqzzrq(DateUtils.parseFormatDate(str_sfssqzzrq,indexFomat));
	else
		this.setSfssqzzrq(null);
}

  //sfssqzzrq  日期类转换 sfssqzzrq 的String 的类型 Get方法
  public String  getStr_sfssqzzrq(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.sfssqzzrq)
		return DateUtils.toDateStr(this.sfssqzzrq,indexFomat);;
	return "";
}

////////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
  private String zsxmmc;
  private String zspmmc;
  private String zhmc;
  private String fpblmc;

public String getFpblmc() {
	return fpblmc;
}

public void setFpblmc(String fpblmc) {
	this.fpblmc = fpblmc;
}

public String getZhmc() {
	return zhmc;
}

public void setZhmc(String zhmc) {
	this.zhmc = zhmc;
}

public String getZspmmc() {
	return zspmmc;
}

public void setZspmmc(String zspmmc) {
	this.zspmmc = zspmmc;
}

public String getZsxmmc() {
	return zsxmmc;
}

public void setZsxmmc(String zsxmmc) {
	this.zsxmmc = zsxmmc;
}
}

