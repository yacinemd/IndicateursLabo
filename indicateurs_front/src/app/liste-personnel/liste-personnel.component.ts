import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Personne } from '../models/personne.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-liste-personnel',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './liste-personnel.component.html',
  styleUrl: './liste-personnel.component.scss'
})
export class ListePersonnelComponent implements OnInit {
  personnelList: Personne[] = [];

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.apiService.getPersonnel().subscribe((data: any[]) => {
      this.personnelList = data;
    });
  }
}