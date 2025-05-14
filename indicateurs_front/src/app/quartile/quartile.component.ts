import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../services/api.service';
import { Revue } from '../models/revue.model';
import { RouterModule, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-quartile',
  templateUrl: './quartile.component.html',
  styleUrls: ['./quartile.component.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule]
})
export class QuartileComponent implements OnInit {
  searchQuery = '';
  publisherQuery = '';
  revueList: Revue[] = [];
  uniquePublishers: string[] = [];
  uniqueTitles: string[] = [];
  revueId: number | null = null;
  quartiles: any[] = [];
  bestQuartiles: any[] = [];
  loading = false;
  error: string | null = null;
  revueInfo: any = null;
  uniqueDomaines: string[] = [];
  years: number[] = [];
  displayYears: number[] = [];
  yearInterval = 1;
  allQuartiles = ['Q1', 'Q2', 'Q3', 'Q4'];
  quartileColors: Record<string, string> = {};
  hoveredCell: any = null;
  hoveredBestQuartile: any = null;
  tooltipPosition = { x: 0, y: 0 };
  colorPalette = ['#FFED5E', '#F9A958', '#F17350', '#E63946'];

  constructor(private apiService: ApiService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.searchQuery = params['titre'] || '';
      this.publisherQuery = params['editeur'] || '';
    });
    this.allQuartiles.forEach((q, i) => this.quartileColors[q] = this.colorPalette[i]);
  }
  resetSearch(): void {
    this.searchQuery = '';
    this.publisherQuery = '';
    this.uniqueTitles = [];
    this.uniquePublishers = [];
    this.revueInfo = null;  
    this.quartiles = [];  
    this.bestQuartiles = [];  
    this.hoveredCell = null; 
    this.allQuartiles = []; 
  }



  search(): void {
    if (!this.searchQuery.trim() && !this.publisherQuery.trim()) return;
    this.loading = true;
    this.error = null;
    this.quartiles = [];
    this.bestQuartiles = [];
    this.hoveredCell = this.hoveredBestQuartile = null;

    this.apiService.searchRevue(this.searchQuery, this.publisherQuery).subscribe({
      next: revueId => {
        this.revueId = typeof revueId === 'string' ? parseInt(revueId, 10) : revueId;
        if (!isNaN(this.revueId!)) {
          this.getQuartiles(this.revueId!);
          this.getBestQuartiles(this.revueId!);
        } else {
          this.error = 'ID de revue invalide';
          this.loading = false;
        }
      },
      error: () => {
        this.error = 'Erreur lors de la recherche de la revue';
        this.loading = false;
      }
    });
  }

  searchTitle(): void {
    const term = this.searchQuery.trim();
    this.revueList = [];
    this.uniqueTitles = [];
    if (!term) return;
  
    this.apiService.searchRevueByTitre(term).subscribe({
      next: data => {
        this.revueList = data;
        this.uniqueTitles = Array.from(new Set(data.map(r => r.titre)));
      },
      error: () => {
        this.uniqueTitles = [];
      }
    });
  }

  searchPublisher(): void {
    const term = this.publisherQuery.trim();
    this.revueList = [];
    this.uniquePublishers = [];

    if (!term) return;

    this.apiService.searchRevueByPublisher(term).subscribe({
      next: data => {
        this.revueList = data;
        this.uniquePublishers = Array.from(new Set(data.map(r => r.publisher)));
      },
      error: () => {
        this.revueList = [];
        this.uniquePublishers = [];
      }
    });
  }

  getQuartiles(revueId: number): void {
    this.apiService.getQuartilesByRevue(revueId).subscribe({
      next: data => {
        this.quartiles = data;
        if (this.quartiles.length === 0) {
          this.error = 'Aucun quartile trouvé pour cette revue';
        } else {
          this.processDataForHeatmap();
          this.revueInfo = {
            titre: this.quartiles[0].titre,
            publisher: this.quartiles[0].publisher
          };
        }
        if (this.bestQuartiles.length > 0 || this.error) {
          this.loading = false;
        }
      },
      error: () => {
        this.error = 'Erreur lors du chargement des quartiles';
        this.loading = false;
      }
    });
  }

  getBestQuartiles(revueId: number): void {
    this.apiService.getBestQuartilesByRevue(revueId).subscribe({
      next: data => {
        this.bestQuartiles = data.sort((a, b) => a.annee - b.annee);
        if (this.quartiles.length > 0 || this.error) {
          this.loading = false;
        }
      },
      error: () => {
        this.error = 'Erreur lors du chargement des meilleurs quartiles';
        this.loading = false;
      }
    });
  }

  processDataForHeatmap(): void {
    this.uniqueDomaines = [...new Set(this.quartiles.map(q => q.domaine))];
    this.years = [...new Set(this.quartiles.map(q => q.annee))].sort();
    this.determineYearInterval();
    this.generateDisplayYears();
  }

  determineYearInterval(): void {
    this.yearInterval = this.years.length > 10 ? 5 : 1;
  }

  generateDisplayYears(): void {
    if (this.years.length === 0) {
      this.displayYears = [];
      return;
    }
    const firstYear = this.years[0];
    const lastYear = this.years[this.years.length - 1];
    const adjustedFirstYear = Math.floor(firstYear / this.yearInterval) * this.yearInterval;
    this.displayYears = [];
    for (let year = adjustedFirstYear; year <= lastYear; year += this.yearInterval) {
      if (year % this.yearInterval === 0) {
        this.displayYears.push(year);
      }
    }
  }

  getEntryForDomaineAndYear(domaine: string, year: number): any {
    return this.quartiles.find(q => q.domaine === domaine && q.annee === year);
  }

  getCellStyle(domaine: string, year: number): any {
    const entry = this.getEntryForDomaineAndYear(domaine, year);
    return entry
      ? { 'background-color': this.quartileColors[entry.quartile] }
      : { 'background-color': '#f0f0f0' };
  }

  hoverCell(domaine: string | null, year: number | null): void {
    this.hoveredCell = (domaine && year) ? this.getEntryForDomaineAndYear(domaine, year) : null;
  }

  getQuartileColor(quartile: string): string {
    return this.quartileColors[quartile] || '#CCCCCC';
  }

  getQuartileNumericValue(quartile: string): number {
    return parseInt(quartile.substring(1), 10);
  }

  hoverBestQuartile(quartile: any | null, event?: MouseEvent): void {
    this.hoveredBestQuartile = quartile;
    if (quartile && event) {
      const rect = (event.target as Element).getBoundingClientRect();
      this.tooltipPosition = { x: rect.left, y: rect.top - 10 };
    }
  }

  getDomainsForQuartile(quartile: any): string[] {
    return this.quartiles
      .filter(q => q.annee === quartile.annee && q.quartile === quartile.quartile)
      .map(q => q.domaine);
  }

  getPointPosition(quartile: any, index: number): { x: number; y: number } {
    const chartWidth = this.years.length > 1
      ? (this.years[this.years.length - 1] - this.years[0]) * 40
      : 80;
    const xStep = chartWidth / Math.max(1, this.years.length - 1);
    const yearIndex = this.years.indexOf(quartile.annee);
    const x = yearIndex >= 0 ? yearIndex * xStep + 40 : 40;
    const chartHeight = 100;
    const yValue = this.getQuartileNumericValue(quartile.quartile);
    const y = (yValue * chartHeight) / 4;
    return { x, y };
  }

  getPathData(): string {
    if (this.bestQuartiles.length < 2) return '';
    return this.bestQuartiles
      .map((q, i) => {
        const p = this.getPointPosition(q, i);
        return `${i === 0 ? 'M' : 'L'} ${p.x} ${p.y}`;
      })
      .join(' ');
  }

  shouldDisplayYear(year: number): boolean {
    return this.displayYears.includes(year);
  }
}
