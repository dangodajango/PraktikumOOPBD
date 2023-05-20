package uni.plovdiv.database.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.plovdiv.database.application.dto.shoe.ShoeCreateDto;
import uni.plovdiv.database.application.dto.shoe.ShoeGetDto;
import uni.plovdiv.database.application.dto.shoe.ShoeUpdateDto;
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

    public List<ShoeGetDto> getAllShoes(String name, Short minPrice, Short maxPrice, Long brandId) {
        return shoeRepository.findAll()
                .stream()
                .filter(shoe -> name == null || shoe.getName().contains(name))
                .filter(shoe -> brandId == null || shoe.getBrand().getId().equals(brandId))
                .filter(shoe -> minPrice == null || shoe.getPrice() >= minPrice)
                .filter(shoe -> maxPrice == null || shoe.getPrice() <= maxPrice)
                .map(this::mapToShoeGetDto)
                .toList();
    }

    public ShoeGetDto getShoeById(Long id) {
        Shoe shoe = shoeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("Shoe with ID - %s does not exist", id)));
        return mapToShoeGetDto(shoe);
    }

    private ShoeGetDto mapToShoeGetDto(Shoe shoe) {
        return ShoeGetDto.builder()
                .id(shoe.getId())
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

    public void updateShoe(Long id, ShoeUpdateDto shoeUpdateDto) {
        Shoe shoe = shoeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("Shoe with ID - %s does not exist!", id)));
        shoe.setName(shoeUpdateDto.getName());
        shoe.setURL(shoeUpdateDto.getImageURL());
        shoe.setPrice(shoeUpdateDto.getPrice());
        shoe.setMinSize(shoeUpdateDto.getMinSize());
        shoe.setMaxSize(shoeUpdateDto.getMaxSize());
        updateShoeBrandRelation(shoe, shoeUpdateDto.getBrandId());
        updateShoeCategoryRelation(shoe, shoeUpdateDto.getCategoryIds());
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
