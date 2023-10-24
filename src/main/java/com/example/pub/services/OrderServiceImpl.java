package com.example.pub.services;

import com.example.pub.repos.OrderRepo;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;

    public OrderServiceImpl(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }
}
