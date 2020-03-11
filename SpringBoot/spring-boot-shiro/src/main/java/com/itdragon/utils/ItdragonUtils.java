package com.itdragon.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.itdragon.pojo.User;


/**
 * 工具类
 * @author itdragon
 *
 */
public class ItdragonUtils {
	
	private static final String ALGORITHM_NAME = "MD5";
	private static final Integer HASH_ITERATIONS = 1024;
	
	/**
	 * 加盐加密的策略非常多,根据实际业务来
	 */
	public static void entryptPassword(User user) {
		String salt = UUID.randomUUID().toString();
		String temPassword = user.getPlainPassword();
		Object md5Password = new SimpleHash(ALGORITHM_NAME, temPassword, ByteSource.Util.bytes(salt), HASH_ITERATIONS);
		user.setSalt(salt);
		user.setPassword(md5Password.toString());
	}
	
	public static String getCurrentDateTime() {
		TimeZone zone = TimeZone.getTimeZone("Asia/Shanghai");
		TimeZone.setDefault(zone);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}

}
