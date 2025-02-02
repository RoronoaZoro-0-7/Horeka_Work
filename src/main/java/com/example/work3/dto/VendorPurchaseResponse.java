package com.example.work3.dto;

import java.time.LocalDate;
import java.util.List;

public class VendorPurchaseResponse {
    private Long id;
    private LocalDate purchaseDate;
    private String vendorName;
    private List<VendorPurchaseItemResponse> purchasedItems;
    private List<VendorTransactionResponse> vendorTransactions;
    private double totalAmount;
    private String transactionStatus;

    public VendorPurchaseResponse(Long id, LocalDate purchaseDate, String vendorName,
                                  List<VendorPurchaseItemResponse> purchasedItems,
                                  List<VendorTransactionResponse> vendorTransactions,
                                  double totalAmount, String transactionStatus) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.vendorName = vendorName;
        this.purchasedItems = purchasedItems;
        this.vendorTransactions = vendorTransactions;
        this.totalAmount = totalAmount;
        this.transactionStatus = transactionStatus;
    }

    public Long getId() { return id; }
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public String getVendorName() { return vendorName; }
    public List<VendorPurchaseItemResponse> getPurchasedItems() { return purchasedItems; }
    public List<VendorTransactionResponse> getVendorTransactions() { return vendorTransactions; }
    public double getTotalAmount() { return totalAmount; }
    public String getTransactionStatus() { return transactionStatus; }
}