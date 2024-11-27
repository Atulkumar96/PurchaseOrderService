package com.appiness.purchaseorderflowservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class PurchaseOrder extends BaseModel {
    @NotNull
    private String taskId;
    private double partsPrice;
    private double labourPrice;
    private double amount;
    private String status;
    @ElementCollection
    private List<String> reviewSummary;
//    @Version
//    private Long version;
}
