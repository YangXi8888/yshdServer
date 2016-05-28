package gov.jslt.taxevent.bsfw.ssbl.ssbl003;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class WBjmcztdsqmxVO extends CssBaseBpoVO {

public WBjmcztdsqmxVO() {
super();
}

    //单位税额;
    private String dwse;

    //管理机关;
    private String gljgdm;

    //申请减免类型;
    private String jmlx;

    //减免类型;
    private String jmlxdm;

    //减免日期起;
    private Calendar jmrqq;

    //减免日期止;
    private Calendar jmrqz;

    //流水号;
    private String lsh;

    //明细序号;
    private String mxxh;

    //年应纳税额;
    private String nynse;

    //申请减免面积;
    private String sqjmmj;

    //申请减免税额;
    private String sqjmse;

    //应税面积;
    private String ysmj;

    //政策依据;
    private String zcyj;

    //政策依据细目;
    private String zcyjxm;

    //占地面积;
    private String zdmj;

    //征收品目;
    private String zspmdm;

    //征收项目;
    private String zsxmdm;

    //jmrqq 的String 的类型;
    private  String  str_jmrqq;

    //jmrqz 的String 的类型;
    private  String  str_jmrqz;


 public String getDwse() {
   return dwse;
}

 public String getGljgdm() {
   return gljgdm;
}

 public String getJmlx() {
   return jmlx;
}

 public String getJmlxdm() {
   return jmlxdm;
}

 public Calendar getJmrqq() {
   return jmrqq;
}

 public Calendar getJmrqz() {
   return jmrqz;
}

 public String getLsh() {
   return lsh;
}

 public String getMxxh() {
   return mxxh;
}

 public String getNynse() {
   return nynse;
}

 public String getSqjmmj() {
   return sqjmmj;
}

 public String getSqjmse() {
   return sqjmse;
}

 public String getYsmj() {
   return ysmj;
}

 public String getZcyj() {
   return zcyj;
}

 public String getZcyjxm() {
   return zcyjxm;
}

 public String getZdmj() {
   return zdmj;
}

 public String getZspmdm() {
   return zspmdm;
}

 public String getZsxmdm() {
   return zsxmdm;
}


  public void  setDwse(String dwse) {
	/** 数据库DWSE为Double类型;
		类型转换 String  -> Double*/
    if(null!=dwse&&dwse.length()>0 ) {
    	status.put("DWSE", new Double(dwse));
    }
    this.dwse=dwse;
}

  public void  setGljgdm(String gljgdm) {
    status.put("GLJG_DM", gljgdm);
    this.gljgdm=gljgdm;
}

  public void  setJmlx(String jmlx) {
    status.put("JMLX", jmlx);
    this.jmlx=jmlx;
}

  public void  setJmlxdm(String jmlxdm) {
    status.put("JMLX_DM", jmlxdm);
    this.jmlxdm=jmlxdm;
}

  public void  setJmrqq(Calendar jmrqq) {
    status.put("JMRQQ", jmrqq);
    this.jmrqq=jmrqq;
}

  public void  setJmrqz(Calendar jmrqz) {
    status.put("JMRQZ", jmrqz);
    this.jmrqz=jmrqz;
}

  public void  setLsh(String lsh) {
    status.put("LSH", lsh);
    this.lsh=lsh;
}

  public void  setMxxh(String mxxh) {
	/** 数据库MX_XH为Long类型;
	 	类型转换 String  ->Long */
    if(null!=mxxh&&mxxh.length()>0 ) {
    	status.put("MX_XH", new Long(mxxh));
    }
    this.mxxh=mxxh;
}

  public void  setNynse(String nynse) {
	/** 数据库NYNSE为Double类型;
		类型转换 String  -> Double*/
    if(null!=nynse&&nynse.length()>0 ) {
    	status.put("NYNSE", new Double(nynse));
    }
    this.nynse=nynse;
}

  public void  setSqjmmj(String sqjmmj) {
	/** 数据库SQJMMJ为Double类型;
		类型转换 String  -> Double*/
    if(null!=sqjmmj&&sqjmmj.length()>0 ) {
    	status.put("SQJMMJ", new Double(sqjmmj));
    }
    this.sqjmmj=sqjmmj;
}

  public void  setSqjmse(String sqjmse) {
	/** 数据库SQJMSE为Double类型;
		类型转换 String  -> Double*/
    if(null!=sqjmse&&sqjmse.length()>0 ) {
    	status.put("SQJMSE", new Double(sqjmse));
    }
    this.sqjmse=sqjmse;
}

  public void  setYsmj(String ysmj) {
	/** 数据库YSMJ为Double类型;
		类型转换 String  -> Double*/
    if(null!=ysmj&&ysmj.length()>0 ) {
    	status.put("YSMJ", new Double(ysmj));
    }
    this.ysmj=ysmj;
}

  public void  setZcyj(String zcyj) {
    status.put("ZCYJ", zcyj);
    this.zcyj=zcyj;
}

  public void  setZcyjxm(String zcyjxm) {
    status.put("ZCYJXM", zcyjxm);
    this.zcyjxm=zcyjxm;
}

  public void  setZdmj(String zdmj) {
	/** 数据库ZDMJ为Double类型;
		类型转换 String  -> Double*/
    if(null!=zdmj&&zdmj.length()>0 ) {
    	status.put("ZDMJ", new Double(zdmj));
    }
    this.zdmj=zdmj;
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
  //jmrqq  日期类转换 jmrqq 的String 的类型 Set方法
  public void  setStr_jmrqq(String str_jmrqq ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_jmrqq!="")
		this.setJmrqq(DateUtils.parseFormatDate(str_jmrqq,indexFomat));
	else
		this.setJmrqq(null);
}

  //jmrqq  日期类转换 jmrqq 的String 的类型 Get方法
  public String  getStr_jmrqq(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.jmrqq)
		return DateUtils.toDateStr(this.jmrqq,indexFomat);;
	return "";
}

  //jmrqz  日期类转换 jmrqz 的String 的类型 Set方法
  public void  setStr_jmrqz(String str_jmrqz ){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (str_jmrqz!="")
		this.setJmrqz(DateUtils.parseFormatDate(str_jmrqz,indexFomat));
	else
		this.setJmrqz(null);
}

  //jmrqz  日期类转换 jmrqz 的String 的类型 Get方法
  public String  getStr_jmrqz(){
	//indexFomat 表示日期的格式类型；默认是第3种，可以修改为你需要的格式
	int indexFomat=3	;
	if (null!=this.jmrqz)
		return DateUtils.toDateStr(this.jmrqz,indexFomat);;
	return "";
}

////////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
}

