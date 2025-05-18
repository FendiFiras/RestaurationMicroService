
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
-  **Keycloack**.


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
# 📅 ReservationMicroService - Gestion des Réservations

## 📕 Objectif
Le **ReservationMicroService** gère les réservations des clients dans un restaurant. Il permet d'ajouter, modifier, supprimer des réservations, de marquer certaines comme **urgentes**, et d'exporter les données vers un fichier Excel.
# 💬 AvisMicroService - Gestion des avis clients

## 📕 Objectif
Le **AvisMicroService** est responsable de la gestion des avis clients sur les commandes, plats ou l’expérience globale dans l'application. Il permet aux utilisateurs de laisser un avis avec une note, de le modifier, le supprimer et de faire des recherches intelligentes.
# 💼 CommandeMicroService - Gestion des Commandes

## 📕 Objectif
Le microservice **CommandeMicroService** permet de gérer les commandes passées dans l'application de restauration. Il est connecté à plusieurs autres services (utilisateur, plat, livraison) et offre des opérations avancées telles que le tri, le filtrage, la commande partagée ou la génération de PDF de facture.
# 🍽️ MenuMicroService - Gestion des Menus et Plats

## 📅 Objectif du Service
Le **MenuMicroService** est responsable de la gestion des menus et des plats pour l'application de restauration. Il offre des opérations CRUD complètes pour les menus et les plats ainsi que des fonctionnalités avancées telles que la recherche ou un plat mystère aléatoire.

---

## 🚀 Technologies Utilisées
- Java 17
- Spring Boot 3
- Spring Data JPA
- Lombok
- Apache POI (pour export Excel)
- Postman pour les tests
- Swagger / OpenAPI
- Lombok
- MySQL

---

## 🔄 Endpoints Disponibles

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| POST    | `/reservation/add-reservation` | Ajouter une nouvelle réservation |
| GET     | `/reservation/all` | Récupérer toutes les réservations |
| DELETE  | `/reservation/delete-reservation/{id}` | Supprimer une réservation |
| PUT     | `/reservation/update-reservation/{id}` | Modifier une réservation existante |
| PUT     | `/reservation/mark-urgent/{id}` | Marquer une réservation comme urgente |
| GET     | `/reservation/urgent` | Récupérer toutes les réservations urgentes |
| GET     | `/reservation/export` | Exporter les réservations au format Excel |
- MySQL
- Postman pour le test

---

## 🔄 API - Menus
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET     | `/Menu/GetAllMenu` | Récupérer tous les menus |
| GET     | `/Menu/GetMenu/{id}` | Récupérer un menu par ID |
| POST    | `/Menu/AddNewMenu` | Créer un nouveau menu |
| PUT     | `/Menu/UpdateMenu/{id}` | Modifier un menu existant |
| DELETE  | `/Menu/DeleteMenu/{id}` | Supprimer un menu |

---

## 🍽️ API - Plats
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET     | `/Menu/plats/GetAllPlats` | Récupérer tous les plats |
| GET     | `/Menu/plats/GetPlatBy/{id}` | Récupérer un plat par ID |
| POST    | `/Menu/plats/AddNewPlat` | Ajouter un nouveau plat |
| PUT     | `/Menu/plats/UpdatePlat/{id}` | Modifier un plat existant |
| DELETE  | `/Menu/plats/DeletePlatBy/{id}` | Supprimer un plat |

### 🔍 Recherche avancée
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET     | `/Menu/plats/search?nom=...&prixMax=...` | Recherche par nom et/ou prix maximum |

### 🎩 Plat mystère
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET     | `/Menu/plats/Surprise Dish` | Retourne un plat mystère avec réduction aléatoire |

---

## 📊 Exemple de Requêtes

### Ajouter une réservation
```json
POST /reservation/add-reservation
{
  "nomClient": "Sami",
  "nombrePersonnes": 4,
  "date": "2025-04-21T19:30:00",
  "urgent": false
}
```

### Marquer comme urgente
```
PUT /reservation/mark-urgent/1
```

### Exporter vers Excel
```
GET /reservation/export
| GET     | `/avis` | Récupérer tous les avis |
| GET     | `/avis/getbyid/{id}` | Récupérer un avis par ID |
| POST    | `/avis/create` | Ajouter un nouvel avis |
| PUT     | `/avis/update/{id}` | Modifier un avis existant |
| DELETE  | `/avis/delete/{id}` | Supprimer un avis |
| GET     | `/avis/ping` | Tester le bon fonctionnement du service |
| GET     | `/avis/SmartKeywordSearch?keyword=...&rating=...` | Recherche intelligente par mot-clé et note |
- MySQL
- iTextPDF
- Postman pour test

---

## 🔄 API Commande

### 🔢 CRUD
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| POST    | `/commande/addCommande` | Ajouter une nouvelle commande |
| GET     | `/commande/Gett_All_Commandes` | Récupérer toutes les commandes |
| GET     | `/commande/GetoneByID/{id}` | Récupérer une commande par ID |
| PUT     | `/commande/Update/{id}` | Modifier une commande existante |
| DELETE  | `/commande/Supprimer/{id}` | Supprimer une commande |

### 🔍 Fonctionnalités avancées
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| POST    | `/commande/{id}/generate-pdf` | Générer un PDF de la commande (facture) |
| GET     | `/commande/sorted?order=asc|desc` | Trier les commandes par total croissant/décroissant |
| GET     | `/commande/filter-by-mode?modePaiement=CASH` | Filtrer les commandes par mode de paiement |
| POST    | `/commande/partagee/save` | Sauvegarder une commande partagée (plusieurs utilisateurs) |

---

## 📊 Exemple Requêtes

### ➕ Créer un avis
```http
POST /avis/create
```
```json
{
  "contenu": "Super bon plat, livraison rapide !",
  "note": 5,
}
```

### 🔍 Recherche intelligente
```http
GET /avis/SmartKeywordSearch?keyword=livraison&rating=5
### ➕ Ajouter une commande
```json
POST /commande/addCommande
{
  "idUser": 1,
  "idLivraison": 10,
  "idPlats": [1, 2, 3],
  "modePaiement": "CARTE",
  "typeCommande": "LIVRAISON"
}
```

### 👥 Commande partagée
```json
POST /commande/partagee/save
{
  "participants": [
    { "idUser": 1, "idLivraison": 100, "idPlats": [1, 2] },
    { "idUser": 2, "idLivraison": 101, "idPlats": [3] }
  ]
}
```

### 🔎 Filtrer par mode de paiement
```
GET /commande/filter-by-mode?modePaiement=CARTE
### Ajouter un plat
```http
POST /Menu/plats/AddNewPlat
```
```json
{
  "nom": "Pizza 4 Fromages",
  "prix": 18.50,
  "description": "Délicieuse pizza avec mozzarella, chèvre, bleu et parmesan"
}
```

### Recherche de plat par nom
```http
GET /Menu/plats/search?nom=pizza
```

---



---

📍 Port par défaut : `8086`

🔄 Préfixe API : `/reservation`

📄 Format supporté : `application/json`, `application/vnd.openxmlformats-officedocument.spreadsheetml.sheet`

✍️ Auteur Projet développé par Firas Abdidhiaf dans le cadre d’un projet microservices de gestion de reservation pour une application de restauration.
## 🧱 Connectivité
Ce microservice peut être appelé par :
- `CommandeMicroService` pour noter une commande
- `MenuMicroService` pour noter un plat
- `UserMicroService` pour associer un avis à un utilisateur

---

📍 Port par défaut : `8089`

🔄 Préfixe API : `/avis`

📄 Format supporté : `application/json`
📍 Port : `8082`

🔐 Swagger UI : `http://localhost:8082/swagger-ui/index.html`

🔄 Préfixe API : `/commande`

✍️ Auteur Projet développé par Blel Montassar dans le cadre d’un projet microservices de gestion d’utilisateurs pour une application de restauration.
📍 Port par défaut : `8085`


✍️ Auteur
Projet développé par Adem Zitouni dans le cadre d’un microservice de gestion de restauration
