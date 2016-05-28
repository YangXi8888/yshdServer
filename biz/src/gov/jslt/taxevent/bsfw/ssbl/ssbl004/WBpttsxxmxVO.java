package gov.jslt.taxevent.bsfw.ssbl.ssbl004;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class WBpttsxxmxVO extends CssBaseBpoVO {

public WBpttsxxmxVO() {
super();
}

    //0 插入 1 更新 2 删除;
    private String czlx;

    //录入人员代码;
    private String lrrydm;

    //流水号;
    private String lsh;

    //明细序号;
    private String mxxh;

    //0 无变化 1 有变化;
    private String nrxgbj;

    //数据项新内容;
    private String sjxxnr;

    //数据项原内容;
    private String sjxynr;

    //推送数据项代码;
    private String tssjxdm;

    //修改人员代码;
    private String xgrydm;

    //修改条件;
    private String xgtj;


 public String getCzlx() {
   return czlx;
}

 public String getLrrydm() {
   return lrrydm;
}

 public String getLsh() {
   return lsh;
}

 public String getMxxh() {
   return mxxh;
}

 public String getNrxgbj() {
   return nrxgbj;
}

 public String getSjxxnr() {
   return sjxxnr;
}

 public String getSjxynr() {
   return sjxynr;
}

 public String getTssjxdm() {
   return tssjxdm;
}

 public String getXgrydm() {
   return xgrydm;
}

 public String getXgtj() {
   return xgtj;
}


  public void  setCzlx(String czlx) {
    status.put("CZ_LX", czlx);
    this.czlx=czlx;
}

  public void  setLrrydm(String lrrydm) {
    status.put("LRRY_DM", lrrydm);
    this.lrrydm=lrrydm;
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

  public void  setNrxgbj(String nrxgbj) {
    status.put("NRXG_BJ", nrxgbj);
    this.nrxgbj=nrxgbj;
}

  public void  setSjxxnr(String sjxxnr) {
    status.put("SJX_XNR", sjxxnr);
    this.sjxxnr=sjxxnr;
}

  public void  setSjxynr(String sjxynr) {
    status.put("SJX_YNR", sjxynr);
    this.sjxynr=sjxynr;
}

  public void  setTssjxdm(String tssjxdm) {
    status.put("TSSJX_DM", tssjxdm);
    this.tssjxdm=tssjxdm;
}

  public void  setXgrydm(String xgrydm) {
    status.put("XGRY_DM", xgrydm);
    this.xgrydm=xgrydm;
}

  public void  setXgtj(String xgtj) {
    status.put("XG_TJ", xgtj);
    this.xgtj=xgtj;
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

