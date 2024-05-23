package AlexSpring.AlexTmShop.payloads;

import jakarta.validation.constraints.NotEmpty;
import org.aspectj.weaver.ast.Not;

public record NewCategoryDTO(

        @NotEmpty(message = "E' richiesto il nome della categoria")
        String name,

        @NotEmpty(message = "E' richiesta una descrizione")
        String description
) {
}
