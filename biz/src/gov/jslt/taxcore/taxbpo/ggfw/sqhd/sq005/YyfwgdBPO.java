package gov.jslt.taxcore.taxbpo.ggfw.sqhd.sq005;
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
import gov.jslt.taxevent.ggfw.sqhd.sq005.YyfwgdVO;

public class YyfwgdBPO extends CssBaseBPO {

public YyfwgdBPO() {
super();
}

private static final String TABLENAME = "DB_WSBS.T_WB_YYFWSQB";

private static  YyfwgdVO loadConcreteBPO(ResultSet rs) throws SQLException {
 		YyfwgdVO    vo = new YyfwgdVO()	 ;
     	vo.setBz(rs.getString("BZ"));
     	vo.setCxmm(rs.getString("CXMM"));
     	vo.setDwmc(rs.getString("DWMC"));
     	vo.setLrrydm(rs.getString("LRRY_DM"));
     	vo.setLsh(rs.getString("LSH"));
     	vo.setLxdh(rs.getString("LXDH"));
     	vo.setLyqd(rs.getString("LYQD"));
     	vo.setNssbh(rs.getString("NSSBH"));
     	vo.setXgrydm(rs.getString("XGRY_DM"));
     	vo.setYwms(rs.getString("YWMS"));
     	vo.setYyds(rs.getString("YYDS"));
     	vo.setYymc(rs.getString("YYMC"));
     	vo.setYyqx(rs.getString("YYQX"));
     	vo.setYyrmc(rs.getString("YYRMC"));
     	vo.setYyrq(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("YYRQ")));
     	vo.setYysj(rs.getString("YYSJ"));
     	vo.setYysxdm(rs.getString("YYSXDM"));
     	vo.setZcdz(rs.getString("ZCDZ"));
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

private static YyfwgdVO queryByWhere(Connection con,String tablename,String sqlOrder,String sqlWhere,ArrayList sqlParams,boolean isCount) throws SQLException {

	ResultSet result=null;
	PreparedStatement ps=null;
	result = findAllByWhere(con,tablename,sqlOrder,  sqlWhere, sqlParams,isCount,result,ps);

	YyfwgdVO vo = null;
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

public static boolean insert(Connection con , YyfwgdVO vo)throws SQLException {
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
public static YyfwgdVO queryByPK(Connection con,String LSH) throws SQLException {
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
 public static boolean updateByPK(Connection con,String LSH, YyfwgdVO vo) throws SQLException {
	String strSqlWhere="LSH=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(LSH);

	return update(con,TABLENAME ,strSqlWhere,sqlParams,vo) ;
}
////////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////
}

