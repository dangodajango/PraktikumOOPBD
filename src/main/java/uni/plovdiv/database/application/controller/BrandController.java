package uni.plovdiv.database.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uni.plovdiv.database.application.service.BrandService;

@Controller
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @RequestMapping("/brands")
    public String brands(Model model) {
        model.addAttribute("brands", brandService.getAllBrands());
        return "brands";
    }

    @PostMapping(value = "brand/create")
    public String createBrand(
            @RequestParam String name,
            @RequestParam String description
    ) {
        brandService.createBrand(name, description);
        return "redirect:/brands";
    }

}
