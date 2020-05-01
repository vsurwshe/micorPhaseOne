package org.repository.address;

import java.util.List;

import org.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	// This method return list of Address by profile id
	@Query(value = "select * from address  where profile_id=?1", nativeQuery = true)
	List<Address> findAddressByProfileId(Integer profileId);

}
