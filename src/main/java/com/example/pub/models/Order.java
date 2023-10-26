package com.example.pub.models;

import jakarta.persistence.*;


@Entity
@Table(name = "orders")             //table name "order" can not be used, it creates conflict with mySQL.
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private int amount;
    private double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Order(Long id, String productName, int amount, double price, User user) {
        this.id = id;
        this.productName = productName;
        this.amount = amount;
        this.price = price;
        this.user = user;
    }

    public Order(Drink drink, int amount) {
        this.productName = drink.getProductName();
        this.amount = amount;
        this.price = drink.getPrice() * amount;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void dataForSummary (double price, int amount) {
        price += price*amount;
        amount += amount;
    }
}
