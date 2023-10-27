package com.example.pub.controllers;

import com.example.pub.dtos.RegisterResponseDTO;
import com.example.pub.dtos.RegisterRequestDTO;
import com.example.pub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("auth/register")
    public ResponseEntity<?> registerUser(@RequestBody(required = false) RegisterRequestDTO newUserDTO) throws AuthenticationException {
        return userService.addUser(newUserDTO);
    }


}
