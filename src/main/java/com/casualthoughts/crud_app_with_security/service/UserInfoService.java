package com.casualthoughts.crud_app_with_security.service;

import com.casualthoughts.crud_app_with_security.constant.Role;
import com.casualthoughts.crud_app_with_security.dto.GenericApiResponse;
import com.casualthoughts.crud_app_with_security.dto.RegisterRequest;
import com.casualthoughts.crud_app_with_security.entity.UserInfo;
import com.casualthoughts.crud_app_with_security.repository.UserInfoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

import static org.springframework.http.HttpStatusCode.*;

@Service
public class UserInfoService {

    private final UserInfoRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserInfoService(UserInfoRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<GenericApiResponse<?>> userInfoRegistration(RegisterRequest request) {
        String methodName = "userInfoRegistration:service";
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(request.getUsername());
        userInfo.setPassword(passwordEncoder.encode(request.getPassword()));
        userInfo.setRole(Role.ROLE_USER);
        UserInfo savedUserInfo = repository.save(userInfo);
        GenericApiResponse<?> response = new GenericApiResponse<>();
        if (savedUserInfo != null) {
            return  response.success("User registered successfully", 201, null);
        } else {
           return response.error("User registration failed", 400, null);
        }
    }

}
