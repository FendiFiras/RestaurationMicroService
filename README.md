# 🍽️ MenuMicroService - Gestion des Menus et Plats

## 📅 Objectif du Service
Le **MenuMicroService** est responsable de la gestion des menus et des plats pour l'application de restauration. Il offre des opérations CRUD complètes pour les menus et les plats ainsi que des fonctionnalités avancées telles que la recherche ou un plat mystère aléatoire.

---

## 🚀 Technologies Utilisées
- Java 17
- Spring Boot 3
- Spring Data JPA
- Lombok
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

📍 Port par défaut : `8085`


✍️ Auteur
Projet développé par Adem Zitouni dans le cadre d’un microservice de gestion de restauration
