package com.itdragon.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.itdragon.common.pojo.ItdragonResult;
import com.itdragon.common.pojo.User;
import com.itdragon.common.utils.CookieUtils;
import com.itdragon.common.utils.ItdragonUtils;
import com.itdragon.common.utils.JsonUtils;
import com.itdragon.repository.JedisClient;
import com.itdragon.repository.UserRepository;

@Service
@Transactional
@PropertySource(value = "classpath:redis.properties")
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	
	@Value("${SSO_SESSION_EXPIRE}")
	private Integer SSO_SESSION_EXPIRE;
	
    public ItdragonResult registerUser(User user) {
    	// 检查用户名是否注册，一般在前端验证的时候处理，因为注册不存在高并发的情况，这里再加一层查询是不影响性能的
    	if (null != userRepository.findByAccount(user.getAccount())) {
    		return ItdragonResult.build(400, "");
    	}
    	userRepository.save(user);
    	// 注册成功后选择发送邮件激活。现在一般都是短信验证码
    	return ItdragonResult.build(200, "");
    }
    
    public ItdragonResult userLogin(String account, String password,
			HttpServletRequest request, HttpServletResponse response) {
    	// 判断账号密码是否正确
		User user = userRepository.findByAccount(account);
		if (!ItdragonUtils.decryptPassword(user, password)) {
			return ItdragonResult.build(400, "账号名或密码错误");
		}
		// 生成token
		String token = UUID.randomUUID().toString();
		// 清空密码避免泄漏
		user.setPassword(null);
		// 把用户信息写入redis
		jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
		// 设置session的过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
		// 添加写cookie的逻辑，cookie的有效期是关闭浏览器就失效。
		CookieUtils.setCookie(request, response, "USER_TOKEN", token);
		// 返回token
		return ItdragonResult.ok(token);
	}

	public ItdragonResult queryUserByToken(String token) {
		// 根据token从redis中查询用户信息
		String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
		// 判断是否为空
		if (StringUtils.isEmpty(json)) {
			return ItdragonResult.build(400, "此session已经过期，请重新登录");
		}
		// 更新过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
		// 返回用户信息
		return ItdragonResult.ok(JsonUtils.jsonToPojo(json, User.class));
	}
    
}
