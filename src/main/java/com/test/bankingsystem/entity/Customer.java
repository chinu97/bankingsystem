package com.test.bankingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Table(name = "customers_list")
@Entity
public class Customer {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String address;
    private String emailId;
    private int aadharNumber;



    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<BankAccount> bankAccounts;

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public Customer(int id, String name, String address, String emailId, int aadharNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.emailId = emailId;
        this.aadharNumber = aadharNumber;
    }

    public Customer() {
    }
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(int aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
