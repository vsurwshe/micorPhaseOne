package org.repository.repo;

import org.domain.model.Profile;
import org.domain.model.UserDet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfileRespositery extends JpaRepository<Profile, Integer> {

	@Query(value = "select case when count(*)>0 then true else false end from profile where profile_id=:profileId", nativeQuery = true)
	boolean existsProileId(@Param("profileId") Integer profileId);

	boolean existsById(Integer profileId);

	// This Method Return the Profile Details by id
	@Query(nativeQuery = true, value = "SELECT `profile_id`,`profile_name`,`user`,`features`,`type`,`created_at`,`updated_at` FROM `profile` WHERE `profile_id`=?1")
	Profile findByprofileId(Integer profileId);

	// This method return user details by profile id
	UserDet findUserByProfileId(Integer profileId);
}
