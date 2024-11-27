package com.appiness.purchaseorderflowservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PurchaseOrderUpdateDTO {
    private int recordId;
    private String action;
    private List<String> reviewSummary;
}
