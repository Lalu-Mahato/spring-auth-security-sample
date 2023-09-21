package com.example.springauthsecuritysample.auth.dto;

import com.example.springauthsecuritysample.user.entity.Role;

import lombok.Data;

@Data
public class RegisterResponse {
    private Integer id;
    private String name;
    private String email;
    private Role role;

    public RegisterResponse(Integer id, String name, String email, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
