package com.example.work3.model;

import jakarta.persistence.*;

@Entity
@Table(name = "VendorPurchaseItems")
public class VendorPurchaseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Item_Name", nullable = false)
    public String name;

    @Column(name = "Item_Price", nullable = false)
    private float price;

    @Column(name = "Item_Quantity", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "Vendor_Purchase_Id", referencedColumnName = "Id")
    private VendorPurchase vendorPurchase;

    public VendorPurchaseItem() {}

    public VendorPurchaseItem(VendorPurchase vendorPurchase, String itemName, int quantity, float price) {
        this.vendorPurchase = vendorPurchase;
        this.name = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public VendorPurchase getVendorPurchase() {return vendorPurchase;}

    public String getItemName() {return name;}
    public int getQuantity() {return quantity;}
    public float getPrice() {return price;}

    public void setVendorPurchase(VendorPurchase vendorPurchase) { this.vendorPurchase = vendorPurchase; }
    public void setItemName(String name) { this.name = name; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(float price) { this.price = price; }
}