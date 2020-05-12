package org.repository.hoteltable;

import java.util.List;
import org.domain.entity.hoteltabel.HotelTabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HotelTableRepository extends JpaRepository<HotelTabel, Integer> {

	@Query(nativeQuery=true, value="SELECT * FROM `hotel_tabel` WHERE `hotel_table_id`=?1")
	HotelTabel findByHotelTabelId(Integer hotelTabelID);
	
	@Query(nativeQuery=true, value="SELECT * FROM `hotel_tabel` WHERE `profile_id`=?1")
	List<HotelTabel> findByProfileId(Integer profileID);
}
