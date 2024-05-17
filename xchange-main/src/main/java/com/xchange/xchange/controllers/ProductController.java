package com.xchange.xchange.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xchange.xchange.models.Product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public String getProduct(@PathVariable("id") Long id) {
        // TODO: Implement logic to retrieve product by ID
        return "Get product with ID: " + id;
    }

    @PostMapping("/")
    public String createProduct(@RequestBody Product product) {
        // TODO: Implement logic to create a new product
        return "Create product: " + product.getName();
    }

    // TODO: Add more methods for updating, deleting, and listing products

}
