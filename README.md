1.	Első lépés regisztráljon a Neo4j SandBox fiókot
      •	https://neo4j.com/sandbox/
2.	Második lépés „Your own data” menüponton válassza a „Blank Sandbox” adatbázist, ezáltal kap egy üres adatbázist.
3.	Blank Sandbox adatbázisánál megfog találni egy „Connection details” menüpontot, ahol megtalálható a szükséges adatok az adatbázishoz való csatlakozáshoz.
4.	Open gombra kattintva „Open with Browser” -t választva kap egy böngésző által kezelt adatbázist. „Authentication type”-nak válassza a „Username/password” menüpontot. A megadott információk alapján lépjen be.
5.	Bal oldalt kínált menü sávba válassza ki a „Database Information” fület majd „Use database” címnél állítsa át neo4j adatbázisra.
6.	Töltse fel az adatbázist

    CREATE (Invisible_Man:Book {title: 'Invisible Man', language: 'English'})
    CREATE (Moby_Dick:Book {title: 'Moby Dick', language: 'English'})
    CREATE (Hamlet:Book {title: 'Hamlet', language: 'English'})
    CREATE (Az_Arany_Ember:Book {title: 'Az Arany Ember', language: 'Magyar'})
    CREATE (Egri_csillagok:Book {title: 'Egri Csillagok', language: 'Magyar'})
    CREATE (A_Pal_utcai_fiuk:Book {title: 'A Pál utcai fiúk', language: 'English'})
    CREATE (Ellison:Author {name: 'Ralph Ellison'})
    CREATE (Shakespeare:Author {name: 'William Shakespeare'})
    CREATE (Melville:Author {name: 'Herman Melville'})
    CREATE (Gardonyi:Author {name: 'Gárdonyi Géza'})
    CREATE (Jokai:Author {name: 'Jókai Mór'})
    CREATE (Molnar:Author {name: 'Molnár Ferenc'})
    CREATE
    (Invisible_Man)-[:AUTHORED]->(Ellison),
    (Moby_Dick)-[:AUTHORED]->(Melville),
    (Hamlet)-[:AUTHORED]->(Shakespeare);
7.	Nyissa meg a projekt fájlt egy fejlesztői környezetbe majd application.properties fájlba írja át a adatbázishoz kapcsolódáshoz a szükséges adatokat amit a „Connection details” menüpontba talál.

    spring.neo4j.uri=bolt://1.23.456.789:1234			---> Bolt URL
    spring.neo4j.authentication.username=neo4j			---> username
    spring.neo4j.authentication.password=passoword		        ---> password
    spring.data.neo4j.database=neo4j				---> Use database
    logging.level.org.springframework.data.neo4j.cypher=trace
8.	Indítsa el a webalkalmazást és megtalálható lesz a következő porton:
      •	localhost:8080/index
