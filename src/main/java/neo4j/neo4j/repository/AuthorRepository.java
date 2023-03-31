package neo4j.neo4j.repository;

import neo4j.neo4j.model.Author;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends Neo4jRepository<Author, Long> {

    @Query("MATCH (au:Author)<-[a:AUTHORED]-(b:Book) RETURN au,a,b")
    List<Author> getAllAuthorsRelationship();

    @Query("MATCH (a:Author) WHERE NOT ()--(a) RETURN a")
    List<Author> getAllAuthorsNoRelationship();

    @Query("CREATE (a:Author {name: $name}) RETURN a")
    public Author saveAuthor(@Param("name") String name);

    @Query("MATCH (a:Author) WHERE a.name = $name RETURN a")
    Author findByName(@Param("name") String name);


}
