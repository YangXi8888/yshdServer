package gov.jslt.taxevent.bsfw.sbns.sbns001;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class WBgs12wsbbVO extends CssBaseBpoVO {

public WBgs12wsbbVO() {
super();
}

    //应补退税额;
    private String bqybtseje;

    //抵扣税额;
    private String dkeje;

    //减免税额;
    private String jmseje;

    //经营单位纳税人名称;
    private String jydwnsrmc;

    //年所得额合计;
    private String nsdehjje;

    //境内年所得额;
    private String nsdejnje;

    //境外年所得额;
    private String nsdejwje;

    //纳税人手机号;
    private String nsrdh;

    //纳税人姓名;
    private String nsrmc;

    //凭证序号;
    private String pzxh;

    //迁移状态;
    private String qyzt;

    //申报表序号;
    private String sbbxh;

    //申报明细序号;
    private String sbmxxh;

    //申报期限;
    private Calendar sbqx;

    //税款所属期起;
    private Calendar sfssqqsrq;

    //税款所属期止;
    private Calendar sfssqzzrq;

    //纳税人身份证照号码;
    private String sfzmhm;

    //纳税人身份证照类别;
    private String sfzmlxdm;

    //审核标记;
    private String shbj;

    //纳税个人税务管理码;
    private String swglm;

    //经营单位纳税人识别号;
    private String swglmjydm;

    //应纳税额;
    private String yingnseje;

    //应纳税所得额;
    private String yingnssdeje;

    //已缴（扣）税额;
    private String yinseje;

    //征收品目代码;
    private String zspmdm;

    //征收项目代码;
    private String zsxmdm;

    //sbqx 的String 的类型;
    private  String  str_sbqx;

    //sfssqqsrq 的String 的类型;
    private  String  str_sfssqqsrq;

    //sfssqzzrq 的String 的类型;
    private  String  str_sfssqzzrq;


 public String getBqybtseje() {
   return bqybtseje;
}

 public String getDkeje() {
   return dkeje;
}

 public String getJmseje() {
   return jmseje;
}

 public String getJydwnsrmc() {
   return jydwnsrmc;
}

 public String getNsdehjje() {
   return nsdehjje;
}

 public String getNsdejnje() {
   return nsdejnje;
}

 public String getNsdejwje() {
   return nsdejwje;
}

 public String getNsrdh() {
   return nsrdh;
}

 public String getNsrmc() {
   return nsrmc;
}

 public String getPzxh() {
   return pzxh;
}

 public String getQyzt() {
   return qyzt;
}

 public String getSbbxh() {
   return sbbxh;
}

 public String getSbmxxh() {
   return sbmxxh;
}

 public Calendar getSbqx() {
   return sbqx;
}

 public Calendar getSfssqqsrq() {
   return sfssqqsrq;
}

 public Calendar getSfssqzzrq() {
   return sfssqzzrq;
}

 public String getSfzmhm() {
   return sfzmhm;
}

 public String getSfzmlxdm() {
   return sfzmlxdm;
}

 public String getShbj() {
   return shbj;
}

 public String getSwglm() {
   return swglm;
}

 public String getSwglmjydm() {
   return swglmjydm;
}

 public String getYingnseje() {
   return yingnseje;
}

 public String getYingnssdeje() {
   return yingnssdeje;
}

 public String getYinseje() {
   return yinseje;
}

 public String getZspmdm() {
   return zspmdm;
}

 public String getZsxmdm() {
   return zsxmdm;
}


  public void  setBqybtseje(String bqybtseje) {
	/** 数据库BQYBTSE_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=bqybtseje&&bqybtseje.length()>0 ) {
    	status.put("BQYBTSE_JE", new Double(bqybtseje));
    }
    this.bqybtseje=bqybtseje;
}

  public void  setDkeje(String dkeje) {
	/** 数据库DKE_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=dkeje&&dkeje.length()>0 ) {
    	status.put("DKE_JE", new Double(dkeje));
    }
    this.dkeje=dkeje;
}

  public void  setJmseje(String jmseje) {
	/** 数据库JMSE_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=jmseje&&jmseje.length()>0 ) {
    	status.put("JMSE_JE", new Double(jmseje));
    }
    this.jmseje=jmseje;
}

  public void  setJydwnsrmc(String jydwnsrmc) {
    status.put("JYDWNSR_MC", jydwnsrmc);
    this.jydwnsrmc=jydwnsrmc;
}

  public void  setNsdehjje(String nsdehjje) {
	/** 数据库NSDEHJ_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=nsdehjje&&nsdehjje.length()>0 ) {
    	status.put("NSDEHJ_JE", new Double(nsdehjje));
    }
    this.nsdehjje=nsdehjje;
}

  public void  setNsdejnje(String nsdejnje) {
	/** 数据库NSDEJN_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=nsdejnje&&nsdejnje.length()>0 ) {
    	status.put("NSDEJN_JE", new Double(nsdejnje));
    }
    this.nsdejnje=nsdejnje;
}

  public void  setNsdejwje(String nsdejwje) {
	/** 数据库NSDEJW_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=nsdejwje&&nsdejwje.length()>0 ) {
    	status.put("NSDEJW_JE", new Double(nsdejwje));
    }
    this.nsdejwje=nsdejwje;
}

  public void  setNsrdh(String nsrdh) {
    status.put("NSR_DH", nsrdh);
    this.nsrdh=nsrdh;
}

  public void  setNsrmc(String nsrmc) {
    status.put("NSR_MC", nsrmc);
    this.nsrmc=nsrmc;
}

  public void  setPzxh(String pzxh) {
    status.put("PZ_XH", pzxh);
    this.pzxh=pzxh;
}

  public void  setQyzt(String qyzt) {
    status.put("QYZT", qyzt);
    this.qyzt=qyzt;
}

  public void  setSbbxh(String sbbxh) {
    status.put("SBB_XH", sbbxh);
    this.sbbxh=sbbxh;
}

  public void  setSbmxxh(String sbmxxh) {
	/** 数据库SBMX_XH为Long类型;
	 	类型转换 String  ->Long */
    if(null!=sbmxxh&&sbmxxh.length()>0 ) {
    	status.put("SBMX_XH", new Long(sbmxxh));
    }
    this.sbmxxh=sbmxxh;
}

  public void  setSbqx(Calendar sbqx) {
    status.put("SB_QX", sbqx);
    this.sbqx=sbqx;
}

  public void  setSfssqqsrq(Calendar sfssqqsrq) {
    status.put("SFSSQ_QSRQ", sfssqqsrq);
    this.sfssqqsrq=sfssqqsrq;
}

  public void  setSfssqzzrq(Calendar sfssqzzrq) {
    status.put("SFSSQ_ZZRQ", sfssqzzrq);
    this.sfssqzzrq=sfssqzzrq;
}

  public void  setSfzmhm(String sfzmhm) {
    status.put("SFZMHM", sfzmhm);
    this.sfzmhm=sfzmhm;
}

  public void  setSfzmlxdm(String sfzmlxdm) {
    status.put("SFZMLX_DM", sfzmlxdm);
    this.sfzmlxdm=sfzmlxdm;
}

  public void  setShbj(String shbj) {
    status.put("SH_BJ", shbj);
    this.shbj=shbj;
}

  public void  setSwglm(String swglm) {
	/** 数据库SWGLM为Long类型;
	 	类型转换 String  ->Long */
    if(null!=swglm&&swglm.length()>0 ) {
    	status.put("SWGLM", new Long(swglm));
    }
    this.swglm=swglm;
}

  public void  setSwglmjydm(String swglmjydm) {
	/** 数据库SWGLM_JYDM为Long类型;
	 	类型转换 String  ->Long */
    if(null!=swglmjydm&&swglmjydm.length()>0 ) {
    	status.put("SWGLM_JYDM", new Long(swglmjydm));
    }
    this.swglmjydm=swglmjydm;
}

  public void  setYingnseje(String yingnseje) {
	/** 数据库YINGNSE_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=yingnseje&&yingnseje.length()>0 ) {
    	status.put("YINGNSE_JE", new Double(yingnseje));
    }
    this.yingnseje=yingnseje;
}

  public void  setYingnssdeje(String yingnssdeje) {
	/** 数据库YINGNSSDE_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=yingnssdeje&&yingnssdeje.length()>0 ) {
    	status.put("YINGNSSDE_JE", new Double(yingnssdeje));
    }
    this.yingnssdeje=yingnssdeje;
}

  public void  setYinseje(String yinseje) {
	/** 数据库YINSE_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=yinseje&&yinseje.length()>0 ) {
    	status.put("YINSE_JE", new Double(yinseje));
    }
    this.yinseje=yinseje;
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
  //sbqx  日期类转换 sbqx 的String 的类型 Set方法
  public void  setStr_sbqx(String str_sbqx ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_sbqx!="")
		this.setSbqx(DateUtils.parseFormatDate(str_sbqx,indexFomat));
	else
		this.setSbqx(null);
}

  //sbqx  日期类转换 sbqx 的String 的类型 Get方法
  public String  getStr_sbqx(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.sbqx)
		return DateUtils.toDateStr(this.sbqx,indexFomat);;
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
}

