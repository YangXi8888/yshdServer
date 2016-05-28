package gov.jslt.taxevent.comm;
import com.ctp.core.bpo.BLOBObject;
import com.ctp.core.bpo.CssBaseVOWithLOB;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class WBfjfileVO extends CssBaseVOWithLOB {

public WBfjfileVO() {
super();
 hasXgrq=false ;
}

    //业务ID;
    private String bizid;

    //文件类别;
    private String wjlb;

    //文件名;
    private String wjm;

    //文件内容;
    private BLOBObject wjnr;

    //序号;
    private String xh;

    //选用标记 1 使用 0 删除;
    private String xybj;


 public String getBizid() {
   return bizid;
}

 public String getWjlb() {
   return wjlb;
}

 public String getWjm() {
   return wjm;
}

 public BLOBObject getWjnr() {
   return wjnr;
}

 public String getXh() {
   return xh;
}

 public String getXybj() {
   return xybj;
}


  public void  setBizid(String bizid) {
    status.put("BIZID", bizid);
    this.bizid=bizid;
}

  public void  setWjlb(String wjlb) {
    status.put("WJLB", wjlb);
    this.wjlb=wjlb;
}

  public void  setWjm(String wjm) {
    status.put("WJM", wjm);
    this.wjm=wjm;
}

  public void  setWjnr(BLOBObject wjnr) {
    blobBuffer.put("WJNR", wjnr);
    this.wjnr=wjnr;
}

  public void  setXh(String xh) {
    status.put("XH", xh);
    this.xh=xh;
}

  public void  setXybj(String xybj) {
    status.put("XY_BJ", xybj);
    this.xybj=xybj;
}

////////////////////////////////////////以下为【blob的字段转换为byte[]的部分】//////////////////////////////////////////////////// 
  //wjnr 的byte[]类型  wjnr  转换Blob类型 Set方法
  public void  setWjnrByte(byte[]  bytewjnr ){
	if (null!=bytewjnr){
 		BLOBObject blobObj = new BLOBObject();
 		blobObj.setContent(bytewjnr );
		this.setWjnr(blobObj);
	}else
		this.setWjnr(null);
}

  //wjnr  Blob类转换 wjnr Byte[] 的类型 Get方法
  public byte[]  getWjnrByte(){
	if (null!=this.wjnr)
		return wjnr.getContent();
	return null;
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

