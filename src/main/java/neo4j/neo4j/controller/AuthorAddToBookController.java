package neo4j.neo4j.controller;

import neo4j.neo4j.model.Author;
import neo4j.neo4j.model.Book;
import neo4j.neo4j.service.AuthorAddToBookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("author-book")
public class AuthorAddToBookController {

    private final AuthorAddToBookService authorAddToBookService;


    public AuthorAddToBookController(AuthorAddToBookService authorAddToBookService) {
        this.authorAddToBookService = authorAddToBookService;
    }

    @GetMapping
    public String getAllData(final Model model){
        final List authors = authorAddToBookService.getAllARelationship();
        model.addAttribute("authors",authors);
        final List noauthors = authorAddToBookService.getAllAWithoutRelationship();
        model.addAttribute("noauthors",noauthors);
        final List books = authorAddToBookService.getAllBRelationship();
        model.addAttribute("books",books);
        final List nobooks = authorAddToBookService.getAllBWithoutRelationship();
        model.addAttribute("nobooks",nobooks);
        return "authorbook/list";
    }

    @PostMapping("/add-relationship")
    public String createAuthoredRelationship(@RequestParam("authorName") String authorName, @RequestParam("bookTitle") String bookTitle) {
        authorAddToBookService.createAuthoredRelationship(authorName, bookTitle);
        return "redirect:/author-book";
    }
}
