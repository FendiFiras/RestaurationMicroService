import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { isPlatformBrowser } from '@angular/common';

@Injectable({ providedIn: 'root' })
export class KeycloakInitService {
  constructor(
    private readonly keycloak: KeycloakService,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  async init(): Promise<boolean> {
    // Vérification pour ne pas initialiser Keycloak côté serveur
    if (!isPlatformBrowser(this.platformId)) {
      console.log('Keycloak cannot be initialized on the server');
      return false;
    }

    try {
      // Configuration de Keycloak
      const authenticated = await this.keycloak.init({
        config: {
          url: 'http://localhost:8180', // URL de ton serveur Keycloak
          realm: 'RestaurationKeycloak', // Nom du realm
          clientId: 'restaurant-angular-client' // ID du client Keycloak
        },
        initOptions: {
          onLoad: 'login-required', // Force la connexion de l'utilisateur
          checkLoginIframe: false, // Désactive le contrôle iframe
          pkceMethod: 'S256', // Sécurisation PKCE
        },
        enableBearerInterceptor: true, // Active l'intercepteur d'authentification
        bearerExcludedUrls: ['/assets', '/config'] // Exclut certaines URLs de l'intercepteur
      });

      // Log si Keycloak a bien été initialisé
      console.log('Keycloak initialized', authenticated);
      return authenticated;
    } catch (error) {
      console.error('Keycloak initialization failed', error);
      return false;
    }
  }
}
