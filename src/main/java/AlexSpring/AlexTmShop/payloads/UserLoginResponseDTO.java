package AlexSpring.AlexTmShop.payloads;


import java.util.UUID;

public record UserLoginResponseDTO(
        String accessToken,

        UUID userId) {
}
