FROM openjdk:15-slim-buster

MAINTAINER wonder wonder@imooc.com

ADD target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]