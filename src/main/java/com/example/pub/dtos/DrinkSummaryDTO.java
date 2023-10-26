package com.example.pub.dtos;

public class DrinkSummaryDTO {
    private Long userId;
    private int amount;
    private double price;
    public DrinkSummaryDTO(Long userId, int amount, double price) {
        this.userId = userId;
        this.amount = amount;
        this.price = price;
    }

    public DrinkSummaryDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

