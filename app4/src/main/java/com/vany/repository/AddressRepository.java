package com.vany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vany.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	

}
