package org.service.apiService;

import org.springframework.stereotype.Service;

@Service
public class ErrorServiceMessage {
	// This is central error message handling
   //----- User 	
	public static final String NOT_PASS_CORRECT_TOKEN = " You are passing token is not correct for these authentications. ";
	public static final String NOT_VALID_USER = "This user, not an authorized user. This resource can access only authorized users ";
	public static final String TOKEN_EXPIRED = " Sorry but your token time is expired";
	public static final String VERIFY_CODE_WORNG=" This code is not correct verifiction code, please provide correct verification code";
	public static final String VERIFY_USER_WORNG=" this user Already Verifyed";
	public static final String SUBJECT_EMAIL=" Successfully registerd user with cloud app";

	// --- Profile
	public static final String NO_REC_PROFILE = "No profile record found with id ";
	public static final String PROFILE_UPDATE_WORNG_VERSION = " this not correct version given, the expected version is  ";
	public static final String PROFILE_DELETE_SUCCESS = " Your profile record deleted successfully !!! ";
	public static final String PROFILE_NOT_FOUND_ADDRESS = "No address record found with profile id ";
	public static final String PROFILE_NOT_FOUND_USER = "No user record found with profile id  ";
	public static final String PROFILE_NOT_UPDATE = "No profile record is updated successfully with id  ";
	public static final String PROFILE_USER_BALANCE_NOT_SUFFICENT = "Sorry you can not create the Profile, please make payments accroding to profile cost";

	// --- Payment
	public static final String NO_REC_PAYMENT = "No payment records found with id ";
	public static final String NO_REC_PROFILE_WITH_PAYMENT = " profile id related, no payment record found with payment id  ";
	public static final String PAYMENT_DELETE_SUCCESS = " Your payment record deleted successfully !!! ";

	// ---- Address
	public static final String NO_REC_ADDRESS = "No payment records found with id ";
	public static final String NO_REC_ADDRESS_WITH_PROFILE = "No payment records found with profile id ";

	// ---- Profile Type
	public static final String NO_REC_PROFILETYPE = "No profile type records found ";

	// This is central error message handling
	public static final String USER_LOCKED = "Sorry but you provided user cerrdtional are locked by system ";
	public static final String INVALID_CREDTIONAL = "Sorry but you provided user cerrdtional are not valid, please provide valid credtional ";

	//------- Invoice
	public static final String NO_FILTER_PAYMENT_RECORDS_FOUND="Sorry no payment records found";
	
}
