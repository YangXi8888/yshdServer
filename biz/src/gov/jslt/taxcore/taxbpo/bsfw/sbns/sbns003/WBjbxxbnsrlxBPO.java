package gov.jslt.taxcore.taxbpo.bsfw.sbns.sbns003;

import gov.jslt.taxevent.bsfw.sbns.sbns003.WBjbxxbnsrlxVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ctp.core.bpo.CssBaseBPO;

public class WBjbxxbnsrlxBPO extends CssBaseBPO {

	public WBjbxxbnsrlxBPO() {
		super();
	}

	private static final String TABLENAME = "DB_WSBS.T_WB_GRXX_B_NSRLX";

	private static WBjbxxbnsrlxVO loadConcreteBPO(ResultSet rs)
			throws SQLException {
		WBjbxxbnsrlxVO vo = new WBjbxxbnsrlxVO();
		vo.setMxxh(String.valueOf(rs.getLong("MX_XH")));
		vo.setNsrlxdm(rs.getString("NSRLX_DM"));
		vo.setPzxh(rs.getString("PZ_XH"));
		vo.setXgjgdm(rs.getString("XGJG_DM"));
		vo.setXgrydm(rs.getString("XGRY_DM"));
		vo.setStatus(new HashMap());
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
	 *             修改历史： 版本0.5 2008-4-24周红江 : 1.将原来的findAll废弃 因为其存在内存浪费，2.不再使用
	 *             CachedRowSet 3.在子类中申明ResultSet,PreparedStatement 在子类中释放;
	 */

	private static WBjbxxbnsrlxVO queryByWhere(Connection con,
			String tablename, String sqlOrder, String sqlWhere,
			ArrayList sqlParams, boolean isCount) throws SQLException {

		ResultSet result = null;
		PreparedStatement ps = null;
		result = findAllByWhere(con, tablename, sqlOrder, sqlWhere, sqlParams,
				isCount, result, ps);

		WBjbxxbnsrlxVO vo = null;
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
	 * @param con
	 *            :Connection 与数据库建立的连接
	 * @throws SQLException
	 */

	public static boolean insert(Connection con, WBjbxxbnsrlxVO vo)
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
	public static boolean deleteByPK(Connection con, String PZ_XH, String MX_XH)
			throws SQLException {
		String strSqlWhere = "PZ_XH=?  AND MX_XH=? ";
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(PZ_XH);
		sqlParams.add(MX_XH);

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
	public static WBjbxxbnsrlxVO queryByPK(Connection con, String PZ_XH,
			String MX_XH) throws SQLException {
		String strSqlWhere = "PZ_XH=?  AND MX_XH=? ";
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(PZ_XH);
		sqlParams.add(MX_XH);

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
	public static boolean updateByPK(Connection con, String PZ_XH,
			String MX_XH, WBjbxxbnsrlxVO vo) throws SQLException {
		String strSqlWhere = "PZ_XH=?  AND MX_XH=? ";
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(PZ_XH);
		sqlParams.add(MX_XH);

		return update(con, TABLENAME, strSqlWhere, sqlParams, vo);
	}

	// //////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////
	public static List getListByPK(Connection con, String PZ_XH)
			throws SQLException {
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(PZ_XH);
		List list = queryByZdyWhere(con, TABLENAME, null, "PZ_XH=?", sqlParams,
				false);
		if (null == list) {
			list = new ArrayList();
		}
		return list;
	}

	public static boolean deleteByPK(Connection con, String PZ_XH)
			throws SQLException {
		String strSqlWhere = "PZ_XH=? ";
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(PZ_XH);

		return delete(con, TABLENAME, strSqlWhere, sqlParams);
	}
}
