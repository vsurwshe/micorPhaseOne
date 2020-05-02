package org.repository.customer;

import org.domain.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query(nativeQuery=true, value="SELECT * FROM `customer` where `customer_id`=?1")
	Customer findByCustomerID(Integer customerID); 
}
