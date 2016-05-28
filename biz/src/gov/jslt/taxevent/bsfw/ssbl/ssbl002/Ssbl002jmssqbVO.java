package gov.jslt.taxevent.bsfw.ssbl.ssbl002;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class Ssbl002jmssqbVO extends CssBaseBpoVO {

public Ssbl002jmssqbVO() {
super();
}

    //发文日期;
    private Calendar fwrq;

    //公文文号;
    private String gwwh;

    //录入人员代码;
    private String lrrydm;

    //流水号;
    private String lsh;

    //审批备案标记;
    private String spbabj;

    //申请减免理由;
    private String sqjmly;

    //申请人;
    private String sqrmc;

    //申请日期;
    private Calendar sqrq;

    //税务管理码;
    private String swglm;

    //文件名;
    private String wjm;

    //1正常受理2补资料0同意结束9不同意作废;
    private String zfbj;

    //fwrq 的String 的类型;
    private  String  str_fwrq;

    //sqrq 的String 的类型;
    private  String  str_sqrq;


 public Calendar getFwrq() {
   return fwrq;
}

 public String getGwwh() {
   return gwwh;
}

 public String getLrrydm() {
   return lrrydm;
}

 public String getLsh() {
   return lsh;
}

 public String getSpbabj() {
   return spbabj;
}

 public String getSqjmly() {
   return sqjmly;
}

 public String getSqrmc() {
   return sqrmc;
}

 public Calendar getSqrq() {
   return sqrq;
}

 public String getSwglm() {
   return swglm;
}

 public String getWjm() {
   return wjm;
}

 public String getZfbj() {
   return zfbj;
}


  public void  setFwrq(Calendar fwrq) {
    status.put("FW_RQ", fwrq);
    this.fwrq=fwrq;
}

  public void  setGwwh(String gwwh) {
    status.put("GWWH", gwwh);
    this.gwwh=gwwh;
}

  public void  setLrrydm(String lrrydm) {
    status.put("LRRY_DM", lrrydm);
    this.lrrydm=lrrydm;
}

  public void  setLsh(String lsh) {
    status.put("LSH", lsh);
    this.lsh=lsh;
}

  public void  setSpbabj(String spbabj) {
    status.put("SPBA_BJ", spbabj);
    this.spbabj=spbabj;
}

  public void  setSqjmly(String sqjmly) {
    status.put("SQJMLY", sqjmly);
    this.sqjmly=sqjmly;
}

  public void  setSqrmc(String sqrmc) {
    status.put("SQR_MC", sqrmc);
    this.sqrmc=sqrmc;
}

  public void  setSqrq(Calendar sqrq) {
    status.put("SQ_RQ", sqrq);
    this.sqrq=sqrq;
}

  public void  setSwglm(String swglm) {
	/** 数据库SWGLM为Long类型;
	 	类型转换 String  ->Long */
    if(null!=swglm&&swglm.length()>0 ) {
    	status.put("SWGLM", new Long(swglm));
    }
    this.swglm=swglm;
}

  public void  setWjm(String wjm) {
    status.put("WJM", wjm);
    this.wjm=wjm;
}

  public void  setZfbj(String zfbj) {
    status.put("ZF_BJ", zfbj);
    this.zfbj=zfbj;
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
  //fwrq  日期类转换 fwrq 的String 的类型 Set方法
  public void  setStr_fwrq(String str_fwrq ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_fwrq!="")
		this.setFwrq(DateUtils.parseFormatDate(str_fwrq,indexFomat));
	else
		this.setFwrq(null);
}

  //fwrq  日期类转换 fwrq 的String 的类型 Get方法
  public String  getStr_fwrq(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.fwrq)
		return DateUtils.toDateStr(this.fwrq,indexFomat);;
	return "";
}

  //sqrq  日期类转换 sqrq 的String 的类型 Set方法
  public void  setStr_sqrq(String str_sqrq ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_sqrq!="")
		this.setSqrq(DateUtils.parseFormatDate(str_sqrq,indexFomat));
	else
		this.setSqrq(null);
}

  //sqrq  日期类转换 sqrq 的String 的类型 Get方法
  public String  getStr_sqrq(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.sqrq)
		return DateUtils.toDateStr(this.sqrq,indexFomat);;
	return "";
}

////////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
}

