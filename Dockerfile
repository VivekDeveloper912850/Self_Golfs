# Build stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jdk-jammy
COPY --from=build /app/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]