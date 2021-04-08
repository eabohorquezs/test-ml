FROM maven:3.6.3-jdk-8 AS build-env
WORKDIR /usr/src/app

COPY pom.xml ./
RUN mvn dependency:go-offline

COPY . ./
RUN mvn package

FROM openjdk:8-jre-alpine as run-env

WORKDIR /usr/src/app

COPY --from=build-env /usr/src/app/target/ipmonitor-1.0-SNAPSHOT.jar ./ipmonitor-1.0-SNAPSHOT.jar
CMD ["/usr/bin/java", "-jar", "ipmonitor-1.0-SNAPSHOT.jar"]