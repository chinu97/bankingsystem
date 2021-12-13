//package com.test.bankingsystem.service;
//
//import com.test.bankingsystem.entity.Admin;
//import com.test.bankingsystem.repository.AdminRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//@Service
//public class AdminDetailsService implements UserDetailsService{
//
//    @Autowired
//    private AdminRepo adminRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Admin admin = adminRepo.findByUsername(username);
//        return new User(admin.getUsername(), admin.getPassword(), new ArrayList<>());
//    }
//
//
//
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        Admin admin = adminRepo.findByUsername(username);
////        return new User(admin.getUsername(),admin.getPassword(),new ArrayList<>());
////    }
//}
