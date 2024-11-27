package com.appiness.purchaseorderflowservice.services;

import com.appiness.purchaseorderflowservice.designpatterns.strategy.RoleStrategyFactory;
import com.appiness.purchaseorderflowservice.dtos.PurchaseOrderRequestDTO;
import com.appiness.purchaseorderflowservice.dtos.PurchaseOrderResponseDTO;
import com.appiness.purchaseorderflowservice.dtos.PurchaseOrderUpdateDTO;
import com.appiness.purchaseorderflowservice.dtos.PurchaseOrderUpdateResponseDTO;
import com.appiness.purchaseorderflowservice.models.PurchaseOrder;
import com.appiness.purchaseorderflowservice.repositories.PurchaseOrderRepository;
import com.appiness.purchaseorderflowservice.utils.PurchaseOrderMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private RoleStrategyFactory roleStrategyFactory;
    @Autowired
    private PurchaseOrderRepository repository;

    @Override
    public List<PurchaseOrderResponseDTO> listPurchaseOrders(String role) {
        return roleStrategyFactory.getStrategy(role).listPurchaseOrders();
        //return null;
    }

    @Override
    public PurchaseOrderResponseDTO createPurchaseOrder(PurchaseOrderRequestDTO purchaseOrderDTO, String role, String username) {

        // Convert the PurchaseOrderRequestDTO to a PurchaseOrder entity
        PurchaseOrder purchaseOrder = PurchaseOrderMapper.convertToEntity(purchaseOrderDTO, role, username);

        // Create the purchase order through the repository
        PurchaseOrder savedPurchaseOrder = repository.save(purchaseOrder);

        return PurchaseOrderMapper.convertToDTO(savedPurchaseOrder);
    }

    @Override
    public PurchaseOrderResponseDTO updatePurchaseOrder(PurchaseOrderUpdateDTO purchaseOrderUpdateDTO, String role, String username) {
        Optional<PurchaseOrder> orderToUpdate = repository.findById(purchaseOrderUpdateDTO.getRecordId());
        if(orderToUpdate.isPresent()){
            PurchaseOrder existingOrder = orderToUpdate.get();
            existingOrder.setId(purchaseOrderUpdateDTO.getRecordId());
            existingOrder.setReviewSummary(purchaseOrderUpdateDTO.getReviewSummary());
//            if(role.equals("reviewer") && purchaseOrderUpdateDTO.getAction().equals("accept")) {
//                existingOrder.setStatus("reviewed");
//            } else if(role.equals("reviewer") && purchaseOrderUpdateDTO.getAction().equals("reject")) {
//                existingOrder.setStatus("reject");
//            }
            existingOrder.setStatus(purchaseOrderUpdateDTO.getAction());
            existingOrder.setUpdatedBy(username);
            existingOrder.setCreatedBy(role);

            PurchaseOrder updatedOrder = repository.save(existingOrder);
            return PurchaseOrderMapper.convertToDTO(updatedOrder);

        }
        else {
            throw new EntityNotFoundException("Purchase Order not found with id: " + purchaseOrderUpdateDTO.getRecordId());
        }
    }
}
