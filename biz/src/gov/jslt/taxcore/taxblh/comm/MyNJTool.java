package gov.jslt.taxcore.taxblh.comm;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.ctp.core.log.LogWritter;

/**
 * 我的南京对接接口工具类
 * 
 * @author gjh
 * 
 */
public class MyNJTool {
	/**
	 * 将byte[]转为各种进制的字符串
	 * 
	 * @param bytes
	 *            byte[]
	 * @param radix
	 *            可以转换进制的范围，从Character.MIN_RADIX 到Character.MAX_RADIX，超出 范围后变为10
	 *            进制
	 * @return 转换后的字符串
	 */
	public static String binary(byte[] bytes, int radix) {
		return new BigInteger(1, bytes).toString(radix);// 这里的1 代表正数
	}

	/**
	 * base 64 encode
	 * 
	 * @param bytes
	 *            待编码的byte[]
	 * @return 编码后的base 64 code
	 */
	public static String base64Encode(byte[] bytes) {
		return Base64.encodeBase64String(bytes);
	}

	/**
	 * base 64 decode
	 * 
	 * @param base64Code
	 *            待解码的base 64 code
	 * @return 解码后的byte[]
	 * @throws Exception
	 */
	public static byte[] base64Decode(String base64Code) throws Exception {
		return isEmpty(base64Code) ? null : Base64.decodeBase64(base64Code);
	}

	/**
	 * AES 加密
	 * 
	 * @param content
	 *            待加密的内容
	 * @param encryptKey
	 *            加密密钥
	 * @return 加密后的byte[]
	 * @throws Exception
	 */
	public static byte[] aesEncryptToBytes(String content, String encryptKey)
			throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(encryptKey.getBytes());
		kgen.init(128, secureRandom);
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey()
				.getEncoded(), "AES"));
		return cipher.doFinal(content.getBytes("utf-8"));
	}

	/**
	 * AES 加密为base 64 code
	 * 
	 * @param content
	 *            待加密的内容
	 * @param encryptKey
	 *            加密密钥
	 * @return 加密后的base 64 code
	 */
	public static String aesEncrypt(String content, String encryptKey) {
		try {
			return Base64.encodeBase64String(aesEncryptToBytes(content,
					encryptKey));
		} catch (Exception e) {
			LogWritter.sysError("请求信息加密出现异常：" + e.getMessage());
			return null;
		}
	}

	/**
	 * AES 解密
	 * 
	 * @param encryptBytes
	 *            待解密的byte[]
	 * @param decryptKey
	 *            解密密钥
	 * @return 解密后的String
	 * @throws Exception
	 */
	public static String aesDecryptByBytes(byte[] encryptBytes,
			String decryptKey) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(decryptKey.getBytes());
		kgen.init(128, secureRandom);
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey()
				.getEncoded(), "AES"));
		byte[] decryptBytes = cipher.doFinal(encryptBytes);
		return new String(decryptBytes, "utf-8");
	}

	/**
	 * 将base 64 code AES 解密
	 * 
	 * @param encryptStr
	 *            待解密的base 64 code
	 * @param decryptKey
	 *            解密密钥
	 * @return 解密后的string
	 */
	public static String aesDecrypt(String encryptStr, String decryptKey) {
		try {
			return isEmpty(encryptStr) ? null : aesDecryptByBytes(
					base64Decode(encryptStr), decryptKey);
		} catch (Exception e) {
			LogWritter.sysError("响应信息解密出现异常异常：" + e.getMessage());
			return null;
		}
	}

	public static boolean isEmpty(String content) {
		if (null == content) {
			return true;
		} else {
			if (content.length() == 0) {
				return true;
			}
		}
		return false;
	}
}
