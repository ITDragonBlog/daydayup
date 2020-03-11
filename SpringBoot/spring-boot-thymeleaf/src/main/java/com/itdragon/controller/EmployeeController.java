package com.itdragon.controller;

import com.itdragon.dao.DepartmentDao;
import com.itdragon.dao.EmployeeDao;
import com.itdragon.entities.Department;
import com.itdragon.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * 员工管理
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    @GetMapping("employees")
    public String findAll(ModelMap map){
        map.put("employees", employeeDao.getAll());
        return "employees/list";
    }

    @GetMapping("employee")
    public String toAddPage(Model model){
        model.addAttribute("departments", departmentDao.getDepartments());
        return "employees/add";
    }

    @PostMapping("employee")
    public String addEmp(Employee employee){
        employeeDao.save(employee);
        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/employees";
    }

    //来到修改页面，查出当前员工，在页面回显
    @GetMapping("/employee/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("employee", employee);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //回到修改页面(add是一个修改添加二合一的页面);
        return "employee/add";
    }

    //员工修改；需要提交员工id；
    @PutMapping("/employee")
    public String updateEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/employees";
    }

    //员工删除
    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.deleteEmployeeById(id);
        return "redirect:/employees";
    }

}
