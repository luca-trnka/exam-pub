package com.example.pub.services;

import com.example.pub.models.Drink;
import com.example.pub.models.User;
import com.example.pub.repos.DrinkRepo;
import com.example.pub.repos.OrderRepo;
import com.example.pub.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final DrinkRepo drinkRepo;
    private final UserRepo userRepo;
    private final OrderRepo orderRepo;

    @Autowired
    public OrderServiceImpl(DrinkRepo drinkRepo, UserRepo userRepo, OrderRepo orderRepo) {
        this.drinkRepo = drinkRepo;
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
    }


    @Override
    public ResponseEntity<?> buyDrinkValidation(Long user_id, Long drink_id, int amount) {

        Optional<User> checkedUser = Optional.ofNullable(userRepo.findUserById(user_id));
        Optional<Drink> checkedDrink = Optional.ofNullable(drinkRepo.findDrinkById(drink_id));

        //Validation of request
        if (user_id == null || drink_id == null) {
            return ResponseEntity.status(400).body("All fields are required.");
        } else if (checkedUser.isEmpty() || checkedDrink.isEmpty()) {
            return ResponseEntity.status(400).body("User or drink is not found.");
        } else if (amount < 1) {
            return ResponseEntity.status(400).body("You have to buy at least 1.");
        } else {

            //Validation of age and pocket
            if (checkedUser.get().getPocket() < (checkedDrink.get().getPrice()) * amount) {
                return ResponseEntity.status(403).body("You don't have enough of money in your pocket :(.");
            }
            if (checkedDrink.get().isForAdult() && !checkedUser.get().isAdult()) {
                return ResponseEntity.status(403).body("You aren't able to drink this drink, wait until you will be 18 :).");
            }
            return ResponseEntity.status(200).body("Let's Get Ready to Rumble :)!");
        }

    }
}
