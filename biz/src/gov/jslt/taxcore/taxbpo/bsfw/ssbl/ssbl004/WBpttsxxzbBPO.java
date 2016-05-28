package gov.jslt.taxcore.taxbpo.bsfw.ssbl.ssbl004;
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
import gov.jslt.taxevent.bsfw.ssbl.ssbl004.WBpttsxxzbVO;

public class WBpttsxxzbBPO extends CssBaseBPO {

public WBpttsxxzbBPO() {
super();
}

private static final String TABLENAME = "DB_WSBS.T_XT_TSPT_TSXXZB";

private static  WBpttsxxzbVO loadConcreteBPO(ResultSet rs) throws SQLException {
 		WBpttsxxzbVO    vo = new WBpttsxxzbVO()	 ;
     	vo.setLrrydm(rs.getString("LRRY_DM"));
     	vo.setLsh(rs.getString("LSH"));
     	vo.setQrlx(rs.getString("QR_LX"));
     	vo.setSfbgbj(rs.getString("SFBG_BJ"));
     	vo.setSfshbj(rs.getString("SFSH_BJ"));
     	vo.setSwglm(String.valueOf(rs.getLong("SWGLM")));
     	vo.setTsjgdm(rs.getString("TSJG_DM"));
     	vo.setTsqksm(rs.getString("TSQK_SM"));
     	vo.setTsqrzt(rs.getString("TSQR_ZT"));
     	vo.setTsrydm(rs.getString("TSRY_DM"));
     	vo.setTssjydm(rs.getString("TSSJY_DM"));
     	vo.setTstxnr(rs.getString("TSTX_NR"));
     	vo.setTslx(rs.getString("TS_LX"));
     	vo.setTsrq(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("TS_RQ")));
     	vo.setXgrydm(rs.getString("XGRY_DM"));
     	vo.setYxrqqs(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("YXRQ_QS")));
     	vo.setYxrqzz(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("YXRQ_ZZ")));
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

private static WBpttsxxzbVO queryByWhere(Connection con,String tablename,String sqlOrder,String sqlWhere,ArrayList sqlParams,boolean isCount) throws SQLException {

	ResultSet result=null;
	PreparedStatement ps=null;
	result = findAllByWhere(con,tablename,sqlOrder,  sqlWhere, sqlParams,isCount,result,ps);

	WBpttsxxzbVO vo = null;
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

public static boolean insert(Connection con , WBpttsxxzbVO vo)throws SQLException {
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
public static WBpttsxxzbVO queryByPK(Connection con,String LSH) throws SQLException {
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
 public static boolean updateByPK(Connection con,String LSH, WBpttsxxzbVO vo) throws SQLException {
	String strSqlWhere="LSH=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(LSH);

	return update(con,TABLENAME ,strSqlWhere,sqlParams,vo) ;
}
////////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////
}

