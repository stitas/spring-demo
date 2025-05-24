package com.springer.demoWebApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@Entity
public class Product {
    @Id
    private int id;
    private String title;
    private float price;

    public Product() {}

    public Product(String title, float price) {
        this.title = title;
        this.price = price;
    }

}
