package gov.jslt.taxevent.comm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.ctp.cssesb.esbevent.ESBRequestEvent;
import com.ctp.cssesb.esbevent.ESBResponseEvent;

public class SerializeUtils {

	/**
	 * <p>
	 * 对ESBRequestEvent类型对象进行序列�?,
	 * </p>
	 * <p>
	 * 并将序列化后的信息保存在以iso-8859-1为编码方式的字符串中
	 * </p>
	 * 
	 * @param request
	 *            - �?要序列化的ESBRequestEvent类型对象
	 * @return 序列化后的iso-8859-1编码的字符串
	 * @throws IOException
	 */
	public String RequestEventToString(ESBRequestEvent request)
			throws IOException {
		return new String(RequestEventToByteArray(request), "iso-8859-1");
	}

	/**
	 * <p>
	 * 对ESBRequestEvent类型对象进行序列�?,
	 * </p>
	 * <p>
	 * 并将序列化后的信息保存在字节数组当中
	 * </p>
	 * 
	 * @param request
	 *            - �?要序列化的ESBRequestEvent类型对象
	 * @return 序列化后的iso-8859-1编码的字符串
	 * @throws IOException
	 */
	public byte[] RequestEventToByteArray(ESBRequestEvent request)
			throws IOException {
		ByteArrayOutputStream memory = new ByteArrayOutputStream();
		// GZIPOutputStream zip = new GZIPOutputStream(memory);
		// ObjectOutputStream out = new ObjectOutputStream(zip);
		ObjectOutputStream out = new ObjectOutputStream(memory);
		out.writeObject(request);
		return memory.toByteArray();
	}

	/**
	 * <p>
	 * 对ESBResponseEvent类型对象进行序列�?,
	 * </p>
	 * <p>
	 * 并将序列化后的信息保存在以iso-8859-1为编码方式的字符串中
	 * </p>
	 * 
	 * @param request
	 *            - �?要序列化的ESBRequestEvent类型对象
	 * @return 序列化后的iso-8859-1编码的字符串
	 * @throws IOException
	 */
	public String ResponseEventToString(ESBResponseEvent response)
			throws IOException {
		return new String(ResponseEventToByteArray(response), "iso-8859-1");
	}

	/**
	 * <p>
	 * 对ESBResponseEvent类型对象进行序列�?,
	 * </p>
	 * <p>
	 * 并将序列化后的信息保存在字节数组当中
	 * </p>
	 * 
	 * @param request
	 *            - �?要序列化的ESBRequestEvent类型对象
	 * @return 序列化后的字节数�?
	 * @throws IOException
	 */
	public byte[] ResponseEventToByteArray(ESBResponseEvent response)
			throws IOException {
		ByteArrayOutputStream memory = new ByteArrayOutputStream();
		// GZIPOutputStream zip = new GZIPOutputStream(memory);
		// ObjectOutputStream out = new ObjectOutputStream(zip);
		ObjectOutputStream out = new ObjectOutputStream(memory);
		out.writeObject(response);
		return memory.toByteArray();
	}

	/**
	 * <p>
	 * 对数据包进行反序列化,
	 * </p>
	 * <p>
	 * 并返回ESBRequestEvent类型对象
	 * </p>
	 * 
	 * @param sjb
	 *            - 载有ESBRequestEvent类型对象信息的iso-8859-1编码的字符串
	 * @return 反序列化后的ESBRequestEvent类型对象
	 * @throws IOException
	 */
	public ESBRequestEvent StringToRequestEvent(String sjb) throws IOException,
			ClassNotFoundException {
		return byteArrayToRequestEvent(sjb.getBytes("iso-8859-1"));
	}

	/**
	 * <p>
	 * 对数据包进行反序列化,
	 * </p>
	 * <p>
	 * 并返回ESBRequestEvent类型对象
	 * </p>
	 * 
	 * @param sjb
	 *            - 载有ESBRequestEvent类型对象信息的字节数�?
	 * @return 反序列化后的ESBRequestEvent类型对象
	 * @throws IOException
	 */
	public ESBRequestEvent byteArrayToRequestEvent(byte[] buffer)
			throws IOException, ClassNotFoundException {
		ByteArrayInputStream memory = new ByteArrayInputStream(buffer);
		// GZIPInputStream zip = new GZIPInputStream(memory);
		// ObjectInputStream in = new ObjectInputStream(zip);
		ObjectInputStream in = new ObjectInputStream(memory);
		return (ESBRequestEvent) in.readObject();
	}

	/**
	 * <p>
	 * 对数据包进行反序列化,
	 * </p>
	 * <p>
	 * 并返回ESBResponseEvent类型对象
	 * </p>
	 * 
	 * @param sjb
	 *            - 载有ESBResponseEvent类型对象信息的iso-8859-1编码的字符串
	 * @return 反序列化后的ESBResponseEvent类型对象
	 * @throws IOException
	 */
	public ESBResponseEvent StringToResponseEvent(String sjb)
			throws IOException, ClassNotFoundException {
		return byteArrayToResponseEvent(sjb.getBytes("iso-8859-1"));
	}

	/**
	 * <p>
	 * 对数据包进行反序列化,
	 * </p>
	 * <p>
	 * 并返回ESBResponseEvent类型对象
	 * </p>
	 * 
	 * @param sjb
	 *            - 载有ESBResponseEvent类型对象信息字节数组
	 * @return 反序列化后的ESBResponseEvent类型对象
	 * @throws IOException
	 */
	public ESBResponseEvent byteArrayToResponseEvent(byte[] buffer)
			throws IOException, ClassNotFoundException {
		ByteArrayInputStream memory = new ByteArrayInputStream(buffer);
		ObjectInputStream in = new ObjectInputStream(memory);
		return (ESBResponseEvent) in.readObject();
	}

}
