package org.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name = "invoice")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_Id")
	private long invoiceId;

	@NotNull
	@Column(name = "invoice_date")
	private String invoiceDate;

	@NotNull
	@Column(name = "invoice_total_amount")
	private Double invoiceTotalAmount;

	@NotNull
	@Column(name = "invoice_sub_total_amount")
	private Double invoiceSubTotalAmount;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "invoice")
	private Payments payments;

	// One invoice have many invoice items
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "invoice",orphanRemoval = true)
	private List<InvoiceItem> invoiceItem= new ArrayList<InvoiceItem>();

	public long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Double getInvoiceTotalAmount() {
		return invoiceTotalAmount;
	}

	public void setInvoiceTotalAmount(Double invoiceTotalAmount) {
		this.invoiceTotalAmount = invoiceTotalAmount;
	}

	public Double getInvoiceSubTotalAmount() {
		return invoiceSubTotalAmount;
	}

	public void setInvoiceSubTotalAmount(Double invoiceSubTotalAmount) {
		this.invoiceSubTotalAmount = invoiceSubTotalAmount;
	}

	public List<InvoiceItem> getInvoiceItem() {
		return invoiceItem;
	}

	public void setInvoiceItem(List<InvoiceItem> list) {
		this.invoiceItem = list;
	}

	public Payments getPayments() {
		return payments;
	}

	public void setPayments(Payments payments) {
		this.payments = payments;
	}
	
	
}
