package com.example.pub.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    ResponseEntity<?> buyDrinkValidation (Long user_id, Long drink_id, int amount);
}
