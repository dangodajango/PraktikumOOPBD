package uni.plovdiv.database.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uni.plovdiv.database.application.dto.brand.BrandCreateDto;
import uni.plovdiv.database.application.dto.brand.BrandUpdateDto;
import uni.plovdiv.database.application.models.Brand;
import uni.plovdiv.database.application.service.BrandService;

import java.util.List;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/all")
    public List<Brand> brands() {
        return brandService.getAllBrands();
    }

    @PostMapping("/create")
    public void createBrand(
            @RequestBody BrandCreateDto brandCreateDto
    ) {
        brandService.createBrand(brandCreateDto);
    }

    @PutMapping("/update")
    public void updateBrand(
            @RequestBody BrandUpdateDto brandUpdateDto
    ) {
        brandService.updateBrand(brandUpdateDto);
    }

    @DeleteMapping("/delete")
    public void deleteBrand(
            @RequestParam Long brandId
    ) {
        brandService.deleteBrand(brandId);
    }
}
