# Start with a base image containing Java runtime
FROM openjdk:17-jdk-alpine

# Add a volume to store logs
VOLUME /tmp

# Copy the jar file generated by the Maven build
COPY target/blogweb-*.jar app.jar

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "/app.jar"]
