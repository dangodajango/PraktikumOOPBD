package uni.plovdiv.database.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.plovdiv.database.application.models.Category;
import uni.plovdiv.database.application.repositories.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void createCategory(String title, String description) {
        categoryRepository.save(Category.builder()
                .title(title)
                .description(description)
                .build()
        );
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
