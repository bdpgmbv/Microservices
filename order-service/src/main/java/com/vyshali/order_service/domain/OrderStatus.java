package com.vyshali.order_service.domain;

/**
 * Order status enumeration representing the lifecycle of an order
 */
public enum OrderStatus {
    PENDING("Order created, awaiting confirmation"),
    CONFIRMED("Order confirmed, preparing for shipment"),
    SHIPPED("Order shipped, in transit"),
    DELIVERED("Order delivered successfully"),
    CANCELLED("Order cancelled");
    
    private final String description;
    
    OrderStatus(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}
