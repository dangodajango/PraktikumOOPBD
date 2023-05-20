package uni.plovdiv.database.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.plovdiv.database.application.dto.brand.BrandCreateDto;
import uni.plovdiv.database.application.dto.brand.BrandGetDto;
import uni.plovdiv.database.application.dto.brand.BrandUpdateDto;
import uni.plovdiv.database.application.models.Brand;
import uni.plovdiv.database.application.repositories.BrandRepository;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public List<BrandGetDto> getAllBrands(String name) {
        if (Objects.isNull(name)) {
            return brandRepository.findAll()
                    .stream()
                    .map(this::mapToBrandGetDto)
                    .toList();
        } else {
            return brandRepository.getBrandByTitle(name)
                    .stream()
                    .map((this::mapToBrandGetDto))
                    .toList();
        }
    }

    public BrandGetDto getBrandById(Long brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new IllegalStateException(String.format("Brand with ID - %s does not exist", brandId)));
        return mapToBrandGetDto(brand);
    }

    private BrandGetDto mapToBrandGetDto(Brand brand) {
        return BrandGetDto.builder()
                .id(brand.getId())
                .name(brand.getName())
                .description(brand.getDescription())
                .build();
    }

    public void createBrand(BrandCreateDto brandCreateDto) {
        brandRepository.save(Brand.builder()
                .name(brandCreateDto.getName())
                .description(brandCreateDto.getDescription())
                .build()
        );
    }

    public void updateBrand(Long brandId, BrandUpdateDto brandUpdateDto) {
        Brand brandToBeUpdated = brandRepository.findById(brandId)
                .orElseThrow(() -> new IllegalStateException(String.format("Brand with ID - %s does not exist", brandId)));
        updateBrand(brandToBeUpdated, brandUpdateDto);
    }

    private void updateBrand(Brand brand, BrandUpdateDto brandUpdateDto) {
        if (!isNull(brandUpdateDto.getName())) {
            brand.setName(brandUpdateDto.getName());
        }
        if (!isNull(brandUpdateDto.getDescription())) {
            brand.setDescription(brandUpdateDto.getDescription());
        }
        brandRepository.save(brand);
    }

    public void deleteBrand(Long brandId) {
        brandRepository.deleteById(brandId);
    }
}
