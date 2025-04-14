# Use a lightweight JDK base image
FROM openjdk:17

# Set the working directory
WORKDIR /app

# Copy the jar file into the container
COPY target/GharbiPI.jar app.jar

# Expose the application port
EXPOSE 8081

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
