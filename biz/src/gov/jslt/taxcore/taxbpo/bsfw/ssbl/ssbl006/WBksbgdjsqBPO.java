package gov.jslt.taxcore.taxbpo.bsfw.ssbl.ssbl006;
import gov.jslt.taxevent.bsfw.ssbl.ssbl006.WBksbgdjsqVO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.bpo.CssBaseBPO;
import com.ctp.core.bpo.CssBaseBpoVO;
import com.ctp.core.commutils.DateUtils;
import com.ctp.core.log.LogWritter;

public class WBksbgdjsqBPO extends CssBaseBPO {

public WBksbgdjsqBPO() {
super();
}

private static final String TABLENAME = "DB_WSBS.T_WSFW_KSBGDJSQ";

private static  WBksbgdjsqVO loadConcreteBPO(ResultSet rs) throws SQLException {
 		WBksbgdjsqVO    vo = new WBksbgdjsqVO()	 ;
     	vo.setBsydhx(rs.getString("BSY_DH_X"));
     	vo.setBsydhx2(rs.getString("BSY_DH_X2"));
     	vo.setBsydhx3(rs.getString("BSY_DH_X3"));
     	vo.setBsydhy(rs.getString("BSY_DH_Y"));
     	vo.setBsydhy2(rs.getString("BSY_DH_Y2"));
     	vo.setBsydhy3(rs.getString("BSY_DH_Y3"));
     	vo.setBsyemailx(rs.getString("BSY_EMAIL_X"));
     	vo.setBsyemailx2(rs.getString("BSY_EMAIL_X2"));
     	vo.setBsyemailx3(rs.getString("BSY_EMAIL_X3"));
     	vo.setBsyemaily(rs.getString("BSY_EMAIL_Y"));
     	vo.setBsyemaily2(rs.getString("BSY_EMAIL_Y2"));
     	vo.setBsyemaily3(rs.getString("BSY_EMAIL_Y3"));
     	vo.setBsysfzhx(rs.getString("BSY_SFZH_X"));
     	vo.setBsysfzhx2(rs.getString("BSY_SFZH_X2"));
     	vo.setBsysfzhx3(rs.getString("BSY_SFZH_X3"));
     	vo.setBsysfzhy(rs.getString("BSY_SFZH_Y"));
     	vo.setBsysfzhy2(rs.getString("BSY_SFZH_Y2"));
     	vo.setBsysfzhy3(rs.getString("BSY_SFZH_Y3"));
     	vo.setBsysjx(rs.getString("BSY_SJ_X"));
     	vo.setBsysjx2(rs.getString("BSY_SJ_X2"));
     	vo.setBsysjx3(rs.getString("BSY_SJ_X3"));
     	vo.setBsysjy(rs.getString("BSY_SJ_Y"));
     	vo.setBsysjy2(rs.getString("BSY_SJ_Y2"));
     	vo.setBsysjy3(rs.getString("BSY_SJ_Y3"));
     	vo.setBsyxmx(rs.getString("BSY_XM_X"));
     	vo.setBsyxmx2(rs.getString("BSY_XM_X2"));
     	vo.setBsyxmx3(rs.getString("BSY_XM_X3"));
     	vo.setBsyxmy(rs.getString("BSY_XM_Y"));
     	vo.setBsyxmy2(rs.getString("BSY_XM_Y2"));
     	vo.setBsyxmy3(rs.getString("BSY_XM_Y3"));
     	vo.setBsyzjzlx(rs.getString("BSY_ZJZL_X"));
     	vo.setBsyzjzlx2(rs.getString("BSY_ZJZL_X2"));
     	vo.setBsyzjzlx3(rs.getString("BSY_ZJZL_X3"));
     	vo.setBsyzjzly(rs.getString("BSY_ZJZL_Y"));
     	vo.setBsyzjzly2(rs.getString("BSY_ZJZL_Y2"));
     	vo.setBsyzjzly3(rs.getString("BSY_ZJZL_Y3"));
     	vo.setBsyzwx(rs.getString("BSY_ZW_X"));
     	vo.setBsyzwx2(rs.getString("BSY_ZW_X2"));
     	vo.setBsyzwx3(rs.getString("BSY_ZW_X3"));
     	vo.setBsyzwy(rs.getString("BSY_ZW_Y"));
     	vo.setBsyzwy2(rs.getString("BSY_ZW_Y2"));
     	vo.setBsyzwy3(rs.getString("BSY_ZW_Y3"));
     	vo.setCffzrdhx(rs.getString("CFFZR_DH_X"));
     	vo.setCffzrdhx2(rs.getString("CFFZR_DH_X2"));
     	vo.setCffzrdhy(rs.getString("CFFZR_DH_Y"));
     	vo.setCffzrdhy2(rs.getString("CFFZR_DH_Y2"));
     	vo.setCffzremailx(rs.getString("CFFZR_EMAIL_X"));
     	vo.setCffzremailx2(rs.getString("CFFZR_EMAIL_X2"));
     	vo.setCffzremaily(rs.getString("CFFZR_EMAIL_Y"));
     	vo.setCffzremaily2(rs.getString("CFFZR_EMAIL_Y2"));
     	vo.setCffzrsfzhx(rs.getString("CFFZR_SFZH_X"));
     	vo.setCffzrsfzhx2(rs.getString("CFFZR_SFZH_X2"));
     	vo.setCffzrsfzhy(rs.getString("CFFZR_SFZH_Y"));
     	vo.setCffzrsfzhy2(rs.getString("CFFZR_SFZH_Y2"));
     	vo.setCffzrsjx(rs.getString("CFFZR_SJ_X"));
     	vo.setCffzrsjx2(rs.getString("CFFZR_SJ_X2"));
     	vo.setCffzrsjy(rs.getString("CFFZR_SJ_Y"));
     	vo.setCffzrsjy2(rs.getString("CFFZR_SJ_Y2"));
     	vo.setCffzrxmx(rs.getString("CFFZR_XM_X"));
     	vo.setCffzrxmx2(rs.getString("CFFZR_XM_X2"));
     	vo.setCffzrxmy(rs.getString("CFFZR_XM_Y"));
     	vo.setCffzrxmy2(rs.getString("CFFZR_XM_Y2"));
     	vo.setCffzrzjzlx(rs.getString("CFFZR_ZJZL_X"));
     	vo.setCffzrzjzlx2(rs.getString("CFFZR_ZJZL_X2"));
     	vo.setCffzrzjzly(rs.getString("CFFZR_ZJZL_Y"));
     	vo.setCffzrzjzly2(rs.getString("CFFZR_ZJZL_Y2"));
     	vo.setCffzrzwx(rs.getString("CFFZR_ZW_X"));
     	vo.setCffzrzwx2(rs.getString("CFFZR_ZW_X2"));
     	vo.setCffzrzwy(rs.getString("CFFZR_ZW_Y"));
     	vo.setCffzrzwy2(rs.getString("CFFZR_ZW_Y2"));
     	vo.setGswzx(rs.getString("GSWZ_X"));
     	vo.setGswzy(rs.getString("GSWZ_Y"));
     	vo.setJbrxm(rs.getString("JBR_XM"));
     	vo.setJydzlxdhx(rs.getString("JYDZ_LXDH_X"));
     	vo.setJydzlxdhy(rs.getString("JYDZ_LXDH_Y"));
     	vo.setJydzx(rs.getString("JYDZ_X"));
     	vo.setJydzy(rs.getString("JYDZ_Y"));
     	vo.setJydzybx(rs.getString("JYDZ_YB_X"));
     	vo.setJydzyby(rs.getString("JYDZ_YB_Y"));
     	vo.setKjdshrjx(rs.getString("KJDSHRJ_X"));
     	vo.setKjdshrjy(rs.getString("KJDSHRJ_Y"));
     	vo.setKjzlx(rs.getString("KJZL_X"));
     	vo.setKjzly(rs.getString("KJZL_Y"));
     	vo.setLsh(rs.getString("LSH"));
     	vo.setNsrmc(rs.getString("NSR_MC"));
     	vo.setQyfzremailx(rs.getString("QYFZR_EMAIL_X"));
     	vo.setQyfzremaily(rs.getString("QYFZR_EMAIL_Y"));
     	vo.setQyfzrgddhx(rs.getString("QYFZR_GDDH_X"));
     	vo.setQyfzrgddhy(rs.getString("QYFZR_GDDH_Y"));
     	vo.setQyfzrxmx(rs.getString("QYFZR_XM_X"));
     	vo.setQyfzrxmy(rs.getString("QYFZR_XM_Y"));
     	vo.setQyfzryddhx(rs.getString("QYFZR_YDDH_X"));
     	vo.setQyfzryddhy(rs.getString("QYFZR_YDDH_Y"));
     	vo.setQyfzrzjhmx(rs.getString("QYFZR_ZJHM_X"));
     	vo.setQyfzrzjhmy(rs.getString("QYFZR_ZJHM_Y"));
     	vo.setQyfzrzjzlx(rs.getString("QYFZR_ZJZL_X"));
     	vo.setQyfzrzjzly(rs.getString("QYFZR_ZJZL_Y"));
     	vo.setQyfzrzwx(rs.getString("QYFZR_ZW_X"));
     	vo.setQyfzrzwy(rs.getString("QYFZR_ZW_Y"));
     	vo.setSqly(rs.getString("SQ_LY"));
     	vo.setSwglm(String.valueOf(rs.getLong("SWGLM")));
		vo.setStatus(new HashMap());
		return vo;

}

/**
*  返回一条记录的自定义查询;
 * @param 
* @param con:Connection 与数据库建立的连接
  @param String tablename 表
  @param String sqlOrder 排序字段
  @param String sqlWhere 条件 sql
  @param ArrayList sqlParams 条件数据
  @param boolean isCount false
* @throws SQLException
* 修改历史： 
       版本0.5 2008-4-24周红江 :  1.将原来的findAll废弃  因为其存在内存浪费，2.不再使用 CachedRowSet  3.在子类中申明ResultSet,PreparedStatement 在子类中释放;
*/

private static WBksbgdjsqVO queryByWhere(Connection con,String tablename,String sqlOrder,String sqlWhere,ArrayList sqlParams,boolean isCount) throws SQLException {

	ResultSet result=null;
	PreparedStatement ps=null;
	result = findAllByWhere(con,tablename,sqlOrder,  sqlWhere, sqlParams,isCount,result,ps);

	WBksbgdjsqVO vo = null;
	if (result != null) {
		    if(result.next())
    		vo = loadConcreteBPO(result);
     	} 
	closeDbsession(result,ps);;
	return vo;
}


/**
*  返回多条记录的自定义查询;
 * @param 
* @param con:Connection 与数据库建立的连接
  @param String tablename 表
  @param String sqlOrder 排序字段
  @param String sqlWhere 条件 sql
  @param ArrayList sqlParams 条件数据
  @param boolean isCount false
* @throws SQLException
* 修改历史：
		 版本0.5 2008-4-24周红江 :  1.将原来的findAll废弃 因为其存在内存浪费 ，2.不再使用 CachedRowSet  3.在子类中申明ResultSet,PreparedStatement 在子类中释放;
*/

private static ArrayList queryByZdyWhere(Connection con,String tablename,String sqlOrder,String sqlWhere,ArrayList sqlParams,boolean isCount) throws SQLException {

	ResultSet result=null;
	PreparedStatement ps=null;
	result = findAllByWhere(con,tablename,sqlOrder,  sqlWhere, sqlParams,isCount,result,ps);

	ArrayList listLodadata=null;
	if (result != null) {
 			listLodadata = new ArrayList();
 			while(result.next())
				listLodadata.add(loadConcreteBPO(result));
	} 
	closeDbsession(result,ps);
	return listLodadata;
}

////////////////////////////////////////以下为业 务方法 //////////////////////////////////////////////////////////////

/**
*  增加一条新记录;
 * @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/

public static boolean insert(Connection con , WBksbgdjsqVO vo)throws SQLException {
    return insert(  con ,TABLENAME  , vo);
}

/**
*  根据主键条件删除一条记录
* @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/
public static boolean deleteByPK(Connection con,String LSH) throws SQLException {
	String strSqlWhere="LSH=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(LSH);

	return delete(con,TABLENAME ,strSqlWhere,sqlParams)  ;
}

/**
*   根据主键条件查询一条记录
* @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/
public static WBksbgdjsqVO queryByPK(Connection con,String LSH) throws SQLException {
	String strSqlWhere="LSH=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(LSH);

	return  queryByWhere(con,TABLENAME ,null,strSqlWhere,sqlParams,false) ;
}

/**
*    根据主键条件更新一条记录
* @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/
 public static boolean updateByPK(Connection con,String LSH, WBksbgdjsqVO vo) throws SQLException {
	String strSqlWhere="LSH=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(LSH);

	return update(con,TABLENAME ,strSqlWhere,sqlParams,vo) ;
}
////////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////
}

