package org.food.controller;

import javax.validation.Valid;

import org.domain.entity.food.Food;
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

	// ---------------- API Method Declarations
	@GetMapping(value = "/food/getAll")
	private ResponseEntity<?> findAllFood(@PathVariable("profileID") Integer profileID) {
		return this.getAllFood(profileID);
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
	private ResponseEntity<?> getAllFood(Integer profileID) {
		// TODO Auto-generated method stub
		return null;
	}

	private ResponseEntity<?> saveFoodDetails(Integer profileID, @Valid Food saveFood) {
		// TODO Auto-generated method stub
		return null;
	}

	private ResponseEntity<?> updateFoodDetails(Integer profileID, Integer foodID, @Valid Food saveFood) {
		// TODO Auto-generated method stub
		return null;
	}

	private ResponseEntity<?> deleteFoodDetails(Integer profileID, Integer foodID) {
		// TODO Auto-generated method stub
		return null;
	}
}
