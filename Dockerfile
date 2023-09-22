FROM openjdk:17 as build

COPY gradle gradle
COPY build/libs/subclubapp-plain.jar subclubapp-plain.jar

ENTRYPOINT ["java", "-jar", "subclubapp-plain.jar"]