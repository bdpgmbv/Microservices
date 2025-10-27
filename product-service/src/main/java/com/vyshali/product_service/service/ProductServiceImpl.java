package com.vyshali.product_service.service;

import com.vyshali.product_service.domain.Product;
import com.vyshali.product_service.dto.ProductRequest;
import com.vyshali.product_service.dto.ProductResponse;
import com.vyshali.product_service.exception.ResourceNotFoundException;
import com.vyshali.product_service.repository.ProductRepository;
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
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        log.info("Creating new product: {}", request.getName());

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());

        Product savedProduct = productRepository.save(product);
        log.info("Product created successfully with ID: {}", savedProduct.getId());

        return mapToResponse(savedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long id) {
        log.info("Fetching product with ID: {}", id);

        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        return mapToResponse(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {
        log.info("Fetching all products");

        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        log.info("Updating product with ID: {}", id);

        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());

        Product updatedProduct = productRepository.save(product);
        log.info("Product updated successfully with ID: {}", updatedProduct.getId());

        return mapToResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Deleting product with ID: {}", id);

        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with ID: " + id);
        }

        productRepository.deleteById(id);
        log.info("Product deleted successfully with ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> searchProducts(String searchTerm) {
        log.info("Searching products with term: {}", searchTerm);

        List<Product> products = productRepository.searchProducts(searchTerm);
        return products.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getProductsInStock() {
        log.info("Fetching products in stock");

        List<Product> products = productRepository.findAllInStock();
        return products.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    /**
     * Helper method to map Product entity to ProductResponse DTO
     */
    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder().id(product.getId()).name(product.getName()).description(product.getDescription()).price(product.getPrice()).stockQuantity(product.getStockQuantity()).createdAt(product.getCreatedAt()).updatedAt(product.getUpdatedAt()).build();
    }
}
