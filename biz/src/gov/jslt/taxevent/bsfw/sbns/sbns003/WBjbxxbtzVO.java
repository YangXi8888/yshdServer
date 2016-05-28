package gov.jslt.taxevent.bsfw.sbns.sbns003;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class WBjbxxbtzVO extends CssBaseBpoVO {

public WBjbxxbtzVO() {
super();
}

    //被投资单位登记注册类型;
    private String btzdwdjzclxdm;

    //被投资单位地址;
    private String btzdwdz;

    //被投资单位行业;
    private String btzdwhydm;

    //被投资单位名称;
    private String btzdwmc;

    //被投资单位所得税征收方式1查账征收2核定征收;
    private String btzdwsdszsfsdm;

    //被投资单位扣缴义务人编码;
    private String btzdwswglm;

    //被投资单位邮政编码;
    private String btzdwyzbm;

    //个人股本（投资）额;
    private String grtzje;

    //公司股本（投资）总额;
    private String gstzje;

    //申报凭证序号;
    private String pzxh;

    //修改机关;
    private String xgjgdm;

    //修改人员;
    private String xgrydm;


 public String getBtzdwdjzclxdm() {
   return btzdwdjzclxdm;
}

 public String getBtzdwdz() {
   return btzdwdz;
}

 public String getBtzdwhydm() {
   return btzdwhydm;
}

 public String getBtzdwmc() {
   return btzdwmc;
}

 public String getBtzdwsdszsfsdm() {
   return btzdwsdszsfsdm;
}

 public String getBtzdwswglm() {
   return btzdwswglm;
}

 public String getBtzdwyzbm() {
   return btzdwyzbm;
}

 public String getGrtzje() {
   return grtzje;
}

 public String getGstzje() {
   return gstzje;
}

 public String getPzxh() {
   return pzxh;
}

 public String getXgjgdm() {
   return xgjgdm;
}

 public String getXgrydm() {
   return xgrydm;
}


  public void  setBtzdwdjzclxdm(String btzdwdjzclxdm) {
    status.put("BTZDW_DJZCLXDM", btzdwdjzclxdm);
    this.btzdwdjzclxdm=btzdwdjzclxdm;
}

  public void  setBtzdwdz(String btzdwdz) {
    status.put("BTZDW_DZ", btzdwdz);
    this.btzdwdz=btzdwdz;
}

  public void  setBtzdwhydm(String btzdwhydm) {
    status.put("BTZDW_HYDM", btzdwhydm);
    this.btzdwhydm=btzdwhydm;
}

  public void  setBtzdwmc(String btzdwmc) {
    status.put("BTZDW_MC", btzdwmc);
    this.btzdwmc=btzdwmc;
}

  public void  setBtzdwsdszsfsdm(String btzdwsdszsfsdm) {
    status.put("BTZDW_SDSZSFSDM", btzdwsdszsfsdm);
    this.btzdwsdszsfsdm=btzdwsdszsfsdm;
}

  public void  setBtzdwswglm(String btzdwswglm) {
    status.put("BTZDW_SWGLM", btzdwswglm);
    this.btzdwswglm=btzdwswglm;
}

  public void  setBtzdwyzbm(String btzdwyzbm) {
    status.put("BTZDW_YZBM", btzdwyzbm);
    this.btzdwyzbm=btzdwyzbm;
}

  public void  setGrtzje(String grtzje) {
	/** 数据库GRTZ_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=grtzje&&grtzje.length()>0 ) {
    	status.put("GRTZ_JE", new Double(grtzje));
    }
    this.grtzje=grtzje;
}

  public void  setGstzje(String gstzje) {
	/** 数据库GSTZ_JE为Double类型;
		类型转换 String  -> Double*/
    if(null!=gstzje&&gstzje.length()>0 ) {
    	status.put("GSTZ_JE", new Double(gstzje));
    }
    this.gstzje=gstzje;
}

  public void  setPzxh(String pzxh) {
    status.put("PZ_XH", pzxh);
    this.pzxh=pzxh;
}

  public void  setXgjgdm(String xgjgdm) {
    status.put("XGJG_DM", xgjgdm);
    this.xgjgdm=xgjgdm;
}

  public void  setXgrydm(String xgrydm) {
    status.put("XGRY_DM", xgrydm);
    this.xgrydm=xgrydm;
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
////////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
}

