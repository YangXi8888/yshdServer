package gov.jslt.taxcore.taxbpo.bsfw.ssbl.ssbl002;

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
import com.ctp.core.commutils.DateUtils;
import com.ctp.core.log.LogWritter;

import gov.jslt.taxevent.bsfw.ssbl.ssbl002.Ssbl002jmssqmxVO;

public class Ssbl002jmssqmxBPO extends CssBaseBPO {

	public Ssbl002jmssqmxBPO() {
		super();
	}

	private static final String TABLENAME = "DB_WSBS.T_WB_WS_YH_JMSQMX";

	private static Ssbl002jmssqmxVO loadConcreteBPO(ResultSet rs)
			throws SQLException {
		Ssbl002jmssqmxVO vo = new Ssbl002jmssqmxVO();
		vo.setGljgdm(rs.getString("GLJG_DM"));
		vo.setJmfs(rs.getString("JMFS"));
		vo.setJmlxdm(rs.getString("JMLX_DM"));
		vo.setJmxmdm(rs.getString("JMXM_DM"));
		vo.setJmyeje(String.valueOf(rs.getDouble("JMYE_JE")));
		vo.setJmyjbz(rs.getString("JMYJ_BZ"));
		vo.setJmzb(rs.getString("JMZB"));
		vo.setJsyj(String.valueOf(rs.getDouble("JS_YJ")));
		vo.setLsh(rs.getString("LSH"));
		vo.setMxxh(String.valueOf(rs.getLong("MX_XH")));
		vo.setNsqxrdm(rs.getString("NSQXR_DM"));
		vo.setPzjmfd(String.valueOf(rs.getDouble("PZJMFD")));
		vo.setPzjmqxsqsrq(DateUtils.convSqlTimestampToUtilCalendar(rs
				.getTimestamp("PZJMQXS_QSRQ")));
		vo.setPzjmqxzzzrq(DateUtils.convSqlTimestampToUtilCalendar(rs
				.getTimestamp("PZJMQXZ_ZZRQ")));
		vo.setPzjmsx(String.valueOf(rs.getDouble("PZJM_SX")));
		vo.setSfssqqsrq(DateUtils.convSqlTimestampToUtilCalendar(rs
				.getTimestamp("SFSSQ_QSRQ")));
		vo.setSfssqzzrq(DateUtils.convSqlTimestampToUtilCalendar(rs
				.getTimestamp("SFSSQ_ZZRQ")));
		vo.setSl(String.valueOf(rs.getDouble("SL")));
		vo.setSqjmfd(String.valueOf(rs.getDouble("SQJMFD")));
		vo.setSqjmqxqsrq(DateUtils.convSqlTimestampToUtilCalendar(rs
				.getTimestamp("SQJMQX_QSRQ")));
		vo.setSqjmqxzzrq(DateUtils.convSqlTimestampToUtilCalendar(rs
				.getTimestamp("SQJMQX_ZZRQ")));
		vo.setSqjmje(String.valueOf(rs.getDouble("SQJM_JE")));
		vo.setZcyj(rs.getString("ZCYJ"));
		vo.setZcyjxm(rs.getString("ZCYJXM"));
		vo.setZcyjwh(rs.getString("ZCYJWH"));
		vo.setZspmdm(rs.getString("ZSPM_DM"));
		vo.setZsxmdm(rs.getString("ZSXM_DM"));
		vo.setZjyxm(rs.getString("ZJYXM"));
		vo.setZjlx(rs.getString("ZJLX"));
		vo.setZjhm(rs.getString("ZJHM"));
		vo.setStatus(new HashMap());
		return vo;

	}

	/**
	 * 返回一条记录的自定义查询;
	 * 
	 * @param
	 * @param con:Connection
	 *            与数据库建立的连接
	 * @param String
	 *            tablename 表
	 * @param String
	 *            sqlOrder 排序字段
	 * @param String
	 *            sqlWhere 条件 sql
	 * @param ArrayList
	 *            sqlParams 条件数据
	 * @param boolean
	 *            isCount false
	 * @throws SQLException
	 *             修改历史： 版本0.5 2008-4-24周红江 : 1.将原来的findAll废弃 因为其存在内存浪费，2.不再使用
	 *             CachedRowSet 3.在子类中申明ResultSet,PreparedStatement 在子类中释放;
	 */

	private static Ssbl002jmssqmxVO queryByWhere(Connection con, String tablename,
			String sqlOrder, String sqlWhere, ArrayList sqlParams,
			boolean isCount) throws SQLException {

		ResultSet result = null;
		PreparedStatement ps = null;
		result = findAllByWhere(con, tablename, sqlOrder, sqlWhere, sqlParams,
				isCount, result, ps);

		Ssbl002jmssqmxVO vo = null;
		if (result != null) {
			if (result.next())
				vo = loadConcreteBPO(result);
		}
		closeDbsession(result, ps);
		;
		return vo;
	}

	/**
	 * 返回多条记录的自定义查询;
	 * 
	 * @param
	 * @param con:Connection
	 *            与数据库建立的连接
	 * @param String
	 *            tablename 表
	 * @param String
	 *            sqlOrder 排序字段
	 * @param String
	 *            sqlWhere 条件 sql
	 * @param ArrayList
	 *            sqlParams 条件数据
	 * @param boolean
	 *            isCount false
	 * @throws SQLException
	 *             修改历史： 版本0.5 2008-4-24周红江 : 1.将原来的findAll废弃 因为其存在内存浪费 ，2.不再使用
	 *             CachedRowSet 3.在子类中申明ResultSet,PreparedStatement 在子类中释放;
	 */

	private static ArrayList queryByZdyWhere(Connection con, String tablename,
			String sqlOrder, String sqlWhere, ArrayList sqlParams,
			boolean isCount) throws SQLException {

		ResultSet result = null;
		PreparedStatement ps = null;
		result = findAllByWhere(con, tablename, sqlOrder, sqlWhere, sqlParams,
				isCount, result, ps);

		ArrayList listLodadata = null;
		if (result != null) {
			listLodadata = new ArrayList();
			while (result.next())
				listLodadata.add(loadConcreteBPO(result));
		}
		closeDbsession(result, ps);
		return listLodadata;
	}

	// //////////////////////////////////////以下为业 务方法
	// //////////////////////////////////////////////////////////////

	/**
	 * 增加一条新记录;
	 * 
	 * @param
	 * @param con:Connection
	 *            与数据库建立的连接
	 * @throws SQLException
	 */

	public static boolean insert(Connection con, Ssbl002jmssqmxVO vo)
			throws SQLException {
		return insert(con, TABLENAME, vo);
	}

	/**
	 * 根据主键条件删除一条记录
	 * 
	 * @param
	 * @param con:Connection
	 *            与数据库建立的连接
	 * @throws SQLException
	 */
	public static boolean deleteByPK(Connection con, String LSH, String MX_XH)
			throws SQLException {
		String strSqlWhere = "LSH=?  AND MX_XH=? ";
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(LSH);
		sqlParams.add(MX_XH);

		return delete(con, TABLENAME, strSqlWhere, sqlParams);
	}

	/**
	 * 根据主键条件查询一条记录
	 * 
	 * @param
	 * @param con:Connection
	 *            与数据库建立的连接
	 * @throws SQLException
	 */
	public static Ssbl002jmssqmxVO queryByPK(Connection con, String LSH, String MX_XH)
			throws SQLException {
		String strSqlWhere = "LSH=?  AND MX_XH=? ";
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(LSH);
		sqlParams.add(MX_XH);

		return queryByWhere(con, TABLENAME, null, strSqlWhere, sqlParams, false);
	}

	/**
	 * 根据主键条件更新一条记录
	 * 
	 * @param
	 * @param con:Connection
	 *            与数据库建立的连接
	 * @throws SQLException
	 */
	public static boolean updateByPK(Connection con, String LSH, String MX_XH,
			Ssbl002jmssqmxVO vo) throws SQLException {
		String strSqlWhere = "LSH=?  AND MX_XH=? ";
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(LSH);
		sqlParams.add(MX_XH);

		return update(con, TABLENAME, strSqlWhere, sqlParams, vo);
	}

	// //////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////
	public static List getListByPK(Connection con, String LSH)
			throws SQLException {
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(LSH);
		List list = queryByZdyWhere(con, TABLENAME, null, "LSH=?", sqlParams,
				false);
		if (null == list) {
			list = new ArrayList();
		}
		return list;
	}

	/**
	 * 根据主键条件删除所有记录
	 * 
	 * @param
	 * @param con:Connection
	 *            与数据库建立的连接
	 * @throws SQLException
	 */
	public static boolean deleteByPK(Connection con, String LSH)
			throws SQLException {
		String strSqlWhere = "LSH=?";
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(LSH);

		return delete(con, TABLENAME, strSqlWhere, sqlParams);
	}
}
