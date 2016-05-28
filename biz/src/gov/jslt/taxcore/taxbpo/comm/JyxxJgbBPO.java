package gov.jslt.taxcore.taxbpo.comm;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.bpo.CssBaseBPO;
import com.ctp.core.bpo.CssBaseBpoVO;
import com.ctp.core.bpo.QueryCssBPO;
import com.ctp.core.commutils.DateUtils;
import com.ctp.core.log.LogWritter;

import gov.jslt.taxevent.comm.JyxxJgbVO;

public class JyxxJgbBPO extends CssBaseBPO {

public JyxxJgbBPO() {
super();
}

private static final String TABLENAME = "T_ZB_LS_JYZT";

private static  JyxxJgbVO loadConcreteBPO(ResultSet rs) throws SQLException {
 		JyxxJgbVO    vo = new JyxxJgbVO()	 ;
     	vo.setJyztdm(rs.getString("JYZT_DM"));
     	vo.setJyztmc(rs.getString("JYZT_MC"));
     	vo.setSjnr(rs.getString("SJNR"));
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

private static JyxxJgbVO queryByWhere(Connection con,String tablename,String sqlOrder,String sqlWhere,ArrayList sqlParams,boolean isCount) throws SQLException {

	ResultSet result=null;
	PreparedStatement ps=null;
	result = findAllByWhere(con,tablename,sqlOrder,  sqlWhere, sqlParams,isCount,result,ps);

	JyxxJgbVO vo = null;
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

public static boolean insert(Connection con , JyxxJgbVO vo)throws SQLException {
    return insert(  con ,TABLENAME  , vo);
}

/**
*  根据主键条件删除一条记录
* @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/
public static boolean deleteByPK(Connection con,String JYZT_DM) throws SQLException {
	String strSqlWhere="JYZT_DM=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(JYZT_DM);

	return delete(con,TABLENAME ,strSqlWhere,sqlParams)  ;
}

/**
*   根据主键条件查询一条记录
* @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/
public static JyxxJgbVO queryByPK(Connection con,String JYZT_DM) throws SQLException {
	String strSqlWhere="JYZT_DM=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(JYZT_DM);

	return  queryByWhere(con,TABLENAME ,null,strSqlWhere,sqlParams,false) ;
}

/**
*    根据主键条件更新一条记录
* @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/
 public static boolean updateByPK(Connection con,String JYZT_DM, JyxxJgbVO vo) throws SQLException {
	String strSqlWhere="JYZT_DM=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(JYZT_DM);

	return update(con,TABLENAME ,strSqlWhere,sqlParams,vo) ;
}
////////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////
 	public static List queryJyxxJgb(Connection con)
 	throws SQLException{
 		CachedRowSet result = QueryCssBPO.findAll(con, "select * from T_ZB_LS_JYZT", null);
 		List list = null;
 	    if(result != null){
 	    	list = new ArrayList();
 	    	while(result.next())
 	    		list.add(loadConcreteBPO(result));
 	    }
		return list;
 		
 	}
 	
 	public static JyxxJgbVO queryJyxxxJgb(Connection con)
 	throws SQLException{
 		CachedRowSet result = QueryCssBPO.findAll(con, "select * from T_ZB_LS_JYZT", null);
 		JyxxJgbVO vo = null;
 	    if(result != null){
 	    	while(result.next())
 	    		vo = loadConcreteBPO(result);
 	    }
		return vo;
 		
 	}
}

