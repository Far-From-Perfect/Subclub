package com.example.subclub.controllers;

import com.example.subclub.dto.UserCreationDTO;
import com.example.subclub.dto.UserDTO;
import com.example.subclub.entity.User;
import com.example.subclub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    public UserDTO save(UserCreationDTO user) {
        return userService.save(user);
    }
}
