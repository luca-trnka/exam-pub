package com.example.pub.controllers;

import com.example.pub.dtos.UserDTO;
import com.example.pub.models.Drink;
import com.example.pub.models.Order;
import com.example.pub.services.DrinkService;
import com.example.pub.services.OrderService;
import com.example.pub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    private final UserService userService;
    private final DrinkService drinkService;
    private final OrderService orderService;

    @Autowired
    public MainController(UserService userService, DrinkService drinkService, OrderService orderService) {
        this.userService = userService;
        this.drinkService = drinkService;
        this.orderService = orderService;
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

    @PostMapping("/buy")
    public ResponseEntity<?> canThisUserBuyThis(@RequestParam Long user_id, @RequestParam Long drink_id,
                                                @RequestParam int amount) {
        return orderService.buyDrinkValidation(user_id, drink_id, amount);
    }

    @GetMapping("summary/all")
    public ResponseEntity<?> getAllDrinkInformation() {
        return ResponseEntity.status(200).body(orderService.drinkInfoSummary());
    }

    @GetMapping("/summary/product")
    public ResponseEntity<?> getSummaryOfChosenDrink(@RequestParam("productName") String productName) {
        return orderService.findOrdersByProductName(productName);
    }

}
