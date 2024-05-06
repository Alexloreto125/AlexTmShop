package AlexSpring.AlexTmShop.payloads;

import AlexSpring.AlexTmShop.entities.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewRoleDTO(

        @NotNull(message = "il ruolo non deve essere null")
        Role role
) {
}
