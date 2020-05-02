package org.customer.controller;

import java.util.List;

import javax.validation.Valid;

import org.domain.entity.customer.Customer;
import org.exception.exec.UserServiceException;
import org.repository.customer.CustomerRepository;
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
@RequestMapping(value = "/profile/{profileID}")
@CrossOrigin(origins = "*")
public class CustomerController {

	// ------------------ Global Variable declarations
	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private ProfileRespositery profileRepo;

	// ------------------ API Method declarations
	@GetMapping(value = "/customer/getAll")
	public ResponseEntity<?> findAllCustomer(@PathVariable("profileID") Integer profileID) {
		return this.getAllCustomer(profileID);
	}

	@GetMapping(value = "/customer/getAll/{customerID}")
	public ResponseEntity<?> findCustomerByID(@PathVariable("profileID") Integer profileID,
			@PathVariable("customerID") Integer customerID) {
		return this.getCustomerById(profileID, customerID);
	}

	@PostMapping(value = "/customer/save")
	public ResponseEntity<?> saveCustomer(@PathVariable("profileID") Integer profileID,
			@Valid @RequestBody Customer saveCustomer) {
		return this.regsiterCustomer(profileID, saveCustomer);
	}

	@PutMapping(value = "/customer/update/{customerID}")
	public ResponseEntity<?> updateCustomer(@PathVariable("profileID") Integer profileID,
			@PathVariable("customerID") Integer customerID, @Valid @RequestBody Customer saveCustomer) {
		return this.updateCustomerDetails(profileID, customerID, saveCustomer);
	}

	@DeleteMapping(value = "/customer/delete/{customerID}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("profileID") Integer profileID,
			@PathVariable("customerID") Integer customerID) {
		return this.deleteCustomerDetails(profileID, customerID);
	}

	// ----------------- Custom Method Implemented Sections
	private ResponseEntity<?> getAllCustomer(Integer profileID) {
		List<Customer> listOfCustomer = null;
		try {
			this.checkProfileIsOrNot(profileID);
			listOfCustomer = customerRepo.findAll();
			if (listOfCustomer.isEmpty()) {
				throw new UserServiceException(ErrorServiceMessage.NO_CUSTOMER_RECORDS_FOUND);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(listOfCustomer);
	}

	private ResponseEntity<?> getCustomerById(Integer profileID, Integer customerID) {
		Customer resultCustomer = null;
		try {
			this.checkProfileIsOrNot(profileID);
			resultCustomer = this.getCustomerById(customerID);
			if (resultCustomer == null) {
				throw new UserServiceException(ErrorServiceMessage.NO_CUSTOMER_RECORDS_FOUND);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(resultCustomer);
	}

	private ResponseEntity<?> regsiterCustomer(Integer profileID, @Valid Customer saveCustomer) {
		Customer resultCustomer = null;
		try {
			this.checkProfileIsOrNot(profileID);
			saveCustomer.setProfile(profileRepo.findByprofileId(profileID));
			saveCustomer.setVersion(0.0);
			resultCustomer = customerRepo.saveAndFlush(saveCustomer);
			if (resultCustomer == null) {
				throw new UserServiceException(ErrorServiceMessage.NO_CUSTOMER_SAVE);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(resultCustomer);
	}

	private ResponseEntity<?> updateCustomerDetails(Integer profileID, Integer customerID,
			@Valid Customer saveCustomer) {
		Customer resultCustomer = null;
		try {
			this.checkProfileIsOrNot(profileID);
			Customer tempCustomer = this.getCustomerById(customerID);
			if (tempCustomer.getProfile().getProfileId() == profileID) {
				if (tempCustomer.getVersion() == saveCustomer.getVersion()) {
					tempCustomer.setCustomerAddress(saveCustomer.getCustomerAddress());
					tempCustomer.setCustomerEmail(saveCustomer.getCustomerEmail());
					tempCustomer.setCustomerMobileNo(saveCustomer.getCustomerMobileNo());
					tempCustomer.setCustomerName(saveCustomer.getCustomerName());
					tempCustomer.setProfile(profileRepo.findByprofileId(profileID));
					tempCustomer.setVersion(saveCustomer.getVersion() + 1);
					resultCustomer = customerRepo.saveAndFlush(tempCustomer);
				} else {
					throw new UserServiceException(
							ErrorServiceMessage.INVALID_CUSTOMER_VERSION + tempCustomer.getVersion());
				}
			} else {
				throw new UserServiceException(ErrorServiceMessage.INVALID_CUSTOMER_ID);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(resultCustomer);
	}

	private ResponseEntity<?> deleteCustomerDetails(Integer profileID, Integer customerID) {
		String result = null;
		try {
			this.checkProfileIsOrNot(profileID);
			Customer tempCustomer = this.getCustomerById(customerID);
			if (tempCustomer.getProfile().getProfileId() == profileID) {
				customerRepo.delete(tempCustomer);
				result = ErrorServiceMessage.CUSTOMER_DELETE_MESSAGE;
			} else {
				throw new UserServiceException(ErrorServiceMessage.INVALID_CUSTOMER_ID);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(result);
	}

	// -------------------- Common method declarations
	// This method checking profile is there not
	public void checkProfileIsOrNot(Integer profileId) {
		if (!profileRepo.existsById(profileId)) {
			throw new UserServiceException(ErrorServiceMessage.NO_REC_PROFILE + profileId);
		}
	}

	public Customer getCustomerById(Integer customerID) {
		return customerRepo.findByCustomerID(customerID);
	}
}
