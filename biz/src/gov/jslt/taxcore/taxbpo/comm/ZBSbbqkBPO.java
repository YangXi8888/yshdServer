package gov.jslt.taxcore.taxbpo.comm;
import gov.jslt.taxevent.comm.ZBSbbqkVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ctp.core.bpo.CssBaseBPO;

public class ZBSbbqkBPO extends CssBaseBPO {

public ZBSbbqkBPO() {
super();
}

private static final String TABLENAME = "T_ZB_SBBQK";

private static  ZBSbbqkVO loadConcreteBPO(ResultSet rs) throws SQLException {
 		ZBSbbqkVO    vo = new ZBSbbqkVO()	 ;
     	vo.setFwjgdm(rs.getString("FWJG_DM"));
     	vo.setGljgdm(rs.getString("GLJG_DM"));
     	vo.setHsjgdm(rs.getString("HSJG_DM"));
     	vo.setJcjgdm(rs.getString("JCJG_DM"));
     	vo.setJiancjgdm(rs.getString("JIANCJG_DM"));
     	vo.setPzxh(rs.getString("PZ_XH"));
     	vo.setQyzt(rs.getString("QYZT"));
     	vo.setSjly(rs.getString("SJLY"));
     	vo.setSkssjgdm(rs.getString("SKSSJG_DM"));
     	vo.setSwglm(String.valueOf(rs.getLong("SWGLM")));
     	vo.setZsjgdm(rs.getString("ZSJG_DM"));
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

private static ZBSbbqkVO queryByWhere(Connection con,String tablename,String sqlOrder,String sqlWhere,ArrayList sqlParams,boolean isCount) throws SQLException {

	ResultSet result=null;
	PreparedStatement ps=null;
	result = findAllByWhere(con,tablename,sqlOrder,  sqlWhere, sqlParams,isCount,result,ps);

	ZBSbbqkVO vo = null;
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

public static boolean insert(Connection con , ZBSbbqkVO vo)throws SQLException {
    return insert(  con ,TABLENAME  , vo);
}

/**
*  根据主键条件删除一条记录
* @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/
public static boolean deleteByPK(Connection con,String PZ_XH) throws SQLException {
	String strSqlWhere="PZ_XH=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(PZ_XH);

	return delete(con,TABLENAME ,strSqlWhere,sqlParams)  ;
}

/**
*   根据主键条件查询一条记录
* @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/
public static ZBSbbqkVO queryByPK(Connection con,String PZ_XH) throws SQLException {
	String strSqlWhere="PZ_XH=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(PZ_XH);

	return  queryByWhere(con,TABLENAME ,null,strSqlWhere,sqlParams,false) ;
}

/**
*    根据主键条件更新一条记录
* @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/
 public static boolean updateByPK(Connection con,String PZ_XH, ZBSbbqkVO vo) throws SQLException {
	String strSqlWhere="PZ_XH=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(PZ_XH);

	return update(con,TABLENAME ,strSqlWhere,sqlParams,vo) ;
}
////////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////
}

