package gov.jslt.taxcore.taxbpo.comm;

import gov.jslt.taxevent.comm.WBwsfwqkbVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ctp.core.bpo.CssBaseBPO;
import com.ctp.core.commutils.DateUtils;

public class WBwsfwqkbBPO extends CssBaseBPO {

	public WBwsfwqkbBPO() {
		super();
	}

	private static final String TABLENAME = "DB_WSBS.T_WB_WSFWQKB";

	private static WBwsfwqkbVO loadConcreteBPO(ResultSet rs)
			throws SQLException {
		WBwsfwqkbVO vo = new WBwsfwqkbVO();
		vo.setByzd(rs.getString("BYZD"));
		vo.setCarz(rs.getString("CARZ"));
		vo.setGljgdm(rs.getString("GLJG_DM"));
		vo.setIsprocess(String.valueOf(rs.getLong("ISPROCESS")));
		vo.setLcslh(rs.getString("LCSLH"));
		vo.setLsh(rs.getString("LSH"));
		vo.setLxdh(rs.getString("LXDH"));
		vo.setLxr(rs.getString("LXR"));
		vo.setSlswjgdm(rs.getString("SLSWJGDM"));
		vo.setSlswjgmc(rs.getString("SLSWJGMC"));
		vo.setSlswrydm(rs.getString("SLSWRYDM"));
		vo.setSlswrymc(rs.getString("SLSWRYMC"));
		vo.setSqlxdm(rs.getString("SQLX_DM"));
		vo.setSqsj(DateUtils.convSqlTimestampToUtilCalendar(rs
				.getTimestamp("SQ_SJ")));
		vo.setSslx(rs.getString("SSLX"));
		vo.setSssqdl(rs.getString("SSSQ_DL"));
		vo.setSssqxl(rs.getString("SSSQ_XL"));
		vo.setSsxmdm(rs.getString("SSXMDM"));
		vo.setSsxmmc(rs.getString("SSXMMC"));
		vo.setSwglm(String.valueOf(rs.getLong("SWGLM")));
		vo.setThsj(DateUtils.convSqlTimestampToUtilCalendar(rs
				.getTimestamp("TH_SJ")));
		vo.setXmbm(rs.getString("XMBM"));
		vo.setYj(rs.getString("YJ"));
		vo.setZsxm(rs.getString("ZSXM"));
		vo.setZt(rs.getString("ZT"));
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

	private static WBwsfwqkbVO queryByWhere(Connection con, String tablename,
			String sqlOrder, String sqlWhere, ArrayList sqlParams,
			boolean isCount) throws SQLException {

		ResultSet result = null;
		PreparedStatement ps = null;
		result = findAllByWhere(con, tablename, sqlOrder, sqlWhere, sqlParams,
				isCount, result, ps);

		WBwsfwqkbVO vo = null;
		if (result != null) {
			if (result.next())
				vo = loadConcreteBPO(result);
		}
		closeDbsession(result, ps);
		;
		return vo;
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

	public static boolean insert(Connection con, WBwsfwqkbVO vo)
			throws SQLException {
		if ("1".equals(vo.getZt())) {
			vo.setZt("9");
		}
		return insert(con, TABLENAME, vo);
	}

	public static boolean insertBytdqs(Connection con, WBwsfwqkbVO vo)
			throws SQLException {
		if ("1".equals(vo.getZt())) {
			vo.setZt("3");
		}
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
	public static WBwsfwqkbVO queryByPK(Connection con, String LSH)
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
	public static boolean updateByPK(Connection con, String LSH, WBwsfwqkbVO vo)
			throws SQLException {
		String strSqlWhere = "LSH=? ";
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(LSH);
		if ("1".equals(vo.getZt())) {
			vo.setZt("9");
		}
		return update(con, TABLENAME, strSqlWhere, sqlParams, vo);
	}
	// //////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////
}
