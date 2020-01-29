package com.vany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vany.model.Payments;
import com.vany.repository.PaymentsRepository;

@RestController
public class PaymentsController {
	
	@Autowired
	private PaymentsRepository paymentsRepo;
	
	@GetMapping(value="/payments")
	public List<Payments> getPayments() {
		return paymentsRepo.findAll();
	}

}
