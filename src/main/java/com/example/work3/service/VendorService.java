package com.example.work3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.work3.model.Vendor;
import com.example.work3.repository.VendorRepository;
import java.util.List;

@Service
public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;
    public List<Vendor> getAllvendors() {
        return vendorRepository.findAll();
    }
    public Vendor getvendorById(Long id) {
        return vendorRepository.findById(id).orElse(null);
    }
    public void addvendor(Vendor vendor) {
        vendorRepository.save(vendor);
    }
    public void deletevendor(Long id) {
        vendorRepository.deleteById(id);   
    }
    public void updatevendor(Vendor vendor) {
        vendorRepository.save(vendor);
    }
    public Long getVendorIdByName(String vendorName) {
        Vendor vendor = vendorRepository.findByVendorName(vendorName);
        return (vendor != null) ? vendor.getId() : null;
    }
}