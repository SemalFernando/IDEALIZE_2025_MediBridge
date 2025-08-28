# Stage 1: build the jar
FROM maven:3.9.0-eclipse-temurin-17 AS builder
WORKDIR /workspace

# copy maven files and source
COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src ./src

# build (skip tests)
RUN mvn -B -DskipTests package

# Stage 2: runtime image
FROM eclipse-temurin:17-jre
WORKDIR /app

# copy built jar
COPY --from=builder /workspace/target/*.jar app.jar

# expose port
EXPOSE 8080

# run the app, use Render's PORT env var
CMD ["sh", "-c", "java -jar /app/app.jar --server.port=$PORT"]
