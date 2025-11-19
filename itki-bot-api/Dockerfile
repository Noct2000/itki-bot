FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /home/api
COPY . /home/api
RUN ./mvnw clean package

# Run application
FROM eclipse-temurin:21-jdk-jammy
COPY --from=builder /home/api/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 5005
