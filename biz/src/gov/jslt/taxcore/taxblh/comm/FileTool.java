package gov.jslt.taxcore.taxblh.comm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.apache.struts.upload.FormFile;

import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.core.log.LogWritter;

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
			LogWritter.sysError(e.getMessage());
			throw new TaxBaseBizException(e.getMessage());
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
					LogWritter.sysError(e.getMessage());
					throw new TaxBaseBizException(e.getMessage());
				}
			}
		}
	}

	public static final InputStream byte2Input(byte[] buf) {
		return new ByteArrayInputStream(buf);
	}

	public static final byte[] input2byte(InputStream inputStream)
			throws TaxBaseBizException {
		try {
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
			byte[] buff = new byte[100];
			int rc = 0;
			while ((rc = inputStream.read(buff, 0, 100)) > 0) {
				swapStream.write(buff, 0, rc);
			}
			byte[] in2b = swapStream.toByteArray();
			return in2b;
		} catch (Exception e) {
			LogWritter.sysError(e.getMessage());
			throw new TaxBaseBizException(e.getMessage());
		}
	}

	public static byte[] getFileByte(String fileName) throws TaxBaseBizException {
		try {
			InputStream inputStream = new FileInputStream(new File(
					fileName));
			return FileTool.input2byte(inputStream);
		} catch (Exception e) {
			LogWritter.sysError(e.getMessage());
			throw new TaxBaseBizException(e.getMessage());
		}

	}

	public static byte[] getFileContent(FormFile formFile)throws TaxBaseBizException {
		try {
			
			return formFile.getFileData();
		} catch (Exception e) {
			LogWritter.sysError(e.getMessage());
			throw new TaxBaseBizException(e.getMessage());
		}
	}
}
