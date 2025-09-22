# ---- build ----
FROM eclipse-temurin:22-jdk AS build
WORKDIR /app
COPY . .
# Asegura permisos del wrapper y construye el JAR
RUN chmod +x ./gradlew && ./gradlew clean bootJar --no-daemon

# ---- run ----
FROM eclipse-temurin:22-jre
WORKDIR /app
ENV PORT=8080
# La URI la toma del application.properties (env o fallback)
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
# Render inyecta $PORT; respetamos ese puerto
CMD ["sh", "-c", "java -Dserver.port=${PORT} -jar app.jar"]
