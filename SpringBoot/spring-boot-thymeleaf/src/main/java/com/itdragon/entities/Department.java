package com.itdragon.entities;

/**
 * 部门实体类
 */
public class Department {

	private Integer id;	// 部门编号
	private String departmentName; // 部门名称
	private String position;	// 部门职位

	public Department() {
	}
	
	public Department(int i, String departmentName, String position) {
		this.id = i;
		this.departmentName = departmentName;
		this.position = position;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return "Department{" +
				"id=" + id +
				", departmentName='" + departmentName + '\'' +
				", position='" + position + '\'' +
				'}';
	}
}
