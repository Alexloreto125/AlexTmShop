package AlexSpring.AlexTmShop.controller;

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

@RestController
@RequestMapping("/fatture")
public class FattureController {

    @Autowired
    private FattureService fattureService;


    @GetMapping
    private Page<Fatture> getAllInvoices(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "data") String sortBy) {
        return this.fattureService.getAllInvoices(page, size, sortBy);
    }



    @PostMapping("/purchase")
    @ResponseStatus(HttpStatus.CREATED)
    private Fatture saveInvoices(@RequestBody @Validated FattureDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.fattureService.saveInvoices(payload);
    }

}
