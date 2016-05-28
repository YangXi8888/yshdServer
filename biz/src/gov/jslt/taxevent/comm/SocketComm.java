package gov.jslt.taxevent.comm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import com.ctp.cssesb.esbevent.ESBRequestEvent;
import com.ctp.cssesb.esbevent.ESBResponseEvent;

public class SocketComm {
	public boolean doSendRequestMessage(Socket conn, ESBRequestEvent reqEvent)
			throws Exception {

		boolean result = false;
		OutputStream out = null;
		try {

			SerializeUtils util = new SerializeUtils();
			byte[] buffer = util.RequestEventToByteArray(reqEvent);

			out = conn.getOutputStream();

			out.write(getDataLength(buffer.length).getBytes("UTF-8"));

			out.write(buffer, 0, buffer.length);
			out.flush();

			result = true;
		} catch (Exception e1) {

			throw new Exception(e1);
		}

		return result;
	}

	private String getDataLength(int length) {
		String strLength = String.valueOf(length);
		return "00000000".substring(strLength.length()) + strLength;
	}

	public void doReceiveMessageFromServer(Socket conn, int socketTimeOut,
			ESBRequestEvent reqEvent, ESBResponseEvent respEvent)
			throws Exception {
		System.out
				.println("[J_M.2] 接收数据包内容 doReceiveMessageFromServer(SessionID:"
						+ reqEvent.getSessionID() + "): ========");
		InputStream in = null;
		try {
			in = conn.getInputStream();
			byte[] buffer = new byte[4096];
			ByteArrayOutputStream memory = new ByteArrayOutputStream();
			int readSize = in.read(buffer, 0, 8);// 第一次读取数据返回报文头长度
			if (readSize <= 0) {
				System.out.println("ESB_Error [E10310]  ========接收数据长度出现问题!!");
				throw new Exception("E10310");
			}
			int readLength = 0;
			int dataLength = Integer
					.parseInt(new String(buffer, 0, 8, "UTF-8"));// iso-8859-1
			byte[] rdata = new byte[dataLength];
			System.out.println("[S_M.1] ---- 接收预定服务端返回数据,读取返回数据的长度,长度为:"
					+ dataLength + "字节");
			int freeTime = 0;
			System.out.println("[S_M.2] ---- 预定接收服务端返回数据长度确定!开始接收返回数据信息");
			while (readLength < dataLength) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("[S_M.2] ---- 接收客服务端返回信息,线程暂停时出现异常");
					return;
				}
				// 读取网络流内的信息并将读取长度追加至总读取长度
				readSize = in.read(buffer, 0, 4096);
				readLength += readSize;
				freeTime++;
				System.out.println("[S_M.2.1] ---- 接收客服务端返回信息,接收次数" + freeTime
						+ ",接收当次长度" + readSize + ",接收当前总长度" + readLength);
				// 读取长度大于0时将读取到的信息写入内存流
				if (readSize > 0) {
					memory.write(buffer, 0, readSize);
					freeTime = 0;// 重置接收空余时间
				}
				// 判断是否超时
				if (freeTime >= socketTimeOut) {
					if (readLength == dataLength) {
						System.out
								.println("[Ser_socket_server] ---- 最后一次读取完成!!");
						break;
					}
					System.out
							.println("[S_M.2] ---- 响应socket信息超时或者网络错误!SessionID:"
									+ respEvent.getSessionID());
					throw new Exception("E10310");
				}
			}
			if (readLength != dataLength) {
				System.out
						.println("ESB_Error [E10310]  ========接收消息长度不等于预定发送长度!!");
				throw new Exception("E10310");
			}
			System.out.println("[S_M.1] ---- 接收数据完毕,数据长度为:" + memory.size());

			rdata = memory.toByteArray();
			System.out.println("[S_M.1.1] ---- 接收数据完毕,实际接收（rdata）的数据长度为:"
					+ rdata.length);
			SerializeUtils util = new SerializeUtils();
			System.out.println("[S_M.2] ---- 开始反序列化ESBResponseEvent对象...");
			// ESBResponseEvent response =
			// util.byteArrayToResponseEvent(buffer);
			ESBResponseEvent response;
			try {
				response = util.byteArrayToResponseEvent(rdata);
			} catch (Exception ex) {
				System.out
						.println("ESB_Error [E10340]  ========地税socket响应信息接收序列化不成功!!");
				throw new Exception("E10310");
			}
			System.out.println("11:" + response.toString());
			try {
				respCpy(respEvent, response);
			} catch (Exception e) {
				System.out.println("[S_M.4] ---- 拷贝返回信息时发生异常!!");
			}
			System.out.println("[S_M.3] ---- 开始反序列化ESBResponseEvent对象成功!!");

		} catch (IOException e) {
			System.out.println("[S_M.4] ---- 接收服务端返回信息时发生异常!!");
		}
		// catch (ClassNotFoundException e) {
		// BizLogESB.bizError("[S_M.4] ---- 序列化返回信息时发生异常!!",e);
		// }
		finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println("[S_M.5] ---- 关闭输入流时发生异常!!");
				}
			}
		}
	}

	public void respCpy(ESBResponseEvent target, ESBResponseEvent value)
			throws Exception {
		HashMap fields = new HashMap();
		Class respClass = ESBResponseEvent.class;
		// ESBResponseEvent的所有成员获取
		Field[] list = respClass.getDeclaredFields();
		for (int i = list.length - 1; i >= 0; i--) {
			if (!"serialVersionUID".equals(list[i].getName())) {
				fields.put(list[i].getName(), list[i]);
			}

		}
		// ESBResponseEvent的父类所有成员获取
		list = respClass.getSuperclass().getDeclaredFields();
		for (int i = list.length - 1; i >= 0; i--) {
			fields.put(list[i].getName(), list[i]);
		}
		// 遍历所有变量
		Iterator iterator = fields.keySet().iterator();
		while (iterator.hasNext()) {
			String fieldName = (String) iterator.next();
			Field field = (Field) fields.get(fieldName);
			String methodName = Character.toUpperCase(fieldName.charAt(0))
					+ fieldName.substring(1);// 获取首字母大写的成员名，为setter和getter函数的提取做准备
			// 当成员名为ReponseCode和ExceptionParam时不处理直接进入下次循环，因为这两个属性无setter和getter函数
			if (methodName.equals("ReponseCode")
					|| methodName.equals("ExceptionParam")) {
				continue;
			}
			// setter方法所需要的参数类型
			Class[] paramTypes = new Class[1];
			paramTypes[0] = field.getType();

			Method setter = respClass.getMethod("set" + methodName, paramTypes);
			Method getter = respClass.getMethod("get" + methodName, null);

			// setter方法所需要的参数
			Object[] paramValue = new Object[1];
			paramValue[0] = getter.invoke(value, null);// 执行getter得到参数值
			setter.invoke(target, paramValue);// 执行setter方法
		}
	}
}
