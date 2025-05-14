import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ContactComponent } from './contact/contact.component';
import { MapComponent } from './map/map.component';
import { StatistiquesComponent } from './statistiques/statistiques.component';
import {QuartileComponent } from './quartile/quartile.component';
import { TeamComponent } from './team/team.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'map',component:MapComponent},
  { path: 'statistiques',component:StatistiquesComponent},
  { path: 'quartile',component:QuartileComponent},
  { path: 'equipes', component: TeamComponent}


];