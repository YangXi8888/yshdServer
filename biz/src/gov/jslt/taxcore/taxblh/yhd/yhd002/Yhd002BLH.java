package gov.jslt.taxcore.taxblh.yhd.yhd002;

import gov.jslt.taxevent.comm.UploadFile;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ctp.core.blh.BaseBizLogicHandler;
import com.ctp.core.event.RequestEvent;
import com.ctp.core.event.ResponseEvent;
import com.ctp.core.exception.TaxBaseBizException;

public class Yhd002BLH extends BaseBizLogicHandler {

	protected ResponseEvent performTask(RequestEvent req, Connection conn)
			throws SQLException, TaxBaseBizException {
		if ("upLoadFile".equals(req.getDealMethod())) {
			return upLoadFile(req, conn);
		}
		return null;
	}

	protected ResponseEvent upLoadFile(RequestEvent req, Connection conn)
			throws SQLException, TaxBaseBizException {
		ResponseEvent responseEvent = new ResponseEvent();
		List<UploadFile> uploadFiles = (List<UploadFile>) req.reqMapParam
				.get("UploadFiles");
		System.out.println(uploadFiles.get(0).getFile().getFileName());
		System.out.println(uploadFiles.get(0).getFile().getFileSize());
		return responseEvent;
	}

	protected ResponseEvent validateData(RequestEvent req, Connection conn)
			throws Exception {
		return null;
	}

}
