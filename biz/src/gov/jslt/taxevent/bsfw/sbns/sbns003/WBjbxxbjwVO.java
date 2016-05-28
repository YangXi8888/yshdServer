package gov.jslt.taxevent.bsfw.sbns.sbns003;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class WBjbxxbjwVO extends CssBaseBpoVO {

public WBjbxxbjwVO() {
super();
}

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

    //联系地类型(1户籍所在地2经常居住地);
    private String lxdlx;

    //申报凭证序号;
    private String pzxh;

    //修改机关;
    private String xgjgdm;

    //修改人员;
    private String xgrydm;

    //邮政编码;
    private String yzdm;


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

 public String getLxdlx() {
   return lxdlx;
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

 public String getYzdm() {
   return yzdm;
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

  public void  setLxdlx(String lxdlx) {
    status.put("LXD_LX", lxdlx);
    this.lxdlx=lxdlx;
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

  public void  setYzdm(String yzdm) {
    status.put("YZDM", yzdm);
    this.yzdm=yzdm;
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

