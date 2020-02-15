package com.vany.SMS.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.vany.SMS.dto.ResultSMS;
import com.vany.SMS.dto.SMSMoulde;
import com.vany.SMS.service.LogService;
import org.springframework.http.*;

import java.util.Collections;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
public class SMSController {
	private static final Logger logger = Logger.getLogger(SMSController.class);

	@GetMapping(value = "/")
	public String getIndex() {
		// logs debug message
		if (logger.isDebugEnabled()) {
			logger.debug("Welcome | SMS Service is executed!");
		}
		return "Welcome to SMS Service Applications";
	}

	@PostMapping(value = "/sendSMS")
	private ResultSMS sendSMS(@Valid @RequestBody SMSMoulde smsMoulde) {
		return this.sendCustomSMS(smsMoulde);
	}

	// --------------- custome method
	// This method setting custome headers for request
	private HttpEntity<SMSMoulde> setHeaders(SMSMoulde smsMoulde) {
		// create headers
		HttpHeaders headers = new HttpHeaders();
		// set `accept` header
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		// set custom header
		headers.set("authorization",
				"awy6NGlBQERLZjvkDPd9Ffe2bATzUKM8VCrcnS375JWYOgu0px73wfTzV0KkpBsgo9JZyURMndDuIitj");
		// build the request
		return new HttpEntity<SMSMoulde>(smsMoulde, headers);
	}

	// this method sending SMS
	private ResultSMS sendCustomSMS(SMSMoulde smsMoulde) {
		ResultSMS result = null;
		try {
			final String uri = "https://www.fast2sms.com/dev/bulk";
			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.postForObject(uri, this.setHeaders(smsMoulde), ResultSMS.class);
			System.out.println(result);
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return result;
	}

}