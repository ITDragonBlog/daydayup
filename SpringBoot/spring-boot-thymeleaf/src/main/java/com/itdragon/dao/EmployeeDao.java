package com.itdragon.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.itdragon.entities.Department;
import com.itdragon.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {

	private static Map<Integer, Employee> employees = null;
	
	@Autowired
	private DepartmentDao departmentDao;

	static{
		employees = new HashMap<Integer, Employee>();
		employees.put(1001, new Employee(1001, "E-itdragon", "1234567891@163.com", 1, new Department(101, "D-JAVA", "Java工程师")));
		employees.put(1002, new Employee(1002, "E-ITDragon", "1234567892@163.com", 1, new Department(102, "D-Python", "Python工程师")));
		employees.put(1003, new Employee(1003, "E-ITDragonBlog", "1234567893@163.com", 0, new Department(103, "D-PHP", "Php工程师")));
		employees.put(1004, new Employee(1004, "E-CNBlog", "1234567894@163.com", 0, new Department(104, "D-GO", "Go工程师")));
		employees.put(1005, new Employee(1005, "E-CSDN", "1234567895@163.com", 1, new Department(105, "D-Data", "大数据分析师")));
	}
	
	private static Integer initId = 1006;
	
	public void save(Employee employee){
		if(employee.getId() == null){
			employee.setId(initId++);
		}
		
		employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
		employees.put(employee.getId(), employee);
	}
	
	public Collection<Employee> getAll(){
		return employees.values();
	}
	
	public Employee getEmployeeById(Integer id){
		return employees.get(id);
	}
	
	public void deleteEmployeeById(Integer id){
		employees.remove(id);
	}
}
