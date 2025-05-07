package com.springer.demoWebApp.controller;

import com.springer.demoWebApp.model.Product;
import com.springer.demoWebApp.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/products/{prodId}")
    public Product getProduct(@PathVariable int prodId) {
        return productService.getProduct(prodId);
    }

    @PostMapping("/products")
    public String addProduct(@RequestBody Product prod) {
        productService.addProduct(prod);
        return "Product successfully added !";
    }

    @PutMapping("/products")
    public String updateProduct(@RequestBody Product prod) {
        productService.updateProduct(prod);
        return "Product successfully updated !";
    }

    @DeleteMapping("/products/{prodId}")
    public String deleteProduct(@PathVariable int prodId) {
        productService.deleteProduct(prodId);
        return "Product successfully deleted !";
    }
}
