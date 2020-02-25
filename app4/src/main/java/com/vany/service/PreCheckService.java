package com.vany.service;

import com.vany.exception.UserServiceException;
import com.vany.repository.PaymentsRepository;
import com.vany.repository.ProfileRespositery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PreCheckService{

    @Autowired
    public ProfileRespositery profileRepo;
    
    @Autowired
    public PaymentsRepository paymentRepo;

    public String checkProfileIsOrNot(Integer profileId) {
       boolean pr= profileRepo.existsById(profileId);
        System.out.println(pr);
        // if(!profileRepo.existsById(profileId)){
        //     throw new UserServiceException(profileId+ErrorServiceMessage.NO_REC_PROFILE);
        // }
        return null;
    }

	public void checkPaymentIsOrNot(Integer paymentId) {
		if (!paymentRepo.existsBypayId(paymentId)) {
			throw new UserServiceException(paymentId + ErrorServiceMessage.NO_REC_PAYMENT);
		}
	}

}