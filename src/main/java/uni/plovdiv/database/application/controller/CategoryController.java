package uni.plovdiv.database.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uni.plovdiv.database.application.service.CategoryService;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @RequestMapping(value = "/categories")
    public String categories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories";
    }

    @PostMapping(value = "/category/create")
    public String createCategory(
            @RequestParam String title,
            @RequestParam String description
    ) {
        categoryService.createCategory(title, description);
        return "redirect:/categories";
    }
}
