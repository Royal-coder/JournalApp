package com.royal.JournalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthCheck {

    @GetMapping(value = "/health")
    public String healthCheck(){
        return "Journal App is up and running";
    }
}
