package com.royal.JournalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The healthCheck class provides a simple REST endpoint for checking the health status
 * of the application.
 *
 * Annotations:
 * - @RestController: Indicates that this class is a RESTful controller where every method
 *   returns a domain object rather than a view. It is used to create RESTful web services.
 *
 * Endpoint:
 * - GET /health: Returns a static message indicating that the application is running
 *   and operational. This endpoint can be used for basic health monitoring.
 */
@RestController
public class healthCheck {

    @GetMapping(value = "/health")
    public String healthCheck(){
        return "Journal App is up and running";
    }
}
