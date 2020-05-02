package org.hoteltabel.controller;

import javax.validation.Valid;

import org.domain.entity.hoteltabel.HotelTabel;
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

	// ---------------- API Method Declarations
	@GetMapping(value = "/hotelTable/getAll")
	private ResponseEntity<?> findAllHotelTabel(@PathVariable("profileID") Integer profileID) {
		return this.getAllHotelTabel(profileID);
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
		// TODO Auto-generated method stub
		return null;
	}

	private ResponseEntity<?> saveHotelTabelDetails(Integer profileID, @Valid HotelTabel saveHotelTabel) {
		// TODO Auto-generated method stub
		return null;
	}

	private ResponseEntity<?> updateHotelTabelDetails(Integer profileID, Integer hotelTableID,
			@Valid HotelTabel saveHotelTabel) {
		// TODO Auto-generated method stub
		return null;
	}

	private ResponseEntity<?> deleteHotelTabelDetails(Integer profileID, Integer hotelTableID) {
		// TODO Auto-generated method stub
		return null;
	}
}
