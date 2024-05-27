package AlexSpring.AlexTmShop.payloads;

import AlexSpring.AlexTmShop.entities.Fatture;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record FattureResDTO(Fatture fatture) {}