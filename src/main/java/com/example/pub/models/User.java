package com.example.pub.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean isActive;
    private boolean isAdult;
    private double pocket;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    public User(Long id, String name, boolean isActive, boolean isAdult, double pocket, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.isAdult = isAdult;
        this.pocket = pocket;
        this.orders = orders;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public double getPocket() {
        return pocket;
    }

    public void setPocket(double pocket) {
        this.pocket = pocket;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
