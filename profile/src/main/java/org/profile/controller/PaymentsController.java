package org.profile.controller;

import java.util.List;

import javax.validation.Valid;

import org.domain.entity.Payments;
import org.domain.entity.UserDet;
import org.domain.model.enu.PaymentVerified;
import org.exception.exec.CustomeException;
import org.exception.exec.UserServiceException;
import org.repository.repo.PaymentsRepository;
import org.repository.repo.ProfileRespositery;
import org.repository.repo.UserRepository;
import org.service.apiService.ErrorServiceMessage;
import org.service.apiService.LogService;
import org.service.apiService.ResponseEntityResult;
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
//@RequestMapping(value = "/{userId}")
public class PaymentsController {

	@Autowired
	private PaymentsRepository paymentsRepo;

	@Autowired
	public ProfileRespositery profileRepo;
	
	@Autowired
	public UserRepository userRepo;

	// This method get payment by payment id
	@GetMapping(value = "/{userId}/payment/{paymentId}")
	public ResponseEntity<?> findPaymentsById(@PathVariable(value = "userId") Integer userId,
			@PathVariable(value = "paymentId") Integer paymentId) {
		return this.getPaymentById(userId, paymentId);
	}

	// This method get payment by transtion date
	@GetMapping(value = "/{userId}/payment/transctionDate/{transDate}")
	public ResponseEntity<?> findPaymentsByTranscationDate(@PathVariable(value = "userId") Integer userId,
			@PathVariable(value = "transDate") String transctionDate) {
		return this.getPaymentByTransctionDate(userId, transctionDate);
	}

	// This method get verify value by payment id
	@GetMapping(value = "/payment/{paymentId}/verify")
	public ResponseEntity<?> findVerifyValueByPaymentId(@PathVariable(value = "paymentId") Integer paymentId) {
		return this.setVerifyValueByPaymentId(paymentId);
	}

	// This method saving payment
	@PostMapping(value = "/{userId}/payment/save")
	public ResponseEntity<?> savePayments(@PathVariable(value = "userId") Integer userId,
			@Valid @RequestBody Payments payment) {
		return this.saveUserPayment(userId, payment);
	}

	// This method update the payments details
	@PutMapping(value = "/{userId}/payment/{paymentId}/update")
	public ResponseEntity<?> updatePayments(@PathVariable(value = "userId") Integer userId,@PathVariable(value = "paymentId") Integer paymentId,
			@Valid @RequestBody Payments payments) {
		return this.updatePaymnetsDetails(userId, paymentId, payments);
	}

	// This method delete the payments details by payment id
	@DeleteMapping(value = "/{userId}/payment/{paymentId}/delete")
	public ResponseEntity<?> deletePayments(@PathVariable(value = "userId") Integer userId,
			@PathVariable(value = "paymentId") Integer paymentId) {
		return this.deletePaymnetsDetails(userId, paymentId);
	}

	// --------------- Custom method development
	// This method get payments by payment id & profile id
	public ResponseEntity<?> getPaymentById(Integer userId, Integer paymentId) {
		Payments pay = null;
		try {
		    this.checkPaymentIsOrNot(paymentId);
			pay = paymentsRepo.findByPaymentIdAndUserId(paymentId, userId);
			if (pay.equals(null)) {
				throw new CustomeException(userId + ErrorServiceMessage.NO_REC_PROFILE_WITH_PAYMENT+ paymentId);
			}

		} catch (CustomeException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.notFound(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(pay);
	}

	// This method saving the payments details
	public ResponseEntity<?> saveUserPayment(Integer userId, @Valid Payments userPayment) {
		Payments userPay = null;
		try {
			userPayment.setUser(userRepo.findByuserId(userId));
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
	public ResponseEntity<?> updatePaymnetsDetails(Integer userId, Integer paymentId, Payments payments) {
		Payments userPay = null;
		try {
			this.checkPaymentIsOrNot(paymentId);
			Payments tempPayments = paymentsRepo.findByPaymentIdAndUserId(paymentId,userId);
			// this condition checking version by send user and current object
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
	public ResponseEntity<?> deletePaymnetsDetails(Integer userId, Integer paymentId) {
		String userDeleteMessage = null;
		try {
			this.checkPaymentIsOrNot(paymentId);
			Payments tempPayments = paymentsRepo.findByPaymentIdAndUserId(paymentId,userId);
			if(tempPayments.equals(null)){
				throw new UserServiceException(userId+" this profile id related with this pyment id no record found "+paymentId );
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
	private ResponseEntity<?> getPaymentByTransctionDate(Integer paymentId, String transctionDate) {
		List<Payments> userPayments = null;
		try {
			this.checkPaymentIsOrNot(paymentId);
			userPayments = paymentsRepo.findBytarnsDate(transctionDate);
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.badRequest(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(userPayments);
	}

	// This method return verify value form payment id
	private ResponseEntity<?> setVerifyValueByPaymentId(Integer paymentId) {
		UserDet resultUserDet = null;
		try {
			this.checkPaymentIsOrNot(paymentId);
			PaymentVerified verifyValue = paymentsRepo.findByPaymentId(paymentId).getVerify();
			if(verifyValue != PaymentVerified.YES) {
				UserDet userTemp= paymentsRepo.findByPaymentId(paymentId).getUser();
				Payments paymentTemp=paymentsRepo.findByPaymentId(paymentId);
				double userBalance= userTemp.getUserBalance();
				double paymentAmount=paymentTemp.getAmount();
				double mainBalance=userBalance+paymentAmount;
				userTemp.setUserBalance(mainBalance);
				paymentTemp.setVerify(PaymentVerified.YES);
				paymentsRepo.saveAndFlush(paymentTemp);
				resultUserDet = userRepo.saveAndFlush(userTemp);
			}else {
				throw new UserServiceException("You Allredy Verified this Payment "+paymentId);
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}catch (Exception e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.badRequest(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(resultUserDet);
	}

	public void checkPaymentIsOrNot(Integer paymentId) {
		if (!paymentsRepo.existsBypayId(paymentId)) {
			throw new UserServiceException(paymentId + ErrorServiceMessage.NO_REC_PAYMENT);
		}
	}

}
