package com.example.work3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.work3.model.VendorPurchaseItem;

@Repository
public interface VendorPurchaseItemRepository extends JpaRepository<VendorPurchaseItem, Long> {
    
}
