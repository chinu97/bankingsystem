package com.test.bankingsystem.controller;

import com.test.bankingsystem.entity.Employee;
import com.test.bankingsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin")
    public String welcome(){
        return "welcome chinmay";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@RequestBody Employee employee) throws Exception {
        return adminService.addEmployee(employee);
    }
    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return adminService.getEmployees();
    }
    @GetMapping("/employeeById/{id}")
    public  Employee getEmployee(@PathVariable int id) throws Exception {
        return adminService.getEmployeeById(id);
    }

    @DeleteMapping("/deleteEmployeeById/{id}")
    public String deleteEmployee(@PathVariable int id) throws IllegalAccessException {
        return adminService.deleteEmployee(id);
    }
    @DeleteMapping("/deleteAllEmployees")
    public String deleteAllEmployees(){
        return adminService.deleteAllEmployees();
    }
}
