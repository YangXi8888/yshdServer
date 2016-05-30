package gov.jslt.taxweb;

import com.ctp.core.log.LogWritter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadRuleFilterXML {
    public Map parsePropertyFile(String filePath, String fileName) {
        List list = new ArrayList();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        LogWritter.sysDebug("WSBS ==开始装载" + filePath + ",fileName" + fileName);
        try {
            builder = factory.newDocumentBuilder();
            LogWritter.sysDebug("WSBS ==开始装载" + filePath + ",fileName"
                    + fileName + ","
                    + getClass().getResourceAsStream(filePath + fileName));
            Document doc = builder.parse(getClass().getResourceAsStream(
                    filePath + fileName));
            Element root = doc.getDocumentElement();
            NodeList nodeList = root.getElementsByTagName("url");
            for (int i = 0; i < nodeList.getLength(); i++) {
                LogWritter.sysDebug((nodeList.item(i).getFirstChild()
                        .getNodeValue()));
                list.add((nodeList.item(i).getFirstChild().getNodeValue()));
            }
            LogWritter
                    .sysDebug("WSBS ==装载结束nodeList长度=" + nodeList.getLength());
        } catch (ParserConfigurationException e) {
            LogWritter.sysError("解析文件 " + fileName + " 时发生错误", e);
        } catch (SAXException e) {
            LogWritter.sysError("解析文件 " + fileName + " 时发生错误", e);
        } catch (IOException e) {
            LogWritter.sysError("解析文件 " + fileName + " 时发生错误", e);
        }
        Map map = new HashMap();
        map.put("rulefilter", list);
        return map;
    }
}
