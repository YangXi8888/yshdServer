package gov.jslt.taxevent.nsrd.nsrd001;
import com.ctp.core.bpo.CLOBObject;
import com.ctp.core.bpo.CssBaseVOWithLOB;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class NsrXzcfVO extends CssBaseVOWithLOB {

public NsrXzcfVO() {
super();
}

    //处罚决定文号;
    private String cfjdwh;

    //处罚金额;
    private String cfje;

    //注册地址;
    private String cfrq;

    //处罚事由;
    private CLOBObject cfsy;

    //纳税人识别码;
    private String nsrsbm;

    //纳税人名称;
    private String nsrmc;

    //税务管理码;
    private String swglm;

    //主键无意义;
    private String uuid;

    //主表主键;
    private String zbuuid;

    //执行完成日期;
    private String zxwcrq;


 public String getCfjdwh() {
   return cfjdwh;
}

 public String getCfje() {
   return cfje;
}

 public String getCfrq() {
   return cfrq;
}

 public CLOBObject getCfsy() {
   return cfsy;
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

 public String getZbuuid() {
   return zbuuid;
}

 public String getZxwcrq() {
   return zxwcrq;
}


  public void  setCfjdwh(String cfjdwh) {
    status.put("CFJDWH", cfjdwh);
    this.cfjdwh=cfjdwh;
}

  public void  setCfje(String cfje) {
	/** 数据库CFJE为Double类型;
		类型转换 String  -> Double*/
    if(null!=cfje&&cfje.length()>0 ) {
    	status.put("CFJE", new Double(cfje));
    }
    this.cfje=cfje;
}

  public void  setCfrq(String cfrq) {
    status.put("CFRQ", cfrq);
    this.cfrq=cfrq;
}

  public void  setCfsy(CLOBObject cfsy) {
    clobBuffer.put("CFSY", cfsy);
    this.cfsy=cfsy;
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

  public void  setZbuuid(String zbuuid) {
    status.put("ZB_UUID", zbuuid);
    this.zbuuid=zbuuid;
}

  public void  setZxwcrq(String zxwcrq) {
    status.put("ZXWCRQ", zxwcrq);
    this.zxwcrq=zxwcrq;
}

////////////////////////////////////////以下为【clob的字段转换为String的部分】//////////////////////////////////////////////////// 
  //cfsy 的String类型  cfsy  转换Clob类型 Set方法
  public void  setCfsyStr(String str_cfsy ){
	if (str_cfsy!=""){
 		CLOBObject addclob=  new CLOBObject();
 		addclob.setContent(str_cfsy.toCharArray() );
		this.setCfsy(addclob);
	}else
		this.setCfsy(null);
}

  //cfsy  Clob类转换 cfsy 的String 的类型 Get方法
  public String  getCfsyStr(){
	if (null!=this.cfsy)
		return cfsy.toString();
	return "";
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

