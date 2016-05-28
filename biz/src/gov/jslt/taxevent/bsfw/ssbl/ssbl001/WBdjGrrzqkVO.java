package gov.jslt.taxevent.bsfw.ssbl.ssbl001;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class WBdjGrrzqkVO extends CssBaseBpoVO {

public WBdjGrrzqkVO() {
super();
}

    //扣缴义务人税务管理码;
    private String kjywrnsrnbm;

    //录入人员;
    private String lrrydm;

    //外网申请产生流水号;
    private String lsh;

    //自然人离职时间;
    private Calendar narlzrq;

    //任职单位地址;
    private String rzdwdz;

    //任职单位地址（英文）;
    private String rzdwdzyw;

    //任职单位名称（英文）;
    private String rzdwmcyw;

    //任职单位名称;
    private String rzdwmc;

    //单位纳税人识别号;
    private String rzdwnsrsbh;

    //任职单位税务登记号;
    private String rzdwnsrsbm;

    //是否董事:0-非董事；1-董事;
    private String sfds;

    //税务管理码;
    private String swglm;

    //修改人员;
    private String xgrydm;

    //序号;
    private String xh;

    //自然人任职时间;
    private Calendar zrrrzsj;

    //任职单位所任职务;
    private String zwdm;

    //主要任职标记;
    private String zyrzbj;

    //narlzrq 的String 的类型;
    private  String  str_narlzrq;

    //zrrrzsj 的String 的类型;
    private  String  str_zrrrzsj;


 public String getKjywrnsrnbm() {
   return kjywrnsrnbm;
}

 public String getLrrydm() {
   return lrrydm;
}

 public String getLsh() {
   return lsh;
}

 public Calendar getNarlzrq() {
   return narlzrq;
}

 public String getRzdwdz() {
   return rzdwdz;
}

 public String getRzdwdzyw() {
   return rzdwdzyw;
}

 public String getRzdwmcyw() {
   return rzdwmcyw;
}

 public String getRzdwmc() {
   return rzdwmc;
}

 public String getRzdwnsrsbh() {
   return rzdwnsrsbh;
}

 public String getRzdwnsrsbm() {
   return rzdwnsrsbm;
}

 public String getSfds() {
   return sfds;
}

 public String getSwglm() {
   return swglm;
}

 public String getXgrydm() {
   return xgrydm;
}

 public String getXh() {
   return xh;
}

 public Calendar getZrrrzsj() {
   return zrrrzsj;
}

 public String getZwdm() {
   return zwdm;
}

 public String getZyrzbj() {
   return zyrzbj;
}


  public void  setKjywrnsrnbm(String kjywrnsrnbm) {
	/** 数据库KJYWR_NSRNBM为Long类型;
	 	类型转换 String  ->Long */
    if(null!=kjywrnsrnbm&&kjywrnsrnbm.length()>0 ) {
    	status.put("KJYWR_NSRNBM", new Long(kjywrnsrnbm));
    }
    this.kjywrnsrnbm=kjywrnsrnbm;
}

  public void  setLrrydm(String lrrydm) {
    status.put("LRRY_DM", lrrydm);
    this.lrrydm=lrrydm;
}

  public void  setLsh(String lsh) {
    status.put("LSH", lsh);
    this.lsh=lsh;
}

  public void  setNarlzrq(Calendar narlzrq) {
    status.put("NARLZ_RQ", narlzrq);
    this.narlzrq=narlzrq;
}

  public void  setRzdwdz(String rzdwdz) {
    status.put("RZDWDZ", rzdwdz);
    this.rzdwdz=rzdwdz;
}

  public void  setRzdwdzyw(String rzdwdzyw) {
    status.put("RZDWDZ_YW", rzdwdzyw);
    this.rzdwdzyw=rzdwdzyw;
}

  public void  setRzdwmcyw(String rzdwmcyw) {
    status.put("RZDWMC_YW", rzdwmcyw);
    this.rzdwmcyw=rzdwmcyw;
}

  public void  setRzdwmc(String rzdwmc) {
    status.put("RZDW_MC", rzdwmc);
    this.rzdwmc=rzdwmc;
}

  public void  setRzdwnsrsbh(String rzdwnsrsbh) {
    status.put("RZDW_NSRSBH", rzdwnsrsbh);
    this.rzdwnsrsbh=rzdwnsrsbh;
}

  public void  setRzdwnsrsbm(String rzdwnsrsbm) {
    status.put("RZDW_NSRSBM", rzdwnsrsbm);
    this.rzdwnsrsbm=rzdwnsrsbm;
}

  public void  setSfds(String sfds) {
    status.put("SFDS", sfds);
    this.sfds=sfds;
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

  public void  setXh(String xh) {
    status.put("XH", xh);
    this.xh=xh;
}

  public void  setZrrrzsj(Calendar zrrrzsj) {
    status.put("ZRRRZSJ", zrrrzsj);
    this.zrrrzsj=zrrrzsj;
}

  public void  setZwdm(String zwdm) {
    status.put("ZW_DM", zwdm);
    this.zwdm=zwdm;
}

  public void  setZyrzbj(String zyrzbj) {
    status.put("ZYRZ_BJ", zyrzbj);
    this.zyrzbj=zyrzbj;
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
  //narlzrq  日期类转换 narlzrq 的String 的类型 Set方法
  public void  setStr_narlzrq(String str_narlzrq ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_narlzrq!="")
		this.setNarlzrq(DateUtils.parseFormatDate(str_narlzrq,indexFomat));
	else
		this.setNarlzrq(null);
}

  //narlzrq  日期类转换 narlzrq 的String 的类型 Get方法
  public String  getStr_narlzrq(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.narlzrq)
		return DateUtils.toDateStr(this.narlzrq,indexFomat);;
	return "";
}

  //zrrrzsj  日期类转换 zrrrzsj 的String 的类型 Set方法
  public void  setStr_zrrrzsj(String str_zrrrzsj ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_zrrrzsj!="")
		this.setZrrrzsj(DateUtils.parseFormatDate(str_zrrrzsj,indexFomat));
	else
		this.setZrrrzsj(null);
}

  //zrrrzsj  日期类转换 zrrrzsj 的String 的类型 Get方法
  public String  getStr_zrrrzsj(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.zrrrzsj)
		return DateUtils.toDateStr(this.zrrrzsj,indexFomat);;
	return "";
}

////////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
}

