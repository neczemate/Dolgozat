package neo4j.neo4j.controller;

import neo4j.neo4j.service.AuthorService;
import neo4j.neo4j.model.Author;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("aut-hor")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String getAllAuthor(final Model model){
        final List authors = authorService.getAllNoRelationship();
        model.addAttribute("authors",authors);
        return "author/list";
    }

    @GetMapping("/{id}")
    public String getAuthor(final Model model, final @PathVariable Long id){
        final Optional<Author> author = authorService.getAuthorById(id);
        model.addAttribute("author",author);
        return "author/edit";
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateAuthor(final Model model,
                               final @RequestParam(value ="id") Long id,
                               final @RequestParam(value ="name") String newName) {
        final Author updatedAuthor = authorService.updateAuthor(id, newName);
        model.addAttribute("author", updatedAuthor);
        return "redirect:/index";
    }

    @GetMapping("/create")
    public String createAuthorForm(final Model model) {
        return "author/create";
    }

    @PostMapping("/create")
    public String createAuthor(final Model model, final Author author) {
        final Optional<Author> savedAuthor = Optional.ofNullable(authorService.saveAuthor(author));
        model.addAttribute("author", savedAuthor);

        return "author/edit";
    }

    @GetMapping("/{id}/delete")
    public String deleteAuthor(final Model model, final @PathVariable("id") Long id){
        try {
            authorService.deleteAuthor(id);
        } catch (NotFoundException e) {
            //Ignored
        }
        final  List<Author> authors = authorService.getAllNoRelationship();
        model.addAttribute("authors", authors);
        return "redirect:/index";
    }

}
