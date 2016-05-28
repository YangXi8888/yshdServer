package gov.jslt.taxcore.taxblh.comm;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.ctp.core.log.LogWritter;

public class FtpNewUtil {

	/**
	 * 
	 * 上传程序方法
	 * 
	 * 
	 * 
	 */

	public static boolean uploadFile(String url, String username,
			String password, String path, String filename, InputStream input)
			throws Exception {
		// filename:要上传的文件
		// path :上传的路径
		// 初始表示上传失败
		boolean success = false;
		boolean wjj_flag = false;
		// 创建FTPClient对象
		FTPClient ftp = new FTPClient();

		int reply;
		// 连接FTP服务器
		// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
		// ftp.connect(url, port);
		ftp.connect(url);
		// 下面三行代码必须要，而且不能改变编码格式，否则不能正确下载中文文件
		ftp.setControlEncoding("GBK");
		FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
		conf.setServerLanguageCode("zh");
		// 登录ftp
		ftp.login(username, password);
		ftp.enterLocalPassiveMode();
		// 看返回的值是不是230，如果是，表示登陆成功
		reply = ftp.getReplyCode();
		// 以2开头的返回值就会为真
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			LogWritter.sysInfo("===============连接ftp服务器失败===============");
			throw new Exception("连接ftp服务器失败!");
		}
		LogWritter.sysInfo("===============登陆ftp服务器成功===============");
		// 判断目录是否存在
		// 转移到FTP服务器目录
		wjj_flag = ftp.changeWorkingDirectory(path);
		if (false == wjj_flag) {
			if (false == creatDir(ftp, path)) {
				throw new Exception("目录" + path + "创建失败!");
			}
		}
		// 如果缺省该句 传输txt正常 但图片和其他格式的文件传输出现乱码
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		ftp.storeFile(filename, input);
		// 关闭输入流
		input.close();
		// 退出ftp
		ftp.logout();
		// 表示上传成功
		success = true;
		LogWritter.sysInfo("===============ftp文件上传成功===============");
		ftp.disconnect();
		return success;
	}

	/**
	 * 
	 * 删除程序
	 * 
	 * 
	 * 
	 */

	public static boolean deleteFile(String url, String username,
			String password, String path, String filename) throws Exception {
		// filename:要上传的文件
		// path :上传的路径
		// 初始表示上传失败
		boolean success = false;
		boolean wjj_flag = false;
		// 创建FTPClient对象
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			// ftp.connect(url, port);
			ftp.connect(url);
			// 下面三行代码必须要，而且不能改变编码格式，否则不能正确下载中文文件
			ftp.setControlEncoding("GBK");
			FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
			conf.setServerLanguageCode("zh");
			// 登录ftp
			ftp.login(username, password);
			ftp.enterLocalPassiveMode();
			// 看返回的值是不是230，如果是，表示登陆成功
			reply = ftp.getReplyCode();
			// 以2开头的返回值就会为真
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				LogWritter.sysInfo("===============连接ftp服务器失败===============");
				throw new Exception("连接ftp服务器失败!");
			}
			LogWritter.sysInfo("===============登陆ftp服务器成功===============");
			String filename2 = new String(filename.getBytes("GBK"),
					"ISO-8859-1");
			// 转到指定上传目录
			wjj_flag = ftp.changeWorkingDirectory(path);
			if (false == wjj_flag) {
				if (false == creatDir(ftp, path)) {
					throw new Exception("目录" + path + "创建失败!");
				}
			}
			ftp.deleteFile(filename2);
			ftp.logout();
			success = true;
			LogWritter.sysInfo("===============ftp服务器删除文件成功===============");
		} catch (IOException e) {
			LogWritter.sysInfo(e.getMessage());
			throw new Exception(e.getMessage());
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					LogWritter.sysInfo(ioe.getMessage());
					throw new Exception(ioe.getMessage());
				}
			}
		}
		return success;
	}

	// 判断是否有重名方法
	public static boolean isDirExist(String fileName, FTPFile[] fs) {
		for (int i = 0; i < fs.length; i++) {
			FTPFile ff = fs[i];
			if (ff.getName().equals(fileName)) {
				return true; // 如果存在返回 正确信号
			}
		}
		return false; // 如果不存在返回错误信号
	}

	// 根据重名判断的结果 生成新的文件的名称
	public static String changeName(String filename, FTPFile[] fs) {
		int n = 0;
		// 创建一个可变的字符串对象 即StringBuffer对象，把filename值付给该对象
		StringBuffer filename1 = new StringBuffer("");
		filename1 = filename1.append(filename);
		while (isDirExist(filename1.toString(), fs)) {
			n++;
			String a = "[" + n + "]";
			int b = filename1.lastIndexOf(".");// 最后一出现小数点的位置
			int c = filename1.lastIndexOf("[");// 最后一次"["出现的位置
			if (c < 0) {
				c = b;
			}
			StringBuffer name = new StringBuffer(filename1.substring(0, c));// 文件的名字
			StringBuffer suffix = new StringBuffer(filename1.substring(b + 1));// 后缀的名称
			filename1 = name.append(a).append(".").append(suffix);
		}
		return filename1.toString();
	}

	public static boolean creatDir(FTPClient ftp, String path) throws Exception {
		boolean flag = false;
		String[] ml = path.split("/");
		for (int i = 0; i < ml.length; i++) {
			ftp.makeDirectory(ml[i]);
			flag = ftp.changeWorkingDirectory(ml[i]);
			if (false == flag) {
				return false;
			}
		}
		return flag;
	}

}
