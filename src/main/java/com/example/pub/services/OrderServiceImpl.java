package com.example.pub.services;

import com.example.pub.dtos.AllSummaryDTO;
import com.example.pub.dtos.DrinkSummaryDTO;
import com.example.pub.dtos.UserSummaryDTO;
import com.example.pub.models.Drink;
import com.example.pub.models.Order;
import com.example.pub.models.User;
import com.example.pub.repos.DrinkRepo;
import com.example.pub.repos.OrderRepo;
import com.example.pub.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public List<AllSummaryDTO> allDrinkInfoSummary() {

        List<AllSummaryDTO> drinkInfoList = new ArrayList<>();
        List<Drink> drinks = drinkRepo.findAll();

        for (Drink drink : drinks) {
            drinkInfoList.add(new AllSummaryDTO(drink));
        }

        List<Order> orders = orderRepo.findAll();

        for (Order order : orders) {
            String productName = order.getProductName();
            int orderAmount = order.getAmount();

            for (AllSummaryDTO drinkInfo : drinkInfoList) {
                if (Objects.equals(productName, drinkInfo.getProductName())) {
                    drinkInfo.addingNewAmount(orderAmount);
                    drinkInfo.sumOfSummaryPrice();
                }
            }
        }
        return drinkInfoList;
    }

    public ResponseEntity<?> findOrdersByProductName(String productName) {
        Optional<Drink> checkedDrink = Optional.ofNullable(drinkRepo.findByProductName(productName));

        if (checkedDrink.isPresent()) {
        List<DrinkSummaryDTO> summaryList = new ArrayList<>();
        List<Order> orders = orderRepo.findAllByProductName(productName);

        for (Order order : orders) {
            User user = userRepo.findById(order.getUser().getId()).orElse(null);
            if (user != null) {
                DrinkSummaryDTO drinkSummary = new DrinkSummaryDTO(user.getId(), order.getAmount(), order.getPrice());
                summaryList.add(drinkSummary);
            }
        }
        return ResponseEntity.status(200).body(summaryList);
    } else {
            return ResponseEntity.status(400).body("There is no such a drink here.");
        }
    }

    @Override
    public ResponseEntity<?> findOrdersByUserName (String name) {
        Optional<User> checkedUser = Optional.ofNullable(userRepo.findUserByName(name));

        if (checkedUser.isPresent()) {
            List<UserSummaryDTO> summaryList = new ArrayList<>();
            List<Order> orders = orderRepo.findAllByUserId(checkedUser.get().getId());

            for (Order order : orders) {
                Drink drink = drinkRepo.findByProductName(order.getProductName());
                if (drink != null) {
                    UserSummaryDTO userSummary = new UserSummaryDTO(drink.getProductName(), order.getPrice());
                    summaryList.add(userSummary);
                }
            }
            return ResponseEntity.status(200).body(summaryList);
        } else {
            return ResponseEntity.status(400).body("There is no such an user here.");
        }
    }
}
