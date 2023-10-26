package com.example.pub.services;

import com.example.pub.models.Drink;
import com.example.pub.repos.DrinkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkServiceImpl implements DrinkService {
    private final DrinkRepo drinkRepo;

    @Autowired
    public DrinkServiceImpl(DrinkRepo drinkRepo) {
        this.drinkRepo = drinkRepo;
    }

    @Override
    public List<Drink> getAllDrinks() {
        return drinkRepo.findAll();
    }
}
