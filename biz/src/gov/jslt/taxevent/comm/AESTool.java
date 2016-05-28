package gov.jslt.taxevent.comm;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESTool {

	/**
	 * 加密
	 * 
	 * @param sSrc
	 * @param sKey
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String sSrc, String sKey) {
		if (sKey == null) {
			return null;
		}
		if (sKey.length() > 16) {
			sKey = sKey.substring(0, 16);
		}
		try {
			byte[] raw = sKey.getBytes("UTF-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(sKey.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(sSrc.getBytes());
			return Base64.encodeBase64String(encrypted);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 解密
	 * 
	 * @param sSrc
	 * @param sKey
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String sSrc, String sKey) {
		try {
			if (sKey == null) {
				return null;
			}
			if (sKey.length() > 16) {
				sKey = sKey.substring(0, 16);
			}
			byte[] raw = sKey.getBytes("UTF-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(sKey.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = Base64.decodeBase64(sSrc);

			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original);
			return originalString;
		} catch (Exception e) {
			return null;
		}
	}
}
