package AlexSpring.AlexTmShop.controller;

import AlexSpring.AlexTmShop.entities.User;
import AlexSpring.AlexTmShop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@GetMapping
//	@PreAuthorize("hasAuthority('ADMIN')") // PreAuthorize serve per poter dichiarare delle regole di accesso
	// all'endpoint basandoci sul ruolo dell'utente. In questo caso solo gli ADMIN possono accedere
	public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
								  @RequestParam(defaultValue = "10") int size,
								  @RequestParam(defaultValue = "id") String sortBy) {
		return this.usersService.getUsers(page, size, sortBy);
	}

	@GetMapping("/me")
	public User getProfile(@AuthenticationPrincipal User currentAuthenticatedUser){
		// @AuthenticationPrincipal mi consente di accedere all'utente attualmente autenticato
		// Questa cosa è resa possibile dal fatto che precedentemente a questo endpoint (ovvero nel JWTFilter)
		// ho estratto l'id dal token e sono andato nel db per cercare l'utente ed "associarlo" a questa richiesta
		return currentAuthenticatedUser;
	}

	@PutMapping("/me")
	public User updateProfile(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody User updatedUser){
		return this.usersService.findByIdAndUpdate(currentAuthenticatedUser.getId(), updatedUser);
	}

	@DeleteMapping("/me")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProfile(@AuthenticationPrincipal User currentAuthenticatedUser){
		this.usersService.findByIdAndDelete(currentAuthenticatedUser.getId());
	}


	@GetMapping("/{userId}")
	public User findById(@PathVariable UUID userId){
		return this.usersService.findById(userId);
	}

	@PutMapping("/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public User findByIdAndUpdate(@PathVariable UUID userId, @RequestBody User body){
		return this.usersService.findByIdAndUpdate(userId, body);
	}

	@DeleteMapping("/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void findByIdAndDelete(@PathVariable UUID userId){
		this.usersService.findByIdAndDelete(userId);
	}

}
