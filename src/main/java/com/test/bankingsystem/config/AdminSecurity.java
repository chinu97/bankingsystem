//package com.test.bankingsystem.config;
//
//
//import com.test.bankingsystem.repository.AdminRepo;
//import com.test.bankingsystem.service.AdminDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import static java.lang.String.format;
//
//@Configuration
//@EnableWebSecurity
//public class AdminSecurity extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private AdminDetailsService adminDetailsService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(adminDetailsService);
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//
//
//
//
////    private final AdminRepo adminRepo;
//
////    public AdminSecurity(AdminRepo adminRepo) {
////        this.adminRepo = adminRepo;
////    }
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(username -> adminRepo
////                .findByUsername(username)
////                .orElseThrow(
////                        () -> new UsernameNotFoundException(
////                                format("User: %s, not found", username)
////                        )
////                ));
////    }
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        super.configure(http);
////    }
//}
