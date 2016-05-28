package gov.jslt.taxcore.taxbpo.bsfw.ssbl.ssbl002;
import gov.jslt.taxevent.bsfw.ssbl.ssbl002.Ssbl002jmssqbVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ctp.core.bpo.CssBaseBPO;
import com.ctp.core.commutils.DateUtils;

public class Ssbl002jmssqbBPO extends CssBaseBPO {

public Ssbl002jmssqbBPO() {
super();
}

private static final String TABLENAME = "DB_WSBS.T_WB_WS_YH_JMSQB";

private static  Ssbl002jmssqbVO loadConcreteBPO(ResultSet rs) throws SQLException {
 		Ssbl002jmssqbVO    vo = new Ssbl002jmssqbVO()	 ;
     	vo.setFwrq(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("FW_RQ")));
     	vo.setGwwh(rs.getString("GWWH"));
     	vo.setLrrydm(rs.getString("LRRY_DM"));
     	vo.setLsh(rs.getString("LSH"));
     	vo.setSpbabj(rs.getString("SPBA_BJ"));
     	vo.setSqjmly(rs.getString("SQJMLY"));
     	vo.setSqrmc(rs.getString("SQR_MC"));
     	vo.setSqrq(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("SQ_RQ")));
     	vo.setSwglm(String.valueOf(rs.getLong("SWGLM")));
     	vo.setWjm(rs.getString("WJM"));
     	vo.setZfbj(rs.getString("ZF_BJ"));
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

private static Ssbl002jmssqbVO queryByWhere(Connection con,String tablename,String sqlOrder,String sqlWhere,ArrayList sqlParams,boolean isCount) throws SQLException {

	ResultSet result=null;
	PreparedStatement ps=null;
	result = findAllByWhere(con,tablename,sqlOrder,  sqlWhere, sqlParams,isCount,result,ps);

	Ssbl002jmssqbVO vo = null;
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

public static boolean insert(Connection con , Ssbl002jmssqbVO vo)throws SQLException {
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
public static Ssbl002jmssqbVO queryByPK(Connection con,String LSH) throws SQLException {
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
 public static boolean updateByPK(Connection con,String LSH, Ssbl002jmssqbVO vo) throws SQLException {
	String strSqlWhere="LSH=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(LSH);

	return update(con,TABLENAME ,strSqlWhere,sqlParams,vo) ;
}
////////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////
}

