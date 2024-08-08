package com.Backend.java.demo.controller;

import com.Backend.java.demo.Security.JwtTokenProvider;
import com.Backend.java.demo.payload.JwtAuthResponse;
import com.Backend.java.demo.payload.LoginDto;
import com.Backend.java.demo.payload.UsersDto;
import com.Backend.java.demo.service.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AutthController {
    @Autowired
    private UsersServiceImpl UserService;
    @Autowired  
    JwtTokenProvider jwtTokenProvider;
    @PostMapping("/register")
    public ResponseEntity<UsersDto> saveData(@RequestBody UsersDto usersDto){
        return new ResponseEntity<>(UserService.createUser(usersDto), HttpStatus.CREATED);
    }
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> loginUser(@RequestBody LoginDto loginDto){
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword())
        );
        System.out.println(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token= jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthResponse(token));
    }
}
