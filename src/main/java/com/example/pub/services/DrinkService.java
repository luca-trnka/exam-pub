package com.example.pub.services;

import com.example.pub.models.Drink;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DrinkService {

    List<Drink> getAllDrinks();
    Optional<Drink> findDrinkById (Long id);
    Optional<Drink> findDrinkByProductName (String productName);
}
