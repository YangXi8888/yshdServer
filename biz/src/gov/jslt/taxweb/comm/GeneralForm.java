package gov.jslt.taxweb.comm;

import gov.jslt.taxevent.comm.UploadFile;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class GeneralForm extends ActionForm {

	/**
     *
     */
	private static final long serialVersionUID = 1L;

	private String jsonData;

	private String callback;
	private List<UploadFile> uploadFiles;

	public List<UploadFile> getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(List<UploadFile> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	private class UploadFiles extends ArrayList<UploadFile> {
		private static final long serialVersionUID = 1L;

		public UploadFile get(int index) {
			while (index >= this.size()) {
				this.add(new UploadFile());
			}
			return super.get(index);
		}
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		uploadFiles = new UploadFiles();
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

}
