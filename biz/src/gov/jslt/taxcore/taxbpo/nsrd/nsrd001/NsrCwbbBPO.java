package gov.jslt.taxcore.taxbpo.nsrd.nsrd001;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ctp.core.bpo.CssBaseBPO;

import gov.jslt.taxevent.nsrd.nsrd001.NsrCwbbVO;

public class NsrCwbbBPO extends CssBaseBPO {

	public NsrCwbbBPO() {
		super();
	}

	private static final String TABLENAME = "T_YS_NSRFS_CWBB";

	private static NsrCwbbVO loadConcreteBPO(ResultSet rs) throws SQLException {
		NsrCwbbVO vo = new NsrCwbbVO();
		vo.setFzze(rs.getString("FZZE"));
		vo.setKjlr(rs.getString("KJLR"));
		vo.setNsrsbm(rs.getString("NSRSBM"));
		vo.setNsrmc(rs.getString("NSR_MC"));
		vo.setSsnd(rs.getString("SSND"));
		vo.setSszb(rs.getString("SSZB"));
		vo.setSwglm(String.valueOf(rs.getLong("SWGLM")));
		vo.setUuid(rs.getString("UUID"));
		vo.setZbgj(rs.getString("ZBGJ"));
		vo.setZbuuid(rs.getString("ZB_UUID"));
		vo.setZcze(rs.getString("ZCZE"));
		vo.setZyywcb(rs.getString("ZYYWCB"));
		vo.setZyywsr(rs.getString("ZYYWSR"));
		vo.setStatus(new HashMap<String, String>());
		return vo;

	}

	/**
	 * 返回一条记录的自定义查询;
	 * 
	 * @param
	 * @param con
	 *            :Connection 与数据库建立的连接
	 * @param String
	 *            tablename 表
	 * @param String
	 *            sqlOrder 排序字段
	 * @param String
	 *            sqlWhere 条件 sql
	 * @param ArrayList
	 *            sqlParams 条件数据
	 * @param boolean isCount false
	 * @throws SQLException
	 *             修改历史： 版本0.5 2008-4-24周红江 : 1.将原来的findAll废弃 因为其存在内存浪费，2.不再使用 CachedRowSet
	 *             3.在子类中申明ResultSet,PreparedStatement 在子类中释放;
	 */

	private static NsrCwbbVO queryByWhere(Connection con, String tablename,
			String sqlOrder, String sqlWhere, ArrayList<String> sqlParams,
			boolean isCount) throws SQLException {
		ResultSet result = null;
		PreparedStatement ps = null;
		result = findAllByWhere(con, tablename, sqlOrder, sqlWhere, sqlParams,
				isCount, result, ps);
		NsrCwbbVO vo = null;
		if (result != null) {
			if (result.next())
				vo = loadConcreteBPO(result);
		}
		closeDbsession(result, ps);
		return vo;
	}

	/**
	 * 返回多条记录的自定义查询;
	 * 
	 * @param
	 * @param con
	 *            :Connection 与数据库建立的连接
	 * @param String
	 *            tablename 表
	 * @param String
	 *            sqlOrder 排序字段
	 * @param String
	 *            sqlWhere 条件 sql
	 * @param ArrayList
	 *            sqlParams 条件数据
	 * @param boolean isCount false
	 * @throws SQLException
	 *             修改历史： 版本0.5 2008-4-24周红江 : 1.将原来的findAll废弃 因为其存在内存浪费 ，2.不再使用 CachedRowSet
	 *             3.在子类中申明ResultSet,PreparedStatement 在子类中释放;
	 */

	private static ArrayList<NsrCwbbVO> queryByZdyWhere(Connection con,
			String tablename, String sqlOrder, String sqlWhere,
			ArrayList<String> sqlParams, boolean isCount) throws SQLException {
		ResultSet result = null;
		PreparedStatement ps = null;
		result = findAllByWhere(con, tablename, sqlOrder, sqlWhere, sqlParams,
				isCount, result, ps);
		ArrayList<NsrCwbbVO> listLodadata = null;
		if (result != null) {
			listLodadata = new ArrayList<NsrCwbbVO>();
			while (result.next())
				listLodadata.add(loadConcreteBPO(result));
		}
		closeDbsession(result, ps);
		return listLodadata;
	}

	/**
	 * 增加一条新记录;
	 * 
	 * @param
	 * @param con
	 *            :Connection 与数据库建立的连接
	 * @throws SQLException
	 */

	public static boolean insert(Connection con, NsrCwbbVO vo)
			throws SQLException {
		return insert(con, TABLENAME, vo);
	}

	/**
	 * 根据主键条件删除一条记录
	 * 
	 * @param
	 * @param con
	 *            :Connection 与数据库建立的连接
	 * @throws SQLException
	 */
	public static boolean deleteByPK(Connection con, String UUID)
			throws SQLException {
		String strSqlWhere = "UUID=? ";
		ArrayList<String> sqlParams = new ArrayList<String>();
		sqlParams.add(UUID);
		return delete(con, TABLENAME, strSqlWhere, sqlParams);
	}

	/**
	 * 根据主键条件查询一条记录
	 * 
	 * @param
	 * @param con
	 *            :Connection 与数据库建立的连接
	 * @throws SQLException
	 */
	public static NsrCwbbVO queryByPK(Connection con, String UUID)
			throws SQLException {
		String strSqlWhere = "UUID=? ";
		ArrayList<String> sqlParams = new ArrayList<String>();
		sqlParams.add(UUID);
		return queryByWhere(con, TABLENAME, null, strSqlWhere, sqlParams, false);
	}

	/**
	 * 根据主键条件更新一条记录
	 * 
	 * @param
	 * @param con
	 *            :Connection 与数据库建立的连接
	 * @throws SQLException
	 */
	public static boolean updateByPK(Connection con, String UUID, NsrCwbbVO vo)
			throws SQLException {
		String strSqlWhere = "UUID=? ";
		ArrayList<String> sqlParams = new ArrayList<String>();
		sqlParams.add(UUID);
		return update(con, TABLENAME, strSqlWhere, sqlParams, vo);
	}

	// //////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////
	public static List queryList(Connection conn, String ZB_UUID) throws SQLException {
		String sqlWhere = "ZB_UUID=? ";
		ArrayList<String> sqlParams = new ArrayList<String>();
		sqlParams.add(ZB_UUID);
		List<NsrCwbbVO> list = queryByZdyWhere(conn, TABLENAME, null, sqlWhere, sqlParams, false);
		return list;
	}
}