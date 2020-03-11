package com.itdragon.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.itdragon.entities.Department;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDao {

	private static Map<Integer, Department> departments = null;
	
	static{
		departments = new HashMap<Integer, Department>();
		
		departments.put(101, new Department(101, "D-JAVA", "Java工程师"));
		departments.put(102, new Department(102, "D-Python", "Python工程师"));
		departments.put(103, new Department(103, "D-PHP", "Php工程师"));
		departments.put(104, new Department(104, "D-GO", "Go工程师"));
		departments.put(105, new Department(105, "D-Data", "大数据分析师"));
	}
	
	public Collection<Department> getDepartments(){
		return departments.values();
	}
	
	public Department getDepartmentById(Integer id){
		return departments.get(id);
	}
	
}
