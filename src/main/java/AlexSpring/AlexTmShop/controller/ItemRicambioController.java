package AlexSpring.AlexTmShop.controller;

import AlexSpring.AlexTmShop.Exceptions.BadRequestException;
import AlexSpring.AlexTmShop.Exceptions.NotFoundException;
import AlexSpring.AlexTmShop.entities.ItemRicambio;
import AlexSpring.AlexTmShop.payloads.ItemRicambioDTO;
import AlexSpring.AlexTmShop.payloads.ItemRicambioResDTO;
import AlexSpring.AlexTmShop.repositories.ItemRicambioDAO;
import AlexSpring.AlexTmShop.services.ItemRicambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemRicambioController {

    @Autowired
    private ItemRicambioService itemRicambioService;

    @Autowired
    private ItemRicambioDAO itemRicambioDAO;


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemRicambioResDTO createItem(@RequestBody @Validated ItemRicambioDTO body, BindingResult validation) {

        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }

        return new ItemRicambioResDTO(this.itemRicambioService.createItem(body));

    }

    @GetMapping("/ricambi")
    public Page<ItemRicambio> getAllRicambi(@RequestParam(defaultValue = "0") int pageNumber,
                                            @RequestParam(defaultValue = "10") int pageSize,
                                            @RequestParam(defaultValue = "name") String sortBy) {
        return itemRicambioService.findAllRicambi(pageNumber, pageSize, sortBy);
    }


    @GetMapping("/{id}")
    public ItemRicambio getRicambio(@PathVariable Long id) {
        return this.itemRicambioDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

//    @GetMapping("/search/{name}")
//    public List<ItemRicambio> getRicambioName(@PathVariable String name) {
//        List<ItemRicambio> ricambi = this.itemRicambioDAO.findByNameContainingIgnoreCase(name);
//
//
//        if (ricambi.isEmpty()) {
//
//        }
//        return ricambi;
//    }

    @GetMapping("/ricambi/search")
    public List<ItemRicambio> getRicambioSearch(@RequestParam("q") String query) {
        if (query.matches("[A-Za-z0-9]+")) {
            Optional<ItemRicambio> ricambio = this.itemRicambioDAO.findByCodiceContainingIgnoreCase(query);
            if (ricambio.isPresent()) {
                return Collections.singletonList(ricambio.get());
            } else {
                List<ItemRicambio> ricambiByNome = this.itemRicambioDAO.findByNameContainingIgnoreCase(query);
                if (ricambiByNome.isEmpty()) {
                    throw new NotFoundException("Nessun ricambio trovato per il nome: " + query);
                }
                return ricambiByNome;
            }
        } else {

            throw new NotFoundException("La query non contiene solo caratteri alfanumerici: " + query);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemRicambio update(@PathVariable Long id, @RequestBody ItemRicambioDTO body) {

        return this.itemRicambioService.update(id, body);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        ItemRicambio found = itemRicambioDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        this.itemRicambioDAO.delete(found);
    }

    @PutMapping("/upload/{id}")
    public ItemRicambio uploadImage(@PathVariable Long id,@RequestParam("image") MultipartFile image) throws IOException {

        return this.itemRicambioService.uploadImage(image,id);
    }

@GetMapping("/categories/{categoryId}")
    public List<ItemRicambio> getItemsByCategoryId(@PathVariable Long categoryId){
        return itemRicambioService.getItemsByCategoryId(categoryId);
}

}
