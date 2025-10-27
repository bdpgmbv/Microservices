package com.vyshali.product_service.service;

import com.vyshali.product_service.dto.ProductRequest;
import com.vyshali.product_service.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    /**
     * Create a new product
     */
    ProductResponse createProduct(ProductRequest request);

    /**
     * Get product by ID
     */
    ProductResponse getProductById(Long id);

    /**
     * Get all products
     */
    List<ProductResponse> getAllProducts();

    /**
     * Update existing product
     */
    ProductResponse updateProduct(Long id, ProductRequest request);

    /**
     * Delete product by ID
     */
    void deleteProduct(Long id);

    /**
     * Search products by name
     */
    List<ProductResponse> searchProducts(String searchTerm);

    /**
     * Get products in stock
     */
    List<ProductResponse> getProductsInStock();
}
