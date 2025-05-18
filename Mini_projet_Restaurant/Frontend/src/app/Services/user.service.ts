import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { KeycloakService } from 'keycloak-angular';
import { user } from '../Models/user.model';
import { lastValueFrom, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly baseUrl = 'http://localhost:8093/users';

  constructor(
    private http: HttpClient,
    private keycloakService: KeycloakService
  ) {}

  private async getAuthHeaders(): Promise<HttpHeaders> {
    const token = await this.keycloakService.getToken();
    console.log('Access Token:', token); // You can log the token to see it

    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
  }

  private async handleRequest<T>(observable: Observable<T>): Promise<T> {
    try {
      return await lastValueFrom(observable);
    } catch (error) {
      if (error instanceof HttpErrorResponse) {
        if (error.status === 401) {
          console.warn('Unauthorized: Redirecting to login');
          this.keycloakService.login();
        } else if (error.status === 403) {
          console.error('Access denied', error);
        } else {
          console.error('HTTP Error:', error);
        }
      } else {
        console.error('Unexpected error:', error);
      }
      throw error;
    }
  }

  async createUser(user: user): Promise<user> {
    const headers = await this.getAuthHeaders();
    return this.handleRequest(this.http.post<user>(`${this.baseUrl}/add`, user, { headers }));
  }
  
  async getAllUsers(): Promise<user[]> {
    const headers = await this.getAuthHeaders();
    return this.handleRequest(this.http.get<user[]>(`${this.baseUrl}/show`, { headers }));
  }
  
  async getUserById(id: number): Promise<user> {
    const headers = await this.getAuthHeaders();
    return this.handleRequest(this.http.get<user>(`${this.baseUrl}/showbyid/${id}`, { headers }));
  }
  
  async getUserByEmail(email: string): Promise<user> {
    const headers = await this.getAuthHeaders();
    return this.handleRequest(this.http.get<user>(`${this.baseUrl}/email/${email}`, { headers }));
  }
  
  async updateUser(id: number, user: user): Promise<user> {
    const headers = await this.getAuthHeaders();
    return this.handleRequest(this.http.put<user>(`${this.baseUrl}/update/${id}`, user, { headers }));
  }
  
  async deleteUser(id: number): Promise<void> {
    const headers = await this.getAuthHeaders();
    return this.handleRequest(this.http.delete<void>(`${this.baseUrl}/delete/${id}`, { headers }));
  }
  
  async countUsers(): Promise<number> {
    const headers = await this.getAuthHeaders();
    return this.handleRequest(this.http.get<number>(`${this.baseUrl}/count`, { headers }));
  }
  
  async getGenderStats(): Promise<{ [key: string]: number }> {
    const headers = await this.getAuthHeaders();
    return this.handleRequest(this.http.get<{ [key: string]: number }>(`${this.baseUrl}/gender-stats`, { headers }));
  }
  
  async getUsersSortedAsc(): Promise<user[]> {
    const headers = await this.getAuthHeaders();
    return this.handleRequest(this.http.get<user[]>(`${this.baseUrl}/sorted/asc`, { headers }));
  }
  
  async getUsersSortedDesc(): Promise<user[]> {
    const headers = await this.getAuthHeaders();
    return this.handleRequest(this.http.get<user[]>(`${this.baseUrl}/sorted/desc`, { headers }));
  }
  
  // Optionally fetch the token directly for other use cases
  async getToken(): Promise<string> {
    try {
      const token = await this.keycloakService.getToken();
      if (!token) throw new Error('Token not found');
      return token;
    } catch (error) {
      console.error("Error fetching token", error);
      throw error;
    }
  }
  async getUserProfile(): Promise<any> {
    try {
      const userProfile = await this.keycloakService.loadUserProfile();
      return userProfile; // Retourne le profil utilisateur
    } catch (error) {
      console.error("Erreur lors du chargement du profil utilisateur", error);
      throw error;
    }
  }

  // Déconnexion
  async logout(): Promise<void> {
    try {
      await this.keycloakService.logout();
    } catch (error) {
      console.error("Erreur lors de la déconnexion", error);
      throw error;
    }
  }
  
}
