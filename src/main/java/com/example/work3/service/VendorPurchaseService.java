package com.example.work3.service;

import com.example.work3.model.Vendor;
import com.example.work3.model.VendorPurchase;
import com.example.work3.model.VendorPurchaseItem;
import com.example.work3.model.VendorTransaction;
import com.example.work3.repository.VendorPurchaseRepository;
import com.example.work3.dto.VendorPurchaseItemResponse;
import com.example.work3.dto.VendorPurchaseResponse;
import com.example.work3.dto.VendorTransactionResponse;
import com.example.work3.repository.VendorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendorPurchaseService {
    private final VendorPurchaseRepository vendorPurchaseRepository;
    private final VendorRepository vendorRepository;

    public VendorPurchaseService(VendorPurchaseRepository vendorPurchaseRepository, VendorRepository vendorRepository) {
        this.vendorPurchaseRepository = vendorPurchaseRepository;
        this.vendorRepository = vendorRepository;
    }

    @Transactional
    public VendorPurchase createPurchase(Long vendorId, LocalDate date, String transactionStatus,
            List<VendorPurchaseItem> purchasedItems,
            List<VendorTransaction> vendorTransactions) {
        Optional<Vendor> vendorOptional = vendorRepository.findById(vendorId);
        if (vendorOptional.isEmpty()) {
            throw new IllegalArgumentException("Vendor not found with ID: " + vendorId);
        }
        Vendor vendor = vendorOptional.get();

        double purchaseCost = 0;
        if (purchasedItems == null || purchasedItems.isEmpty()) {
            throw new IllegalArgumentException("Purchased items cannot be empty");
        }
        for (VendorPurchaseItem item : purchasedItems) {
            if (item.getQuantity() < 0 || item.getPrice() < 0) {
                throw new IllegalArgumentException("Item quantity and price must be positive");
            }
            purchaseCost += item.getQuantity() * item.getPrice();
        }

        double transactionCost = 0;
        if (vendorTransactions == null || vendorTransactions.isEmpty()) {
            throw new IllegalArgumentException("Vendor transactions cannot be empty");
        }
        for (VendorTransaction transaction : vendorTransactions) {
            if (transaction.getAmount() < 0) {
                throw new IllegalArgumentException("Transaction amount must be positive");
            }
            transactionCost += transaction.getAmount();
        }

        if (purchaseCost != transactionCost) {
            throw new IllegalArgumentException("Purchase cost and transaction cost do not match");
        }

        VendorPurchase purchase = new VendorPurchase(vendor, date, transactionStatus, purchaseCost, purchasedItems,
                vendorTransactions);

        for (VendorPurchaseItem item : purchasedItems) {
            item.setVendorPurchase(purchase);
        }

        for (VendorTransaction transaction : vendorTransactions) {
            transaction.setVendorPurchase(purchase);
        }

        return vendorPurchaseRepository.save(purchase);
    }

    public List<VendorPurchaseResponse> getAllPurchases() {
        return vendorPurchaseRepository.findAll().stream().map(purchase -> new VendorPurchaseResponse(
                purchase.getId(),
                purchase.getPurchaseDate(),
                purchase.getVendor().getName(),
                purchase.getPurchasedItems().stream()
                        .map(item -> new VendorPurchaseItemResponse(
                                item.getItemName(),
                                item.getQuantity(),
                                item.getPrice()))
                        .collect(Collectors.toList()),
                purchase.getVendorTransactions().stream()
                        .map(transaction -> new VendorTransactionResponse(
                                transaction.getTransactionType(),
                                transaction.getAmount()))
                        .collect(Collectors.toList()),
                purchase.getTotalAmount(),
                purchase.getTransactionStatus()))
                .collect(Collectors.toList());
    }

    public VendorPurchaseResponse getPurchaseById(Long id) {
        VendorPurchase purchase = vendorPurchaseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Purchase not found with ID: " + id));

        return new VendorPurchaseResponse(
                purchase.getId(),
                purchase.getPurchaseDate(),
                purchase.getVendor().getName(),
                purchase.getPurchasedItems().stream()
                        .map(item -> new VendorPurchaseItemResponse(
                                item.getItemName(),
                                item.getQuantity(),
                                item.getPrice()))
                        .collect(Collectors.toList()),
                purchase.getVendorTransactions().stream()
                        .map(transaction -> new VendorTransactionResponse(
                                transaction.getTransactionType(),
                                transaction.getAmount()))
                        .collect(Collectors.toList()),
                purchase.getTotalAmount(),
                purchase.getTransactionStatus());
    }

}
