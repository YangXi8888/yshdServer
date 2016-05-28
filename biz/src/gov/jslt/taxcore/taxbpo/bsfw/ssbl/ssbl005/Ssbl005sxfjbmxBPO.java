package gov.jslt.taxcore.taxbpo.bsfw.ssbl.ssbl005;
import gov.jslt.taxevent.bsfw.ssbl.ssbl005.Ssbl005sxfjbmxVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ctp.core.bpo.CssBaseBPO;
import com.ctp.core.commutils.DateUtils;


public class Ssbl005sxfjbmxBPO extends CssBaseBPO {

public Ssbl005sxfjbmxBPO() {
super();
}

private static final String TABLENAME = "DB_WSBS.T_WB_KT_SXFLQMXXX";

private static  Ssbl005sxfjbmxVO loadConcreteBPO(ResultSet rs) throws SQLException {
 		Ssbl005sxfjbmxVO    vo = new Ssbl005sxfjbmxVO()	 ;
     	vo.setDzsphxh(rs.getString("DZSPH_XH"));
     	vo.setGsrq(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("GS_RQ")));
     	vo.setHjje(String.valueOf(rs.getDouble("HJ_JE")));
     	vo.setJklsmxhxh(String.valueOf(rs.getLong("JKLSMXH_XH")));
     	vo.setJkxh(rs.getString("JK_XH"));
     	vo.setLqbl(String.valueOf(rs.getDouble("LQBL")));
     	vo.setLqje(String.valueOf(rs.getDouble("LQ_JE")));
     	vo.setLrrydm(rs.getString("LRRY_DM"));
     	vo.setLsh(rs.getString("LSH"));
     	vo.setLybz(rs.getString("LY_BZ"));
     	vo.setMxxh(String.valueOf(rs.getLong("MX_XH")));
     	vo.setPzdm(rs.getString("PZ_DM"));
     	vo.setSfssqqsrq(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("SFSSQ_QSRQ")));
     	vo.setSfssqzzrq(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("SFSSQ_ZZRQ")));
     	vo.setSkssjgdm(rs.getString("SKSSJG_DM"));
     	vo.setSkzhid(rs.getString("SKZHID"));
     	vo.setSkje(String.valueOf(rs.getDouble("SK_JE")));
     	vo.setSphjje(String.valueOf(rs.getDouble("SPHJ_JE")));
     	vo.setSplqje(String.valueOf(rs.getDouble("SPLQ_JE")));
     	vo.setSqwsh(rs.getString("SQ_WSH"));
     	vo.setSwglm(String.valueOf(rs.getLong("SWGLM")));
     	vo.setXgrydm(rs.getString("XGRY_DM"));
     	vo.setYsfpbldm(rs.getString("YSFPBL_DM"));
     	vo.setZspmdm(rs.getString("ZSPM_DM"));
     	vo.setZsxmdm(rs.getString("ZSXM_DM"));
     	vo.setZtdm(rs.getString("ZT_DM"));
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

private static Ssbl005sxfjbmxVO queryByWhere(Connection con,String tablename,String sqlOrder,String sqlWhere,ArrayList sqlParams,boolean isCount) throws SQLException {

	ResultSet result=null;
	PreparedStatement ps=null;
	result = findAllByWhere(con,tablename,sqlOrder,  sqlWhere, sqlParams,isCount,result,ps);

	Ssbl005sxfjbmxVO vo = null;
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

public static ArrayList queryByZdyWhere(Connection con,String sqlOrder,String sqlWhere,ArrayList sqlParams) throws SQLException {
	String tablename=TABLENAME;
	boolean isCount=false;
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

public static boolean insert(Connection con , Ssbl005sxfjbmxVO vo)throws SQLException {
    return insert(  con ,TABLENAME  , vo);
}

/**
*  根据主键条件删除一条记录
* @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/
public static boolean deleteByPK(Connection con,String LSH, String JK_XH, String JKLSMXH_XH) throws SQLException {
	String strSqlWhere="LSH=?  AND JK_XH=?  AND JKLSMXH_XH=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(LSH);
 	sqlParams.add(JK_XH);
 	sqlParams.add(JKLSMXH_XH);

	return delete(con,TABLENAME ,strSqlWhere,sqlParams)  ;
}

/**
*   根据主键条件查询一条记录
* @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/
public static Ssbl005sxfjbmxVO queryByPK(Connection con,String LSH, String JK_XH, String JKLSMXH_XH) throws SQLException {
	String strSqlWhere="LSH=?  AND JK_XH=?  AND JKLSMXH_XH=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(LSH);
 	sqlParams.add(JK_XH);
 	sqlParams.add(JKLSMXH_XH);

	return  queryByWhere(con,TABLENAME ,null,strSqlWhere,sqlParams,false) ;
}

/**
*    根据主键条件更新一条记录
* @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/
 public static boolean updateByPK(Connection con,String LSH, String JK_XH, String JKLSMXH_XH, Ssbl005sxfjbmxVO vo) throws SQLException {
	String strSqlWhere="LSH=?  AND JK_XH=?  AND JKLSMXH_XH=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(LSH);
 	sqlParams.add(JK_XH);
 	sqlParams.add(JKLSMXH_XH);

	return update(con,TABLENAME ,strSqlWhere,sqlParams,vo) ;
}
////////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////
 public static boolean deleteByLSH(Connection con,String LSH) throws SQLException {
		String strSqlWhere="LSH=?";
		ArrayList sqlParams=new ArrayList();
	 	sqlParams.add(LSH);
		return delete(con,TABLENAME ,strSqlWhere,sqlParams)  ;
	}

}

