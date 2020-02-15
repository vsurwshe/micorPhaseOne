package com.vany.SMS.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class LogService {

	// Setting logger object
	private static final Logger logger = Logger.getLogger(LogService.class);

	// ------------
	public static void setLogger(String message) {
		logger.error(message);
	}

	// ------------------
	public static void setDebbug(Object object) {
		logger.debug(object);
	}
}
