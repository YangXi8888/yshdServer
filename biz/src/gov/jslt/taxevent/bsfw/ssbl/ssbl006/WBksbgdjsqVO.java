package gov.jslt.taxevent.bsfw.ssbl.ssbl006;
import com.ctp.core.bpo.CssBaseBpoVO;
import java.util.Calendar;
import com.ctp.core.commutils.DateUtils;
import java.util.ArrayList;

public class WBksbgdjsqVO extends CssBaseBpoVO {

public WBksbgdjsqVO() {
super();
}

    //新办税人员电话;
    private String bsydhx;

    //新办税人员电话2;
    private String bsydhx2;

    //新办税人员电话3;
    private String bsydhx3;

    //原办税人员电话;
    private String bsydhy;

    //原办税人员电话2;
    private String bsydhy2;

    //原办税人员电话3;
    private String bsydhy3;

    //新办税人员电子邮箱E-mail;
    private String bsyemailx;

    //新办税人员电子邮箱E-mail2;
    private String bsyemailx2;

    //新办税人员3电子邮箱E-mail;
    private String bsyemailx3;

    //原办税人员电子邮箱E-mail;
    private String bsyemaily;

    //原办税人员电子邮箱E-mail2;
    private String bsyemaily2;

    //原办税人员电子邮箱E-mail3;
    private String bsyemaily3;

    //新办税人员身份证号;
    private String bsysfzhx;

    //新办税人员身份证号2;
    private String bsysfzhx2;

    //新办税人员身份证号3;
    private String bsysfzhx3;

    //原办税人员身份证号;
    private String bsysfzhy;

    //原办税人员身份证号2;
    private String bsysfzhy2;

    //原办税人员身份证号3;
    private String bsysfzhy3;

    //新办税人员手机;
    private String bsysjx;

    //新办税人员手机2;
    private String bsysjx2;

    //新办税人员手机3;
    private String bsysjx3;

    //原办税人员手机;
    private String bsysjy;

    //原办税人员手机2;
    private String bsysjy2;

    //原办税人员手机3;
    private String bsysjy3;

    //新办税人员姓名;
    private String bsyxmx;

    //新办税人员姓名2;
    private String bsyxmx2;

    //新办税人员姓名3;
    private String bsyxmx3;

    //原办税人员姓名;
    private String bsyxmy;

    //原办税人员姓名2;
    private String bsyxmy2;

    //原办税人员姓名3;
    private String bsyxmy3;

    //新办税人员证件种类;
    private String bsyzjzlx;

    //新办税人员证件种类2;
    private String bsyzjzlx2;

    //新办税人员3证件种类;
    private String bsyzjzlx3;

    //原办税人员证件种类;
    private String bsyzjzly;

    //原办税人员证件种类2;
    private String bsyzjzly2;

    //原办税人员证件种类3;
    private String bsyzjzly3;

    //新办税人员职务;
    private String bsyzwx;

    //新办税人员职务2;
    private String bsyzwx2;

    //新办税人员3职务;
    private String bsyzwx3;

    //原办税人员职务;
    private String bsyzwy;

    //原办税人员职务2;
    private String bsyzwy2;

    //原办税人员职务3;
    private String bsyzwy3;

    //新财务负责人电话;
    private String cffzrdhx;

    //新财务负责人电话2;
    private String cffzrdhx2;

    //原财务负责人电话;
    private String cffzrdhy;

    //原财务负责人电话2;
    private String cffzrdhy2;

    //新财务负责人电子邮箱E-mail;
    private String cffzremailx;

    //新财务负责人电子邮箱E-mail2;
    private String cffzremailx2;

    //原财务负责人电子邮箱E-mail;
    private String cffzremaily;

    //原财务负责人电子邮箱E-mail2;
    private String cffzremaily2;

    //新财务负责人身份证号;
    private String cffzrsfzhx;

    //新财务负责人身份证号2;
    private String cffzrsfzhx2;

    //原财务负责人身份证号;
    private String cffzrsfzhy;

    //原财务负责人身份证号2;
    private String cffzrsfzhy2;

    //新财务负责人手机;
    private String cffzrsjx;

    //新财务负责人手机2;
    private String cffzrsjx2;

    //原财务负责人手机;
    private String cffzrsjy;

    //原财务负责人手机2;
    private String cffzrsjy2;

    //新财务负责人姓名;
    private String cffzrxmx;

    //新财务负责人姓名2;
    private String cffzrxmx2;

    //原财务负责人姓名;
    private String cffzrxmy;

    //原财务负责人姓名2;
    private String cffzrxmy2;

    //新财务负责人证件种类;
    private String cffzrzjzlx;

    //新财务负责人证件种类2;
    private String cffzrzjzlx2;

    //原财务负责人证件种类;
    private String cffzrzjzly;

    //原财务负责人证件种类2;
    private String cffzrzjzly2;

    //新财务负责人职务;
    private String cffzrzwx;

    //新财务负责人职务2;
    private String cffzrzwx2;

    //原财务负责人职务;
    private String cffzrzwy;

    //原财务负责人职务2;
    private String cffzrzwy2;

    //新公司网址;
    private String gswzx;

    //原公司网址;
    private String gswzy;

    //经办人姓名;
    private String jbrxm;

    //新经营地联系电话;
    private String jydzlxdhx;

    //原经营地联系电话;
    private String jydzlxdhy;

    //新经营地址;
    private String jydzx;

    //原经营地址;
    private String jydzy;

    //新经营地邮政编码;
    private String jydzybx;

    //原经营地邮政编码;
    private String jydzyby;

    //新会计电算化软件名称;
    private String kjdshrjx;

    //原会计电算化软件名称;
    private String kjdshrjy;

    //新适用会计制度;
    private String kjzlx;

    //原适用会计制度;
    private String kjzly;

    //流水号;
    private String lsh;

    //纳税人名称;
    private String nsrmc;

    //新企业负责人电子邮箱E-mail;
    private String qyfzremailx;

    //原企业负责人电子邮箱E-mail;
    private String qyfzremaily;

    //新企业负责人固定电话;
    private String qyfzrgddhx;

    //原企业负责人固定电话;
    private String qyfzrgddhy;

    //新企业负责人姓名;
    private String qyfzrxmx;

    //原企业负责人姓名;
    private String qyfzrxmy;

    //新企业负责人移动电话;
    private String qyfzryddhx;

    //原企业负责人移动电话;
    private String qyfzryddhy;

    //新企业负责人证件号码;
    private String qyfzrzjhmx;

    //原企业负责人证件号码;
    private String qyfzrzjhmy;

    //新企业负责人证件种类;
    private String qyfzrzjzlx;

    //原企业负责人证件种类;
    private String qyfzrzjzly;

    //新企业负责人职务;
    private String qyfzrzwx;

    //原企业负责人职务;
    private String qyfzrzwy;

    //申请理由;
    private String sqly;

    //税务管理码;
    private String swglm;


 public String getBsydhx() {
   return bsydhx;
}

 public String getBsydhx2() {
   return bsydhx2;
}

 public String getBsydhx3() {
   return bsydhx3;
}

 public String getBsydhy() {
   return bsydhy;
}

 public String getBsydhy2() {
   return bsydhy2;
}

 public String getBsydhy3() {
   return bsydhy3;
}

 public String getBsyemailx() {
   return bsyemailx;
}

 public String getBsyemailx2() {
   return bsyemailx2;
}

 public String getBsyemailx3() {
   return bsyemailx3;
}

 public String getBsyemaily() {
   return bsyemaily;
}

 public String getBsyemaily2() {
   return bsyemaily2;
}

 public String getBsyemaily3() {
   return bsyemaily3;
}

 public String getBsysfzhx() {
   return bsysfzhx;
}

 public String getBsysfzhx2() {
   return bsysfzhx2;
}

 public String getBsysfzhx3() {
   return bsysfzhx3;
}

 public String getBsysfzhy() {
   return bsysfzhy;
}

 public String getBsysfzhy2() {
   return bsysfzhy2;
}

 public String getBsysfzhy3() {
   return bsysfzhy3;
}

 public String getBsysjx() {
   return bsysjx;
}

 public String getBsysjx2() {
   return bsysjx2;
}

 public String getBsysjx3() {
   return bsysjx3;
}

 public String getBsysjy() {
   return bsysjy;
}

 public String getBsysjy2() {
   return bsysjy2;
}

 public String getBsysjy3() {
   return bsysjy3;
}

 public String getBsyxmx() {
   return bsyxmx;
}

 public String getBsyxmx2() {
   return bsyxmx2;
}

 public String getBsyxmx3() {
   return bsyxmx3;
}

 public String getBsyxmy() {
   return bsyxmy;
}

 public String getBsyxmy2() {
   return bsyxmy2;
}

 public String getBsyxmy3() {
   return bsyxmy3;
}

 public String getBsyzjzlx() {
   return bsyzjzlx;
}

 public String getBsyzjzlx2() {
   return bsyzjzlx2;
}

 public String getBsyzjzlx3() {
   return bsyzjzlx3;
}

 public String getBsyzjzly() {
   return bsyzjzly;
}

 public String getBsyzjzly2() {
   return bsyzjzly2;
}

 public String getBsyzjzly3() {
   return bsyzjzly3;
}

 public String getBsyzwx() {
   return bsyzwx;
}

 public String getBsyzwx2() {
   return bsyzwx2;
}

 public String getBsyzwx3() {
   return bsyzwx3;
}

 public String getBsyzwy() {
   return bsyzwy;
}

 public String getBsyzwy2() {
   return bsyzwy2;
}

 public String getBsyzwy3() {
   return bsyzwy3;
}

 public String getCffzrdhx() {
   return cffzrdhx;
}

 public String getCffzrdhx2() {
   return cffzrdhx2;
}

 public String getCffzrdhy() {
   return cffzrdhy;
}

 public String getCffzrdhy2() {
   return cffzrdhy2;
}

 public String getCffzremailx() {
   return cffzremailx;
}

 public String getCffzremailx2() {
   return cffzremailx2;
}

 public String getCffzremaily() {
   return cffzremaily;
}

 public String getCffzremaily2() {
   return cffzremaily2;
}

 public String getCffzrsfzhx() {
   return cffzrsfzhx;
}

 public String getCffzrsfzhx2() {
   return cffzrsfzhx2;
}

 public String getCffzrsfzhy() {
   return cffzrsfzhy;
}

 public String getCffzrsfzhy2() {
   return cffzrsfzhy2;
}

 public String getCffzrsjx() {
   return cffzrsjx;
}

 public String getCffzrsjx2() {
   return cffzrsjx2;
}

 public String getCffzrsjy() {
   return cffzrsjy;
}

 public String getCffzrsjy2() {
   return cffzrsjy2;
}

 public String getCffzrxmx() {
   return cffzrxmx;
}

 public String getCffzrxmx2() {
   return cffzrxmx2;
}

 public String getCffzrxmy() {
   return cffzrxmy;
}

 public String getCffzrxmy2() {
   return cffzrxmy2;
}

 public String getCffzrzjzlx() {
   return cffzrzjzlx;
}

 public String getCffzrzjzlx2() {
   return cffzrzjzlx2;
}

 public String getCffzrzjzly() {
   return cffzrzjzly;
}

 public String getCffzrzjzly2() {
   return cffzrzjzly2;
}

 public String getCffzrzwx() {
   return cffzrzwx;
}

 public String getCffzrzwx2() {
   return cffzrzwx2;
}

 public String getCffzrzwy() {
   return cffzrzwy;
}

 public String getCffzrzwy2() {
   return cffzrzwy2;
}

 public String getGswzx() {
   return gswzx;
}

 public String getGswzy() {
   return gswzy;
}

 public String getJbrxm() {
   return jbrxm;
}

 public String getJydzlxdhx() {
   return jydzlxdhx;
}

 public String getJydzlxdhy() {
   return jydzlxdhy;
}

 public String getJydzx() {
   return jydzx;
}

 public String getJydzy() {
   return jydzy;
}

 public String getJydzybx() {
   return jydzybx;
}

 public String getJydzyby() {
   return jydzyby;
}

 public String getKjdshrjx() {
   return kjdshrjx;
}

 public String getKjdshrjy() {
   return kjdshrjy;
}

 public String getKjzlx() {
   return kjzlx;
}

 public String getKjzly() {
   return kjzly;
}

 public String getLsh() {
   return lsh;
}

 public String getNsrmc() {
   return nsrmc;
}

 public String getQyfzremailx() {
   return qyfzremailx;
}

 public String getQyfzremaily() {
   return qyfzremaily;
}

 public String getQyfzrgddhx() {
   return qyfzrgddhx;
}

 public String getQyfzrgddhy() {
   return qyfzrgddhy;
}

 public String getQyfzrxmx() {
   return qyfzrxmx;
}

 public String getQyfzrxmy() {
   return qyfzrxmy;
}

 public String getQyfzryddhx() {
   return qyfzryddhx;
}

 public String getQyfzryddhy() {
   return qyfzryddhy;
}

 public String getQyfzrzjhmx() {
   return qyfzrzjhmx;
}

 public String getQyfzrzjhmy() {
   return qyfzrzjhmy;
}

 public String getQyfzrzjzlx() {
   return qyfzrzjzlx;
}

 public String getQyfzrzjzly() {
   return qyfzrzjzly;
}

 public String getQyfzrzwx() {
   return qyfzrzwx;
}

 public String getQyfzrzwy() {
   return qyfzrzwy;
}

 public String getSqly() {
   return sqly;
}

 public String getSwglm() {
   return swglm;
}


  public void  setBsydhx(String bsydhx) {
    status.put("BSY_DH_X", bsydhx);
    this.bsydhx=bsydhx;
}

  public void  setBsydhx2(String bsydhx2) {
    status.put("BSY_DH_X2", bsydhx2);
    this.bsydhx2=bsydhx2;
}

  public void  setBsydhx3(String bsydhx3) {
    status.put("BSY_DH_X3", bsydhx3);
    this.bsydhx3=bsydhx3;
}

  public void  setBsydhy(String bsydhy) {
    status.put("BSY_DH_Y", bsydhy);
    this.bsydhy=bsydhy;
}

  public void  setBsydhy2(String bsydhy2) {
    status.put("BSY_DH_Y2", bsydhy2);
    this.bsydhy2=bsydhy2;
}

  public void  setBsydhy3(String bsydhy3) {
    status.put("BSY_DH_Y3", bsydhy3);
    this.bsydhy3=bsydhy3;
}

  public void  setBsyemailx(String bsyemailx) {
    status.put("BSY_EMAIL_X", bsyemailx);
    this.bsyemailx=bsyemailx;
}

  public void  setBsyemailx2(String bsyemailx2) {
    status.put("BSY_EMAIL_X2", bsyemailx2);
    this.bsyemailx2=bsyemailx2;
}

  public void  setBsyemailx3(String bsyemailx3) {
    status.put("BSY_EMAIL_X3", bsyemailx3);
    this.bsyemailx3=bsyemailx3;
}

  public void  setBsyemaily(String bsyemaily) {
    status.put("BSY_EMAIL_Y", bsyemaily);
    this.bsyemaily=bsyemaily;
}

  public void  setBsyemaily2(String bsyemaily2) {
    status.put("BSY_EMAIL_Y2", bsyemaily2);
    this.bsyemaily2=bsyemaily2;
}

  public void  setBsyemaily3(String bsyemaily3) {
    status.put("BSY_EMAIL_Y3", bsyemaily3);
    this.bsyemaily3=bsyemaily3;
}

  public void  setBsysfzhx(String bsysfzhx) {
    status.put("BSY_SFZH_X", bsysfzhx);
    this.bsysfzhx=bsysfzhx;
}

  public void  setBsysfzhx2(String bsysfzhx2) {
    status.put("BSY_SFZH_X2", bsysfzhx2);
    this.bsysfzhx2=bsysfzhx2;
}

  public void  setBsysfzhx3(String bsysfzhx3) {
    status.put("BSY_SFZH_X3", bsysfzhx3);
    this.bsysfzhx3=bsysfzhx3;
}

  public void  setBsysfzhy(String bsysfzhy) {
    status.put("BSY_SFZH_Y", bsysfzhy);
    this.bsysfzhy=bsysfzhy;
}

  public void  setBsysfzhy2(String bsysfzhy2) {
    status.put("BSY_SFZH_Y2", bsysfzhy2);
    this.bsysfzhy2=bsysfzhy2;
}

  public void  setBsysfzhy3(String bsysfzhy3) {
    status.put("BSY_SFZH_Y3", bsysfzhy3);
    this.bsysfzhy3=bsysfzhy3;
}

  public void  setBsysjx(String bsysjx) {
    status.put("BSY_SJ_X", bsysjx);
    this.bsysjx=bsysjx;
}

  public void  setBsysjx2(String bsysjx2) {
    status.put("BSY_SJ_X2", bsysjx2);
    this.bsysjx2=bsysjx2;
}

  public void  setBsysjx3(String bsysjx3) {
    status.put("BSY_SJ_X3", bsysjx3);
    this.bsysjx3=bsysjx3;
}

  public void  setBsysjy(String bsysjy) {
    status.put("BSY_SJ_Y", bsysjy);
    this.bsysjy=bsysjy;
}

  public void  setBsysjy2(String bsysjy2) {
    status.put("BSY_SJ_Y2", bsysjy2);
    this.bsysjy2=bsysjy2;
}

  public void  setBsysjy3(String bsysjy3) {
    status.put("BSY_SJ_Y3", bsysjy3);
    this.bsysjy3=bsysjy3;
}

  public void  setBsyxmx(String bsyxmx) {
    status.put("BSY_XM_X", bsyxmx);
    this.bsyxmx=bsyxmx;
}

  public void  setBsyxmx2(String bsyxmx2) {
    status.put("BSY_XM_X2", bsyxmx2);
    this.bsyxmx2=bsyxmx2;
}

  public void  setBsyxmx3(String bsyxmx3) {
    status.put("BSY_XM_X3", bsyxmx3);
    this.bsyxmx3=bsyxmx3;
}

  public void  setBsyxmy(String bsyxmy) {
    status.put("BSY_XM_Y", bsyxmy);
    this.bsyxmy=bsyxmy;
}

  public void  setBsyxmy2(String bsyxmy2) {
    status.put("BSY_XM_Y2", bsyxmy2);
    this.bsyxmy2=bsyxmy2;
}

  public void  setBsyxmy3(String bsyxmy3) {
    status.put("BSY_XM_Y3", bsyxmy3);
    this.bsyxmy3=bsyxmy3;
}

  public void  setBsyzjzlx(String bsyzjzlx) {
    status.put("BSY_ZJZL_X", bsyzjzlx);
    this.bsyzjzlx=bsyzjzlx;
}

  public void  setBsyzjzlx2(String bsyzjzlx2) {
    status.put("BSY_ZJZL_X2", bsyzjzlx2);
    this.bsyzjzlx2=bsyzjzlx2;
}

  public void  setBsyzjzlx3(String bsyzjzlx3) {
    status.put("BSY_ZJZL_X3", bsyzjzlx3);
    this.bsyzjzlx3=bsyzjzlx3;
}

  public void  setBsyzjzly(String bsyzjzly) {
    status.put("BSY_ZJZL_Y", bsyzjzly);
    this.bsyzjzly=bsyzjzly;
}

  public void  setBsyzjzly2(String bsyzjzly2) {
    status.put("BSY_ZJZL_Y2", bsyzjzly2);
    this.bsyzjzly2=bsyzjzly2;
}

  public void  setBsyzjzly3(String bsyzjzly3) {
    status.put("BSY_ZJZL_Y3", bsyzjzly3);
    this.bsyzjzly3=bsyzjzly3;
}

  public void  setBsyzwx(String bsyzwx) {
    status.put("BSY_ZW_X", bsyzwx);
    this.bsyzwx=bsyzwx;
}

  public void  setBsyzwx2(String bsyzwx2) {
    status.put("BSY_ZW_X2", bsyzwx2);
    this.bsyzwx2=bsyzwx2;
}

  public void  setBsyzwx3(String bsyzwx3) {
    status.put("BSY_ZW_X3", bsyzwx3);
    this.bsyzwx3=bsyzwx3;
}

  public void  setBsyzwy(String bsyzwy) {
    status.put("BSY_ZW_Y", bsyzwy);
    this.bsyzwy=bsyzwy;
}

  public void  setBsyzwy2(String bsyzwy2) {
    status.put("BSY_ZW_Y2", bsyzwy2);
    this.bsyzwy2=bsyzwy2;
}

  public void  setBsyzwy3(String bsyzwy3) {
    status.put("BSY_ZW_Y3", bsyzwy3);
    this.bsyzwy3=bsyzwy3;
}

  public void  setCffzrdhx(String cffzrdhx) {
    status.put("CFFZR_DH_X", cffzrdhx);
    this.cffzrdhx=cffzrdhx;
}

  public void  setCffzrdhx2(String cffzrdhx2) {
    status.put("CFFZR_DH_X2", cffzrdhx2);
    this.cffzrdhx2=cffzrdhx2;
}

  public void  setCffzrdhy(String cffzrdhy) {
    status.put("CFFZR_DH_Y", cffzrdhy);
    this.cffzrdhy=cffzrdhy;
}

  public void  setCffzrdhy2(String cffzrdhy2) {
    status.put("CFFZR_DH_Y2", cffzrdhy2);
    this.cffzrdhy2=cffzrdhy2;
}

  public void  setCffzremailx(String cffzremailx) {
    status.put("CFFZR_EMAIL_X", cffzremailx);
    this.cffzremailx=cffzremailx;
}

  public void  setCffzremailx2(String cffzremailx2) {
    status.put("CFFZR_EMAIL_X2", cffzremailx2);
    this.cffzremailx2=cffzremailx2;
}

  public void  setCffzremaily(String cffzremaily) {
    status.put("CFFZR_EMAIL_Y", cffzremaily);
    this.cffzremaily=cffzremaily;
}

  public void  setCffzremaily2(String cffzremaily2) {
    status.put("CFFZR_EMAIL_Y2", cffzremaily2);
    this.cffzremaily2=cffzremaily2;
}

  public void  setCffzrsfzhx(String cffzrsfzhx) {
    status.put("CFFZR_SFZH_X", cffzrsfzhx);
    this.cffzrsfzhx=cffzrsfzhx;
}

  public void  setCffzrsfzhx2(String cffzrsfzhx2) {
    status.put("CFFZR_SFZH_X2", cffzrsfzhx2);
    this.cffzrsfzhx2=cffzrsfzhx2;
}

  public void  setCffzrsfzhy(String cffzrsfzhy) {
    status.put("CFFZR_SFZH_Y", cffzrsfzhy);
    this.cffzrsfzhy=cffzrsfzhy;
}

  public void  setCffzrsfzhy2(String cffzrsfzhy2) {
    status.put("CFFZR_SFZH_Y2", cffzrsfzhy2);
    this.cffzrsfzhy2=cffzrsfzhy2;
}

  public void  setCffzrsjx(String cffzrsjx) {
    status.put("CFFZR_SJ_X", cffzrsjx);
    this.cffzrsjx=cffzrsjx;
}

  public void  setCffzrsjx2(String cffzrsjx2) {
    status.put("CFFZR_SJ_X2", cffzrsjx2);
    this.cffzrsjx2=cffzrsjx2;
}

  public void  setCffzrsjy(String cffzrsjy) {
    status.put("CFFZR_SJ_Y", cffzrsjy);
    this.cffzrsjy=cffzrsjy;
}

  public void  setCffzrsjy2(String cffzrsjy2) {
    status.put("CFFZR_SJ_Y2", cffzrsjy2);
    this.cffzrsjy2=cffzrsjy2;
}

  public void  setCffzrxmx(String cffzrxmx) {
    status.put("CFFZR_XM_X", cffzrxmx);
    this.cffzrxmx=cffzrxmx;
}

  public void  setCffzrxmx2(String cffzrxmx2) {
    status.put("CFFZR_XM_X2", cffzrxmx2);
    this.cffzrxmx2=cffzrxmx2;
}

  public void  setCffzrxmy(String cffzrxmy) {
    status.put("CFFZR_XM_Y", cffzrxmy);
    this.cffzrxmy=cffzrxmy;
}

  public void  setCffzrxmy2(String cffzrxmy2) {
    status.put("CFFZR_XM_Y2", cffzrxmy2);
    this.cffzrxmy2=cffzrxmy2;
}

  public void  setCffzrzjzlx(String cffzrzjzlx) {
    status.put("CFFZR_ZJZL_X", cffzrzjzlx);
    this.cffzrzjzlx=cffzrzjzlx;
}

  public void  setCffzrzjzlx2(String cffzrzjzlx2) {
    status.put("CFFZR_ZJZL_X2", cffzrzjzlx2);
    this.cffzrzjzlx2=cffzrzjzlx2;
}

  public void  setCffzrzjzly(String cffzrzjzly) {
    status.put("CFFZR_ZJZL_Y", cffzrzjzly);
    this.cffzrzjzly=cffzrzjzly;
}

  public void  setCffzrzjzly2(String cffzrzjzly2) {
    status.put("CFFZR_ZJZL_Y2", cffzrzjzly2);
    this.cffzrzjzly2=cffzrzjzly2;
}

  public void  setCffzrzwx(String cffzrzwx) {
    status.put("CFFZR_ZW_X", cffzrzwx);
    this.cffzrzwx=cffzrzwx;
}

  public void  setCffzrzwx2(String cffzrzwx2) {
    status.put("CFFZR_ZW_X2", cffzrzwx2);
    this.cffzrzwx2=cffzrzwx2;
}

  public void  setCffzrzwy(String cffzrzwy) {
    status.put("CFFZR_ZW_Y", cffzrzwy);
    this.cffzrzwy=cffzrzwy;
}

  public void  setCffzrzwy2(String cffzrzwy2) {
    status.put("CFFZR_ZW_Y2", cffzrzwy2);
    this.cffzrzwy2=cffzrzwy2;
}

  public void  setGswzx(String gswzx) {
    status.put("GSWZ_X", gswzx);
    this.gswzx=gswzx;
}

  public void  setGswzy(String gswzy) {
    status.put("GSWZ_Y", gswzy);
    this.gswzy=gswzy;
}

  public void  setJbrxm(String jbrxm) {
    status.put("JBR_XM", jbrxm);
    this.jbrxm=jbrxm;
}

  public void  setJydzlxdhx(String jydzlxdhx) {
    status.put("JYDZ_LXDH_X", jydzlxdhx);
    this.jydzlxdhx=jydzlxdhx;
}

  public void  setJydzlxdhy(String jydzlxdhy) {
    status.put("JYDZ_LXDH_Y", jydzlxdhy);
    this.jydzlxdhy=jydzlxdhy;
}

  public void  setJydzx(String jydzx) {
    status.put("JYDZ_X", jydzx);
    this.jydzx=jydzx;
}

  public void  setJydzy(String jydzy) {
    status.put("JYDZ_Y", jydzy);
    this.jydzy=jydzy;
}

  public void  setJydzybx(String jydzybx) {
    status.put("JYDZ_YB_X", jydzybx);
    this.jydzybx=jydzybx;
}

  public void  setJydzyby(String jydzyby) {
    status.put("JYDZ_YB_Y", jydzyby);
    this.jydzyby=jydzyby;
}

  public void  setKjdshrjx(String kjdshrjx) {
    status.put("KJDSHRJ_X", kjdshrjx);
    this.kjdshrjx=kjdshrjx;
}

  public void  setKjdshrjy(String kjdshrjy) {
    status.put("KJDSHRJ_Y", kjdshrjy);
    this.kjdshrjy=kjdshrjy;
}

  public void  setKjzlx(String kjzlx) {
    status.put("KJZL_X", kjzlx);
    this.kjzlx=kjzlx;
}

  public void  setKjzly(String kjzly) {
    status.put("KJZL_Y", kjzly);
    this.kjzly=kjzly;
}

  public void  setLsh(String lsh) {
    status.put("LSH", lsh);
    this.lsh=lsh;
}

  public void  setNsrmc(String nsrmc) {
    status.put("NSR_MC", nsrmc);
    this.nsrmc=nsrmc;
}

  public void  setQyfzremailx(String qyfzremailx) {
    status.put("QYFZR_EMAIL_X", qyfzremailx);
    this.qyfzremailx=qyfzremailx;
}

  public void  setQyfzremaily(String qyfzremaily) {
    status.put("QYFZR_EMAIL_Y", qyfzremaily);
    this.qyfzremaily=qyfzremaily;
}

  public void  setQyfzrgddhx(String qyfzrgddhx) {
    status.put("QYFZR_GDDH_X", qyfzrgddhx);
    this.qyfzrgddhx=qyfzrgddhx;
}

  public void  setQyfzrgddhy(String qyfzrgddhy) {
    status.put("QYFZR_GDDH_Y", qyfzrgddhy);
    this.qyfzrgddhy=qyfzrgddhy;
}

  public void  setQyfzrxmx(String qyfzrxmx) {
    status.put("QYFZR_XM_X", qyfzrxmx);
    this.qyfzrxmx=qyfzrxmx;
}

  public void  setQyfzrxmy(String qyfzrxmy) {
    status.put("QYFZR_XM_Y", qyfzrxmy);
    this.qyfzrxmy=qyfzrxmy;
}

  public void  setQyfzryddhx(String qyfzryddhx) {
    status.put("QYFZR_YDDH_X", qyfzryddhx);
    this.qyfzryddhx=qyfzryddhx;
}

  public void  setQyfzryddhy(String qyfzryddhy) {
    status.put("QYFZR_YDDH_Y", qyfzryddhy);
    this.qyfzryddhy=qyfzryddhy;
}

  public void  setQyfzrzjhmx(String qyfzrzjhmx) {
    status.put("QYFZR_ZJHM_X", qyfzrzjhmx);
    this.qyfzrzjhmx=qyfzrzjhmx;
}

  public void  setQyfzrzjhmy(String qyfzrzjhmy) {
    status.put("QYFZR_ZJHM_Y", qyfzrzjhmy);
    this.qyfzrzjhmy=qyfzrzjhmy;
}

  public void  setQyfzrzjzlx(String qyfzrzjzlx) {
    status.put("QYFZR_ZJZL_X", qyfzrzjzlx);
    this.qyfzrzjzlx=qyfzrzjzlx;
}

  public void  setQyfzrzjzly(String qyfzrzjzly) {
    status.put("QYFZR_ZJZL_Y", qyfzrzjzly);
    this.qyfzrzjzly=qyfzrzjzly;
}

  public void  setQyfzrzwx(String qyfzrzwx) {
    status.put("QYFZR_ZW_X", qyfzrzwx);
    this.qyfzrzwx=qyfzrzwx;
}

  public void  setQyfzrzwy(String qyfzrzwy) {
    status.put("QYFZR_ZW_Y", qyfzrzwy);
    this.qyfzrzwy=qyfzrzwy;
}

  public void  setSqly(String sqly) {
    status.put("SQ_LY", sqly);
    this.sqly=sqly;
}

  public void  setSwglm(String swglm) {
	/** 数据库SWGLM为Long类型;
	 	类型转换 String  ->Long */
    if(null!=swglm&&swglm.length()>0 ) {
    	status.put("SWGLM", new Long(swglm));
    }
    this.swglm=swglm;
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

