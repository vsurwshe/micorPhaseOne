package com.vany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.vany.exception.UserServiceException;


@Controller
public class Home{

    @GetMapping(value="/")
    public String getIndex() {
        return "index.html";
    }

    @GetMapping(value="/auth")
    public String getHello() {
        return "Your Token is : wrhfalsdhfashdfkjashdflkjashdfkjhsd123123sznvlxhcvdh123123vnslkhfvshdf123123lvslxhg123";
    }
    
}