import { APP_INITIALIZER, ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { KeycloakAngularModule } from 'keycloak-angular';
import { KeycloakInitService } from './Services/keycloak-init-service.service';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideClientHydration(withEventReplay()),
    provideHttpClient(withFetch()),
    importProvidersFrom(KeycloakAngularModule),
    KeycloakInitService,
    {
      provide: APP_INITIALIZER,
      useFactory: (service: KeycloakInitService) => () => service.init(),
      deps: [KeycloakInitService],
      multi: true
    }
  ]
};