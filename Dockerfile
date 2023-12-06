FROM openjdk:11
WORKDIR /app
COPY target/TestTask-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","TestTask-0.0.1-SNAPSHOT.jar"]