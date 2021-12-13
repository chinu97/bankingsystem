package com.test.bankingsystem.service;


import com.test.bankingsystem.entity.Employee;
import com.test.bankingsystem.repository.AdminRepo;
import com.test.bankingsystem.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private EmployeeRepo employeeRepo;



    public String addEmployee(Employee employee) throws Exception {
        try {
            employeeRepo.save(employee);
            return "Employee added successfully";
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

    }

    public List<Employee> getEmployees(){
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(int id) throws Exception {
        try {
            Employee employee = employeeRepo.findById(id).orElse(null);
            if (employee != null){
                return employee;
            }else {
                throw new NullPointerException();
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

    }

    public String deleteEmployee(int id) throws IllegalAccessException {
        if (employeeRepo.findById(id) == null){
            throw new IllegalAccessException("No Employee found with ID " + id);
        }
        employeeRepo.deleteById(id);
        return "Employee deleted successfully "+ id;
    }
    public String deleteAllEmployees(){
        employeeRepo.deleteAll();
        return "All Employees data deleted successfully";
    }
}
