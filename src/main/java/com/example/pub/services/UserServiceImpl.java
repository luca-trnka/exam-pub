package com.example.pub.services;

import com.example.pub.dtos.OrderDTO;
import com.example.pub.dtos.UserDTO;
import com.example.pub.dtos.UserProfileDTO;
import com.example.pub.models.User;
import com.example.pub.repos.UserRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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




}
