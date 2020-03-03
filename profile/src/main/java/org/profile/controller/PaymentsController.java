package org.profile.controller;

import java.util.List;

import javax.validation.Valid;

import org.domain.model.Payments;
import org.domain.model.enu.PaymentVerified;
import org.profile.exception.UserServiceException;
import org.profile.repository.PaymentsRepository;
import org.profile.repository.ProfileRespositery;
import org.profile.service.ErrorServiceMessage;
import org.profile.service.LogService;
import org.profile.service.ResponseEntityResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/profile/{profileId}")
public class PaymentsController {

	@Autowired
	private PaymentsRepository paymentsRepo;

	@Autowired
	public ProfileRespositery profileRepo;

	// This method get payment by payment id
	@GetMapping(value = "/payment/{paymentId}")
	public ResponseEntity<?> findPaymentsById(@PathVariable(value = "profileId") Integer profileId,
			@PathVariable(value = "paymentId") Integer paymentId) {
		return this.getPaymentById(profileId, paymentId);
	}

	// This method get payment by transtion date
	@GetMapping(value = "/payment/transctionDate/{transDate}")
	public ResponseEntity<?> findPaymentsByTranscationDate(@PathVariable(value = "profileId") Integer profileId,
			@PathVariable(value = "transDate") String transctionDate) {
		return this.getPaymentByTransctionDate(profileId, transctionDate);
	}

	// This method get verify value by payment id
	@GetMapping(value = "/payment/{paymentId}/verify")
	public ResponseEntity<?> findVerifyValueByPaymentId(@PathVariable(value = "profileId") Integer profileId,
			@PathVariable(value = "paymentId") Integer paymentId) {
		return this.getVerifyValueByPaymentId(profileId, paymentId);
	}

	// This method saving payment
	@PostMapping(value = "/payment/save")
	public ResponseEntity<?> savePayments(@PathVariable(value = "profileId") Integer profileId,
			@Valid @RequestBody Payments payment) {
		return this.saveUserPayment(profileId, payment);
	}

	// This method update the payments details
	@PutMapping(value = "/payment/{paymentId}/update")
	public ResponseEntity<?> updatePayments(@PathVariable(value = "profileId") Integer profileId,@PathVariable(value = "paymentId") Integer paymentId,
			@Valid @RequestBody Payments payments) {
		return this.updatePaymnetsDetails(profileId, paymentId, payments);
	}

	// This method delete the payments details by payment id
	@DeleteMapping(value = "/payment/{paymentId}/delete")
	public ResponseEntity<?> deletePayments(@PathVariable(value = "profileId") Integer profileId,
			@PathVariable(value = "paymentId") Integer paymentId) {
		return this.deletePaymnetsDetails(profileId, paymentId);
	}

	// --------------- Custom method development
	// This method get payments by payment id & profile id
	public ResponseEntity<?> getPaymentById(Integer profileId, Integer paymentId) {
		Payments pay = null;
		try {
			this.checkProfileIsOrNot(profileId);
			this.checkPaymentIsOrNot(paymentId);
			pay = paymentsRepo.findBypayId(paymentId, profileId);
			if (pay.equals(null)) {
				ResponseEntityResult
						.badRequest(profileId + ErrorServiceMessage.NO_REC_PROFILE_WITH_PAYMENT+ paymentId);
			}

		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.badRequest(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(pay);
	}

	// This method saving the payments details
	public ResponseEntity<?> saveUserPayment(Integer profileId, @Valid Payments userPayment) {
		Payments userPay = null;
		try {
			this.checkProfileIsOrNot(profileId);
			// userPayment.setProfile(profileRepo.findByprofileId(profileId));
			userPayment.setVerify(PaymentVerified.No);
			userPayment.setVersion(0);
			userPay = paymentsRepo.saveAndFlush(userPayment);
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.badRequest(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(userPay);
	}

	// This method update the payments details
	public ResponseEntity<?> updatePaymnetsDetails(Integer profileId, Integer paymentId, Payments payments) {
		Payments userPay = null;
		try {
			this.checkProfileIsOrNot(profileId);
			this.checkPaymentIsOrNot(paymentId);
			Payments tempPayments = paymentsRepo.findBypayId(paymentId,profileId);
			// this condtion cechking version by send user and current object
			if (tempPayments.getVersion().equals(payments.getVersion())) {
				tempPayments.setAmount(payments.getAmount());
				tempPayments.setMode(payments.getMode());
				tempPayments.setTarnsDate(payments.getTarnsDate());
				tempPayments.setTransctionsId(payments.getTransctionsId());
				tempPayments.setVerify(payments.getVerify());
				tempPayments.setVersion(tempPayments.getVersion() + 1);
				userPay = paymentsRepo.saveAndFlush(tempPayments);
			} else {

				return ResponseEntityResult.badRequest(payments.getVersion()
						+ ErrorServiceMessage.PROFILE_UPDATE_WORNG_VERSION + tempPayments.getVersion());
			}
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.badRequest(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(userPay);
	}

	// This method delete payments details
	public ResponseEntity<?> deletePaymnetsDetails(Integer profileId, Integer paymentId) {
		String userDeleteMessage = null;
		try {
			this.checkProfileIsOrNot(profileId);
			this.checkPaymentIsOrNot(paymentId);
			Payments tempPayments = paymentsRepo.findBypayId(paymentId,profileId);
			if(tempPayments.equals(null)){
				throw new UserServiceException(profileId+" this profile id related with this pyment id no record found "+paymentId );
			}
			paymentsRepo.delete(tempPayments);
			userDeleteMessage = paymentId + ErrorServiceMessage.PAYMENT_DELETE_SUCCESS;
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.badRequest(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(userDeleteMessage);
	}

	// This method find the payments list by transcions date
	private ResponseEntity<?> getPaymentByTransctionDate(Integer profileId, String transctionDate) {
		List<Payments> userPayments = null;
		try {
			this.checkPaymentIsOrNot(profileId);
			userPayments = paymentsRepo.findBytarnsDate(transctionDate);
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.badRequest(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(userPayments);
	}

	// This method return verify value form payment id
	private ResponseEntity<?> getVerifyValueByPaymentId(Integer profileId, Integer paymentId) {
		PaymentVerified verifyValue = null;
		try {
			this.checkPaymentIsOrNot(profileId);
			verifyValue = paymentsRepo.findBypayId(paymentId, profileId).getVerify();
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.badRequest(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(verifyValue);
	}

	// This method checking profile is there not
	public void checkProfileIsOrNot(Integer profileId) {
		if (!profileRepo.existsById(profileId)) {
			throw new UserServiceException(profileId + ErrorServiceMessage.NO_REC_PROFILE);
		}
	}

	public void checkPaymentIsOrNot(Integer paymentId) {
		if (!paymentsRepo.existsBypayId(paymentId)) {
			throw new UserServiceException(paymentId + ErrorServiceMessage.NO_REC_PAYMENT);
		}
	}

}
