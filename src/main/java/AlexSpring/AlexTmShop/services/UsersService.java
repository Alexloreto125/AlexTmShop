package AlexSpring.AlexTmShop.services;

import AlexSpring.AlexTmShop.Exceptions.BadRequestException;
import AlexSpring.AlexTmShop.Exceptions.NotFoundException;
import AlexSpring.AlexTmShop.entities.User;
import AlexSpring.AlexTmShop.payloads.NewUserDTO;
import AlexSpring.AlexTmShop.repositories.UsersDAO;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
public class UsersService {
	@Autowired
	private UsersDAO usersDAO;

	public Page<User> getUsers(int page, int size, String sortBy){
		if(size > 100) size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return this.usersDAO.findAll(pageable);
	}

	public User save(NewUserDTO body){
		// 1. Verifico se l'email è già in uso
		this.usersDAO.findByEmail(body.email()).ifPresent(
				// 2. Se lo è triggero un errore
				user -> {
					throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
				}
		);
		String username = body.name();
		String[] names= username.split(" ");
		StringBuilder initials= new StringBuilder();

		for (String name: names){
			initials.append(name.charAt(0));
		}
		// 3. Creo un nuovo oggetto User con i dati provenienti dal body
		User newUser = new User(body.name(), body.phone(), body.email(), body.password(),
				"https://ui-avatars.com/api/?name="+ initials.toString() );

		// 4. Salvo lo user
		return usersDAO.save(newUser);
	}

	public User findById(UUID userId){
        return this.usersDAO.findById(userId).orElseThrow(() -> new NotFoundException(String.valueOf(userId)));
	}




	public User findByIdAndUpdate(UUID userId, User modifiedUser){
		User found = this.findById(userId);
		if (modifiedUser.getName()!= null) {
			found.setName(modifiedUser.getName());
		found.setAvatarURL("https://ui-avatars.com/api/?name="+ modifiedUser.getName());
		}

		if (modifiedUser.getPhone()!=null){

        found.setPhone(modifiedUser.getPhone());
		}
		if (modifiedUser.getEmail()!=null){

        found.setEmail(modifiedUser.getEmail());
		}
		if (modifiedUser.getPassword()!=null){
        found.setPassword(modifiedUser.getPassword());

		}

		return this.usersDAO.save(found);
	}

	public void findByIdAndDelete(UUID userId){
		User found = this.findById(userId);
		this.usersDAO.delete(found);
	}

	public User findByEmail(String email) {
        return usersDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato!"));
	}

}
