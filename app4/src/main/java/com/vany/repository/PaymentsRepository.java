package com.vany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vany.model.Payments;

public interface PaymentsRepository extends JpaRepository<Payments, Integer> {

	// This method find Payments by payment id	
	Payments findBypayId(Integer payId);
}
