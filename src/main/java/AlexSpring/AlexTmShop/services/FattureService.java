package AlexSpring.AlexTmShop.services;

import AlexSpring.AlexTmShop.Exceptions.NotFoundException;
import AlexSpring.AlexTmShop.entities.Fatture;
import AlexSpring.AlexTmShop.entities.ItemRicambio;
import AlexSpring.AlexTmShop.entities.User;
import AlexSpring.AlexTmShop.payloads.FattureDTO;
import AlexSpring.AlexTmShop.repositories.CategoryDAO;
import AlexSpring.AlexTmShop.repositories.FattureDAO;
import AlexSpring.AlexTmShop.repositories.ItemRicambioDAO;
import AlexSpring.AlexTmShop.repositories.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class FattureService {

    @Autowired
    private FattureDAO fattureDAO;
    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private UsersService usersService;
    @Autowired
    private  CategoryService categoryService;

    @Autowired
    private ItemRicambioDAO itemRicambioDAO;


//    public Page<Fatture> getAllInvoices(int page, int size, String sortBy) {
//        if (size > 30) size = 30;
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
//        return this.fattureDAO.findAll(pageable);
//
//    }


    public List<Fatture> findByUserId(UUID userId){
        return this.fattureDAO.findByUserId(userId);
    }

    public Fatture getInvoicesById(Long id) {
        return this.fattureDAO.findById(id).orElseThrow(()->new NotFoundException("Fattura n# "+ id + " non trovata"));
    }


    public Fatture saveInvoices(FattureDTO payload) {
        LocalDate currentDate = LocalDate.now();
        double totalPrice = 0;
        List<ItemRicambio> items = new ArrayList<>();


        for (Long itemId : payload.items()) {
            ItemRicambio item = itemRicambioDAO.findById(itemId)
                    .orElseThrow(() -> new NotFoundException("Item not found"));
            totalPrice += item.getPrezzo().doubleValue();
            items.add(item);
        }
        Fatture fatture = new Fatture(currentDate, totalPrice,usersService.findById(payload.userId()),items);
        return this.fattureDAO.save(fatture);
    }
//    public Fatture saveInvoices2(FattureDTO payload) {
//        LocalDate currentDate = LocalDate.now();
//
//        List<ItemRicambio>items  = itemRicambioDAO.findAllById(payload.items());
//        double totalPrice = items.stream()
//                .map(ItemRicambio::getPrezzo)
//                .mapToDouble(BigDecimal::doubleValue)
//                .sum();
//        System.out.println("Prezzo totale degli item: " + totalPrice);
//
//        System.out.println("ID ITEM PAYLOAD" + payload.items());
//
//        System.out.println("ITEM DAL DB"+ items);
//        Fatture fatture = new Fatture(currentDate, totalPrice,usersService.findById(payload.userId()),items);
//        return this.fattureDAO.save(fatture);
//    }

//    public Fatture saveInvoices(FattureDTO payload) {
//        LocalDate currentDate = LocalDate.now();
//        double totalPrice = 0;
//
//        List<ItemRicambio> items = new ArrayList<>();
//        for (FattureDTO.ItemQuantity itemQuantity : payload.items()) {
//            ItemRicambio item = itemRicambioDAO.findById(itemQuantity.id())
//                    .orElseThrow(() -> new NotFoundException("Item not found " ));
//            totalPrice += item.getPrezzo().doubleValue() * itemQuantity.quantity();
//            for (int i = 0; i < itemQuantity.quantity(); i++) {
//                items.add(item);
//            }
//        }
//
//        Fatture fatture = new Fatture(currentDate, totalPrice, usersService.findById(payload.userId()), items);
//        return this.fattureDAO.save(fatture);
//    }



    public void deleteInvoices(long id) {
        Fatture fatture = this.fattureDAO.findById(id);
        this.fattureDAO.delete(fatture);
    }
}
