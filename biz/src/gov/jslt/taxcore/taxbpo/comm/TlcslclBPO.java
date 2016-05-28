package gov.jslt.taxcore.taxbpo.comm;

import gov.jslt.taxevent.comm.TlcslclVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ctp.core.bpo.CssBaseBPO;

public class TlcslclBPO extends CssBaseBPO {

	public TlcslclBPO() {
		super();
	}

	private static final String TABLENAME = "DB_WSBS.T_LCSLCL";

	private static TlcslclVO loadConcreteBPO(ResultSet rs) throws SQLException {
		TlcslclVO vo = new TlcslclVO();
		vo.setCjsj(rs.getString("CJSJ"));
		vo.setClly(rs.getString("CLLY"));
		vo.setCzlsh(rs.getString("CZ_LSH"));
		vo.setFbzldm(rs.getString("FBZLDM"));
		vo.setLcslclmc(rs.getString("LCSLCL_MC"));
		vo.setLcslh(rs.getString("LCSLH"));
		vo.setLsh(rs.getString("LSH"));
		vo.setScbj(String.valueOf(rs.getLong("SCBJ")));
		vo.setSczt(String.valueOf(rs.getLong("SC_ZT")));
		vo.setSsclbh(rs.getString("SSCLBH"));
		vo.setTxm(rs.getString("TXM"));
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

	private static TlcslclVO queryByWhere(Connection con, String tablename,
			String sqlOrder, String sqlWhere, ArrayList sqlParams,
			boolean isCount) throws SQLException {

		ResultSet result = null;
		PreparedStatement ps = null;
		result = findAllByWhere(con, tablename, sqlOrder, sqlWhere, sqlParams,
				isCount, result, ps);

		TlcslclVO vo = null;
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

	public static boolean insert(Connection con, TlcslclVO vo)
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
	public static TlcslclVO queryByPK(Connection con, String LCSLH,String fbzldm)
			throws SQLException {
		String strSqlWhere = "LCSLH=?  AND FBZLDM =? ";
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(LCSLH);
		sqlParams.add(fbzldm);

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
	public static boolean updateByPK(Connection con, String LSH, TlcslclVO vo)
			throws SQLException {
		String strSqlWhere = "LSH=? ";
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(LSH);

		return update(con, TABLENAME, strSqlWhere, sqlParams, vo);
	}

	// //////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////

	public static boolean deleteByPK(Connection con, String LCSLH, String FBZLDM)
			throws SQLException {
		String strSqlWhere = "LCSLH=?  AND FBZLDM = ?";
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(LCSLH);
		sqlParams.add(FBZLDM);
		return delete(con, TABLENAME, strSqlWhere, sqlParams);
	}

}
