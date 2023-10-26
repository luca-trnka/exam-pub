package com.example.pub.services;

import com.example.pub.dtos.AllSummaryDTO;
import com.example.pub.dtos.DrinkSummaryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    ResponseEntity<?> buyDrinkValidation (Long user_id, Long drink_id, int amount);

    List<AllSummaryDTO> drinkInfoSummary();
    ResponseEntity<?> findOrdersByProductName(String productName);
}
