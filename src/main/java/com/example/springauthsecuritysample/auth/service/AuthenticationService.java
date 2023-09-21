package com.example.springauthsecuritysample.auth.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.springauthsecuritysample.auth.dto.LoginUserDTO;
import com.example.springauthsecuritysample.auth.dto.RegisterUserDTO;

@Service
public class AuthenticationService {

    public ResponseEntity<Object> register(RegisterUserDTO registerUserDTO) {
        return ResponseEntity.ok(registerUserDTO);
    }

    public ResponseEntity<Object> login(LoginUserDTO loginUserDTO) {
        return ResponseEntity.ok(loginUserDTO);
    }

}
