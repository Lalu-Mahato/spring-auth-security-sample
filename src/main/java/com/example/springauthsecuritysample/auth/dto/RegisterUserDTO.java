package com.example.springauthsecuritysample.auth.dto;

import com.example.springauthsecuritysample.user.entity.Role;

import lombok.Data;

@Data
public class RegisterUserDTO {
    private String name;
    private String email;
    private String password;
    private Role role;
}
