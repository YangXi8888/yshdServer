package gov.jslt.taxevent.nsrd.nsrd001;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class NsrCwbbVO extends CssBaseBpoVO {

public NsrCwbbVO() {
super();
}

    //负债总额;
    private String fzze;

    //会计利润;
    private String kjlr;

    //纳税人识别码;
    private String nsrsbm;

    //纳税人名称;
    private String nsrmc;

    //所属年度;
    private String ssnd;

    //实收资本;
    private String sszb;

    //税务管理码;
    private String swglm;

    //主键无意义;
    private String uuid;

    //资本公积;
    private String zbgj;

    //主表主键;
    private String zbuuid;

    //资产总额;
    private String zcze;

    //主营业务成本;
    private String zyywcb;

    //主营业务收入;
    private String zyywsr;


 public String getFzze() {
   return fzze;
}

 public String getKjlr() {
   return kjlr;
}

 public String getNsrsbm() {
   return nsrsbm;
}

 public String getNsrmc() {
   return nsrmc;
}

 public String getSsnd() {
   return ssnd;
}

 public String getSszb() {
   return sszb;
}

 public String getSwglm() {
   return swglm;
}

 public String getUuid() {
   return uuid;
}

 public String getZbgj() {
   return zbgj;
}

 public String getZbuuid() {
   return zbuuid;
}

 public String getZcze() {
   return zcze;
}

 public String getZyywcb() {
   return zyywcb;
}

 public String getZyywsr() {
   return zyywsr;
}


  public void  setFzze(String fzze) {
	/** 数据库FZZE为Double类型;
		类型转换 String  -> Double*/
    if(null!=fzze&&fzze.length()>0 ) {
    	status.put("FZZE", new Double(fzze));
    }
    this.fzze=fzze;
}

  public void  setKjlr(String kjlr) {
	/** 数据库KJLR为Double类型;
		类型转换 String  -> Double*/
    if(null!=kjlr&&kjlr.length()>0 ) {
    	status.put("KJLR", new Double(kjlr));
    }
    this.kjlr=kjlr;
}

  public void  setNsrsbm(String nsrsbm) {
    status.put("NSRSBM", nsrsbm);
    this.nsrsbm=nsrsbm;
}

  public void  setNsrmc(String nsrmc) {
    status.put("NSR_MC", nsrmc);
    this.nsrmc=nsrmc;
}

  public void  setSsnd(String ssnd) {
    status.put("SSND", ssnd);
    this.ssnd=ssnd;
}

  public void  setSszb(String sszb) {
	/** 数据库SSZB为Double类型;
		类型转换 String  -> Double*/
    if(null!=sszb&&sszb.length()>0 ) {
    	status.put("SSZB", new Double(sszb));
    }
    this.sszb=sszb;
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

  public void  setZbgj(String zbgj) {
	/** 数据库ZBGJ为Double类型;
		类型转换 String  -> Double*/
    if(null!=zbgj&&zbgj.length()>0 ) {
    	status.put("ZBGJ", new Double(zbgj));
    }
    this.zbgj=zbgj;
}

  public void  setZbuuid(String zbuuid) {
    status.put("ZB_UUID", zbuuid);
    this.zbuuid=zbuuid;
}

  public void  setZcze(String zcze) {
	/** 数据库ZCZE为Double类型;
		类型转换 String  -> Double*/
    if(null!=zcze&&zcze.length()>0 ) {
    	status.put("ZCZE", new Double(zcze));
    }
    this.zcze=zcze;
}

  public void  setZyywcb(String zyywcb) {
	/** 数据库ZYYWCB为Double类型;
		类型转换 String  -> Double*/
    if(null!=zyywcb&&zyywcb.length()>0 ) {
    	status.put("ZYYWCB", new Double(zyywcb));
    }
    this.zyywcb=zyywcb;
}

  public void  setZyywsr(String zyywsr) {
	/** 数据库ZYYWSR为Double类型;
		类型转换 String  -> Double*/
    if(null!=zyywsr&&zyywsr.length()>0 ) {
    	status.put("ZYYWSR", new Double(zyywsr));
    }
    this.zyywsr=zyywsr;
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

