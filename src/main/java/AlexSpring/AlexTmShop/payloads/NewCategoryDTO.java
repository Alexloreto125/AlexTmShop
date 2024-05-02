package AlexSpring.AlexTmShop.payloads;

import jakarta.validation.constraints.NotEmpty;

public record NewCategoryDTO(

        @NotEmpty(message = "E' richiesto il nome della categoria")
        String name
) {
}
