package com.xchange.xchange.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xchange.xchange.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}