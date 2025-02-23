package com.stamp_iot_project.controller;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Product;
import com.stamp_iot_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ApiResponse<List<Product>> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ApiResponse<Product> getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public ApiResponse<Product> createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public ApiResponse<Product> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }
}

