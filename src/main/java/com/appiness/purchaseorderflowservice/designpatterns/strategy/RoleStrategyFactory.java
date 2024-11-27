package com.appiness.purchaseorderflowservice.designpatterns.strategy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RoleStrategyFactory {
    private final Map<String, RoleStrategy> strategies = new HashMap<>();

    @Autowired
    public RoleStrategyFactory(PublisherStrategy publisherStrategy, ReviewerStrategy reviewerStrategy, ApproverStrategy approverStrategy) {
        strategies.put("Publisher", publisherStrategy);
        strategies.put("Reviewer", reviewerStrategy);
        strategies.put("Approver", approverStrategy);
    }

    public RoleStrategy getStrategy(String role) {
        RoleStrategy strategy = strategies.get(role);
        if (strategy == null) {
            throw new IllegalArgumentException("Invalid role: " + role);
        }

        return strategy;
    }
}
