package gov.jslt.taxcore.taxbpo.bsfw.ssbl.ssbl005;
import gov.jslt.taxevent.bsfw.ssbl.ssbl005.Ssbl005sxfjbzbVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ctp.core.bpo.CssBaseBPO;
import com.ctp.core.commutils.DateUtils;


public class Ssbl005sxfjbzbBPO extends CssBaseBPO {

public Ssbl005sxfjbzbBPO() {
super();
}

private static final String TABLENAME = "DB_WSBS.T_WB_KT_SXFLQZBXX";

private static  Ssbl005sxfjbzbVO loadConcreteBPO(ResultSet rs) throws SQLException {
 		Ssbl005sxfjbzbVO    vo = new Ssbl005sxfjbzbVO()	 ;
     	vo.setFfrydm(rs.getString("FFRY_DM"));
     	vo.setFfsj(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("FF_SJ")));
     	vo.setHjlqje(String.valueOf(rs.getDouble("HJLQ_JE")));
     	vo.setHjbj(rs.getString("HJ_BJ"));
     	vo.setLqje(String.valueOf(rs.getDouble("LQ_JE")));
     	vo.setLrrydm(rs.getString("LRRY_DM"));
     	vo.setLsh(rs.getString("LSH"));
     	vo.setMc(rs.getString("MC"));
     	vo.setPzdm(rs.getString("PZ_DM"));
     	vo.setShrydm(rs.getString("SHRY_DM"));
     	vo.setShsj(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("SH_SJ")));
     	vo.setSlr(String.valueOf(rs.getLong("SLR")));
     	vo.setSlsj(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("SL_SJ")));
     	vo.setSqsyhjje(String.valueOf(rs.getDouble("SQSYHJ_JE")));
     	vo.setSqwsh(rs.getString("SQ_WSH"));
     	vo.setXgrydm(rs.getString("XGRY_DM"));
     	vo.setYhdm(rs.getString("YH_DM"));
     	vo.setYhzh(rs.getString("YH_ZH"));
     	vo.setZphm(rs.getString("ZP_HM"));
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

private static Ssbl005sxfjbzbVO queryByWhere(Connection con,String tablename,String sqlOrder,String sqlWhere,ArrayList sqlParams,boolean isCount) throws SQLException {

	ResultSet result=null;
	PreparedStatement ps=null;
	result = findAllByWhere(con,tablename,sqlOrder,  sqlWhere, sqlParams,isCount,result,ps);

	Ssbl005sxfjbzbVO vo = null;
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

public static boolean insert(Connection con , Ssbl005sxfjbzbVO vo)throws SQLException {
    return insert(  con ,TABLENAME  , vo);
}

/**
*  根据主键条件删除一条记录
* @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/
public static boolean deleteByPK(Connection con,String LSH, String PZ_DM) throws SQLException {
	String strSqlWhere="LSH=?  AND PZ_DM=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(LSH);
 	sqlParams.add(PZ_DM);

	return delete(con,TABLENAME ,strSqlWhere,sqlParams)  ;
}

/**
*   根据主键条件查询一条记录
* @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/
public static Ssbl005sxfjbzbVO queryByPK(Connection con,String LSH, String PZ_DM) throws SQLException {
	String strSqlWhere="LSH=?  AND PZ_DM=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(LSH);
 	sqlParams.add(PZ_DM);

	return  queryByWhere(con,TABLENAME ,null,strSqlWhere,sqlParams,false) ;
}

/**
*    根据主键条件更新一条记录
* @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/
 public static boolean updateByPK(Connection con,String LSH, String PZ_DM, Ssbl005sxfjbzbVO vo) throws SQLException {
	String strSqlWhere="LSH=?  AND PZ_DM=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(LSH);
 	sqlParams.add(PZ_DM);

	return update(con,TABLENAME ,strSqlWhere,sqlParams,vo) ;
}
////////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////
 public static boolean deleteByLSH(Connection con,String LSH) throws SQLException {
		String strSqlWhere="LSH=? ";
		ArrayList sqlParams=new ArrayList();
	 	sqlParams.add(LSH);

		return delete(con,TABLENAME ,strSqlWhere,sqlParams)  ;
	}
}

