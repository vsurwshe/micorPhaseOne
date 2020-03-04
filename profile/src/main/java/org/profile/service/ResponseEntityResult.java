package org.profile.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseEntityResult {

	public static Map<String,String> response = new HashMap<String, String>();
    // this method return as response entity as a bad request
	public static ResponseEntity<?> badRequest(String message){
		response.put("error", "an error expected on processing file");
		return ResponseEntity.badRequest().body(response);
		// return new  ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
		// return new  ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
	}

	// this method return as response enitiy and ok status
	public static ResponseEntity<?> successResponseEntity(Object classObject){
		response.put("ok", ""+classObject);
		return ResponseEntity.badRequest().body(response);
		// return new ResponseEntity<Object>(classObject,HttpStatus.OK);
	}
}