package gov.jslt.taxcore.taxblh.comm;

import com.ctp.core.log.LogWritter;
import gt3.xml.exception.Gt3XmlException;
import gt3.xml.tool.rep.Gt3RepXml;
import gt3.xml.tool.req.Gt3ReqXml;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Gt3JMSSender {

    private JMSUtil jms;
    private QueueSender qsender;
    private TemporaryQueue replyQueue;
    private MapMessage mapMsg;

    public Gt3JMSSender() {

    }

    /**
     * 同步发送消息
     *
     * @param reqMap
     * @return
     * @throws JMSException
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> synSend(Map<String, Object> reqMap) {
        Map<String, String> returnMap = new HashMap<String, String>();
        String reqXml = null;
        try {
            Gt3ReqXml gt3ReqXml = new Gt3ReqXml();
            reqMap.put("channelId", "JSDS.NFWB.WSBS");
            reqXml = gt3ReqXml.getReqXml(reqMap);
            LogWritter.sysError("组装金三请求报文成功：" + reqXml);
            System.out.println("组装金三请求报文成功：" + reqXml);
        } catch (Gt3XmlException e1) {
            LogWritter.sysError("组装金三请求报文异常：" + e1.getMessage());
            System.out.println("组装金三请求报文异常：" + e1.getMessage());
            // 具体描述查看T_DM_GY_JMSFSZT
            returnMap.put("ztDm", "01");
            return returnMap;
        }
        try {
            jms = new JMSUtil();
            qsender = jms.getQueueSender();
            replyQueue = jms.getTempQueue();
            mapMsg = jms.getMapMsg();
            mapMsg.setString("reqXml", reqXml);
            if (null != reqMap.get("bizKey")) {
                mapMsg.setString("bizKey", (String) reqMap.get("bizKey"));
            }
            if (null != reqMap.get("djxh")) {
                mapMsg.setString("djxh", (String) reqMap.get("djxh"));
            }
            if (null != reqMap.get("swglm")) {
                mapMsg.setString("swglm", (String) reqMap.get("swglm"));
            }
            //0金三 1大集中
            mapMsg.setString("bwlxDm", "0");
            String correlationId = UUID.randomUUID().toString();
            mapMsg.setJMSCorrelationID(correlationId);
            // 消息返回目的地
            mapMsg.setJMSReplyTo(replyQueue);
            // 消息在队列中的超时时间为10分钟
            qsender.setTimeToLive(1000 * 60 * 10);
            LogWritter.sysError("创建JMS请求成功：" + reqXml);
            System.out.println("创建JMS请求成功：" + reqXml);
        } catch (JMSException e) {
            jms.close();
            LogWritter.sysError("创建JMS请求异常：" + e.getMessage());
            System.out.println("创建JMS请求异常：" + e.getMessage());
            // 具体描述查看T_DM_GY_JMSFSZT
            returnMap.put("ztDm", "02");
            return returnMap;
        }

        LogWritter.sysError("开始同步发送消息");
        System.out.println("开始同步发送消息");
        try {
            // 发送消息
            qsender.send(mapMsg);
        } catch (JMSException e) {
            jms.close();
            LogWritter.sysError("同步发送消息异常：" + e.getMessage());
            System.out.println("同步发送消息异常：" + e.getMessage());
            returnMap.put("ztDm", "03");
            return returnMap;
        }
        try {
            // 超时时间，默认60秒
            int timeOut = 60;
            // 如果有参数中含有超时时间设置，则覆盖默认超时时间;
            if (null != reqMap.get("timeOut")) {
                timeOut = Integer.parseInt((String) reqMap.get("timeOut"));
            }
            // 创建接收者，用于接收返回消息
            QueueReceiver qreceiver = jms.getQueueReceiver(replyQueue);

            // 设置超时时间
            Message tempMsg = (MapMessage) qreceiver.receive(1000 * timeOut);
            if (tempMsg == null) {
                returnMap.put("ztDm", "04");
                return returnMap;
            } else {
                try {
                    Gt3RepXml gt3RepXml = new Gt3RepXml();
                    returnMap = gt3RepXml.getRepMap(((MapMessage) tempMsg)
                            .getString("replyMsg"));
                    LogWritter.sysError("同步发送消息成功,且接收返回消息结束");
                    System.out.println("同步发送消息成功,且接收返回消息结束");
                    returnMap.put("ztDm", "00");
                    return returnMap;
                } catch (Gt3XmlException e) {
                    LogWritter.sysError("解析返回报文异常" + e.getMessage());
                    System.out.println("解析返回报文异常" + e.getMessage());
                    returnMap.put("ztDm", "07");
                    return returnMap;
                }
            }
        } catch (JMSException e) {
            jms.close();
            LogWritter.sysError("同步发送消息成功,但接收返回消息异常：" + e.getMessage());
            System.out.println("同步发送消息成功,但接收返回消息异常：" + e.getMessage());
            returnMap.put("ztDm", "05");
            return returnMap;
        }
    }

    /**
     * 异步发送消息
     *
     * @param reqMap
     * @return
     * @throws JMSException
     */
    public Map<String, String> asynSend(Map<String, Object> reqMap) {
        Map<String, String> returnMap = new HashMap<String, String>();
        String reqXml = null;
        try {
            Gt3ReqXml gt3ReqXml = new Gt3ReqXml();
            reqMap.put("channelId", "JSDS.NFWB.WSBS");
            reqXml = gt3ReqXml.getReqXml(reqMap);
            LogWritter.sysError("组装金三请求报文成功：" + reqXml);
            System.out.println("组装金三请求报文成功：" + reqXml);
        } catch (Gt3XmlException e1) {
            LogWritter.sysError("组装金三请求报文异常：" + e1.getMessage());
            System.out.println("组装金三请求报文异常：" + e1.getMessage());
            // 具体描述查看T_DM_GY_JMSFSZT
            returnMap.put("ztDm", "01");
            return returnMap;
        }

        try {
            jms = new JMSUtil();
            qsender = jms.getQueueSender();

            mapMsg = jms.getMapMsg();
            mapMsg.setString("reqXml", reqXml);
            if (null != reqMap.get("bizKey")) {
                mapMsg.setString("bizKey", (String) reqMap.get("bizKey"));
            }
            if (null != reqMap.get("djxh")) {
                mapMsg.setString("djxh", (String) reqMap.get("djxh"));
            }
            if (null != reqMap.get("swglm")) {
                mapMsg.setString("swglm", (String) reqMap.get("swglm"));
            }
            //0金三 1大集中
            mapMsg.setString("bwlxDm", "0");
            String correlationId = UUID.randomUUID().toString();
            mapMsg.setJMSCorrelationID(correlationId);
            // 消息在队列中的超时时间为10分钟
            qsender.setTimeToLive(1000 * 60 * 10);
            LogWritter.sysError("创建JMS请求成功：" + reqXml);
            System.out.println("创建JMS请求成功：" + reqXml);
        } catch (JMSException e) {
            jms.close();
            LogWritter.sysError("创建JMS请求异常：" + e.getMessage());
            System.out.println("创建JMS请求异常：" + e.getMessage());
            // 具体描述查看T_DM_GY_JMSFSZT
            returnMap.put("ztDm", "02");
            return returnMap;
        }
        LogWritter.sysError("开始异步发送消息");
        System.out.println("开始异步发送消息");
        try {
            // 发送消息
            qsender.send(mapMsg);
            LogWritter.sysError("异步发送消息成功：");
            System.out.println("异步发送消息成功：");
            // 具体描述查看T_DM_GY_JMSFSZT
            returnMap.put("ztDm", "00");
            return returnMap;
        } catch (Exception e) {
            jms.close();
            LogWritter.sysError("异步发送消息异常：" + e.getMessage());
            System.out.println("异步发送消息异常：" + e.getMessage());
            // 具体描述查看T_DM_GY_JMSFSZT
            returnMap.put("ztDm", "06");
            return returnMap;
        }
    }

}
