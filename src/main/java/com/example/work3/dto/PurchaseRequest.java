package com.example.work3.dto;

import com.example.work3.model.VendorPurchaseItem;
import com.example.work3.model.VendorTransaction;
import java.util.List;

public class PurchaseRequest {
    private List<VendorPurchaseItem> purchasedItems;
    private List<VendorTransaction> vendorTransactions;

    public List<VendorPurchaseItem> getPurchasedItems() {
        return purchasedItems;
    }

    public List<VendorTransaction> getVendorTransactions() {
        return vendorTransactions;
    }
}