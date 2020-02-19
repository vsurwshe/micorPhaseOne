package com.vany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vany.model.UserDet;

@Repository
public interface UserRepository extends JpaRepository<UserDet, Integer> {
	// this method get user details form user email
	public UserDet findByUserEmail(String userEmail);
}
