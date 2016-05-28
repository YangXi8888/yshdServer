package gov.jslt.taxevent.bsfw.sbns.sbns003;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class WBCbsjVO extends CssBaseBpoVO {

public WBCbsjVO() {
super();
}

    //esb是否成功打催报的表的标记，'0' 不成功；'1' 成功;;
    private String esbcgbj;

    //服务机关;
    private String fwjgdm;

    //管理机关;
    private String gljgdm;

    //稽查机关;
    private String jcjgdm;

    //鉴定来源;
    private String jdlydm;

    //检查机关;
    private String jiancjgdm;

    //明细序号;
    private String mxxh;

    //凭证序号;
    private String pzxh;

    //税款所属机关;
    private String skssjgdm;

    //所属期起;
    private Calendar ssqq;

    //所属期止;
    private Calendar ssqz;

    //税务管理码;
    private String swglm;

    //修改人员;
    private String xgrydm;

    //征收机关;
    private String zsjgdm;

    //征收品目;
    private String zspmdm;

    //征收项目代码;
    private String zsxmdm;

    //ssqq 的String 的类型;
    private  String  str_ssqq;

    //ssqz 的String 的类型;
    private  String  str_ssqz;


 public String getEsbcgbj() {
   return esbcgbj;
}

 public String getFwjgdm() {
   return fwjgdm;
}

 public String getGljgdm() {
   return gljgdm;
}

 public String getJcjgdm() {
   return jcjgdm;
}

 public String getJdlydm() {
   return jdlydm;
}

 public String getJiancjgdm() {
   return jiancjgdm;
}

 public String getMxxh() {
   return mxxh;
}

 public String getPzxh() {
   return pzxh;
}

 public String getSkssjgdm() {
   return skssjgdm;
}

 public Calendar getSsqq() {
   return ssqq;
}

 public Calendar getSsqz() {
   return ssqz;
}

 public String getSwglm() {
   return swglm;
}

 public String getXgrydm() {
   return xgrydm;
}

 public String getZsjgdm() {
   return zsjgdm;
}

 public String getZspmdm() {
   return zspmdm;
}

 public String getZsxmdm() {
   return zsxmdm;
}


  public void  setEsbcgbj(String esbcgbj) {
    status.put("ESB_CGBJ", esbcgbj);
    this.esbcgbj=esbcgbj;
}

  public void  setFwjgdm(String fwjgdm) {
    status.put("FWJG_DM", fwjgdm);
    this.fwjgdm=fwjgdm;
}

  public void  setGljgdm(String gljgdm) {
    status.put("GLJG_DM", gljgdm);
    this.gljgdm=gljgdm;
}

  public void  setJcjgdm(String jcjgdm) {
    status.put("JCJG_DM", jcjgdm);
    this.jcjgdm=jcjgdm;
}

  public void  setJdlydm(String jdlydm) {
    status.put("JDLY_DM", jdlydm);
    this.jdlydm=jdlydm;
}

  public void  setJiancjgdm(String jiancjgdm) {
    status.put("JIANCJG_DM", jiancjgdm);
    this.jiancjgdm=jiancjgdm;
}

  public void  setMxxh(String mxxh) {
    status.put("MX_XH", mxxh);
    this.mxxh=mxxh;
}

  public void  setPzxh(String pzxh) {
    status.put("PZ_XH", pzxh);
    this.pzxh=pzxh;
}

  public void  setSkssjgdm(String skssjgdm) {
    status.put("SKSSJG_DM", skssjgdm);
    this.skssjgdm=skssjgdm;
}

  public void  setSsqq(Calendar ssqq) {
    status.put("SSQQ", ssqq);
    this.ssqq=ssqq;
}

  public void  setSsqz(Calendar ssqz) {
    status.put("SSQZ", ssqz);
    this.ssqz=ssqz;
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

  public void  setZsjgdm(String zsjgdm) {
    status.put("ZSJG_DM", zsjgdm);
    this.zsjgdm=zsjgdm;
}

  public void  setZspmdm(String zspmdm) {
    status.put("ZSPM_DM", zspmdm);
    this.zspmdm=zspmdm;
}

  public void  setZsxmdm(String zsxmdm) {
    status.put("ZSXM_DM", zsxmdm);
    this.zsxmdm=zsxmdm;
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
  //ssqq  日期类转换 ssqq 的String 的类型 Set方法
  public void  setStr_ssqq(String str_ssqq ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_ssqq!="")
		this.setSsqq(DateUtils.parseFormatDate(str_ssqq,indexFomat));
	else
		this.setSsqq(null);
}

  //ssqq  日期类转换 ssqq 的String 的类型 Get方法
  public String  getStr_ssqq(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.ssqq)
		return DateUtils.toDateStr(this.ssqq,indexFomat);;
	return "";
}

  //ssqz  日期类转换 ssqz 的String 的类型 Set方法
  public void  setStr_ssqz(String str_ssqz ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_ssqz!="")
		this.setSsqz(DateUtils.parseFormatDate(str_ssqz,indexFomat));
	else
		this.setSsqz(null);
}

  //ssqz  日期类转换 ssqz 的String 的类型 Get方法
  public String  getStr_ssqz(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.ssqz)
		return DateUtils.toDateStr(this.ssqz,indexFomat);;
	return "";
}

////////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
}

