package com.example.subclub.controllers;


import com.example.subclub.dto.JWTokenRequest;
import com.example.subclub.dto.UserCreationDTO;
import com.example.subclub.services.AuthService;
import com.example.subclub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody JWTokenRequest request) {
        return authService.createAuthToken(request);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody UserCreationDTO userDTO) {
        return authService.createNewUser(userDTO);
    }
}
