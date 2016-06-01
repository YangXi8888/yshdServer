package gov.jslt.taxcore.taxblh.yhd.yhd001;

import gov.jslt.taxevent.comm.FileVO;
import gov.jslt.taxevent.comm.GeneralCons;

import java.sql.Connection;
import java.sql.SQLException;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;

public class Yhd001BLH extends BaseBizLogicHandler {

	protected ResponseEvent performTask(RequestEvent req, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		FileVO fileVO = new FileVO();
		fileVO.setFileContent("测试".getBytes());
		fileVO.setFileName("测试.txt");
		fileVO.setFileType(".txt");
		responseEvent.respMapParam.put(GeneralCons.FILE_VO, fileVO);
		return responseEvent;
	}

	protected ResponseEvent validateData(RequestEvent req, Connection conn)
			throws Exception {
		return null;
	}

}
