package org.domain.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class UserDet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@NotNull
	@Column(name = "user_name")
	private String userName;

	@NotNull
	@Email
	@Column(unique = true, name = "user_email")
	private String userEmail;

	@NotNull
	@Column(name = "user_password")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String userPassword;

	@Column(name = "is_enabled")
	@NotNull
	private boolean isEnabled;

	@Column(name = "user_balance")
	private Double userBalance;

	@Column(name = "user_veirfy_code")
	@JsonIgnore
	private Long userVeirfyCode;
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@JsonProperty(access = Access.WRITE_ONLY)
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@JsonProperty(access = Access.WRITE_ONLY)
	private Date updatedAt;

	// One User have Mulitpal profiles
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	@JsonIgnore
	private Set<Profile> profile = new HashSet<Profile>();

	// One User have Mulitpal Payments
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	@JsonIgnore
	private Set<Payments> payments = new HashSet<Payments>();

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Double getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(Double userBalance) {
		this.userBalance = userBalance;
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

	public Set<Profile> getProfile() {
		return profile;
	}

	public void setProfile(Set<Profile> profile) {
		this.profile = profile;
	}

	public Long getUserVeirfyCode() {
		return userVeirfyCode;
	}

	public void setUserVeirfyCode(Long userVeirfyCode) {
		this.userVeirfyCode = userVeirfyCode;
	}

	public Set<Payments> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payments> payments) {
		this.payments = payments;
	}
	
	
}
