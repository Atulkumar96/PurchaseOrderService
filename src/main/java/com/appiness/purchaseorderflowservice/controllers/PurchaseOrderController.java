package com.appiness.purchaseorderflowservice.controllers;

import com.appiness.purchaseorderflowservice.dtos.PurchaseOrderRequestDTO;
import com.appiness.purchaseorderflowservice.dtos.PurchaseOrderResponseDTO;
import com.appiness.purchaseorderflowservice.dtos.PurchaseOrderUpdateDTO;
import com.appiness.purchaseorderflowservice.dtos.PurchaseOrderUpdateResponseDTO;
import com.appiness.purchaseorderflowservice.exceptions.UnauthorizedAccessException;
import com.appiness.purchaseorderflowservice.services.AuthService;
import com.appiness.purchaseorderflowservice.services.PurchaseOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;
    private final AuthService authService;

    PurchaseOrderController(PurchaseOrderService purchaseOrderService, AuthService authService) {
        this.purchaseOrderService = purchaseOrderService;
        this.authService = authService;
    }

    @GetMapping("/list")
    public List<PurchaseOrderResponseDTO> listPurchaseOrders(
            @RequestHeader String role, @RequestHeader String username) {
            return purchaseOrderService.listPurchaseOrders(role);
    }

    @PostMapping("/txn")
    public ResponseEntity<PurchaseOrderResponseDTO> createPurchaseOrder(@RequestHeader String role,
                                                                        @RequestHeader String username,
                                                                        @RequestBody PurchaseOrderRequestDTO purchaseOrderRequestDTO) {

        // Check if user has the access to create a purchase order
        StringBuilder action = new StringBuilder("createPurchaseOrder");
        if (authService.userHasAccess(role, username, action)) {
            throw new UnauthorizedAccessException("You do not have permission to create a purchase order");
        }

        // Create the purchase order
        PurchaseOrderResponseDTO createdPurchaseOrder = purchaseOrderService.createPurchaseOrder(purchaseOrderRequestDTO, role, username);
        return ResponseEntity.ok(createdPurchaseOrder);
    }

    @PatchMapping("/txn")
    public ResponseEntity<PurchaseOrderResponseDTO>  updatePurchaseOrder(@RequestHeader String role,
                                                              @RequestHeader String username,
                                                              @RequestBody PurchaseOrderUpdateDTO purchaseOrderUpdateDTO) {

        // Check if user has the access to accept or reject the purchase order
        StringBuilder action = new StringBuilder(purchaseOrderUpdateDTO.getAction());
        if (authService.userHasAccess(role, username, action)) {
            throw new UnauthorizedAccessException("You do not have permission to accept or reject a purchase order");
        }

        // Update the purchase order
        PurchaseOrderResponseDTO updatedPurchaseOrder = purchaseOrderService.updatePurchaseOrder(purchaseOrderUpdateDTO, role, username);
        return ResponseEntity.ok(updatedPurchaseOrder);
    }
}
