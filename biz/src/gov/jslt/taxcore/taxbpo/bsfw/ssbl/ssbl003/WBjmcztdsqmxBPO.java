package gov.jslt.taxcore.taxbpo.bsfw.ssbl.ssbl003;
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
import gov.jslt.taxevent.bsfw.ssbl.ssbl003.WBjmcztdsqmxVO;

public class WBjmcztdsqmxBPO extends CssBaseBPO {

public WBjmcztdsqmxBPO() {
super();
}

private static final String TABLENAME = "DB_WSBS.T_WB_WS_YH_JMCZTDSQMX";

private static  WBjmcztdsqmxVO loadConcreteBPO(ResultSet rs) throws SQLException {
 		WBjmcztdsqmxVO    vo = new WBjmcztdsqmxVO()	 ;
     	vo.setDwse(String.valueOf(rs.getDouble("DWSE")));
     	vo.setGljgdm(rs.getString("GLJG_DM"));
     	vo.setJmlx(rs.getString("JMLX"));
     	vo.setJmlxdm(rs.getString("JMLX_DM"));
     	vo.setJmrqq(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("JMRQQ")));
     	vo.setJmrqz(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("JMRQZ")));
     	vo.setLsh(rs.getString("LSH"));
     	vo.setMxxh(String.valueOf(rs.getLong("MX_XH")));
     	vo.setNynse(String.valueOf(rs.getDouble("NYNSE")));
     	vo.setSqjmmj(String.valueOf(rs.getDouble("SQJMMJ")));
     	vo.setSqjmse(String.valueOf(rs.getDouble("SQJMSE")));
     	vo.setYsmj(String.valueOf(rs.getDouble("YSMJ")));
     	vo.setZcyj(rs.getString("ZCYJ"));
     	vo.setZcyjxm(rs.getString("ZCYJXM"));
     	vo.setZdmj(String.valueOf(rs.getDouble("ZDMJ")));
     	vo.setZspmdm(rs.getString("ZSPM_DM"));
     	vo.setZsxmdm(rs.getString("ZSXM_DM"));
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

private static WBjmcztdsqmxVO queryByWhere(Connection con,String tablename,String sqlOrder,String sqlWhere,ArrayList sqlParams,boolean isCount) throws SQLException {

	ResultSet result=null;
	PreparedStatement ps=null;
	result = findAllByWhere(con,tablename,sqlOrder,  sqlWhere, sqlParams,isCount,result,ps);

	WBjmcztdsqmxVO vo = null;
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

public static boolean insert(Connection con , WBjmcztdsqmxVO vo)throws SQLException {
    return insert(  con ,TABLENAME  , vo);
}

/**
*  根据主键条件删除一条记录
* @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/
public static boolean deleteByPK(Connection con,String LSH, String MX_XH) throws SQLException {
	String strSqlWhere="LSH=?  AND MX_XH=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(LSH);
 	sqlParams.add(MX_XH);

	return delete(con,TABLENAME ,strSqlWhere,sqlParams)  ;
}

/**
*   根据主键条件查询一条记录
* @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/
public static WBjmcztdsqmxVO queryByPK(Connection con,String LSH, String MX_XH) throws SQLException {
	String strSqlWhere="LSH=?  AND MX_XH=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(LSH);
 	sqlParams.add(MX_XH);

	return  queryByWhere(con,TABLENAME ,null,strSqlWhere,sqlParams,false) ;
}

/**
*    根据主键条件更新一条记录
* @param 
* @param con:Connection 与数据库建立的连接
* @throws SQLException
*/
 public static boolean updateByPK(Connection con,String LSH, String MX_XH, WBjmcztdsqmxVO vo) throws SQLException {
	String strSqlWhere="LSH=?  AND MX_XH=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(LSH);
 	sqlParams.add(MX_XH);

	return update(con,TABLENAME ,strSqlWhere,sqlParams,vo) ;
}
////////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////
}

