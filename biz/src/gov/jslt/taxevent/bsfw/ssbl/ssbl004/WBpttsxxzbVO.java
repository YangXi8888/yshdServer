package gov.jslt.taxevent.bsfw.ssbl.ssbl004;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class WBpttsxxzbVO extends CssBaseBpoVO {

public WBpttsxxzbVO() {
super();
}

    //录入人员代码;
    private String lrrydm;

    //流水号;
    private String lsh;

    //0  纳税人确认 1 税务人员确认;
    private String qrlx;

    //变更标记 0 不需要变更 1 需要变更;
    private String sfbgbj;

    //审核标记 0 不需要审核 1 需要审核;
    private String sfshbj;

    //税务管理码;
    private String swglm;

    //推送机关代码;
    private String tsjgdm;

    //推送情况说明;
    private String tsqksm;

    //状态 0 未提交确认信息 1 暂存修改中   2补正资料(暂存)  3 已提交确认信息     4退回(审核不通过)  5初审通过  6审核通过    7已完成   9作废 ;
    private String tsqrzt;

    //推送人员代码;
    private String tsrydm;

    //所属数据源代码(t_dm_xt_tspt_sjy);
    private String tssjydm;

    //推送提醒内容;
    private String tstxnr;

    //推送类型 0 网上办税厅 1 稽查调用  2 税收评定调用;
    private String tslx;

    //推送时间;
    private Calendar tsrq;

    //修改人员代码;
    private String xgrydm;

    //推送有效日期起;
    private Calendar yxrqqs;

    //推送有效日期止;
    private Calendar yxrqzz;

    //tsrq 的String 的类型;
    private  String  str_tsrq;

    //yxrqqs 的String 的类型;
    private  String  str_yxrqqs;

    //yxrqzz 的String 的类型;
    private  String  str_yxrqzz;


 public String getLrrydm() {
   return lrrydm;
}

 public String getLsh() {
   return lsh;
}

 public String getQrlx() {
   return qrlx;
}

 public String getSfbgbj() {
   return sfbgbj;
}

 public String getSfshbj() {
   return sfshbj;
}

 public String getSwglm() {
   return swglm;
}

 public String getTsjgdm() {
   return tsjgdm;
}

 public String getTsqksm() {
   return tsqksm;
}

 public String getTsqrzt() {
   return tsqrzt;
}

 public String getTsrydm() {
   return tsrydm;
}

 public String getTssjydm() {
   return tssjydm;
}

 public String getTstxnr() {
   return tstxnr;
}

 public String getTslx() {
   return tslx;
}

 public Calendar getTsrq() {
   return tsrq;
}

 public String getXgrydm() {
   return xgrydm;
}

 public Calendar getYxrqqs() {
   return yxrqqs;
}

 public Calendar getYxrqzz() {
   return yxrqzz;
}


  public void  setLrrydm(String lrrydm) {
    status.put("LRRY_DM", lrrydm);
    this.lrrydm=lrrydm;
}

  public void  setLsh(String lsh) {
    status.put("LSH", lsh);
    this.lsh=lsh;
}

  public void  setQrlx(String qrlx) {
    status.put("QR_LX", qrlx);
    this.qrlx=qrlx;
}

  public void  setSfbgbj(String sfbgbj) {
    status.put("SFBG_BJ", sfbgbj);
    this.sfbgbj=sfbgbj;
}

  public void  setSfshbj(String sfshbj) {
    status.put("SFSH_BJ", sfshbj);
    this.sfshbj=sfshbj;
}

  public void  setSwglm(String swglm) {
	/** 数据库SWGLM为Long类型;
	 	类型转换 String  ->Long */
    if(null!=swglm&&swglm.length()>0 ) {
    	status.put("SWGLM", new Long(swglm));
    }
    this.swglm=swglm;
}

  public void  setTsjgdm(String tsjgdm) {
    status.put("TSJG_DM", tsjgdm);
    this.tsjgdm=tsjgdm;
}

  public void  setTsqksm(String tsqksm) {
    status.put("TSQK_SM", tsqksm);
    this.tsqksm=tsqksm;
}

  public void  setTsqrzt(String tsqrzt) {
    status.put("TSQR_ZT", tsqrzt);
    this.tsqrzt=tsqrzt;
}

  public void  setTsrydm(String tsrydm) {
    status.put("TSRY_DM", tsrydm);
    this.tsrydm=tsrydm;
}

  public void  setTssjydm(String tssjydm) {
    status.put("TSSJY_DM", tssjydm);
    this.tssjydm=tssjydm;
}

  public void  setTstxnr(String tstxnr) {
    status.put("TSTX_NR", tstxnr);
    this.tstxnr=tstxnr;
}

  public void  setTslx(String tslx) {
    status.put("TS_LX", tslx);
    this.tslx=tslx;
}

  public void  setTsrq(Calendar tsrq) {
    status.put("TS_RQ", tsrq);
    this.tsrq=tsrq;
}

  public void  setXgrydm(String xgrydm) {
    status.put("XGRY_DM", xgrydm);
    this.xgrydm=xgrydm;
}

  public void  setYxrqqs(Calendar yxrqqs) {
    status.put("YXRQ_QS", yxrqqs);
    this.yxrqqs=yxrqqs;
}

  public void  setYxrqzz(Calendar yxrqzz) {
    status.put("YXRQ_ZZ", yxrqzz);
    this.yxrqzz=yxrqzz;
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
  //tsrq  日期类转换 tsrq 的String 的类型 Set方法
  public void  setStr_tsrq(String str_tsrq ,Integer dateFomat){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=dateFomat==null?3:dateFomat	;
	if (str_tsrq!="")
		this.setTsrq(DateUtils.parseFormatDate(str_tsrq,indexFomat));
	else
		this.setTsrq(null);
}

  //tsrq  日期类转换 tsrq 的String 的类型 Get方法
  public String  getStr_tsrq(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.tsrq)
		return DateUtils.toDateStr(this.tsrq,indexFomat);;
	return "";
}

  //yxrqqs  日期类转换 yxrqqs 的String 的类型 Set方法
  public void  setStr_yxrqqs(String str_yxrqqs ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_yxrqqs!="")
		this.setYxrqqs(DateUtils.parseFormatDate(str_yxrqqs,indexFomat));
	else
		this.setYxrqqs(null);
}

  //yxrqqs  日期类转换 yxrqqs 的String 的类型 Get方法
  public String  getStr_yxrqqs(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.yxrqqs)
		return DateUtils.toDateStr(this.yxrqqs,indexFomat);;
	return "";
}

  //yxrqzz  日期类转换 yxrqzz 的String 的类型 Set方法
  public void  setStr_yxrqzz(String str_yxrqzz ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_yxrqzz!="")
		this.setYxrqzz(DateUtils.parseFormatDate(str_yxrqzz,indexFomat));
	else
		this.setYxrqzz(null);
}

  //yxrqzz  日期类转换 yxrqzz 的String 的类型 Get方法
  public String  getStr_yxrqzz(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.yxrqzz)
		return DateUtils.toDateStr(this.yxrqzz,indexFomat);;
	return "";
}

////////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
}

