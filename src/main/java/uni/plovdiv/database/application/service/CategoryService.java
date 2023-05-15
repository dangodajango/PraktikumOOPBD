package uni.plovdiv.database.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.plovdiv.database.application.dto.category.CategoryCreateDto;
import uni.plovdiv.database.application.dto.category.CategoryGetDto;
import uni.plovdiv.database.application.dto.category.CategoryUpdateDto;
import uni.plovdiv.database.application.models.Category;
import uni.plovdiv.database.application.repositories.CategoryRepository;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryGetDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> CategoryGetDto.builder()
                        .id(category.getId())
                        .title(category.getTitle())
                        .description(category.getDescription())
                        .build())
                .toList();
    }

    public void createCategory(CategoryCreateDto categoryCreateDto) {
        categoryRepository.save(Category.builder()
                .title(categoryCreateDto.getTitle())
                .description(categoryCreateDto.getDescription())
                .build()
        );
    }

    public void updateCategory(CategoryUpdateDto categoryUpdateDto) {
        Long categoryId = categoryUpdateDto.getId();
        Category categoryToBeUpdated = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException(String.format("Category with ID - %s does not exist", categoryId)));
        updateCategory(categoryToBeUpdated, categoryUpdateDto);
    }

    private void updateCategory(Category category, CategoryUpdateDto categoryUpdateDto) {
        if (!isNull(categoryUpdateDto.getTitle())) {
            category.setTitle(categoryUpdateDto.getTitle());
        }
        if (!isNull(categoryUpdateDto.getDescription())) {
            category.setDescription(categoryUpdateDto.getDescription());
        }
        categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
