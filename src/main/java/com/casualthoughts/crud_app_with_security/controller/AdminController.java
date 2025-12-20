package com.casualthoughts.crud_app_with_security.controller;

import com.casualthoughts.crud_app_with_security.entity.UserInfo;
import com.casualthoughts.crud_app_with_security.repository.UserInfoRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserInfoRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UserInfoRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // Admin creates specialized users (Manager, Guest, etc.)
    @PostMapping("/createUser")
    @PreAuthorize("hasRole('ADMIN')")
    public String createUserByAdmin(@RequestBody UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User created by Admin with role: " + userInfo.getRole();
    }

    // Admin can see all users
    @GetMapping("/all-users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserInfo> getAllUsers() {
        return repository.findAll();
    }
}