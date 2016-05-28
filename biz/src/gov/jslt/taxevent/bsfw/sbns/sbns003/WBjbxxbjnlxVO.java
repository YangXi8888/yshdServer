package gov.jslt.taxevent.bsfw.sbns.sbns003;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class WBjbxxbjnlxVO extends CssBaseBpoVO {

public WBjbxxbjnlxVO() {
super();
}

    //类型代码;
    private String jnlxdm;

    //明细序号;
    private String mxxh;

    //凭证序号;
    private String pzxh;

    //修改机关代码;
    private String xgjgdm;

    //修改人员代码;
    private String xgrydm;


 public String getJnlxdm() {
   return jnlxdm;
}

 public String getMxxh() {
   return mxxh;
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


  public void  setJnlxdm(String jnlxdm) {
    status.put("JNLX_DM", jnlxdm);
    this.jnlxdm=jnlxdm;
}

  public void  setMxxh(String mxxh) {
	/** 数据库MX_XH为Long类型;
	 	类型转换 String  ->Long */
    if(null!=mxxh&&mxxh.length()>0 ) {
    	status.put("MX_XH", new Long(mxxh));
    }
    this.mxxh=mxxh;
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

