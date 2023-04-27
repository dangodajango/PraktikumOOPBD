package uni.plovdiv.database.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uni.plovdiv.database.application.service.ShoeService;

@RestController
@RequiredArgsConstructor
public class ShoeController {

    private final ShoeService shoeService;
}
