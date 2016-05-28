package gov.jslt.taxcore.taxbpo.comm;

import gov.jslt.taxevent.comm.WBsbbqkZrrVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ctp.core.bpo.CssBaseBPO;
import com.ctp.core.commutils.DateUtils;

public class WBsbbqkZrrBPO extends CssBaseBPO {

	public WBsbbqkZrrBPO() {
		super();
	}

	private static final String TABLENAME = "DB_WSBS.T_WB_SBBQK_ZRR";

	private static WBsbbqkZrrVO loadConcreteBPO(ResultSet rs)
			throws SQLException {
		WBsbbqkZrrVO vo = new WBsbbqkZrrVO();
		vo.setDhrq(DateUtils.convSqlTimestampToUtilCalendar(rs
				.getTimestamp("DH_RQ")));
		vo.setDhsj(DateUtils.convSqlTimestampToUtilCalendar(rs
				.getTimestamp("DH_SJ")));
		vo.setDjzclxdm(rs.getString("DJZCLX_DM"));
		vo.setDlwzxzdm(rs.getString("DLWZXZ_DM"));
		vo.setFwjgdm(rs.getString("FWJG_DM"));
		vo.setGbhydm(rs.getString("GBHY_DM"));
		vo.setGjmc(rs.getString("GJ_MC"));
		vo.setGljgdm(rs.getString("GLJG_DM"));
		vo.setHsjgdm(rs.getString("HSJG_DM"));
		vo.setJcjgdm(rs.getString("JCJG_DM"));
		vo.setJiancjgdm(rs.getString("JIANCJG_DM"));
		vo.setJydwnsrmc(rs.getString("JYDWNSRMC"));
		vo.setJydwswglm(String.valueOf(rs.getLong("JYDWSWGLM")));
		vo.setJzd(rs.getString("JZD"));
		vo.setKhyhmc(rs.getString("KHYH_MC"));
		vo.setLsgxdm(rs.getString("LSGX_DM"));
		vo.setLxdh(rs.getString("LXDH"));
		vo.setLxdz(rs.getString("LXDZ"));
		vo.setNsrsbm(rs.getString("NSRSBM"));
		vo.setNsrmc(rs.getString("NSR_MC"));
		vo.setPzzldm(rs.getString("PZZL_DM"));
		vo.setPzxh(rs.getString("PZ_XH"));
		vo.setQyzt(rs.getString("QYZT"));
		vo.setQysj(DateUtils.convSqlTimestampToUtilCalendar(rs
				.getTimestamp("QY_SJ")));
		vo.setRzdwhy(rs.getString("RZDWHY"));
		vo.setRzdwmc(rs.getString("RZDWMC"));
		vo.setRzdwswglm(String.valueOf(rs.getLong("RZDWSWGLM")));
		vo.setRzfs(rs.getString("RZFS"));
		vo.setSbdm(rs.getString("SB_DM"));
		vo.setSfssqqsrq(DateUtils.convSqlTimestampToUtilCalendar(rs
				.getTimestamp("SFSSQ_QSRQ")));
		vo.setSfssqzzrq(DateUtils.convSqlTimestampToUtilCalendar(rs
				.getTimestamp("SFSSQ_ZZRQ")));
		vo.setShjssj(DateUtils.convSqlTimestampToUtilCalendar(rs
				.getTimestamp("SHJS_SJ")));
		vo.setShry(rs.getString("SH_RY"));
		vo.setShsj(DateUtils.convSqlTimestampToUtilCalendar(rs
				.getTimestamp("SH_SJ")));
		vo.setSkssjgdm(rs.getString("SKSSJG_DM"));
		vo.setSwglm(String.valueOf(rs.getLong("SWGLM")));
		vo.setTbrq(DateUtils.convSqlTimestampToUtilCalendar(rs
				.getTimestamp("TB_RQ")));
		vo.setTjsj(DateUtils.convSqlTimestampToUtilCalendar(rs
				.getTimestamp("TJ_SJ")));
		vo.setYb(rs.getString("YB"));
		vo.setYbtsehdjje(String.valueOf(rs.getDouble("YBTSEHDJ_JE")));
		vo.setYhzh(rs.getString("YH_ZH"));
		vo.setZclxmc(rs.getString("ZCLX_MC"));
		vo.setZhts(String.valueOf(rs.getLong("ZHTS")));
		vo.setZjlxdm(rs.getString("ZJLX_DM"));
		vo.setZjlxmc(rs.getString("ZJLX_MC"));
		vo.setZjhm(rs.getString("ZJ_HM"));
		vo.setZpzxh(rs.getString("ZPZ_XH"));
		vo.setZsjgdm(rs.getString("ZSJG_DM"));
		vo.setZt(rs.getString("ZT"));
		vo.setZwmc(rs.getString("ZW_MC"));
		vo.setZymc(rs.getString("ZY_MC"));
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

	private static WBsbbqkZrrVO queryByWhere(Connection con, String tablename,
			String sqlOrder, String sqlWhere, ArrayList sqlParams,
			boolean isCount) throws SQLException {

		ResultSet result = null;
		PreparedStatement ps = null;
		result = findAllByWhere(con, tablename, sqlOrder, sqlWhere, sqlParams,
				isCount, result, ps);

		WBsbbqkZrrVO vo = null;
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

	public static boolean insert(Connection con, WBsbbqkZrrVO vo)
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
	public static boolean deleteByPK(Connection con, String PZ_XH)
			throws SQLException {
		String strSqlWhere = "PZ_XH=? ";
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(PZ_XH);

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
	public static WBsbbqkZrrVO queryByPK(Connection con, String PZ_XH)
			throws SQLException {
		String strSqlWhere = "PZ_XH=? ";
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(PZ_XH);

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
			WBsbbqkZrrVO vo) throws SQLException {
		String strSqlWhere = "PZ_XH=? ";
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(PZ_XH);

		return update(con, TABLENAME, strSqlWhere, sqlParams, vo);
	}
	// //////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////
}
