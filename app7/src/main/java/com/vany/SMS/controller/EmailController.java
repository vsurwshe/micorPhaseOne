package com.vany.SMS.controller;

import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class EmailController{
	private static final Logger logger = Logger.getLogger(EmailController.class);
    @GetMapping(value="/")
    public String getIndex() {
    	//logs debug message
    	if(logger.isDebugEnabled()){
    			logger.debug("Welcome | SMS Service is executed!");
    	}
        return "Welcome to SMS Service Applications";
    }
    
    
}