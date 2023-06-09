package uni.plovdiv.database.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigationController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/shoe")
    public String shoe() {
        return "shoe";
    }

    @RequestMapping("/categories")
    public String categories() {
        return "categories";
    }

    @RequestMapping("/category")
    public String category() {
        return "category";
    }

    @RequestMapping("/brand")
    public String brand() {
        return "brand";
    }

    @RequestMapping("/brands")
    public String brands() {
        return "brands";
    }
}
