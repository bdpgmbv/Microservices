package com.vyshali.order_service.service;

import com.vyshali.order_service.domain.OrderStatus;
import com.vyshali.order_service.dto.OrderRequest;
import com.vyshali.order_service.dto.OrderResponse;
import com.vyshali.order_service.dto.OrderStatusUpdateRequest;

import java.util.List;

public interface OrderService {
    
    /**
     * Create a new order
     */
    OrderResponse createOrder(OrderRequest request);
    
    /**
     * Get order by ID
     */
    OrderResponse getOrderById(Long id);
    
    /**
     * Get all orders
     */
    List<OrderResponse> getAllOrders();
    
    /**
     * Update existing order
     */
    OrderResponse updateOrder(Long id, OrderRequest request);
    
    /**
     * Delete/Cancel order by ID
     */
    void cancelOrder(Long id);
    
    /**
     * Update order status
     */
    OrderResponse updateOrderStatus(Long id, OrderStatusUpdateRequest request);
    
    /**
     * Get orders by customer ID
     */
    List<OrderResponse> getOrdersByCustomerId(String customerId);
    
    /**
     * Get orders by status
     */
    List<OrderResponse> getOrdersByStatus(OrderStatus status);
    
    /**
     * Get recent orders
     */
    List<OrderResponse> getRecentOrders();
}
