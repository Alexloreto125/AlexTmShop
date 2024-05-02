package AlexSpring.AlexTmShop.repositories;

import AlexSpring.AlexTmShop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, Long> {

}
