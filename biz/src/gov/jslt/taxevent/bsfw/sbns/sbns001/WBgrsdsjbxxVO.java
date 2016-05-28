package gov.jslt.taxevent.bsfw.sbns.sbns001;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class WBgrsdsjbxxVO extends CssBaseBpoVO {

public WBgrsdsjbxxVO() {
super();
}

    //国籍名称;
    private String gjmc;

    //工作地点;
    private String gzdwdz;

    //工作单位;
    private String gzdwmc;

    //经常居住地;
    private String jcjzd;

    //中国境内住址;
    private String jnzz;

    //联系电话;
    private String lxdh;

    //纳税人识别码;
    private String nsrsbm;

    //凭证序号;
    private String pzxh;

    //迁移状态;
    private String qyzt;

    //首次抵华时间;
    private Calendar scdhsjrq;

    //身份证件类型名称;
    private String sfzjlxmc;

    //身份证件号码;
    private String sfzjhm;

    //税务管理码;
    private String swglm;

    //邮政编码;
    private String yzbm;

    //在华居住天数;
    private String zhjzts;

    //纳税人姓名;
    private String zwmc;

    //职务;
    private String zw_mc;

    //职业名称;
    private String zymc;

    //scdhsjrq 的String 的类型;
    private  String  str_scdhsjrq;


 public String getGjmc() {
   return gjmc;
}

 public String getGzdwdz() {
   return gzdwdz;
}

 public String getGzdwmc() {
   return gzdwmc;
}

 public String getJcjzd() {
   return jcjzd;
}

 public String getJnzz() {
   return jnzz;
}

 public String getLxdh() {
   return lxdh;
}

 public String getNsrsbm() {
   return nsrsbm;
}

 public String getPzxh() {
   return pzxh;
}

 public String getQyzt() {
   return qyzt;
}

 public Calendar getScdhsjrq() {
   return scdhsjrq;
}

 public String getSfzjlxmc() {
   return sfzjlxmc;
}

 public String getSfzjhm() {
   return sfzjhm;
}

 public String getSwglm() {
   return swglm;
}

 public String getYzbm() {
   return yzbm;
}

 public String getZhjzts() {
   return zhjzts;
}

 public String getZwmc() {
   return zwmc;
}

 public String getZw_mc() {
   return zw_mc;
}

 public String getZymc() {
   return zymc;
}


  public void  setGjmc(String gjmc) {
    status.put("GJ_MC", gjmc);
    this.gjmc=gjmc;
}

  public void  setGzdwdz(String gzdwdz) {
    status.put("GZDW_DZ", gzdwdz);
    this.gzdwdz=gzdwdz;
}

  public void  setGzdwmc(String gzdwmc) {
    status.put("GZDW_MC", gzdwmc);
    this.gzdwmc=gzdwmc;
}

  public void  setJcjzd(String jcjzd) {
    status.put("JCJZD", jcjzd);
    this.jcjzd=jcjzd;
}

  public void  setJnzz(String jnzz) {
    status.put("JNZZ", jnzz);
    this.jnzz=jnzz;
}

  public void  setLxdh(String lxdh) {
    status.put("LXDH", lxdh);
    this.lxdh=lxdh;
}

  public void  setNsrsbm(String nsrsbm) {
    status.put("NSRSBM", nsrsbm);
    this.nsrsbm=nsrsbm;
}

  public void  setPzxh(String pzxh) {
    status.put("PZ_XH", pzxh);
    this.pzxh=pzxh;
}

  public void  setQyzt(String qyzt) {
    status.put("QYZT", qyzt);
    this.qyzt=qyzt;
}

  public void  setScdhsjrq(Calendar scdhsjrq) {
    status.put("SCDHSJ_RQ", scdhsjrq);
    this.scdhsjrq=scdhsjrq;
}

  public void  setSfzjlxmc(String sfzjlxmc) {
    status.put("SFZJLX_MC", sfzjlxmc);
    this.sfzjlxmc=sfzjlxmc;
}

  public void  setSfzjhm(String sfzjhm) {
    status.put("SFZJ_HM", sfzjhm);
    this.sfzjhm=sfzjhm;
}

  public void  setSwglm(String swglm) {
	/** 数据库SWGLM为Long类型;
	 	类型转换 String  ->Long */
    if(null!=swglm&&swglm.length()>0 ) {
    	status.put("SWGLM", new Long(swglm));
    }
    this.swglm=swglm;
}

  public void  setYzbm(String yzbm) {
    status.put("YZBM", yzbm);
    this.yzbm=yzbm;
}

  public void  setZhjzts(String zhjzts) {
	/** 数据库ZHJZ_TS为Long类型;
	 	类型转换 String  ->Long */
    if(null!=zhjzts&&zhjzts.length()>0 ) {
    	status.put("ZHJZ_TS", new Long(zhjzts));
    }
    this.zhjzts=zhjzts;
}

  public void  setZwmc(String zwmc) {
    status.put("ZWMC", zwmc);
    this.zwmc=zwmc;
}

  public void  setZw_mc(String zw_mc) {
    status.put("ZW_MC", zw_mc);
    this.zw_mc=zw_mc;
}

  public void  setZymc(String zymc) {
    status.put("ZY_MC", zymc);
    this.zymc=zymc;
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
  //scdhsjrq  日期类转换 scdhsjrq 的String 的类型 Set方法
  public void  setStr_scdhsjrq(String str_scdhsjrq ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_scdhsjrq!="")
		this.setScdhsjrq(DateUtils.parseFormatDate(str_scdhsjrq,indexFomat));
	else
		this.setScdhsjrq(null);
}

  //scdhsjrq  日期类转换 scdhsjrq 的String 的类型 Get方法
  public String  getStr_scdhsjrq(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.scdhsjrq)
		return DateUtils.toDateStr(this.scdhsjrq,indexFomat);;
	return "";
}

////////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
}

