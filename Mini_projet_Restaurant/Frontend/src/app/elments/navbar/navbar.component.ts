import { Component, OnInit, OnDestroy } from '@angular/core';
import {
  KeycloakService,
  KeycloakEvent,
  KeycloakEventType,
  KeycloakEventLegacy,
} from 'keycloak-angular';
import { Subscription } from 'rxjs';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit, OnDestroy {
  isMenuOpen = false;
  userProfile: any = null;
  isLoggedIn = false;
  isLoading = true;
  localStorageData: any = null;

  private keycloakEventsSub?: Subscription;

  constructor(private keycloakService: KeycloakService) {}

  async ngOnInit(): Promise<void> {
    await this.checkAuthState();
    this.loadLocalStorageData();

    this.keycloakEventsSub = this.keycloakService.keycloakEvents$.subscribe({
      next: (event: KeycloakEvent | KeycloakEventLegacy) => {
        if (this.isKeycloakEvent(event)) {
          switch (event.type) {
            case KeycloakEventType.AuthSuccess:
            case KeycloakEventType.AuthRefreshSuccess:
              this.checkAuthState();
              this.loadLocalStorageData();
              break;
            case KeycloakEventType.AuthLogout:
              this.handleLogout();
              break;
            case KeycloakEventType.TokenExpired:
              this.keycloakService.updateToken(20).catch(err =>
                console.error('Token refresh failed', err)
              );
              break;
          }
        }
      },
      error: (err) => console.error('Keycloak event error', err),
    });
  }

  ngOnDestroy(): void {
    this.keycloakEventsSub?.unsubscribe();
  }

  private isKeycloakEvent(event: KeycloakEvent | KeycloakEventLegacy): event is KeycloakEvent {
    return event && typeof event.type === 'string' && event.type in KeycloakEventType;
  }

  async checkAuthState(): Promise<void> {
    try {
      this.isLoggedIn = await this.keycloakService.isLoggedIn();
      console.log('Is user logged in?', this.isLoggedIn);

      if (this.isLoggedIn) {
        this.userProfile = await this.keycloakService.loadUserProfile();
        console.log('User profile:', this.userProfile);

        const token = await this.keycloakService.getToken();
        console.log('Access Token:', token);
      }
    } catch (error) {
      console.error('Error checking auth state:', error);
    } finally {
      this.isLoading = false;
    }
  }

  login(): void {
    this.keycloakService.login({ redirectUri: window.location.origin });
  }

  logout(): void {
    this.keycloakService.logout(window.location.origin);
  }

  toggleMenu(): void {
    this.isMenuOpen = !this.isMenuOpen;
  }

  get username(): string {
    if (!this.userProfile) return 'Utilisateur';
    return this.userProfile.username ?? this.userProfile.firstName ?? 'Utilisateur';
  }

  handleLogout(): void {
    this.isLoggedIn = false;
    this.userProfile = null;
    this.localStorageData = null;
  }

  loadLocalStorageData(): void {
    const storedData = localStorage.getItem('keycloak'); // Remplace la clé exacte si nécessaire
    this.localStorageData = storedData ? JSON.parse(storedData) : null;
  }
}
