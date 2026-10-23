# ==============================================================================
# Stage 1: Build the application using JDK 25
# ==============================================================================
FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app

# 1. Copy the Maven wrapper files first to use caching
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# 2. Fix potential Windows line-ending issues and make mvnw executable
RUN tr -d '\r' < mvnw > mvnw.tmp && mv mvnw.tmp mvnw && chmod +x mvnw

# 3. Cache dependencies before copying source code
RUN ./mvnw dependency:go-offline -B

# 4. Copy the source code and compile
COPY src ./src
RUN ./mvnw -q -DskipTests package

# ==============================================================================
# Stage 2: Create a lightweight runtime image using JRE 25
# ==============================================================================
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

# Copy the compiled JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75", "-jar", "app.jar"]