package com.example.springauthsecuritysample.auth.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String email;
    private String token;
}
