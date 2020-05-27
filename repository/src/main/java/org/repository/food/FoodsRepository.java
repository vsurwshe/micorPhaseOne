package org.repository.food;

import java.util.List;
import org.domain.entity.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FoodsRepository extends JpaRepository<Food, Integer> {

	// this function used for the find food by id
	@Query(nativeQuery=true, value="SELECT * FROM `food` where `food_id`=?1")
	Food findByFoodId(Integer foodID);
	
	// get the list of foods by profile id
	@Query(nativeQuery=true, value="SELECT * FROM `food` WHERE `profile_id`=?1")
	List<Food> findByProfileId(Integer profileID);
	
	// this function used for the get the count of food by profile id
	@Query(nativeQuery=true, value="SELECT COUNT(*) FROM `food` WHERE `profile_id`=?1")
	Integer findByProfileIdGetCount(Integer profileID);

}
