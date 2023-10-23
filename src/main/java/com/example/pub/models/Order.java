package com.example.pub.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Order {
    @Id
    private Long id;
    private Long productId;
    private int amount;
    private double price;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Order(Long id, Long productId, int amount, double price, User user) {
        this.id = id;
        this.productId = productId;
        this.amount = amount;
        this.price = price;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
