package com.itdragon.config.beans;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.itdragon.pojo.SysPermission;
import com.itdragon.pojo.SysRole;
import com.itdragon.pojo.User;
import com.itdragon.service.UserService;

public class ITDragonShiroRealm extends AuthorizingRealm {
	
	private static final transient Logger log = LoggerFactory.getLogger(ITDragonShiroRealm.class);
	
	@Autowired
	private UserService userService;
	
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	log.info("^^^^^^^^^^^^^^^^^^^^ ITDragon 配置当前用户权限");
		User user = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//		for (SysRole role : user.getRoleList()) {
//			authorizationInfo.addRole(role.getRole());
//			for (SysPermission p : role.getPermissions()) {
//				authorizationInfo.addStringPermission(p.getPermission());
//			}
//		}
        authorizationInfo.addStringPermission("");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
    	log.info("^^^^^^^^^^^^^^^^^^^^ ITDragon 认证用户身份信息");
        String account = (String)token.getPrincipal();
        // 实际开发中：这里建议从缓存中读取用户信息
        User userInfo = userService.findByAccount(account);
        if(null == userInfo){
            return null;
        }
        System.out.println("realm account : " + userInfo.getAccount());
        System.out.println("realm password : " + userInfo.getPassword());
        System.out.println("realm salt : " + userInfo.getSalt());
        System.out.println("realm salt2 : " + ByteSource.Util.bytes(userInfo.getSalt()));
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo.getAccount(), userInfo.getPassword(),
				ByteSource.Util.bytes(userInfo.getSalt()), getName());
        System.out.println("authenticationInfo : " + authenticationInfo);
        return authenticationInfo;
    }

}