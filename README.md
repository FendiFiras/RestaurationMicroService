# 💬 AvisMicroService - Gestion des avis clients

## 📕 Objectif
Le **AvisMicroService** est responsable de la gestion des avis clients sur les commandes, plats ou l’expérience globale dans l'application. Il permet aux utilisateurs de laisser un avis avec une note, de le modifier, le supprimer et de faire des recherches intelligentes.

---

## 🚀 Technologies Utilisées
- Java 17
- Spring Boot 3
- Spring Data JPA
- Swagger / OpenAPI
- Lombok
- MySQL

---

## 🔄 Endpoints Disponibles

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET     | `/avis` | Récupérer tous les avis |
| GET     | `/avis/getbyid/{id}` | Récupérer un avis par ID |
| POST    | `/avis/create` | Ajouter un nouvel avis |
| PUT     | `/avis/update/{id}` | Modifier un avis existant |
| DELETE  | `/avis/delete/{id}` | Supprimer un avis |
| GET     | `/avis/ping` | Tester le bon fonctionnement du service |
| GET     | `/avis/SmartKeywordSearch?keyword=...&rating=...` | Recherche intelligente par mot-clé et note |

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
```

---

## 🧱 Connectivité
Ce microservice peut être appelé par :
- `CommandeMicroService` pour noter une commande
- `MenuMicroService` pour noter un plat
- `UserMicroService` pour associer un avis à un utilisateur

---

📍 Port par défaut : `8089`

🔄 Préfixe API : `/avis`

📄 Format supporté : `application/json`
