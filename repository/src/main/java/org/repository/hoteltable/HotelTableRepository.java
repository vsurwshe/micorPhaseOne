package org.repository.hoteltable;

import org.domain.entity.hoteltabel.HotelTabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HotelTableRepository extends JpaRepository<HotelTabel, Integer> {

	@Query(nativeQuery=true, value="SELECT * FROM `hotel_tabel` WHERE `hotel_tabel_id`=?1")
	HotelTabel findByHotelTabelId(Integer hotelTabelID);
}
