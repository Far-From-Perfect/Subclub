package com.example.subclub.services;

import com.example.subclub.dto.*;
import com.example.subclub.untils.JWTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JWTokenUtil jwTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public ResponseEntity<?> createAuthToken(JWTokenRequest request) {
        try {
            UserDetails userDetails = (UserDetails) authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()))
                    .getPrincipal();
            String token = jwTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JWTokenResponse(token));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ApplicationError(HttpStatus.UNAUTHORIZED.value(), "Bad credentials"), HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<?> createNewUser(UserCreationDTO userDTO) {
        if (userService.findByName(userDTO.getName()).isPresent()) {
            return new ResponseEntity<>(new ApplicationError(HttpStatus.BAD_REQUEST.value(), "User is already exist"), HttpStatus.BAD_REQUEST);
        }

        UserDTO user = userService.save(userDTO);
        return ResponseEntity.ok(user);
    }
}
