package org.domain.entity.foodinvoice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.domain.entity.profile.Profile;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "food_invoice")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class FoodInvoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "food_invoice_id")
	private Integer FoodInvoiceId;
	
	@Column(name ="food_invoice_date")
	private String FoodInvoiceDate;
	
	@Column(name = "food_invoice_total")
	private Double FoodInvoiceTotal;

	@Column(name = "version")
	private Double version;
	
	// Many Food Invoice Have One Profile
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_id", nullable = false)
	@JsonIgnore
	private Profile profile;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "foodInvoice",orphanRemoval = true)
	private List<FoodInvoiceItem> foodInvoiceItem= new ArrayList<FoodInvoiceItem>();
	
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

	public Integer getFoodInvoiceId() {
		return FoodInvoiceId;
	}

	public void setFoodInvoiceId(Integer foodInvoiceId) {
		FoodInvoiceId = foodInvoiceId;
	}

	public String getFoodInvoiceDate() {
		return FoodInvoiceDate;
	}

	public void setFoodInvoiceDate(String foodInvoiceDate) {
		FoodInvoiceDate = foodInvoiceDate;
	}

	public Double getFoodInvoiceTotal() {
		return FoodInvoiceTotal;
	}

	public void setFoodInvoiceTotal(Double foodInvoiceTotal) {
		FoodInvoiceTotal = foodInvoiceTotal;
	}

	public Double getVersion() {
		return version;
	}

	public void setVersion(Double version) {
		this.version = version;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<FoodInvoiceItem> getFoodInvoiceItem() {
		return foodInvoiceItem;
	}

	public void setFoodInvoiceItem(List<FoodInvoiceItem> foodInvoiceItem) {
		this.foodInvoiceItem = foodInvoiceItem;
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
