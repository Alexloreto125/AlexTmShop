package AlexSpring.AlexTmShop.repositories;

import AlexSpring.AlexTmShop.entities.Fatture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FattureDAO extends JpaRepository<Fatture, Long> {

    List<Fatture> findByUserId(UUID userId);

}
