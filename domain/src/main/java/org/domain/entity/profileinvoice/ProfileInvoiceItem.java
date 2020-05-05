package org.domain.entity.profileinvoice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name = "profile_invoice_item")
public class ProfileInvoiceItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profile_item_id")
	private Integer ProfileItemId;

	@NotNull
	@Column(name = "profile_item_name")
	private String ProfileItemName;

	@NotNull
	@Column(name = "profile_item_qty")
	private Integer ProfileItemQty;

	@NotNull
	@Column(name = "profile_item_price")
	private Double ProfileItemPrice;

	@NotNull
	@Column(name = "profile_item_subtotal")
	private Double ProfileItemSubTotal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_invoice_id")
	@JsonIgnore
	private ProfileInvoice ProfileInvoice;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ProfileInvoiceItem))
			return false;
		return ProfileItemId != null && ProfileItemId.equals(((ProfileInvoiceItem) o).getProfileItemId());
	}

	public ProfileInvoiceItem() {
		super();
	}

	public ProfileInvoiceItem(String profileItemName, Integer profileItemQty, Double tempCost,
			Double profileItemSubTotal, org.domain.entity.profileinvoice.ProfileInvoice tempProfileInvoice) {
		super();
		ProfileItemName = profileItemName;
		ProfileItemQty = profileItemQty;
		ProfileItemPrice = tempCost;
		ProfileItemSubTotal = profileItemSubTotal;
		ProfileInvoice = tempProfileInvoice;
	}

	public Integer getProfileItemId() {
		return ProfileItemId;
	}

	public void setProfileItemId(Integer profileItemId) {
		ProfileItemId = profileItemId;
	}

	public String getProfileItemName() {
		return ProfileItemName;
	}

	public void setProfileItemName(String profileItemName) {
		ProfileItemName = profileItemName;
	}

	public Integer getProfileItemQty() {
		return ProfileItemQty;
	}

	public void setProfileItemQty(Integer profileItemQty) {
		ProfileItemQty = profileItemQty;
	}

	public Double getProfileItemPrice() {
		return ProfileItemPrice;
	}

	public void setProfileItemPrice(Double profileItemPrice) {
		ProfileItemPrice = profileItemPrice;
	}

	public Double getProfileItemSubTotal() {
		return ProfileItemSubTotal;
	}

	public void setProfileItemSubTotal(Double profileItemSubTotal) {
		ProfileItemSubTotal = profileItemSubTotal;
	}

	@JsonIgnore
	public ProfileInvoice getProfileInvoice() {
		return ProfileInvoice;
	}

	@JsonIgnore
	public void setProfileInvoice(ProfileInvoice profileInvoice) {
		ProfileInvoice = profileInvoice;
	}
}
