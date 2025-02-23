package com.stamp_iot_project.service;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Product;
import com.stamp_iot_project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ApiResponse<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return new ApiResponse<>("success", "Đã tìm nạp products thành công", products, 200, new Date(), null);
    }

    public ApiResponse<Product> getProductById(Integer id) {
        Optional<Product> opt = productRepository.findById(id);
        if (opt.isPresent()) {
            return new ApiResponse<>("success", "Đã tìm nạp product thành công", opt.get(), 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Product không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Product> createProduct(Product product) {
        Product saved = productRepository.save(product);
        return new ApiResponse<>("success", "Product created", saved, 201, new Date(), null);
    }

    public ApiResponse<Product> updateProduct(Integer id, Product product) {
        Optional<Product> opt = productRepository.findById(id);
        if (opt.isPresent()) {
            Product existing = opt.get();
            existing.setName(product.getName());
            existing.setDescription(product.getDescription());
            existing.setBrand(product.getBrand());
            existing.setCategory(product.getCategory());
            existing.setSpecification(product.getSpecification());
            existing.setPrice(product.getPrice());
            existing.setImageUrl(product.getImageUrl());
            Product updated = productRepository.save(existing);
            return new ApiResponse<>("success", "Product updated", updated, 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Product không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Void> deleteProduct(Integer id) {
        productRepository.deleteById(id);
        return new ApiResponse<>("success", "Product deleted", null, 200, new Date(), null);
    }
}
