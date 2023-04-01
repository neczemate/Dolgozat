FROM openjdk:17-jdk-alpine3.14

COPY "../../../target/neo4j-0.0.1-SNAPSHOT.jar" "./"

CMD ["java", "-jar", "/neo4j-0.0.1-SNAPSHOT.jar"]