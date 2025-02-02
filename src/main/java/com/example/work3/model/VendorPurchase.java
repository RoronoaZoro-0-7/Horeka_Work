package com.example.work3.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "VendorPurchase")
@Data
public class VendorPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Vendor_Id", nullable = false)
    private Vendor vendor;

    @Column(name = "Purchase_Date", nullable = false)
    private LocalDate purchaseDate;

    @OneToMany(mappedBy = "vendorPurchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VendorPurchaseItem> purchasedItems;

    @OneToMany(mappedBy = "vendorPurchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VendorTransaction> vendorTransactions;

    @Column(name = "Total_Amount", nullable = false)
    private double totalAmount;

    @Column(name = "Transaction_Status", nullable = false)
    private String transactionStatus;

    public VendorPurchase() {}

    public VendorPurchase(Vendor vendor, LocalDate date, String transactionStatus ,double total, List<VendorPurchaseItem> purchasedItems, List<VendorTransaction> vendorTransactions) {
        this.vendor = vendor;
        this.purchaseDate = date;
        this.transactionStatus = transactionStatus;
        this.purchasedItems = purchasedItems;
        this.totalAmount = total;
        this.vendorTransactions = vendorTransactions;
    }

    public Long getId() { return id; }
    public Vendor getVendor() { return vendor; }
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public List<VendorPurchaseItem> getPurchasedItems() { return purchasedItems; }
    public List<VendorTransaction> getVendorTransactions() { return vendorTransactions; }
    public double getTotalAmount() { return totalAmount; }
    public String getTransactionStatus() { return transactionStatus; }

    public void setVendor(Vendor vendor) { this.vendor = vendor; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
    public void setPurchasedItems(List<VendorPurchaseItem> purchasedItems) { this.purchasedItems = purchasedItems; }
    public void setVendorTransactions(List<VendorTransaction> vendorTransactions) { this.vendorTransactions = vendorTransactions; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public void setTransactionStatus(String transactionStatus) { this.transactionStatus = transactionStatus; }
}