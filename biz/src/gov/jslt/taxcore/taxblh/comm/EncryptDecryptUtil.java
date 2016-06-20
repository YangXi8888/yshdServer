package gov.jslt.taxcore.taxblh.comm;

import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.core.log.LogWritter;
import com.jxcell.View;

public class EncryptDecryptUtil {
	/**
	 * 读取excel，并进行加密
	 * 
	 * @param url
	 *            excel文件路径 例：D:\\word.xls
	 * @param pwd
	 *            加密密码
	 */
	public static void encrypt(String url, String pwd) throws TaxBaseBizException {
		View m_view = new View();
		try {
			LogWritter.sysError("加密文件地址为:"+url);
			m_view.read(url);
			m_view.write(url, pwd);
		} catch (Exception e) {
			LogWritter.sysError("加密exell出错："+e.getMessage());
			throw new TaxBaseBizException(e.getMessage());
		}
	}

	/**
	 * excel 解密
	 * 
	 * @return void
	 * @author lifq
	 * @date 2015-3-13 下午02:15:49
	 */
	public static void decrypt(String url, String pwd) throws TaxBaseBizException {
		View m_view = new View();
		try {
			m_view.read(url, pwd);
			m_view.write(url);
		} catch (Exception e) {
			throw new TaxBaseBizException(e.getMessage());
		}
	}
	
	
}
