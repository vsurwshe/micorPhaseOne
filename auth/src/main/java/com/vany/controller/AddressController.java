package com.vany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vany.model.Address;
import com.vany.repository.AddressRepository;

@RestController
public class AddressController {

	@Autowired
	private AddressRepository addressRepo;
	
	@GetMapping(value = "/address")
	public List<Address> getAddress() {
		return addressRepo.findAll();
	}
	
}
