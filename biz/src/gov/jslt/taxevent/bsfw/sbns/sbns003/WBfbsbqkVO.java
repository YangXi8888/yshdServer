package gov.jslt.taxevent.bsfw.sbns.sbns003;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class WBfbsbqkVO extends CssBaseBpoVO {

public WBfbsbqkVO() {
super();
 hasXgrq=false ;
}

    //附表凭证种类代码;
    private String fbpzzldm;

    //纳税人识别码;
    private String nsrsbm;

    //纳税人名称;
    private String nsrmc;

    //凭证种类代码;
    private String pzzldm;

    //凭证序号;
    private String pzxh;

    //迁移状态;
    private String qyzt;

    //申报表序号;
    private String sbbxh;

    //税务管理码;
    private String swglm;

    //填表日期;
    private Calendar tbrq;

    //tbrq 的String 的类型;
    private  String  str_tbrq;


 public String getFbpzzldm() {
   return fbpzzldm;
}

 public String getNsrsbm() {
   return nsrsbm;
}

 public String getNsrmc() {
   return nsrmc;
}

 public String getPzzldm() {
   return pzzldm;
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

 public String getSwglm() {
   return swglm;
}

 public Calendar getTbrq() {
   return tbrq;
}


  public void  setFbpzzldm(String fbpzzldm) {
    status.put("FBPZZL_DM", fbpzzldm);
    this.fbpzzldm=fbpzzldm;
}

  public void  setNsrsbm(String nsrsbm) {
    status.put("NSRSBM", nsrsbm);
    this.nsrsbm=nsrsbm;
}

  public void  setNsrmc(String nsrmc) {
    status.put("NSR_MC", nsrmc);
    this.nsrmc=nsrmc;
}

  public void  setPzzldm(String pzzldm) {
    status.put("PZZL_DM", pzzldm);
    this.pzzldm=pzzldm;
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

  public void  setSwglm(String swglm) {
	/** 数据库SWGLM为Long类型;
	 	类型转换 String  ->Long */
    if(null!=swglm&&swglm.length()>0 ) {
    	status.put("SWGLM", new Long(swglm));
    }
    this.swglm=swglm;
}

  public void  setTbrq(Calendar tbrq) {
    status.put("TB_RQ", tbrq);
    this.tbrq=tbrq;
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
  //tbrq  日期类转换 tbrq 的String 的类型 Set方法
  public void  setStr_tbrq(String str_tbrq ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_tbrq!="")
		this.setTbrq(DateUtils.parseFormatDate(str_tbrq,indexFomat));
	else
		this.setTbrq(null);
}

  //tbrq  日期类转换 tbrq 的String 的类型 Get方法
  public String  getStr_tbrq(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.tbrq)
		return DateUtils.toDateStr(this.tbrq,indexFomat);;
	return "";
}

////////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
}

