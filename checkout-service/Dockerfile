FROM maven:3.6.3-jdk-13 AS MAVEN_BUILD
COPY src/main/java /services/src/main/java
COPY src/main/resources/application-docker.properties /services/src/main/resources/application.properties
COPY pom.xml /services
RUN mvn -f /services/pom.xml -B -DskipTests clean package

FROM openjdk:13-jdk-oracle
COPY --from=MAVEN_BUILD /services/target/checkout-service-0.0.1-SNAPSHOT.jar /app/backend.jar
EXPOSE 8080
WORKDIR /app
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "backend.jar"]
