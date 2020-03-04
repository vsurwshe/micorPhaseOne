package org.auth.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class Home{

	private static final Logger logger = Logger.getLogger(Home.class);
    @GetMapping(value="/")
    public String getIndex() {
    	//logs debug message
    	if(logger.isDebugEnabled()){
    				logger.debug("getWelcome is executed!");
    	}
        return "index.html";
    }

    @GetMapping(value="/auth")
    public String getHello() {
        return "Your Token is : wrhfalsdhfashdfkjashdflkjashdfkjhsd123123sznvlxhcvdh123123vnslkhfvshdf123123lvslxhg123";
    }
    
}