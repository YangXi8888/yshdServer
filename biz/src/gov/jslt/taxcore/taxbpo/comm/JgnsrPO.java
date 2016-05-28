package gov.jslt.taxcore.taxbpo.comm;

import gov.jslt.taxevent.comm.JgnsrVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ctp.core.bpo.CssBaseBPO;

public class JgnsrPO extends CssBaseBPO {

	public JgnsrPO() {
		super();
	}

	private static final String TABLENAME = "DB_WSBS.T_DJ_JGNSR";

	private static JgnsrVO loadConcreteBPO(ResultSet rs) throws SQLException {
		JgnsrVO vo = new JgnsrVO();
		vo.setDjzclxdm(rs.getString("DJZCLX_DM"));
		vo.setDlwzcdm(rs.getString("DLWZC_DM"));
		vo.setDlwzxzdm(rs.getString("DLWZXZ_DM"));
		vo.setFwjgdm(rs.getString("FWJG_DM"));
		vo.setGbhydm(rs.getString("GBHY_DM"));
		vo.setGljgdm(rs.getString("GLJG_DM"));
		vo.setGlqydm(rs.getString("GLQY_DM"));
		vo.setGxflsx1dm(rs.getString("GXFLSX1_DM"));
		vo.setGxflsx2dm(rs.getString("GXFLSX2_DM"));
		vo.setGxflsx3dm(rs.getString("GXFLSX3_DM"));
		vo.setJcjgdm(rs.getString("JCJG_DM"));
		vo.setJiancjgdm(rs.getString("JIANCJG_DM"));
		vo.setJyhydm(rs.getString("JYHY_DM"));
		vo.setKglxdm(rs.getString("KGLX_DM"));
		vo.setLrjgdm(rs.getString("LRJG_DM"));
		vo.setLrrydm(rs.getString("LRRY_DM"));
		vo.setLsgxdm(rs.getString("LSGX_DM"));
		vo.setNsrsbm(rs.getString("NSRSBM"));
		vo.setNsrztdm(rs.getString("NSRZT_DM"));
		vo.setNsrmc(rs.getString("NSR_MC"));
		vo.setScjydlxdh(rs.getString("SCJYDLXDH"));
		vo.setSjjydz(rs.getString("SJJYDZ"));
		vo.setSjjydzyb(rs.getString("SJJYDZYB"));
		vo.setSkssjgdm(rs.getString("SKSSJG_DM"));
		vo.setSwdjlxdm(rs.getString("SWDJLX_DM"));
		vo.setSwglm(String.valueOf(rs.getLong("SWGLM")));
		vo.setWsh(rs.getString("WSH"));
		vo.setXgjgdm(rs.getString("XGJG_DM"));
		vo.setXgrydm(rs.getString("XGRY_DM"));
		vo.setZcdlxdh(rs.getString("ZCDLXDH"));
		vo.setZcdz(rs.getString("ZCDZ"));
		vo.setZcdzyb(rs.getString("ZCDZYB"));
		vo.setZdsyrddm(rs.getString("ZDSYRD_DM"));
		vo.setZsjgdm(rs.getString("ZSJG_DM"));
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

	private static JgnsrVO queryByWhere(Connection con, String tablename,
			String sqlOrder, String sqlWhere, ArrayList sqlParams,
			boolean isCount) throws SQLException {

		ResultSet result = null;
		PreparedStatement ps = null;
		result = findAllByWhere(con, tablename, sqlOrder, sqlWhere, sqlParams,
				isCount, result, ps);

		JgnsrVO vo = null;
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
	 * 根据主键条件查询一条记录
	 * 
	 * @param
	 * @param con
	 *            :Connection 与数据库建立的连接
	 * @throws SQLException
	 */
	public static JgnsrVO queryByPK(Connection con, String SWGLM)
			throws SQLException {
		String strSqlWhere = "SWGLM=? ";
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(SWGLM);

		return queryByWhere(con, TABLENAME, null, strSqlWhere, sqlParams, false);
	}

	// //////////////////////////////////////以下为【自定义部分】/////////////////////////////////////////////////////////
}
