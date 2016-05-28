package gov.jslt.taxcore.taxbpo.bsfw.sbns.sbns003;
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
import gov.jslt.taxevent.bsfw.sbns.sbns003.WBjbxxbwzsVO;

public class WBjbxxbwzsBPO extends CssBaseBPO {

public WBjbxxbwzsBPO() {
super();
}

private static final String TABLENAME = "DB_WSBS.T_WB_GRXX_B_WZS";

private static  WBjbxxbwzsVO loadConcreteBPO(ResultSet rs) throws SQLException {
 		WBjbxxbwzsVO    vo = new WBjbxxbwzsVO()	 ;
     	vo.setCsd(rs.getString("CSD"));
     	vo.setCsrq(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("CSRQ")));
     	vo.setGjdqdm(rs.getString("GJDQ_DM"));
     	vo.setJnzwdm(rs.getString("JNZW_DM"));
     	vo.setJwzwdm(rs.getString("JWZW_DM"));
     	vo.setJwzwgb(rs.getString("JWZW_GB"));
     	vo.setLdjyzhm(rs.getString("LDJYZHM"));
     	vo.setLhsj(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("LHSJ")));
     	vo.setLjdd(rs.getString("LJDD"));
     	vo.setLjrq(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("LJRQ")));
     	vo.setNsrsbm(rs.getString("NSRSBM"));
     	vo.setPqdwdz(rs.getString("PQDW_DZ"));
     	vo.setPqdwmc(rs.getString("PQDW_MC"));
     	vo.setPzxh(rs.getString("PZ_XH"));
     	vo.setQydwdz(rs.getString("QYDW_DZ"));
     	vo.setQydwmc(rs.getString("QYDW_MC"));
     	vo.setQydwswglm(String.valueOf(rs.getLong("QYDW_SWGLM")));
     	vo.setQydwyzbm(rs.getString("QYDW_YZBM"));
     	vo.setRzdwdz(rs.getString("RZDW_DZ"));
     	vo.setRzdwmc(rs.getString("RZDW_MC"));
     	vo.setRzdwswglm(String.valueOf(rs.getLong("RZDW_SWGLM")));
     	vo.setRzdwyzbm(rs.getString("RZDW_YZBM"));
     	vo.setRzqx(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("RZQX")));
     	vo.setSfxddyg(rs.getString("SFXDDYG"));
     	vo.setXb(rs.getString("XB"));
     	vo.setXgjgdm(rs.getString("XGJG_DM"));
     	vo.setXgrydm(rs.getString("XGRY_DM"));
     	vo.setZfd(rs.getString("ZFD"));
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

private static WBjbxxbwzsVO queryByWhere(Connection con,String tablename,String sqlOrder,String sqlWhere,ArrayList sqlParams,boolean isCount) throws SQLException {

	ResultSet result=null;
	PreparedStatement ps=null;
	result = findAllByWhere(con,tablename,sqlOrder,  sqlWhere, sqlParams,isCount,result,ps);

	WBjbxxbwzsVO vo = null;
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

public static boolean insert(Connection con , WBjbxxbwzsVO vo)throws SQLException {
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
public static WBjbxxbwzsVO queryByPK(Connection con,String PZ_XH) throws SQLException {
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
 public static boolean updateByPK(Connection con,String PZ_XH, WBjbxxbwzsVO vo) throws SQLException {
	String strSqlWhere="PZ_XH=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(PZ_XH);

	return update(con,TABLENAME ,strSqlWhere,sqlParams,vo) ;
}
////////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////
}

