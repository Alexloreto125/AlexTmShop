package AlexSpring.AlexTmShop.services;

import AlexSpring.AlexTmShop.entities.User;
import AlexSpring.AlexTmShop.payloads.NewRoleDTO;
import AlexSpring.AlexTmShop.payloads.NewRoleRespDTO;
import AlexSpring.AlexTmShop.repositories.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import riccardogulin.u5d12.entities.User;
//import riccardogulin.u5d12.exceptions.UnauthorizedException;
//import riccardogulin.u5d12.payloads.UserLoginDTO;
//import riccardogulin.u5d12.security.JWTTools;

import java.util.UUID;

@Service
public class AuthService {
	@Autowired
	private UsersService usersService;

	@Autowired
	private UsersDAO usersDAO;

//	@Autowired
//	private JWTTools jwtTools;

//	public String authenticateUserAndGenerateToken(UserLoginDTO payload){
//		// 1. Controllo le credenziali
//		// 1.1 Cerco nel db tramite l'email l'utente
//		User user = this.usersService.findByEmail(payload.email());
//		// 1.2 Verifico se la password combacia con quella ricevuta nel payload
//		if(user.getPassword().equals(payload.password())) {
//			// 2. Se è tutto OK, genero un token e lo torno
//			return jwtTools.createToken(user);
//		} else {
//			// 3. Se le credenziali invece non fossero OK --> 401 (Unauthorized)
//			throw new UnauthorizedException("Credenziali non valide! Effettua di nuovo il login!");
//		}
//
//
//	}

	public User setRoleAdmin(UUID userId,NewRoleDTO body){
		if (userId == null) {
			throw new IllegalArgumentException("UUID nullo inserisci correttamente");
		}

		User found = this.usersService.findById(userId);
		found.setRole(body.role());
		 return this.usersDAO.save(found);
	}
}
