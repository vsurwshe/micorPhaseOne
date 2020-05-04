package org.domain.entity.foodinvoice;

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
@Table(name = "food_invoice_item")
public class FoodInvoiceItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "food_item_id")
	private Integer FoodItemId;
	
	@NotNull
	@Column(name = "food_item_name")
	private String FoodItemName;
	
	@NotNull
	@Column(name = "food_item_qty")
	private Integer FoodItemQty;
	
	@NotNull
	@Column(name = "food_item_subtotal")
	private Double FoodItemSubTotal;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "food_invoice_id")
	@JsonIgnore
	private FoodInvoice foodInvoice;
	
	
	public FoodInvoiceItem() {
		super();
	}

	public FoodInvoiceItem(String foodItemName, Integer foodItemQty, Double foodItemSubTotal, FoodInvoice foodInvoice) {
		super();
		FoodItemName = foodItemName;
		FoodItemQty = foodItemQty;
		FoodItemSubTotal = foodItemSubTotal;
		this.foodInvoice = foodInvoice;
	}

	@Override
	public boolean equals(Object o) {
		if (this== o) {
			return true;
		}
		if(!(o instanceof FoodInvoiceItem))
			return false;
		return FoodItemId!=null && FoodItemId.equals(((FoodInvoiceItem)o).getFoodItemId()); 
	}

	public Integer getFoodItemId() {
		return FoodItemId;
	}

	public void setFoodItemId(Integer foodItemId) {
		FoodItemId = foodItemId;
	}

	public String getFoodItemName() {
		return FoodItemName;
	}

	public void setFoodItemName(String foodItemName) {
		FoodItemName = foodItemName;
	}

	public Integer getFoodItemQty() {
		return FoodItemQty;
	}

	public void setFoodItemQty(Integer foodItemQty) {
		FoodItemQty = foodItemQty;
	}

	public Double getFoodItemSubTotal() {
		return FoodItemSubTotal;
	}

	public void setFoodItemSubTotal(Double foodItemSubTotal) {
		FoodItemSubTotal = foodItemSubTotal;
	}

	public FoodInvoice getFoodInvoice() {
		return foodInvoice;
	}

	public void setFoodInvoice(FoodInvoice foodInvoice) {
		this.foodInvoice = foodInvoice;
	}
}
