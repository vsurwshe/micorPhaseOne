package com.vany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vany.model.Address;
import com.vany.model.Payments;
import com.vany.model.Profile;
import com.vany.model.UserDet;

public interface ProfileRespositery extends JpaRepository<Profile, Integer> {

	@Query(value = "select case when count(*)>0 then true else false end from profile where profile_id=:profileId", nativeQuery = true)
	boolean existsProileId(@Param("profileId") Integer profileId);

	boolean existsById(Integer profileId);

	// This Method Return the Profile Details by id
	Profile findByprofileId(Integer profileId);

	// This method return list of payments by profile id
	@Query(value = "select * from payments  where profile_id=:profileId", nativeQuery = true)
	List<Payments> findPaymentsByProfileId(@Param("profileId") Integer profileId);

	// This method return list of Address by profile id
	@Query(value = "select * from address  where profile_id=:profileId", nativeQuery = true)
	List<Address> findAddressByProfileId(@Param("profileId") Integer profileId);

	// This method return user details by profile id
	UserDet findUserByProfileId(Integer profileId);
}
