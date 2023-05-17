package uni.plovdiv.database.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.plovdiv.database.application.dto.shoe.ShoeCreateDto;
import uni.plovdiv.database.application.dto.shoe.ShoeGetDto;
import uni.plovdiv.database.application.models.Brand;
import uni.plovdiv.database.application.models.Category;
import uni.plovdiv.database.application.models.Shoe;
import uni.plovdiv.database.application.repositories.BrandRepository;
import uni.plovdiv.database.application.repositories.CategoryRepository;
import uni.plovdiv.database.application.repositories.ShoeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoeService {

    private final ShoeRepository shoeRepository;

    private final BrandRepository brandRepository;

    private final CategoryRepository categoryRepository;

    public List<ShoeGetDto> getAllShoes() {
        return shoeRepository.findAll().stream()
                .map(shoe -> ShoeGetDto.builder()
                        .name(shoe.getName())
                        .imageURL(shoe.getURL())
                        .price(shoe.getPrice())
                        .maxSize(shoe.getMaxSize())
                        .minSize(shoe.getMinSize())
                        .brandId(shoe.getBrand().getId())
                        .categoryIds(shoe.getCategories().stream()
                                .map(Category::getId)
                                .toList())
                        .build())
                .toList();
    }

    public ShoeGetDto getShoeById(Long id) {
        Shoe shoe = shoeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("Shoe with ID - %s does not exist", id)));
        return ShoeGetDto.builder()
                .name(shoe.getName())
                .imageURL(shoe.getURL())
                .price(shoe.getPrice())
                .maxSize(shoe.getMaxSize())
                .minSize(shoe.getMinSize())
                .brandId(shoe.getBrand().getId())
                .categoryIds(shoe.getCategories().stream()
                        .map(Category::getId)
                        .toList())
                .build();
    }

    public void createShoe(ShoeCreateDto shoeCreateDto) {
        Shoe shoe = Shoe.builder()
                .name(shoeCreateDto.getName())
                .URL(shoeCreateDto.getImageURL())
                .price(shoeCreateDto.getPrice())
                .maxSize(shoeCreateDto.getMaxSize())
                .minSize(shoeCreateDto.getMinSize())
                .build();
        updateShoeBrandRelation(shoe, shoeCreateDto.getBrandId());
        updateShoeCategoryRelation(shoe, shoeCreateDto.getCategoryIds());
        shoeRepository.save(shoe);
    }

    private void updateShoeBrandRelation(Shoe shoe, Long brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new IllegalStateException(String.format("Brand with ID - %s does not exist!", brandId)));
        shoe.setBrand(brand);
    }

    private void updateShoeCategoryRelation(Shoe shoe, List<Long> categoryIds) {
        List<Category> categories = categoryRepository.findAllById(categoryIds);
        shoe.setCategories(categories);
    }

    public void deleteShoe(Long shoeId) {
        shoeRepository.deleteById(shoeId);
    }
}
