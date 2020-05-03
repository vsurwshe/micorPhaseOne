package org.hoteltabel.controller;

import java.util.List;

import javax.validation.Valid;

import org.domain.entity.food.Food;
import org.domain.entity.hoteltabel.HotelTabel;
import org.exception.exec.UserServiceException;
import org.hibernate.context.TenantIdentifierMismatchException;
import org.repository.hoteltable.HotelTableRepository;
import org.repository.profile.ProfileRespositery;
import org.service.apiService.ErrorServiceMessage;
import org.service.apiService.LogService;
import org.service.apiService.ResponseEntityResult;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@CrossOrigin("*")
@RequestMapping("/profile/{profileID}")
public class HotelTableController {

	// ---------------- Global Variable Declarations
	@Autowired
	private HotelTableRepository hotelTableRepo;

	@Autowired
	private ProfileRespositery profileRepo;

	// ---------------- API Method Declarations
	@GetMapping(value = "/hotelTable/getAll")
	private ResponseEntity<?> findAllHotelTabel(@PathVariable("profileID") Integer profileID) {
		return this.getAllHotelTabel(profileID);
	}

	@GetMapping(value = "/hotelTable/get/{hotelTabelID}")
	private ResponseEntity<?> findAllHotelTabel(@PathVariable("profileID") Integer profileID,
			@PathVariable("hotelTabelID") Integer hotelTabelID) {
		return this.getHotelTabelByID(profileID, hotelTabelID);
	}

	@PostMapping(value = "/hotelTable/save")
	public ResponseEntity<?> saveHotelTabel(@PathVariable("profileID") Integer profileID,
			@Valid @RequestBody HotelTabel saveHotelTabel) {
		return this.saveHotelTabelDetails(profileID, saveHotelTabel);
	}

	@PutMapping(value = "/hotelTable/update/{hotelTableID}")
	public ResponseEntity<?> updateHotelTabel(@PathVariable("profileID") Integer profileID,
			@PathVariable("hotelTableID") Integer hotelTableID, @Valid @RequestBody HotelTabel saveHotelTabel) {
		return this.updateHotelTabelDetails(profileID, hotelTableID, saveHotelTabel);
	}

	@DeleteMapping(value = "/hotelTable/delete/{hotelTableID}")
	public ResponseEntity<?> deleteHotelTabel(@PathVariable("profileID") Integer profileID,
			@PathVariable("hotelTableID") Integer hotelTableID) {
		return this.deleteHotelTabelDetails(profileID, hotelTableID);
	}

	// ---------------- Custom Method Declarations
	private ResponseEntity<?> getAllHotelTabel(Integer profileID) {
		List<HotelTabel> listHotelTabels = null;
		try {
			this.checkProfileIsOrNot(profileID);
			listHotelTabels = hotelTableRepo.findAll();
			if (listHotelTabels.isEmpty()) {
				throw new UserServiceException(ErrorServiceMessage.NO_HOTEL_TABEL_RECORDS_FOUND);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(listHotelTabels);
	}

	private ResponseEntity<?> getHotelTabelByID(Integer profileID, Integer hotelTabelID) {
		HotelTabel result = null;
		try {
			this.checkProfileIsOrNot(profileID);
			HotelTabel tempHotelTabel = this.getHotelTabelById(hotelTabelID);
			if (tempHotelTabel.getProfile().getProfileId() == profileID) {
				result = tempHotelTabel;
			} else {
				throw new UserServiceException(ErrorServiceMessage.INVALID_HOTEL_PROFILE_ID);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(result);
	}

	private ResponseEntity<?> saveHotelTabelDetails(Integer profileID, @Valid HotelTabel saveHotelTabel) {
		HotelTabel resultHotelTable = null;
		try {
			this.checkProfileIsOrNot(profileID);
			saveHotelTabel.setProfile(profileRepo.findByprofileId(profileID));
			saveHotelTabel.setVersion(0.0);
			resultHotelTable = hotelTableRepo.saveAndFlush(saveHotelTabel);
			if (resultHotelTable == null) {
				throw new UserServiceException(ErrorServiceMessage.NO_HOTEL_TABLE_RECORDS_SAVE);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(resultHotelTable);
	}

	private ResponseEntity<?> updateHotelTabelDetails(Integer profileID, Integer hotelTableID,
			@Valid HotelTabel saveHotelTabel) {
		HotelTabel resultHotelTabel = null;
		try {
			this.checkProfileIsOrNot(profileID);
			HotelTabel tempHotelTabel = this.getHotelTabelById(hotelTableID);
			if (tempHotelTabel.getProfile().getProfileId() == profileID) {
				if (tempHotelTabel.getVersion() == saveHotelTabel.getVersion()) {
					tempHotelTabel.setHotelLocations(saveHotelTabel.getHotelLocations());
					tempHotelTabel.setHotelName(saveHotelTabel.getHotelName());
					tempHotelTabel.setHotelTabelSize(saveHotelTabel.getHotelTabelSize());
					tempHotelTabel.setProfile(profileRepo.findByprofileId(profileID));
					tempHotelTabel.setVersion(tempHotelTabel.getVersion() + 1);
					resultHotelTabel = hotelTableRepo.saveAndFlush(tempHotelTabel);
					if (resultHotelTabel == null) {
						throw new UserServiceException(ErrorServiceMessage.NO_HOTEL_TABLE_RECORDS_UPDATED);
					}
				} else {
					throw new UserServiceException(ErrorServiceMessage.INVALID_HOTEL_TABEL_VERSION+ tempHotelTabel.getVersion());
				}
			} else {
				throw new UserServiceException(ErrorServiceMessage.INVALID_HOTEL_TABLE_AND_PROFILE_IDS);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(resultHotelTabel);
	}

	private ResponseEntity<?> deleteHotelTabelDetails(Integer profileID, Integer hotelTableID) {
		String result = null;
		try {
			this.checkProfileIsOrNot(profileID);
			HotelTabel tempHotelTabel = this.getHotelTabelById(hotelTableID);
			if (tempHotelTabel.getProfile().getProfileId() == profileID) {
				hotelTableRepo.delete(tempHotelTabel);
				result = ErrorServiceMessage.HOTEL_TABEL_DElETE_MESSAGE;
			} else {
				throw new UserServiceException(ErrorServiceMessage.INVALID_HOTEL_TABLE_AND_PROFILE_IDS);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(result);
	}

	// ------------- Common method Declarations
	public void checkProfileIsOrNot(Integer profileId) {
		if (!profileRepo.existsById(profileId)) {
			throw new UserServiceException(ErrorServiceMessage.NO_PROFILE_RECORD + profileId);
		}
	}

	public HotelTabel getHotelTabelById(Integer hotelTabelID) {
		return hotelTableRepo.findByHotelTabelId(hotelTabelID);
	}
}
