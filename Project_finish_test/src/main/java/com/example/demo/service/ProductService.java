package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);
    Product findProductById(Long productId);
    Product updateProduct(Product product );
}
