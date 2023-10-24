package com.example.pub.services;

import com.example.pub.dtos.UserDTO;
import com.example.pub.models.User;
import com.example.pub.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

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


}
