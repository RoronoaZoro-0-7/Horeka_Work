package com.example.work3.dto;

public class VendorTransactionResponse {
    private String transactionType;
    private double amount;

    public VendorTransactionResponse(String transactionType, double amount) {
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public String getTransactionType() { return transactionType; }
    public double getAmount() { return amount; }
}