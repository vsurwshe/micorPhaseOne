package org.repository.repo;



import org.domain.entity.ProfileTypeModel;
import org.domain.model.enu.ProfileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileTypeRepository extends JpaRepository<ProfileTypeModel , Integer> {

	@Query(nativeQuery = true,value = "SELECT `cost` FROM `profiles_set` WHERE `type`=?1")
	public Double findByType(ProfileType userType);
}
