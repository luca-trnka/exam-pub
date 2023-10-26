package com.example.pub.controllers;

import com.example.pub.dtos.UserDTO;
import com.example.pub.models.Drink;
import com.example.pub.services.DrinkService;
import com.example.pub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    private final UserService userService;
    private final DrinkService drinkService;


    @Autowired
    public MainController(UserService userService, DrinkService drinkService) {
        this.userService = userService;
        this.drinkService = drinkService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsersDTO());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserProfile(@PathVariable Long id) {
        return userService.getUserProfile(id);
    }

    @GetMapping("/drink-menu")
    public ResponseEntity<List<Drink>> getDrinkMenu() {
        return ResponseEntity.status(200).body(drinkService.getAllDrinks());
    }
}
