package com.vany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vany.model.Profile;
import com.vany.repository.ProfileRespositery;

import java.util.*;

@RestController
public class ProfileController {

	@Autowired
	public ProfileRespositery profile;
	
	//This method return the all User Profiles	
	@GetMapping(value = "/profiles")
	private List<Profile> getProfiles() {
		return profile.findAll();
	}
	
}
