package com.example.springauthsecuritysample.auth.dto;

import com.example.springauthsecuritysample.user.entity.Role;

import lombok.Data;

@Data
public class LoginResponse {
    private Integer id;
    private String name;
    private String email;
    private Role role;
    private String token;
}
