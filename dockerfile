# Etapa 1: Build
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

COPY pom.xml .
RUN mvn -q dependency:go-offline

COPY src ./src
RUN mvn -q package -DskipTests

# Etapa 2: Run
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

# Railway inyecta PORT
ENV PORT=8080

# Forzar IPv4 por si el host resuelve IPv6 y no hay ruta
ENTRYPOINT ["sh", "-c", "java -Djava.net.preferIPv4Stack=true -jar app.jar --server.port=${PORT}"]
