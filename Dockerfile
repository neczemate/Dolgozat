FROM openjdk:17-jdk-alpine3.14

COPY "./target/neo4j.jar" "/application/neo4j.jar"

CMD ["java", "-jar", "/application/neo4j.jar"]