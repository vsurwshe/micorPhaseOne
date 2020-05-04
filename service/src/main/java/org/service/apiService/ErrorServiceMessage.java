package org.service.apiService;

import org.springframework.stereotype.Service;

@Service
public class ErrorServiceMessage {
	// This is central error message handling
   //----- User 	
	public static final String INVALID_TOKEN = " You are passing token is not correct for these authentications. ";
	public static final String INVALID_USER = "This user, not an authorized user. This resource can access only authorized users ";
	public static final String TOKEN_EXPIRED = " Sorry but your token time is expired";
	public static final String VERIFY_CODE_WORNG_MESSAGE=" This code is not correct verifiction code, please provide correct verification code";
	public static final String VERIFY_USER_WORNG_MESSAGE=" this user Already Verifyed";
	public static final String SUBJECT_EMAIL=" Successfully registerd user with cloud app";
	public static final String USER_LOCKED = "Sorry but you provided user cerrdtional are locked by system ";
	public static final String INVALID_CREDTIONAL = "Sorry but you provided user cerrdtional are not valid, please provide valid credtional ";


	// --- Profile
	public static final String NO_PROFILE_RECORD = "No profile record found with id ";
	public static final String NO_PROFILE_ADDRESS_RECORD_FOUND = "No address record found with profile id ";
	public static final String INVLIAD_PROFILE_VERSION = " this not correct version given, the expected version is  ";
	public static final String PROFILE_DELETE_SUCCESS = " Your profile record deleted successfully !!! ";
	public static final String PROFILE_NOT_FOUND_USER = "No user record found with profile id  ";
	public static final String PROFILE_NOT_UPDATE = "No profile record is updated successfully with id  ";
	public static final String PROFILE_USER_BALANCE_NOT_SUFFICENT = "Sorry you can not create the Profile, please make payments accroding to profile cost";

	// --- Payment
	public static final String NO_PAYMENT_RECORD = "No payment records found with id ";
	public static final String NO_PAYMENT_WITH_PROFILE_RECORD = " profile id related, no payment record found with payment id  ";
	public static final String INVLIAD_PAYMENT_UPDATE_VERSION = " this not correct version given, the expected version is  ";
	public static final String PAYMENT_DELETE_SUCCESS = " Your payment record deleted successfully !!! ";
	public static final String PAYMENT_ALREADY_VERIFIED   ="You Allredy Verified this Payment ";
	// ---- Address
	public static final String NO_ADDRESS_RECORD = "No payment records found with id ";
	public static final String NO_ADDRESS_WITH_PROFILE = "No payment records found with profile id ";

	// ---- Profile Type
	public static final String NO_PROFILETYPE_RECORD = "No profile type records found ";

	//------- Invoice
	public static final String NO_FILTER_PAYMENT_RECORDS_FOUND="Sorry no payment records found";
	
	//------- Customer
	public static final String NO_CUSTOMER_RECORDS_FOUND="There is no Customer Avaialble";
	public static final String NO_CUSTOMER_SAVE="Sorry your customer is not saved successfully";
	public static final String INVALID_CUSTOMER_VERSION="Sorry you provide the worng version, excpected ";
	public static final String INVALID_CUSTOMER_ID="Please provide correct cutsomer and profile id";
	public static final String CUSTOMER_DELETE_MESSAGE="Your Customer Deleted Successfully!";
	
	//------- Food
	public static final String NO_FOOD_RECORDS_FOUND="There is no foods records";
	public static final String NO_FOOD_RECORDS_SAVE="Sorry your food record is not saved";
	public static final String NO_FOOD_RECORDS_UPDATE_UNSUCCESSFULL="Sorry your food record is not updated successfully";
	public static final String INVALID_FOOD_VERSION="Please provide correct version, expected version is ";
	public static final String INVALID_FOOD_PROFILE_ID="Please provide correct food id and profile id";
	public static final String FOOD_DELETE_SUCCESS_MESSAGE="your Food Record deleted successfully !";
	
	//------ Hotel Tabel
	public static final String NO_HOTEL_TABEL_RECORDS_FOUND="There is no Hotel Tabel Records";
	public static final String INVALID_HOTEL_PROFILE_ID="Please provide correct hotel table id and profile id";
	public static final String NO_HOTEL_TABLE_RECORDS_SAVE="Sorry your hotel tabel record is not saved successfully";
	public static final String NO_HOTEL_TABLE_RECORDS_UPDATED="Sorry your hotel tabel record is not updated";
	public static final String INVALID_HOTEL_TABLE_AND_PROFILE_IDS="Please provide correct profile id and hotel tabel id";
	public static final String INVALID_HOTEL_TABEL_VERSION= "Please correct version of hotel table provicd, excepted version ";
	public static final String HOTEL_TABEL_DElETE_MESSAGE="your hotel table data deleted successfully !";
	
	//------- Food Invoice
	public static final String NO_FOOD_INVOICE_RECORD="Sorry there is no food invoice records";
	public static final String INVALID_FOOD_INVOICE_WITH_PROFILE_IDS="Please provide the correct profile id with food invoice id";
	public static final String NO_FOOD_INVOICE_SAVED="Sorry your food invoice is not saved successfully";
	public static final String NO_FOOD_INVOICE_UPDATED="Sorry your food invoice is not updated successfully";
	public static final String INVALID_FOOD_INVOICE_VERSION="Please provide the correct version,excpeted version is ";
	public static final String FOOD_INVOICE_DELETE_MESSAGE="Your food invoice deleted successfully!";
	
}





