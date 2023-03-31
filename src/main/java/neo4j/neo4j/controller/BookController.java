package neo4j.neo4j.controller;

import neo4j.neo4j.service.BookService;
import neo4j.neo4j.model.Book;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("bo-ok")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getAllBook(final Model model){
        final List books = bookService.getAllNoRelationship();
        model.addAttribute("books",books);
        return "book/list";
    }

    @GetMapping("/{id}")
    public String getBook(final Model model, final @PathVariable Long id){
        final Optional<Book> book = bookService.getBookById(id);
        model.addAttribute("book",book);
        return "book/edit";
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateBook(final Model model,
                               final @RequestParam(value ="id") Long id,
                               final @RequestParam(value ="title") String newTitle,
                               final @RequestParam(value = "language") String newLanguage) {
        final Book updatedBook = bookService.updateBook(id, newTitle,newLanguage);
        model.addAttribute("book", updatedBook);
        return "redirect:/bo-ok";
    }

    @GetMapping("/create")
    public String createBookForm(final Model model) {
        return "book/create";
    }

    @PostMapping("/create")
    public String createBook(final Model model, final Book book) {
        final Optional<Book> savedBook = Optional.ofNullable(bookService.saveBook(book));
        model.addAttribute("book", savedBook);

        return "book/edit";
    }

    @GetMapping("/{id}/delete")
    public String deleteBook(final Model model, final @PathVariable("id") Long id){
        try {
            bookService.deleteBook(id);
        } catch (NotFoundException e) {
            //Ignored
        }
        final  List<Book> books = bookService.getAllNoRelationship();
        model.addAttribute("books", books);
        return "redirect:/index";
    }
}
