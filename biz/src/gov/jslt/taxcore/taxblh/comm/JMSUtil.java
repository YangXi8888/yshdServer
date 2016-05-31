package gov.jslt.taxcore.taxblh.comm;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.ctp.core.config.ConfigProperties;
import com.ctp.core.log.LogWritter;

public class JMSUtil {
	private String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
	private String PROVIDER_URL = ConfigProperties.getInstance().getValue(
			"wsbs.jms.provider.url");
	private String JMS_FACTORY = "javax.jms.QueueConnectionFactory";
	private String QUEUE_JNDI = ConfigProperties.getInstance().getValue(
			"wsbs.jms.query.jndi.name");
	private QueueConnectionFactory qconfactory;
	private QueueConnection qcon;
	private QueueSession qsession;
	private Queue queue;
	private TemporaryQueue replyQueue;
	private QueueSender qsender;
	private QueueReceiver receiver;
	private MapMessage mapMsg;

	public JMSUtil() {
		try {
			// 初始化上下文
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
			env.put(Context.PROVIDER_URL, PROVIDER_URL);
			InitialContext ctx = new InitialContext(env);
			// 创建连接工厂
			qconfactory = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);
			// 通过连接工厂穿件连接
			qcon = qconfactory.createQueueConnection();
			// 创建一个session会话
			qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			// 创建一个消息队列
			queue = (Queue) ctx.lookup(QUEUE_JNDI);
			// 启动连接
			qcon.start();
			LogWritter.sysError("JMS服务资源已初始化");
			System.out.println("JMS服务资源已初始化");
		} catch (Exception e) {
			LogWritter.sysError("初始化JMS服务资源异常：" + e.getMessage());
			System.out.println("初始化JMS服务资源异常：" + e.getMessage());
			return;
		}
	}

	/**
	 * 获取临时队列
	 * 
	 * @return
	 */
	public TemporaryQueue getTempQueue() {
		try {
			replyQueue = qsession.createTemporaryQueue();
		} catch (JMSException e) {
			LogWritter.sysError("获取TemporaryQueue异常：" + e.getMessage());
			System.out.println("获取TemporaryQueue异常：" + e.getMessage());
		}
		return replyQueue;
	}

	/**
	 * 获取消息制作者
	 * 
	 * @return
	 */
	public QueueSender getQueueSender() {
		try {
			qsender = qsession.createSender(queue);
		} catch (JMSException e) {
			LogWritter.sysError("获取QueueSender异常：" + e.getMessage());
			System.out.println("获取QueueSender异常：" + e.getMessage());
		}
		return qsender;
	}

	/**
	 * 获取消息接受者
	 * 
	 * @param queue
	 * @return
	 */
	public QueueReceiver getQueueReceiver(Queue queue) {
		try {
			receiver = qsession.createReceiver(queue);
		} catch (JMSException e) {
			LogWritter.sysError("获取QueueReceiver异常：" + e.getMessage());
			System.out.println("获取QueueReceiver异常：" + e.getMessage());
		}
		return receiver;
	}

	/**
	 * 获取Map类型的消息对象
	 * 
	 * @return
	 */
	public MapMessage getMapMsg() {
		try {
			mapMsg = qsession.createMapMessage();
		} catch (JMSException e) {
			LogWritter.sysError("获取MapMessage异常：" + e.getMessage());
			System.out.println("获取MapMessage异常：" + e.getMessage());
		}
		return mapMsg;
	}

	/**
	 * 关闭JMS服务资源
	 * 
	 * @throws JMSException
	 */
	public void close() {
		try {
			qsender.close();
			qsession.close();
			receiver.close();
			qcon.close();
			LogWritter.sysError("JMS服务资源已关闭");
			System.out.println("JMS服务资源已关闭");
		} catch (JMSException e) {
			LogWritter.sysError("关闭JMS服务资源异常：" + e.getMessage());
			System.out.println("关闭JMS服务资源异常：" + e.getMessage());
		}
	}

}
