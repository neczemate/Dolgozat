package neo4j.neo4j.repository;

import neo4j.neo4j.model.Book;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends Neo4jRepository<Book, Long> {

    @Query("MATCH (b:Book) WHERE b.title = $title RETURN b")
    Book findByTitle(@Param("title") String title);

    Book findByLanguage(String language);

    @Query("MATCH (au:Author)<-[a:AUTHORED]-(b:Book) RETURN b")
    List<Book> getAllBooks();

    @Query("MATCH (b:Book) WHERE NOT ()--(b) RETURN b")
    List<Book> getAllBooksNoRelationship();

    @Query("MATCH (b:Book), (a:Author) WHERE b.title = $bookTitle AND a.name = $authorName CREATE (b)-[:AUTHORED]->(a)")
    void createAuthoredRelationship(String authorName, String bookTitle);

}
