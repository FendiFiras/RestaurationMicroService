import { Component, OnInit } from '@angular/core';
import { ReservationService } from '../../Services/reservation.service';
import { EventType, Reservation } from '../../Models/reservation.model';  // Importation de la classe Reservation
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-resevation',
  templateUrl: './resevation.component.html',
  styleUrls: ['./resevation.component.css'],
  standalone: true,

  imports: [FormsModule, CommonModule, HttpClientModule,RouterModule]

})
export class ResevationComponent implements OnInit {
getEventTypeLabel(arg0: EventType|undefined) {
throw new Error('Method not implemented.');
}
  reservations: Reservation[] = [];  // Liste des réservations
  newReservation: Reservation = {};  // Modèle pour la nouvelle réservation
  isLoading: boolean = false; // Indicateur de chargement pour l'UI

  constructor(private reservationService: ReservationService) {}

  ngOnInit(): void {
    this.getReservations();  // Récupérer toutes les réservations au démarrage
  }

  // Récupérer toutes les réservations
  getReservations(): void {
    this.isLoading = true; // Affichage du chargement
    this.reservationService.getAllReservations().subscribe(
      (data) => {
        this.reservations = data;  // Assigner les réservations obtenues à la variable 'reservations'
        this.isLoading = false; // Cacher l'indicateur de chargement
      },
      (error) => {
        console.error('Erreur lors de la récupération des réservations:', error);
        this.isLoading = false;  // Cacher l'indicateur en cas d'erreur
      }
    );
  }

  // Ajouter une nouvelle réservation
  addReservation(): void {
    this.reservationService.addReservation(this.newReservation).subscribe(
      (data) => {
        this.reservations.push(data); // Ajouter la nouvelle réservation à la liste
        this.newReservation = {}; // Réinitialiser le modèle
      },
      (error) => {
        console.error('Erreur lors de l\'ajout de la réservation:', error);
      }
    );
  }

  // Supprimer une réservation
  deleteReservation(id: number): void {
    this.reservationService.deleteReservation(id).subscribe(
      () => {
        this.reservations = this.reservations.filter(reservation => reservation.idReservation !== id);  // Retirer la réservation supprimée de la liste
      },
      (error) => {
        console.error('Erreur lors de la suppression de la réservation:', error);
      }
    );
  }

  // Marquer une réservation comme urgente
  markAsUrgent(id: number): void {
    this.reservationService.markAsUrgent(id).subscribe(
      (message) => {
        console.log(message);
        this.getReservations(); // Rafraîchir la liste des réservations
      },
      (error) => {
        console.error('Erreur lors de la mise à jour de la réservation:', error);
      }
    );
  }

  // Exporter les réservations en format Excel
  exportToExcel(): void {
    this.reservationService.exportReservationsToExcel().subscribe(
      (blob) => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'reservations.xlsx';  // Nom du fichier à télécharger
        a.click();
        window.URL.revokeObjectURL(url);  // Libérer l'URL après le téléchargement
      },
      (error) => {
        console.error('Erreur lors de l\'exportation des réservations:', error);
      }
    );
  }
}
