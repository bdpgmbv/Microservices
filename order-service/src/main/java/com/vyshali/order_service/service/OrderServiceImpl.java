package com.vyshali.order_service.service;

import com.vyshali.order_service.domain.Order;
import com.vyshali.order_service.domain.OrderStatus;
import com.vyshali.order_service.dto.OrderRequest;
import com.vyshali.order_service.dto.OrderResponse;
import com.vyshali.order_service.dto.OrderStatusUpdateRequest;
import com.vyshali.order_service.exception.InvalidOrderStatusException;
import com.vyshali.order_service.exception.ResourceNotFoundException;
import com.vyshali.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {
    
    private final OrderRepository orderRepository;
    
    @Override
    public OrderResponse createOrder(OrderRequest request) {
        log.info("Creating new order for customer: {}", request.getCustomerId());
        
        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setProductId(request.getProductId());
        order.setProductName(request.getProductName());
        order.setQuantity(request.getQuantity());
        order.setUnitPrice(request.getUnitPrice());
        order.setStatus(OrderStatus.PENDING);
        
        Order savedOrder = orderRepository.save(order);
        log.info("Order created successfully with ID: {}", savedOrder.getId());
        
        return mapToResponse(savedOrder);
    }
    
    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long id) {
        log.info("Fetching order with ID: {}", id);
        
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));
        
        return mapToResponse(order);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {
        log.info("Fetching all orders");
        
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public OrderResponse updateOrder(Long id, OrderRequest request) {
        log.info("Updating order with ID: {}", id);
        
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));
        
        // Check if order can be updated (not if it's already shipped or delivered)
        if (order.getStatus() == OrderStatus.SHIPPED || order.getStatus() == OrderStatus.DELIVERED) {
            throw new InvalidOrderStatusException(
                    "Cannot update order in " + order.getStatus() + " status");
        }
        
        order.setCustomerId(request.getCustomerId());
        order.setProductId(request.getProductId());
        order.setProductName(request.getProductName());
        order.setQuantity(request.getQuantity());
        order.setUnitPrice(request.getUnitPrice());
        
        Order updatedOrder = orderRepository.save(order);
        log.info("Order updated successfully with ID: {}", updatedOrder.getId());
        
        return mapToResponse(updatedOrder);
    }
    
    @Override
    public void cancelOrder(Long id) {
        log.info("Cancelling order with ID: {}", id);
        
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));
        
        // Check if order can be cancelled
        if (order.getStatus() == OrderStatus.DELIVERED) {
            throw new InvalidOrderStatusException("Cannot cancel order that has been delivered");
        }
        
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        log.info("Order cancelled successfully with ID: {}", id);
    }
    
    @Override
    public OrderResponse updateOrderStatus(Long id, OrderStatusUpdateRequest request) {
        log.info("Updating status for order ID: {} to {}", id, request.getStatus());
        
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));
        
        // Validate status transition
        validateStatusTransition(order.getStatus(), request.getStatus());
        
        order.setStatus(request.getStatus());
        Order updatedOrder = orderRepository.save(order);
        log.info("Order status updated successfully for ID: {}", id);
        
        return mapToResponse(updatedOrder);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByCustomerId(String customerId) {
        log.info("Fetching orders for customer: {}", customerId);
        
        List<Order> orders = orderRepository.findByCustomerIdOrderByOrderDateDesc(customerId);
        return orders.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByStatus(OrderStatus status) {
        log.info("Fetching orders with status: {}", status);
        
        List<Order> orders = orderRepository.findByStatus(status);
        return orders.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getRecentOrders() {
        log.info("Fetching recent orders");
        
        List<Order> orders = orderRepository.findRecentOrders();
        return orders.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    /**
     * Validate order status transition
     */
    private void validateStatusTransition(OrderStatus currentStatus, OrderStatus newStatus) {
        // Cannot change status from CANCELLED
        if (currentStatus == OrderStatus.CANCELLED) {
            throw new InvalidOrderStatusException("Cannot change status of cancelled order");
        }
        
        // Cannot change status from DELIVERED
        if (currentStatus == OrderStatus.DELIVERED) {
            throw new InvalidOrderStatusException("Cannot change status of delivered order");
        }
        
        // Validate logical transitions
        if (currentStatus == OrderStatus.PENDING && 
            newStatus != OrderStatus.CONFIRMED && newStatus != OrderStatus.CANCELLED) {
            throw new InvalidOrderStatusException(
                    "Order in PENDING status can only move to CONFIRMED or CANCELLED");
        }
        
        if (currentStatus == OrderStatus.CONFIRMED && 
            newStatus != OrderStatus.SHIPPED && newStatus != OrderStatus.CANCELLED) {
            throw new InvalidOrderStatusException(
                    "Order in CONFIRMED status can only move to SHIPPED or CANCELLED");
        }
        
        if (currentStatus == OrderStatus.SHIPPED && newStatus != OrderStatus.DELIVERED) {
            throw new InvalidOrderStatusException(
                    "Order in SHIPPED status can only move to DELIVERED");
        }
    }
    
    /**
     * Helper method to map Order entity to OrderResponse DTO
     */
    private OrderResponse mapToResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .productId(order.getProductId())
                .productName(order.getProductName())
                .quantity(order.getQuantity())
                .unitPrice(order.getUnitPrice())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .statusDescription(order.getStatus().getDescription())
                .orderDate(order.getOrderDate())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}
