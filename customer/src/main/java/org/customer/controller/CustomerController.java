package org.customer.controller;

import javax.validation.Valid;

import org.domain.entity.customer.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/profile/{profileID}")
@CrossOrigin(origins = "*")
public class CustomerController {
	
	
	//------------------ API Method declarations	
	@GetMapping(value = "/customer/getAll")
	public ResponseEntity<?> findAllCustomer(@PathVariable("profileID")Integer profileID ){
		return this.getAllCustomer(profileID);
	}
	
	@PostMapping(value = "/customer/save")
	public ResponseEntity<?> saveCustomer(@PathVariable("profileID")Integer profileID, @Valid @RequestBody Customer saveCustomer){
		return this.regsiterCustomer(profileID, saveCustomer);
	}
	
	@PutMapping(value = "/customer/update/{customerID}")
	public ResponseEntity<?> updateCustomer(@PathVariable("profileID")Integer profileID,@PathVariable("customerID")Integer customerID, @Valid @RequestBody Customer saveCustomer){
		return this.updateCustomerDetails(profileID,customerID, saveCustomer);
	}
	
	@DeleteMapping(value = "/customer/delete/{customerID}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("profileID")Integer profileID,@PathVariable("customerID")Integer customerID){
		return this.deleteCustomerDetails(profileID,customerID);
	}

	//----------------- Custom Method Implemented Sections  	
	private ResponseEntity<?> getAllCustomer(Integer profileID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private ResponseEntity<?> regsiterCustomer(Integer profileID, @Valid Customer saveCustomer) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private ResponseEntity<?> updateCustomerDetails(Integer profileID, Integer customerID,
			@Valid Customer saveCustomer) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private ResponseEntity<?> deleteCustomerDetails(Integer profileID, Integer customerID) {
		// TODO Auto-generated method stub
		return null;
	}
}
