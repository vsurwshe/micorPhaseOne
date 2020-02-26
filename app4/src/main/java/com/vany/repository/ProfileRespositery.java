package com.vany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.vany.model.Profile;
import com.vany.model.UserDet;

public interface ProfileRespositery extends JpaRepository<Profile, Integer> {

	@Query(value = "select case when count(*)>0 then true else false end from profile where profile_id=:profileId", nativeQuery = true)
	boolean existsProileId(@Param("profileId") Integer profileId);

	boolean existsById(Integer profileId);

	// This Method Return the Profile Details by id
	Profile findByprofileId(Integer profileId);

	// This method return user details by profile id
	UserDet findUserByProfileId(Integer profileId);
}
