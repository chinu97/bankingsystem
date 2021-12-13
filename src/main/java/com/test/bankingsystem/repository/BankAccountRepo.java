package com.test.bankingsystem.repository;

import com.test.bankingsystem.entity.AccountType;
import com.test.bankingsystem.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BankAccountRepo extends JpaRepository<BankAccount,Integer> {
    @Query("select acc from BankAccount acc where acc.accountType = :accountType")
    BankAccount findByType(@Param("accountType") AccountType accountType);

    @Query("select acc from BankAccount acc where acc.id = :id and  acc.accountNumber= :accountNumber")
    BankAccount findByIdAndAccountNumber(@Param("id") int id,@Param("accountNumber") int accountNumber);
}
