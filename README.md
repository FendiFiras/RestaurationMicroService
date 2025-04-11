# 💼 CommandeMicroService - Gestion des Commandes

## 📕 Objectif
Le microservice **CommandeMicroService** permet de gérer les commandes passées dans l'application de restauration. Il est connecté à plusieurs autres services (utilisateur, plat, livraison) et offre des opérations avancées telles que le tri, le filtrage, la commande partagée ou la génération de PDF de facture.

---

## 🚀 Technologies Utilisées
- Java 17
- Spring Boot 3
- Spring Data JPA
- Lombok
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
```

---

📍 Port : `8082`

🔐 Swagger UI : `http://localhost:8082/swagger-ui/index.html`

🔄 Préfixe API : `/commande`

✍️ Auteur Projet développé par Blel Montassar dans le cadre d’un projet microservices de gestion d’utilisateurs pour une application de restauration.
