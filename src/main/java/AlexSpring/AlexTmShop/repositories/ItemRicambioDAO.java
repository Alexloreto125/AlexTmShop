package AlexSpring.AlexTmShop.repositories;

import AlexSpring.AlexTmShop.entities.Category;
import AlexSpring.AlexTmShop.entities.ItemRicambio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRicambioDAO extends JpaRepository<ItemRicambio, Long> {

    boolean existsByCodiceIgnoreCase(String codice);
    Optional<ItemRicambio> findByCodiceContainingIgnoreCase(String codice);

    List<ItemRicambio> findByNameContainingIgnoreCase(String name);

    List<ItemRicambio> findByCategoryIdOrderByNameAsc(Long categoryId);

    List<ItemRicambio> findByCategory(Category category);
    List<ItemRicambio> findAllById(Iterable<Long> ids);

}
