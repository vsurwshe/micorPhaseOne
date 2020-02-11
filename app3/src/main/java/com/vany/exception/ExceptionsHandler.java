package com.vany.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vany.model.ErrorMessage;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
		
		String errorMessageDesp= ex.getLocalizedMessage();
		
		if(errorMessageDesp == null) {
			errorMessageDesp=ex.toString();
		}
		ErrorMessage errorMessage =new ErrorMessage(new Date(), errorMessageDesp);
		
		return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = { UserServiceException.class })
	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request) {
		
		String errorMessageDesp= ex.getLocalizedMessage();
		
		if(errorMessageDesp == null) {
			errorMessageDesp=ex.toString();
		}
		ErrorMessage errorMessage =new ErrorMessage(new Date(), errorMessageDesp);
		
		return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
