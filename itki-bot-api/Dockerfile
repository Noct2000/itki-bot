FROM openjdk:17-oracle
COPY . .
RUN ./mvnw clean package
ENTRYPOINT ["java", "-jar", "./target/itki-bot-api-0.0.1-SNAPSHOT.jar"]
EXPOSE 5005
