package gov.jslt.taxweb.comm;

import org.apache.struts.action.ActionForm;

public class Gt3GeneralForm extends ActionForm {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String jsonData;

    private String callback;

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
