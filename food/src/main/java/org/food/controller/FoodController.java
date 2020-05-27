package org.food.controller;

import java.util.List;

import javax.validation.Valid;

import org.domain.entity.food.Food;
import org.domain.entity.profile.Profile;
import org.domain.model.enu.ProfileType;
import org.exception.exec.UserServiceException;
import org.repository.food.FoodsRepository;
import org.repository.profile.ProfileRespositery;
import org.service.errorservice.ErrorServiceMessage;
import org.service.logservice.LogService;
import org.service.profileservice.ProfileLimitedFeatuer;
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
@RequestMapping("/profile/{profileID}")
public class FoodController {

	//---------------- Global Variable declarations
	@Autowired
	private FoodsRepository foodRepo;
	
	@Autowired
	private ProfileRespositery profileRepo;
	
	// ---------------- API Method Declarations
	@GetMapping(value = "/food/getAll")
	private ResponseEntity<?> findAllFood(@PathVariable("profileID") Integer profileID) {
		return this.getAllFood(profileID);
	}
	
	@GetMapping(value = "/food/gets")
	private ResponseEntity<?> findFoodByProfileID(@PathVariable("profileID") Integer profileID) {
		return this.getFoodByProfileID(profileID);
	}
	
	@GetMapping(value = "/food/getFood/{foodID}")
	private ResponseEntity<?> findFoodById(@PathVariable("profileID") Integer profileID,@PathVariable("foodID") Integer foodID) {
		return this.getFoodById(profileID,foodID);
	}

	@PostMapping(value = "/food/save")
	public ResponseEntity<?> saveFood(@PathVariable("profileID") Integer profileID, @Valid @RequestBody Food saveFood) {
		return this.saveFoodDetails(profileID, saveFood);
	}

	@PutMapping(value = "/food/update/{foodID}")
	public ResponseEntity<?> updateCustomer(@PathVariable("profileID") Integer profileID,
			@PathVariable("foodID") Integer foodID, @Valid @RequestBody Food saveFood) {
		return this.updateFoodDetails(profileID, foodID, saveFood);
	}

	@DeleteMapping(value = "/food/delete/{foodID}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("profileID") Integer profileID,
			@PathVariable("foodID") Integer foodID) {
		return this.deleteFoodDetails(profileID, foodID);
	}

	// ---------------- Custom Method Declarations
	private ResponseEntity<?> getFoodByProfileID(Integer profileID) {
		List<Food> reslutFoods=null;
		try {
			this.checkProfileIsOrNot(profileID);
			reslutFoods=foodRepo.findByProfileId(profileID);
			if(reslutFoods.isEmpty()){
				throw new UserServiceException(ErrorServiceMessage.NO_FOOD_RECORDS_FOUND);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(reslutFoods);
	}
	
	

	private ResponseEntity<?> getAllFood(Integer profileID) {
		List<Food> foodResult=null;
		try {
			this.checkProfileIsOrNot(profileID);
			foodResult=foodRepo.findAll();
			if(foodResult.isEmpty()) {
				throw new UserServiceException(ErrorServiceMessage.NO_FOOD_RECORDS_FOUND);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(foodResult);
	}

	private ResponseEntity<?> getFoodById(Integer profileID, Integer foodID) {
		Food foodResult=null;
		try {
			this.checkProfileIsOrNot(profileID);
			foodResult=foodRepo.findByFoodId(foodID);
			if(foodResult==null) {
				throw new UserServiceException(ErrorServiceMessage.NO_FOOD_RECORDS_FOUND);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(foodResult);
	}

	private ResponseEntity<?> saveFoodDetails(Integer profileID, @Valid Food saveFood) {
		Food resultFood=null;
		try {
			this.checkProfileIsOrNot(profileID);
			if(this.getPermissionSaveFood(profileID)) {
				saveFood.setProfile(profileRepo.findByprofileId(profileID));
				saveFood.setVersion(0.0);
				resultFood=foodRepo.saveAndFlush(saveFood);
				if(resultFood==null) {
					throw new UserServiceException(ErrorServiceMessage.NO_FOOD_RECORDS_SAVE);
				}
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(resultFood);
	}

	private ResponseEntity<?> updateFoodDetails(Integer profileID, Integer foodID, @Valid Food saveFood) {
		Food resultFood=null;
		try {
			this.checkProfileIsOrNot(profileID);
			Food tempFood=this.getFoodById(foodID);
			if(tempFood.getProfile().getProfileId()== profileID) {
				if(tempFood.getVersion().equals(saveFood.getVersion())) {
					tempFood.setFoodCategory(saveFood.getFoodCategory());
					tempFood.setFoodName(saveFood.getFoodName());
					tempFood.setFoodPrice(saveFood.getFoodPrice());
					tempFood.setProfile(profileRepo.findByprofileId(profileID));
					tempFood.setVersion(saveFood.getVersion()+1);
					resultFood=foodRepo.saveAndFlush(tempFood);
					if(resultFood== null) {
						throw new UserServiceException(ErrorServiceMessage.NO_FOOD_RECORDS_UPDATE_UNSUCCESSFULL);
					}
				}else {
					throw new UserServiceException(ErrorServiceMessage.INVALID_FOOD_VERSION+tempFood.getVersion());
				}
			}else {
				throw new UserServiceException(ErrorServiceMessage.INVALID_FOOD_PROFILE_ID);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(resultFood);
	}

	private ResponseEntity<?> deleteFoodDetails(Integer profileID, Integer foodID) {
		String result=null;
		try {
			this.checkProfileIsOrNot(profileID);
			Food tempFood=this.getFoodById(foodID);
			if(tempFood.getProfile().getProfileId()==profileID) {
				foodRepo.delete(tempFood);
				result=ErrorServiceMessage.FOOD_DELETE_SUCCESS_MESSAGE;
			}else {
				throw new UserServiceException("Please provide correct food id and profile id");
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(result);
	}
	
	//------------- Common method Declarations	
	private boolean getPermissionSaveFood(Integer profileID) {
		Boolean result=false;
		Profile tempProfile= profileRepo.findByprofileId(profileID);
		Integer totalFoodCount= foodRepo.findByProfileIdGetCount(profileID);
		if(tempProfile.getType().equals(ProfileType.PRENINUM)) {
			result = this.checkFoodCondtions(totalFoodCount,ProfileLimitedFeatuer.PREINUM_FOOD_COUNT);
		}else {
			if(tempProfile.getType().equals(ProfileType.BASIC)) {
				result = this.checkFoodCondtions(totalFoodCount,ProfileLimitedFeatuer.BAISC_FOOD_COUNT);
			}else {
				result = this.checkFoodCondtions(totalFoodCount,ProfileLimitedFeatuer.FREE_FOOD_COUNT);
			}
		}
		return result;
	}
	
	public boolean checkFoodCondtions(Integer totalFoodCount, Integer checkCount) {
		if(totalFoodCount <= checkCount) {
			return true;
		}else {
			throw new UserServiceException(ErrorServiceMessage.FOOD_SAVE_COUNT_MESSAGE+ checkCount);
		}
	}
	
	public void checkProfileIsOrNot(Integer profileId) {
		if (!profileRepo.existsById(profileId)) {
			throw new UserServiceException(ErrorServiceMessage.NO_PROFILE_RECORD + profileId);
		}
	}

	public Food getFoodById(Integer foodID) {
		return foodRepo.findByFoodId(foodID);
	}
}
