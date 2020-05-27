package org.service.resultservice;

import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseEntityResult {

	public static Date date = Calendar.getInstance().getTime();

	public static Map<String, String> response = new HashMap<String, String>();

	// this method return as response entity as a bad request
	public static ResponseEntity<?> badRequest(String message) {
		response.put("time", "" + new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(date));
		response.put("error", message);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	// this method return as response entity as a not found request
	public static ResponseEntity<?> notFound(String message) {
		response.put("time", "" + new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(date));
		response.put("error", message);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	// this method return as response entity as a internal server error request
	public static ResponseEntity<?> internalServerError(String message) {
		response.put("time", "" + new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(date));
		response.put("error", message);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	// this method return as response entity and OK status
	public static ResponseEntity<?> successResponseEntity(Object classObject) {
		return ResponseEntity.status(HttpStatus.OK).body(classObject);
		// return new ResponseEntity<Object>(classObject,HttpStatus.OK);
	}
}