package com.itdragon.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.itdragon.StartApplication;
import com.itdragon.pojo.SysPermission;
import com.itdragon.pojo.SysRole;
import com.itdragon.pojo.User;
import com.itdragon.service.SysPermissionService;
import com.itdragon.service.SysRoleService;
import com.itdragon.service.UserService;
import com.itdragon.utils.ItdragonUtils;

/**
 * @RunWith	它是一个运行器
 * @RunWith(SpringRunner.class) 表示让测试运行于Spring测试环境，不用启动spring容器即可使用Spring环境
 * @SpringBootTest(classes=StartApplication.class)  表示将StartApplication.class纳入到测试环境中，若不加这个则提示bean找不到。
 * 
 * @author itdragon
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=StartApplication.class)
public class SpringbootStudyApplicationTests {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SysPermissionService syspermissionService;
	
	@Autowired
	private SysRoleService sysRoleService;

	@Test	// 测试注册，新增数据
	public void registerUser() {
		for (int i = 0; i < 17; i++) {
			User user = new User();
			user.setAccount("other-user-" + i);
			user.setUserName("OtherStaff-" + i);
			user.setEmail("itdragon@git.com");
			user.setIphone(12349857777L - i + "");
			user.setPlainPassword("12345678");
			user.setPlatform("weixin");
			user.setCreatedDate(ItdragonUtils.getCurrentDateTime());
			user.setUpdatedDate(ItdragonUtils.getCurrentDateTime());
			ItdragonUtils.entryptPassword(user);
			List<SysRole> roleList = new ArrayList<>();
			roleList.add(sysRoleService.getSysRole(3));
			user.setRoleList(roleList);
			System.out.println(user);
			userService.registerUser(user);
		}
	}
	
	@Test
	public void createSysRole() {
		SysRole sysRole = new SysRole();
		sysRole.setRole("staff");
		sysRole.setDescription("普通职工，拥有最基本的系统权限");
		List<User> users = new ArrayList<>();
		users.add(userService.findByAccount("user"));
		sysRole.setUsers(users);
		sysRole.setAvailable(true);
		sysRoleService.saveSysRole(sysRole);
	}
	
	@Test
	public void createSysPermission() {
		SysPermission sysPermission = new SysPermission();
		sysPermission.setName("更新用户信息权限");
		sysPermission.setUrl("/employees");
		sysPermission.setAvailable(true);
		List<SysRole> roles = new ArrayList<>();
		roles.add(sysRoleService.getSysRole(2));
		sysPermission.setRoles(roles);
		sysPermission.setPermission("user:update");
		syspermissionService.saveSysPermission(sysPermission);
	}
	
}
