package gov.jslt.taxcore.taxblh.comm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;

import com.ctp.core.exception.TaxBaseBizException;

public class FileTool {
	/**
	 * byte转MB或KB
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytes2kb(long bytes) {
		BigDecimal filesize = new BigDecimal(bytes);
		BigDecimal megabyte = new BigDecimal(1024 * 1024);
		float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP)
				.floatValue();
		if (returnValue > 1)
			return (returnValue + "MB");
		BigDecimal kilobyte = new BigDecimal(1024);
		returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP)
				.floatValue();
		return (returnValue + "KB");
	}

	public static long getFileSize(File file) throws TaxBaseBizException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			return fis.available();
		} catch (Exception e) {
			throw new TaxBaseBizException(e.getMessage());
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
					throw new TaxBaseBizException(e.getMessage());
				}
			}
		}
	}

	public static void main(String args[]) throws TaxBaseBizException {
		System.out.println(getFileSize(new File("F:\\TDDOWNLOAD\\测试文件.txt")));
	}
}
