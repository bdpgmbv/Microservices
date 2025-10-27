package com.vyshali.product_service.repository;

import com.vyshali.product_service.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Find products by name containing the given string (case-insensitive)
     */
    List<Product> findByNameContainingIgnoreCase(String name);

    /**
     * Find products by stock quantity greater than or equal to the given value
     */
    List<Product> findByStockQuantityGreaterThanEqual(Integer quantity);

    /**
     * Custom query to find products in stock (quantity > 0)
     */
    @Query("SELECT p FROM Product p WHERE p.stockQuantity > 0")
    List<Product> findAllInStock();

    /**
     * Custom query to search products by name or description
     */
    @Query("SELECT p FROM Product p WHERE " + "LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " + "LOWER(p.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Product> searchProducts(@Param("searchTerm") String searchTerm);
}
