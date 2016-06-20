package gov.jslt.taxcore.taxblh.comm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.core.log.LogWritter;
import com.jxcell.View;

public class EncryptDecryptUtil {

	public static void encrypt(File file, String pwd)
			throws TaxBaseBizException {
		View m_view = new View();
		try {
			FileInputStream inputStream = new FileInputStream(file);
			FileOutputStream outputStream = new FileOutputStream(file);
			m_view.getLock();
			m_view.read(inputStream);
			m_view.write(outputStream, pwd);
			m_view.releaseLock();
			inputStream.close();
			outputStream.close();
		} catch (Exception e) {
			LogWritter.sysError("加密exell出错：" + e.getMessage());
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
	public static void decrypt(File file, String pwd)
			throws TaxBaseBizException {
		View m_view = new View();
		try {
			FileInputStream inputStream = new FileInputStream(file);
			FileOutputStream outputStream = new FileOutputStream(file);
			m_view.getLock();
			m_view.read(inputStream, pwd);
			m_view.write(outputStream);
			m_view.releaseLock();
			inputStream.close();
			outputStream.close();
		} catch (Exception e) {
			throw new TaxBaseBizException(e.getMessage());
		}
	}

}
