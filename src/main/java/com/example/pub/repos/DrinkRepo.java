package com.example.pub.repos;

import com.example.pub.models.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepo extends JpaRepository <Drink, Long> {
}
