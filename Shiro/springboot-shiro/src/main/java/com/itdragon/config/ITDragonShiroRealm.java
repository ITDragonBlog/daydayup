package com.itdragon.config;

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

/**
 * 自定义安全数据Realm，重点
 * @author itdragon
 *
 */
public class ITDragonShiroRealm extends AuthorizingRealm {
	
	private static final transient Logger log = LoggerFactory.getLogger(ITDragonShiroRealm.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 授权
	 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	log.info("^^^^^^^^^^^^^^^^^^^^ ITDragon 配置当前用户权限");
    	String username = (String) principals.getPrimaryPrincipal();
    	User user = userService.findByAccount(username);
    	if(null == user){
            return null;
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		for (SysRole role : user.getRoleList()) {
			authorizationInfo.addRole(role.getRole());	// 添加角色
			for (SysPermission permission : role.getPermissions()) {
				authorizationInfo.addStringPermission(permission.getPermission());	// 添加具体权限
			}
		}
        return authorizationInfo;
    }

    /**
     * 身份认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
    	log.info("^^^^^^^^^^^^^^^^^^^^ ITDragon 认证用户身份信息");
		String username = (String) token.getPrincipal(); // 获取用户登录账号
		User userInfo = userService.findByAccount(username); // 通过账号查加密后的密码和盐，这里一般从缓存读取
        if(null == userInfo){
            return null;
        }
		// 1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象. 
		Object principal = username;
		// 2). credentials: 加密后的密码. 
		Object credentials = userInfo.getPassword();
		// 3). realmName: 当前 realm 对象的唯一名字. 调用父类的 getName() 方法
		String realmName = getName();
		// 4). credentialsSalt: 盐值. 注意类型是ByteSource
		ByteSource credentialsSalt = ByteSource.Util.bytes(userInfo.getSalt());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		return info;
    }

}