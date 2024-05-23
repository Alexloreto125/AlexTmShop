package AlexSpring.AlexTmShop.controller;

import AlexSpring.AlexTmShop.Exceptions.BadRequestException;
import AlexSpring.AlexTmShop.Exceptions.NotFoundException;
import AlexSpring.AlexTmShop.entities.Category;
import AlexSpring.AlexTmShop.entities.ItemRicambio;
import AlexSpring.AlexTmShop.payloads.NewCategoryDTO;
import AlexSpring.AlexTmShop.payloads.NewCategoryResDTO;
import AlexSpring.AlexTmShop.repositories.CategoryDAO;
import AlexSpring.AlexTmShop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryDAO categoryDAO;


    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.findAllCategory();
    }


    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id){
        return this.categoryDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public NewCategoryResDTO create(@RequestBody @Validated NewCategoryDTO body, BindingResult validation){
       if (validation.hasErrors()){
           throw new BadRequestException((validation.getAllErrors()));
       }

        return new NewCategoryResDTO(this.categoryService.createCategory(body));
    }

    @PutMapping("/update/{updateId}")
    public NewCategoryResDTO update(@PathVariable Long updateId, @RequestBody NewCategoryDTO newCategoryDTO, BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());

        }
        return new NewCategoryResDTO(this.categoryService.updateCategories(updateId,newCategoryDTO));

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        Category category= this.categoryDAO.findById(id).orElseThrow(()-> new NotFoundException(id));

        this.categoryDAO.delete(category);
    }

    @PutMapping("/upload/{id}")
    public Category uploadImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) throws IOException {
        return this.categoryService.uploadImage(image,id);
    }
}
