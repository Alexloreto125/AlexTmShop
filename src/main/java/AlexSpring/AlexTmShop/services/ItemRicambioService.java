package AlexSpring.AlexTmShop.services;

import AlexSpring.AlexTmShop.Exceptions.NotFoundException;
import AlexSpring.AlexTmShop.entities.Category;
import AlexSpring.AlexTmShop.entities.ItemRicambio;
import AlexSpring.AlexTmShop.payloads.ItemRicambioDTO;
import AlexSpring.AlexTmShop.repositories.CategoryDAO;
import AlexSpring.AlexTmShop.repositories.ItemRicambioDAO;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ItemRicambioService {
    private static final String DEFAULT_IMAGE_PATH = "images/defaultItem.jpg";
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ItemRicambioDAO itemRicambioDAO;
    @Autowired
    private Cloudinary cloudinary;


    public Page<ItemRicambio> findAllRicambi(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return this.itemRicambioDAO.findAll(pageable);
    }

    public ItemRicambio createItem(ItemRicambioDTO body) {
        Category category = categoryDAO.findById(body.categoryID()).orElseThrow(() -> new NotFoundException(body.categoryID()));

        String imaePath = (body.image() != null) ? body.image() : DEFAULT_IMAGE_PATH;

        ItemRicambio itemRicambio = new ItemRicambio(body.name(), body.descrizione(), body.prezzo(), imaePath, category);

        return this.itemRicambioDAO.save(itemRicambio);

    }

    public ItemRicambio update(Long id, ItemRicambioDTO body) {
        ItemRicambio found = itemRicambioDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

        if (body.name() != null) {
            found.setName(body.name());
        }
        if (body.descrizione() != null) {
            found.setDescrizione(body.descrizione());
        }
        if (body.prezzo() != null) {
            found.setPrezzo(body.prezzo());
        }

        if (body.image() != null) {
            found.setImage(body.image());
        }
        if (body.categoryID() != null) {
            Category category = categoryDAO.findById(body.categoryID()).orElseThrow(() -> new NotFoundException(body.categoryID()));
            found.setCategory(category);
        }

        return this.itemRicambioDAO.save(found);
    }


    public ItemRicambio uploadImage(MultipartFile img, Long id) throws IOException {
        ItemRicambio found= itemRicambioDAO.findById(id).orElseThrow(()->new NotFoundException(id));
        String url= (String) cloudinary.uploader().upload(img.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setImage(url);
        return this.itemRicambioDAO.save(found);
    }
}
