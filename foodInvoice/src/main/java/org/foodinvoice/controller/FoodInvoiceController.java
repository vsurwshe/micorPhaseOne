package org.foodinvoice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.domain.entity.foodinvoice.FoodInvoice;
import org.domain.entity.foodinvoice.FoodInvoiceItem;
import org.exception.exec.UserServiceException;
import org.repository.foodinvoice.FoodInvoiceRepository;
import org.repository.profile.ProfileRespositery;
import org.service.errorservice.ErrorServiceMessage;
import org.service.logservice.LogService;
import org.service.resultservice.ResponseEntityResult;
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
@RequestMapping(value = "/{profileID}")
public class FoodInvoiceController {
	//----------- Global Variable Declarations
	@Autowired
	private FoodInvoiceRepository foodInvoiceRepo;
	
	@Autowired
	private ProfileRespositery profileRepo;
	
	//----------- API Method Declarations	
	@GetMapping(value = "/invoice/getAll")
	public ResponseEntity<?> findAllInvoice(@PathVariable("profileID")Integer profileID){
		return this.getAllInvoice(profileID);
	}
	
	@GetMapping(value = "/invoice/{invoiceID}/get")
	public ResponseEntity<?> findInvoiceById(@PathVariable("profileID")Integer profileID,@PathVariable("invoiceID")Integer invoiceID){
		return this.getInvoiceById(profileID,invoiceID);
	}
	
	@PostMapping(value = "/invoice/save")
	public ResponseEntity<?> saveFoodInvoice(@PathVariable("profileID")Integer profileID,@Valid @RequestBody FoodInvoice saveFoodInvoice){
		return this.saveFoodInvoiceDetails(profileID,saveFoodInvoice);
	}

	@PutMapping(value = "/invoice/{invoiceID}/update")
	public ResponseEntity<?> updateFoodInvoice(@PathVariable("profileID")Integer profileID,@PathVariable("invoiceID")Integer invoiceID,@Valid @RequestBody FoodInvoice saveFoodInvoice){
		return this.updateFoodInvoiceDetails(profileID,invoiceID,saveFoodInvoice);
	}
	
	@DeleteMapping(value = "/invoice/{invoiceID}/delete")
	public ResponseEntity<?> deleteFoodInvoice(@PathVariable("profileID")Integer profileID,@PathVariable("invoiceID")Integer invoiceID){
		return this.deleteFoodInvoiceDetails(profileID,invoiceID);
	}
	
	//----------- Common Method Declarations
	private ResponseEntity<?> getAllInvoice(Integer profileID) {
		List<FoodInvoice> foodInvoiceResults=null;
		try {
			this.checkProfileIsOrNot(profileID);
			foodInvoiceResults=foodInvoiceRepo.findAll();
			if(foodInvoiceResults.isEmpty()) {
				throw new UserServiceException(ErrorServiceMessage.NO_FOOD_INVOICE_RECORD);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(foodInvoiceResults);
	}
	
	private ResponseEntity<?> getInvoiceById(Integer profileID, Integer invoiceID) {
		FoodInvoice foodInvoiceResults=null;
		try {
			this.checkProfileIsOrNot(profileID);
			FoodInvoice tempFoodInvoice=this.getFoodInvoiceById(invoiceID);
			if(tempFoodInvoice.getProfile().getProfileId()== profileID) {
				foodInvoiceResults=tempFoodInvoice;
			}else {
				throw new UserServiceException(ErrorServiceMessage.INVALID_FOOD_INVOICE_WITH_PROFILE_IDS);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(foodInvoiceResults);
	}
	
	private ResponseEntity<?> saveFoodInvoiceDetails(Integer profileID, @Valid FoodInvoice saveFoodInvoice) {
		FoodInvoice resultFoodInvoice=null;
		try {
			this.checkProfileIsOrNot(profileID);
			saveFoodInvoice.setProfile(profileRepo.findByprofileId(profileID));
			saveFoodInvoice.setVersion(0.0);
			List<FoodInvoiceItem> foodList=new ArrayList<FoodInvoiceItem>();
			if(!saveFoodInvoice.getFoodInvoiceItem().isEmpty()) {
				for (FoodInvoiceItem foodInvoiceItem : saveFoodInvoice.getFoodInvoiceItem()) {
					foodList.add(new FoodInvoiceItem(foodInvoiceItem.getFoodItemName(), foodInvoiceItem.getFoodItemQty(), foodInvoiceItem.getFoodItemSubTotal(),saveFoodInvoice));	
				}
			}
			saveFoodInvoice.setFoodInvoiceItem(foodList);
			resultFoodInvoice=foodInvoiceRepo.saveAndFlush(saveFoodInvoice);
			if(resultFoodInvoice == null) {
				throw new UserServiceException(ErrorServiceMessage.NO_FOOD_INVOICE_SAVED);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(resultFoodInvoice);
	}
	
	private ResponseEntity<?> updateFoodInvoiceDetails(Integer profileID, Integer invoiceID, @Valid FoodInvoice saveFoodInvoice) {
		FoodInvoice resultFoodInvoice=null;
		try {
			this.checkProfileIsOrNot(profileID);
			FoodInvoice tempFoodInvoice=this.getFoodInvoiceById(invoiceID);
			if(tempFoodInvoice.getProfile().getProfileId()== profileID) {
				if(tempFoodInvoice.getVersion()== saveFoodInvoice.getVersion()) {
					saveFoodInvoice.setProfile(profileRepo.findByprofileId(profileID));
					saveFoodInvoice.setVersion(tempFoodInvoice.getVersion()+1);
					resultFoodInvoice=foodInvoiceRepo.saveAndFlush(saveFoodInvoice);
					if(resultFoodInvoice == null) {
						throw new UserServiceException(ErrorServiceMessage.NO_FOOD_INVOICE_UPDATED);
					}
				}else {
					throw new UserServiceException(ErrorServiceMessage.INVALID_FOOD_INVOICE_VERSION+ tempFoodInvoice.getVersion());
				}
			}else {
				throw new UserServiceException(ErrorServiceMessage.INVALID_FOOD_INVOICE_WITH_PROFILE_IDS);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(resultFoodInvoice);
	}
	
	private ResponseEntity<?> deleteFoodInvoiceDetails(Integer profileID, Integer invoiceID) {
		String result=null;
		try {
			this.checkProfileIsOrNot(profileID);
			FoodInvoice tempFoodInvoice=this.getFoodInvoiceById(invoiceID);
			if(tempFoodInvoice.getProfile().getProfileId()== profileID) {
				foodInvoiceRepo.delete(tempFoodInvoice);
				result=ErrorServiceMessage.FOOD_DELETE_SUCCESS_MESSAGE;
			}else {
				throw new UserServiceException(ErrorServiceMessage.INVALID_FOOD_INVOICE_WITH_PROFILE_IDS);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(result);
	}
	
	//------------------------- Common method implementions
	public void checkProfileIsOrNot(Integer profileId) {
		if (!profileRepo.existsById(profileId)) {
			throw new UserServiceException(ErrorServiceMessage.NO_PROFILE_RECORD + profileId);
		}
	}

	public FoodInvoice getFoodInvoiceById(Integer foodInvocieID) {
		return foodInvoiceRepo.findByFoodInvoiceId(foodInvocieID);
	}
}
