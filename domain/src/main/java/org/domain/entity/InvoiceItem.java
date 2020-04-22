package org.domain.entity;

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
@Table(name = "invoice_items")
public class InvoiceItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ItemId;
	
	@NotNull
	@Column(name = "item_name")
	private String ItemName;
	
	@NotNull
	@Column(name = "item_qty")
	private Integer ItemQty;
	
	@NotNull
	@Column(name = "item_price")
	private Double ItemPrice;
	
	@NotNull
	@Column(name="item_total_price")
	private Double ItemTotalPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invoice_id", nullable = false)
	@JsonIgnore
	private Invoice invoice;

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public Integer getItemQty() {
		return ItemQty;
	}

	public void setItemQty(Integer itemQty) {
		ItemQty = itemQty;
	}

	public Double getItemPrice() {
		return ItemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		ItemPrice = itemPrice;
	}

	public Double getItemTotalPrice() {
		return ItemTotalPrice;
	}

	public void setItemTotalPrice(Double itemTotalPrice) {
		ItemTotalPrice = itemTotalPrice;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
}
