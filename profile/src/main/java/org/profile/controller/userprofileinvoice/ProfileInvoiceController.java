package org.profile.controller.userprofileinvoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.domain.entity.profile.Profile;
import org.domain.entity.profileinvoice.ProfileInvoice;
import org.domain.entity.profileinvoice.ProfileInvoiceItem;
import org.domain.entity.user.UserDet;
import org.exception.exec.UserServiceException;
import org.repository.profile.ProfileTypeRepository;
import org.repository.profileinvoice.ProfileInvoiceRepository;
import org.repository.user.UserRepository;
import org.service.errorservice.ErrorServiceMessage;
import org.service.logservice.LogService;
import org.service.resultservice.ResponseEntityResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/{userID}/profileInvoice")
public class ProfileInvoiceController {
	// --------- Global Variable Declarations

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ProfileTypeRepository profileTypeRepo;

	@Autowired
	private ProfileInvoiceRepository profileInvoiceRepo;

	// --------- API Method Declarations
	@GetMapping("/{userID}/profileInvoice/get/{profileInvoiceID}")
	public ResponseEntity<?> findProfileInvoiceByID(@PathVariable("userID") Integer userID,
			@PathVariable("profileInvoiceID") Integer profileInvoiceID) {
		return this.getProfileInvoiceByID(userID, profileInvoiceID);
	}

	@GetMapping("/profileInvoice/getAll")
	public ResponseEntity<?> findAllProfileInvoice() {
		return this.getAllProfileInvoice();
	}

	@PostMapping("/{userID}/profileInvoice/genrate/{invoiceDate}")
	public ResponseEntity<?> genrateProfileInvoice(@PathVariable("userID") Integer userID,
			@PathVariable("invoiceDate") String invoiceDate) {
		return this.saveProfileInvoice(userID, invoiceDate);
	}
	// --------- API method Declarations

	private ResponseEntity<?> getProfileInvoiceByID(Integer userID, Integer profileInvoiceID) {
		ProfileInvoice resultProfileInvoice = null;
		try {
			this.checkUserIsValidOrNot(userID);
			resultProfileInvoice = profileInvoiceRepo.findByProfileInvoiceId(profileInvoiceID);
			if (resultProfileInvoice == null) {
				throw new UserServiceException(ErrorServiceMessage.NO_PROFILE_INVOICE_RECORD);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(resultProfileInvoice);
	}

	private ResponseEntity<?> saveProfileInvoice(Integer userID, String invoiceDate) {
		ProfileInvoice resultProfileInvoice = null;
		try {
			this.checkUserIsValidOrNot(userID);
			UserDet tempUserDet = userRepo.findByuserId(userID);
			Set<Profile> tempProfileList = tempUserDet.getProfile();
			Integer existInvoiceDate = profileInvoiceRepo.existsProfileInvoiceDate(invoiceDate, userID);
			if (!(existInvoiceDate > 0)) {
				if (!tempProfileList.isEmpty()) {
					resultProfileInvoice = profileInvoiceRepo
							.saveAndFlush(this.setProfileInvoice(invoiceDate, tempProfileList, tempUserDet));
					if (resultProfileInvoice == null) {
						throw new UserServiceException(ErrorServiceMessage.NO_PROFILE_INVOICE_SAVED);
					}
				} else {
					throw new UserServiceException(ErrorServiceMessage.NO_PROFILE_INVOICE_RECORD);
				}
			} else {
				throw new UserServiceException(ErrorServiceMessage.INVALID_USER_WITH_INVOICE_DATE);
			}

		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(resultProfileInvoice);
	}

	private ProfileInvoice setProfileInvoice(String invoiceDate, Set<Profile> profileList, UserDet userDet) {
		ProfileInvoice tempProfileInvoice = new ProfileInvoice();
		tempProfileInvoice.setProfileInvoiceDate(invoiceDate);
		List<ProfileInvoiceItem> tempProfileInvoiceItem = new ArrayList<ProfileInvoiceItem>();
		Double tempProfileInvoiceTotal = 0.0;
		for (Profile profile : profileList) {
			Double tempCost = profileTypeRepo.findByType(profile.getType().toString());
			tempProfileInvoiceTotal += tempCost;
			tempProfileInvoiceItem
					.add(new ProfileInvoiceItem(profile.getProfileName(), 1, tempCost, tempCost, tempProfileInvoice));
		}
		tempProfileInvoice.setUser(userDet);
		tempProfileInvoice.setProfileInvoiceItem(tempProfileInvoiceItem);
		tempProfileInvoice.setProfileInvoiceTotal(tempProfileInvoiceTotal);
		tempProfileInvoice.setVersion(0.0);
		return tempProfileInvoice;
	}

	private ResponseEntity<?> getAllProfileInvoice() {
		List<ProfileInvoice> resultProfileInvoices = null;
		try {
			resultProfileInvoices = profileInvoiceRepo.findAll();
			if (resultProfileInvoices.isEmpty()) {
				throw new UserServiceException(ErrorServiceMessage.NO_PROFILE_INVOICE_RECORD);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(resultProfileInvoices);
	}

	// -------- Common method declarations
	public void checkUserIsValidOrNot(Integer userID) {
		if (!userRepo.existsById(userID)) {
			throw new UserServiceException(userID + ErrorServiceMessage.NO_USER_RECORD);
		}
	}
}
