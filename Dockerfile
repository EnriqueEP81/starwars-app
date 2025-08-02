# ----------- BUILD STAGE -----------
FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

COPY gradlew .
COPY gradle ./gradle
COPY build.gradle settings.gradle ./

RUN ./gradlew dependencies --no-daemon || return 0

COPY . .

RUN ./gradlew bootJar --no-daemon -x test

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 6969

ENTRYPOINT ["java", "-jar", "app.jar"]