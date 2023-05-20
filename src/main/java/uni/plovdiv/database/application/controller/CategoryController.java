package uni.plovdiv.database.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uni.plovdiv.database.application.dto.category.CategoryCreateDto;
import uni.plovdiv.database.application.dto.category.CategoryGetDto;
import uni.plovdiv.database.application.dto.category.CategoryUpdateDto;
import uni.plovdiv.database.application.service.CategoryService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
    public List<CategoryGetDto> getAllCategories(
            @RequestParam(required = false) String title
    ) {
        return categoryService.getAllCategories(title);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public CategoryGetDto getCategoryById(
            @PathVariable Long id
    ) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/create")
    public void createCategory(
            @RequestBody CategoryCreateDto categoryCreateDto
    ) {
        categoryService.createCategory(categoryCreateDto);
    }

    @PutMapping("/{id}")
    public void updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryUpdateDto categoryUpdateDto
    ) {
        categoryService.updateCategory(id, categoryUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(
            @PathVariable Long id
    ) {
        categoryService.deleteCategory(id);
    }
}
