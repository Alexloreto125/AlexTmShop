package AlexSpring.AlexTmShop.repositories;

import AlexSpring.AlexTmShop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryDAO extends JpaRepository<Category, Long> {

    boolean existsByNameIgnoreCase(String name);
    Optional<Category> findByNameIgnoreCase(String name);

}
