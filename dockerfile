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

# Render inyecta PORT automáticamente
ENV PORT=8080

# Optimizaciones para producción
ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -Djava.net.preferIPv4Stack=true -Xmx512m -Xms256m -jar app.jar --server.port=${PORT}"]
