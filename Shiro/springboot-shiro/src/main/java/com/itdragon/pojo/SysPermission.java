package com.itdragon.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 权限表，决定用户的具体操作
 * @author itdragon
 *
 */
@Table(name = "itdragon_syspermission")
@Entity
public class SysPermission {

	@Id
	@GeneratedValue
	private Integer id;
	private String name; 		// 名称
	private String url; 		// 资源路径
	private String permission; 	// 权限字符串 如：employees:create,employees:update,employees:delete
	private Boolean available = Boolean.FALSE; // 默认不可用
	@ManyToMany
	@JoinTable(name = "SysRolePermission", joinColumns = { @JoinColumn(name = "permissionId") }, inverseJoinColumns = {@JoinColumn(name = "roleId") })
	private List<SysRole> roles;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	public List<SysRole> getRoles() {
		return roles;
	}
	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}
}
