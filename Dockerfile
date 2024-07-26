# Build Stage
FROM maven:3.8.6-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Runtime Stage
FROM amazoncorretto:17
WORKDIR /app
# Ensure the JAR file path is correct
COPY --from=build /app/target/stock-exchange-app-0.0.1-SNAPSHOT.jar /app/stock-exchange-app.jar
ENTRYPOINT ["java", "-jar", "stock-exchange-app.jar"]
