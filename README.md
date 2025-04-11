# 📅 ReservationMicroService - Gestion des Réservations

## 📕 Objectif
Le **ReservationMicroService** gère les réservations des clients dans un restaurant. Il permet d'ajouter, modifier, supprimer des réservations, de marquer certaines comme **urgentes**, et d'exporter les données vers un fichier Excel.

---

## 🚀 Technologies Utilisées
- Java 17
- Spring Boot 3
- Spring Data JPA
- Lombok
- Apache POI (pour export Excel)
- Postman pour les tests

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
```

---



---

📍 Port par défaut : `8086`

🔄 Préfixe API : `/reservation`

📄 Format supporté : `application/json`, `application/vnd.openxmlformats-officedocument.spreadsheetml.sheet`

✍️ Auteur Projet développé par Firas Abdidhiaf dans le cadre d’un projet microservices de gestion de reservation pour une application de restauration.
