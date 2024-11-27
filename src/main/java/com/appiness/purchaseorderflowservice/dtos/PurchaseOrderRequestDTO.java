package com.appiness.purchaseorderflowservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PurchaseOrderRequestDTO {
    //private int recordId;
    private String taskId;
    private double partsPrice;
    private double labourPrice;
    private double amount;
    private String status;
    private List<String> reviewSummary;
}
