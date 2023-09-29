package com.example.subclub.services;

import com.example.subclub.dto.UserCreationDTO;
import com.example.subclub.dto.UserDTO;
import com.example.subclub.dto.UserInfoDTO;
import com.example.subclub.entity.User;
import com.example.subclub.repository.RoleRepository;
import com.example.subclub.repository.UserRepository;
import com.example.subclub.untils.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }
    public UserDTO save(UserCreationDTO userDTO) {
        User user = new User()
                .setName(userDTO.getName())
                .setPassword(passwordEncoder.encode(userDTO.getPassword()))
                .setEmail(userDTO.getEmail())
                .setCreatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .setRoles(List.of(roleRepository.findByName("ROLE_USER").get()));
        userRepository.save(user);

        return new UserDTO().setName(user.getName());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByName(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", username)
        ));

        return castToUserDetails(user);
    }

    public List<UserInfoDTO> findAll() {
        List<UserInfoDTO> userDetailsList = new ArrayList<>();
        userRepository.findAll().forEach(user -> userDetailsList.add(userMapper.toDto(user)));
        return userDetailsList;
    }

    private UserDetails castToUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }
}
