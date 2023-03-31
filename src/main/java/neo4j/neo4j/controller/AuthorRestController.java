package neo4j.neo4j.controller;

import neo4j.neo4j.service.AuthorService;
import neo4j.neo4j.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/add")
    public Author addAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable String id) {
        Optional authorOpt = authorService.getAuthorById(Long.parseLong(id));
        if (authorOpt.isPresent()) {
            return (Author) authorOpt.get();
        }
        throw new NoSuchElementException("No author found with given id.");
    }

    @GetMapping
    public Collection getAllAuthors() {
        return authorService.getAllNoRelationship();
    }

    @GetMapping("/count")
    public Long getCountofAuthors() {
        return authorService.getCountOfAuthors();
    }

    @DeleteMapping("/{id}")
    public String deleteAuthorById(@PathVariable String id) {
        authorService.deleteAuthor(Long.parseLong(id));
        return "Author deleted successfully";
    }

    @DeleteMapping
    public String deleteAllAuthors() {
        authorService.deleteAllAuthors();
        return "All Authors deleted successfully";
    }

}
