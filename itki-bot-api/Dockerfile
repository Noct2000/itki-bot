FROM openjdk:21-oracle AS builder
WORKDIR /home/api
COPY . /home/api
RUN ./mvnw clean package

# Run application
FROM openjdk:21-oracle
COPY --from=builder /home/api/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 5005
