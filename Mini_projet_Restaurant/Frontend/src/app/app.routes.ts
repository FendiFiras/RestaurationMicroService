import { Routes } from '@angular/router';
import { UserComponent } from './Demo/user/user.component';
import { ResevationComponent } from './Demo/resevation/resevation.component';

export const routes: Routes = [
    { path: 'user', component: UserComponent },
    { path: 'reservation', component: ResevationComponent },  // Route pour le composant ReservationComponent

    { path: '', redirectTo: 'user', pathMatch: 'full' },

    // Ajoutez d'autres routes ici selon les besoins
  
  ];
