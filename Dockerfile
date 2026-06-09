FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/java-loan-0.2.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
