package com.itdragon.common;

import com.itdragon.pojo.User;

/**
 * 工具类
 * @author itdragon
 *
 */
public class ITDragonUtils {
	
	private static final String HASH_ALGORITHM = "SHA-1";
	private static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
//		byte[] salt = Digests.generateSalt(SALT_SIZE);
//		user.setSalt(Encodes.encodeHex(salt));
//
//		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
//		user.setPassword(Encodes.encodeHex(hashPassword));
	}

}
