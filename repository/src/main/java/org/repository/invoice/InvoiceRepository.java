package org.repository.invoice;

import org.domain.entity.invoice.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository  extends JpaRepository<Invoice, Long> {

}
