package com.vyshali.product_service.controller;

import com.vyshali.product_service.dto.ProductRequest;
import com.vyshali.product_service.dto.ProductResponse;
import com.vyshali.product_service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Product Management", description = "APIs for managing products in the catalog")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Create a new product", description = "Creates a new product in the catalog")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Product created successfully"), @ApiResponse(responseCode = "400", description = "Invalid input")})
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {
        log.info("REST request to create product: {}", request.getName());
        ProductResponse response = productService.createProduct(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Returns a single product by its ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Product found"), @ApiResponse(responseCode = "404", description = "Product not found")})
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        log.info("REST request to get product with ID: {}", id);
        ProductResponse response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Returns a list of all products")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        log.info("REST request to get all products");
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update product", description = "Updates an existing product")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Product updated successfully"), @ApiResponse(responseCode = "404", description = "Product not found"), @ApiResponse(responseCode = "400", description = "Invalid input")})
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        log.info("REST request to update product with ID: {}", id);
        ProductResponse response = productService.updateProduct(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product", description = "Deletes a product by its ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Product deleted successfully"), @ApiResponse(responseCode = "404", description = "Product not found")})
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.info("REST request to delete product with ID: {}", id);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search products", description = "Search products by name or description")
    @ApiResponse(responseCode = "200", description = "Search completed successfully")
    public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam(required = false, defaultValue = "") String term) {
        log.info("REST request to search products with term: {}", term);
        List<ProductResponse> products = productService.searchProducts(term);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/in-stock")
    @Operation(summary = "Get products in stock", description = "Returns all products with quantity > 0")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved products")
    public ResponseEntity<List<ProductResponse>> getProductsInStock() {
        log.info("REST request to get products in stock");
        List<ProductResponse> products = productService.getProductsInStock();
        return ResponseEntity.ok(products);
    }
}
