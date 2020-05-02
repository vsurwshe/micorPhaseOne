package org.repository.profile;

import org.domain.entity.profile.ProfileTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileTypeRepository extends JpaRepository<ProfileTypeModel , Integer> {

	@Query(nativeQuery = true,value = "SELECT `cost` FROM `profiles_set` WHERE `name`=?1")
	public Double findByType(String userType);
}
