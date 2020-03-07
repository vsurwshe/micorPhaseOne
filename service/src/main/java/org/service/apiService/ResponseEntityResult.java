package org.service.apiService;

import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseEntityResult {

	public static Map<String,String> response = new HashMap<String, String>();
    // this method return as response entity as a bad request
	public static ResponseEntity<?> badRequest(String message){
		Date date = Calendar.getInstance().getTime();  
		response.put("time", ""+  new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(date) );
		response.put("error", message);
		return ResponseEntity.badRequest().body(response);
		// return new  ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
		// return new  ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
	}

	// this method return as response enitiy and ok status
	public static ResponseEntity<?> successResponseEntity(Object classObject){
		return ResponseEntity.ok().body(classObject);
		// return new ResponseEntity<Object>(classObject,HttpStatus.OK);
	}
}