package com.vany.SMS.dto;

/*
 * This Class Used for the serilize the output of sms api
 * 
 */

public class ResultSMS {

	private String request_id;
	private String[] message;

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String[] getMessage() {
		return message;
	}

	public void setMessage(String[] message) {
		this.message = message;
	}

}
