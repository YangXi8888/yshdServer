package gov.jslt.taxcore.taxblh.swd.swd001;

import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.LoginVO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.bpo.QueryCssBPO;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;

public class Swd001BLH extends BaseBizLogicHandler {

	protected ResponseEvent performTask(RequestEvent req, Connection conn)
			throws SQLException, TaxBaseBizException {
		String handleCode = req.getDealMethod();
		if ("initForm".equals(handleCode)) {
			return initForm(req, conn);
		}
		return null;
	}

	protected ResponseEvent validateData(RequestEvent req, Connection conn)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	protected ResponseEvent initForm(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent reEvent = new ResponseEvent();
		HttpServletRequest request = (HttpServletRequest) reqEvent.reqMapParam
				.get("HttpServletRequest");
		JsonReqData reqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute(
				reqData.getYhwybz());
		reqEvent.reqMapParam.put("sessionId", loginVO.getYhwybz());
		reqEvent.reqMapParam.put("pNodeId", "0");
		reqEvent.reqMapParam.put("yhLxDm", loginVO.getYhLxDm());
		reqEvent.reqMapParam.put("qyYhDm", loginVO.getQyYhDm());
		List<Object> gnsList = new ArrayList<Object>();
		getGns(conn, gnsList, reqEvent.reqMapParam);
		reEvent.respMapParam.put("gnsList", gnsList);
		reEvent.setReponseMesg("主页初始化成功");
		return reEvent;
	}

	private void getGns(Connection conn, List<Object> list,
			Map<String, Object> tempMap) throws SQLException {
		ArrayList<Object> sqlParam = new ArrayList<Object>();
		sqlParam.add(tempMap.get("pNodeId"));
		sqlParam.add(tempMap.get("yhLxDm") + "%");
		if (null != tempMap.get("qyYhDm") && !"".equals(tempMap.get("qyYhDm"))) {
			sqlParam.add(tempMap.get("qyYhDm") + "%");
		} else {
			sqlParam.add("%");
		}
		String sql = "SELECT * FROM  T_YS_GNS A  WHERE A.PNODE_ID=? AND A.XY_BJ='1' AND A.YHLX_DM LIKE ? AND A.QYYH_DM LIKE ? ORDER BY A.PNODE_ID, A.PX";
		CachedRowSet rs = QueryCssBPO.findAll(conn, sql, sqlParam);
		Map<String, Object> map = null;
		while (rs.next()) {
			map = new HashMap<String, Object>();
			list.add(map);
			map.put("id", rs.getString("NODE_ID"));
			map.put("text", rs.getString("CD_MC"));
			if (null == rs.getString("ICONCLS")
					|| "".equals(rs.getString("ICONCLS"))) {
				map.put("iconCls", "icon-wj");
			} else {
				map.put("iconCls", rs.getString("ICONCLS"));
			}
			map.put("openType", rs.getString("OPENFLAG"));
			if (null != rs.getString("URL") && !"".equals(rs.getString("URL"))) {
				if (rs.getString("URL").indexOf("?") != -1) {
					map.put("href", rs.getString("URL") + "&sessionId="
							+ tempMap.get("sessionId"));
				} else {
					map.put("href", rs.getString("URL") + "?sessionId="
							+ tempMap.get("sessionId"));
				}
			} else {
				map.put("href", "");
			}
			if ("0".equals(rs.getString("ISLEAF"))) {
				map.put("state", "opened");
				List<Object> childrenList = new ArrayList<Object>();
				map.put("children", childrenList);
				tempMap.put("pNodeId", rs.getString("NODE_ID"));
				getGns(conn, childrenList, tempMap);
			}
		}
	}

}
