package gov.jslt.taxevent.bsfw.sbns.sbns003;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class WBjbxxbzbVO extends CssBaseBpoVO {

public WBjbxxbzbVO() {
super();
}

    //备注;
    private String bz;

    //残疾等级情况;
    private String cjqk;

    //电子邮箱;
    private String dzyx;

    //纳税服务机关代码;
    private String fwjgdm;

    //固定电话;
    private String gh;

    //国籍(地区);
    private String gjdqdm;

    //管理机关代码;
    private String gljgdm;

    //个人识别号;
    private String grsbh;

    //稽查机关代码;
    private String jcjgdm;

    //检查机关代码;
    private String jiancjgdm;

    //境内联系地址补充地址;
    private String lxdz;

    //境内联系地址省级代码;
    private String lxdzsjdm;

    //境内联系地址市级代码;
    private String lxdzsqdm;

    //境内联系地址区县级代码;
    private String lxdzxjdm;

    //境内联系地址乡镇级代码(备用);
    private String lxdzxzdm;

    //申报凭证序号;
    private String pzxh;

    //迁移时间;
    private Calendar qysj;

    //迁移状态(0未迁移1迁移);
    private String qyzt;

    //任职受雇单位名称;
    private String rzdwmc;

    //任职受雇单位纳税人识别号;
    private String rzdwnsrsbm;

    //;
    private String rzdwswglm;

    //是否残疾烈属孤老(1残疾2烈属3孤老0否);
    private String sfcj;

    //税费所属期起;
    private Calendar sfssqqsrq;

    //税费所属期止;
    private Calendar sfssqzzrq;

    //身份证件号码;
    private String sfzhm;

    //身份证件类型;
    private String sfzlxdm;

    //手机;
    private String sj;

    //数据来源(0外网1内网);
    private String sjly;

    //税款所属机关代码;
    private String skssjgdm;

    //修改机关;
    private String xgjgdm;

    //修改人员;
    private String xgrydm;

    //学历(t_dm_gy_xl);
    private String xldm;

    //姓名;
    private String xm;

    //邮政编码;
    private String yzbm;

    //自然人税务管理码;
    private String zrrswglm;

    //征收机关代码;
    private String zsjgdm;

    //职务代码(t_dm_dj_zw);
    private String zwdm;

    //注销标记（0未注销1注销）;
    private String zxbj;

    //注销日期;
    private Calendar zxrq;

    //职业;
    private String zydm;

    //qysj 的String 的类型;
    private  String  str_qysj;

    //sfssqqsrq 的String 的类型;
    private  String  str_sfssqqsrq;

    //sfssqzzrq 的String 的类型;
    private  String  str_sfssqzzrq;

    //zxrq 的String 的类型;
    private  String  str_zxrq;


 public String getBz() {
   return bz;
}

 public String getCjqk() {
   return cjqk;
}

 public String getDzyx() {
   return dzyx;
}

 public String getFwjgdm() {
   return fwjgdm;
}

 public String getGh() {
   return gh;
}

 public String getGjdqdm() {
   return gjdqdm;
}

 public String getGljgdm() {
   return gljgdm;
}

 public String getGrsbh() {
   return grsbh;
}

 public String getJcjgdm() {
   return jcjgdm;
}

 public String getJiancjgdm() {
   return jiancjgdm;
}

 public String getLxdz() {
   return lxdz;
}

 public String getLxdzsjdm() {
   return lxdzsjdm;
}

 public String getLxdzsqdm() {
   return lxdzsqdm;
}

 public String getLxdzxjdm() {
   return lxdzxjdm;
}

 public String getLxdzxzdm() {
   return lxdzxzdm;
}

 public String getPzxh() {
   return pzxh;
}

 public Calendar getQysj() {
   return qysj;
}

 public String getQyzt() {
   return qyzt;
}

 public String getRzdwmc() {
   return rzdwmc;
}

 public String getRzdwnsrsbm() {
   return rzdwnsrsbm;
}

 public String getRzdwswglm() {
   return rzdwswglm;
}

 public String getSfcj() {
   return sfcj;
}

 public Calendar getSfssqqsrq() {
   return sfssqqsrq;
}

 public Calendar getSfssqzzrq() {
   return sfssqzzrq;
}

 public String getSfzhm() {
   return sfzhm;
}

 public String getSfzlxdm() {
   return sfzlxdm;
}

 public String getSj() {
   return sj;
}

 public String getSjly() {
   return sjly;
}

 public String getSkssjgdm() {
   return skssjgdm;
}

 public String getXgjgdm() {
   return xgjgdm;
}

 public String getXgrydm() {
   return xgrydm;
}

 public String getXldm() {
   return xldm;
}

 public String getXm() {
   return xm;
}

 public String getYzbm() {
   return yzbm;
}

 public String getZrrswglm() {
   return zrrswglm;
}

 public String getZsjgdm() {
   return zsjgdm;
}

 public String getZwdm() {
   return zwdm;
}

 public String getZxbj() {
   return zxbj;
}

 public Calendar getZxrq() {
   return zxrq;
}

 public String getZydm() {
   return zydm;
}


  public void  setBz(String bz) {
    status.put("BZ", bz);
    this.bz=bz;
}

  public void  setCjqk(String cjqk) {
    status.put("CJQK", cjqk);
    this.cjqk=cjqk;
}

  public void  setDzyx(String dzyx) {
    status.put("DZYX", dzyx);
    this.dzyx=dzyx;
}

  public void  setFwjgdm(String fwjgdm) {
    status.put("FWJG_DM", fwjgdm);
    this.fwjgdm=fwjgdm;
}

  public void  setGh(String gh) {
    status.put("GH", gh);
    this.gh=gh;
}

  public void  setGjdqdm(String gjdqdm) {
    status.put("GJDQ_DM", gjdqdm);
    this.gjdqdm=gjdqdm;
}

  public void  setGljgdm(String gljgdm) {
    status.put("GLJG_DM", gljgdm);
    this.gljgdm=gljgdm;
}

  public void  setGrsbh(String grsbh) {
    status.put("GRSBH", grsbh);
    this.grsbh=grsbh;
}

  public void  setJcjgdm(String jcjgdm) {
    status.put("JCJG_DM", jcjgdm);
    this.jcjgdm=jcjgdm;
}

  public void  setJiancjgdm(String jiancjgdm) {
    status.put("JIANCJG_DM", jiancjgdm);
    this.jiancjgdm=jiancjgdm;
}

  public void  setLxdz(String lxdz) {
    status.put("LXDZ", lxdz);
    this.lxdz=lxdz;
}

  public void  setLxdzsjdm(String lxdzsjdm) {
    status.put("LXDZ_SJDM", lxdzsjdm);
    this.lxdzsjdm=lxdzsjdm;
}

  public void  setLxdzsqdm(String lxdzsqdm) {
    status.put("LXDZ_SQDM", lxdzsqdm);
    this.lxdzsqdm=lxdzsqdm;
}

  public void  setLxdzxjdm(String lxdzxjdm) {
    status.put("LXDZ_XJDM", lxdzxjdm);
    this.lxdzxjdm=lxdzxjdm;
}

  public void  setLxdzxzdm(String lxdzxzdm) {
    status.put("LXDZ_XZDM", lxdzxzdm);
    this.lxdzxzdm=lxdzxzdm;
}

  public void  setPzxh(String pzxh) {
    status.put("PZ_XH", pzxh);
    this.pzxh=pzxh;
}

  public void  setQysj(Calendar qysj) {
    status.put("QY_SJ", qysj);
    this.qysj=qysj;
}

  public void  setQyzt(String qyzt) {
    status.put("QY_ZT", qyzt);
    this.qyzt=qyzt;
}

  public void  setRzdwmc(String rzdwmc) {
    status.put("RZDW_MC", rzdwmc);
    this.rzdwmc=rzdwmc;
}

  public void  setRzdwnsrsbm(String rzdwnsrsbm) {
    status.put("RZDW_NSRSBM", rzdwnsrsbm);
    this.rzdwnsrsbm=rzdwnsrsbm;
}

  public void  setRzdwswglm(String rzdwswglm) {
	/** 数据库RZDW_SWGLM为Long类型;
	 	类型转换 String  ->Long */
    if(null!=rzdwswglm&&rzdwswglm.length()>0 ) {
    	status.put("RZDW_SWGLM", new Long(rzdwswglm));
    }
    this.rzdwswglm=rzdwswglm;
}

  public void  setSfcj(String sfcj) {
    status.put("SFCJ", sfcj);
    this.sfcj=sfcj;
}

  public void  setSfssqqsrq(Calendar sfssqqsrq) {
    status.put("SFSSQ_QSRQ", sfssqqsrq);
    this.sfssqqsrq=sfssqqsrq;
}

  public void  setSfssqzzrq(Calendar sfssqzzrq) {
    status.put("SFSSQ_ZZRQ", sfssqzzrq);
    this.sfssqzzrq=sfssqzzrq;
}

  public void  setSfzhm(String sfzhm) {
    status.put("SFZHM", sfzhm);
    this.sfzhm=sfzhm;
}

  public void  setSfzlxdm(String sfzlxdm) {
    status.put("SFZLX_DM", sfzlxdm);
    this.sfzlxdm=sfzlxdm;
}

  public void  setSj(String sj) {
    status.put("SJ", sj);
    this.sj=sj;
}

  public void  setSjly(String sjly) {
    status.put("SJLY", sjly);
    this.sjly=sjly;
}

  public void  setSkssjgdm(String skssjgdm) {
    status.put("SKSSJG_DM", skssjgdm);
    this.skssjgdm=skssjgdm;
}

  public void  setXgjgdm(String xgjgdm) {
    status.put("XGJG_DM", xgjgdm);
    this.xgjgdm=xgjgdm;
}

  public void  setXgrydm(String xgrydm) {
    status.put("XGRY_DM", xgrydm);
    this.xgrydm=xgrydm;
}

  public void  setXldm(String xldm) {
    status.put("XL_DM", xldm);
    this.xldm=xldm;
}

  public void  setXm(String xm) {
    status.put("XM", xm);
    this.xm=xm;
}

  public void  setYzbm(String yzbm) {
    status.put("YZBM", yzbm);
    this.yzbm=yzbm;
}

  public void  setZrrswglm(String zrrswglm) {
	/** 数据库ZRR_SWGLM为Long类型;
	 	类型转换 String  ->Long */
    if(null!=zrrswglm&&zrrswglm.length()>0 ) {
    	status.put("ZRR_SWGLM", new Long(zrrswglm));
    }
    this.zrrswglm=zrrswglm;
}

  public void  setZsjgdm(String zsjgdm) {
    status.put("ZSJG_DM", zsjgdm);
    this.zsjgdm=zsjgdm;
}

  public void  setZwdm(String zwdm) {
    status.put("ZW_DM", zwdm);
    this.zwdm=zwdm;
}

  public void  setZxbj(String zxbj) {
    status.put("ZX_BJ", zxbj);
    this.zxbj=zxbj;
}

  public void  setZxrq(Calendar zxrq) {
    status.put("ZX_RQ", zxrq);
    this.zxrq=zxrq;
}

  public void  setZydm(String zydm) {
    status.put("ZY_DM", zydm);
    this.zydm=zydm;
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
  //qysj  日期类转换 qysj 的String 的类型 Set方法
  public void  setStr_qysj(String str_qysj ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_qysj!="")
		this.setQysj(DateUtils.parseFormatDate(str_qysj,indexFomat));
	else
		this.setQysj(null);
}

  //qysj  日期类转换 qysj 的String 的类型 Get方法
  public String  getStr_qysj(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.qysj)
		return DateUtils.toDateStr(this.qysj,indexFomat);;
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

  //zxrq  日期类转换 zxrq 的String 的类型 Set方法
  public void  setStr_zxrq(String str_zxrq ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_zxrq!="")
		this.setZxrq(DateUtils.parseFormatDate(str_zxrq,indexFomat));
	else
		this.setZxrq(null);
}

  //zxrq  日期类转换 zxrq 的String 的类型 Get方法
  public String  getStr_zxrq(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.zxrq)
		return DateUtils.toDateStr(this.zxrq,indexFomat);;
	return "";
}

////////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
}

