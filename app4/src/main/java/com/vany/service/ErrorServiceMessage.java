package com.vany.service;

public class ErrorServiceMessage {
	// This is central error message handling	
	public static final String NOT_PASS_CORRECT_TOKEN = " You are passing token is not correct for this authentications";
	public static final String NOT_VALID_USER = " This user not autthorized user. This resource can access only authroized User";
	public static final String TOKEN_EXPIRED = " Sorry but your token time is expired";
	//--- Profile
	public static final String NO_REC_PROFILE = " This profile related  no record found";
	public static final String PROFILE_UPDATE_WORNG_VERSION = " this not Correct version, expected version";
	public static final String PROFILE_DELETE_SUCCESS = " Your profile deleted successfully !!!";
	public static final String PROFILE_NOT_FOUND_ADDRESS = " this prfile realted address not found";

	//--- Payment
	public static final String NO_REC_PAYMENT = " Payment record Not found";
	public static final String NO_REC_PROFILE_WITH_PAYMENT = " profile idd realted, no payment record found with payment id ";
	public static final String PAYMENT_DELETE_SUCCESS = " your payment deleted successfully !!!";
}
