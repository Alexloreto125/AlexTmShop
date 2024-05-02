package AlexSpring.AlexTmShop.payloads;

import java.time.LocalDateTime;

public record ErrorDTO (
        String message,
        LocalDateTime timestamp
){
}
