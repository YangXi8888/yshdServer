package gov.jslt.taxevent.ggfw.sqhd.sq005;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class YyfwgdVO extends CssBaseBpoVO {

public YyfwgdVO() {
super();
}

    //备注信息;
    private String bz;

    //查询密码;
    private String cxmm;

    //单位名称;
    private String dwmc;

    //;
    private String lrrydm;

    //流水号（主键）;
    private String lsh;

    //联系电话;
    private String lxdh;

    //来源渠道  01：在线咨询；02：热线电话；03：网上办税厅 ; 04 : 掌上APP;
    private String lyqd;

    //纳税识别号;
    private String nssbh;

    //;
    private String xgrydm;

    //业务描述;
    private String ywms;

    //预约市;
    private String yyds;

    //预约服务厅名称;
    private String yymc;

    //预约区县;
    private String yyqx;

    //预约人名称;
    private String yyrmc;

    //预约日期;
    private Calendar yyrq;

    //预约时间;
    private String yysj;

    //预约事项代码;
    private String yysxdm;

    //注册地址;
    private String zcdz;

    //yyrq 的String 的类型;
    private  String  str_yyrq;


 public String getBz() {
   return bz;
}

 public String getCxmm() {
   return cxmm;
}

 public String getDwmc() {
   return dwmc;
}

 public String getLrrydm() {
   return lrrydm;
}

 public String getLsh() {
   return lsh;
}

 public String getLxdh() {
   return lxdh;
}

 public String getLyqd() {
   return lyqd;
}

 public String getNssbh() {
   return nssbh;
}

 public String getXgrydm() {
   return xgrydm;
}

 public String getYwms() {
   return ywms;
}

 public String getYyds() {
   return yyds;
}

 public String getYymc() {
   return yymc;
}

 public String getYyqx() {
   return yyqx;
}

 public String getYyrmc() {
   return yyrmc;
}

 public Calendar getYyrq() {
   return yyrq;
}

 public String getYysj() {
   return yysj;
}

 public String getYysxdm() {
   return yysxdm;
}

 public String getZcdz() {
   return zcdz;
}


  public void  setBz(String bz) {
    status.put("BZ", bz);
    this.bz=bz;
}

  public void  setCxmm(String cxmm) {
    status.put("CXMM", cxmm);
    this.cxmm=cxmm;
}

  public void  setDwmc(String dwmc) {
    status.put("DWMC", dwmc);
    this.dwmc=dwmc;
}

  public void  setLrrydm(String lrrydm) {
    status.put("LRRY_DM", lrrydm);
    this.lrrydm=lrrydm;
}

  public void  setLsh(String lsh) {
    status.put("LSH", lsh);
    this.lsh=lsh;
}

  public void  setLxdh(String lxdh) {
    status.put("LXDH", lxdh);
    this.lxdh=lxdh;
}

  public void  setLyqd(String lyqd) {
    status.put("LYQD", lyqd);
    this.lyqd=lyqd;
}

  public void  setNssbh(String nssbh) {
    status.put("NSSBH", nssbh);
    this.nssbh=nssbh;
}

  public void  setXgrydm(String xgrydm) {
    status.put("XGRY_DM", xgrydm);
    this.xgrydm=xgrydm;
}

  public void  setYwms(String ywms) {
    status.put("YWMS", ywms);
    this.ywms=ywms;
}

  public void  setYyds(String yyds) {
    status.put("YYDS", yyds);
    this.yyds=yyds;
}

  public void  setYymc(String yymc) {
    status.put("YYMC", yymc);
    this.yymc=yymc;
}

  public void  setYyqx(String yyqx) {
    status.put("YYQX", yyqx);
    this.yyqx=yyqx;
}

  public void  setYyrmc(String yyrmc) {
    status.put("YYRMC", yyrmc);
    this.yyrmc=yyrmc;
}

  public void  setYyrq(Calendar yyrq) {
    status.put("YYRQ", yyrq);
    this.yyrq=yyrq;
}

  public void  setYysj(String yysj) {
    status.put("YYSJ", yysj);
    this.yysj=yysj;
}

  public void  setYysxdm(String yysxdm) {
    status.put("YYSXDM", yysxdm);
    this.yysxdm=yysxdm;
}

  public void  setZcdz(String zcdz) {
    status.put("ZCDZ", zcdz);
    this.zcdz=zcdz;
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
  //yyrq  日期类转换 yyrq 的String 的类型 Set方法
  public void  setStr_yyrq(String str_yyrq ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_yyrq!="")
		this.setYyrq(DateUtils.parseFormatDate(str_yyrq,indexFomat));
	else
		this.setYyrq(null);
}

  //yyrq  日期类转换 yyrq 的String 的类型 Get方法
  public String  getStr_yyrq(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.yyrq)
		return DateUtils.toDateStr(this.yyrq,indexFomat);;
	return "";
}

////////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
}

