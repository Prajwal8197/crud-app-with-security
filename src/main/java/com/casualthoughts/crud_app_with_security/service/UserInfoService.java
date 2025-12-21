package com.casualthoughts.crud_app_with_security.service;

import com.casualthoughts.crud_app_with_security.constant.Role;
import com.casualthoughts.crud_app_with_security.dto.RegisterRequest;
import com.casualthoughts.crud_app_with_security.entity.UserInfo;
import com.casualthoughts.crud_app_with_security.repository.UserInfoRepository;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserInfoService {
    private final Logger logger = Logger.getLogger(UserInfoService.class.getName());

    private final UserInfoRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserInfoService(UserInfoRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public String userInfoRegistration(RegisterRequest request){
        String methodName = "userInfoRegistration:service";
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(request.getUsername());
        userInfo.setPassword(passwordEncoder.encode(request.getPassword()));
        userInfo.setRole(Role.ROLE_USER);
        try {
            repository.save(userInfo);
            return "User registered successfully";

        }catch(JpaSystemException e){
            logger.info(e.getMessage());
            return "An error occurred" + methodName;
        }
    }

}
