FROM openjdk:17
WORKDIR /app
ADD target/eureka-0.0.1-SNAPSHOT.jar eureka-server.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]
