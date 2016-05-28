package gov.jslt.taxevent.bsfw.ssbl.ssbl002;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class Ssbl002jmssqmxVO extends CssBaseBpoVO {

public Ssbl002jmssqmxVO() {
super();
}

    //管理机关代码;
    private String gljgdm;

    //减免方式;
    private String jmfs;

    //减免类型代码;
    private String jmlxdm;

    //减免项目代码;
    private String jmxmdm;

    //减免余额（作废）;
    private String jmyeje;

    //减免依据（作废）;
    private String jmyjbz;

    //减免指标;
    private String jmzb;

    //计税依据（作废）;
    private String jsyj;

    //流水号;
    private String lsh;

    //明细序号;
    private String mxxh;

    //纳税期限日代码;
    private String nsqxrdm;

    //批准减免幅度（作废）;
    private String pzjmfd;

    //批准减免期限始（作废）;
    private Calendar pzjmqxsqsrq;

    //批准减免期限止（作废）;
    private Calendar pzjmqxzzzrq;

    //批准减免上限（作废）;
    private String pzjmsx;

    //税费所属期起（作废）;
    private Calendar sfssqqsrq;

    //税费所属期止（作废）;
    private Calendar sfssqzzrq;

    //税率（作废）;
    private String sl;

    //申请减免幅度（作废）;
    private String sqjmfd;

    //申请减免期限始;
    private Calendar sqjmqxqsrq;

    //申请减免期限止;
    private Calendar sqjmqxzzrq;

    //申请减免金额（作废）;
    private String sqjmje;

    //政策依据;
    private String zcyj;
    
    //政策依据细目
    private String zcyjxm;

    //政策依据文号;
    private String zcyjwh;

    //征收品目代码（作废）;
    private String zspmdm;

    //征收项目代码;
    private String zsxmdm;

    //pzjmqxsqsrq 的String 的类型;
    private  String  str_pzjmqxsqsrq;

    //pzjmqxzzzrq 的String 的类型;
    private  String  str_pzjmqxzzzrq;

    //sfssqqsrq 的String 的类型;
    private  String  str_sfssqqsrq;

    //sfssqzzrq 的String 的类型;
    private  String  str_sfssqzzrq;

    //sqjmqxqsrq 的String 的类型;
    private  String  str_sqjmqxqsrq;

    //sqjmqxzzrq 的String 的类型;
    private  String  str_sqjmqxzzrq;
    
    private  String zjyxm;
    private  String zjlx;
    private  String zjhm;
 public String getZjyxm() {
		return zjyxm;
	}

	public void setZjyxm(String zjyxm) {
		 status.put("zjyxm", zjyxm);
		this.zjyxm = zjyxm;
	}

	public String getZjlx() {
		return zjlx;
	}

	public void setZjlx(String zjlx) {
		 status.put("zjlx", zjlx);
		this.zjlx = zjlx;
	}

	public String getZjhm() {
		return zjhm;
	}

	public void setZjhm(String zjhm) {
		 status.put("zjhm", zjhm);
		this.zjhm = zjhm;
	}

public String getGljgdm() {
   return gljgdm;
}

 public String getJmfs() {
   return jmfs;
}

 public String getJmlxdm() {
   return jmlxdm;
}

 public String getJmxmdm() {
   return jmxmdm;
}

 public String getJmyeje() {
   return jmyeje;
}

 public String getJmyjbz() {
   return jmyjbz;
}

 public String getJmzb() {
   return jmzb;
}

 public String getJsyj() {
   return jsyj;
}

 public String getLsh() {
   return lsh;
}

 public String getMxxh() {
   return mxxh;
}

 public String getNsqxrdm() {
   return nsqxrdm;
}

 public String getPzjmfd() {
   return pzjmfd;
}

 public Calendar getPzjmqxsqsrq() {
   return pzjmqxsqsrq;
}

 public Calendar getPzjmqxzzzrq() {
   return pzjmqxzzzrq;
}

 public String getPzjmsx() {
   return pzjmsx;
}

 public Calendar getSfssqqsrq() {
   return sfssqqsrq;
}

 public Calendar getSfssqzzrq() {
   return sfssqzzrq;
}

 public String getSl() {
   return sl;
}

 public String getSqjmfd() {
   return sqjmfd;
}

 public Calendar getSqjmqxqsrq() {
   return sqjmqxqsrq;
}

 public Calendar getSqjmqxzzrq() {
   return sqjmqxzzrq;
}

 public String getSqjmje() {
   return sqjmje;
}

 public String getZcyj() {
   return zcyj;
}
 
 public String getZcyjxm(){
	 return zcyjxm;
 }

 public String getZcyjwh() {
   return zcyjwh;
}

 public String getZspmdm() {
   return zspmdm;
}

 public String getZsxmdm() {
   return zsxmdm;
}


  public void  setGljgdm(String gljgdm) {
    status.put("GLJG_DM", gljgdm);
    this.gljgdm=gljgdm;
}

  public void  setJmfs(String jmfs) {
    status.put("JMFS", jmfs);
    this.jmfs=jmfs;
}

  public void  setJmlxdm(String jmlxdm) {
    status.put("JMLX_DM", jmlxdm);
    this.jmlxdm=jmlxdm;
}

  public void  setJmxmdm(String jmxmdm) {
    status.put("JMXM_DM", jmxmdm);
    this.jmxmdm=jmxmdm;
}

  public void  setJmyeje(String jmyeje) {
	/** 数据库JMYE_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=jmyeje&&jmyeje.length()>0 ) {
    	status.put("JMYE_JE", new Double(jmyeje));
    }
    this.jmyeje=jmyeje;
}

  public void  setJmyjbz(String jmyjbz) {
    status.put("JMYJ_BZ", jmyjbz);
    this.jmyjbz=jmyjbz;
}

  public void  setJmzb(String jmzb) {
    status.put("JMZB", jmzb);
    this.jmzb=jmzb;
}

  public void  setJsyj(String jsyj) {
	/** 数据库JS_YJ为Double类型;
		类型转换 String  -> Double*/
    if(null!=jsyj&&jsyj.length()>0 ) {
    	status.put("JS_YJ", new Double(jsyj));
    }
    this.jsyj=jsyj;
}

  public void  setLsh(String lsh) {
    status.put("LSH", lsh);
    this.lsh=lsh;
}

  public void  setMxxh(String mxxh) {
	/** 数据库MX_XH为Long类型;
	 	类型转换 String  ->Long */
    if(null!=mxxh&&mxxh.length()>0 ) {
    	status.put("MX_XH", new Long(mxxh));
    }
    this.mxxh=mxxh;
}

  public void  setNsqxrdm(String nsqxrdm) {
    status.put("NSQXR_DM", nsqxrdm);
    this.nsqxrdm=nsqxrdm;
}

  public void  setPzjmfd(String pzjmfd) {
	/** 数据库PZJMFD为Double类型;
		类型转换 String  -> Double*/
    if(null!=pzjmfd&&pzjmfd.length()>0 ) {
    	status.put("PZJMFD", new Double(pzjmfd));
    }
    this.pzjmfd=pzjmfd;
}

  public void  setPzjmqxsqsrq(Calendar pzjmqxsqsrq) {
    status.put("PZJMQXS_QSRQ", pzjmqxsqsrq);
    this.pzjmqxsqsrq=pzjmqxsqsrq;
}

  public void  setPzjmqxzzzrq(Calendar pzjmqxzzzrq) {
    status.put("PZJMQXZ_ZZRQ", pzjmqxzzzrq);
    this.pzjmqxzzzrq=pzjmqxzzzrq;
}

  public void  setPzjmsx(String pzjmsx) {
	/** 数据库PZJM_SX为Double类型;
		类型转换 String  -> Double*/
    if(null!=pzjmsx&&pzjmsx.length()>0 ) {
    	status.put("PZJM_SX", new Double(pzjmsx));
    }
    this.pzjmsx=pzjmsx;
}

  public void  setSfssqqsrq(Calendar sfssqqsrq) {
    status.put("SFSSQ_QSRQ", sfssqqsrq);
    this.sfssqqsrq=sfssqqsrq;
}

  public void  setSfssqzzrq(Calendar sfssqzzrq) {
    status.put("SFSSQ_ZZRQ", sfssqzzrq);
    this.sfssqzzrq=sfssqzzrq;
}

  public void  setSl(String sl) {
	/** 数据库SL为Double类型;
		类型转换 String  -> Double*/
    if(null!=sl&&sl.length()>0 ) {
    	status.put("SL", new Double(sl));
    }
    this.sl=sl;
}

  public void  setSqjmfd(String sqjmfd) {
	/** 数据库SQJMFD为Double类型;
		类型转换 String  -> Double*/
    if(null!=sqjmfd&&sqjmfd.length()>0 ) {
    	status.put("SQJMFD", new Double(sqjmfd));
    }
    this.sqjmfd=sqjmfd;
}

  public void  setSqjmqxqsrq(Calendar sqjmqxqsrq) {
    status.put("SQJMQX_QSRQ", sqjmqxqsrq);
    this.sqjmqxqsrq=sqjmqxqsrq;
}

  public void  setSqjmqxzzrq(Calendar sqjmqxzzrq) {
    status.put("SQJMQX_ZZRQ", sqjmqxzzrq);
    this.sqjmqxzzrq=sqjmqxzzrq;
}

  public void  setSqjmje(String sqjmje) {
	/** 数据库SQJM_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=sqjmje&&sqjmje.length()>0 ) {
    	status.put("SQJM_JE", new Double(sqjmje));
    }
    this.sqjmje=sqjmje;
}

  public void  setZcyj(String zcyj) {
    status.put("ZCYJ", zcyj);
    this.zcyj=zcyj;
}
  
  public void setZcyjxm(String zcyjxm){
	  status.put("ZCYJXM", zcyjxm);
	  this.zcyjxm = zcyjxm;
  }

  public void  setZcyjwh(String zcyjwh) {
    status.put("ZCYJWH", zcyjwh);
    this.zcyjwh=zcyjwh;
}

  public void  setZspmdm(String zspmdm) {
    status.put("ZSPM_DM", zspmdm);
    this.zspmdm=zspmdm;
}

  public void  setZsxmdm(String zsxmdm) {
    status.put("ZSXM_DM", zsxmdm);
    this.zsxmdm=zsxmdm;
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
  //pzjmqxsqsrq  日期类转换 pzjmqxsqsrq 的String 的类型 Set方法
  public void  setStr_pzjmqxsqsrq(String str_pzjmqxsqsrq ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_pzjmqxsqsrq!="")
		this.setPzjmqxsqsrq(DateUtils.parseFormatDate(str_pzjmqxsqsrq,indexFomat));
	else
		this.setPzjmqxsqsrq(null);
}

  //pzjmqxsqsrq  日期类转换 pzjmqxsqsrq 的String 的类型 Get方法
  public String  getStr_pzjmqxsqsrq(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.pzjmqxsqsrq)
		return DateUtils.toDateStr(this.pzjmqxsqsrq,indexFomat);;
	return "";
}

  //pzjmqxzzzrq  日期类转换 pzjmqxzzzrq 的String 的类型 Set方法
  public void  setStr_pzjmqxzzzrq(String str_pzjmqxzzzrq ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_pzjmqxzzzrq!="")
		this.setPzjmqxzzzrq(DateUtils.parseFormatDate(str_pzjmqxzzzrq,indexFomat));
	else
		this.setPzjmqxzzzrq(null);
}

  //pzjmqxzzzrq  日期类转换 pzjmqxzzzrq 的String 的类型 Get方法
  public String  getStr_pzjmqxzzzrq(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.pzjmqxzzzrq)
		return DateUtils.toDateStr(this.pzjmqxzzzrq,indexFomat);;
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

  //sqjmqxqsrq  日期类转换 sqjmqxqsrq 的String 的类型 Set方法
  public void  setStr_sqjmqxqsrq(String str_sqjmqxqsrq ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_sqjmqxqsrq!="")
		this.setSqjmqxqsrq(DateUtils.parseFormatDate(str_sqjmqxqsrq,indexFomat));
	else
		this.setSqjmqxqsrq(null);
}

  //sqjmqxqsrq  日期类转换 sqjmqxqsrq 的String 的类型 Get方法
  public String  getStr_sqjmqxqsrq(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.sqjmqxqsrq)
		return DateUtils.toDateStr(this.sqjmqxqsrq,indexFomat);;
	return "";
}

  //sqjmqxzzrq  日期类转换 sqjmqxzzrq 的String 的类型 Set方法
  public void  setStr_sqjmqxzzrq(String str_sqjmqxzzrq ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_sqjmqxzzrq!="")
		this.setSqjmqxzzrq(DateUtils.parseFormatDate(str_sqjmqxzzrq,indexFomat));
	else
		this.setSqjmqxzzrq(null);
}

  //sqjmqxzzrq  日期类转换 sqjmqxzzrq 的String 的类型 Get方法
  public String  getStr_sqjmqxzzrq(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.sqjmqxzzrq)
		return DateUtils.toDateStr(this.sqjmqxzzrq,indexFomat);;
	return "";
}

////////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
}

