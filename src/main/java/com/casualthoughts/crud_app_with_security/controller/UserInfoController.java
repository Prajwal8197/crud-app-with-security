
package com.casualthoughts.crud_app_with_security.controller;

import com.casualthoughts.crud_app_with_security.constant.Role;
import com.casualthoughts.crud_app_with_security.dto.AuthRequest;
import com.casualthoughts.crud_app_with_security.dto.GenericApiResponse;
import com.casualthoughts.crud_app_with_security.dto.RegisterRequest;
import com.casualthoughts.crud_app_with_security.entity.UserInfo;
import com.casualthoughts.crud_app_with_security.repository.UserInfoRepository;
import com.casualthoughts.crud_app_with_security.service.JwtService;
import com.casualthoughts.crud_app_with_security.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints for User Registration and Login")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
public class UserInfoController {

    private final UserInfoService userInfoService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserInfoController(UserInfoService userInfoService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userInfoService=userInfoService;
    }

    @Operation(
            summary = "Register new user",
            description = "Users can register using this public enpoint"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid User data")
    })

    @PostMapping("/register")
    public ResponseEntity<GenericApiResponse<?>> register(@RequestBody @Valid RegisterRequest request) {
        return userInfoService.userInfoRegistration(request);
    }

    @Operation(
            summary = "Login with credentials to get JWT token",
            description = "Users can login using this public enpoint"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successful. JWT token returned."),
            @ApiResponse(responseCode = "400", description = "Invalid User request !")
    })

    @PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody @Valid AuthRequest authRequest) {
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