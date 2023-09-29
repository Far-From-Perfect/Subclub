package com.example.subclub.dto;

import com.example.subclub.entity.Role;

import java.sql.Timestamp;
import java.util.Collection;

public record UserInfoDTO(
        Long id,
        String name,
        String email,
        Timestamp createdAt,
        Collection<Role> roles
) {
}
