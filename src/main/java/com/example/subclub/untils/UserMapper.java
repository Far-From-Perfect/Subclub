package com.example.subclub.untils;

import com.example.subclub.dto.UserInfoDTO;
import com.example.subclub.entity.User;
import com.example.subclub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper implements IMapper<UserInfoDTO, User> {
    private final UserRepository userRepository;

    @Override
    public UserInfoDTO toDto(User entity) {
        return new UserInfoDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getCreatedAt(),
                entity.getRoles()
        );
    }

    @Override
    public List<UserInfoDTO> toDtoList(List<User> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public User toEntity(UserInfoDTO dto) {
        return userRepository.findById(dto.id()).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", dto.id())));
    }

    @Override
    public List<User> toEntityList(List<UserInfoDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
