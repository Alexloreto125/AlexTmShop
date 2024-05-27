package AlexSpring.AlexTmShop.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record FattureDTO(
                          @NotNull
                          @Min(0)
                          double importo,
                          @NotNull(message = "Il cliente è obbligatorio")
                          UUID userId,
                          @NotNull(message = "Item è obbligatorio")
                          List<Long> items
) {
}