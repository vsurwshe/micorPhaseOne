package org.profile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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