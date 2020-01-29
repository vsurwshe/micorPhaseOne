package com.vany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vany.model.Profile;

public interface ProfileRespositery extends JpaRepository<Profile, Integer> {

}
