# Etapa de build
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa de execução
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/ms-cliente-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
CMD bash -c "sleep 20 && java -jar app.jar"
