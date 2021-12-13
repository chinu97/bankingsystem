package com.test.bankingsystem.repository;

import com.test.bankingsystem.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Integer> {

    @Query("select admin from Admin admin where admin.username = :username")
    Admin findByUsername(String username);
}
