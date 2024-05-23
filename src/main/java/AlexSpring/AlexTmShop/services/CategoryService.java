package AlexSpring.AlexTmShop.services;


import AlexSpring.AlexTmShop.Exceptions.BadRequestException;
import AlexSpring.AlexTmShop.Exceptions.NotFoundException;
import AlexSpring.AlexTmShop.entities.Category;
import AlexSpring.AlexTmShop.entities.ItemRicambio;
import AlexSpring.AlexTmShop.payloads.NewCategoryDTO;
import AlexSpring.AlexTmShop.repositories.CategoryDAO;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private Cloudinary cloudinary;


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

}
