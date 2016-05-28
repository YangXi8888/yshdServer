package gov.jslt.taxcore.taxbpo.ggfw.bsdh.bsdh002;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.bpo.CssBaseBPO;
import com.ctp.core.bpo.CssBaseBpoVO;
import com.ctp.core.bpo.QueryCssBPO;
import com.ctp.core.commutils.DateUtils;
import com.ctp.core.log.LogWritter;

import gov.jslt.taxevent.ggfw.bsdh.bsdh002.Bsdh002VO;

import java.util.List;

public class Bsdh002BPO extends CssBaseBPO {

public Bsdh002BPO() {
super();
}

private static final String TABLENAME = "T_ZB_BSDT";

private static  Bsdh002VO loadConcreteBPO(ResultSet rs) throws SQLException {
 		Bsdh002VO    vo = new Bsdh002VO()	 ;
     	vo.setBj(rs.getString("BJ"));
     	vo.setBssjq(rs.getString("BSSJQ"));
     	vo.setBsyw(rs.getString("BSYW"));
     	vo.setDh(rs.getString("DH"));
     	vo.setDsdm(rs.getString("DS_DM"));
     	vo.setDsdmswjg(rs.getString("DS_DM_SWJG"));
     	vo.setDz(rs.getString("DZ"));
     	vo.setId(String.valueOf(rs.getLong("ID")));
     	vo.setMcj(rs.getString("MC_J"));
     	vo.setPid(String.valueOf(rs.getLong("PID")));
     	vo.setYb(rs.getString("YB"));
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

private static Bsdh002VO queryByWhere(Connection con,String tablename,String sqlOrder,String sqlWhere,ArrayList sqlParams,boolean isCount) throws SQLException {

	ResultSet result=null;
	PreparedStatement ps=null;
	result = findAllByWhere(con,tablename,sqlOrder,  sqlWhere, sqlParams,isCount,result,ps);

	Bsdh002VO vo = null;
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
////////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////
public static List getListByPK(Connection con, String ds_dm, int pid)
		throws SQLException {
	ArrayList sqlParams = new ArrayList();
	sqlParams.add(ds_dm);
	sqlParams.add(pid);
	List list = null;
	CachedRowSet rs=QueryCssBPO.findAll(con, "select DISTINCT PID,DS_DM,DZ,YB,DH,BSSJQ,BSYW,MC_J,BJ,DS_DM_SWJG,ID from DB_ZSBS.T_ZB_BSDT where DS_DM_SWJG=? AND PID=?", sqlParams);
	if(rs !=null){
		list = new ArrayList();
		while(rs.next()){
			list.add(loadConcreteBPO(rs));
		}
	}
	return list;
}
}

