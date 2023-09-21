package com.example.springauthsecuritysample.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springauthsecuritysample.auth.dto.LoginResponse;
import com.example.springauthsecuritysample.auth.dto.LoginUserDTO;
import com.example.springauthsecuritysample.auth.dto.RegisterResponse;
import com.example.springauthsecuritysample.auth.dto.RegisterUserDTO;
import com.example.springauthsecuritysample.config.JwtService;
import com.example.springauthsecuritysample.user.entity.User;
import com.example.springauthsecuritysample.user.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JwtService jwtService;

    public ResponseEntity<Object> register(RegisterUserDTO registerUserDTO) {
        User user = new User();
        user.setName(registerUserDTO.getName());
        user.setEmail(registerUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        user.setRole(registerUserDTO.getRole());

        userRepository.save(user);
        RegisterResponse response = new RegisterResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<Object> login(LoginUserDTO loginUserDTO) {
        try {
            authenticationManager = applicationContext.getBean(AuthenticationManager.class);

            var usernamePassword = new UsernamePasswordAuthenticationToken(loginUserDTO.getEmail(),
                    loginUserDTO.getPassword());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = jwtService.generateToken((User) auth.getPrincipal());

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setId(((User) auth.getPrincipal()).getId());
            loginResponse.setName(((User) auth.getPrincipal()).getName());
            loginResponse.setEmail(loginUserDTO.getEmail());
            loginResponse.setRole(((User) auth.getPrincipal()).getRole());
            loginResponse.setToken(token);
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            return ResponseEntity.ok("User not found");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

}
