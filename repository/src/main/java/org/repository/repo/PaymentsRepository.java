package org.repository.repo;

import java.util.List;

import org.domain.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Integer> {

	boolean existsBypayId(Integer paymentId);

	// This method find Payments by payment id
	@Query(value = "select * from payments  where pay_id=:paymentId and user_id=:userId", nativeQuery = true)
	Payments findByPaymentIdAndUserId(@Param("paymentId") Integer paymentId, @Param("userId") Integer userId);

	// This method find Payments by payment id
		@Query(value = "select * from payments  where pay_id=:paymentId", nativeQuery = true)
		Payments findByPaymentId(@Param("paymentId") Integer paymentId);

	
	// This method return list of payments by profile id
	@Query(value = "select * from payments  where user_id=:userId", nativeQuery = true)
	List<Payments> findPaymentsByUserId(@Param("userId") Integer userId);

	// This method find Payments by trnsaction date and return list
	List<Payments> findBytarnsDate(String userDate);
	
	@Query(nativeQuery= true, value="SELECT DISTINCT(tarns_date) FROM payments")
	List<String> findBytarnsDate();

}
