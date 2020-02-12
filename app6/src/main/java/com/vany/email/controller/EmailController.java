package com.vany.email.controller;

import org.springframework.web.bind.annotation.RestController;

import com.vany.email.dto.EmailModule;
import com.vany.email.service.EmailService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class EmailController{

    @GetMapping(value="/")
    public String getIndex() {
        return "Welcome to Email Service Applications";
    }
    
    @PostMapping(value = "/sendEmail")
    public String sendEmail(@RequestBody EmailModule emailModule) {
      return  new EmailService().sendEmail(emailModule);
    }
 
}