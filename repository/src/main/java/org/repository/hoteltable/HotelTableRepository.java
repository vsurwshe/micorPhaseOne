package org.repository.hoteltable;

import org.domain.entity.hoteltabel.HotelTabel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelTableRepository extends JpaRepository<HotelTabel, Integer> {

}
