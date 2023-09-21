package com.example.springauthsecuritysample.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springauthsecuritysample.auth.dto.LoginUserDTO;
import com.example.springauthsecuritysample.auth.dto.RegisterUserDTO;
import com.example.springauthsecuritysample.auth.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterUserDTO registerUserDTO) {
        return authenticationService.register(registerUserDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginUserDTO loginUserDTO) {
        return authenticationService.login(loginUserDTO);
    }

}
