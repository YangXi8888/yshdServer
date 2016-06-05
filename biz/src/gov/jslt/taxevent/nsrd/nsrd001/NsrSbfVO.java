package gov.jslt.taxevent.nsrd.nsrd001;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class NsrSbfVO extends CssBaseBpoVO {

public NsrSbfVO() {
super();
}

    //纳税人识别码;
    private String nsrsbm;

    //纳税人名称;
    private String nsrmc;

    //实缴金额;
    private String sjje;

    //所属年度;
    private String ssnd;

    //税务管理码;
    private String swglm;

    //主键无意义;
    private String uuid;

    //险种;
    private String xz;

    //主表主键;
    private String zbuuid;


 public String getNsrsbm() {
   return nsrsbm;
}

 public String getNsrmc() {
   return nsrmc;
}

 public String getSjje() {
   return sjje;
}

 public String getSsnd() {
   return ssnd;
}

 public String getSwglm() {
   return swglm;
}

 public String getUuid() {
   return uuid;
}

 public String getXz() {
   return xz;
}

 public String getZbuuid() {
   return zbuuid;
}


  public void  setNsrsbm(String nsrsbm) {
    status.put("NSRSBM", nsrsbm);
    this.nsrsbm=nsrsbm;
}

  public void  setNsrmc(String nsrmc) {
    status.put("NSR_MC", nsrmc);
    this.nsrmc=nsrmc;
}

  public void  setSjje(String sjje) {
	/** 数据库SJJE为Double类型;
		类型转换 String  -> Double*/
    if(null!=sjje&&sjje.length()>0 ) {
    	status.put("SJJE", new Double(sjje));
    }
    this.sjje=sjje;
}

  public void  setSsnd(String ssnd) {
    status.put("SSND", ssnd);
    this.ssnd=ssnd;
}

  public void  setSwglm(String swglm) {
	/** 数据库SWGLM为Long类型;
	 	类型转换 String  ->Long */
    if(null!=swglm&&swglm.length()>0 ) {
    	status.put("SWGLM", new Long(swglm));
    }
    this.swglm=swglm;
}

  public void  setUuid(String uuid) {
    status.put("UUID", uuid);
    this.uuid=uuid;
}

  public void  setXz(String xz) {
    status.put("XZ", xz);
    this.xz=xz;
}

  public void  setZbuuid(String zbuuid) {
    status.put("ZB_UUID", zbuuid);
    this.zbuuid=zbuuid;
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

