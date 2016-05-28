package gov.jslt.taxcore.taxbpo.comm;

import gov.jslt.taxevent.comm.TlcslVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ctp.core.bpo.CssBaseBPO;
import com.ctp.core.commutils.DateUtils;

public class TlcslBPO extends CssBaseBPO {

	public TlcslBPO() {
		super();
	}

	private static final String TABLENAME = "DB_WSBS.T_LCSL";

	private static TlcslVO loadConcreteBPO(ResultSet rs) throws SQLException {
		TlcslVO vo = new TlcslVO();
		vo.setCjsjsj(DateUtils.convSqlTimestampToUtilCalendar(rs
				.getTimestamp("CJSJ_SJ")));
		vo.setClly(String.valueOf(rs.getLong("CLLY")));
		vo.setGdbj(String.valueOf(rs.getLong("GD_BJ")));
		vo.setGdsj(DateUtils.convSqlTimestampToUtilCalendar(rs
				.getTimestamp("GD_SJ")));
		vo.setGljgdm(rs.getString("GLJG_DM"));
		vo.setLcslh(rs.getString("LCSLH"));
		vo.setLsh(rs.getString("LSH"));
		vo.setNsrmc(rs.getString("NSRMC"));
		vo.setNsrsbh(rs.getString("NSRSBH"));
		vo.setScbj(String.valueOf(rs.getLong("SC_BJ")));
		vo.setSsrq(rs.getString("SSRQ"));
		vo.setSssxlsh(rs.getString("SSSX_LSH"));
		vo.setSwglm(String.valueOf(rs.getLong("SWGLM")));
		vo.setSwrydm(rs.getString("SWRY_DM"));
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

	private static TlcslVO queryByWhere(Connection con, String tablename,
			String sqlOrder, String sqlWhere, ArrayList sqlParams,
			boolean isCount) throws SQLException {

		ResultSet result = null;
		PreparedStatement ps = null;
		result = findAllByWhere(con, tablename, sqlOrder, sqlWhere, sqlParams,
				isCount, result, ps);

		TlcslVO vo = null;
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

	public static boolean insert(Connection con, TlcslVO vo)
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
	public static boolean deleteByPK(Connection con, String LSH)
			throws SQLException {
		String strSqlWhere = "LSH=? ";
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(LSH);

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
	public static TlcslVO queryByPK(Connection con, String LSH)
			throws SQLException {
		String strSqlWhere = "LSH=? ";
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(LSH);

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
	public static boolean updateByPK(Connection con, String LSH, TlcslVO vo)
			throws SQLException {
		String strSqlWhere = "LSH=? ";
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(LSH);

		return update(con, TABLENAME, strSqlWhere, sqlParams, vo);
	}
	// //////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////
}
