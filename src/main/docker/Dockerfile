FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD home-page-interface-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
