package uni.plovdiv.database.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uni.plovdiv.database.application.dto.category.CategoryCreateDto;
import uni.plovdiv.database.application.dto.category.CategoryUpdateDto;
import uni.plovdiv.database.application.models.Category;
import uni.plovdiv.database.application.service.CategoryService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/create")
    public void createCategory(
            @RequestBody CategoryCreateDto categoryCreateDto
    ) {
        categoryService.createCategory(categoryCreateDto);
    }

    @PutMapping("/update")
    public void updateCategory(
            @RequestBody CategoryUpdateDto categoryUpdateDto
    ) {
        categoryService.updateCategory(categoryUpdateDto);
    }

    @DeleteMapping("/delete")
    public void deleteCategory(
            @RequestParam Long categoryId
    ) {
        categoryService.deleteCategory(categoryId);
    }
}
