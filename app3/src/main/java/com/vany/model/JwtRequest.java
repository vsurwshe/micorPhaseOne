package com.vany.model;

import java.io.Serializable;



public class JwtRequest implements Serializable{

	private static final long serialVersionUID = 1L;

    private String userEmail;
	private String userPassword;
	
	
	public JwtRequest() {
		super();
	}


	public JwtRequest(String userEmail, String userPassword) {
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
	
	
	
}
