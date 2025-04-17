
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







# 👤 UserMicroService - Gestion des utilisateurs

## 📌 Présentation
Ce microservice est dédié à la *gestion des utilisateurs* dans une architecture microservices. Il offre des fonctionnalités CRUD, des recherches avancées ainsi que des statistiques utiles sur les utilisateurs enregistrés. 

Il est prévu d’intégrer *Keycloak* pour gérer l’authentification, les rôles et la sécurité des endpoints.

## 🛠 Technologies utilisées
Java 17
Spring Boot 3.x
Spring Data JPA
Lombok
MySQL
Postman (pour tests)
🔐 Keycloak (authentification & gestion des rôles)
)

## 🚀 Fonctionnalités exposées via API REST

### 🔹 CRUD Utilisateur
| Méthode | Endpoint               | Description                   |
|---------|------------------------|-------------------------------|
| POST    | /users              | Ajouter un utilisateur        |
| GET     | /users              | Récupérer tous les utilisateurs |
| GET     | /users/{id}         | Récupérer un utilisateur par ID |
| PUT     | /users/{idUser}     | Modifier un utilisateur       |
| DELETE  | /users/{id}         | Supprimer un utilisateur      |

### 🔍 Requêtes personnalisées
| Endpoint | Description |
|----------|-------------|
| /users/email/{email} | Rechercher par adresse email |
| /users/role/{role}   | Rechercher par rôle (ADMIN, CLIENT, etc.) |

### 📊 Statistiques & tri
| Endpoint | Description |
|----------|-------------|
| /users/gender-stats     | Statistiques selon le genre |
| /users/sorted/asc       | Trier par date de naissance croissante |
| /users/sorted/desc      | Trier par date de naissance décroissante |
| /users/count            | Nombre total d'utilisateurs enregistrés |

## 🔐 Sécurité avec Keycloak (prévue)
Le microservice sera sécurisé à l’aide de *Keycloak* :
Authentification via OAuth2 / OpenID Connect

- Gestion des rôles (ADMIN, CLIENT, etc.)
- Intégration avec Spring Security (@PreAuthorize)
Interface d’administration des utilisateurs sur le portail Keycloak


## 🧪 Exemple d'appel via Postman

### ➕ Ajouter un utilisateur :
POST http://localhost:8087/users
{
  "firstName": "Sami",
  "lastName": "Ben Ahmed",
  "email": "sami@example.com",
  "role": "CLIENT",
  "gender": "MALE",
  "dateOfBirth": "1996-07-15"
}

### 📈 Récupérer les statistiques de genre
GET http://localhost:8087/users/gender-stats
Réponse :
{
  "MALE": 8,
  "FEMALE": 5
}

---

## 🧠 Utilité dans l'écosystème microservices
Ce service est souvent sollicité par d'autres microservices (ex: CommandeMicroService) via idUser, et peut être étendu pour :
Gérer l’authentification avec Keycloak
Gérer des rôles et permissions
Fournir des avatars ou préférences utilisateur


---
📎 Port par défaut :*8087**

📎 Préfixe API :/users`

📎 Format supporté : application/json



✍️ Auteur
Projet développé par Khalil CHKILI dans le cadre d’un projet microservices de gestion d’utilisateurs pour une application de restauration.
