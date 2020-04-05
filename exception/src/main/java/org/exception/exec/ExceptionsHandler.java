package org.exception.exec;

import java.util.Date;

import org.domain.component.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
		String errorMessageDesp= ex.getLocalizedMessage();
		if(errorMessageDesp == null) {
			errorMessageDesp=ex.toString();
		}
		ErrorMessage errorMessage =new ErrorMessage(new Date(), errorMessageDesp);
		//return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}
	
	@ExceptionHandler(value = { UserServiceException.class })
	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request) {
		String errorMessageDesp= ex.getLocalizedMessage();
		if(errorMessageDesp == null) {
			errorMessageDesp=ex.toString();
		}
		ErrorMessage errorMessage =new ErrorMessage(new Date(), errorMessageDesp);
		//return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}
	
	@ExceptionHandler(value = { CustomeException.class })
	public ResponseEntity<?> handleCustomException(CustomeException ex, WebRequest request) {
		String errorMessageDesp= ex.getLocalizedMessage();
		if(errorMessageDesp == null) {
			errorMessageDesp=ex.toString();
		}
		ErrorMessage errorMessage =new ErrorMessage(new Date(), errorMessageDesp);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	}
}
