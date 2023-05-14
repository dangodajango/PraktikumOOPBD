package uni.plovdiv.database.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.plovdiv.database.application.models.Brand;
import uni.plovdiv.database.application.repositories.BrandRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public void createBrand(String name, String description) {
        brandRepository.save(Brand.builder()
                .name(name)
                .description(description)
                .build()
        );
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }
}
