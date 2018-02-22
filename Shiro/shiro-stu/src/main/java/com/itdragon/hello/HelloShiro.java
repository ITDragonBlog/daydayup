package com.itdragon.hello;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 代码来自官网demo
 * @author itdragon
 *
 */
public class HelloShiro {
	
	private static final transient Logger log = LoggerFactory.getLogger(HelloShiro.class);

	public static void main(String[] args) {
		// 读取配置文件，初始化SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		// 获取securityManager实例
		SecurityManager securityManager = factory.getInstance();
		// 把securityManager实例绑定到SecurityUtils
		SecurityUtils.setSecurityManager(securityManager);
		// 获取当前执行的用户
		Subject currentUser = SecurityUtils.getSubject();
		// 测试  Session 
        Session session = currentUser.getSession();
        session.setAttribute("sessionKey", "ITDragon");
        String value = (String) session.getAttribute("sessionKey");
        if (value.equals("ITDragon")) {
            log.info("Session ^^^^^^^^^^^^^^^^^^^Retrieved the correct value! [" + value + "]");
        }
        // 测试当前用户是否已经登录
        if (!currentUser.isAuthenticated()) {
        	// 通过账号密码实例化token令牌
        	UsernamePasswordToken token = new UsernamePasswordToken("ITDragon", "123456");
        	try {
        		// 认证身份/登录
        		currentUser.login(token);
        		log.info("登录成功 ^^^^^^^^^^^^^^^^^^^^^User [" + currentUser.getPrincipal() + "] logged in successfully.");
        	} catch (UnknownAccountException uae) {			// 用户没找到异常
                log.info("登录异常 ^^^^^^^^^^^^^^^^^^^^^There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {	// 密码不正确异常
                log.info("登录异常 ^^^^^^^^^^^^^^^^^^^^^Password for account " + token.getPrincipal() + " was incorrect!");
            } catch (LockedAccountException lae) {			// 用户被锁定的异常
                log.info("登录异常 ^^^^^^^^^^^^^^^^^^^^^The account for username " + token.getPrincipal() + " is locked. ");
            } catch (AuthenticationException ae) {			// 认证失败异常，是所有认证异常的父类
            	log.info("登录异常 ^^^^^^^^^^^^^^^^^^^^^Login Error " + token.getPrincipal());
            }
        }
        // 测试用户是否具备某一个行为. 
        if (currentUser.isPermitted("user:delete")) {
            log.info("权限验证 ^^^^^^^^^^^^^^^^^^^^^permit");
        } else {
            log.info("权限验证 ^^^^^^^^^^^^^^^^^^^^^Sorry, you aren't permit!");
        }
        // 退出
        currentUser.logout();
        log.info("用户状态 ^^^^^^^^^^^^^^^^^^^^^User Authenticate is " + currentUser.isAuthenticated());
        System.exit(0);
	}

}