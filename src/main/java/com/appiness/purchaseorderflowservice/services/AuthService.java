package com.appiness.purchaseorderflowservice.services;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public boolean userHasAccess(String role, String username, StringBuilder action) {
        if(role.equals("Publisher") && action.toString().equals("createPurchaseOrder")){
            return false;
        }
        if(role.equals("Reviewer") && action.toString().equals("accept")
        || role.equals("Reviewer") && action.toString().equals("reject")){
            return false;
        }
        return true;
    }
}

/*
In v2:
AuthService as a separate microservice OAuth2.0 and JWT token based authentication
action, role validation through persistence layer
 */
