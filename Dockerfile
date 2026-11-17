FROM maven:3.6.3-openjdk-17 AS maven
WORKDIR /job4j_di
COPY . .
RUN mvn install

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=maven /job4j_di/target/main.jar app.jar
CMD ["java", "-jar", "app.jar"]