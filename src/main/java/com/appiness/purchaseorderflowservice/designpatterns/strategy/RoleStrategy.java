package com.appiness.purchaseorderflowservice.designpatterns.strategy;

import com.appiness.purchaseorderflowservice.dtos.PurchaseOrderRequestDTO;
import com.appiness.purchaseorderflowservice.dtos.PurchaseOrderResponseDTO;

import java.util.List;

/**
 * Strategy design pattern - For Open-Closed Principle
 * RoleStrategy interface
 * This interface is implemented by the concrete classes- PublisherStrategy, ApproverStrategy, ReviewerStrategy
 * This interface has a method listPurchaseOrders() which is implemented by the concrete classes
 * This method returns the list of purchase orders based on the role
 */
public interface RoleStrategy {

    List<PurchaseOrderResponseDTO> listPurchaseOrders();

}
