package com.example.pub.services;

import com.example.pub.dtos.DrinkInfoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    ResponseEntity<?> buyDrinkValidation (Long user_id, Long drink_id, int amount);

    List<DrinkInfoDTO> drinkInfoSummary();
}
