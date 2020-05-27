package org.profile.controller.userprofile;

import org.domain.entity.address.Address;
import org.domain.entity.payments.Payments;
import org.domain.entity.profile.Profile;
import org.domain.entity.user.UserDet;
import org.domain.model.enu.ProfileFeature;
import org.domain.model.enu.ProfileType;
import org.domain.result.model.PaymentResult;
import org.exception.exec.UserServiceException;
import org.repository.address.AddressRepository;
import org.repository.payment.PaymentsRepository;
import org.repository.profile.ProfileRespositery;
import org.repository.profile.ProfileTypeRepository;
import org.repository.user.UserRepository;
import org.service.errorservice.ErrorServiceMessage;
import org.service.logservice.LogService;
import org.service.resultservice.ResponseEntityResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/profile")
@CrossOrigin(origins = "*")
public class ProfileController {

	//---------- Global Variable declarations	
	@Autowired
	public ProfileRespositery profileRepo;

	@Autowired
	public PaymentsRepository paymentRepo;

	@Autowired
	public AddressRepository addressRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	public ProfileTypeRepository profileTypeRepo;

	//----------- API Method declarations	
	@GetMapping(value = "/getUserDetails")
	private ResponseEntity<?> findUserDeatils(){
	 UserDet userResult= this.getUser();
	 return ResponseEntityResult.successResponseEntity(userResult);
	}
	
	// This method return the all User Profiles
	@GetMapping(value = "/getAll")
	private ResponseEntity<?> findAllProfiles() {
		return this.getAllProfiles();
	}

	// This method return the profile details by id
	@GetMapping(value = "/{profileId}")
	private ResponseEntity<?> findByIdProfile(@PathVariable(value = "profileId") Integer profileId) {
		return this.getByProfile(profileId);
	}

	// This method save profile
	@PostMapping(value = "/saveProfile")
	private ResponseEntity<?> saveProfileDetails(@Valid @RequestBody Profile profile) {
		return this.saveProfile(profile);
	}

	// This method update profile
	@PutMapping(value = "/{profileId}/updateProfile")
	private ResponseEntity<?> updateProfileDetails(@Valid @RequestBody Profile profile, @PathVariable(value = "profileId") Integer profileId) {
		return this.updateProfile(profile, profileId);
	}

	// This method delete profile
	@DeleteMapping(value = "/{profileId}")
	private ResponseEntity<?> deleteProfile(@PathVariable(value = "profileId") Integer profileId) {
		return this.deleteProfileById(profileId);
	}

	// This method get all Payments details by id
	@GetMapping(value = "/{profileId}/payment")
	private ResponseEntity<?> findPaymentsById(@PathVariable(value = "profileId") Integer profileId) {
		return this.getPaymentsByProfileId(profileId);
	}

	// This method get all Payments details by id
	@GetMapping(value = "/{profileId}/address")
	private ResponseEntity<?> findAddressById(@PathVariable(value = "profileId") Integer profileId) {
		return this.getAddressByProfileId(profileId);
	}

	// This method get user details by profile id
	@GetMapping(value = "/{profileId}/user")
	private ResponseEntity<?> findUserDetailsByProfileId(@PathVariable(value = "profileId") Integer profileId) {
		return this.getUserByProfileId(profileId);
	}

	// This method get user details by profile id
	@GetMapping(value = "/payment/getAll")
	@ApiIgnore
	private ResponseEntity<?> findAllPayments() {
		return this.getAllPayments();
	}

	// ----------- Custom Method decorations
	// This method get all profiles
	public ResponseEntity<?> getAllProfiles() {
		Set<Profile> profiles = null;
		try {
			profiles = this.getUser().getProfile();
			if (profiles.isEmpty()) {
				throw new UserServiceException(ErrorServiceMessage.NO_PROFILE_RECORD);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.notFound(e.getMessage());
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.badRequest(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(profiles);
	}

	// This method get profile by profileId
	public ResponseEntity<?> getByProfile(Integer profileId) {
		Optional<Profile> profile = null;
		try {
			profile = profileRepo.findById(profileId);
			if (profile != null) {
				throw new UserServiceException(ErrorServiceMessage.NO_PROFILE_RECORD + profileId);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.notFound(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(profile);
	}

	// this method save profile
	public ResponseEntity<?> saveProfile(Profile profile){
		Profile userProfile = null;
		try {
			if(profile.getType().equals(ProfileType.FREE)) {
				if(this.checkFreeProfile()) {
					throw new UserServiceException(ErrorServiceMessage.PROFILE_FREE_NOT_SAVE_AGAIN);
				}
			}
			profile.setUser(this.getUser());
			EnumSet<ProfileFeature> tempProfileFeatures = this.setPrfileFeatuers(profile);
			profile.setFeatures(tempProfileFeatures);
			profile.setVersion(0);
			userProfile = profileRepo.save(profile);
			if (userProfile.equals(null)) {
				throw new UserServiceException(ErrorServiceMessage.PROFILE_NOT_UPDATE + profile.getProfileId());
			}
		}catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.notFound(e.getMessage());
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.badRequest(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(userProfile);
	}

	// This method update profile by id
	public ResponseEntity<?> updateProfile(@Valid Profile profile, Integer profileId) {
		Profile userProfile = null;
		try {
			Profile tempProfile = profileRepo.findByprofileId(profileId);
			// this conditions checking profile is available or not in database
			if (tempProfile != null) {
				// this condition checking sent profile and database profile version are equal
				// or not
				if (profile.getVersion().equals(tempProfile.getVersion())) {
					tempProfile.setAddress(profile.getAddress());
					tempProfile.setFeatures(profile.getFeatures());
					tempProfile.setProfileName(profile.getProfileName());
					tempProfile.setType(profile.getType());
					tempProfile.setVersion(profile.getVersion() + 1);
					userProfile = profileRepo.saveAndFlush(tempProfile);
					// this conditions checking the record updated successfully or not
					if (userProfile == null) {
						throw new UserServiceException(ErrorServiceMessage.PROFILE_NOT_UPDATE);
					}
				} else {
					throw new UserServiceException(profile.getVersion()
							+ ErrorServiceMessage.INVLIAD_PROFILE_VERSION + tempProfile.getVersion());
				}
			} else {
				throw new UserServiceException(ErrorServiceMessage.NO_PROFILE_RECORD + profileId);
			}

		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.notFound(e.getMessage());
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.badRequest(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(userProfile);
	}

	// This method get all payments by profile id
	public ResponseEntity<?> getPaymentsByProfileId(Integer userId) {
		List<Payments> payments = null;
		try {
			payments = paymentRepo.findPaymentsByUserId(userId);
			if (payments.isEmpty()) {
				throw new UserServiceException(ErrorServiceMessage.NO_PROFILE_RECORD + userId);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.notFound(e.getMessage());
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.badRequest(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(payments);
	}

	// This method delete profile by profile id
	public ResponseEntity<?> deleteProfileById(Integer profileId) {
		String deleteMessage = null;
		try {
			// This condition checking profile is available or not
			if (this.getByProfile(profileId) != null) {
				profileRepo.deleteById(profileId);
				deleteMessage = ErrorServiceMessage.PROFILE_DELETE_SUCCESS;
			} else {
				throw new UserServiceException(ErrorServiceMessage.NO_PROFILE_RECORD + profileId);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.notFound(e.getMessage());
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.badRequest(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(deleteMessage);
	}

	// This method get list of address by profile id
	public ResponseEntity<?> getAddressByProfileId(Integer profileId) {
		List<Address> address = null;
		try {
			address = addressRepo.findAddressByProfileId(profileId);
			if (address.isEmpty()) {
				throw new UserServiceException(ErrorServiceMessage.NO_PROFILE_ADDRESS_RECORD_FOUND + profileId);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.badRequest(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(address);
	}

	// This method get user details by profile id
	public ResponseEntity<?> getUserByProfileId(Integer profileId) {
		UserDet user = null;
		try {
			user = profileRepo.findUserByProfileId(profileId);
			if (user.equals(null)) {
				throw new UserServiceException(ErrorServiceMessage.PROFILE_NOT_FOUND_USER + profileId);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.notFound(e.getMessage());
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.badRequest(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(user);
	}

	// this method setting a profile features
	private EnumSet<ProfileFeature> setPrfileFeatuers(Profile profile) {
		EnumSet<ProfileFeature> profileFeatuer = null;
		try {
			if (profile.getType().equals(ProfileType.BASIC) || profile.getType().equals(ProfileType.PRENINUM)) {
				this.checkBalanceProfile(profile);
				if (profile.getType().equals(ProfileType.BASIC)) {
					profileFeatuer = EnumSet.of(ProfileFeature.READ, ProfileFeature.WRITE, ProfileFeature.UPDATE);
				} else {
					profileFeatuer = EnumSet.of(ProfileFeature.READ, ProfileFeature.WRITE, ProfileFeature.UPDATE, ProfileFeature.DELETE);
				}
			}else {
				profileFeatuer = EnumSet.of(ProfileFeature.READ);
			}
			
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			throw new UserServiceException(e.getMessage());
		}
		return profileFeatuer;
	}

	private ResponseEntity<?> getAllPayments() {
		List<PaymentResult> payments = null;
		try {
			if (paymentRepo.findAll().isEmpty()) {
				throw new UserServiceException(ErrorServiceMessage.NO_PAYMENT_RECORD);
			}else {
				payments = new ArrayList<PaymentResult>();
				for (Payments payment : paymentRepo.findAll()) {
					payments.add(new PaymentResult(payment.getPaymentId(), payment.getMode(), payment.getTransctionsId(),payment.getAmount(),payment.getTarnsDate(),payment.getVerify(),payment.getVersion(), payment.getUser().getUserId()));
				}
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.notFound(e.getMessage());
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.badRequest(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(payments);
	}

	// This Functions get User name form Token And Return the User Details
	public UserDet getUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		UserDet daoUser = userRepo.findByUserEmail(username);
		return daoUser;
	}

	// This method checking profile is there not
	public void checkProfileIsOrNot(Integer profileId) {
		if (!profileRepo.existsById(profileId)) {
			throw new UserServiceException(ErrorServiceMessage.NO_PROFILE_RECORD + profileId);
		}
	}

	// This method checking User have sufficient balance to create a BASIC/PRENIMUM
	// profiles
	private void checkBalanceProfile(Profile profile) {
			Double userBalance = profile.getUser().getUserBalance();
			Double profileCost = profileTypeRepo.findByType(profile.getType().toString());
			if (userBalance <= profileCost) {
				throw new UserServiceException(ErrorServiceMessage.PROFILE_USER_BALANCE_NOT_SUFFICENT);
			}
	}
	
	public Boolean checkFreeProfile() {
		Boolean result=false;
		UserDet tempUser= this.getUser();
		Profile tempFreeProfile= profileRepo.findByFreeProfile(tempUser.getUserId());
		if(tempFreeProfile != null) {
			result = true;
		}
		return result;
	}
}
