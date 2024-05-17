package com.xchange.xchange.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xchange.xchange.models.Event;
import com.xchange.xchange.models.Order;
import com.xchange.xchange.models.User;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Event findByTitle(String title);
    Order findFirstByUserId(User user);
}
