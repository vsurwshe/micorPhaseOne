package org.repository.foodinvoice;

import org.domain.entity.foodinvoice.FoodInvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodInvoiceItemRepository extends JpaRepository<FoodInvoiceItem, Integer> {

}
