package AlexSpring.AlexTmShop.payloads;

import AlexSpring.AlexTmShop.entities.Category;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Setter;

import java.math.BigDecimal;

public record ItemRicambioDTO(



        @NotEmpty(message = "E' richiesto l'inserimento del nome")
        @Size(max = 20,min = 4,message = "I caratteri del nome devono essere compresi tra 4 e 20")
        String name,

        @NotEmpty(message = "E' richiesto l'inserimento della descrizione")
        @Size(max = 50,min = 4,message = "I caratteri della descrizione devono essere compresi tra 4 e 50")
        String descrizione,

        @NotNull(message = "E' richiesto l'inserimento del prezzo")
        @DecimalMin(value = "0.0", message = "Il prezzo deve essere un numero positivo")
        BigDecimal prezzo,

        String image,

//        @NotNull(message = "E' richiesto l'inserimento della categoria")
        Long categoryID,

        @Size(max=7, message="Il codice puo avere massimo 7 cifre")
        String codice


) {
}
