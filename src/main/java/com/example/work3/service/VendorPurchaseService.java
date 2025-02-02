package com.example.work3.service;

import com.example.work3.model.Vendor;
import com.example.work3.model.VendorPurchase;
import com.example.work3.model.VendorPurchaseItem;
import com.example.work3.repository.VendorPurchaseRepository;
import com.example.work3.repository.VendorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VendorPurchaseService {
    private final VendorPurchaseRepository vendorPurchaseRepository;
    private final VendorRepository vendorRepository;

    public VendorPurchaseService(VendorPurchaseRepository vendorPurchaseRepository, VendorRepository vendorRepository) {
        this.vendorPurchaseRepository = vendorPurchaseRepository;
        this.vendorRepository = vendorRepository;
    }

    @Transactional
    public VendorPurchase createPurchase(Long vendorId, LocalDate date , List<VendorPurchaseItem> purchasedItems) {
        Optional<Vendor> vendorOptional = vendorRepository.findById(vendorId);
        if (vendorOptional.isEmpty()) {
            throw new IllegalArgumentException("Vendor not found with ID: " + vendorId);
        }
        Vendor vendor = vendorOptional.get();

        if (purchasedItems == null || purchasedItems.isEmpty()) {
            throw new IllegalArgumentException("Purchased items cannot be empty");
        }

        VendorPurchase purchase = new VendorPurchase(vendor, date ,purchasedItems);

        for (VendorPurchaseItem item : purchasedItems) {
            item.setVendorPurchase(purchase);
        }

        return vendorPurchaseRepository.save(purchase);
    }

    public List<VendorPurchase> getAllPurchases() {
        return vendorPurchaseRepository.findAll();
    }

    public VendorPurchase getPurchaseById(Long id) {
        return vendorPurchaseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Purchase not found with ID: " + id));
    }
}