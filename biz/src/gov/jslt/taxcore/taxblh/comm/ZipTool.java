package gov.jslt.taxcore.taxblh.comm;

import gov.jslt.taxevent.comm.FileVO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.ctp.core.exception.TaxBaseBizException;
import com.ctp.core.log.LogWritter;

public class ZipTool {
	/**
	 * @param srcFiles
	 *            需压缩的文件路径及文件名
	 * @param desFile
	 *            保存的文件名及路径
	 * @return 如果压缩成功返回true
	 */
	public static void zipCompress(List<FileVO> dataList, String desFile)
			throws TaxBaseBizException {
		try {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(desFile));
			ZipOutputStream zos = new ZipOutputStream(bos);
			FileVO fileVO;
			for (int i = 0; i < dataList.size(); i++) {
				fileVO = dataList.get(i);
				ZipEntry entry = new ZipEntry(fileVO.getFileName());
				zos.putNextEntry(entry);
				BufferedInputStream bis = new BufferedInputStream(
						FileTool.byte2Input(fileVO.getFileContent()));
				byte[] b = new byte[1024];
				while (bis.read(b, 0, 1024) != -1) {
					zos.write(b, 0, 1024);
				}
				bis.close();
				zos.closeEntry();
			}
			zos.flush();
			zos.close();
		} catch (Exception e) {
			LogWritter.sysError(e.getMessage());
			throw new TaxBaseBizException(e.getMessage());
		}
	}

}
