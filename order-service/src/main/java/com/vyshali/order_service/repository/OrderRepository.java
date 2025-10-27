package com.vyshali.order_service.repository;

import com.vyshali.order_service.domain.Order;
import com.vyshali.order_service.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    /**
     * Find all orders for a specific customer
     */
    List<Order> findByCustomerId(String customerId);
    
    /**
     * Find all orders with a specific status
     */
    List<Order> findByStatus(OrderStatus status);
    
    /**
     * Find all orders for a customer with a specific status
     */
    List<Order> findByCustomerIdAndStatus(String customerId, OrderStatus status);
    
    /**
     * Find all orders for a specific product
     */
    List<Order> findByProductId(Long productId);
    
    /**
     * Count orders by status
     */
    Long countByStatus(OrderStatus status);
    
    /**
     * Find recent orders (last N orders)
     */
    @Query("SELECT o FROM Order o ORDER BY o.orderDate DESC")
    List<Order> findRecentOrders();
    
    /**
     * Find orders by customer ordered by date descending
     */
    List<Order> findByCustomerIdOrderByOrderDateDesc(String customerId);
    
    /**
     * Check if customer has any pending orders
     */
    boolean existsByCustomerIdAndStatus(String customerId, OrderStatus status);
}
