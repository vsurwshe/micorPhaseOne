package org.repository.food;

import java.util.List;
import org.domain.entity.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FoodsRepository extends JpaRepository<Food, Integer> {

	@Query(nativeQuery=true, value="SELECT * FROM `food` where `food_id`=?1")
	Food findByFoodId(Integer foodID);
	
	@Query(nativeQuery=true, value="SELECT * FROM `food` WHERE `profile_id`=?1")
	List<Food> findByProfileId(Integer profileID);
}
