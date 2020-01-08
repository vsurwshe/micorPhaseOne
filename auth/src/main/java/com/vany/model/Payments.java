package com.vany.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vany.model.enu.PaymentMode;
import com.vany.model.enu.PaymentVerified;

@Entity
@Table(name = "payments")
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class Payments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer payId;

	@Column
	private PaymentMode mode;

	@Column
	private String transctionsId;

	@Column
	private String amount;

	@Column
	private String tarnsDate;

	@Column
	private PaymentVerified verify;

	@Column
	private Integer version;

	// Many payments have one user
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
	@CreatedDate
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	@LastModifiedDate
	private Date updatedAt;

	public Integer getPayId() {
		return payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
