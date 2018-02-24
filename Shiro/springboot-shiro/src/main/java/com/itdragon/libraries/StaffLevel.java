package com.itdragon.libraries;

public enum StaffLevel {
	
	NOT_LOGIN(-1), 		// 其他
	STAFF(1), 			// 普通员工
	DIRECTOR(10), 		// 主管
	MANAGER(20), 		// 经理
	INSPECTOR(30), 		// 总监
	PRESIDENT(40), 		// 总经理
	ADMINISTRATOR(100); // 超级管理员

	int staffLevel;

	private StaffLevel(int staffLevel) {
		this.staffLevel = staffLevel;
	}

	public int getStaffLevel() {
		return staffLevel;
	}

	public static int getStaffLevelByName(String staffLevelName) {
		if (null != staffLevelName) {
			for (StaffLevel staffLevel : values()) {
				if (staffLevel.name().equals(staffLevelName)) {
					return staffLevel.getStaffLevel();
				}
			}
		}

		return StaffLevel.NOT_LOGIN.getStaffLevel();
	}
}