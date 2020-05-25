package org.repository.profileinvoice;

import org.domain.entity.profileinvoice.ProfileInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfileInvoiceRepository extends JpaRepository<ProfileInvoice, Integer> {

	@Query(nativeQuery=true,value="SELECT * FROM `profile_invoice` WHERE `profile_invoice_id`=?1")
	ProfileInvoice findByProfileInvoiceId(Integer profileInvoiceID);
	
	@Query(nativeQuery=true,value="select count(*) from profile_invoice where profile_invoice_date=?1 and user=?2")
	Integer existsProfileInvoiceDate(String invoiceDate,Integer userID);
}
