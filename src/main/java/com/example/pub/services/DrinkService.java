package com.example.pub.services;

import com.example.pub.models.Drink;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DrinkService {

    List<Drink> getAllDrinks();
}
