package com.test.bankingsystem.controller;

import com.test.bankingsystem.entity.AccountType;
import com.test.bankingsystem.entity.BankAccount;
import com.test.bankingsystem.entity.Customer;
import com.test.bankingsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/addCustomer")
    public String addCustomer(@RequestBody Customer customer) throws Exception {
        return employeeService.addCustomer(customer);
    }
    @GetMapping("/getCustomer/{id}")
    public Customer getDetailsOfCustomer(@PathVariable int id) throws Exception {
        return employeeService.getDetailsById(id);
    }

    @GetMapping("/getAllCustomers")
    public List<Customer> getAllCustomers(){
        return employeeService.getAllCustomers();
    }
    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable int id) throws Exception {
        return employeeService.deleteCustomer(id);
    }
    @DeleteMapping("/deleteAllCustomers/")
    public String deleteAllCustomers(){
        return employeeService.deleteAllCustomers();
    }
    @PostMapping("/addBankAccount/")
    public String addBankAccount(@RequestParam("id") int customerId,@RequestParam("type") AccountType accountType,@RequestParam("balance") int balance,@RequestParam("accNumber") int accountNumber) throws Exception {
        return employeeService.addBankAccountOfCustomerById(customerId,accountType,balance,accountNumber);
    }
    @GetMapping("/getBalance/{id}/{accountNumber}")
    public int getBalance(@PathVariable int id,@PathVariable int accountNumber) throws Exception {
        return employeeService.getAccountBalance(id,accountNumber);
    }
    @PutMapping("/transferMoney/")
    public String transferMoney(@RequestParam("srcId") int srcId,@RequestParam("srcAcc") int srcAccountNumber,@RequestParam("destId") int destId,@RequestParam("destAcc") int destAccountNumber, @RequestParam("amount") int amount) throws Exception {
        return employeeService.transferMoney(srcId,srcAccountNumber,destId,destAccountNumber,amount);
    }

    @GetMapping("/getAccountStatement/")
    public String getAccountStatement(@RequestParam("id") int id, @RequestParam("accNumber") int accountNumber, @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) throws Exception {
        return employeeService.printAccountStatement(id,accountNumber,startDate,endDate);
    }

    @PutMapping("/updateInterest/")
    public  String updateInterest(@RequestParam int id, @RequestParam int accountNumber) throws Exception {
        return employeeService.calculateInterest(id,accountNumber);
    }




}
