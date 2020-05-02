package org.repository.food;

import org.domain.entity.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FoodsRepository extends JpaRepository<Food, Integer> {

	@Query(nativeQuery=true, value="SELECT * FROM `food` where `food_id`=?1")
	Food findByFoodId(Integer foodID);
}
