package org.customer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/profile/{profileId}")
@CrossOrigin(origins = "*")
public class CustomerController {
	
	
	//------------------ API Method declarations	
	@GetMapping(value = "/customer/getAll")
	public ResponseEntity<?> findAllCustomer(@PathVariable("profileId")Integer profileId ){
		return this.getAllCustomer(profileId);
	}

	//----------------- Custom Method Implemented Sections  	
	private ResponseEntity<?> getAllCustomer(Integer profileId) {
		// TODO Auto-generated method stub
		return null;
	}

}
