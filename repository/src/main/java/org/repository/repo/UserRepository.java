package org.repository.repo;

import org.domain.entity.UserDet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<UserDet, Integer> {
	// this method get user details form user email
	public UserDet findByUserEmail(String userEmail);
	
	@Query(nativeQuery = true, value = "SELECT * FROM `user` WHERE `user_id` =?1")
	public UserDet findByprofileId(Integer userId);
}
