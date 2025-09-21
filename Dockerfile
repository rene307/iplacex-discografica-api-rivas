# --- STAGE 1: build ---
FROM eclipse-temurin:22-jdk AS build
WORKDIR /app
COPY . .
# genera el bootJar
RUN ./gradlew --no-daemon clean bootJar -x test

# --- STAGE 2: runtime ---
FROM eclipse-temurin:22-jre
WORKDIR /app
# copia el jar generado (evita el -plain)
COPY --from=build /app/build/libs/*-1.jar /app/app.jar
EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
