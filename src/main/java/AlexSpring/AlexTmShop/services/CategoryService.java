package AlexSpring.AlexTmShop.services;


import AlexSpring.AlexTmShop.Exceptions.BadRequestException;
import AlexSpring.AlexTmShop.Exceptions.NotFoundException;
import AlexSpring.AlexTmShop.entities.Category;
import AlexSpring.AlexTmShop.entities.ItemRicambio;
import AlexSpring.AlexTmShop.payloads.NewCategoryDTO;
import AlexSpring.AlexTmShop.repositories.CategoryDAO;
import AlexSpring.AlexTmShop.repositories.ItemRicambioDAO;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private ItemRicambioDAO itemRicambioDAO;


    public List<Category> findAllCategory() {


        return this.categoryDAO.findAll();
    }


    public Category createCategory(NewCategoryDTO body) {

        if (this.categoryDAO.existsByNameIgnoreCase(body.name())) {
            throw new BadRequestException("La categoria " + body.name() + " è già presente");

        }

        Category category= new Category(body.name(),body.description());

        return this.categoryDAO.save(category);

    }

    public Category updateCategories(Long id, NewCategoryDTO newCategoryDTO ){
        Category category= categoryDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
        if (newCategoryDTO.name()!=null){
            category.setName(newCategoryDTO.name());
        }
        return categoryDAO.save(category);

    }
    public Category uploadImage(MultipartFile img, Long id) throws IOException {
        Category found = categoryDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        String url = (String) cloudinary.uploader().upload(img.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setImage(url);
        return this.categoryDAO.save(found);
    }



    public void deleteCategory(Long categoryId) {
        Category category = categoryDAO.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Categoria con id " + categoryId + " non trovata"));

        // Recupera tutti gli item associati alla categoria
        List<ItemRicambio> items = itemRicambioDAO.findByCategory(category);

        for (ItemRicambio item : items) {
            item.setCategory(null);
        }

        // Salva gli item aggiornati nel database
        itemRicambioDAO.saveAll(items);
        // Elimina la categoria
        categoryDAO.delete(category);
    }
}
