package com.vany.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseEntityResult {

    // this method return as response entity as a bad request
	public static ResponseEntity<?> badRequest(String message){
		return new  ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
	}

	// this method return as response enitiy and ok status
	public static ResponseEntity<?> successResponseEntity(Object classObject){
		return new ResponseEntity<Object>(classObject,HttpStatus.OK);
	}
}