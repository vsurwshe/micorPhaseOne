package com.vany.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.vany.model.UserDet;
import com.vany.repository.ProfileRespositery;
import com.vany.service.ErrorServiceMessage;
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
		return this.getPaymentsByProfileId(profileId);
	}

	// This method get all Payments details by id
	@GetMapping(value = "/{profileId}/address")
	private List<Address> findAddressById(@PathVariable Integer profileId) {
		return this.getAddressByProfileId(profileId);
	}

	// This method get user details bu profile id
	@GetMapping(value = "/{profileId}/user")
	private UserDet findUserDetailsByProfileId(@PathVariable Integer profileId) {
		return this.getUserByProfileId(profileId);
	}

	// ----------- Custom profile find
	// This method get all profiles
	public List<Profile> getAllProfiles() {
		List<Profile> profiles = null;
		try {
			profiles = profileRepo.findAll();
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return profiles;
	}

	// This method get profile by profileId
	public Profile getByProfile(Integer profileId) {
		Profile profile = null;
		try {
			profile = profileRepo.findByprofileId(profileId);
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return profile;
	}

	// this method save profile
	public Profile saveProfile(Profile profile) {
		Profile userProfile = null;
		try {
			userProfile = profileRepo.save(profile);
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return userProfile;
	}

	// This method update profile by id
	public Profile updateProfile(@Valid Profile profile, Integer profileId) {
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
					LogService.setLogger(profile.getVersion() + ErrorServiceMessage.PROFILE_UPDATE_WORNG_VERSION
							+ tempProfile.getVersion());
					throw new UserServiceException(profile.getVersion()
							+ ErrorServiceMessage.PROFILE_UPDATE_WORNG_VERSION + tempProfile.getVersion());
				}
			} else {
				LogService.setLogger(profileId + ErrorServiceMessage.NO_REC_PROFILE);
				throw new UserServiceException(profileId + ErrorServiceMessage.NO_REC_PROFILE);
			}

		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return userProfile;
	}

	// This method get all payments by profile id
	public List<Payments> getPaymentsByProfileId(Integer profileId) {
		List<Payments> payments = null;
		try {
			payments = profileRepo.findPaymentsByProfileId(profileId);
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return payments;
	}

	// This method delete profile by profile id
	public String deleteProfileById(Integer profileId) {
		String deleteMessage = null;
		try {
			// This condition checking profile is available or not
			if (this.getByProfile(profileId) != null) {
				profileRepo.deleteById(profileId);
				deleteMessage = ErrorServiceMessage.PROFILE_DELETE_SUCCESS;
			} else {
				throw new UserServiceException(profileId + ErrorServiceMessage.NO_REC_PROFILE);
			}
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return deleteMessage;
	}

	// This method get list of address by profile id
	public List<Address> getAddressByProfileId(Integer profileId) {
		List<Address> address = null;
		try {
			address = profileRepo.findAddressByProfileId(profileId);
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return address;
	}

	// This method get user details by profile id
	public UserDet getUserByProfileId(Integer profileId) {
		UserDet user = null;
		try {
			user = profileRepo.findUserByProfileId(profileId);
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return user;
	}
}
