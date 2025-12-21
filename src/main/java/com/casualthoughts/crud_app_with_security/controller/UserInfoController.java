
package com.casualthoughts.crud_app_with_security.controller;

import com.casualthoughts.crud_app_with_security.constant.Role;
import com.casualthoughts.crud_app_with_security.dto.AuthRequest;
import com.casualthoughts.crud_app_with_security.dto.RegisterRequest;
import com.casualthoughts.crud_app_with_security.entity.UserInfo;
import com.casualthoughts.crud_app_with_security.repository.UserInfoRepository;
import com.casualthoughts.crud_app_with_security.service.JwtService;
import com.casualthoughts.crud_app_with_security.service.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserInfoController {

    private final UserInfoService userInfoService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserInfoController(UserInfoService userInfoService, PasswordEncoder passwordEncoder,
                              AuthenticationManager authenticationManager, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userInfoService=userInfoService;
    }

    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterRequest request) {
        return userInfoService.userInfoRegistration(request);
    }

    @PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}