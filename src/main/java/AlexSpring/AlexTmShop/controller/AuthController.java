package AlexSpring.AlexTmShop.controller;

import AlexSpring.AlexTmShop.Exceptions.BadRequestException;
import AlexSpring.AlexTmShop.payloads.NewRoleDTO;
import AlexSpring.AlexTmShop.payloads.NewRoleRespDTO;
import AlexSpring.AlexTmShop.payloads.NewUserDTO;
import AlexSpring.AlexTmShop.payloads.NewUserRespDTO;
import AlexSpring.AlexTmShop.services.AuthService;
import AlexSpring.AlexTmShop.services.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

//import riccardogulin.u5d12.payloads.UserLoginDTO;
//import riccardogulin.u5d12.payloads.UserLoginResponseDTO;


@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService authService;

	@Autowired
	private UsersService usersService;

//	@PostMapping("/login")
//	public UserLoginResponseDTO login(@RequestBody UserLoginDTO payload){
//        return new UserLoginResponseDTO(this.authService.authenticateUserAndGenerateToken(payload));
//	}

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public NewUserRespDTO saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation){
		// @Validated valida il payload in base ai validatori utilizzati nella classe NewUserDTO
		// BindingResult validation ci serve per valutare il risultato di questa validazione
		if(validation.hasErrors()) { // Se ci sono stati errori di validazione dovrei triggerare un 400 Bad Request
			throw new BadRequestException(validation.getAllErrors()); // Inviamo la lista degli errori all'Error Handler opportuno
		}
		// Altrimenti se non ci sono stati errori posso salvare tranquillamente lo user
		return new NewUserRespDTO(this.usersService.save(body).getId());
	}

	@PutMapping("/admin/{uuid}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public NewRoleRespDTO updateRole(@PathVariable  UUID uuid, @RequestBody @Validated NewRoleDTO role, BindingResult validation){
		if (validation.hasErrors()){
			throw new BadRequestException(validation.getAllErrors());
		}
		if (uuid == null) {
			throw new IllegalArgumentException("UUID nullo inserisci correttamente");
		}
		return new NewRoleRespDTO(this.authService.setRoleAdmin(uuid,role));
	}

}
