package com.vany.token.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class Home{

    @GetMapping(value="/")
    public String getIndex() {
        return "Welcome to Resource Applications";
    }

    @GetMapping(value="/res")
    public String getHello() {
        return "Hi This is Your Resource";
    }
    
}