FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/AT-0.0.1-SNAPSHOT.jar /app/AT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "AT.jar"]
