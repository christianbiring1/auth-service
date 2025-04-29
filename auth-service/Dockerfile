# Use official Java 17 runtime as a parent image
FROM openjdk:17-jdk-slim

# Set environment variables
ENV SPRING_PROFILES_ACTIVE=prod

# Create app directory
WORKDIR /app

# Copy Maven/Gradle wrapper files first (for caching later if you want)
# (Optional if you use maven/gradle builds directly)

# Copy the built jar into the container
COPY target/authservice-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
