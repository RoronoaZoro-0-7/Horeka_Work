package com.example.work3.controller;

import com.example.work3.model.VendorPurchase;
import com.example.work3.model.VendorPurchaseItem;
import com.example.work3.service.VendorPurchaseService;

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

    @PostMapping("/add/{vendorId}&{date}")
    public String createPurchase(@PathVariable Long vendorId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestBody List<VendorPurchaseItem> items) {
        try {
            VendorPurchase purchase = vendorPurchaseService.createPurchase(vendorId, date, items);
        } catch (Exception e) {
            return "Something got error";
        }
        return "Ok";
    }

    @GetMapping
    public ResponseEntity<List<VendorPurchase>> getAllPurchases() {
        return ResponseEntity.ok(vendorPurchaseService.getAllPurchases());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorPurchase> getPurchaseById(@PathVariable Long id) {
        return ResponseEntity.ok(vendorPurchaseService.getPurchaseById(id));
    }
}