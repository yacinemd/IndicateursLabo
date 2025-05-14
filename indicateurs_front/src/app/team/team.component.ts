import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { NgChartsModule } from 'ng2-charts';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../services/api.service';
import { Article } from '../models/article.model';
import { Conference } from '../models/conference.model';
import { Livre } from '../models/livre.model';
import { Revue } from '../models/revue.model';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-team',
  imports: [CommonModule, NgChartsModule, FormsModule, RouterModule],
  templateUrl: './team.component.html',
  styleUrl: './team.component.scss'
})
export class TeamComponent {
  equipes: string[] = ['IFTIM', 'CORES', 'VIBOT'];
  typesPublication: string[] = ['Tous','Article', 'Conférence', 'Livre', 'Revue'];
  selectedEquipe: string = 'IFTIM';
  selectedType: string = 'Tous';
  filteredCount: number | null = null;
  articles: Article[] = [];
  conferences: Conference[] = [];
  livres: Livre[] = [];
  revues: Revue[] = [];
  searchPerformed: boolean = false;

  constructor(private apiService: ApiService, private router: Router) {}


  onFilterSubmit() {
    this.filteredCount = null;
    this.articles = [];
    this.conferences = [];
    this.livres = [];
    this.revues = [];
    this.searchPerformed = true;

    if (!this.selectedEquipe || !this.selectedType) return;

    switch (this.selectedType) {
      case 'Tous':
        this.apiService.countPubliByTeam(this.selectedEquipe).subscribe({
          next: (res) => (this.filteredCount = res)
        });
        break;
      case 'Article':
        this.apiService.countArticlesByTeam(this.selectedEquipe).subscribe({
          next: (res) => (this.filteredCount = res)
        });
        this.apiService.getArticleByEquipe(this.selectedEquipe).subscribe({
          next: (res) => (this.articles = res)
        });
        break;
      case 'Conférence':
        this.apiService.countConferencesByTeam(this.selectedEquipe).subscribe({
          next: (res) => (this.filteredCount = res)
        });
        this.apiService.getConferenceByEquipe(this.selectedEquipe).subscribe({
          next: (res) => (this.conferences = res)
        });
        break;
      case 'Livre':
        this.apiService.countLivresByTeam(this.selectedEquipe).subscribe({
          next: (res) => (this.filteredCount = res)
        });
        this.apiService.getLivreByEquipe(this.selectedEquipe).subscribe({
          next: (res) => (this.livres = res)
        });
        break;
      case 'Revue':
        this.apiService.countRevuesByTeam(this.selectedEquipe).subscribe({
          next: (res) => (this.filteredCount = res)
        });
        this.apiService.getRevueByEquipe(this.selectedEquipe).subscribe({
          next: (res) => (this.revues = res)
        });
        break;
    }
  }

  resetFilters() {
    this.selectedEquipe = 'IFTIM';
    this.selectedType = 'Article';
    this.filteredCount = null;
    this.articles = [];
    this.conferences = [];
    this.livres = [];
    this.revues = [];
    this.searchPerformed = false;

  }

  goToQuartile(pub: any,event: Event): void {
    event.preventDefault();
    this.router.navigate(['/quartile'], {
      queryParams: {
        titre: pub.titre,
        editeur: pub.publisher
      }
    });
  }
}