import { Component } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ListePersonnelComponent } from "../liste-personnel/liste-personnel.component";

@Component({
  selector: 'app-tableau',
  standalone: true,
  imports: [ListePersonnelComponent, HttpClientModule],
  templateUrl: './tableau.component.html',
  styleUrl: './tableau.component.scss'
})
export class TableauComponent {

}

