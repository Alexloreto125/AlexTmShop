package AlexSpring.AlexTmShop.repositories;

import AlexSpring.AlexTmShop.entities.ItemRicambio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRicambioDAO extends JpaRepository<ItemRicambio, Long> {

    boolean existsByCodiceIgnoreCase(String codice);
    Optional<ItemRicambio> findByCodiceIgnoreCase(String codice);

}
