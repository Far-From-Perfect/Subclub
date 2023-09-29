package com.example.subclub.controllers;

import com.example.subclub.dto.UserCreationDTO;
import com.example.subclub.dto.UserInfoDTO;
import com.example.subclub.services.AuthService;
import com.example.subclub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<?> save(@RequestBody UserCreationDTO user) {
        return authService.createNewUser(user);
    }

    @GetMapping("/")
    public List<UserInfoDTO> getUsers() {
        return userService.findAll();
    }
}
