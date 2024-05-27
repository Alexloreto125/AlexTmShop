package AlexSpring.AlexTmShop.controller;

import AlexSpring.AlexTmShop.entities.Category;
import AlexSpring.AlexTmShop.repositories.FattureDAO;
import org.springframework.data.domain.Page;
import AlexSpring.AlexTmShop.Exceptions.BadRequestException;
import AlexSpring.AlexTmShop.entities.Fatture;
import AlexSpring.AlexTmShop.payloads.FattureDTO;
import AlexSpring.AlexTmShop.payloads.FattureResDTO;
import AlexSpring.AlexTmShop.services.FattureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fatture")
public class FattureController {

    @Autowired
    private FattureService fattureService;
    @Autowired
    private FattureDAO fattureDAO;



    @GetMapping("{userId}")
    public List<Fatture> findAllFatture(@PathVariable UUID userId) {
        return this.fattureDAO.findByUserId(userId);
    }



    @PostMapping("/purchase")
    @ResponseStatus(HttpStatus.CREATED)
    private Fatture saveInvoices(@RequestBody @Validated FattureDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.fattureService.saveInvoices(payload);
    }

}
