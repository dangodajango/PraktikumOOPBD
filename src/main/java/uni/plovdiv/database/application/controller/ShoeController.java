package uni.plovdiv.database.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uni.plovdiv.database.application.dto.shoe.ShoeCreateDto;
import uni.plovdiv.database.application.dto.shoe.ShoeGetDto;
import uni.plovdiv.database.application.dto.shoe.ShoeUpdateDto;
import uni.plovdiv.database.application.service.ShoeService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/shoes")
@RequiredArgsConstructor
public class ShoeController {

    private final ShoeService shoeService;

    @GetMapping("/all")
    public List<ShoeGetDto> getAllShoes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Short minPrice,
            @RequestParam(required = false) Short maxPrice,
            @RequestParam(required = false) Long brandId
    ) {
        return shoeService.getAllShoes(name, minPrice, maxPrice, brandId);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ShoeGetDto getShoeById(
            @PathVariable Long id
    ) {
        return shoeService.getShoeById(id);
    }

    @PostMapping("/create")
    public void createShoe(
            @RequestBody ShoeCreateDto shoeCreateDto
    ) {
        shoeService.createShoe(shoeCreateDto);
    }

    @PutMapping("/{id}")
    public void updateShoe(
            @PathVariable Long id,
            @RequestBody ShoeUpdateDto shoeUpdateDto
    ) {
        shoeService.updateShoe(id, shoeUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteShoe(
            @PathVariable Long id
    ) {
        shoeService.deleteShoe(id);
    }
}
