import { Component, AfterViewInit } from '@angular/core';
import * as L from 'leaflet';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { FormsModule } from '@angular/forms';
import { of } from 'rxjs';

import { ApiService } from '../services/api.service';
import { Article } from '../models/article.model';
import { Conference } from '../models/conference.model';
import { Livre } from '../models/livre.model';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  imports: [FormsModule],
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements AfterViewInit {

  private map!: L.Map;
  public loadingArticles = true;
  public articleList: Article[] = [];
  public conferenceList: Conference[] = [];
  public livreList: Livre[] = [];

  public displayedArticles: Article[] = [];
  public displayedConferences: Conference[] = [];
  public displayedLivres: Livre[] = [];

  private countryData: any[] = [];
  private displayedCountries: Set<string> = new Set();

  selectedType: string = 'article'; 
  selectedYear: number | null = null;
  selectedYear2: number | null = null;


  constructor(
    private apiService: ApiService,
    private http: HttpClient
  ) {}

  ngAfterViewInit(): void {
    setTimeout(() => {
      this.initializeMap();
      this.loadCountryData();
    }, 0);
  }

  onFilterSubmit() {
    console.log('Filtre appliqué');

    this.displayedCountries.clear();
    this.clearMapMarkers();
    this.loadFilteredArticlesAndConferences();
  }

  resetFilters(): void {
    this.selectedType = 'article';
    this.selectedYear = null;
    this.selectedYear2 = null;
    this.displayedCountries.clear();
    this.resetPublicationList();
    this.clearMapMarkers();
  }

  private clearMapMarkers(): void {
    this.map.eachLayer((layer: any) => {
      if (layer instanceof L.Marker) {
        this.map.removeLayer(layer);
      }
    });
  }

  private initializeMap(): void {
    this.map = L.map('map').setView([20, 0], 2);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; OpenStreetMap contributors'
    }).addTo(this.map);

    setTimeout(() => this.map.invalidateSize(), 0);
  }

  

  private loadCountryData(): Promise<void> {
    return this.http.get<any>('assets/country-codes-lat-long-alpha3.json')
      .pipe(
        catchError(error => {
          console.error('Erreur lors du chargement des données pays :', error);
          return of({ ref_country_codes: [] });
        })
      )
      .toPromise()
      .then(data => {
        this.countryData = data.ref_country_codes || [];
      });
  }

  private loadFilteredArticlesAndConferences(): void {
    this.displayedArticles = [];
    this.displayedConferences = [];
  
    if (this.selectedType === 'article') {
      this.loadArticlesByYear();
    } else if (this.selectedType === 'conference') {
      this.loadConferencesByYear();
    } else if (this.selectedType === 'livre') {
      this.loadLivresByYear();
    }
  }
  
  
  
  private loadArticlesByYear(): void {
    if (this.selectedYear && this.selectedYear2) {
      this.apiService.getArticlesByYear(this.selectedYear, this.selectedYear2)
        .pipe(catchError(error => of([])))
        .subscribe(articles => {
          this.displayedArticles = articles;
          this.addAllMarkers(articles);
        });
    }
  }
  
  private loadConferencesByYear(): void {
    if (this.selectedYear && this.selectedYear2) {
      this.apiService.getConferencesByYear(this.selectedYear, this.selectedYear2)
        .pipe(catchError(error => of([])))
        .subscribe(conferences => {
          this.displayedConferences = conferences;
          this.addAllConferenceMarkers(conferences);
        });
    }
  }

  private loadLivresByYear(): void {
    if (this.selectedYear && this.selectedYear2) {
      this.apiService.getLivresByYear(this.selectedYear, this.selectedYear2)
        .pipe(catchError(error => of([])))
        .subscribe(livres => {
          this.displayedLivres = livres;
          this.addAllLivreMarkers(livres);
        });
    }
  }
  



  private addAllMarkers(filteredArticles: Article[]): void {
    filteredArticles.forEach(article => {
      if (!article.pays) return;
      const alpha2Codes = article.pays.split(',').map(c => c.trim());
      alpha2Codes.forEach(code => {
        if (!this.displayedCountries.has(code)) {
          this.addMarkerForAlpha2(code, `${article.titre} (Article)`);
        }
      });
    });
  }

  private addAllConferenceMarkers(filteredConferences: Conference[]): void {
    filteredConferences.forEach(conf => {
      if (!conf.pays) return;
      const alpha2Codes = conf.pays.split(',').map(c => c.trim());
      alpha2Codes.forEach(code => {
        if (!this.displayedCountries.has(code)) {
          this.addMarkerForAlpha2(code, `${conf.titre} (Conférence)`);
        }
      });
    });
  }

  private addAllLivreMarkers(filteredLivres: Livre[]): void {
    
    filteredLivres.forEach(livre => {
      if (!livre.pays) return;            
      
      const alpha2Codes = livre.pays.split(',').map(c => c.trim());
      alpha2Codes.forEach(code => {
        if (!this.displayedCountries.has(code)) {
          this.addMarkerForAlpha2(code, `${livre.titre} (Livre)`);
        }
      });
    });
  }

  private getCountryByAlpha2(alpha2Code: string): any | undefined {
    return this.countryData.find(c =>
      c.alpha2?.trim().toUpperCase() === alpha2Code.trim().toUpperCase()
    );
  }

  private addMarkerForAlpha2(alpha2Code: string, label: string): void {
    const country = this.getCountryByAlpha2(alpha2Code);
    if (country?.latitude && country?.longitude) {
      const markerGroup = L.layerGroup();
  
      let markerColor = 'red'; 
      if (this.selectedType === 'article') {
        markerColor = 'blue';
      } else if (this.selectedType === 'livre') {
        markerColor = 'green';
      } else if (this.selectedType === 'conference') {
        markerColor = 'red';
      }
  
      const customIcon = L.divIcon({
        html: `<div style="width: 12px; height: 12px; background-color: ${markerColor}; border-radius: 50%; border: 2px solid white;"></div>`,
        className: '',
        iconSize: [12, 12],
        iconAnchor: [6, 6],
        popupAnchor: [0, -6]
      });
  
      const marker = L.marker([country.latitude, country.longitude], { icon: customIcon })
      markerGroup.addLayer(marker);

      
  
  
      let countObservable;
      if (this.selectedType === 'article') {
        countObservable = this.apiService.getcountArticlesByYearAndCountry(this.selectedYear, this.selectedYear2, alpha2Code);
        marker.on('click', () => {
          this.loadArticles(this.selectedYear, this.selectedYear2, alpha2Code);
        });
      } else if (this.selectedType === 'conference') {
        countObservable = this.apiService.getcountConferencesByYearAndCountry(this.selectedYear, this.selectedYear2, alpha2Code);
        marker.on('click', () => {
          this.loadConferences(this.selectedYear, this.selectedYear2, alpha2Code);
        });
      } else if (this.selectedType === 'livre') {
        countObservable = this.apiService.getcountLivresByYearAndCountry(this.selectedYear, this.selectedYear2, alpha2Code);
        marker.on('click', () => {
          this.loadLivres(this.selectedYear, this.selectedYear2, alpha2Code);
        });
      }
  
      if (countObservable) {
        countObservable.subscribe((count) => {
          const numberMarker = this.createNumberAboveMarker(country.latitude, country.longitude, count, alpha2Code);
          markerGroup.addLayer(numberMarker);
        });
      }
  
      markerGroup.addTo(this.map);
      this.displayedCountries.add(alpha2Code);
    } else {
      console.warn(`Coordonnées non trouvées pour le code : "${alpha2Code}"`);
    }
  }

  
  private createNumberAboveMarker(lat: number, lng: number, number: number, alpha2Code: string): L.Marker {
    let markerColor = 'red'; 
    if (this.selectedType === 'article') {
      markerColor = 'blue';
    } else if (this.selectedType === 'livre') {
      markerColor = 'green';
    } else if (this.selectedType === 'conference') {
      markerColor = 'red';
    }
  
    const numberIcon = L.divIcon({
      html: `<div style="
        transform: translate(-50%, -100%);
        background: white;
        border-radius: 10px;
        padding: 2px 5px;
        font-weight: bold;
        color: ${markerColor};
        font-size: 14px;
        border: 1px solid ${markerColor};
        text-align: center;
        cursor: pointer;
        width: 30px;
        height: 20px;
        pointer-events: all; /* Force l'élément à être cliquable */
      ">${number}</div>`,
      className: 'number-icon',
      iconSize: [30, 20],
      iconAnchor: [15, 0],
    });
  
    const numberMarker = L.marker([lat, lng], { icon: numberIcon });
  
    // Attacher l'événement de clic au marqueur
    if (this.selectedType === 'article') {
      numberMarker.on('click', () => {
        this.loadArticles(this.selectedYear, this.selectedYear2, alpha2Code);
      });
    }
    else if (this.selectedType === 'conference') {
      numberMarker.on('click', () => {
        this.loadConferences(this.selectedYear, this.selectedYear2, alpha2Code);
      });
    }
    else if (this.selectedType === 'livre') {
      numberMarker.on('click', () => {
        this.loadLivres(this.selectedYear, this.selectedYear2, alpha2Code);
      });
    }
  
    return numberMarker;
  }
  
  

  loadArticles(year: number | null, year2: number | null, country: string): void {
    this.apiService.getArticlesByYearAndCountry(year, year2, country)
      .subscribe(data => {
        this.articleList = data;
  
        // Construction du HTML
        let html = '<div class="publication-list">'
          html +='<h2>Liste des articles</h2><ul>';
        for (const article of this.articleList) {
          html += `<li>${article.titre} (${article.date}) - ${article.pays}</li>`;
        }
        html += '</ul></div';
  
        // Injection dans un élément de la page
        const container = document.getElementById('articles-container');
        if (container) {
          container.innerHTML = html;
        }
      });
  }

  loadConferences(year: number | null, year2: number | null, country: string): void {
    this.apiService.getConferencesByYearAndCountry(year, year2, country)
      .subscribe(data => {
        this.conferenceList = data;
        // Construction du HTML
        let html = '<div class="publication-list">'
        html +='<h2>Liste des conférences</h2><ul>';
        for (const conference of this.conferenceList) { 
          html += `<li>${conference.titre} (${conference.annee}) - ${conference.pays}</li>`;
        }
        html += '</ul></div>';
        // Injection dans un élément de la page
        const container = document.getElementById('articles-container');
        if (container) {
          container.innerHTML = html;
        }
      });
  }
  loadLivres(year: number | null, year2: number | null, country: string): void {
    this.apiService.getLivresByYearAndCountry(year, year2, country)
      .subscribe(data => {
        this.livreList = data;
        // Construction du HTML
        
        let html = '<div class="publication-list">'
        html +='<h2>Liste des livres</h2><ul>';
        for (const livre of this.livreList) {
          html += `<li>${livre.titre} (${livre.annee}) - ${livre.pays}</li>`;
        }
        html += '</ul></div>';
        // Injection dans un élément de la page
        const container = document.getElementById('articles-container');
        if (container) {
          container.innerHTML = html;
        }
      });
  }
  

  
  resetPublicationList(): void {
    const container = document.getElementById('articles-container');
    if (container) {
      container.innerHTML = '';  
    }
  }
  
  public onShowMap(): void {
    this.map.invalidateSize();
  }
}
