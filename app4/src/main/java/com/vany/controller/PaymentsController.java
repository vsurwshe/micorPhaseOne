package com.vany.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vany.exception.UserServiceException;
import com.vany.model.Payments;
import com.vany.repository.PaymentsRepository;
import com.vany.service.ErrorServiceMessage;
import com.vany.service.LogService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/profile/{profileId}")
public class PaymentsController {
	
	@Autowired
	private PaymentsRepository paymentsRepo;
	
	// This method get payment by payment id	
	@GetMapping(value="/payment/{paymentId}")
	public Payments findPaymentsById(@PathVariable Integer profileId, @PathVariable Integer paymentId) {
		return this.getPaymentById(profileId,paymentId);
	}
	
	// This method saving payment	
	@PostMapping(value = "/payment/save")
	public Payments savePayments(@PathVariable Integer profileId, @Valid @RequestBody Payments payment) {
		return this.saveUserPayment(profileId,payment);
	}
	
	// --------------- Custom method development
	// This method get payments by payment id & profile id	
	public Payments getPaymentById(Integer profileId, Integer paymentId) {
		Payments pay=null;
		try {
			if(this.checkingPaymentByProfileId(profileId)) {
				List<Payments> payments=new ProfileController().getPaymentsByProfileId(profileId);
				// this condition checking in above payments list have a payment details by provided user payment id 
				if(payments.contains(paymentsRepo.findBypayId(paymentId))) {
					   pay=paymentsRepo.findBypayId(paymentId);
				   }else {
					   throw new UserServiceException(profileId+ErrorServiceMessage.NO_REC_PROFILE_WITH_PAYMENT+paymentId);
				   }
			}
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return pay;
	}
	
	public boolean checkingPaymentByProfileId(Integer profileId) {
		boolean result=false;
		try {
			// this condition checking user provided profile id have payments or not			
			if(!new ProfileController().getPaymentsByProfileId(profileId).isEmpty()) {
				result=true;
			}else {
				throw new UserServiceException(profileId+ErrorServiceMessage.NO_REC_PROFILE);
			}
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return result;
	}
	
	private Payments saveUserPayment(Integer profileId, @Valid Payments userPayment) {
		// TODO Auto-generated method stub
		return null;
	}

}
