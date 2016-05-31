package gov.jslt.taxcore.taxblh.comm;

import java.net.InetAddress;

/**
 * 
 * @see UUIDHexGenerator
 * @author yangxi
 */

public abstract class UUIDGenerator {

	private static final int IP;
	static {
		int ipadd;
		try {
			ipadd = toInt(InetAddress.getLocalHost().getAddress());
		} catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}
	public static short counter = (short) 0;
	public static final int JVM = (int) (System.currentTimeMillis() >>> 8);

	public UUIDGenerator() {
	}

	public static int getJVM() {
		return JVM;
	}

	public static short getCount() {
		synchronized (UUIDGenerator.class) {
			if (counter < 0)
				counter = 0;
			return counter++;
		}
	}

	/**
	 * Unique in a local network
	 */
	public static int getIP() {
		return IP;
	}

	public static short getHiTime() {
		return (short) (System.currentTimeMillis() >>> 32);
	}

	public static int getLoTime() {
		return (int) System.currentTimeMillis();
	}

	public static String getUUID() {
		String str = new StringBuffer(36).append(format(getIP())).append("")
				.append(format(getJVM())).append("")
				.append(format(getHiTime())).append("").append(
						format(getLoTime())).append("").append(
						format(getCount())).toString();
		return str.toUpperCase();
	}

	public static String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	public static String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

	public static int toInt(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}

	public static void main(String args[]) {
		for (int i = 0; i < 100; i++) {
			System.out.println(getUUID());
		}
	}

}
