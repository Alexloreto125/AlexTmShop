package AlexSpring.AlexTmShop.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime dateMessage) {
}
