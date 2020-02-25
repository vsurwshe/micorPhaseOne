package com.vany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vany.exception.UserServiceException;
import com.vany.model.Address;
import com.vany.model.Payments;
import com.vany.model.Profile;
import com.vany.repository.ProfileRespositery;
import com.vany.service.LogService;

import java.util.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/profile")
public class ProfileController {

	@Autowired
	public ProfileRespositery profileRepo;

	// This method return the all User Profiles
	@GetMapping(value = "/getAll")
	private List<Profile> findAllProfiles() {
		return this.getAllProfiles();
	}

	// This method return the profile details by id
	@GetMapping(value = "/{profileId}")
	private Profile findByIdProfile(@PathVariable Integer profileId) {
		return this.getByProfile(profileId);
	}

	// This method save profile
	@PostMapping(value = "/saveProfile")
	private Profile saveProfileDetails(@Valid @RequestBody Profile profile) {
		return this.saveProfile(profile);
	}

	// This method update profile
	@PutMapping(value = "/updateProfile")
	private Profile updateProfileDetails(@Valid @RequestBody Profile profile, @PathVariable Integer profileId) {
		return this.updateProfile(profile, profileId);
	}

	// This method delete profile
	@DeleteMapping(value = "/{profileId}")
	private String deleteProfile(@PathVariable Integer profileId) {
		return this.deleteProfileById(profileId);
	}

	// This method get all Payments details by id
	@GetMapping(value = "/{profileId}/payments")
	private List<Payments> findPaymentsById(@PathVariable Integer profileId) {
		return this.getPaymentsById(profileId);
	}

	// This method get all Payments details by id
	@GetMapping(value = "/{profileId}/address")
	private ResponseEntity<?> findAddressById(@PathVariable Integer profileId) {
		return this.getAddressById(profileId);
	}

	// ----------- custome profile find
	private List<Profile> getAllProfiles() {
		List<Profile> profiles = null;
		try {
			profiles = profileRepo.findAll();
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return profiles;
	}

	private Profile getByProfile(int id) {
		Profile profile = null;
		try {
			profile = profileRepo.findByprofileId(id);
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return profile;
	}

	private Profile saveProfile(Profile profile) {
		Profile userProfile = null;
		try {
			userProfile = profileRepo.save(profile);
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return userProfile;
	}

	private Profile updateProfile(@Valid Profile profile, Integer profileId) {
		Profile userProfile = null;
		try {
			Profile tempProfile = this.getByProfile(profileId);
			if (tempProfile != null) {
				if (profile.getVersion().equals(tempProfile.getVersion())) {
					tempProfile.setAddress(profile.getAddress());
					tempProfile.setProfileName(profile.getProfileName());
					tempProfile.setType(profile.getType());
					tempProfile.setVersion(profile.getVersion() + 1);
				} else {
					LogService.setLogger(profile.getVersion() + " this not Correct version, expected version "
							+ tempProfile.getVersion());
					throw new UserServiceException(profile.getVersion() + " this not Correct version, expected version "
							+ tempProfile.getVersion());
				}
			} else {
				LogService.setLogger(profileId + " This Profile Not Found Exception");
				throw new UserServiceException(profileId + " This Profile Not Found Exception");
			}

		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return userProfile;
	}

	private List<Payments> getPaymentsById(Integer profileId) {
		List<Payments> payments = null;
		try {
			payments = profileRepo.findPaymentsByProfileId(profileId);
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return payments;
	}

	private String deleteProfileById(Integer profileId) {
		String deleteMessage = null;
		try {
			// This condtions checking profile is available or not
			if (this.getByProfile(profileId) != null) {
				profileRepo.deleteById(profileId);
				return "Your Profile Deleted SuccessFull !!!";
			} else {
				throw new UserServiceException(profileId + " this Profile not found");
			}
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return deleteMessage;
	}

	private ResponseEntity<?> getAddressById(Integer profileId) {
		List<Address> address = null;
		try {
			address = profileRepo.findAddressByProfileId(profileId);
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			return  ResponseEntity.badRequest().body(profileId+"Not Found");
		}
		return new ResponseEntity<List<Address>>(address,HttpStatus.OK) ;
	}

}
