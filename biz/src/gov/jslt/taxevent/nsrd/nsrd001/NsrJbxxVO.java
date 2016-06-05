package gov.jslt.taxevent.nsrd.nsrd001;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class NsrJbxxVO extends CssBaseBpoVO {

public NsrJbxxVO() {
super();
}

    //注册类型;
    private String djzclxmc;

    //国标行业;
    private String gbhymc;

    //纳税人识别码;
    private String nsrsbm;

    //纳税人名称;
    private String nsrmc;

    //税务管理码;
    private String swglm;

    //主键无意义;
    private String uuid;

    //信用等级;
    private String xydj;

    //主表主键;
    private String zbuuid;

    //注册地址;
    private String zcdz;


 public String getDjzclxmc() {
   return djzclxmc;
}

 public String getGbhymc() {
   return gbhymc;
}

 public String getNsrsbm() {
   return nsrsbm;
}

 public String getNsrmc() {
   return nsrmc;
}

 public String getSwglm() {
   return swglm;
}

 public String getUuid() {
   return uuid;
}

 public String getXydj() {
   return xydj;
}

 public String getZbuuid() {
   return zbuuid;
}

 public String getZcdz() {
   return zcdz;
}


  public void  setDjzclxmc(String djzclxmc) {
    status.put("DJZCLX_MC", djzclxmc);
    this.djzclxmc=djzclxmc;
}

  public void  setGbhymc(String gbhymc) {
    status.put("GBHY_MC", gbhymc);
    this.gbhymc=gbhymc;
}

  public void  setNsrsbm(String nsrsbm) {
    status.put("NSRSBM", nsrsbm);
    this.nsrsbm=nsrsbm;
}

  public void  setNsrmc(String nsrmc) {
    status.put("NSR_MC", nsrmc);
    this.nsrmc=nsrmc;
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

  public void  setXydj(String xydj) {
    status.put("XYDJ", xydj);
    this.xydj=xydj;
}

  public void  setZbuuid(String zbuuid) {
    status.put("ZB_UUID", zbuuid);
    this.zbuuid=zbuuid;
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
////////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
}

