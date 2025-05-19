package com.springer.demoWebApp.service;

import com.springer.demoWebApp.model.Product;
import com.springer.demoWebApp.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(int prodId) {
        return productRepository.findById(prodId).orElse(new Product());
    }

    public void addProduct(Product prod) {
        productRepository.save(prod);
    }

    public void updateProduct(Product prod) {
        productRepository.save(prod);
    }

    public void deleteProduct(int prodId) {
        productRepository.deleteById(prodId);
    }
}
