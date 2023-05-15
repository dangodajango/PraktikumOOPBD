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
import uni.plovdiv.database.application.dto.brand.BrandGetDto;
import uni.plovdiv.database.application.dto.brand.BrandUpdateDto;
import uni.plovdiv.database.application.service.BrandService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
    public List<BrandGetDto> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public BrandGetDto getBrandById(
            @RequestParam Long id
    ) {
        return brandService.getBrandById(id);
    }

    @PostMapping("/create")
    public void createBrand(
            @RequestBody BrandCreateDto brandCreateDto
    ) {
        brandService.createBrand(brandCreateDto);
    }

    @PutMapping
    public void updateBrand(
            @RequestParam Long id,
            @RequestBody BrandUpdateDto brandUpdateDto
    ) {
        brandService.updateBrand(id, brandUpdateDto);
    }

    @DeleteMapping
    public void deleteBrand(
            @RequestParam Long id
    ) {
        brandService.deleteBrand(id);
    }
}
