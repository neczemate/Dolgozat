package neo4j.neo4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("index")
public class BookAndAuthorController {

    @GetMapping
    public String showIndexPage(Model model) {
        model.addAttribute("pageTitle", "Book and Author Page");
        return "bookandauthor/index";
    }
}

