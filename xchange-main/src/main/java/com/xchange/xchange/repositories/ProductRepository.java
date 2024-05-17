package com.xchange.xchange.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xchange.xchange.models.Event;
import com.xchange.xchange.models.Product;
import com.xchange.xchange.models.Order;
import com.xchange.xchange.models.User;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Event findByName(String name);

    Product findFirstByOrderId(Order order);

    Product findFirstByUserId(User user);
}
