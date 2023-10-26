package com.example.pub.services;

import com.example.pub.dtos.UserDTO;
import com.example.pub.dtos.UserProfileDTO;
import com.example.pub.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<UserDTO> getAllUsersDTO();

    List<User> getAllUsers();

    Optional<User> findUserById(Long id);
    Optional<User> findUserByName(String name);

    UserProfileDTO createUserDTO(User user);
    ResponseEntity<?> getUserProfile (Long id);

}
