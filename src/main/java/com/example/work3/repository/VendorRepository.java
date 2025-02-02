package com.example.work3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.work3.model.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Vendor findByVendorName(String vendorName);
}