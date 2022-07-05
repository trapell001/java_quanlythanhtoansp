package com.example.demo.service.Impl;

import com.example.demo.entity.Product;
import com.example.demo.exeption.ProductQuantityNotEngoughException;
import com.example.demo.repo.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable.previousOrFirst());
    }

    @Override
    public Product findProductById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        product.orElseThrow(()-> new ProductQuantityNotEngoughException());
        return product.get();
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
}
