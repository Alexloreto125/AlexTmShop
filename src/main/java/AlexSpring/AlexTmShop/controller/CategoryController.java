package AlexSpring.AlexTmShop.controller;

import AlexSpring.AlexTmShop.Exceptions.BadRequestException;
import AlexSpring.AlexTmShop.entities.Category;
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

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Page<Category> getAllCategories(@RequestParam(defaultValue = "0") int pageNumber,
                                           @RequestParam(defaultValue = "10") int pageSize,
                                           @RequestParam(defaultValue = "name") String sortBy) {
        return categoryService.findAllCategory(pageNumber, pageSize, sortBy);
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public NewCategoryResDTO create(@RequestBody @Validated NewCategoryDTO body, BindingResult validation){
       if (validation.hasErrors()){
           throw new BadRequestException((validation.getAllErrors()));
       }

        return new NewCategoryResDTO(this.categoryService.createCategory(body));
    }


}
