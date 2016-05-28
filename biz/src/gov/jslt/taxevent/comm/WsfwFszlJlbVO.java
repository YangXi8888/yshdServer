package gov.jslt.taxevent.comm;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class WsfwFszlJlbVO extends CssBaseBpoVO {

public WsfwFszlJlbVO() {
super();
}

    //备注;
    private String bz;

    //附送资料代码;
    private String fbzldm;

    //附送资料名称;
    private String fbzlmc;

    //流水号;
    private String lsh;

    //是否必报;
    private String sfbb;

    //是否公共;
    private String sfgg;

    //税务管理码;
    private String swglm;

    //序号;
    private String xh;

    //资料来源t_dm_dj_zjzllx;
    private String zlly;


 public String getBz() {
   return bz;
}

 public String getFbzldm() {
   return fbzldm;
}

 public String getFbzlmc() {
   return fbzlmc;
}

 public String getLsh() {
   return lsh;
}

 public String getSfbb() {
   return sfbb;
}

 public String getSfgg() {
   return sfgg;
}

 public String getSwglm() {
   return swglm;
}

 public String getXh() {
   return xh;
}

 public String getZlly() {
   return zlly;
}


  public void  setBz(String bz) {
    status.put("BZ", bz);
    this.bz=bz;
}

  public void  setFbzldm(String fbzldm) {
    status.put("FBZL_DM", fbzldm);
    this.fbzldm=fbzldm;
}

  public void  setFbzlmc(String fbzlmc) {
    status.put("FBZL_MC", fbzlmc);
    this.fbzlmc=fbzlmc;
}

  public void  setLsh(String lsh) {
    status.put("LSH", lsh);
    this.lsh=lsh;
}

  public void  setSfbb(String sfbb) {
    status.put("SFBB", sfbb);
    this.sfbb=sfbb;
}

  public void  setSfgg(String sfgg) {
    status.put("SFGG", sfgg);
    this.sfgg=sfgg;
}

  public void  setSwglm(String swglm) {
	/** 数据库SWGLM为Long类型;
	 	类型转换 String  ->Long */
    if(null!=swglm&&swglm.length()>0 ) {
    	status.put("SWGLM", new Long(swglm));
    }
    this.swglm=swglm;
}

  public void  setXh(String xh) {
    status.put("XH", xh);
    this.xh=xh;
}

  public void  setZlly(String zlly) {
    status.put("ZLLY", zlly);
    this.zlly=zlly;
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

