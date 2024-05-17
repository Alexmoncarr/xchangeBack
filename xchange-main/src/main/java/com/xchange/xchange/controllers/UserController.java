package com.xchange.xchange.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.xchange.xchange.models.User;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;




@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        // Code to retrieve user by ID from database or any other data source
    }

    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        // Code to create a new user in the database or any other data source
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        // Code to update an existing user in the database or any other data source
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        // Code to delete a user from the database or any other data source
    }

}