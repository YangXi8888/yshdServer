package gov.jslt.taxevent.bsfw.sbns.sbns003;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class WBgrsdssbabVO extends CssBaseBpoVO {

public WBgrsdssbabVO() {
super();
}

    //财产原值;
    private String ccyzje;

    //纳税服务机关代码;
    private String fwjgdm;

    //国籍（地区）;
    private String gjdqdm;

    //管理机关代码;
    private String gljgdm;

    //基本医疗保险费;
    private String hbylbxfje;

    //合计（原允许扣除的费用额）;
    private String hjje;

    //基本养老保险费;
    private String jbylbxfje;

    //减除费用（原法定费用扣除额）;
    private String jcfyje;

    //稽查机关代码;
    private String jcjgdm;

    //检查机关代码;
    private String jiancjgdm;

    //减免税额;
    private String jmseje;

    //免税所得(原免税收入额);
    private String mssdeje;

    //纳税人识别码;
    private String nsrsbm;

    //凭证序号;
    private String pzxh;

    //其他;
    private String qtje;

    //0未迁移1已迁移;
    private String qyzt;

    //任职受雇单位名称（原扣缴义务人）;
    private String rzsgdwmc;

    //申报表顺序号(SBB_XH)表示是从第几张申报表补录的，如一个纳税人填写2张营业税申报表 在通用申报表一次录入，一个凭证序号 而在补录的时候分2次补录，这个时候本字段用来填写是第几张。;
    private String sbbxh;

    //申报明细序号;
    private String sbmxxh;

    //申报期限;
    private Calendar sbqx;

    //所得期起;
    private Calendar sdqqsrq;

    //所得期止;
    private Calendar sdqzzrq;

    //身份证件号码;
    private String sfzjhm;

    //身份证件类型;
    private String sfzjlx;

    //"0"审核通过"1"一般规则不通过"2"强制规则不通过;
    private String shbj;

    //税款所属机关代码;
    private String skssjgdm;

    //税率;
    private String sl;

    //收入额;
    private String sreje;

    //速算扣除数;
    private String sskcs;

    //税务管理码;
    private String swglm;

    //失业保险费;
    private String sybxfje;

    //应补退税额;
    private String ybtsseje;

    //已缴税额;
    private String yjseje;

    //应纳税额;
    private String ynseje;

    //应纳税所得额;
    private String ynssdeje;

    //允许扣除的税费;
    private String yxkcdsfje;

    //住房公积金;
    private String zfgjjje;

    //征收机关代码;
    private String zsjgdm;

    //征收品目代码;
    private String zspmdm;

    //征收项目代码;
    private String zsxmdm;

    //纳税人姓名;
    private String zwmc;

    //01从中国境内两处或者两处以上取得工资、薪金所得 02 没有扣缴义务人  03其他情形 ;
    private String zxsbqx;

    //准予扣除的捐赠额;
    private String zykcdjzeje;

    //sbqx 的String 的类型;
    private  String  str_sbqx;

    //sdqqsrq 的String 的类型;
    private  String  str_sdqqsrq;

    //sdqzzrq 的String 的类型;
    private  String  str_sdqzzrq;


 public String getCcyzje() {
   return ccyzje;
}

 public String getFwjgdm() {
   return fwjgdm;
}

 public String getGjdqdm() {
   return gjdqdm;
}

 public String getGljgdm() {
   return gljgdm;
}

 public String getHbylbxfje() {
   return hbylbxfje;
}

 public String getHjje() {
   return hjje;
}

 public String getJbylbxfje() {
   return jbylbxfje;
}

 public String getJcfyje() {
   return jcfyje;
}

 public String getJcjgdm() {
   return jcjgdm;
}

 public String getJiancjgdm() {
   return jiancjgdm;
}

 public String getJmseje() {
   return jmseje;
}

 public String getMssdeje() {
   return mssdeje;
}

 public String getNsrsbm() {
   return nsrsbm;
}

 public String getPzxh() {
   return pzxh;
}

 public String getQtje() {
   return qtje;
}

 public String getQyzt() {
   return qyzt;
}

 public String getRzsgdwmc() {
   return rzsgdwmc;
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

 public Calendar getSdqqsrq() {
   return sdqqsrq;
}

 public Calendar getSdqzzrq() {
   return sdqzzrq;
}

 public String getSfzjhm() {
   return sfzjhm;
}

 public String getSfzjlx() {
   return sfzjlx;
}

 public String getShbj() {
   return shbj;
}

 public String getSkssjgdm() {
   return skssjgdm;
}

 public String getSl() {
   return sl;
}

 public String getSreje() {
   return sreje;
}

 public String getSskcs() {
   return sskcs;
}

 public String getSwglm() {
   return swglm;
}

 public String getSybxfje() {
   return sybxfje;
}

 public String getYbtsseje() {
   return ybtsseje;
}

 public String getYjseje() {
   return yjseje;
}

 public String getYnseje() {
   return ynseje;
}

 public String getYnssdeje() {
   return ynssdeje;
}

 public String getYxkcdsfje() {
   return yxkcdsfje;
}

 public String getZfgjjje() {
   return zfgjjje;
}

 public String getZsjgdm() {
   return zsjgdm;
}

 public String getZspmdm() {
   return zspmdm;
}

 public String getZsxmdm() {
   return zsxmdm;
}

 public String getZwmc() {
   return zwmc;
}

 public String getZxsbqx() {
   return zxsbqx;
}

 public String getZykcdjzeje() {
   return zykcdjzeje;
}


  public void  setCcyzje(String ccyzje) {
	/** 数据库CCYZ_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=ccyzje&&ccyzje.length()>0 ) {
    	status.put("CCYZ_JE", new Double(ccyzje));
    }
    this.ccyzje=ccyzje;
}

  public void  setFwjgdm(String fwjgdm) {
    status.put("FWJG_DM", fwjgdm);
    this.fwjgdm=fwjgdm;
}

  public void  setGjdqdm(String gjdqdm) {
    status.put("GJDQ_DM", gjdqdm);
    this.gjdqdm=gjdqdm;
}

  public void  setGljgdm(String gljgdm) {
    status.put("GLJG_DM", gljgdm);
    this.gljgdm=gljgdm;
}

  public void  setHbylbxfje(String hbylbxfje) {
	/** 数据库HBYLBXF_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=hbylbxfje&&hbylbxfje.length()>0 ) {
    	status.put("HBYLBXF_JE", new Double(hbylbxfje));
    }
    this.hbylbxfje=hbylbxfje;
}

  public void  setHjje(String hjje) {
	/** 数据库HJ_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=hjje&&hjje.length()>0 ) {
    	status.put("HJ_JE", new Double(hjje));
    }
    this.hjje=hjje;
}

  public void  setJbylbxfje(String jbylbxfje) {
	/** 数据库JBYLBXF_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=jbylbxfje&&jbylbxfje.length()>0 ) {
    	status.put("JBYLBXF_JE", new Double(jbylbxfje));
    }
    this.jbylbxfje=jbylbxfje;
}

  public void  setJcfyje(String jcfyje) {
	/** 数据库JCFY_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=jcfyje&&jcfyje.length()>0 ) {
    	status.put("JCFY_JE", new Double(jcfyje));
    }
    this.jcfyje=jcfyje;
}

  public void  setJcjgdm(String jcjgdm) {
    status.put("JCJG_DM", jcjgdm);
    this.jcjgdm=jcjgdm;
}

  public void  setJiancjgdm(String jiancjgdm) {
    status.put("JIANCJG_DM", jiancjgdm);
    this.jiancjgdm=jiancjgdm;
}

  public void  setJmseje(String jmseje) {
	/** 数据库JMSE_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=jmseje&&jmseje.length()>0 ) {
    	status.put("JMSE_JE", new Double(jmseje));
    }
    this.jmseje=jmseje;
}

  public void  setMssdeje(String mssdeje) {
	/** 数据库MSSDE_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=mssdeje&&mssdeje.length()>0 ) {
    	status.put("MSSDE_JE", new Double(mssdeje));
    }
    this.mssdeje=mssdeje;
}

  public void  setNsrsbm(String nsrsbm) {
    status.put("NSRSBM", nsrsbm);
    this.nsrsbm=nsrsbm;
}

  public void  setPzxh(String pzxh) {
    status.put("PZ_XH", pzxh);
    this.pzxh=pzxh;
}

  public void  setQtje(String qtje) {
	/** 数据库QT_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=qtje&&qtje.length()>0 ) {
    	status.put("QT_JE", new Double(qtje));
    }
    this.qtje=qtje;
}

  public void  setQyzt(String qyzt) {
    status.put("QYZT", qyzt);
    this.qyzt=qyzt;
}

  public void  setRzsgdwmc(String rzsgdwmc) {
    status.put("RZSGDW_MC", rzsgdwmc);
    this.rzsgdwmc=rzsgdwmc;
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

  public void  setSdqqsrq(Calendar sdqqsrq) {
    status.put("SDQ_QSRQ", sdqqsrq);
    this.sdqqsrq=sdqqsrq;
}

  public void  setSdqzzrq(Calendar sdqzzrq) {
    status.put("SDQ_ZZRQ", sdqzzrq);
    this.sdqzzrq=sdqzzrq;
}

  public void  setSfzjhm(String sfzjhm) {
    status.put("SFZJ_HM", sfzjhm);
    this.sfzjhm=sfzjhm;
}

  public void  setSfzjlx(String sfzjlx) {
    status.put("SFZJ_LX", sfzjlx);
    this.sfzjlx=sfzjlx;
}

  public void  setShbj(String shbj) {
    status.put("SH_BJ", shbj);
    this.shbj=shbj;
}

  public void  setSkssjgdm(String skssjgdm) {
    status.put("SKSSJG_DM", skssjgdm);
    this.skssjgdm=skssjgdm;
}

  public void  setSl(String sl) {
	/** 数据库SL为Double类型;
		类型转换 String  -> Double*/
    if(null!=sl&&sl.length()>0 ) {
    	status.put("SL", new Double(sl));
    }
    this.sl=sl;
}

  public void  setSreje(String sreje) {
	/** 数据库SRE_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=sreje&&sreje.length()>0 ) {
    	status.put("SRE_JE", new Double(sreje));
    }
    this.sreje=sreje;
}

  public void  setSskcs(String sskcs) {
	/** 数据库SSKCS为Double类型;
		类型转换 String  -> Double*/
    if(null!=sskcs&&sskcs.length()>0 ) {
    	status.put("SSKCS", new Double(sskcs));
    }
    this.sskcs=sskcs;
}

  public void  setSwglm(String swglm) {
	/** 数据库SWGLM为Long类型;
	 	类型转换 String  ->Long */
    if(null!=swglm&&swglm.length()>0 ) {
    	status.put("SWGLM", new Long(swglm));
    }
    this.swglm=swglm;
}

  public void  setSybxfje(String sybxfje) {
	/** 数据库SYBXF_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=sybxfje&&sybxfje.length()>0 ) {
    	status.put("SYBXF_JE", new Double(sybxfje));
    }
    this.sybxfje=sybxfje;
}

  public void  setYbtsseje(String ybtsseje) {
	/** 数据库YBTSSE_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=ybtsseje&&ybtsseje.length()>0 ) {
    	status.put("YBTSSE_JE", new Double(ybtsseje));
    }
    this.ybtsseje=ybtsseje;
}

  public void  setYjseje(String yjseje) {
	/** 数据库YJSE_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=yjseje&&yjseje.length()>0 ) {
    	status.put("YJSE_JE", new Double(yjseje));
    }
    this.yjseje=yjseje;
}

  public void  setYnseje(String ynseje) {
	/** 数据库YNSE_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=ynseje&&ynseje.length()>0 ) {
    	status.put("YNSE_JE", new Double(ynseje));
    }
    this.ynseje=ynseje;
}

  public void  setYnssdeje(String ynssdeje) {
	/** 数据库YNSSDE_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=ynssdeje&&ynssdeje.length()>0 ) {
    	status.put("YNSSDE_JE", new Double(ynssdeje));
    }
    this.ynssdeje=ynssdeje;
}

  public void  setYxkcdsfje(String yxkcdsfje) {
	/** 数据库YXKCDSF_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=yxkcdsfje&&yxkcdsfje.length()>0 ) {
    	status.put("YXKCDSF_JE", new Double(yxkcdsfje));
    }
    this.yxkcdsfje=yxkcdsfje;
}

  public void  setZfgjjje(String zfgjjje) {
	/** 数据库ZFGJJ_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=zfgjjje&&zfgjjje.length()>0 ) {
    	status.put("ZFGJJ_JE", new Double(zfgjjje));
    }
    this.zfgjjje=zfgjjje;
}

  public void  setZsjgdm(String zsjgdm) {
    status.put("ZSJG_DM", zsjgdm);
    this.zsjgdm=zsjgdm;
}

  public void  setZspmdm(String zspmdm) {
    status.put("ZSPM_DM", zspmdm);
    this.zspmdm=zspmdm;
}

  public void  setZsxmdm(String zsxmdm) {
    status.put("ZSXM_DM", zsxmdm);
    this.zsxmdm=zsxmdm;
}

  public void  setZwmc(String zwmc) {
    status.put("ZWMC", zwmc);
    this.zwmc=zwmc;
}

  public void  setZxsbqx(String zxsbqx) {
    status.put("ZXSBQX", zxsbqx);
    this.zxsbqx=zxsbqx;
}

  public void  setZykcdjzeje(String zykcdjzeje) {
	/** 数据库ZYKCDJZE_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=zykcdjzeje&&zykcdjzeje.length()>0 ) {
    	status.put("ZYKCDJZE_JE", new Double(zykcdjzeje));
    }
    this.zykcdjzeje=zykcdjzeje;
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

  //sdqqsrq  日期类转换 sdqqsrq 的String 的类型 Set方法
  public void  setStr_sdqqsrq(String str_sdqqsrq ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_sdqqsrq!="")
		this.setSdqqsrq(DateUtils.parseFormatDate(str_sdqqsrq,indexFomat));
	else
		this.setSdqqsrq(null);
}

  //sdqqsrq  日期类转换 sdqqsrq 的String 的类型 Get方法
  public String  getStr_sdqqsrq(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.sdqqsrq)
		return DateUtils.toDateStr(this.sdqqsrq,indexFomat);;
	return "";
}

  //sdqzzrq  日期类转换 sdqzzrq 的String 的类型 Set方法
  public void  setStr_sdqzzrq(String str_sdqzzrq ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_sdqzzrq!="")
		this.setSdqzzrq(DateUtils.parseFormatDate(str_sdqzzrq,indexFomat));
	else
		this.setSdqzzrq(null);
}

  //sdqzzrq  日期类转换 sdqzzrq 的String 的类型 Get方法
  public String  getStr_sdqzzrq(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.sdqzzrq)
		return DateUtils.toDateStr(this.sdqzzrq,indexFomat);;
	return "";
}

////////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
}

