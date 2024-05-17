package com.xchange.xchange.controllers;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xchange.xchange.models.Order;




@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping
    public List<Order> getAllOrders() {
        // TODO: Implement logic to retrieve all orders
        // For now, let's return an empty list
        return new ArrayList<>();
    }
}
