package uni.plovdiv.database.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigationController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/brands")
    public String brands() {
        return "brands";
    }

    @RequestMapping("/categories")
    public String categories() {
        return "categories";
    }

    @RequestMapping("/brand")
    public String brand() {
        return "brand";
    }
}
