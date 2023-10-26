package com.example.pub.dtos;

import com.example.pub.models.Drink;

public class AllSummaryDTO {

        private String productName;
        private int amount;
        private double unitPrice;
        private double summaryPrice;

        public AllSummaryDTO(Drink drink) {
            this.productName = drink.getProductName();
            this.unitPrice = drink.getPrice();
        }

    public AllSummaryDTO() {
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(double summaryPrice) {
        this.summaryPrice = summaryPrice;
    }

    public void sumOfSummaryPrice() {
            summaryPrice = amount * unitPrice;
        }

        public void addingNewAmount(int incomingAmount) {
            amount += incomingAmount;
        }
    }

