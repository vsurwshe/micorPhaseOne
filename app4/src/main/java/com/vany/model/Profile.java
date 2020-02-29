package com.vany.model;

import java.util.Date;
import java.util.EnumSet;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.vany.model.enu.ProfileFeature;
import com.vany.model.enu.ProfileType;

@Entity
@Table(name = "profile")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer profileId;

	@NotNull
	@Column
	private String profileName;

	@NotNull
	@Column
	private ProfileType  type;

	@Column
	private EnumSet<ProfileFeature> features;

	@Column
	private Integer version;

	// One user have many Profile
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profile")
	@JsonIgnore
	private Set<Address> address = new HashSet<Address>();

	// One User have Mulitpal Payments
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profile")
	@JsonIgnore
	private Set<Payments> payments = new HashSet<Payments>();

	// Many payments have one user
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user", nullable = false)
	@JsonIgnore
	private UserDet user;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@JsonProperty(access = Access.WRITE_ONLY)
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	@LastModifiedDate
	@JsonIgnore
	private Date updatedAt;

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public ProfileType getType() {
		return type;
	}

	public void setType(ProfileType type) {
		this.type = type;
	}

	public EnumSet<ProfileFeature> getFeatures() {
		return features;
	}

	public void setFeatures(EnumSet<ProfileFeature> features) {
		this.features = features;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Set<Address> getAddress() {
		return address;
	}

	public void setAddress(Set<Address> address) {
		this.address = address;
	}

	public Set<Payments> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payments> payments) {
		this.payments = payments;
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

	public UserDet getUser() {
		return user;
	}

	public void setUser(UserDet user) {
		this.user = user;
	}

}
