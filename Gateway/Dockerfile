FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app
COPY ./src ./src
COPY ./pom.xml .
RUN mvn clean package

FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} eureka-gateway.jar
ENTRYPOINT ["java","-jar","/eureka-gateway.jar"]