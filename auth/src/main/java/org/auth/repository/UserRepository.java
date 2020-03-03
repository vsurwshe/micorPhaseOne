package org.auth.repository;

import org.domain.model.UserDet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<UserDet, Integer> {
	public UserDet findByUserEmail(String userEmail);
}
