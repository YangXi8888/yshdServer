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
import gov.jslt.taxevent.bsfw.sbns.sbns003.WBgrsdssbabVO;

public class WBgrsdssbabBPO extends CssBaseBPO {

public WBgrsdssbabBPO() {
super();
}

private static final String TABLENAME = "DB_WSBS.T_WB_GRSDSSBB_A";

private static  WBgrsdssbabVO loadConcreteBPO(ResultSet rs) throws SQLException {
 		WBgrsdssbabVO    vo = new WBgrsdssbabVO()	 ;
     	vo.setCcyzje(String.valueOf(rs.getDouble("CCYZ_JE")));
     	vo.setFwjgdm(rs.getString("FWJG_DM"));
     	vo.setGjdqdm(rs.getString("GJDQ_DM"));
     	vo.setGljgdm(rs.getString("GLJG_DM"));
     	vo.setHbylbxfje(String.valueOf(rs.getDouble("HBYLBXF_JE")));
     	vo.setHjje(String.valueOf(rs.getDouble("HJ_JE")));
     	vo.setJbylbxfje(String.valueOf(rs.getDouble("JBYLBXF_JE")));
     	vo.setJcfyje(String.valueOf(rs.getDouble("JCFY_JE")));
     	vo.setJcjgdm(rs.getString("JCJG_DM"));
     	vo.setJiancjgdm(rs.getString("JIANCJG_DM"));
     	vo.setJmseje(String.valueOf(rs.getDouble("JMSE_JE")));
     	vo.setMssdeje(String.valueOf(rs.getDouble("MSSDE_JE")));
     	vo.setNsrsbm(rs.getString("NSRSBM"));
     	vo.setPzxh(rs.getString("PZ_XH"));
     	vo.setQtje(String.valueOf(rs.getDouble("QT_JE")));
     	vo.setQyzt(rs.getString("QYZT"));
     	vo.setRzsgdwmc(rs.getString("RZSGDW_MC"));
     	vo.setSbbxh(rs.getString("SBB_XH"));
     	vo.setSbmxxh(String.valueOf(rs.getLong("SBMX_XH")));
     	vo.setSbqx(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("SB_QX")));
     	vo.setSdqqsrq(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("SDQ_QSRQ")));
     	vo.setSdqzzrq(DateUtils.convSqlTimestampToUtilCalendar(rs.getTimestamp("SDQ_ZZRQ")));
     	vo.setSfzjhm(rs.getString("SFZJ_HM"));
     	vo.setSfzjlx(rs.getString("SFZJ_LX"));
     	vo.setShbj(rs.getString("SH_BJ"));
     	vo.setSkssjgdm(rs.getString("SKSSJG_DM"));
     	vo.setSl(String.valueOf(rs.getDouble("SL")));
     	vo.setSreje(String.valueOf(rs.getDouble("SRE_JE")));
     	vo.setSskcs(String.valueOf(rs.getDouble("SSKCS")));
     	vo.setSwglm(String.valueOf(rs.getLong("SWGLM")));
     	vo.setSybxfje(String.valueOf(rs.getDouble("SYBXF_JE")));
     	vo.setYbtsseje(String.valueOf(rs.getDouble("YBTSSE_JE")));
     	vo.setYjseje(String.valueOf(rs.getDouble("YJSE_JE")));
     	vo.setYnseje(String.valueOf(rs.getDouble("YNSE_JE")));
     	vo.setYnssdeje(String.valueOf(rs.getDouble("YNSSDE_JE")));
     	vo.setYxkcdsfje(String.valueOf(rs.getDouble("YXKCDSF_JE")));
     	vo.setZfgjjje(String.valueOf(rs.getDouble("ZFGJJ_JE")));
     	vo.setZsjgdm(rs.getString("ZSJG_DM"));
     	vo.setZspmdm(rs.getString("ZSPM_DM"));
     	vo.setZsxmdm(rs.getString("ZSXM_DM"));
     	vo.setZwmc(rs.getString("ZWMC"));
     	vo.setZxsbqx(rs.getString("ZXSBQX"));
     	vo.setZykcdjzeje(String.valueOf(rs.getDouble("ZYKCDJZE_JE")));
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

private static WBgrsdssbabVO queryByWhere(Connection con,String tablename,String sqlOrder,String sqlWhere,ArrayList sqlParams,boolean isCount) throws SQLException {

	ResultSet result=null;
	PreparedStatement ps=null;
	result = findAllByWhere(con,tablename,sqlOrder,  sqlWhere, sqlParams,isCount,result,ps);

	WBgrsdssbabVO vo = null;
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

public static boolean insert(Connection con , WBgrsdssbabVO vo)throws SQLException {
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
public static WBgrsdssbabVO queryByPK(Connection con,String PZ_XH, String SBMX_XH) throws SQLException {
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
 public static boolean updateByPK(Connection con,String PZ_XH, String SBMX_XH, WBgrsdssbabVO vo) throws SQLException {
	String strSqlWhere="PZ_XH=?  AND SBMX_XH=? ";
	ArrayList sqlParams=new ArrayList();
 	sqlParams.add(PZ_XH);
 	sqlParams.add(SBMX_XH);

	return update(con,TABLENAME ,strSqlWhere,sqlParams,vo) ;
}
////////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////
}

