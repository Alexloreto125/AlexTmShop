package AlexSpring.AlexTmShop.repositories;

import AlexSpring.AlexTmShop.entities.Fatture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FattureDAO extends JpaRepository<Fatture, Long> {

}
