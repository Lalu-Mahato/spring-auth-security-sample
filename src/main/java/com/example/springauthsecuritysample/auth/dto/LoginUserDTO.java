package com.example.springauthsecuritysample.auth.dto;

import lombok.Data;

@Data
public class LoginUserDTO {
    private String email;
    private String password;
}
