package gov.jslt.taxevent.bsfw.sbns.sbns003;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class WBjbxxbwzsVO extends CssBaseBpoVO {

public WBjbxxbwzsVO() {
super();
}

    //无住所纳税人出生地;
    private String csd;

    //无住所纳税人出生日期;
    private Calendar csrq;

    //无住所纳税人国籍（地区）;
    private String gjdqdm;

    //无住所纳税人境内职务(t_dm_dj_zw);
    private String jnzwdm;

    //无住所纳税人境外职务;
    private String jwzwdm;

    //境外支付国国别（地区）;
    private String jwzwgb;

    //无住所纳税人劳动就业证号码;
    private String ldjyzhm;

    //无住所纳税人来华时间;
    private Calendar lhsj;

    //无住所纳税人预计离境地点;
    private String ljdd;

    //无住所纳税人预计离境时间;
    private Calendar ljrq;

    //无住所纳税人纳税人识别号;
    private String nsrsbm;

    //无住所纳税人境外派遣单位地址;
    private String pqdwdz;

    //无住所纳税人境外派遣单位名称;
    private String pqdwmc;

    //申报凭证序号;
    private String pzxh;

    //无住所纳税人境内受聘签约单位地址;
    private String qydwdz;

    //无住所纳税人境内受聘签约单位名称;
    private String qydwmc;

    //无住所纳税人境内受聘签约单位扣缴义务人编码;
    private String qydwswglm;

    //无住所纳税人境内受聘签约单位邮政编码;
    private String qydwyzbm;

    //无住所纳税人境内任职受雇单位地址;
    private String rzdwdz;

    //无住所纳税人境内任职受雇单位名称;
    private String rzdwmc;

    //无住所纳税人境内任职受雇单位扣缴义务人编码;
    private String rzdwswglm;

    //无住所纳税人境内任职受雇单位邮政编码;
    private String rzdwyzbm;

    //无住所纳税人任职期限;
    private Calendar rzqx;

    //无住所纳税人是否税收协定缔约国对方居民(1是0否);
    private String sfxddyg;

    //无住所纳税人性别（1男0女）;
    private String xb;

    //修改机关;
    private String xgjgdm;

    //修改人员;
    private String xgrydm;

    //支付地（1境内支付2境外支付3境内、外同时支付）;
    private String zfd;

    //csrq 的String 的类型;
    private  String  str_csrq;

    //lhsj 的String 的类型;
    private  String  str_lhsj;

    //ljrq 的String 的类型;
    private  String  str_ljrq;

    //rzqx 的String 的类型;
    private  String  str_rzqx;


 public String getCsd() {
   return csd;
}

 public Calendar getCsrq() {
   return csrq;
}

 public String getGjdqdm() {
   return gjdqdm;
}

 public String getJnzwdm() {
   return jnzwdm;
}

 public String getJwzwdm() {
   return jwzwdm;
}

 public String getJwzwgb() {
   return jwzwgb;
}

 public String getLdjyzhm() {
   return ldjyzhm;
}

 public Calendar getLhsj() {
   return lhsj;
}

 public String getLjdd() {
   return ljdd;
}

 public Calendar getLjrq() {
   return ljrq;
}

 public String getNsrsbm() {
   return nsrsbm;
}

 public String getPqdwdz() {
   return pqdwdz;
}

 public String getPqdwmc() {
   return pqdwmc;
}

 public String getPzxh() {
   return pzxh;
}

 public String getQydwdz() {
   return qydwdz;
}

 public String getQydwmc() {
   return qydwmc;
}

 public String getQydwswglm() {
   return qydwswglm;
}

 public String getQydwyzbm() {
   return qydwyzbm;
}

 public String getRzdwdz() {
   return rzdwdz;
}

 public String getRzdwmc() {
   return rzdwmc;
}

 public String getRzdwswglm() {
   return rzdwswglm;
}

 public String getRzdwyzbm() {
   return rzdwyzbm;
}

 public Calendar getRzqx() {
   return rzqx;
}

 public String getSfxddyg() {
   return sfxddyg;
}

 public String getXb() {
   return xb;
}

 public String getXgjgdm() {
   return xgjgdm;
}

 public String getXgrydm() {
   return xgrydm;
}

 public String getZfd() {
   return zfd;
}


  public void  setCsd(String csd) {
    status.put("CSD", csd);
    this.csd=csd;
}

  public void  setCsrq(Calendar csrq) {
    status.put("CSRQ", csrq);
    this.csrq=csrq;
}

  public void  setGjdqdm(String gjdqdm) {
    status.put("GJDQ_DM", gjdqdm);
    this.gjdqdm=gjdqdm;
}

  public void  setJnzwdm(String jnzwdm) {
    status.put("JNZW_DM", jnzwdm);
    this.jnzwdm=jnzwdm;
}

  public void  setJwzwdm(String jwzwdm) {
    status.put("JWZW_DM", jwzwdm);
    this.jwzwdm=jwzwdm;
}

  public void  setJwzwgb(String jwzwgb) {
    status.put("JWZW_GB", jwzwgb);
    this.jwzwgb=jwzwgb;
}

  public void  setLdjyzhm(String ldjyzhm) {
    status.put("LDJYZHM", ldjyzhm);
    this.ldjyzhm=ldjyzhm;
}

  public void  setLhsj(Calendar lhsj) {
    status.put("LHSJ", lhsj);
    this.lhsj=lhsj;
}

  public void  setLjdd(String ljdd) {
    status.put("LJDD", ljdd);
    this.ljdd=ljdd;
}

  public void  setLjrq(Calendar ljrq) {
    status.put("LJRQ", ljrq);
    this.ljrq=ljrq;
}

  public void  setNsrsbm(String nsrsbm) {
    status.put("NSRSBM", nsrsbm);
    this.nsrsbm=nsrsbm;
}

  public void  setPqdwdz(String pqdwdz) {
    status.put("PQDW_DZ", pqdwdz);
    this.pqdwdz=pqdwdz;
}

  public void  setPqdwmc(String pqdwmc) {
    status.put("PQDW_MC", pqdwmc);
    this.pqdwmc=pqdwmc;
}

  public void  setPzxh(String pzxh) {
    status.put("PZ_XH", pzxh);
    this.pzxh=pzxh;
}

  public void  setQydwdz(String qydwdz) {
    status.put("QYDW_DZ", qydwdz);
    this.qydwdz=qydwdz;
}

  public void  setQydwmc(String qydwmc) {
    status.put("QYDW_MC", qydwmc);
    this.qydwmc=qydwmc;
}

  public void  setQydwswglm(String qydwswglm) {
	/** 数据库QYDW_SWGLM为Long类型;
	 	类型转换 String  ->Long */
    if(null!=qydwswglm&&qydwswglm.length()>0 ) {
    	status.put("QYDW_SWGLM", new Long(qydwswglm));
    }
    this.qydwswglm=qydwswglm;
}

  public void  setQydwyzbm(String qydwyzbm) {
    status.put("QYDW_YZBM", qydwyzbm);
    this.qydwyzbm=qydwyzbm;
}

  public void  setRzdwdz(String rzdwdz) {
    status.put("RZDW_DZ", rzdwdz);
    this.rzdwdz=rzdwdz;
}

  public void  setRzdwmc(String rzdwmc) {
    status.put("RZDW_MC", rzdwmc);
    this.rzdwmc=rzdwmc;
}

  public void  setRzdwswglm(String rzdwswglm) {
	/** 数据库RZDW_SWGLM为Long类型;
	 	类型转换 String  ->Long */
    if(null!=rzdwswglm&&rzdwswglm.length()>0 ) {
    	status.put("RZDW_SWGLM", new Long(rzdwswglm));
    }
    this.rzdwswglm=rzdwswglm;
}

  public void  setRzdwyzbm(String rzdwyzbm) {
    status.put("RZDW_YZBM", rzdwyzbm);
    this.rzdwyzbm=rzdwyzbm;
}

  public void  setRzqx(Calendar rzqx) {
    status.put("RZQX", rzqx);
    this.rzqx=rzqx;
}

  public void  setSfxddyg(String sfxddyg) {
    status.put("SFXDDYG", sfxddyg);
    this.sfxddyg=sfxddyg;
}

  public void  setXb(String xb) {
    status.put("XB", xb);
    this.xb=xb;
}

  public void  setXgjgdm(String xgjgdm) {
    status.put("XGJG_DM", xgjgdm);
    this.xgjgdm=xgjgdm;
}

  public void  setXgrydm(String xgrydm) {
    status.put("XGRY_DM", xgrydm);
    this.xgrydm=xgrydm;
}

  public void  setZfd(String zfd) {
    status.put("ZFD", zfd);
    this.zfd=zfd;
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
  //csrq  日期类转换 csrq 的String 的类型 Set方法
  public void  setStr_csrq(String str_csrq ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_csrq!="")
		this.setCsrq(DateUtils.parseFormatDate(str_csrq,indexFomat));
	else
		this.setCsrq(null);
}

  //csrq  日期类转换 csrq 的String 的类型 Get方法
  public String  getStr_csrq(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.csrq)
		return DateUtils.toDateStr(this.csrq,indexFomat);;
	return "";
}

  //lhsj  日期类转换 lhsj 的String 的类型 Set方法
  public void  setStr_lhsj(String str_lhsj ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_lhsj!="")
		this.setLhsj(DateUtils.parseFormatDate(str_lhsj,indexFomat));
	else
		this.setLhsj(null);
}

  //lhsj  日期类转换 lhsj 的String 的类型 Get方法
  public String  getStr_lhsj(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.lhsj)
		return DateUtils.toDateStr(this.lhsj,indexFomat);;
	return "";
}

  //ljrq  日期类转换 ljrq 的String 的类型 Set方法
  public void  setStr_ljrq(String str_ljrq ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_ljrq!="")
		this.setLjrq(DateUtils.parseFormatDate(str_ljrq,indexFomat));
	else
		this.setLjrq(null);
}

  //ljrq  日期类转换 ljrq 的String 的类型 Get方法
  public String  getStr_ljrq(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.ljrq)
		return DateUtils.toDateStr(this.ljrq,indexFomat);;
	return "";
}

  //rzqx  日期类转换 rzqx 的String 的类型 Set方法
  public void  setStr_rzqx(String str_rzqx ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_rzqx!="")
		this.setRzqx(DateUtils.parseFormatDate(str_rzqx,indexFomat));
	else
		this.setRzqx(null);
}

  //rzqx  日期类转换 rzqx 的String 的类型 Get方法
  public String  getStr_rzqx(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.rzqx)
		return DateUtils.toDateStr(this.rzqx,indexFomat);;
	return "";
}

////////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
}

