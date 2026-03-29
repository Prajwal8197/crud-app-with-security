package com.casualthoughts.crud_app_with_security.controller;

import com.casualthoughts.crud_app_with_security.entity.UserInfo;
import com.casualthoughts.crud_app_with_security.repository.UserInfoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Admin", description = "Administrative operations and system management")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
@RequestMapping("/api/admin")
public class AdminController {

    private final UserInfoRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UserInfoRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // Admin creates specialized users (Manager, Guest, etc.)
    @Operation(
            summary = "Create new user",
            description = "Admin can add new users to the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid User data")
    })
    @PostMapping("/createUser")
    @PreAuthorize("hasRole('ADMIN')")
    public String createUserByAdmin(@RequestBody UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User created by Admin with role: " + userInfo.getRole();
    }

    // Admin can see all users
    @Operation(
            summary = "Get all users",
            description = "Retrieve all user accounts. Admin only."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user list")
    })
    @GetMapping("/all-users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserInfo> getAllUsers() {
        return repository.findAll();
    }
}