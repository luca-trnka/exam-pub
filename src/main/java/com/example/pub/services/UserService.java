package com.example.pub.services;

import com.example.pub.dtos.UserDTO;
import com.example.pub.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<UserDTO> getAllUsersDTO();

    List<User> getAllUsers();

}
