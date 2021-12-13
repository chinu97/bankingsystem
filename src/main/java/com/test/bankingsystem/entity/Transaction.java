package com.test.bankingsystem.entity;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue
    private int id;
    private int amount;
    private LocalDate date;
    private LocalTime time;


    @ManyToOne
    @JoinColumn(name = "src_bank_account_id")
    @JsonBackReference
    private BankAccount srcBankAccount;


    @ManyToOne
    @JoinColumn(name = "dest_bank_account_id")
    @JsonBackReference
    private BankAccount destBankAccount;

    public BankAccount getDestBankAccount() {
        return destBankAccount;
    }

    public void setSrcBankAccount(BankAccount srcBankAccount) {
        this.srcBankAccount = srcBankAccount;
    }

    public void setDestBankAccount(BankAccount destBankAccount) {
        this.destBankAccount = destBankAccount;
    }

    public BankAccount getSrcBankAccount() {
        return srcBankAccount;
    }

    public Transaction(int amount) {

        this.amount = amount;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public Transaction() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
