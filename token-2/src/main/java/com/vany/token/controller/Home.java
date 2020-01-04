package com.vany.token.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class Home{

    @GetMapping(value="/")
    public String getIndex() {
        return "Welcome to First Spring Project Form Token - 2";
    }

    @GetMapping(value="/hello")
    public String getHello() {
        return "Hello Welcome World.......";
    }
    
}