package com.vany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vany.model.Address;
import com.vany.model.Payments;
import com.vany.model.Profile;

public interface ProfileRespositery extends JpaRepository<Profile, Integer> {
	// This Method Return the Profile Details by id	
	Profile findByprofileId(Integer profileId);
	// This method return list of payments by profile id
	List<Payments> findPaymentsByProfileId(Integer profileId);
	// This method return list of Address by profile id	
	List<Address> findAddressByProfileId(Integer profileId);
	
}
