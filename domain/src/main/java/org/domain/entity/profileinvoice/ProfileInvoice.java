package org.domain.entity.profileinvoice;

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

import org.domain.entity.user.UserDet;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "profile_invoice")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "CreatedAt", "UpdatedAt" }, allowGetters = true)
public class ProfileInvoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profile_invoice_id")
	private Integer ProfileInvoiceId;
	
	@Column(name ="profile_invoice_date")
	private String ProfileInvoiceDate;
	
	@Column(name = "profile_invoice_total")
	private Double ProfileInvoiceTotal;

	@Column(name = "version")
	private Double Version;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ProfileInvoice",orphanRemoval = true)
	private List<ProfileInvoiceItem> ProfileInvoiceItem= new ArrayList<ProfileInvoiceItem>();
	
	// Many profile invoice have one user
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "user", nullable = false)
		@JsonIgnore
		private UserDet user;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
	@CreatedDate
	@JsonIgnore
	private Date CreatedAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	@LastModifiedDate
	@JsonIgnore
	private Date UpdatedAt;

	public Integer getProfileInvoiceId() {
		return ProfileInvoiceId;
	}

	public void setProfileInvoiceId(Integer profileInvoiceId) {
		ProfileInvoiceId = profileInvoiceId;
	}

	public String getProfileInvoiceDate() {
		return ProfileInvoiceDate;
	}

	public void setProfileInvoiceDate(String profileInvoiceDate) {
		ProfileInvoiceDate = profileInvoiceDate;
	}

	public Double getProfileInvoiceTotal() {
		return ProfileInvoiceTotal;
	}

	public void setProfileInvoiceTotal(Double profileInvoiceTotal) {
		ProfileInvoiceTotal = profileInvoiceTotal;
	}

	public Double getVersion() {
		return Version;
	}

	public void setVersion(Double version) {
		Version = version;
	}

	public List<ProfileInvoiceItem> getProfileInvoiceItem() {
		return ProfileInvoiceItem;
	}

	public void setProfileInvoiceItem(List<ProfileInvoiceItem> profileInvoiceItem) {
		ProfileInvoiceItem = profileInvoiceItem;
	}

	public Date getCreatedAt() {
		return CreatedAt;
	}

	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}

	public Date getUpdatedAt() {
		return UpdatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		UpdatedAt = updatedAt;
	}

	public UserDet getUser() {
		return user;
	}

	public void setUser(UserDet user) {
		this.user = user;
	}
}
