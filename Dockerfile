# Use official Maven image for building
FROM maven:3.8.4-openjdk-17-slim AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Production stage
FROM openjdk:17-jdk-slim

# Copy the built jar file
COPY --from=build /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]