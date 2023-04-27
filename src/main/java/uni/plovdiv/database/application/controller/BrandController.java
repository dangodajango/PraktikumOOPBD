package uni.plovdiv.database.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uni.plovdiv.database.application.service.BrandService;

@RestController
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;
}
