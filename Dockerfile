# ---- build ----
FROM eclipse-temurin:22-jdk AS build
WORKDIR /app
COPY . .
RUN chmod +x ./gradlew && ./gradlew clean bootJar --no-daemon

# ---- run ----
FROM eclipse-temurin:22-jre
WORKDIR /app
ENV PORT=8080
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
CMD ["sh","-c","java -Dserver.port=${PORT} -jar app.jar"]

