# ---- Build (JDK 22) ----
FROM eclipse-temurin:22-jdk AS build
WORKDIR /app
COPY gradlew gradlew.bat ./
COPY gradle ./gradle
RUN chmod +x ./gradlew
COPY settings.gradle build.gradle gradle.properties* ./
RUN ./gradlew --no-daemon build -x test || true
COPY . .
RUN ./gradlew --no-daemon bootJar -x test

# ---- Runtime (JRE 22) ----
FROM eclipse-temurin:22-jre
WORKDIR /app
COPY --from=build /app/build/libs/iplacex-discografica-api-Rivas-1.jar /app/app.jar
EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
