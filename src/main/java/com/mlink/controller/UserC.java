package com.mlink.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mlink.conf.api.RestControllerV1;
import com.mlink.conf.app.JwtUtil;
import com.mlink.entities.User;
import com.mlink.request.UserReq;
import com.mlink.services.impl.CustomUserDetailsService;

@RestControllerV1
@RestController
public class UserC {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userService;

    public UserC(CustomUserDetailsService userService,AuthenticationManager authenticationManager,JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserReq request) {
        User user = userService.registerUser(request);
        return ResponseEntity.ok("Usuario registrado con éxito: " + user.getUsername()); // fix 
    }

    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserReq request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(token);
    }
    
}
