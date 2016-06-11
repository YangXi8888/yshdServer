package gov.jslt.taxcore.taxblh.yhd.yhd001;

import gov.jslt.taxcore.taxbpo.nsrd.nsrd001.NsrCwbbBPO;
import gov.jslt.taxcore.taxbpo.nsrd.nsrd001.NsrJbxxBPO;
import gov.jslt.taxcore.taxbpo.nsrd.nsrd001.NsrSbfBPO;
import gov.jslt.taxcore.taxbpo.nsrd.nsrd001.NsrSfBPO;
import gov.jslt.taxcore.taxbpo.nsrd.nsrd001.NsrXzcfBPO;
import gov.jslt.taxevent.comm.FileVO;
import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonReqData;
import gov.jslt.taxevent.comm.LoginVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrCwbbVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrJbxxVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrSbfVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrSfVO;
import gov.jslt.taxevent.nsrd.nsrd001.NsrXzcfVO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import sun.jdbc.rowset.CachedRowSet;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.bpo.QueryCssBPO;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;

public class Yhd001BLH extends BaseBizLogicHandler {

	protected ResponseEvent performTask(RequestEvent req, Connection conn)
			throws SQLException, TaxBaseBizException {
		String handleCode = req.getDealMethod();
		if ("queryData".equals(handleCode)) {
			return queryData(req, conn);
		} else if ("downLoadFile".equals(handleCode)) {
			return downLoadFile(req, conn);
		}
		// else if ("downLoadAllFile".equals(handleCode)) {
		// return downLoadAllFile(req, conn);
		// }
		return null;
	}

	protected ResponseEvent queryData(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData reqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		HttpServletRequest request = (HttpServletRequest) reqEvent.reqMapParam
				.get("HttpServletRequest");
		LoginVO loingVo = (LoginVO) request.getSession().getAttribute(
				reqData.getYhwybz());

		ArrayList<Object> sqlParam = new ArrayList<Object>();
		sqlParam.add(loingVo.getQyYhDm());
		CachedRowSet rs;
		String sql = "SELECT T.ZB_UUID, T.NSR_MC, TO_CHAR(T.LR_SJ, 'YYYY-MM-DD HH24:MI:SS') FSRQ\n"
				+ "  FROM T_YS_NSRFS_ZB T\n"
				+ " WHERE T.QYYH_DM = ?\n"
				+ "   AND T.LR_SJ > TO_DATE(? || ' 00:00:01', 'YYYY-MM-DD HH24:MI:SS')";

		if (null != reqData.getData().get("rqq")
				&& !"".equals(reqData.getData().get("rqq"))) {
			sql = "SELECT T.ZB_UUID, T.NSR_MC, TO_CHAR(T.LR_SJ, 'YYYY-MM-DD HH24:MI:SS') FSRQ\n"
					+ "  FROM T_YS_NSRFS_ZB T\n"
					+ " WHERE T.QYYH_DM = ?\n"
					+ "   AND T.LR_SJ > TO_DATE(? || ' 00:00:01', 'YYYY-MM-DD HH24:MI:SS')\n"
					+ "   AND T.LR_SJ < TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')";
			sqlParam.add(reqData.getData().get("rqq"));
			sqlParam.add(reqData.getData().get("rqz"));
		}
		rs = QueryCssBPO.findAll(conn, sql, sqlParam);
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		Map<String, String> dataMap;
		while (rs.next()) {
			dataMap = new HashMap<String, String>();
			dataMap.put("ZB_UUID", rs.getString("ZB_UUID"));
			dataMap.put("NSR_MC", rs.getString("NSR_MC"));
			dataMap.put("FSRQ", rs.getString("FSRQ"));
			dataList.add(dataMap);
		}
		responseEvent.respMapParam.put("dataList", dataList);
		responseEvent.setReponseMesg("查询成功");
		return responseEvent;
	}

	protected ResponseEvent downLoadFile(RequestEvent reqEvent, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		JsonReqData reqData = (JsonReqData) reqEvent.reqMapParam
				.get("JsonReqData");
		String zbUuid = (String) reqData.getData().get("zbUuid");
		ArrayList<String> sqlParams = new ArrayList<String>();
		sqlParams.add(zbUuid);

		// 创建excel
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		// 头部样式
		HSSFFont headFont = wb.createFont();
		headFont.setFontName("黑体");
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		HSSFCellStyle headStyle = wb.createCellStyle();
		headStyle.setFont(headFont);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中

		// 纳税人基本信息表
		NsrJbxxVO jbxxVO = NsrJbxxBPO.queryByZbuuid(conn, zbUuid);
		// 创建sheet
		sheet = wb.createSheet();
		wb.setSheetName(0, "基本信息", (short) 1);// 设置seet标题，第3个参数为避免中文乱码
		sheet.createFreezePane(0, 1);// 冻结
		sheet.setColumnWidth((short) 0, (short) (40 * 256));
		sheet.setColumnWidth((short) 1, (short) (20 * 256));
		sheet.setColumnWidth((short) 2, (short) (20 * 256));
		sheet.setColumnWidth((short) 3, (short) (50 * 256));
		sheet.setColumnWidth((short) 4, (short) (20 * 256));
		// 纳税人基本信息头部
		row = sheet.createRow(0);
		cell = row.createCell((short) 0);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("纳税人名称");
		cell = row.createCell((short) 1);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("登记注册类型");
		cell = row.createCell((short) 2);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("国标行业");
		cell = row.createCell((short) 3);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("注册地址");
		cell = row.createCell((short) 4);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("信用等级");
		// 纳税人基本信息内容
		row = sheet.createRow(1);
		cell = row.createCell((short) 0);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(jbxxVO.getNsrmc());
		cell = row.createCell((short) 1);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(jbxxVO.getDjzclxmc());
		cell = row.createCell((short) 2);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(jbxxVO.getGbhymc());
		cell = row.createCell((short) 3);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(jbxxVO.getZcdz());
		cell = row.createCell((short) 4);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(jbxxVO.getXydj());

		// 税费信息
		List<NsrSfVO> sFList = NsrSfBPO.queryList(conn, "ZB_UUID=?", sqlParams);
		// 创建sheet
		sheet = wb.createSheet();
		wb.setSheetName(1, "税费信息", (short) 1);
		sheet.createFreezePane(0, 1);// 冻结
		sheet.setColumnWidth((short) 0, (short) (30 * 256));
		sheet.setColumnWidth((short) 1, (short) (30 * 256));
		sheet.setColumnWidth((short) 2, (short) (30 * 256));
		// 税费信息头部
		row = sheet.createRow(0);
		cell = row.createCell((short) 0);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("所属年度");
		cell = row.createCell((short) 1);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("税种");
		cell = row.createCell((short) 2);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("入库税额");
		// 税费信息内容
		for (int i = 0; i < sFList.size(); i++) {
			NsrSfVO sfVO = sFList.get(i);
			row = sheet.createRow(i + 1);
			cell = row.createCell((short) 0);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(sfVO.getSsnd());
			cell = row.createCell((short) 1);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(sfVO.getSz());
			cell = row.createCell((short) 2);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(sfVO.getRkse());
		}

		// 社保费信息
		List<NsrSbfVO> sbfList = NsrSbfBPO.queryList(conn, "ZB_UUID=?",
				sqlParams);
		// 创建sheet
		sheet = wb.createSheet();
		wb.setSheetName(2, "社保信息", (short) 1);
		sheet.createFreezePane(0, 1);// 冻结
		sheet.setColumnWidth((short) 0, (short) (30 * 256));
		sheet.setColumnWidth((short) 1, (short) (30 * 256));
		sheet.setColumnWidth((short) 2, (short) (30 * 256));
		// 社保信息头部
		row = sheet.createRow(0);
		cell = row.createCell((short) 0);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("所属年度");
		cell = row.createCell((short) 1);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("险种");
		cell = row.createCell((short) 2);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("实缴金额");
		// 社保信息内容
		for (int i = 0; i < sbfList.size(); i++) {
			NsrSbfVO sbfVO = sbfList.get(i);
			row = sheet.createRow(i + 1);
			cell = row.createCell((short) 0);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(sbfVO.getSsnd());
			cell = row.createCell((short) 1);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(sbfVO.getXz());
			cell = row.createCell((short) 2);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(sbfVO.getSjje());
		}

		// 财务报表内容
		List<NsrCwbbVO> cwbbList = NsrCwbbBPO.queryList(conn, "ZB_UUID=?",
				sqlParams);
		// 创建sheet
		sheet = wb.createSheet();
		wb.setSheetName(3, "财务报表信息", (short) 1);
		sheet.createFreezePane(0, 1);// 冻结
		sheet.setColumnWidth((short) 0, (short) (20 * 256));
		sheet.setColumnWidth((short) 1, (short) (20 * 256));
		sheet.setColumnWidth((short) 2, (short) (20 * 256));
		sheet.setColumnWidth((short) 3, (short) (20 * 256));
		sheet.setColumnWidth((short) 4, (short) (20 * 256));
		sheet.setColumnWidth((short) 5, (short) (20 * 256));
		sheet.setColumnWidth((short) 6, (short) (20 * 256));
		sheet.setColumnWidth((short) 7, (short) (20 * 256));
		// 财务报表头部
		row = sheet.createRow(0);
		cell = row.createCell((short) 0);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("所属年度");
		cell = row.createCell((short) 1);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("资产总额");
		cell = row.createCell((short) 2);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("负载总额");
		cell = row.createCell((short) 3);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("实收资本");
		cell = row.createCell((short) 4);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("资本公积");
		cell = row.createCell((short) 5);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("主营业务收入");
		cell = row.createCell((short) 6);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("主营业务成本");
		cell = row.createCell((short) 7);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("会计利润");
		// 财务报表内容
		for (int i = 0; i < cwbbList.size(); i++) {
			NsrCwbbVO cwbbVO = cwbbList.get(i);
			row = sheet.createRow(i + 1);
			cell = row.createCell((short) 0);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(cwbbVO.getSsnd());
			cell = row.createCell((short) 1);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(cwbbVO.getZcze());
			cell = row.createCell((short) 2);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(cwbbVO.getFzze());
			cell = row.createCell((short) 3);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(cwbbVO.getSszb());
			cell = row.createCell((short) 4);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(cwbbVO.getZbgj());
			cell = row.createCell((short) 5);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(cwbbVO.getZyywsr());
			cell = row.createCell((short) 6);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(cwbbVO.getZyywcb());
			cell = row.createCell((short) 7);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(cwbbVO.getKjlr());
		}

		// 处罚信息
		List<NsrXzcfVO> xccfList = NsrXzcfBPO.queryList(conn, "ZB_UUID=?",
				sqlParams);
		// 创建sheet
		sheet = wb.createSheet();
		wb.setSheetName(4, "处罚信息", (short) 1);
		sheet.createFreezePane(0, 1);// 冻结
		sheet.setColumnWidth((short) 0, (short) (30 * 256));
		sheet.setColumnWidth((short) 1, (short) (40 * 256));
		sheet.setColumnWidth((short) 2, (short) (30 * 256));
		sheet.setColumnWidth((short) 3, (short) (30 * 256));
		// 处罚信息头部
		row = sheet.createRow(0);
		cell = row.createCell((short) 0);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("处罚决定文号");
		cell = row.createCell((short) 1);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("处罚事由");
		cell = row.createCell((short) 2);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("处罚日期");
		cell = row.createCell((short) 3);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(headStyle);
		cell.setCellValue("处罚金额");
		// 处罚信息内容
		for (int i = 0; i < xccfList.size(); i++) {
			NsrXzcfVO xzcfVO = xccfList.get(i);
			row = sheet.createRow(i + 1);
			cell = row.createCell((short) 0);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(xzcfVO.getCfjdwh());
			cell = row.createCell((short) 1);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(xzcfVO.getCfsyStr());
			cell = row.createCell((short) 2);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(xzcfVO.getCfrq());
			cell = row.createCell((short) 3);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(xzcfVO.getCfje());
		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileVO fileVO = new FileVO();
		fileVO.setFileContent(os.toByteArray());
		fileVO.setFileName("纳税情况_" + jbxxVO.getNsrmc() + ".xls");
		fileVO.setFileType(".xls");
		responseEvent.respMapParam.put(GeneralCons.FILE_VO, fileVO);
		responseEvent.setReponseMesg("下载单个文件成功");

		return responseEvent;
	}

	protected ResponseEvent validateData(RequestEvent req, Connection conn)
			throws Exception {
		return null;
	}

}
