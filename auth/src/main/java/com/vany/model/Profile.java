package com.vany.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.vany.model.enu.ProfileFeature;
import com.vany.model.enu.ProfileType;



@Entity
@Table(name="profile")
public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer profileId;
	
	@NotNull
	@Column
	private String profileName;
	
	
	@NotNull
	@Column
	private ProfileType type;
	
	@Column
	private ProfileFeature features;

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

	public ProfileFeature getFeatures() {
		return features;
	}

	public void setFeatures(ProfileFeature features) {
		this.features = features;
	}
	
	


}
