package org.repository.food;

import org.domain.entity.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodsRepository extends JpaRepository<Food, Integer> {

}
