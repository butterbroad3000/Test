
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY build.gradle /app/

COPY gradlew /app/
COPY gradle /app/gradle

RUN ./gradlew build --no-daemon || return 0

COPY . /app/

RUN ./gradlew bootJar --no-daemon

EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/app/build/libs/Testovoe-0.0.1-SNAPSHOT.jar"]
