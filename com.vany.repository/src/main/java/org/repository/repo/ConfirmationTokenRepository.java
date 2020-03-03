package org.repository.repo;



import org.domain.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken , Integer> {
	ConfirmationToken findByconfirmationToken(String confirmationToken);
}
