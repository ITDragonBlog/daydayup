package com.itdragon.controller;

import com.itdragon.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 员工管理
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @GetMapping("emps")
    public String findAll(ModelMap map){
        map.put("emps", employeeDao.getAll());
        return "employees/manageEmployees";
    }

}
