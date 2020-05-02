package org.repository.invoice;

import org.domain.entity.invoice.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Integer> {

}
