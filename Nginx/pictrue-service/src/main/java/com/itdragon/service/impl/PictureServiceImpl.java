package com.itdragon.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itdragon.service.PictureService;

@Service
@SuppressWarnings({"rawtypes", "unchecked"})
public class PictureServiceImpl implements PictureService {
	
	// 通过 Spring4 的 Value注解，获取配置文件中的属性值
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS; 	// ftp 服务器ip地址
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;		// ftp 服务器port，默认是21
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;	// ftp 服务器用户名
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;	// ftp 服务器密码
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;	// ftp 服务器存储图片的绝对路径
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;	// ftp 服务器外网访问图片路径

	@Override
	public Map uploadPicture(MultipartFile uploadFile) {
		Map resultMap = new HashMap<>();
		try {
			// 1. 取原始文件名
			String oldName = uploadFile.getOriginalFilename();
			
			// 2. ftp 服务器的文件名
			String newName = oldName;
			//图片上传
			boolean result = uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, 
					uploadFile.getInputStream(), FTP_BASE_PATH, newName);
			//返回结果
			if(!result) {
				resultMap.put("error", 1);
				resultMap.put("message", "upload Fail");
				return resultMap;
			}
			resultMap.put("error", 0);
			resultMap.put("url", IMAGE_BASE_URL + "/" + newName);
			return resultMap;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("error", 1);
			resultMap.put("message", "upload Fail");
			return resultMap;
		}
	}
	
	/**
	 * ftp 上传图片方法
	 * @param ip 			ftp 服务器ip地址
	 * @param port 			ftp 服务器port，默认是21
	 * @param account 		ftp 服务器用户名
	 * @param passwd 		ftp 服务器密码
	 * @param inputStream	文件流
	 * @param workingDir 	ftp 服务器存储图片的绝对路径
	 * @param fileName  	上传到ftp 服务器文件名
	 * @throws Exception
	 * 
	 */
	public boolean uploadFile(String ip, Integer port, String account, String passwd,
			InputStream inputStream, String workingDir, String fileName) throws Exception{
		boolean result = false;
		// 1. 创建一个FtpClient对象
		FTPClient ftpClient = new FTPClient();
		try {
			// 2. 创建 ftp 连接
			ftpClient.connect(ip, port);
			// 3. 登录 ftp 服务器
			ftpClient.login(account, passwd);
			int reply = ftpClient.getReplyCode(); // 获取连接ftp 状态返回值
			System.out.println("code : " + reply);
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect(); // 如果返回状态不再 200 ~ 300 则认为连接失败
				return result;
			}
			// 4. 读取本地文件
//			FileInputStream inputStream = new FileInputStream(new File("F:\\hello.png"));
			// 5. 设置上传的路径
			ftpClient.changeWorkingDirectory(workingDir);
			// 6. 修改上传文件的格式为二进制
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			// 7. 服务器存储文件，第一个参数是存储在服务器的文件名，第二个参数是文件流
			if (!ftpClient.storeFile(fileName, inputStream)) {
				return result;
			}
			// 8. 关闭连接
			inputStream.close();
			ftpClient.logout();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// FIXME 听说，项目里面最好少用try catch 捕获异常，这样会导致Spring的事务回滚出问题？？？难道之前写的代码都是假代码！！！
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}

}
