package com.appiness.purchaseorderflowservice.designpatterns.strategy;

import com.appiness.purchaseorderflowservice.dtos.PurchaseOrderRequestDTO;
import com.appiness.purchaseorderflowservice.dtos.PurchaseOrderResponseDTO;
import com.appiness.purchaseorderflowservice.models.PurchaseOrder;
import com.appiness.purchaseorderflowservice.repositories.PurchaseOrderRepository;
import com.appiness.purchaseorderflowservice.utils.PurchaseOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApproverStrategy implements RoleStrategy {
    @Autowired
    private PurchaseOrderRepository repository;

    @Override
    public List<PurchaseOrderResponseDTO> listPurchaseOrders() {
        List<PurchaseOrder> purchaseOrders = repository.findByStatus("reviewed");
        return purchaseOrders.stream().map(PurchaseOrderMapper::convertToDTO).collect(Collectors.toList());
//        return new ArrayList<>();
    }
}
