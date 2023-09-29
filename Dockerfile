FROM openjdk:17 as build

# TODO delete copy gradle
COPY gradle gradle
COPY build/libs/subclubapp-plain.jar subclubapp-plain.jar

ENTRYPOINT ["java", "-jar", "subclubapp-plain.jar"]