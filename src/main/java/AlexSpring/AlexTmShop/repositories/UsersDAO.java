package AlexSpring.AlexTmShop.repositories;

import AlexSpring.AlexTmShop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface UsersDAO extends JpaRepository<User, UUID> {
	boolean existsByEmail(String email);
	Optional<User> findByEmail(String email);

}
