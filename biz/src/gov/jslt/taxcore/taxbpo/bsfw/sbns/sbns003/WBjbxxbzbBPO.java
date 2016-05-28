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
import gov.jslt.taxevent.bsfw.sbns.sbns003.WBjbxxbzbVO;

public class WBjbxxbzbBPO extends CssBaseBPO {

public WBjbxxbzbBPO() {
super();
}

private static final String TABLENAME = "DB_WSBS.T_WB_GRXX_B_ZB";

private static  WBjbxxbzbVO loadConcreteBPO(ResultSet rs) throws SQLException {
 		WBjbxxbzbVO    vo = new WBjbxxbzbVO()	 ;
     	vo.setBz(rs.getString("BZ"));
     	vo.setCjqk(rs.getString("CJQK"));
     	vo.setDzyx(rs.getString("DZYX"));
     	vo.setFwjgdm(rs.getString("FWJG_DM"));
     	vo.setGh(rs.getString("GH"));
     	vo.setGjdqdm(rs.getString("GJDQ_DM"));
     	vo.setGljgdm(rs.getString("GLJG_DM"));
     	vo.setGrsbh(rs.getString("GRSBH"));
     	vo.setJcjgdm(rs.getString("JCJG_DM"));
     	vo.setJiancjgdm(rs.getString("JIANCJG_DM"));
     	vo.setLxdz(rs.getString("LXDZ"));
     	vo.setLxdzsjdm(rs.getString("LXDZ_SJDM"));
     	vo.setLxdzsqdm(rs.getString("LXDZ_SQDM"));
     	vo.setLxdzxjdm(rs.getString("LXDZ_XJDM"));
     	vo.setLxdzxzdm(rs.getString("LXDZ_XZDM"));
     	vo.setPzxh(rs.getString("PZ_XH"));
     	vo.setQysj(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("QY_SJ")));
     	vo.setQyzt(rs.getString("QY_ZT"));
     	vo.setRzdwmc(rs.getString("RZDW_MC"));
     	vo.setRzdwnsrsbm(rs.getString("RZDW_NSRSBM"));
     	vo.setRzdwswglm(String.valueOf(rs.getLong("RZDW_SWGLM")));
     	vo.setSfcj(rs.getString("SFCJ"));
     	vo.setSfssqqsrq(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("SFSSQ_QSRQ")));
     	vo.setSfssqzzrq(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("SFSSQ_ZZRQ")));
     	vo.setSfzhm(rs.getString("SFZHM"));
     	vo.setSfzlxdm(rs.getString("SFZLX_DM"));
     	vo.setSj(rs.getString("SJ"));
     	vo.setSjly(rs.getString("SJLY"));
     	vo.setSkssjgdm(rs.getString("SKSSJG_DM"));
     	vo.setXgjgdm(rs.getString("XGJG_DM"));
     	vo.setXgrydm(rs.getString("XGRY_DM"));
     	vo.setXldm(rs.getString("XL_DM"));
     	vo.setXm(rs.getString("XM"));
     	vo.setYzbm(rs.getString("YZBM"));
     	vo.setZrrswglm(String.valueOf(rs.getLong("ZRR_SWGLM")));
     	vo.setZsjgdm(rs.getString("ZSJG_DM"));
     	vo.setZwdm(rs.getString("ZW_DM"));
     	vo.setZxbj(rs.getString("ZX_BJ"));
     	vo.setZxrq(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("ZX_RQ")));
     	vo.setZydm(rs.getString("ZY_DM"));
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

private static WBjbxxbzbVO queryByWhere(Connection con,String tablename,String sqlOrder,String sqlWhere,ArrayList sqlParams,boolean isCount) throws SQLException {

	ResultSet result=null;
	PreparedStatement ps=null;
	result = findAllByWhere(con,tablename,sqlOrder,  sqlWhere, sqlParams,isCount,result,ps);

	WBjbxxbzbVO vo = null;
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

public static boolean insert(Connection con , WBjbxxbzbVO vo)throws SQLException {
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
public static WBjbxxbzbVO queryByPK(Connection con,String PZ_XH) throws SQLException {
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
 public static boolean updateByPK(Connection con,String PZ_XH, WBjbxxbzbVO vo) throws SQLException {
	String strSqlWhere="PZ_XH=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(PZ_XH);

	return update(con,TABLENAME ,strSqlWhere,sqlParams,vo) ;
}
////////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////
}

