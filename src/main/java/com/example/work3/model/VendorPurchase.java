package com.example.work3.model;

import java.util.*;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

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

    private LocalDate purchaseDate;
    @OneToMany(mappedBy = "vendorPurchase", cascade = CascadeType.ALL)

    private List<VendorPurchaseItem> purchasedItems;

    public VendorPurchase() {
    }

    public VendorPurchase(Vendor vendor,LocalDate date , List<VendorPurchaseItem> purchasedItems) {
        this.vendor = vendor;
        this.purchaseDate = date;
        this.purchasedItems = purchasedItems;
    }

    public Long getId() { return id; }
    public Vendor getVendor() { return vendor; }
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public List<VendorPurchaseItem> getPurchasedItems() { return purchasedItems; }

    public void setVendor(Vendor vendor) { this.vendor = vendor; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
    public void setPurchasedItems(List<VendorPurchaseItem> purchasedItems) { this.purchasedItems = purchasedItems; }

}