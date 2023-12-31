package com.example.pub.dtos;

public class UserSummaryDTO {
    private String productName;
    private double price;

    public UserSummaryDTO(String productName, double price) {
        this.productName = productName;
        this.price = price;
    }

    public UserSummaryDTO() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

