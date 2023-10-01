FROM eclipse-temurin:17-jdk as build

COPY gradle gradle
COPY settings.gradle.kts .
COPY gradlew .
COPY gradlew.bat .
COPY build.gradle.kts .

ADD src src

RUN ./gradlew build

FROM eclipse-temurin:17-jre-alpine

COPY --from=build build/libs/subclubapp-plain.jar subclubapp-plain.jar

ENTRYPOINT ["java", "-jar", "subclubapp-plain.jar"]