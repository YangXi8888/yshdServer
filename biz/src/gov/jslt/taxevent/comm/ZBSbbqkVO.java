package gov.jslt.taxevent.comm;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class ZBSbbqkVO extends CssBaseBpoVO {

public ZBSbbqkVO() {
super();
}

    //服务机关;
    private String fwjgdm;

    //管理机关;
    private String gljgdm;

    //核算机关代码;
    private String hsjgdm;

    //稽查机关代码;
    private String jcjgdm;

    //检查机关;
    private String jiancjgdm;

    //凭证序号;
    private String pzxh;

    //迁移状态;
    private String qyzt;

    //0自然人1企业;
    private String sjly;

    //税款所属机关代码;
    private String skssjgdm;

    //税务管理码;
    private String swglm;

    //征收机关代码;
    private String zsjgdm;


 public String getFwjgdm() {
   return fwjgdm;
}

 public String getGljgdm() {
   return gljgdm;
}

 public String getHsjgdm() {
   return hsjgdm;
}

 public String getJcjgdm() {
   return jcjgdm;
}

 public String getJiancjgdm() {
   return jiancjgdm;
}

 public String getPzxh() {
   return pzxh;
}

 public String getQyzt() {
   return qyzt;
}

 public String getSjly() {
   return sjly;
}

 public String getSkssjgdm() {
   return skssjgdm;
}

 public String getSwglm() {
   return swglm;
}

 public String getZsjgdm() {
   return zsjgdm;
}


  public void  setFwjgdm(String fwjgdm) {
    status.put("FWJG_DM", fwjgdm);
    this.fwjgdm=fwjgdm;
}

  public void  setGljgdm(String gljgdm) {
    status.put("GLJG_DM", gljgdm);
    this.gljgdm=gljgdm;
}

  public void  setHsjgdm(String hsjgdm) {
    status.put("HSJG_DM", hsjgdm);
    this.hsjgdm=hsjgdm;
}

  public void  setJcjgdm(String jcjgdm) {
    status.put("JCJG_DM", jcjgdm);
    this.jcjgdm=jcjgdm;
}

  public void  setJiancjgdm(String jiancjgdm) {
    status.put("JIANCJG_DM", jiancjgdm);
    this.jiancjgdm=jiancjgdm;
}

  public void  setPzxh(String pzxh) {
    status.put("PZ_XH", pzxh);
    this.pzxh=pzxh;
}

  public void  setQyzt(String qyzt) {
    status.put("QYZT", qyzt);
    this.qyzt=qyzt;
}

  public void  setSjly(String sjly) {
    status.put("SJLY", sjly);
    this.sjly=sjly;
}

  public void  setSkssjgdm(String skssjgdm) {
    status.put("SKSSJG_DM", skssjgdm);
    this.skssjgdm=skssjgdm;
}

  public void  setSwglm(String swglm) {
	/** 数据库SWGLM为Long类型;
	 	类型转换 String  ->Long */
    if(null!=swglm&&swglm.length()>0 ) {
    	status.put("SWGLM", new Long(swglm));
    }
    this.swglm=swglm;
}

  public void  setZsjgdm(String zsjgdm) {
    status.put("ZSJG_DM", zsjgdm);
    this.zsjgdm=zsjgdm;
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

