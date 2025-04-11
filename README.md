
# 🍽️ Restauration - Microservices Training Project

## 📅 About the Project
**Restauration** est un projet d'apprentissage visant à familiariser les étudiants avec l'**architecture microservices** à travers une application de gestion de commandes dans un restaurant. Le projet est développé en mode **hands-on** pour offrir une expérience concrète de création, d'organisation et de communication entre services distribués.

## 🚀 Project Architecture
```
Microservices :
├── Gateway               (8093)
├── CommandeMicroService (8082)
├── UserMicroService     (8087)
├── LivraisonMicroService (8083)
├── MenuMicroService (8084)
├── ReservationMicroService (8086)
├── AvisMicroService (8081)

```
Chaque microservice est déployé indépendamment et communique via REST, le tout géré par un Gateway central et enregistré sur Eureka.

## 🌍 Project Objectives
Le projet a pour but de :

- Apprendre à concevoir une **architecture microservices**.
- Implémenter des services REST avec **Spring Boot**.
- Gérer la découverte de services avec **Eureka**.
- Utiliser un **API Gateway** pour centraliser les appels clients.
- Tester les routes via **Postman**.

## 🛠️ Technologies Utilisées
- **Java 17 / Spring Boot 3.4.3**
- **Spring Cloud Eureka & Gateway**
- **MySQL** pour la base de données
- **Lombok** pour réduire le boilerplate
- **Postman** pour les tests API

## 🔍 Fonctionnalités Clés
- 🧠 Microservice de Recommandation Intelligente :
- 👉 Objectif : proposer des plats pertinents à un utilisateur en fonction de ses commandes passées ou de son profil
- Fonction : Analyse les plats commandés et propose des recommandations
- Techno : Flask







