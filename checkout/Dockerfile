FROM maven:3.6.3-jdk-13 AS MAVEN_BUILD
COPY src/main/java /services/src/main/java
COPY pom.xml /services
RUN mvn -f /services/pom.xml -B -DskipTests clean package

FROM openjdk:13-jdk-oracle
COPY --from=MAVEN_BUILD /services/target/checkout-0.0.1-SNAPSHOT.jar /app/backend.jar
WORKDIR /app
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "backend.jar"]
