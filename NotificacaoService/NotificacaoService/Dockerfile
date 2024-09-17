FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/NotificacaoService-0.0.1-SNAPSHOT.jar /app/NotificacaoService.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "NotificacaoService.jar"]
