package com.test.bankingsystem.repository;

import com.test.bankingsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
    @Query("select emp from Employee emp where emp.username = :username")
    Employee findByUsername(@Param("username") String username);
}
