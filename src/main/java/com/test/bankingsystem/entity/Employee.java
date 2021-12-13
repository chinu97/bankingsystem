package com.test.bankingsystem.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "employees_list")
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String password;
    private String emailId;
    //private Role role;

    public Employee(int id,String userName, String password, String emailId) {
        this.id = id;
        this.username = userName;
        this.password = password;
        this.emailId = emailId;
       // this.role = Role.EMPLOYEE;
    }

    public Employee() {
    }

//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
