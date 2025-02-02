package com.example.work3.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="VendorTransaction")
@Data
public class VendorTransaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "transaction_type", nullable = false)
    private String transactionType;

    @Column(name = "amount", nullable = false)
    private float amount;

    @ManyToOne
    @JoinColumn(name = "vendor_purchase_id", nullable = false)
    private VendorPurchase vendorPurchase;

    public VendorTransaction() {}

    public VendorTransaction(String transactionType, float amount, VendorPurchase vendorPurchase) {
        this.transactionType = transactionType;
        setAmount(amount);
        this.vendorPurchase = vendorPurchase;
    }

    public Long getId() {
        return id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.amount = amount;
    }

    public VendorPurchase getVendorPurchase() {
        return vendorPurchase;
    }

    public void setVendorPurchase(VendorPurchase vendorPurchase) {
        this.vendorPurchase = vendorPurchase;
    }
}