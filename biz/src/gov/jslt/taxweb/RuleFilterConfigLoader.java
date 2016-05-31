package gov.jslt.taxweb;

import com.ctp.core.config.IConfigContext;
import com.ctp.core.config.IConfigContextLoader;
import com.ctp.core.log.LogWritter;

import java.util.HashMap;
import java.util.Map;

public class RuleFilterConfigLoader implements IConfigContextLoader {
    public static final String CODEHELP_FILE_PATH = "/config/rulefilter/";

    private Map configCTXPool = null;

    /**
     * 构造函数
     */
    public RuleFilterConfigLoader() {
        configCTXPool = new HashMap();
    }

    public Map getConfigCTXPool() {
        return configCTXPool;
    }

    public void setConfigCTXPool(Map configCTXPool) {
        this.configCTXPool = configCTXPool;
    }

    public IConfigContext loadConfigData(String[] fileNames) {
        LogWritter.sysDebug("WSBS RuleFilterConfigLoader loadConfigData  "
                + fileNames);
        String fileName = fileNames[0];
        RuleFilterContext configContext = new RuleFilterContext();
        ReadRuleFilterXML fiterXml = new ReadRuleFilterXML();
        configCTXPool = fiterXml
                .parsePropertyFile(CODEHELP_FILE_PATH, fileName);
        configContext.setCTXPool(configCTXPool);
        return configContext;
    }
}
