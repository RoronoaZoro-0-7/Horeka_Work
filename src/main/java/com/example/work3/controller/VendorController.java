package com.example.work3.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.work3.service.VendorService;
import com.example.work3.model.Vendor;
import java.util.List;

@RestController
@RequestMapping("/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @GetMapping
    public List<Vendor> getAllvendors() {
        return vendorService.getAllvendors();
    }

    @GetMapping("/{id}")
    public Vendor getvendorById(@PathVariable Long id) {
        return vendorService.getvendorById(id);
    }

    @PostMapping("/add")
    public void addvendor(@RequestBody Vendor vendor) {
        vendorService.addvendor(vendor);
    }

    @DeleteMapping("/{id}")
    public void deletevendor(@PathVariable Long id) {
        vendorService.deletevendor(id);
    }

    @PutMapping("/update")
    public void updatevendor(@RequestBody Vendor vendor) {
        vendorService.updatevendor(vendor);
    }


    @GetMapping("/id")
    public Long getVendorId(@RequestParam String name) {
        return vendorService.getVendorIdByName(name);
    }
}