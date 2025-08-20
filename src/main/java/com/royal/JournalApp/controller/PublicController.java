package com.royal.JournalApp.controller;

import com.royal.JournalApp.entity.User;
import com.royal.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/health")
    public String healthCheck(){
        return "Journal App is up and running";
    }

    @PostMapping(value = "/create-user")
    public void createUser(@RequestBody User user) {
        userService.createEntry(user);
    }
}
