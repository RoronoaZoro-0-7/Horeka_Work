package com.example.work3.controller;

import com.example.work3.model.VendorPurchase;
import com.example.work3.service.VendorPurchaseService;
import com.example.work3.dto.PurchaseRequest;
import com.example.work3.dto.VendorPurchaseResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vendorPurchases")
public class VendorPurchaseController {
    private final VendorPurchaseService vendorPurchaseService;

    public VendorPurchaseController(VendorPurchaseService vendorPurchaseService) {
        this.vendorPurchaseService = vendorPurchaseService;
    }

    @PostMapping("/add/{vendorId}&{date}&{transactionStatus}")
    public String createPurchase(@PathVariable Long vendorId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @PathVariable String transactionStatus,
            @RequestBody PurchaseRequest request) {
        try {
            vendorPurchaseService.createPurchase(vendorId, date, transactionStatus, request.getPurchasedItems(),
                    request.getVendorTransactions());
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Ok";
    }

    @GetMapping
    public ResponseEntity<List<VendorPurchaseResponse>> getAllPurchases() {
        return ResponseEntity.ok(vendorPurchaseService.getAllPurchases());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorPurchaseResponse> getPurchaseById(@PathVariable Long id) {
        return ResponseEntity.ok(vendorPurchaseService.getPurchaseById(id));
    }

}