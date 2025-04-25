FROM openjdk:17
WORKDIR /app
COPY target/reservation-microservice-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]
