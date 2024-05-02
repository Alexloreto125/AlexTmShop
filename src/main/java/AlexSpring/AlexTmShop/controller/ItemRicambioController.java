package AlexSpring.AlexTmShop.controller;

import AlexSpring.AlexTmShop.Exceptions.BadRequestException;
import AlexSpring.AlexTmShop.Exceptions.NotFoundException;
import AlexSpring.AlexTmShop.entities.Category;
import AlexSpring.AlexTmShop.entities.ItemRicambio;
import AlexSpring.AlexTmShop.payloads.ItemRicambioDTO;
import AlexSpring.AlexTmShop.payloads.ItemRicambioResDTO;
import AlexSpring.AlexTmShop.payloads.NewCategoryResDTO;
import AlexSpring.AlexTmShop.repositories.ItemRicambioDAO;
import AlexSpring.AlexTmShop.services.ItemRicambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemRicambioController {

    @Autowired
    private ItemRicambioService itemRicambioService;

    @Autowired
    private ItemRicambioDAO itemRicambioDAO;


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemRicambioResDTO createItem(@RequestBody @Validated ItemRicambioDTO body, BindingResult validation){

        if (validation.hasErrors()){
            throw  new BadRequestException(validation.getAllErrors());
        }

        return new ItemRicambioResDTO(this.itemRicambioService.createItem(body));

    }

    @GetMapping
    public Page<ItemRicambio> getAllRicambi(@RequestParam(defaultValue = "0") int pageNumber,
                                           @RequestParam(defaultValue = "10") int pageSize,
                                           @RequestParam(defaultValue = "name") String sortBy) {
        return itemRicambioService.findAllRicambi(pageNumber, pageSize, sortBy);
    }


    @GetMapping("/{id}")
    public ItemRicambio getRicambio(@PathVariable Long id){
        return this.itemRicambioDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }
}
