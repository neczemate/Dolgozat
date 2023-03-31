package neo4j.neo4j.service;

import neo4j.neo4j.model.Author;
import neo4j.neo4j.model.Book;
import neo4j.neo4j.repository.AuthorRepository;
import neo4j.neo4j.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorAddToBookService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public List getAllARelationship() {
        return authorRepository.getAllAuthorsRelationship();
    }

    public List getAllBRelationship() {
        return bookRepository.getAllBooks();
    }

    public List getAllAWithoutRelationship() {
        return authorRepository.getAllAuthorsNoRelationship();
    }

    public List getAllBWithoutRelationship() {
        return bookRepository.getAllBooksNoRelationship();
    }

    public void createAuthoredRelationship(String authorName, String bookTitle) {
        bookRepository.createAuthoredRelationship(authorName, bookTitle);
    }

}
