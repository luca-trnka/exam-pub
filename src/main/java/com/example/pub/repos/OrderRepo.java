package com.example.pub.repos;

import com.example.pub.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository <Order, Long> {
}
