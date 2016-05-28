package gov.jslt.taxcore.taxbpo.bsfw.sbns.sbns002;

import gov.jslt.taxevent.bsfw.sbns.sbns002.WBsbfsbVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ctp.core.bpo.CssBaseBPO;
import com.ctp.core.commutils.DateUtils;

public class WBsbfsbBPO  extends CssBaseBPO {

public WBsbfsbBPO() {
super();
}

private static final String TABLENAME = "DB_WSBS.T_WB_SBFSB";

private static  WBsbfsbVO loadConcreteBPO(ResultSet rs) throws SQLException {
 		WBsbfsbVO    vo = new WBsbfsbVO()	 ;
     	vo.setBlbj(rs.getString("BL_BJ"));
     	vo.setPzxh(rs.getString("PZ_XH"));
     	vo.setSbbxh(rs.getString("SBB_XH"));
     	vo.setSbmxxh(String.valueOf(rs.getLong("SBMX_XH")));
     	vo.setSbzspmdm(rs.getString("SBZSPM_DM"));
     	vo.setSbzsxmdm(rs.getString("SBZSXM_DM"));
     	vo.setSbdm(rs.getString("SB_DM"));
     	vo.setSfssqqsrq(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("SFSSQ_QSRQ")));
     	vo.setSfssqzzrq(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("SFSSQ_ZZRQ")));
     	vo.setShbj(rs.getString("SH_BJ"));
     	vo.setSjje(String.valueOf(rs.getDouble("SJ_JE")));
     	vo.setSwglm(String.valueOf(rs.getLong("SWGLM")));
     	vo.setYjje(String.valueOf(rs.getDouble("YJ_JE")));
     	vo.setYzxh(rs.getString("YZ_XH"));
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

private static WBsbfsbVO queryByWhere(Connection con,String tablename,String sqlOrder,String sqlWhere,ArrayList sqlParams,boolean isCount) throws SQLException {

	ResultSet result=null;
	PreparedStatement ps=null;
	result = findAllByWhere(con,tablename,sqlOrder,  sqlWhere, sqlParams,isCount,result,ps);

	WBsbfsbVO vo = null;
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

public static boolean insert(Connection con , WBsbfsbVO vo)throws SQLException {
    return insert(  con ,TABLENAME  , vo);
}

/**
*  根据主键条件删除一条记录
* @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/
public static boolean deleteByPK(Connection con,String PZ_XH, String SBMX_XH) throws SQLException {
	String strSqlWhere="PZ_XH=?  AND SBMX_XH=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(PZ_XH);
 	sqlParams.add(SBMX_XH);

	return delete(con,TABLENAME ,strSqlWhere,sqlParams)  ;
}

/**
*   根据主键条件查询一条记录
* @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/
public static WBsbfsbVO queryByPK(Connection con,String PZ_XH, String SBMX_XH) throws SQLException {
	String strSqlWhere="PZ_XH=?  AND SBMX_XH=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(PZ_XH);
 	sqlParams.add(SBMX_XH);

	return  queryByWhere(con,TABLENAME ,null,strSqlWhere,sqlParams,false) ;
}

/**
*    根据主键条件更新一条记录
* @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/
 public static boolean updateByPK(Connection con,String PZ_XH, String SBMX_XH, WBsbfsbVO vo) throws SQLException {
	String strSqlWhere="PZ_XH=?  AND SBMX_XH=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(PZ_XH);
 	sqlParams.add(SBMX_XH);

	return update(con,TABLENAME ,strSqlWhere,sqlParams,vo) ;
}
////////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////
 public static boolean deleteByPzxh(Connection con,String pzxh) throws SQLException{
		String strSqlWhere="PZ_XH=?";
		ArrayList sqlParams=new ArrayList();
		sqlParams.add(pzxh);
		return delete(con,TABLENAME,strSqlWhere,sqlParams);
	}

 public static List queryByPzxh(Connection con,String pzxh) throws SQLException{
		List retlist = null;
		String strSqlWhere = "PZ_XH=? ";
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(pzxh);
		retlist=queryByZdyWhere(con,TABLENAME,"SBMX_XH",strSqlWhere,sqlParams,false);
		return retlist;
	}

}
