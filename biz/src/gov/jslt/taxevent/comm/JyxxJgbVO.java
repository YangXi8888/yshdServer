package gov.jslt.taxevent.comm;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class JyxxJgbVO extends CssBaseBpoVO {

public JyxxJgbVO() {
super();
 hasLrrq=false ;
 hasXgrq=false ;
}

    //校验明细代码;
    private String jyztdm;

    //提示内容;
    private String jyztmc;

    //实际提示内容($分割);
    private String sjnr;


 public String getJyztdm() {
   return jyztdm;
}

 public String getJyztmc() {
   return jyztmc;
}

 public String getSjnr() {
   return sjnr;
}


  public void  setJyztdm(String jyztdm) {
    status.put("JYZT_DM", jyztdm);
    this.jyztdm=jyztdm;
}

  public void  setJyztmc(String jyztmc) {
    status.put("JYZT_MC", jyztmc);
    this.jyztmc=jyztmc;
}

  public void  setSjnr(String sjnr) {
    status.put("SJNR", sjnr);
    this.sjnr=sjnr;
}

////////////////////////////////////////以下为【自定义部分】//////////////////////////////////////////////////////////////
}

