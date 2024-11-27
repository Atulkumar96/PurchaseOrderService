package com.appiness.purchaseorderflowservice.repositories;

import com.appiness.purchaseorderflowservice.models.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    List<PurchaseOrder> findByStatus(String status);
    List<PurchaseOrder> findByStatusIn(List<String> statuses);
    List<PurchaseOrder> findByStatusAndCreatedBy(String status, String createdBy);

    @Modifying
    @Query("UPDATE PurchaseOrder po SET po.status = :status, po.updatedBy = :updatedBy WHERE po.id = :recordId")
    int updatePurchaseOrderStatus(int recordId, String status, String updatedBy);

    Optional<PurchaseOrder> findById(int id);


}
