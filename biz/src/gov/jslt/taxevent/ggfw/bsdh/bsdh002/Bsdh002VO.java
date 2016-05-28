package gov.jslt.taxevent.ggfw.bsdh.bsdh002;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class Bsdh002VO extends CssBaseBpoVO {

public Bsdh002VO() {
super();
 hasLrrq=false ;
 hasXgrq=false ;
}

    //1税务所2契税所;
    private String bj;

    //办税时间起;
    private String bssjq;

    //办税业务;
    private String bsyw;

    //电话;
    private String dh;

    //地市代码;
    private String dsdm;

    //地市税务机关;
    private String dsdmswjg;

    //地址;
    private String dz;

    //;
    private String id;

    //名称简;
    private String mcj;

    //;
    private String pid;


    //邮编;
    private String yb;


 public String getBj() {
   return bj;
}

 public String getBssjq() {
   return bssjq;
}

 public String getBsyw() {
   return bsyw;
}

 public String getDh() {
   return dh;
}

 public String getDsdm() {
   return dsdm;
}

 public String getDsdmswjg() {
   return dsdmswjg;
}

 public String getDz() {
   return dz;
}

 public String getId() {
   return id;
}

 public String getMcj() {
   return mcj;
}

 public String getPid() {
   return pid;
}



 public String getYb() {
   return yb;
}


  public void  setBj(String bj) {
    status.put("BJ", bj);
    this.bj=bj;
}

  public void  setBssjq(String bssjq) {
    status.put("BSSJQ", bssjq);
    this.bssjq=bssjq;
}

  public void  setBsyw(String bsyw) {
    status.put("BSYW", bsyw);
    this.bsyw=bsyw;
}

  public void  setDh(String dh) {
    status.put("DH", dh);
    this.dh=dh;
}

  public void  setDsdm(String dsdm) {
    status.put("DS_DM", dsdm);
    this.dsdm=dsdm;
}

  public void  setDsdmswjg(String dsdmswjg) {
    status.put("DS_DM_SWJG", dsdmswjg);
    this.dsdmswjg=dsdmswjg;
}

  public void  setDz(String dz) {
    status.put("DZ", dz);
    this.dz=dz;
}

  public void  setId(String id) {
	/** 数据库ID为Long类型;
	 	类型转换 String  ->Long */
    if(null!=id&&id.length()>0 ) {
    	status.put("ID", new Long(id));
    }
    this.id=id;
}

  public void  setMcj(String mcj) {
    status.put("MC_J", mcj);
    this.mcj=mcj;
}

  public void  setPid(String pid) {
	/** 数据库PID为Long类型;
	 	类型转换 String  ->Long */
    if(null!=pid&&pid.length()>0 ) {
    	status.put("PID", new Long(pid));
    }
    this.pid=pid;
}



  public void  setYb(String yb) {
    status.put("YB", yb);
    this.yb=yb;
}

////////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
}

