package AlexSpring.AlexTmShop.services;

import AlexSpring.AlexTmShop.Exceptions.NotFoundException;
import AlexSpring.AlexTmShop.entities.Category;
import AlexSpring.AlexTmShop.entities.ItemRicambio;
import AlexSpring.AlexTmShop.payloads.ItemRicambioDTO;
import AlexSpring.AlexTmShop.payloads.ItemRicambioResDTO;
import AlexSpring.AlexTmShop.repositories.CategoryDAO;
import AlexSpring.AlexTmShop.repositories.ItemRicambioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ItemRicambioService {
    private static  final String DEFAULT_IMAGE_PATH= "images/defaultItem.jpg";
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ItemRicambioDAO itemRicambioDAO;



    public Page<ItemRicambio> findAllRicambi(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return this.itemRicambioDAO.findAll(pageable);
    }

    public ItemRicambio createItem(ItemRicambioDTO body) {
        Category category= categoryDAO.findById(body.categoryID()).orElseThrow(()-> new NotFoundException(body.categoryID()));

        String imaePath= (body.image() !=null)? body.image(): DEFAULT_IMAGE_PATH;

        ItemRicambio itemRicambio = new ItemRicambio(body.name(),body.descrizione(),body.prezzo(),imaePath, category);

        return  this.itemRicambioDAO.save(itemRicambio);

    }
}
