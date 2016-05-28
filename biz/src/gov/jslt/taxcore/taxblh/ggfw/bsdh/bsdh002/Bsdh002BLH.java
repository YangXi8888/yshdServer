package gov.jslt.taxcore.taxblh.ggfw.bsdh.bsdh002;

import gov.jslt.taxcore.taxblh.comm.JyxxJgbHelper;
import gov.jslt.taxcore.taxblh.comm.ZBCoreHelper;
import gov.jslt.taxcore.taxbpo.ggfw.bsdh.bsdh002.Bsdh002BPO;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.ggfw.bsdh.bsdh002.Bsdh002VO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.bpo.QueryCssBPO;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;

public class Bsdh002BLH extends BaseBizLogicHandler {
	protected ResponseEvent performTask(RequestEvent req, Connection con)
			throws TaxBaseBizException, SQLException {
		String delMethod = req.getDealMethod();
		if ("queryContent".equals(delMethod)) {
			return queryContent(req, con);
		}
		return null;
	}

	protected ResponseEvent queryContent(RequestEvent req, Connection con)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData jsonReqData = (JsonReqData) req.reqMapParam
				.get("JsonReqData");
		Map<String, Object> dataMap = jsonReqData.getData();
		String pid = "";
		String mcj = "";
		CachedRowSet rs = null;
		String id = (String) dataMap.get("id");
		try {
			List<Map<String, String>> bssjList = new ArrayList<Map<String, String>>();
			// 地市List
			List<Map<String, String>> dsList = new ArrayList<Map<String, String>>();
			List<Map<String, String>> List = new ArrayList<Map<String, String>>();
			dsList = Bsdh002BPO.getListByPK(con, id, 0);
			for (int i = 0; i < dsList.size(); i++) {
				Bsdh002VO vo = (Bsdh002VO) dsList.get(i);
				pid = vo.getId();
				mcj = vo.getMcj().trim();
				ArrayList<Object> paramList = new ArrayList<Object>();
				paramList.add(pid);
				rs = QueryCssBPO.findAll(con,
						"SELECT T.* FROM DB_ZSBS.T_ZB_BSDT T  where t.pid= ? ORDER BY T.PX",
						paramList);
				Map<String, String> map;
				while (rs.next()) {
					map = new HashMap<String, String>();
					map.put("content",
							rs.getString("MC_J").trim() + "$"
									+ rs.getString("BSYW").trim() + "$"
									+ rs.getString("DZ").trim() + "("
									+ rs.getString("YB").trim() + ")" + "$"
									+ rs.getString("BSSJQ").trim() + "$"
									+ rs.getString("DH").trim());
					bssjList.add(map);
				}
				Map map1 = new HashMap();
				map1.put(mcj, bssjList);
				List.add(map1);
				responseEvent.respMapParam.put("List", List);
				bssjList = new ArrayList<Map<String, String>>();
			}

		} catch (Exception e) {
			responseEvent.setRepCode(GeneralCons.ERROR_CODE_ZB9999);
			responseEvent.setReponseMesg(JyxxJgbHelper.queryJyxxx(
					ZBCoreHelper.getJyztMc(con, GeneralCons.ERROR_CODE_ZB9999),
					e.getMessage()));
			return responseEvent;
		}
		responseEvent.setRepCode(GeneralCons.SUCCESS_CODE);
		responseEvent.setReponseMesg(GeneralCons.SUCCESS_MSG);
		return responseEvent;
	}

	@Override
	protected ResponseEvent validateData(RequestEvent arg0, Connection arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
