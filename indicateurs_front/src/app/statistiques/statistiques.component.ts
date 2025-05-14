import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { ChartConfiguration, ChartData, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { CommonModule } from '@angular/common';
import { NgChartsModule } from 'ng2-charts';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-statistiques',
  standalone: true,
  imports: [CommonModule, NgChartsModule],
  templateUrl: './statistiques.component.html',
  styleUrls: ['./statistiques.component.scss']
})
export class StatistiquesComponent implements OnInit {
  @ViewChild(BaseChartDirective) chart?: BaseChartDirective;
  
  articlesStats: any[] = [];
  conferencesStats: any[] = [];
  livresStats: any[] = [];
  loadingArticles: boolean = true;
  loadingConferences:boolean =true;
  loadingLivres: boolean = true;

  public scatterChartOptions: ChartConfiguration<'scatter'>['options'] = {
    responsive: true,
    maintainAspectRatio: false,
    scales: {
      x: {
        title: { 
          display: true, 
          text: 'Années', 
          color: '#666', 
          font: { size: 14 } 
        },
        ticks: { color: '#333' }
      },
      y: {
        title: { 
          display: true, 
          text: 'Nombre d\'articles', 
          color: '#666', 
          font: { size: 14 } 
        },
        ticks: {
          color: '#333',
          stepSize: 5, 
        }, 
        beginAtZero: true 
      }
    },
    elements: {
      line: { tension: 0.4 } 
    },
    plugins: {
      legend: { 
        labels: { color: '#333' } 
      },
      tooltip: {
        backgroundColor: 'rgba(0,0,0,0.7)',
        bodyColor: '#fff',
        titleColor: '#fff',
        borderColor: 'rgba(255,255,255,0.2)',
        borderWidth: 1
      }
    }
  };

  public scatterChartData: ChartData<'scatter'> = {
    datasets: [
      {
        label: 'Articles',
        data: [],
        pointBackgroundColor: '#42A5F5',
        pointBorderColor: '#1E88E5',
        pointRadius: 6,
        pointHoverRadius: 8,
        borderColor: '#1565C0',
        backgroundColor: 'rgba(66, 165, 245, 0.2)',
        showLine: true,
        borderWidth: 2,
        fill: true
      },
      {
        label: 'Conferences',
        data: [],
        pointBackgroundColor: '#FF7043',
        pointBorderColor: '#F4511E',
        pointRadius: 6,
        pointHoverRadius: 8,
        borderColor: '#BF360C',
        backgroundColor: 'rgba(224, 29, 22, 0.2)',
        showLine: true,
        borderWidth: 2,
        fill: true
      },
      {
        label: 'Livres',
        data: [],
        pointBackgroundColor: '#66BB6A',
        pointBorderColor: '#43A047',
        pointRadius: 6,
        pointHoverRadius: 8,
        borderColor: '#388E3C',
        backgroundColor: 'rgba(15, 204, 24, 0.2)',
        showLine: true,
        borderWidth: 2,
        fill: true
      }
    ]
  };

  constructor(
    private apiService: ApiService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadChartData();
    window.addEventListener('resize', this.handleResize.bind(this));
  }

  loadChartData(): void {
    this.loadingArticles = true;
    this.loadingConferences = true;
    this.apiService.getArticlesStats().subscribe(
      (data: any[]) => {
        this.articlesStats = data;
        this.updateChartData();
        this.loadingArticles = false;
        this.cdr.detectChanges();
        
        setTimeout(() => {
          this.chart?.update();
        }, 100);
      },
      (error) => {
        console.error('Error loading stats:', error);
        this.loadingArticles = false;
      }
    );
    this.apiService.getConferencesStats().subscribe(
      (data: any[]) => {
        this.conferencesStats = data;
        this.updateChartData();
        this.loadingConferences = false;
        this.cdr.detectChanges();
        
        setTimeout(() => {
          this.chart?.update();
        }, 100);
      },
      (error) => {
        console.error('Error loading stats:', error);
        this.loadingConferences = false;
      }
    );
    this.apiService.getLivresStats().subscribe(
      (data: any[]) => {
        this.livresStats = data;
        this.updateChartData();
        this.loadingLivres = false;
        this.cdr.detectChanges();
        
        setTimeout(() => {
          this.chart?.update();
        }, 100);
      },
      (error) => {
        console.error('Error loading stats:', error);
        this.loadingLivres = false;
      }
    );
  }

  updateChartData(): void {
    this.scatterChartData.datasets[0].data = this.articlesStats.map(item => ({
      x: item[0],
      y: item[1]
    }));
    this.scatterChartData.datasets[1].data = this.conferencesStats.map(item => ({
      x: item[0],
      y: item[1]
    }));
    this.scatterChartData.datasets[2].data = this.livresStats.map(item => ({
      x: item[0],
      y: item[1]
    }));
  }

  handleResize(): void {
    if (this.chart) {
      this.chart.update();
      this.chart.render();
    }
  }

  ngOnDestroy(): void {
    window.removeEventListener('resize', this.handleResize.bind(this));
  }
}