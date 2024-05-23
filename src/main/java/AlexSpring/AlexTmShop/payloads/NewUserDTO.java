package AlexSpring.AlexTmShop.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
		@NotEmpty(message = "Il nome proprio è obbligatorio")
		@Size(min = 3, max = 30, message = "Il nome proprio deve essere compreso tra i 3 e i 30 caratteri")
		String name,
		@NotNull(message = "Il phone è obbligatorio")
		@Size(min = 10, max = 10, message = "Il numero di telefono deve contenere 10 cifre")
		String phone,
		@NotEmpty(message = "L'email è obbligatoria")
		@Email(message = "L'email inserita non è valida")
		String email,
		@NotEmpty(message = "La password è obbligatoria")
		@Size(min = 8, message = "La password deve avere come minimo 8 caratteri")
		String password) {
}
