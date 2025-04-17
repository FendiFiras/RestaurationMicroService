FROM openjdk:17-jdk-slim
LABEL authors="GIGABYTE"

EXPOSE 8082

ADD target/CommandeMicroService-0.0.1-SNAPSHOT.jar commande.jar

ENTRYPOINT ["java", "-jar", "commande.jar"]
