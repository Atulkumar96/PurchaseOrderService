package com.appiness.purchaseorderflowservice.utils;

import com.appiness.purchaseorderflowservice.dtos.PurchaseOrderRequestDTO;
import com.appiness.purchaseorderflowservice.dtos.PurchaseOrderResponseDTO;
import com.appiness.purchaseorderflowservice.dtos.PurchaseOrderUpdateDTO;
import com.appiness.purchaseorderflowservice.dtos.PurchaseOrderUpdateResponseDTO;
import com.appiness.purchaseorderflowservice.models.PurchaseOrder;

/*
Mapper class to convert PurchaseOrderRequestDTO to PurchaseOrder and vice versa
 */
public class PurchaseOrderMapper {
    public static PurchaseOrder convertToEntity(PurchaseOrderRequestDTO dto, String role, String username) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        //purchaseOrder.setRecordId(dto.getRecordId());
        purchaseOrder.setTaskId(dto.getTaskId());
        purchaseOrder.setPartsPrice(dto.getPartsPrice());
        purchaseOrder.setLabourPrice(dto.getLabourPrice());
        purchaseOrder.setAmount(dto.getAmount());
        purchaseOrder.setStatus("created");
        purchaseOrder.setCreatedBy(role);
//        purchaseOrder.setUpdatedBy(dto.getUpdatedBy());
        purchaseOrder.setReviewSummary(dto.getReviewSummary());
        return purchaseOrder;
    }

    public static PurchaseOrder convertToEntity(PurchaseOrderUpdateDTO dto, String role, String username) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(dto.getRecordId());
        if(role.equals("reviewer")) {
            purchaseOrder.setStatus("reviewed");
        } else {
            purchaseOrder.setStatus("approved");
        }
        purchaseOrder.setReviewSummary(dto.getReviewSummary());
        purchaseOrder.setUpdatedBy(username+" "+role);
        return purchaseOrder;
    }

    public static PurchaseOrderResponseDTO convertToDTO(PurchaseOrder entity) {
        PurchaseOrderResponseDTO dto = new PurchaseOrderResponseDTO();
        dto.setRecordId(entity.getId());
        dto.setTaskId(entity.getTaskId());
        dto.setPartsPrice(entity.getPartsPrice());
        dto.setLabourPrice(entity.getLabourPrice());
        dto.setAmount(entity.getAmount());
        if(entity.getCreatedBy().equals("Reviewer") && entity.getStatus().equals("accept")) {
            dto.setStatus("reviewed");
        } else if(entity.getCreatedBy().equals("Reviewer") && entity.getStatus().equals("reject")) {
            dto.setStatus("rework");
        }else if(entity.getCreatedBy().equals("Publisher")) {
            dto.setStatus("created");
        }
        dto.setReviewSummary(entity.getReviewSummary());
        return dto;
    }

    public static PurchaseOrderResponseDTO convertToDTO(int updatedIds) {
        PurchaseOrderResponseDTO purchaseOrderUpdateResponseDTO = new PurchaseOrderResponseDTO();
        purchaseOrderUpdateResponseDTO.setRecordId(updatedIds);
        return purchaseOrderUpdateResponseDTO;
    }
}
