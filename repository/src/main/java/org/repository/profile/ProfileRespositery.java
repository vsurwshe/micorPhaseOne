package org.repository.profile;

import java.util.List;

import org.domain.entity.profile.Profile;
import org.domain.entity.user.UserDet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRespositery extends JpaRepository<Profile, Integer> {

	@Query(value = "select case when count(*)>0 then true else false end from profile where profile_id=:profileId", nativeQuery = true)
	boolean existsProileId(@Param("profileId") Integer profileId);

	boolean existsById(Integer profileId);

	// This Method Return the Profile Details by id
	@Query(nativeQuery = true, value = "SELECT `profile_id`,`profile_name`,`user`,`features`,`type`,`created_at`,`updated_at`,`version` FROM `profile` WHERE `profile_id`=?1")
	Profile findByprofileId(Integer profileId);

	// This method return user details by profile id
	UserDet findUserByProfileId(Integer profileId);
	
	// This method return the list of profile by user
	List<Profile> findByUser(UserDet user);
	
	@Query(nativeQuery=true, value="SELECT * FROM `profile` WHERE type= 0 AND user= ?1")
	Profile findByFreeProfile(Integer userId);
}
