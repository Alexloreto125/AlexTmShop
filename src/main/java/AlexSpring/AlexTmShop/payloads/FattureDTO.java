package AlexSpring.AlexTmShop.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record FattureDTO(

                          @NotNull(message = "Il cliente è obbligatorio")
                          UUID userId,
                          @NotNull(message = "Item è obbligatorio")
                          List<ItemQuantity> items
) {
    public record ItemQuantity(
            @NotNull
            Long id,
            @NotNull
            @Min(1)
            int quantity
    ) {}

}
