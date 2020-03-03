package org.profile.repository;

import java.util.List;

import org.domain.model.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentsRepository extends JpaRepository<Payments, Integer> {

	boolean existsBypayId(Integer paymentId);

	// This method find Payments by payment id
	@Query(value = "select * from payments  where profile_id=:profileId and pay_id=:paymentId", nativeQuery = true)
	Payments findBypayId(@Param("paymentId") Integer paymentId, @Param("profileId") Integer profileId);

	// This method return list of payments by profile id
	@Query(value = "select * from payments  where profile_id=:profileId", nativeQuery = true)
	List<Payments> findPaymentsByProfileId(@Param("profileId") Integer profileId);

	// This method find Payments by trnsaction date and return list
	List<Payments> findBytarnsDate(String userDate);

}
