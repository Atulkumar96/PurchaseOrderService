package com.appiness.purchaseorderflowservice.services;

import com.appiness.purchaseorderflowservice.dtos.PurchaseOrderRequestDTO;
import com.appiness.purchaseorderflowservice.dtos.PurchaseOrderResponseDTO;
import com.appiness.purchaseorderflowservice.dtos.PurchaseOrderUpdateDTO;
import com.appiness.purchaseorderflowservice.dtos.PurchaseOrderUpdateResponseDTO;

import java.util.List;

public interface PurchaseOrderService {
    List<PurchaseOrderResponseDTO> listPurchaseOrders(String role);
    PurchaseOrderResponseDTO createPurchaseOrder(PurchaseOrderRequestDTO dto, String role, String username);
    PurchaseOrderResponseDTO updatePurchaseOrder(PurchaseOrderUpdateDTO dto, String role, String username);
}
