package org.repository.foodinvoice;

import org.domain.entity.foodinvoice.FoodInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FoodInvoiceRepository extends JpaRepository<FoodInvoice, Integer> {

	@Query(nativeQuery=true, value="SELECT * FROM `food_invoice` WHERE `food_invoice_id`=?1")
	FoodInvoice findByFoodInvoiceId(Integer foodInvoiceId);
}
