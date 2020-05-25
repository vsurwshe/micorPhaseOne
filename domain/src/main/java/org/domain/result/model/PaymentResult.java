package org.domain.result.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.domain.model.enu.PaymentMode;
import org.domain.model.enu.PaymentVerified;

public class PaymentResult {

	private Integer paymentId;

	private PaymentMode mode;

	private String transctionsId;

	private Double amount;

	private String tarnsDate;

	private PaymentVerified verify;

	private Integer version;
	
	private Integer userId;

	public PaymentResult(Integer paymentId, PaymentMode mode, String transctionsId, Double amount, String tarnsDate,
			PaymentVerified verify, Integer version, Integer userId) {
		super();
		this.paymentId = paymentId;
		this.mode = mode;
		this.transctionsId = transctionsId;
		this.amount = amount;
		this.tarnsDate = tarnsDate;
		this.verify = verify;
		this.version = version;
		this.userId = userId;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public PaymentMode getMode() {
		return mode;
	}

	public void setMode(PaymentMode mode) {
		this.mode = mode;
	}

	public String getTransctionsId() {
		return transctionsId;
	}

	public void setTransctionsId(String transctionsId) {
		this.transctionsId = transctionsId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getTarnsDate() {
		return tarnsDate;
	}

	public void setTarnsDate(String tarnsDate) {
		this.tarnsDate = tarnsDate;
	}

	public PaymentVerified getVerify() {
		return verify;
	}

	public void setVerify(PaymentVerified verify) {
		this.verify = verify;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
