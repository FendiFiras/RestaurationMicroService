import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { KeycloakAngularModule } from 'keycloak-angular';
import { AppComponent } from './app/app.component';
import { KeycloakInitService } from './app/Services/keycloak-init-service.service';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { routes } from './app/app.routes';
import { importProvidersFrom, APP_INITIALIZER } from '@angular/core';

bootstrapApplication(AppComponent, {
  providers: [
    provideHttpClient(withFetch()),
    importProvidersFrom(KeycloakAngularModule),
    provideRouter(routes, withComponentInputBinding()),
    KeycloakInitService,
    {
      provide: APP_INITIALIZER,
      useFactory: (service: KeycloakInitService) => () => service.init(),
      deps: [KeycloakInitService],
      multi: true
    }
  ]
}).catch(err => console.error(err));