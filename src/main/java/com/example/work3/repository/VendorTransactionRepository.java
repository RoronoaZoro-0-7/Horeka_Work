package com.example.work3.repository;

import com.example.work3.model.VendorTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VendorTransactionRepository extends JpaRepository<VendorTransaction, Long> {
    List<VendorTransaction> findByVendorPurchaseId(Long vendorPurchaseId);
}