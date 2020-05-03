package org.address.controller;


import java.util.List;

import javax.validation.Valid;

import org.domain.entity.address.Address;
import org.exception.exec.UserServiceException;
import org.repository.address.AddressRepository;
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
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/{profileId}")
public class AddressController {

	//--------------- Global variable Declarations	
	@Autowired
	public ProfileRespositery profileRepo;
	
	@Autowired
	private AddressRepository addressRepo;
	
	//-------------- API Method implemetions	
	@GetMapping(value = "/address/{addressId}")
	@ApiOperation(value = "Retruning  Address details by id")
	public ResponseEntity<?> findAddress(@PathVariable("profileId")Integer profileId, @PathVariable("addressId")Integer addressId) {
		return this.getALlAddressByAddressId(profileId,addressId);
	}
	
	@PostMapping(value = "/address/save")
	public ResponseEntity<?> saveAddress(@PathVariable("profileID") Integer profileID,
			@Valid @RequestBody Address saveAddress) {
		return this.saveAddressDetails(profileID, saveAddress);
	}
	
	@PutMapping(value = "/address/update/{addressID}")
	public ResponseEntity<?> updateAddress(@PathVariable("profileID") Integer profileID,
			@PathVariable("addressID") Integer addressID, @Valid @RequestBody Address saveAddress) {
		return this.updateAddressDetails(profileID, addressID, saveAddress);
	}

	@DeleteMapping(value = "/address/delete/{addressID}")
	public ResponseEntity<?> deleteHotelTabel(@PathVariable("profileID") Integer profileID,
			@PathVariable("addressID") Integer addressID) {
		return this.deleteAddressDetails(profileID, addressID);
	}

	//---------------- This sections custom method implementions
	private ResponseEntity<?> getALlAddressByAddressId(Integer profileId, Integer addressId) {
		List<Address> address=null;
		try {
			this.checkProfileIdValidOrNot(profileId);
			address=addressRepo.findAddressByProfileId(profileId);
			if(address != null) {
				throw new UserServiceException(ErrorServiceMessage.NO_ADDRESS_RECORD+addressId);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(address);
	}
	
	private ResponseEntity<?> saveAddressDetails(Integer profileID, @Valid Address saveAddress) {
		Address resultAddress=null;
		try {
			
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(resultAddress);
	}
	
	private ResponseEntity<?> updateAddressDetails(Integer profileID, Integer addressID, @Valid Address saveAddress) {
		Address resultAddress=null;
		try {
			
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(resultAddress);	
	}
	
	private ResponseEntity<?> deleteAddressDetails(Integer profileID, Integer addressID) {
		String result=null;
		try {
			
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(result);
	}
	
	// This is checking passing profile id is valid or not	
	public void checkProfileIdValidOrNot(Integer profileId) {
		if (!profileRepo.existsById(profileId)) {
			throw new UserServiceException(ErrorServiceMessage.NO_PROFILE_RECORD+profileId);
		}
	}
	
//	public Address getAddressById(Integer addressID) {
//		return addressRepo.findById(addressID);
//	}
}
