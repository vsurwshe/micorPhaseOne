package com.vany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vany.model.Payments;

public interface PaymentsRepository extends JpaRepository<Payments, Integer> {

	// This method find Payments by payment id	
	Payments findBypayId(Integer payId);
	// This method find Payments by trnsaction date	and return list
	List<Payments> findBytarnsDate(String userDate);
	
}
