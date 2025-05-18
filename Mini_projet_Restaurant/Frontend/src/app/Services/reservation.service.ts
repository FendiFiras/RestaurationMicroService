import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reservation } from '../Models/reservation.model';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  private apiUrl = 'http://localhost:8093/reservation';  // Assurez-vous que l'URL correspond à celle de votre serveur

  constructor(private http: HttpClient) {}

  // Ajouter une réservation
  addReservation(reservation: Reservation): Observable<Reservation> {
    return this.http.post<Reservation>(`${this.apiUrl}/add-reservation`, reservation);
  }

  // Obtenir toutes les réservations
  getAllReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(`${this.apiUrl}/all`);
  }

  // Supprimer une réservation
  deleteReservation(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete-reservation/${id}`);
  }

  // Modifier une réservation
  updateReservation(id: number, reservation: Reservation): Observable<Reservation> {
    return this.http.put<Reservation>(`${this.apiUrl}/update-reservation/${id}`, reservation);
  }

  // Exporter les réservations en Excel
  exportReservationsToExcel(): Observable<Blob> {
    return this.http.get(`${this.apiUrl}/export`, { responseType: 'blob' });
  }

  // Marquer une réservation comme urgente
  markAsUrgent(id: number): Observable<string> {
    return this.http.put<string>(`${this.apiUrl}/mark-urgent/${id}`, {});
  }

  // Obtenir toutes les réservations urgentes
  getUrgentReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(`${this.apiUrl}/urgent`);
  }
}
