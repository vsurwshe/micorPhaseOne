package org.domain.entity.hoteltabel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.domain.entity.profile.Profile;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "hotel_tabel")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class HotelTabel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer HotelTabelId;
	
	@Column(name="hotel_name")
	private String HotelName;
	
	@Column(name = "hotel_tabel_szie" )
	private Integer HotelTabelSize;
	
	@Column(name = "hotel_locations")
	private String HotelLocations;
	
	@Column(name = "version")
	private Double version;
	
	// Many Address Have One User
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_id", nullable = false)
	@JsonIgnore
	private Profile profile;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
	@CreatedDate
	@JsonIgnore
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	@LastModifiedDate
	@JsonIgnore
	private Date updatedAt;

	public Integer getHotelTabelId() {
		return HotelTabelId;
	}

	public void setHotelTabelId(Integer hotelTabelId) {
		HotelTabelId = hotelTabelId;
	}

	public String getHotelName() {
		return HotelName;
	}

	public void setHotelName(String hotelName) {
		HotelName = hotelName;
	}

	public Integer getHotelTabelSize() {
		return HotelTabelSize;
	}

	public void setHotelTabelSize(Integer hotelTabelSize) {
		HotelTabelSize = hotelTabelSize;
	}

	public String getHotelLocations() {
		return HotelLocations;
	}

	public void setHotelLocations(String hotelLocations) {
		HotelLocations = hotelLocations;
	}

	public Double getVersion() {
		return version;
	}

	public void setVersion(Double version) {
		this.version = version;
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

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
}
