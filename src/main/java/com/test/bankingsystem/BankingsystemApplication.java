package com.test.bankingsystem;

import com.test.bankingsystem.entity.Admin;
import com.test.bankingsystem.entity.Employee;
import com.test.bankingsystem.repository.AdminRepo;
import com.test.bankingsystem.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class BankingsystemApplication {
	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	@PostConstruct
	public void init_admin(){
		adminRepo.deleteAll();
		Admin admin = new Admin("admin","admin","admin@gmail.com");
		adminRepo.save(admin);
	}
//	@PostConstruct
//	public void init_employees(){
//		List<Employee> employees = Stream.of(
//				new Employee(1,"user1","pwd1","user1@gmail.com"),
//				new Employee(2,"user2","pwd2","user2@gmail.com"),
//				new Employee(3,"user3","pwd3","user3@gmail.com")
//		).collect(Collectors.toList());
//		employeeRepo.saveAll(employees);
//	}

	public static void main(String[] args) {
		SpringApplication.run(BankingsystemApplication.class, args);
	}

}
