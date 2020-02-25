package com.vany.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vany.exception.UserServiceException;
import com.vany.model.Payments;
import com.vany.model.enu.PaymentVerified;
import com.vany.repository.PaymentsRepository;
import com.vany.repository.ProfileRespositery;
import com.vany.service.ErrorServiceMessage;
import com.vany.service.LogService;

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
	public Payments findPaymentsById(@PathVariable(value = "profileId") Integer profileId,
			@PathVariable(value = "paymentId") Integer paymentId) {
		return this.getPaymentById(profileId, paymentId);
	}

	// This method get payment by transtion date
	@GetMapping(value = "/payment/transctionDate/{transDate}")
	public List<Payments> findPaymentsByTranscationDate(@PathVariable(value = "profileId") Integer profileId,
			@PathVariable(value = "transDate") String transctionDate) {
		return this.getPaymentByTransctionDate(profileId, transctionDate);
	}

	// This method get verify value by payment id
	@GetMapping(value = "/payment/{paymentId}/verify")
	public PaymentVerified findVerifyValueByPaymentId(@PathVariable(value = "profileId") Integer profileId,
			@PathVariable(value = "paymentId") Integer paymentId) {
		return this.getVerifyValueByPaymentId(profileId, paymentId);
	}

	// This method saving payment
	@PostMapping(value = "/payment/save")
	public Payments savePayments(@PathVariable(value = "profileId") Integer profileId,
			@Valid @RequestBody Payments payment) {
		return this.saveUserPayment(profileId, payment);
	}

	// This method update the payments details
	@PutMapping(value = "/payment/update")
	public Payments updatePayments(@PathVariable(value = "profileId") Integer profileId,
			@Valid @RequestBody Payments payments) {
		return this.updatePaymnetsDetails(profileId, payments);
	}

	// This method delete the payments details by payment id
	@DeleteMapping(value = "/payment/{paymentId}/delete")
	public String deletePayments(@PathVariable(value = "profileId") Integer profileId,
			@PathVariable(value = "paymentId") Integer paymentId) {
		return this.deletePaymnetsDetails(profileId, paymentId);
	}

	// --------------- Custom method development
	// This method get payments by payment id & profile id
	public Payments getPaymentById(Integer profileId, Integer paymentId) {
		Payments pay = null;
		try {
			if (this.checkingPaymentByProfileId(profileId)) {
				List<Payments> payments =profileRepo.findPaymentsByProfileId(profileId);
				// this condition checking in above payments list have a payment details by
				// provided user payment id
				if (payments.contains(paymentsRepo.findBypayId(paymentId))) {
					pay = paymentsRepo.findBypayId(paymentId);
				} else {
					throw new UserServiceException(
							profileId + ErrorServiceMessage.NO_REC_PROFILE_WITH_PAYMENT + paymentId);
				}
			}
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return pay;
	}

	// This method checking payment id is valid or not
	public boolean checkingPaymentByProfileId(Integer profileId) {
		boolean result = false;
		try {
			// this condition checking user provided profile id have payments or not
			if (!profileRepo.findPaymentsByProfileId(profileId).isEmpty()) {
				result = true;
			} else {
				throw new UserServiceException(profileId + ErrorServiceMessage.NO_REC_PROFILE);
			}
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return result;
	}

	// This method saving the payments details
	public Payments saveUserPayment(Integer profileId, @Valid Payments userPayment) {
		Payments userPay = null;
		try {
			if (this.checkingPaymentByProfileId(profileId)) {
				userPayment.setProfile(profileRepo.findByprofileId(profileId));
				userPay = paymentsRepo.saveAndFlush(userPayment);
			}

		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return userPay;
	}

	// This method update the payments details
	public Payments updatePaymnetsDetails(Integer profileId, Payments payments) {
		Payments userPay = null;
		try {
			if (this.checkingPaymentByProfileId(profileId)) {
				Payments tempPayments = paymentsRepo.findById(payments.getPayId()).orElseThrow(
						() -> new UserServiceException(payments.getPayId() + ErrorServiceMessage.NO_REC_PAYMENT));
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
					new UserServiceException(payments.getVersion() + ErrorServiceMessage.PROFILE_UPDATE_WORNG_VERSION
							+ tempPayments.getVersion());
				}
			}

		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return userPay;
	}

	// This method delete payments details
	public String deletePaymnetsDetails(Integer profileId, Integer paymentId) {
		String userDeleteMessage = null;
		try {
			if (this.checkingPaymentByProfileId(profileId)) {
				Payments tempPayments = paymentsRepo.findById(paymentId)
						.orElseThrow(() -> new UserServiceException(paymentId + ErrorServiceMessage.NO_REC_PAYMENT));
				paymentsRepo.delete(tempPayments);
				userDeleteMessage = paymentId + ErrorServiceMessage.PAYMENT_DELETE_SUCCESS;
			}

		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return userDeleteMessage;
	}

	// This method find the payments list by transcions date
	private List<Payments> getPaymentByTransctionDate(Integer profileId, String transctionDate) {
		List<Payments> userPayments = null;
		try {
			if (this.checkingPaymentByProfileId(profileId)) {
				userPayments = paymentsRepo.findBytarnsDate(transctionDate);
			}
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return userPayments;
	}

	// This method return verify value form payment id
	private PaymentVerified getVerifyValueByPaymentId(Integer profileId, Integer paymentId) {
		PaymentVerified verifyValue = null;
		try {
			if (this.checkingPaymentByProfileId(profileId)) {
				verifyValue = paymentsRepo.findBypayId(paymentId).getVerify();
			}
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
		}
		return verifyValue;
	}
}
