package com.vany.SMS.dto;

import javax.validation.constraints.NotNull;

/*
 * This class used for the taking input form user 
 * 
 */

public class SMSMoulde {

	// "FSTSMS" is the default sender id, you can create your own custom 6 digit
	private String sender_id = "FSTSMS";
	// Message "text" to be sent
	@NotNull
	private String message;
	// Default language is "english". You can use "unicode" for other languages
	private String language = "english";
	// For promotional use "p" & For transactional use "t"
	private String route = "p";
	// You can send multiple mobile numbers seperated by comma like:
	@NotNull
	private String numbers;

	public String getSender_id() {
		return sender_id;
	}

	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
}
