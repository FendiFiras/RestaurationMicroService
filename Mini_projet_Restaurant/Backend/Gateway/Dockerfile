FROM openjdk:17
WORKDIR /app
COPY target/apigateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8093
ENTRYPOINT ["java", "-jar", "app.jar"]
