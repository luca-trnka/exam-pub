package com.example.pub.services;

import com.example.pub.dtos.*;
import com.example.pub.models.ErrorMessage;
import com.example.pub.models.Role;
import com.example.pub.models.User;
import com.example.pub.repos.UserRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<UserDTO> getAllUsersDTO() {
        List<User> users = getAllUsers();
        List<UserDTO> userDTOs = new ArrayList<>();

        for (User user : users) {
            UserDTO userDTO = new UserDTO(
                    user.getId(),
                    user.getName(),
                    user.isActive(),
                    user.isAdult(),
                    user.getPocket()
            );
            userDTOs.add(userDTO);
        }

        return userDTOs;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    @Override
    public Optional<User> findUserById (Long id) {
        return Optional.ofNullable(userRepo.findUserById(id));
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return Optional.ofNullable(userRepo.findUserByName(name));
    }

    public UserProfileDTO createUserDTO(User user) {
        Hibernate.initialize(user.getOrders());
        List<OrderDTO> orderDTOs = user.getOrders().stream()
                .map(order -> new OrderDTO(order.getId(), order.getProductName(), order.getAmount(), order.getPrice()))
                .toList();

        return new UserProfileDTO(user.getId(), user.getName(), user.isActive(), user.isAdult(), user.getPocket(), orderDTOs);
    }
    @Override
    public ResponseEntity<?> getUserProfile(Long id) {
        Optional<User> user = Optional.ofNullable(userRepo.findUserById(id));

        if (user.isPresent()) {
            UserProfileDTO foundUser = createUserDTO(user.get());
            return ResponseEntity.status(200).body(foundUser);
        } else {
           return ResponseEntity.status(404).body("This user doesn't exist:(.");
        }
    }
    @Override
    public boolean isUsernameInDatabase(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public ResponseEntity<?> addUser(RegisterRequestDTO newUserDTO) throws AuthenticationException {
        if (newUserDTO.getUsername() == null || newUserDTO.getUsername().isEmpty()) {
            return ResponseEntity.status(400).body(new ErrorMessage("Username is required"));
        }
        if (newUserDTO.getUsername().length() < 4)
            return ResponseEntity.status(400).body(new ErrorMessage("Username must be at least 4 characters long"));
        if (newUserDTO.getPassword() == null || newUserDTO.getPassword().isEmpty()) {
            return ResponseEntity.status(400).body(new ErrorMessage("Password is required"));
        }
        if (newUserDTO.getPassword().length() < 8) {
            return ResponseEntity.status(400).body(new ErrorMessage("Password must be at least 8 characters long"));
        }
        if (isUsernameInDatabase(newUserDTO.getUsername())) {
            return ResponseEntity.status(400).body(new ErrorMessage("Username is already taken"));
        }
        if (newUserDTO == null)
            throw new IllegalArgumentException("Request body is empty");
        User user = new User(newUserDTO.getUsername(), newUserDTO.getName(), newUserDTO.isAdult(), newUserDTO.getPassword());
        System.err.println(user);
        user.setActive(true);
        user.setRole(Role.USER);
        userRepo.save(user);
        return ResponseEntity.status(200).body("User created.");
    }
}
