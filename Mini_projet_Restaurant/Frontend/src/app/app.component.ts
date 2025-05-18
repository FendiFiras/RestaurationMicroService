import { Component, OnInit } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { NavbarComponent } from './elments/navbar/navbar.component';
import { FooterComponent } from './elments/footer/footer.component';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterModule,
    NavbarComponent,
    FooterComponent,
    RouterOutlet,
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'RestaurantFront';

  constructor(private keycloakService: KeycloakService) {}

  async ngOnInit() {
    // Utilisation de async/await pour vérifier si l'utilisateur est authentifié
    const authenticated = await this.keycloakService.isLoggedIn();
    if (authenticated) {
      console.log('User is authenticated');
    } else {
      console.log('User is not authenticated');
    }
  }
}
