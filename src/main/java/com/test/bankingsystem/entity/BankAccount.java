package com.test.bankingsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue
    private int id;
    private int accountNumber;
    private int accountBalance;
    private AccountType accountType;
    //@ElementCollection(targetClass=Integer.class)
    //private List<Integer> transactionIds;

    @OneToMany(mappedBy = "srcBankAccount")
    @JsonManagedReference
    List<Transaction> debitTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "destBankAccount")
    @JsonManagedReference
    List<Transaction> creditTransactions = new ArrayList<>();

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public BankAccount(int accountNumber, int accountBalance, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountType = accountType;
    }

    public BankAccount() {
    }

    public List<Transaction> getDebitTransactions() {
        return debitTransactions;
    }

    public void setDebitTransactions(List<Transaction> debitTransactions) {
        this.debitTransactions = debitTransactions;
    }

    public List<Transaction> getCreditTransactions() {
        return creditTransactions;
    }

    public void setCreditTransactions(List<Transaction> creditTransactions) {
        this.creditTransactions = creditTransactions;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

//    public List<Integer> getTransactionIds() {
//        return transactionIds;
//    }
//
//    public void setTransactionIds(List<Integer> transactionIds) {
//        this.transactionIds = transactionIds;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }
}
