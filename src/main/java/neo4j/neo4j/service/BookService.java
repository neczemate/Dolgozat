package neo4j.neo4j.service;

import neo4j.neo4j.model.Book;
import neo4j.neo4j.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Book getBooksByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }

    public List<Book> getAllBooks(){
        return bookRepository.getAllBooks();
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }

    public Long getCountOfBooks() {
        return bookRepository.count();
    }

    public List getAllNoRelationship() {
        return bookRepository.getAllBooksNoRelationship();
    }

    public Book updateBook(Long authorId, String newTitle,String newLanguage) {
        Book book = bookRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found"));
        book.setTitle(newTitle);
        book.setLanguage(newLanguage);
        return bookRepository.save(book);
    }

}
