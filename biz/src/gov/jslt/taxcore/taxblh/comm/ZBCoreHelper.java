package gov.jslt.taxcore.taxblh.comm;

import gov.jslt.taxcore.taxbpo.comm.TlcslBPO;
import gov.jslt.taxcore.taxbpo.comm.TlcslclBPO;
import gov.jslt.taxcore.taxbpo.comm.TwjxxBPO;
import gov.jslt.taxevent.comm.AESTool;
import gov.jslt.taxevent.comm.DateUtil;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.SocketComm;
import gov.jslt.taxevent.comm.StringUtil;
import gov.jslt.taxevent.comm.TlcslVO;
import gov.jslt.taxevent.comm.TlcslclVO;
import gov.jslt.taxevent.comm.TwjxxVO;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.bean.MorphDynaBean;

import org.apache.commons.codec.binary.Base64;

import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.bpo.QueryCssBPO;
import com.ctp.core.config.ApplicationContext;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.core.log.LogWritter;
import com.ctp.cssesb.esbevent.ESBRequestEvent;
import com.ctp.cssesb.esbevent.ESBResponseEvent;

public class ZBCoreHelper {

	public static boolean xxjy(Connection conn, Map map) throws SQLException {
		boolean result = false;
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		// MD5校验
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT X.CSZ FROM DB_WSBS.T_XT_XTCS X ");
		sql.append(" WHERE X.CSBM = 'ANDROID_MD5' ");
		sql.append(" AND X.CSZ = ? ");
		sqlParams.add(map.get("md5"));
		CachedRowSet rs = QueryCssBPO.findAll(conn, sql.toString(), sqlParams);
		if (rs.next()) {
			// 证书校验
			sql = new StringBuffer();
			sql.append(" SELECT X.CSZ FROM DB_WSBS.T_XT_XTCS X ");
			sql.append(" WHERE X.CSBM = 'ANDROID_CERT' ");
			sql.append(" AND X.CSZ = ? ");
			sqlParams = new ArrayList<Object>();
			sqlParams.add(map.get("cert"));
			rs = QueryCssBPO.findAll(conn, sql.toString(), sqlParams);
			if (rs.next()) {
				result = true;
			}
		}
		return result;
	}

	public static boolean getUserInfo(Connection conn,
			Map<String, Object> sqlMap) throws SQLException {
		// 用户信息校验
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM DB_ZSBS.T_ZB_LOGIN L ");
		sql.append(" WHERE L.SJHM = ? ");
		sql.append(" AND L.XY_BJ = '1' AND L.SFZMLX_DM = ? ");
		sql.append(" AND L.SFZHM = ? ");
		sql.append(" AND L.LOGPASS_MD5 = ? ");
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(sqlMap.get("sjhm"));
		sqlParams.add(sqlMap.get("zjlx"));
		sqlParams.add(sqlMap.get("zjhm"));
		sqlParams.add(sqlMap.get("passWord"));
		CachedRowSet rs = QueryCssBPO.findAll(conn, sql.toString(), sqlParams);
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

	public static String getGUID(Connection con) throws SQLException {
		String guid = "";
		String getGuidSql = "select  DB_WSBS.F_XT_GET_GUID() as uuid from dual";
		CachedRowSet rs = QueryCssBPO.findAll(con, getGuidSql, null);
		rs.next();
		guid = rs.getString("uuid");

		return guid;
	}

	public static String getNextSysdateTime(Connection con) throws SQLException {
		String dateTime = "";
		String getGuidSql = "SELECT TO_CHAR(SYSDATE + 3/24, 'YYYYMMDDHH24MISS') AS DATETIME FROM DUAL";
		CachedRowSet rs = QueryCssBPO.findAll(con, getGuidSql, null);
		rs.next();
		dateTime = rs.getString("DATETIME");
		return dateTime;
	}

	public static Map<String, String> getFtp(String gljgdm, Connection conn)
			throws SQLException {
		Map<String, String> map = new HashMap<String, String>();
		String sql = "SELECT S.CSZ,TO_CHAR(SYSDATE,'YYYYMMDD') AS RQ FROM DB_WSBS.T_XT_XTCS S WHERE S.CSBM ='ZSBSFTP' ";
		CachedRowSet rs = QueryCssBPO.findAll(conn, sql, null, null);
		if (rs.next()) {
			map.put("ml", gljgdm.substring(0, 5) + "/" + rs.getString("RQ"));
			String[] temp = rs.getString("CSZ").split(":");
			map.put("ftpIp", temp[0]);
			map.put("ftpUser", temp[1]);
			map.put("ftpPwd", temp[2]);
		}
		return map;
	}

	public static String getDateTime(Connection con, String format)
			throws SQLException {
		String dateTime = "";
		if (format == null) {
			format = "YYYY-MM-DD HH24:MI:SS";
		}
		String getGuidSql = "SELECT  TO_CHAR(SYSTIMESTAMP,'" + format
				+ "') AS  DATETIME FROM DUAL";
		CachedRowSet rs = QueryCssBPO.findAll(con, getGuidSql, null);
		rs.next();
		dateTime = rs.getString("DATETIME");
		return dateTime;
	}

	public static String getBeforeDateTime(Connection con, String format)
			throws SQLException {
		String dateTime = "";
		if (format == null) {
			format = "YYYY-MM-DD HH24:MI:SS";
		}
		String getGuidSql = "SELECT  TO_CHAR(SYSTIMESTAMP-1,'" + format
				+ "') AS  DATETIME FROM DUAL";
		CachedRowSet rs = QueryCssBPO.findAll(con, getGuidSql, null);
		rs.next();
		dateTime = rs.getString("DATETIME");
		return dateTime;
	}

	public static String getJyztMc(Connection con, String jyztDm)
			throws SQLException {
		if (null == jyztDm || "".equals(jyztDm)) {
			return GeneralCons.SUCCESS_MSG_ZB7777;
		} else {
			ArrayList sqlParams = new ArrayList();
			sqlParams.add(jyztDm);
			CachedRowSet rs = QueryCssBPO
					.findAll(
							con,
							"SELECT JYZT_MC FROM DB_ZSBS.T_DM_ZB_JYZT D WHERE JYZT_DM=?",
							sqlParams);
			if (rs.next()) {
				return rs.getString("JYZT_MC");
			} else {
				return GeneralCons.SUCCESS_MSG_ZB7777;
			}
		}
	}

	public static String empty(Object str) {
		if (null == str || "null".equals(str)) {
			return "";
		}
		return str.toString();
	}

	// 涉税月报
	public static String getColnum_ssyb(Object colname) {
		String colnum = "";
		if ("23201".equals(colname)) {
			colnum = "col47958";
		}
		if ("23202".equals(colname)) {
			colnum = "col51394";
		}
		if ("23203".equals(colname)) {
			colnum = "col47960";
		}
		if ("23204".equals(colname)) {
			colnum = "col47961";
		}
		if ("23205".equals(colname)) {
			colnum = "col47962";
		}
		if ("23206".equals(colname)) {
			colnum = "col47963";
		}
		if ("23207".equals(colname)) {
			colnum = "col47964";
		}
		if ("23208".equals(colname)) {
			colnum = "col47966";
		}
		if ("23209".equals(colname)) {
			colnum = "col47967";
		}
		if ("23210".equals(colname)) {
			colnum = "col47968";
		}
		if ("23211".equals(colname)) {
			colnum = "col47969";
		}
		if ("23212".equals(colname)) {
			colnum = "col47970";
		}
		if ("23213".equals(colname)) {
			colnum = "col47971";
		}
		if ("23217".equals(colname)) {
			colnum = "col47965";
		}
		if ("23216".equals(colname)) {
			colnum = "col47972";
		}
		if ("23298".equals(colname)) {
			colnum = "col47973";
		}
		if ("".equals(colname)) {
			colnum = "col47958";
		}
		return colnum;
	}

	// 涉税优惠
	public static String getColnum_ssyh(Object colname) {
		String colnum = "";
		if ("ZHL".equals(colname)) {
			colnum = "col54079";
		}
		if ("YYS".equals(colname)) {
			colnum = "col54080";
		}
		if ("QYSDS".equals(colname)) {
			colnum = "col54081";
		}
		if ("GRSDS".equals(colname)) {
			colnum = "col54082";
		}
		if ("FCS".equals(colname)) {
			colnum = "col54083";
		}
		if ("CZTDSYS".equals(colname)) {
			colnum = "col54084";
		}
		if ("DTZZS".equals(colname)) {
			colnum = "col54192";
		}
		if ("GDZYS".equals(colname)) {
			colnum = "col54085";
		}
		if ("ZYS".equals(colname)) {
			colnum = "col54086";
		}
		if ("QS".equals(colname)) {
			colnum = "col54087";
		}
		if ("YHS".equals(colname)) {
			colnum = "col54088";
		}
		if ("CCS".equals(colname)) {
			colnum = "col54089";
		}
		if ("CSWHJS_JYFJ".equals(colname)) {
			colnum = "col54090";
		}
		if ("".equals(colname)) {
			colnum = "col54079";
		}
		return colnum;
	}

	// 办税日历
	public static String getColnum_bsrl(Object colname) {
		String url = "";
		// 南京市区
		if ("NJ_SQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47697_491281.html";
		}
		// 江宁区
		if ("NJ_JNQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47697_491280.html";
		}
		// 浦口区
		if ("NJ_PKQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47697_491279.html";
		}
		// 六合区
		if ("NJ_LHQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47697_491278.html";
		}
		// 溧水区
		if ("NJ_LSQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47697_491277.html";
		}
		// 高淳区
		if ("NJ_GCQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47697_491276.html";
		}

		// 无锡市区
		if ("WX_SQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/23/art_47698_491286.html";
		}
		// 无锡江阴市
		if ("WX_JYS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/23/art_47698_491285.html";
		}
		// 无锡宜兴市
		if ("WX_YXS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/23/art_47698_491284.html";
		}
		// 无锡锡山区
		if ("WX_XSQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/23/art_47698_491283.html";
		}
		// 无锡惠山区
		if ("WX_HSQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/23/art_47698_491282.html";
		}

		// 徐州市区
		if ("XZ_SQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47699_557850.html";
		}
		// 丰县
		if ("XZ_FX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47699_491320.html";
		}
		// 沛县
		if ("XZ_PX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47699_491319.html";
		}
		// 铜山区
		if ("XZ_TSQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47699_491318.html";
		}
		// 睢宁县
		if ("XZ_SNX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47699_491317.html";
		}
		// 邳州市
		if ("XZ_PZS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47699_491316.html";
		}
		// 新沂市
		if ("XZ_XYS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47699_491315.html";
		}
		// 贾汪区
		if ("XZ_JWQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47699_491314.html";
		}

		// 常州市区
		if ("CZ_SQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47700_491334.html";
		}
		// 武进区
		if ("CZ_WJQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47700_491333.html";
		}
		// 金坛市
		if ("CZ_JTS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47700_491332.html";
		}
		// 溧阳市
		if ("CZ_LYS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47700_491331.html";
		}

		// 苏州市区
		if ("SZ_SQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47701_491352.html";
		}
		// 常熟市
		if ("SZ_CSS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47701_491351.html";
		}
		// 张家港市
		if ("SZ_ZJGS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47701_491350.html";
		}
		// 昆山市
		if ("SZ_KSS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47701_491349.html";
		}
		// 太仓市
		if ("SZ_TCS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47701_491348.html";
		}
		// 吴江市
		if ("SZ_WJS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47701_491347.html";
		}
		// 吴中区
		if ("SZ_WZQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47701_491346.html";
		}
		// 相城区
		if ("SZ_XCQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47701_491345.html";
		}

		// 南通市区
		if ("NT_SQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47702_491374.html";
		}
		// 通州区
		if ("NT_TZQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47702_491373.html";
		}
		// 启东市
		if ("NT_QDS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47702_491372.html";
		}
		// 海门市
		if ("NT_HMS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47702_491371.html";
		}
		// 如东县
		if ("NT_RDX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47702_491370.html";
		}
		// 如皋市
		if ("NT_RGS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47702_491369.html";
		}
		// 海安县
		if ("NT_HAX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47702_491368.html";
		}

		// 连云港市区
		if ("LYG_SQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/23/art_47703_491380.html";
		}
		// 赣榆区
		if ("LYG_GYQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/23/art_47703_491379.html";
		}
		// 东海县
		if ("LYG_DHX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/23/art_47703_491378.html";
		}
		// 灌云县
		if ("LYG_GYX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/23/art_47703_491377.html";
		}
		// 灌南县
		if ("LYG_GNX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/23/art_47703_491376.html";
		}

		// 淮安市区
		if ("HA_SQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/24/art_47705_491388.html";
		}
		// 淮阴区
		if ("HA_HYQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/24/art_47705_491387.html";
		}
		// 淮安区
		if ("HA_HAQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/24/art_47705_491386.html";
		}
		// 涟水县
		if ("HA_LSX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/24/art_47705_491385.html";
		}
		// 洪泽县
		if ("HA_HZX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/24/art_47705_491384.html";
		}
		// 盱眙县
		if ("HA_XYX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/24/art_47705_491383.html";
		}
		// 金湖县
		if ("HA_JHX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/24/art_47705_491382.html";
		}

		// 盐城市区
		if ("YC_SQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47706_491397.html";
		}
		// 东台市
		if ("YC_DTS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47706_491396.html";
		}
		// 大丰市
		if ("YC_DFS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47706_491395.html";
		}
		// 盐都区
		if ("YC_YDQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47706_491394.html";
		}
		// 建湖县
		if ("YC_JHX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47706_491393.html";
		}
		// 射阳县
		if ("YC_SYX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47706_491392.html";
		}
		// 响水县
		if ("YC_XSX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47706_491389.html";
		}
		// 阜宁县
		if ("YC_FNX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47706_491391.html";
		}
		// 滨海县
		if ("YC_BHX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47706_491390.html";
		}

		// 扬州市区
		if ("YZ_SQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2015/2/2/art_47707_776037.html";
		}
		// 宝应县
		if ("YZ_BYX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2015/2/2/art_47707_776036.html";
		}
		// 高邮市
		if ("YZ_GYS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2015/2/2/art_47707_776035.html";
		}
		// 江都区
		if ("YZ_JDQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2015/2/2/art_47707_776034.html";
		}
		// 邗江区
		if ("YZ_GJQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2015/2/19/art_47707_776033.html";
		}
		// 仪征市
		if ("YZ_YZS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2015/2/19/art_47707_776032.html";
		}

		// 镇江市区
		if ("ZJ_SQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47708_491424.html";
		}
		// 丹阳市
		if ("ZJ_DYS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47708_491423.html";
		}
		// 扬中市
		if ("ZJ_YZS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47708_491422.html";
		}
		// 句容市
		if ("ZJ_JRS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47708_491421.html";
		}
		// 丹徒区
		if ("ZJ_DTQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47708_491420.html";
		}

		// 泰州市区
		if ("TZ_SQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2014/12/25/art_47709_669270.html";
		}
		// 姜堰市
		if ("TZ_JYS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2014/12/25/art_47709_669269.html";
		}
		// 靖江市
		if ("TZ_JJS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2014/12/25/art_47709_669268.html";
		}
		// 泰兴市
		if ("TZ_TXS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2014/12/25/art_47709_669267.html";
		}
		// 兴化市
		if ("TZ_XHS".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2014/12/25/art_47709_669266.html";
		}

		// 宿迁市区
		if ("SQ_SQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/20/art_47710_618092.html";
		}
		// 沭阳县
		if ("SQ_SUYX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/20/art_47710_618090.html";
		}
		// 泗阳县
		if ("SQ_SIYX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/20/art_47710_618089.html";
		}
		// 泗洪县
		if ("SQ_SHX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/20/art_47710_618088.html";
		}
		// 宿豫区
		if ("SQ_SYQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/20/art_47710_618091.html";
		}
		// 苏州工业园区
		if ("SZYQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47704_491412.html";
		}
		// 张家港保税区
		if ("BSQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47711_491435.html";
		}
		if ("".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47697_491281.html";
		}

		return url;
	}

	// 办税日历
	public static String getColnum_bssj(Object colname) {
		String url = "";
		if ("NJ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/3/7/art_47256_478611.html";
		}
		if ("WX".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/3/6/art_47257_478591.html";
		}
		if ("XZ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/3/6/art_47258_478592.html";
		}
		if ("CZ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/3/6/art_47259_478593.html";
		}
		if ("SZ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/3/6/art_47260_478594.html";
		}
		if ("NT".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/3/6/art_47261_478595.html";
		}
		if ("LYG".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/3/6/art_47262_478596.html";
		}
		if ("HA".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/3/6/art_47264_478598.html";
		}
		if ("YC".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/3/6/art_47265_478599.html";
		}
		if ("YZ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/3/6/art_47266_478600.html";
		}
		if ("ZJ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/3/6/art_47267_478601.html";
		}
		if ("TZ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/3/6/art_47268_478602.html";
		}
		if ("SQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/3/6/art_47269_478603.html";
		}
		if ("SZYQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/3/6/art_47263_478597.html";
		}
		if ("BSQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/3/6/art_47270_478604.html";
		}
		if ("SZSQ".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/3/6/art_47271_478605.html";
		}
		if ("".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/3/7/art_47256_478611.html";
		}
		return url;
	}

	// 税种税率
	public static String getColnum_szsl(Object colname) {
		String url = "";
		if ("".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2011/12/31/art_47737_491673.html";
		}
		if ("NJ_taxType1".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2011/12/31/art_47737_491673.html";
		}
		if ("NJ_taxType2".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2011/12/31/art_47737_491672.html";
		}
		if ("NJ_taxType3".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2011/12/31/art_47737_491671.html";
		}
		if ("NJ_taxType4".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2011/12/31/art_47737_491670.html";
		}
		if ("NJ_taxType5".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2011/12/31/art_47737_491669.html";
		}
		if ("NJ_taxType6".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2011/12/31/art_47737_491668.html";
		}
		if ("NJ_taxType7".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2011/12/31/art_47737_491667.html";
		}
		if ("NJ_taxType8".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2011/12/31/art_47737_491666.html";
		}
		if ("NJ_taxType9".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2011/12/31/art_47737_491665.html";
		}
		if ("NJ_taxType10".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2011/12/31/art_47737_491664.html";
		}
		if ("NJ_taxType11".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2011/12/31/art_47737_491663.html";
		}
		if ("NJ_taxType12".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2011/12/31/art_47737_491662.html";
		}
		if ("WX_taxType1".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/23/art_47738_491685.html";
		}
		if ("WX_taxType2".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47738_491684.html";
		}
		if ("WX_taxType3".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47738_491683.html";
		}
		if ("WX_taxType4".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/23/art_47738_491682.html";
		}
		if ("WX_taxType5".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2011/11/10/art_47738_491681.html";
		}
		if ("WX_taxType6".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/23/art_47738_491680.html";
		}
		if ("WX_taxType7".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/23/art_47738_491679.html";
		}
		if ("WX_taxType8".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/23/art_47738_491678.html";
		}
		if ("WX_taxType9".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/23/art_47738_491677.html";
		}
		if ("WX_taxType10".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/23/art_47738_491676.html";
		}
		if ("WX_taxType11".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/23/art_47738_491675.html";
		}
		if ("WX_taxType12".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/23/art_47738_491674.html";
		}
		if ("XZ_taxType1".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47739_491699.html";
		}
		if ("XZ_taxType2".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47739_491698.html";
		}
		if ("XZ_taxType3".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47739_491697.html";
		}
		if ("XZ_taxType4".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47739_491696.html";
		}
		if ("XZ_taxType5".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2013/12/31/art_47739_642867.html";
		}
		if ("XZ_taxType6".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47739_491694.html";
		}
		if ("XZ_taxType7".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47739_491693.html";
		}
		if ("XZ_taxType8".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47739_491692.html";
		}
		if ("XZ_taxType9".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47739_491691.html";
		}
		if ("XZ_taxType10".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47739_491690.html";
		}
		if ("XZ_taxType11".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47739_491689.html";
		}
		if ("XZ_taxType12".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47739_491688.html";
		}
		if ("CZ_taxType1".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/23/art_47740_491713.html";
		}
		if ("CZ_taxType2".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/23/art_47740_491712.html";
		}
		if ("CZ_taxType3".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/23/art_47740_491711.html";
		}
		if ("CZ_taxType4".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/23/art_47740_491710.html";
		}
		if ("CZ_taxType5".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/23/art_47740_491709.html";
		}
		if ("CZ_taxType6".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/23/art_47740_491708.html";
		}
		if ("CZ_taxType7".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/23/art_47740_491707.html";
		}
		if ("CZ_taxType8".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/23/art_47740_491706.html";
		}
		if ("CZ_taxType9".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/23/art_47740_491705.html";
		}
		if ("CZ_taxType10".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/23/art_47740_491704.html";
		}
		if ("CZ_taxType11".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/23/art_47740_491703.html";
		}
		if ("CZ_taxType12".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/23/art_47740_491701.html";
		}
		if ("SZ_taxType1".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47741_491726.html";
		}
		if ("SZ_taxType2".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47741_491725.html";
		}
		if ("SZ_taxType3".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47741_491724.html";
		}
		if ("SZ_taxType4".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47741_491723.html";
		}
		if ("SZ_taxType5".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47741_491722.html";
		}
		if ("SZ_taxType6".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47741_491721.html";
		}
		if ("SZ_taxType7".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47741_491720.html";
		}
		if ("SZ_taxType8".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47741_491719.html";
		}
		if ("SZ_taxType9".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47741_491718.html";
		}
		if ("SZ_taxType10".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47741_491717.html";
		}
		if ("SZ_taxType11".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47741_491716.html";
		}
		if ("SZ_taxType12".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47741_491715.html";
		}
		if ("NT_taxType1".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47742_491738.html";
		}
		if ("NT_taxType2".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47742_491737.html";
		}
		if ("NT_taxType3".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47742_491736.html";
		}
		if ("NT_taxType4".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47742_491735.html";
		}
		if ("NT_taxType5".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47742_491734.html";
		}
		if ("NT_taxType6".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47742_491733.html";
		}
		if ("NT_taxType7".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47742_491732.html";
		}
		if ("NT_taxType8".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47742_491731.html";
		}
		if ("NT_taxType9".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47742_491730.html";
		}
		if ("NT_taxType10".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47742_491729.html";
		}
		if ("NT_taxType11".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47742_491728.html";
		}
		if ("NT_taxType12".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47742_491727.html";
		}
		if ("LYG_taxType1".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47743_491752.html";
		}
		if ("LYG_taxType2".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47743_491751.html";
		}
		if ("LYG_taxType3".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47743_491750.html";
		}
		if ("LYG_taxType4".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47743_491749.html";
		}
		if ("LYG_taxType5".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47743_491748.html";
		}
		if ("LYG_taxType6".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47743_491747.html";
		}
		if ("LYG_taxType7".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47743_491746.html";
		}
		if ("LYG_taxType8".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47743_491745.html";
		}
		if ("LYG_taxType9".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47743_491744.html";
		}
		if ("LYG_taxType10".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47743_491743.html";
		}
		if ("LYG_taxType11".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47743_491742.html";
		}
		if ("LYG_taxType12".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47743_491741.html";
		}
		if ("SZYQ_taxType1".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47744_491764.html";
		}
		if ("SZYQ_taxType2".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47744_491763.html";
		}
		if ("SZYQ_taxType3".equals(colname)) {
			// 无资源税 返回营业税税率表
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47744_491764.html";
		}
		if ("SZYQ_taxType4".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47744_491762.html";
		}
		if ("SZYQ_taxType5".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47744_491761.html";
		}
		if ("SZYQ_taxType6".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47744_491760.html";
		}
		if ("SZYQ_taxType7".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47744_491759.html";
		}
		if ("SZYQ_taxType8".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47744_491758.html";
		}
		if ("SZYQ_taxType9".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47744_491757.html";
		}
		if ("SZYQ_taxType10".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47744_491756.html";
		}
		if ("SZYQ_taxType11".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47744_491755.html";
		}
		if ("SZYQ_taxType12".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47744_491754.html";
		}
		if ("HA_taxType1".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47745_491778.html";
		}
		if ("HA_taxType2".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47745_491777.html";
		}
		if ("HA_taxType3".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47745_491776.html";
		}
		if ("HA_taxType4".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47745_491775.html";
		}
		if ("HA_taxType5".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47745_491774.html";
		}
		if ("HA_taxType6".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47745_491773.html";
		}
		if ("HA_taxType7".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47745_491772.html";
		}
		if ("HA_taxType8".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47745_491771.html";
		}
		if ("HA_taxType9".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47745_491770.html";
		}
		if ("HA_taxType10".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47745_491769.html";
		}
		if ("HA_taxType11".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47745_491768.html";
		}
		if ("HA_taxType12".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47745_491767.html";
		}
		if ("YC_taxType1".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47746_491794.html";
		}
		if ("YC_taxType2".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47746_491793.html";
		}
		if ("YC_taxType3".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47746_491792.html";
		}
		if ("YC_taxType4".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47746_491791.html";
		}
		if ("YC_taxType5".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47746_491790.html";
		}
		if ("YC_taxType6".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47746_491789.html";
		}
		if ("YC_taxType7".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47746_491788.html";
		}
		if ("YC_taxType8".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/12/31/art_47746_491787.html";
		}
		if ("YC_taxType9".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47746_491786.html";
		}
		if ("YC_taxType10".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47746_491785.html";
		}
		if ("YC_taxType11".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47746_491784.html";
		}
		if ("YC_taxType12".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47746_491783.html";
		}
		if ("YZ_taxType1".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2015/8/19/art_47747_775621.html";
		}
		if ("YZ_taxType2".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2015/8/19/art_47747_775622.html";
		}
		if ("YZ_taxType3".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2015/8/19/art_47747_775623.html";
		}
		if ("YZ_taxType4".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2015/8/19/art_47747_775624.html";
		}
		if ("YZ_taxType5".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2015/8/19/art_47747_775625.html";
		}
		if ("YZ_taxType6".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2015/8/19/art_47747_775626.html";
		}
		if ("YZ_taxType7".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2015/8/19/art_47747_775627.html";
		}
		if ("YZ_taxType8".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2015/8/19/art_47747_775628.html";
		}
		if ("YZ_taxType9".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2015/8/19/art_47747_775629.html";
		}
		if ("YZ_taxType10".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2015/8/19/art_47747_775630.html";
		}
		if ("YZ_taxType11".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2015/8/19/art_47747_775631.html";
		}
		if ("YZ_taxType12".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2015/8/19/art_47747_775632.html";
		}
		if ("ZJ_taxType1".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47748_491828.html";
		}
		if ("ZJ_taxType2".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47748_491827.html";
		}
		if ("ZJ_taxType3".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47748_491826.html";
		}
		if ("ZJ_taxType4".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47748_491825.html";
		}
		if ("ZJ_taxType5".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47748_491824.html";
		}
		if ("ZJ_taxType6".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2011/12/31/art_47748_491823.html";
		}
		if ("ZJ_taxType7".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47748_491822.html";
		}
		if ("ZJ_taxType8".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47748_491821.html";
		}
		if ("ZJ_taxType9".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47748_491820.html";
		}
		if ("ZJ_taxType10".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2011/12/31/art_47748_491819.html";
		}
		if ("ZJ_taxType11".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2011/12/29/art_47748_491818.html";
		}
		if ("ZJ_taxType12".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2011/12/31/art_47748_491817.html";
		}

		if ("TZ_taxType1".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47749_491842.html";
		}
		if ("TZ_taxType2".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47749_491841.html";
		}
		if ("TZ_taxType3".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47749_491840.html";
		}
		if ("TZ_taxType4".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47749_491839.html";
		}
		if ("TZ_taxType5".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47749_491838.html";
		}
		if ("TZ_taxType6".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47749_491837.html";
		}
		if ("TZ_taxType7".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47749_491836.html";
		}
		if ("TZ_taxType8".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47749_491835.html";
		}
		if ("TZ_taxType9".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47749_491833.html";
		}
		if ("TZ_taxType10".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47749_491834.html";
		}
		if ("TZ_taxType11".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47749_491832.html";
		}
		if ("TZ_taxType12".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47749_491831.html";
		}
		if ("SQ_taxType1".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47750_491854.html";
		}
		if ("SQ_taxType2".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47750_491853.html";
		}
		if ("SQ_taxType3".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47750_491852.html";
		}
		if ("SQ_taxType4".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47750_491851.html";
		}
		if ("SQ_taxType5".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47750_491850.html";
		}
		if ("SQ_taxType6".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47750_491849.html";
		}
		if ("SQ_taxType7".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47750_491848.html";
		}
		if ("SQ_taxType8".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47750_491847.html";
		}
		if ("SQ_taxType9".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47750_491846.html";
		}
		if ("SQ_taxType10".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47750_491845.html";
		}
		if ("SQ_taxType11".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47750_491844.html";
		}
		if ("SQ_taxType12".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/21/art_47750_491843.html";
		}

		if ("BSQ_taxType1".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47751_491865.html";
		}
		if ("BSQ_taxType2".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47751_491864.html";
		}
		if ("BSQ_taxType3".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47751_491863.html";
		}
		if ("BSQ_taxType4".equals(colname)) {
			// 没有，返回营业税
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47751_491865.html";
		}
		if ("BSQ_taxType5".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47751_491862.html";
		}
		if ("BSQ_taxType6".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47751_491861.html";
		}
		if ("BSQ_taxType7".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47751_491860.html";
		}
		if ("BSQ_taxType8".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47751_491859.html";
		}
		if ("BSQ_taxType9".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47751_491858.html";
		}
		if ("BSQ_taxType10".equals(colname)) {
			// 没有，返回营业税
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47751_491865.html";
		}
		if ("BSQ_taxType11".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47751_491857.html";
		}
		if ("BSQ_taxType12".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47751_491856.html";
		}
		if ("SZSQ_taxType1".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47752_491877.html";
		}
		if ("SZSQ_taxType2".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47752_491876.html";
		}
		if ("SZSQ_taxType3".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47752_491875.html";
		}
		if ("SZSQ_taxType4".equals(colname)) {
			// 没有，返回营业税
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47752_491877.html";
		}
		if ("SZSQ_taxType5".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47752_491874.html";
		}
		if ("SZSQ_taxType6".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47752_491873.html";
		}
		if ("SZSQ_taxType76".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47752_491872.html";
		}
		if ("SZSQ_taxType8".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47752_491871.html";
		}
		if ("SZSQ_taxType9".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47752_491870.html";
		}
		if ("SZSQ_taxType10".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47752_491869.html";
		}
		if ("SZSQ_taxType11".equals(colname)) {
			// 没有，返回营业税
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47752_491877.html";
		}
		if ("SZSQ_taxType12".equals(colname)) {
			// 没有，返回营业税
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/art/2012/5/22/art_47752_491877.html";
		}
		return url;
	}

	// 办税指南
	public static String getColnum_sszn(Object colname) {
		String colnum = "";
		if ("bsdh008_swdj".equals(colname)) {
			colnum = "col54074";
		}
		if ("bsdh008_swrd".equals(colname)) {
			colnum = "col54075";
		}
		if ("bsdh008_fp".equals(colname)) {
			colnum = "col54076";
		}
		if ("bsdh008_sbns".equals(colname)) {
			colnum = "col54077";
		}
		if ("bsdh008_zm".equals(colname)) {
			colnum = "col54078";
		}
		if ("".equals(colname)) {
			colnum = "col54074";
		}
		return colnum;
	}

	// 办税学堂
	public static String getColnum_bsxt(Object colname) {
		String url = "";
		if ("23201".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/col/col47993/index.html";
		}
		if ("23202".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/col/col47997/index.html";
		}
		if ("23203".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/col/col48002/index.html";
		}
		if ("23204".equals(colname)) {
			url = "http://218.93.18.190:81/nsrschool/more.do?action=kcxx";
		}
		if ("23205".equals(colname)) {
			url = "http://school.cmclouds.com/suzhou/school/szds/noticeList.html";
		}
		if ("23206".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/col/col48014/index.html";
		}
		if ("23207".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/col/col48018/index.html";
		}
		if ("23208".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/col/col48080/index.html";
		}
		if ("23209".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/col/col48167/index.html";
		}
		if ("23210".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/col/col48171/index.html";
		}
		if ("23211".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/col/col48175/index.html";
		}
		if ("23212".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/col/col48179/index.html";
		}
		if ("23213".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/col/col48183/index.html";
		}
		if ("23217".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/col/col48071/index.html";
		}
		if ("".equals(colname)) {
			url = ApplicationContext.singleton().getValueAsString(
					"sjmh.server.www")
					+ "/col/col47993/index.html";
		}
		return url;
	}

	public static ResponseEvent getEsbResponseEvent(ESBRequestEvent reqEvent,
			Connection con) throws Exception {
		LogWritter.sysError("调用ctp.esb.socket.server:"
				+ ApplicationContext.singleton().getValueAsString(
						"ctp.esb.socket.server"));
		Socket conn = new Socket(ApplicationContext.singleton()
				.getValueAsString("ctp.esb.socket.server"),
				Integer.parseInt(ApplicationContext.singleton()
						.getValueAsString("ctp.esb.socket.server.port")));
		LogWritter.sysError("ctp.esb.socket.server创建成功");
		reqEvent.setJydm(ApplicationContext.singleton().getValueAsString(
				"ctp.esb.zsbs.jydm"));
		reqEvent.setMbswjgdm("23200000000");
		reqEvent.setBcjhdld(ApplicationContext.singleton().getValueAsString(
				"ctp.esb.bcjydld"));
		SocketComm socketComm = new SocketComm();
		socketComm.doSendRequestMessage(conn, reqEvent);
		ESBResponseEvent respEvent = new ESBResponseEvent(
				reqEvent.getSessionID(), reqEvent.getChannelType());
		socketComm.doReceiveMessageFromServer(conn, Integer
				.parseInt(ApplicationContext.singleton().getValueAsString(
						"ctp.esb.socket.timeout")), reqEvent, respEvent);
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
		return respEvent;
	}

	public static void upFile(Map map, Connection conn) throws Exception {

		List<MorphDynaBean> upFileList = (List<MorphDynaBean>) map.get("file");
		if (upFileList.size() == 0) {
			return;
		}

		Map<String, String> ftpMap = getFtp(
				StringUtil.empty(map.get("gljgDm")), conn);

		TlcslVO tlcslvo = new TlcslVO();
		tlcslvo.setLsh((String) map.get("lsh"));
		if (null != (String) map.get("swglm")) {
			tlcslvo.setSwglm((String) map.get("swglm"));
		}

		if (null != (String) map.get("nsrMc")) {
			tlcslvo.setNsrmc((String) map.get("nsrMc"));
		}

		if (null != (String) map.get("nsrSbh")) {
			tlcslvo.setNsrsbh((String) map.get("nsrSbh"));
		}

		if (null != (String) map.get("gljgdm")) {
			tlcslvo.setGljgdm((String) map.get("gljgdm"));
		}

		tlcslvo.setLcslh((String) map.get("lsh"));
		tlcslvo.setSssxlsh((String) map.get("ywsxDm"));

		tlcslvo.setStr_cjsjsj(getDateTime(conn, "yyyy-MM-dd"));
		tlcslvo.setScbj("0");
		tlcslvo.setSsrq(getDateTime(conn, "yyyy-MM-dd"));

		TlcslBPO.deleteByPK(conn, (String) map.get("lsh"));
		TlcslBPO.insert(conn, tlcslvo);

		TlcslclVO tlcslclvo = null;
		TwjxxVO twjxxvo = null;
		MorphDynaBean upFileMap = null;
		List<Map<String, String>> wjList = null;
		byte[] wjNr;

		Map<String, String> tlcslclvoLsh = new HashMap<>();

		for (int i = 0; i < upFileList.size(); i++) {
			upFileMap = upFileList.get(i);
			// 此表是保旺达公司设计，字段命名比较乱
			TlcslclVO tempVO = TlcslclBPO.queryByPK(conn,
					(String) map.get("lsh"), (String) upFileMap.get("zlBm"));
			// 判断是否已经上传过
			// 如果上传过则根据申请流水号和资料编码将之前的文件全部删除
			if (null != tempVO) {
				wjList = TwjxxBPO.getListByPK(conn, tempVO.getLsh());
				for (int n = 0; n < wjList.size(); n++) {
					twjxxvo = (TwjxxVO) wjList.get(n);
					FtpNewUtil.deleteFile(
							StringUtil.empty(ftpMap.get("ftpIp")),
							StringUtil.empty(ftpMap.get("ftpUser")),
							StringUtil.empty(ftpMap.get("ftpPwd")),
							twjxxvo.getWjwz(), twjxxvo.getWjmc());
					TwjxxBPO.deleteByPK(conn,
							StringUtil.empty(twjxxvo.getLsh()));
				}
				tlcslclvoLsh.put((String) upFileMap.get("zlBm"),
						tempVO.getLsh());
			} else {
				// 如果没上传过则生成一条上传记录
				tlcslclvo = new TlcslclVO();
				tlcslclvo.setLcslh((String) map.get("lsh"));
				tlcslclvo.setSsclbh((String) upFileMap.get("zlBm"));
				tlcslclvo.setClly("3");
				tlcslclvo.setSczt("1");
				tlcslclvo.setScbj("0");
				tlcslclvo.setLcslclmc((String) upFileMap.get("zlMc"));
				tlcslclvo.setFbzldm((String) upFileMap.get("zlBm"));
				tlcslclvo.setLsh(getGUID(conn));
				TlcslclBPO.deleteByPK(conn, (String) map.get("lsh"),
						(String) upFileMap.get("zlBm"));
				TlcslclBPO.insert(conn, tlcslclvo);
				tlcslclvoLsh.put((String) upFileMap.get("zlBm"),
						tlcslclvo.getLsh());
			}
		}

		for (int i = 0; i < upFileList.size(); i++) {
			upFileMap = upFileList.get(i);
			if (null == upFileMap.get("base64")
					|| "".equals(upFileMap.get("base64").toString())) {
				continue;
			}
			twjxxvo = new TwjxxVO();
			twjxxvo.setLsh(getGUID(conn));
			System.out.println(twjxxvo.getLsh());
			twjxxvo.setLshwj(tlcslclvoLsh.get((String) upFileMap.get("zlBm")));
			twjxxvo.setWjwz(StringUtil.empty(ftpMap.get("ml")));
			String wjm = twjxxvo.getLsh() + "_" + (i + 1) + "."
					+ upFileMap.get("type");
			twjxxvo.setWjmc(wjm);
			twjxxvo.setXh(String.valueOf(i + 1));
			twjxxvo.setScbj("0");
			wjNr = Base64.decodeBase64(upFileMap.get("base64").toString()
					.getBytes());
			twjxxvo.setWjdx(String.valueOf(wjNr.length));

			if ("JPG".equals(upFileMap.get("type").toString().toUpperCase())
					|| "PNG".equals(upFileMap.get("type").toString()
							.toUpperCase())) {
				// 图片
				twjxxvo.setWjlx("0");
			} else {
				// PDF
				twjxxvo.setWjlx("1");
			}
			TwjxxBPO.insert(conn, twjxxvo);
			InputStream is = new ByteArrayInputStream(wjNr);
			FtpNewUtil.uploadFile(StringUtil.empty(ftpMap.get("ftpIp")),
					StringUtil.empty(ftpMap.get("ftpUser")),
					StringUtil.empty(ftpMap.get("ftpPwd")),
					StringUtil.empty(ftpMap.get("ml")), wjm, is);
		}
	}

	public static void upFileDecrypt(Map map, Connection conn) throws Exception {

		List<MorphDynaBean> upFileList = (List<MorphDynaBean>) map.get("file");
		if (upFileList.size() == 0) {
			return;
		}

		Map<String, String> ftpMap = getFtp(
				StringUtil.empty(map.get("gljgDm")), conn);

		TlcslVO tlcslvo = new TlcslVO();
		tlcslvo.setLsh((String) map.get("lsh"));
		if (null != (String) map.get("swglm")) {
			tlcslvo.setSwglm((String) map.get("swglm"));
		}

		if (null != (String) map.get("nsrMc")) {
			tlcslvo.setNsrmc((String) map.get("nsrMc"));
		}

		if (null != (String) map.get("nsrSbh")) {
			tlcslvo.setNsrsbh((String) map.get("nsrSbh"));
		}

		if (null != (String) map.get("gljgdm")) {
			tlcslvo.setGljgdm((String) map.get("gljgdm"));
		}

		tlcslvo.setLcslh((String) map.get("lsh"));
		tlcslvo.setSssxlsh((String) map.get("ywsxDm"));

		tlcslvo.setStr_cjsjsj(getDateTime(conn, "yyyy-MM-dd"));
		tlcslvo.setScbj("0");
		tlcslvo.setSsrq(getDateTime(conn, "yyyy-MM-dd"));

		TlcslBPO.deleteByPK(conn, (String) map.get("lsh"));
		TlcslBPO.insert(conn, tlcslvo);

		TlcslclVO tlcslclvo = null;
		TwjxxVO twjxxvo = null;
		MorphDynaBean upFileMap = null;
		List<Map<String, String>> wjList = null;
		byte[] wjNr;

		Map<String, String> tlcslclvoLsh = new HashMap<>();

		for (int i = 0; i < upFileList.size(); i++) {
			upFileMap = upFileList.get(i);
			// 此表是保旺达公司设计，字段命名比较乱
			TlcslclVO tempVO = TlcslclBPO.queryByPK(conn,
					(String) map.get("lsh"), (String) upFileMap.get("zlBm"));
			// 判断是否已经上传过
			// 如果上传过则根据申请流水号和资料编码将之前的文件全部删除
			if (null != tempVO) {
				wjList = TwjxxBPO.getListByPK(conn, tempVO.getLsh());
				for (int n = 0; n < wjList.size(); n++) {
					twjxxvo = (TwjxxVO) wjList.get(n);
					FtpNewUtil.deleteFile(
							StringUtil.empty(ftpMap.get("ftpIp")),
							StringUtil.empty(ftpMap.get("ftpUser")),
							StringUtil.empty(ftpMap.get("ftpPwd")),
							twjxxvo.getWjwz(), twjxxvo.getWjmc());
					TwjxxBPO.deleteByPK(conn,
							StringUtil.empty(twjxxvo.getLsh()));
				}
				tlcslclvoLsh.put((String) upFileMap.get("zlBm"),
						tempVO.getLsh());
			} else {
				// 如果没上传过则生成一条上传记录
				tlcslclvo = new TlcslclVO();
				tlcslclvo.setLcslh((String) map.get("lsh"));
				tlcslclvo.setSsclbh((String) upFileMap.get("zlBm"));
				tlcslclvo.setClly("3");
				tlcslclvo.setSczt("1");
				tlcslclvo.setScbj("0");
				tlcslclvo.setLcslclmc((String) upFileMap.get("zlMc"));
				tlcslclvo.setFbzldm((String) upFileMap.get("zlBm"));
				tlcslclvo.setLsh(getGUID(conn));
				TlcslclBPO.deleteByPK(conn, (String) map.get("lsh"),
						(String) upFileMap.get("zlBm"));
				TlcslclBPO.insert(conn, tlcslclvo);
				tlcslclvoLsh.put((String) upFileMap.get("zlBm"),
						tlcslclvo.getLsh());
			}
		}

		for (int i = 0; i < upFileList.size(); i++) {
			upFileMap = upFileList.get(i);
			if (null == upFileMap.get("base64")
					|| "".equals(upFileMap.get("base64").toString())) {
				continue;
			}
			twjxxvo = new TwjxxVO();
			twjxxvo.setLsh(getGUID(conn));
			System.out.println(twjxxvo.getLsh());
			twjxxvo.setLshwj(tlcslclvoLsh.get((String) upFileMap.get("zlBm")));
			twjxxvo.setWjwz(StringUtil.empty(ftpMap.get("ml")));
			String wjm = twjxxvo.getLsh() + "_" + (i + 1) + "."
					+ upFileMap.get("type");
			twjxxvo.setWjmc(wjm);
			twjxxvo.setXh(String.valueOf(i + 1));
			twjxxvo.setScbj("0");
			wjNr = Base64.decodeBase64(AESTool.decrypt(
					upFileMap.get("base64").toString(), "0102030405060708")
					.getBytes());
			twjxxvo.setWjdx(String.valueOf(wjNr.length));

			if ("JPG".equals(upFileMap.get("type").toString().toUpperCase())
					|| "PNG".equals(upFileMap.get("type").toString()
							.toUpperCase())) {
				// 图片
				twjxxvo.setWjlx("0");
			} else {
				// PDF
				twjxxvo.setWjlx("1");
			}
			TwjxxBPO.insert(conn, twjxxvo);
			InputStream is = new ByteArrayInputStream(wjNr);
			FtpNewUtil.uploadFile(StringUtil.empty(ftpMap.get("ftpIp")),
					StringUtil.empty(ftpMap.get("ftpUser")),
					StringUtil.empty(ftpMap.get("ftpPwd")),
					StringUtil.empty(ftpMap.get("ml")), wjm, is);
		}
	}

	public static String getWsh(String swglm) {
		if (null != swglm) {
			return swglm + DateUtil.getNowString("yyyyMMddHHmmss") + "000";
		} else {
			return null;
		}
	}

	public static Map getisNew(Connection conn, String sqzldm, String swglm)
			throws SQLException {
		Map m = new HashMap();
		String temp = "0";
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(sqzldm);
		paramList.add(swglm);
		CachedRowSet rs = QueryCssBPO
				.findAll(
						conn,
						// "select b.zt from db_wsbs.t_wb_wsfwqkb b where b.zt not in ('3','4','10','11') and b.sqlx_dm= ? and b.swglm=?",
						"select count(b.zt) num from db_wsbs.t_wb_wsfwqkb b where b.zt in ('1','5','9') and b.sqlx_dm= ? and b.swglm=?",
						paramList);
		rs.next();
		if (rs.getInt("num") > 0) {
			// String t = rs.getString("zt");
			// if ("0".equals(t)) {
			// m.put("zt", "已暂存");
			// } else if ("1".equals(t)) {
			// m.put("zt", "已提交");
			// } else if ("2".equals(t)) {
			// m.put("zt", "已迁移");
			// } else if ("5".equals(t)) {
			// m.put("zt", "审核中");
			// } else if ("6".equals(t)) {
			// m.put("zt", "已退回");
			// } else if ("7".equals(t)) {
			// m.put("zt", "已退回");
			// } else if ("8".equals(t)) {
			// m.put("zt", "迁移中");
			// } else if ("9".equals(t)) {
			// m.put("zt", "审核中");
			// }
			m.put("temp", temp);
		} else {
			m.put("temp", "1");
		}
		return m;
	}

	// 附送资料
	public static List<Map<String, String>> getFszl(Connection con, String XMDM)
			throws SQLException {
		List<Map<String, String>> FszlList = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(XMDM);
		CachedRowSet rs = QueryCssBPO.findAll(con,
				"SELECT * FROM DB_WSBS.T_WSFW_FSZL  WHERE XMDM=? AND XY_BJ=1",
				sqlParams);
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("XMDM", rs.getString("XMDM"));
			map.put("FBZL_DM", rs.getString("FBZL_DM"));
			map.put("FBZL_MC", rs.getString("FBZL_MC"));
			FszlList.add(map);
		}
		return FszlList;
	}

	// 办税服务厅
	public static String getBsfwt(Object colname) {
		String bsfwt = "";
		if ("23201".equals(colname)) {
			bsfwt = "10001";
		}
		if ("23202".equals(colname)) {
			bsfwt = "10002";
		}
		if ("23203".equals(colname)) {
			bsfwt = "10003";
		}
		if ("23204".equals(colname)) {
			bsfwt = "10004";
		}
		if ("23205".equals(colname)) {
			bsfwt = "10005";
		}
		if ("23206".equals(colname)) {
			bsfwt = "10006";
		}
		if ("23207".equals(colname)) {
			bsfwt = "10007";
		}
		if ("23208".equals(colname)) {
			bsfwt = "10008";
		}
		if ("23209".equals(colname)) {
			bsfwt = "10009";
		}
		if ("23210".equals(colname)) {
			bsfwt = "10010";
		}
		if ("23211".equals(colname)) {
			bsfwt = "10011";
		}
		if ("23212".equals(colname)) {
			bsfwt = "10012";
		}
		if ("23213".equals(colname)) {
			bsfwt = "10013";
		}
		if ("23217".equals(colname)) {
			bsfwt = "10014";
		}
		if ("23216".equals(colname)) {
			bsfwt = "10015";
		}
		if ("10016".equals(colname)) {
			bsfwt = "10016";
		}
		if ("".equals(colname)) {
			bsfwt = "10001";
		}
		return bsfwt;
	}

	public static List<Map<String, String>> getZt(Connection conn,
			String sqzldm, String swglm) throws SQLException {
		List<Map<String, String>> ZtList = new ArrayList<Map<String, String>>();
		Map m = new HashMap();
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(sqzldm);
		paramList.add(swglm);
		CachedRowSet rs = QueryCssBPO
				.findAll(
						conn,
						"select b.zt,to_char(b.sq_sj,'yyyy-mm-dd') sq_sj from db_wsbs.t_wb_wsfwqkb b where b.zt not in ('3','4','10','11') and b.sqlx_dm= ? and b.swglm= ?",
						paramList);
		while (rs.next()) {
			String t = rs.getString("zt");
			if ("0".equals(t)) {
				m.put("zt", "已暂存");
			} else if ("1".equals(t)) {
				m.put("zt", "已提交");
			} else if ("2".equals(t)) {
				m.put("zt", "已迁移");
			} else if ("5".equals(t)) {
				m.put("zt", "审核中");
			} else if ("6".equals(t)) {
				m.put("zt", "已退回");
			} else if ("7".equals(t)) {
				m.put("zt", "已退回");
			} else if ("8".equals(t)) {
				m.put("zt", "迁移中");
			} else if ("9".equals(t)) {
				m.put("zt", "审核中");
			}
			m.put("sqsj", rs.getString("sq_sj"));
			ZtList.add(m);
		}
		return ZtList;
	}

	public static String getAaginURL(Map map, Connection conn)
			throws SQLException, TaxBaseBizException {
		String url = "";
		String pzxh = (String) map.get("pzxh");
		String ddly = (String) map.get("ddly");
		String sql = null;
		CachedRowSet rs = null;
		// 校验是否可以再次发起扣款
		int gs = 0;
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(pzxh);
		if ("01".equals(ddly)) {
			sql = "SELECT count(1) as gs   FROM DB_WSBS.T_WB_SBBQK_ZRR X WHERE X.PZ_XH = ?    AND X.YBTSEHDJ_JE > 0    AND X.ZT = 'j'";
		} else {
			sql = "SELECT count(1) as gs   FROM DB_WSBS.T_WB_SBBQK X WHERE X.PZ_XH = ?    AND X.YBTSEHDJ_JE > 0    AND X.ZT = 'j'";
		}
		rs = QueryCssBPO.findAll(conn, sql, sqlParams);
		if (rs.next()) {
			gs = rs.getInt("gs");
		}
		if (gs == 0) {
			LogWritter.sysError("生成订单出错！凭证序号：" + pzxh + "，错误信息："
					+ "此申报表不满足再次扣款条件。报表状态扣款失败，金额大于0才允许再次扣款！");
			return url;
		}
		// 获取URL
		sqlParams = new ArrayList();
		sqlParams.add("WY_ZSBS_Again");
		sqlParams.add("23200000000");
		sql = "select a.csz as cs  from DB_WSBS.t_xt_xtcs a where a.csbm = ? and a.swjg_dm = ?";
		rs = QueryCssBPO.findAll(conn, sql, sqlParams);
		if (rs.next()) {
			url = rs.getString("cs");
			url = url + "&ddLy=" + ddly + "&pzXh=" + pzxh;
		}
		return url;
	}

	public static Map queryAaginURL(Map map, Connection conn)
			throws SQLException, TaxBaseBizException {
		Map<String, String> resultMap = new HashMap<String, String>();
		String WYUrl = "";
		String ZFBUrl = "";
		String pzxh = (String) map.get("pzxh");
		String ddly = (String) map.get("ddly");
		boolean hasUrl = (boolean) map.get("hasUrl");
		boolean hasZFBUrl = (boolean) map.get("hasZFBUrl");
		String sql = null;
		CachedRowSet rs = null;
		// 校验是否可以再次发起扣款
		int gs = 0;
		ArrayList sqlParams = new ArrayList();
		sqlParams.add(pzxh);
		if ("01".equals(ddly)) {
			sql = "SELECT count(1) as gs   FROM DB_WSBS.T_WB_SBBQK_ZRR X WHERE X.PZ_XH = ?    AND X.YBTSEHDJ_JE > 0    AND X.ZT = 'j'";
		} else {
			sql = "SELECT count(1) as gs   FROM DB_WSBS.T_WB_SBBQK X WHERE X.PZ_XH = ?    AND X.YBTSEHDJ_JE > 0    AND X.ZT = 'j'";
		}
		rs = QueryCssBPO.findAll(conn, sql, sqlParams);
		if (rs.next()) {
			gs = rs.getInt("gs");
		}
		if (gs == 0) {
			LogWritter.sysError("生成订单出错！凭证序号：" + pzxh + "，错误信息："
					+ "此申报表不满足再次扣款条件。报表状态扣款失败，金额大于0才允许再次扣款！");
			return null;
		}
		// 获取银联URL
		if (hasUrl) {
			sqlParams = new ArrayList();
			sqlParams.add("WY_ZSBS_Again");
			sqlParams.add("23200000000");
			sql = "select a.csz as cs  from DB_WSBS.t_xt_xtcs a where a.csbm = ? and a.swjg_dm = ?";
			rs = QueryCssBPO.findAll(conn, sql, sqlParams);
			if (rs.next()) {
				WYUrl = rs.getString("cs");
				WYUrl = WYUrl + "&ddLy=" + ddly + "&pzXh=" + pzxh;
				resultMap.put("WYUrl", WYUrl);
			}
		}
		// 获取支付宝URL
		if (hasZFBUrl) {
			sqlParams = new ArrayList();
			sqlParams.add("WY_ZRR_ZFBKKAgain");
			sqlParams.add("23200000000");
			rs = QueryCssBPO.findAll(conn, sql, sqlParams);
			if (rs.next()) {
				ZFBUrl = rs.getString("cs");
				ZFBUrl = ZFBUrl + "&ddLy=" + ddly + "&pzXh=" + pzxh;
				resultMap.put("ZFBUrl", ZFBUrl);
			}
		}
		return resultMap;
	}

	public static Map getCreateZrrOrderURL(Map map, Connection conn)
			throws SQLException, TaxBaseBizException {
		Map<String, String> resultMap = new HashMap<String, String>();
		String WYUrl = "";
		String ZFBUrl = "";
		String pzxh = (String) map.get("pzxh");
		String ddly = (String) map.get("ddly");
		boolean hasUrl = (boolean) map.get("hasUrl");
		boolean hasZFBUrl = (boolean) map.get("hasZFBUrl");
		String sql = null;
		CachedRowSet rs = null;
		ArrayList sqlParams = null;
		// 校验是否可以发起扣款
		int gs = 0;
		sqlParams = new ArrayList();
		sqlParams.add(pzxh);
		if ("01".equals(ddly)) {
			sql = "SELECT count(1) as gs   FROM DB_WSBS.T_WB_SBBQK_ZRR X WHERE X.PZ_XH = ?    AND X.YBTSEHDJ_JE > 0    AND X.ZT = '1'";
		} else {
			sql = "SELECT count(1) as gs   FROM DB_WSBS.T_WB_SBBQK X WHERE X.PZ_XH = ?    AND X.YBTSEHDJ_JE > 0    AND X.ZT = '1'";
		}
		rs = QueryCssBPO.findAll(conn, sql, sqlParams);
		if (rs.next()) {
			gs = rs.getInt("gs");
		}
		if (gs == 0) {
			LogWritter.sysError("生成订单出错！凭证序号：" + pzxh + "，错误信息："
					+ "此申报表不满足扣款条件。报表状态为已提交，金额大于0才允许网银扣款！");
			return resultMap;
		}
		// 获取银联URL
		if (hasUrl) {
			sqlParams = new ArrayList();
			sqlParams.add("WY_ZSBS_CREATEORDER");
			sqlParams.add("23200000000");
			sql = "select a.csz as cs  from DB_WSBS.t_xt_xtcs a where a.csbm = ? and a.swjg_dm = ?";
			rs = QueryCssBPO.findAll(conn, sql, sqlParams);
			if (rs.next()) {
				WYUrl = rs.getString("cs");
				WYUrl = WYUrl + "&ddLy=" + ddly + "&pzXh=" + pzxh;
				resultMap.put("WYUrl", WYUrl);
			}
		}
		// 获取支付宝URL
		if (hasZFBUrl) {
			sqlParams = new ArrayList();
			sqlParams.add("WY_ZRR_SJZFBKK");
			sqlParams.add("23200000000");
			rs = QueryCssBPO.findAll(conn, sql, sqlParams);
			if (rs.next()) {
				ZFBUrl = rs.getString("cs");
				ZFBUrl = ZFBUrl + "&ddLy=" + ddly + "&pzXh=" + pzxh;
				resultMap.put("ZFBUrl", ZFBUrl);
			}
		}
		return resultMap;
	}

	/**
	 * 获取国籍地区列表
	 * 
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public static List findGJ(Connection con) throws SQLException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT T.GJDQ_DM, T.MC_J GJDQ_MC FROM DB_WSBS.T_DM_GY_GJ T WHERE T.XY_BJ = '1' ORDER BY T.MC_J",
						null);
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("GJDQ_DM", rs.getString("GJDQ_DM"));
			map.put("GJDQ_MC", rs.getString("GJDQ_MC"));
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取自然人职业列表
	 * 
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public static List findZRRZY(Connection con) throws SQLException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT T.ZY_DM, T.MC FROM DB_WSBS.T_DM_DJ_ZY T WHERE T.XY_BJ = '1'",
						null);
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("ZY_DM", rs.getString("ZY_DM"));
			map.put("MC", rs.getString("MC"));
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取自然人登记职务列表
	 * 
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public static List findZRRZW(Connection con) throws SQLException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT T.ZW_DM, T.MC FROM DB_WSBS.T_DM_DJ_ZW T WHERE T.XY_BJ = '1'",
						null);
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("ZW_DM", rs.getString("ZW_DM"));
			map.put("MC", rs.getString("MC"));
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取自然人学历列表
	 * 
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public static List findZRRXL(Connection con) throws SQLException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT T.XL_DM, T.MC FROM DB_WSBS.T_DM_GY_XL T WHERE T.XY_BJ = '1'",
						null);
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("XL_DM", rs.getString("XL_DM"));
			map.put("MC", rs.getString("MC"));
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取注册类型列表
	 * 
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public static List findDJZCLX(Connection con) throws SQLException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT T.DJZCLX_DM, T.MC_J MC FROM DB_WSBS.T_DM_GY_DJZCLX T WHERE T.XY_BJ = '1'",
						null);
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("DJZCLX_DM", rs.getString("DJZCLX_DM"));
			map.put("MC", rs.getString("MC"));
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取经营行业小类列表
	 * 
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public static List findJYHYXL(Connection con) throws SQLException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT T.JYHYXL_DM, T.MC_J MC FROM DB_WSBS.T_DM_GY_JYHYXL T WHERE T.XY_BJ = '1'",
						null);
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("JYHYXL_DM", rs.getString("JYHYXL_DM"));
			map.put("MC", rs.getString("MC"));
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取行政区划列表
	 * 
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public static List findXZQH(Connection con) throws SQLException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT T.XZQHSZXS_DM, T.MC_J XZQHSZXS_MC FROM DB_WSBS.T_DM_GY_XZQHSZXS T WHERE T.XZQHSZXS_DM != '00' AND T.XY_BJ = '1' ORDER BY T.MC_J",
						null);
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("XZQHSZXS_DM", rs.getString("XZQHSZXS_DM"));
			map.put("XZQHSZXS_MC", rs.getString("XZQHSZXS_MC"));
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取地理位置市列表
	 * 
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public static List findDJDLWZS(Connection con) throws SQLException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT T.XZQHDS_DM, T.MC_J MC FROM DB_WSBS.T_DM_GY_XZQHDS T WHERE T.XY_BJ = '1' ORDER BY T.MC_J",
						null);
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("XZQHDS_DM", rs.getString("XZQHDS_DM"));
			map.put("MC", rs.getString("MC"));
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取地理位置县(区)列表
	 * 
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public static List findDJDLWZXQ(Connection con) throws SQLException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		// ArrayList<Object> sqlParams = new ArrayList<Object>();
		// sqlParams.add(xzqhds_dm);
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT T.XZQHXQ_DM, T.MC_J MC FROM DB_WSBS.T_DM_GY_XZQHXQ T WHERE T.XY_BJ = '1' ORDER BY T.MC_J",
						null);
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("XZQHXQ_DM", rs.getString("XZQHXQ_DM"));
			map.put("MC", rs.getString("MC"));
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取地理位置乡镇(街道)列表
	 * 
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public static List findDJDLWZXZJD(String xzqhds_dm, Connection con)
			throws SQLException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		ArrayList<Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(xzqhds_dm);
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT T.XZJD_DM, T.MC_J MC FROM DB_WSBS.T_DM_GY_XZJD T WHERE T.XY_BJ = '1' AND T.XZQHXQ_DM = ? ORDER BY T.MC_J",
						sqlParams);
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("XZJD_DM", rs.getString("XZJD_DM"));
			map.put("MC", rs.getString("MC"));
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取预约办税时间
	 * 
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public static List findYybssj(Connection con) throws SQLException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT T.FWSJ, T.CXDM FROM DB_WSBS.T_DM_GY_YYBSSJ T WHERE T.XY_BJ = '1' ORDER BY T.CXDM",
						null);
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("FWSJ", rs.getString("FWSJ"));
			map.put("CXDM", rs.getString("CXDM"));
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取预约事项类型
	 * 
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public static List findYysxlx(Connection con) throws SQLException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		CachedRowSet rs = QueryCssBPO
				.findAll(
						con,
						"SELECT T.MC, T.SX_DM FROM DB_WSBS.T_DM_GY_SXLX T WHERE T.XY_BJ = '1' ORDER BY T.SX_DM",
						null);
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("MC", rs.getString("MC"));
			map.put("SX_DM", rs.getString("SX_DM"));
			list.add(map);
		}
		return list;
	}

	public static List<Map<String, String>> queryJsgs(String pzzlDm,
			String xtLx, Connection conn) throws SQLException {
		ArrayList<String> sqlParams = new ArrayList<String>();
		sqlParams.add(pzzlDm);
		sqlParams.add(xtLx);
		String sql = "select T.GS,T.PXZD,T.GSLX,T.TYPE from T_CS_WB_YMJSGS T WHERE PZZL_DM=? AND XY_BJ='1' AND XTLX=?  ORDER BY T.PXZD";
		CachedRowSet rs = QueryCssBPO.findAll(conn, sql, sqlParams);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("GS", rs.getString("GS"));
			map.put("PXZD", rs.getString("PXZD"));
			map.put("GSLX", rs.getString("GSLX"));
			map.put("TYPE", rs.getString("TYPE"));
			list.add(map);
		}
		return list;
	}

}
