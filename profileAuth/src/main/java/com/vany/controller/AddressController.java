package com.vany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vany.model.Address;
import com.vany.repository.AddressRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "A1",description = "This Address Contoller")
public class AddressController {

	@Autowired
	private AddressRepository addressRepo;
	
	@GetMapping(value = "/address")
	@ApiOperation(value = "Retruning All Address")
	public List<Address> getAddress() {
		return addressRepo.findAll();
	}
	
}
