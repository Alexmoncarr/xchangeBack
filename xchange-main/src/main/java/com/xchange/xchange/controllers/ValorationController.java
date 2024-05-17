package com.xchange.xchange.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xchange.xchange.models.Valoration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/valorations")
public class ValorationController {

    @GetMapping
    public String getAllValorations() {
        // Add code to retrieve all valorations from the database
        return "Get all valorations";
    }

    @PostMapping
    public String createValoration(@RequestBody Valoration valoration) {
        // Add code to create a new valoration in the database
        return "Create valoration";
    }
}
