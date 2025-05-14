import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Article } from '../models/article.model';
import { Conference } from '../models/conference.model';
import { Livre } from '../models/livre.model';
import { Personne } from '../models/personne.model';
import { Personnel } from '../models/personnel.model';
import { Projet } from '../models/projet.model';
import { Revue } from '../models/revue.model';
import { Chercheur } from '../models/chercheur.model';
import { Doctorant } from '../models/doctorant.model';
import { AutrePoste } from '../models/autrePoste.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private api_url_local = 'http://localhost:8080/api';
  private api_url_dev = 'http://172.31.60.56:8080/api';
  private api_url_prod = 'http://172.31.60.57:8080/api';
  private api_url = this.getApiUrl();

  private getApiUrl(): string {
    const hostname = window.location.hostname;
    if (hostname === 'localhost') {
      return this.api_url_local;
    } else if (hostname === '172.31.60.56') {
      return this.api_url_dev;
    } else if (hostname === '172.31.60.57') {
      return this.api_url_prod;
    } else {
      throw new Error('Unknown environment');
    }
  }

  constructor(private http: HttpClient) { }

  getArticle(): Observable<Article> {
    const url = `${this.api_url}/articles`;
    return this.http.get<Article>(url);
  }

  getConference(): Observable<Conference> {
    const url = `${this.api_url}/conferences`;
    return this.http.get<Conference>(url);
  }

  getLivre(): Observable<Livre> {
    const url = `${this.api_url}/livres`;
    return this.http.get<Livre>(url);
  }

  getPersonne(): Observable<Personne> {
    const url = `${this.api_url}/personnes`;
    return this.http.get<Personne>(url);
  }

  getPersonnel(): Observable<Personnel[]> {
    const url = `${this.api_url}/personnels`;
    return this.http.get<Personnel[]>(url);
  }

  getProjet(): Observable<Projet> {
    const url = `${this.api_url}/projects`;
    return this.http.get<Projet>(url);
  }

  getRevue(): Observable<Revue> {
    const url = `${this.api_url}/revues`;
    return this.http.get<Revue>(url);
  }
  
  getArticlesStats(): Observable<any> {
    return this.http.get<Article[]>(`${this.api_url}/statistiques/articles`)
    
  }

  getLivresStats(): Observable<any> {
    return this.http.get<Livre[]>(`${this.api_url}/statistiques/livres`)
  }

  getConferencesStats(): Observable<any> {
    return this.http.get<Conference[]>(`${this.api_url}/statistiques/conferences`)
  }
  
  getPersonnelStats(): Observable<any> {
    const url = `${this.api_url}/statistiques/personnel`;  
    return this.http.get<any>(url);
  }

  getQuartilesByRevue(id:Number): Observable<any[]> {
    const url = `${this.api_url}/statistiques/quartiles/search?id=${id}`;
    return this.http.get<any[]>(url);
  }

  searchPersonneByNom(nom: string): Observable<Personne[]> {
    const url = `${this.api_url}/personnes?nom=${encodeURIComponent(nom)}`;
    return this.http.get<Personne[]>(url);
  }
  
  
  searchChercheur(query: string): Observable<Chercheur[]> {
    const url = `${this.api_url}/chercheurs?nom=${encodeURIComponent(query)}&prenom=${encodeURIComponent(query)}`;
    return this.http.get<Chercheur[]>(url);
  }
  
  searchDoctorant(query: string): Observable<Doctorant[]> {
    const url = `${this.api_url}/doctorants?nom=${encodeURIComponent(query)}&prenom=${encodeURIComponent(query)}`;
    return this.http.get<Doctorant[]>(url);
}

searchAutrePoste(query: string): Observable<AutrePoste[]> {
  const url = `${this.api_url}/autrepostes?nom=${encodeURIComponent(query)}&prenom=${encodeURIComponent(query)}`;
  return this.http.get<AutrePoste[]>(url);
}




searchArticle(query: string): Observable<Article[]> {
  const url = `${this.api_url}/articles?titre=${encodeURIComponent(query)}`;
  return this.http.get<Article[]>(url);
}

searchRevue(title: string, publisher: string): Observable<number | null> {
  const url = `${this.api_url}/revues/id?titre=${encodeURIComponent(title)}&publisher=${encodeURIComponent(publisher)}`;
  return this.http.get<number>(url);
}

searchRevueByTitre(title: string): Observable<Revue[]> {
  const url = `${this.api_url}/revues/titre?titre=${encodeURIComponent(title)}`;
  return this.http.get<Revue[]>(url);
}

searchRevueByPublisher(publisher: string): Observable<Revue[]> {
  const url = `${this.api_url}/revues/publisher?publisher=${encodeURIComponent(publisher)}`;
  return this.http.get<Revue[]>(url);
}

searchRevue2(queryparam:string): Observable<Revue[]> {
  const url = `${this.api_url}/revues/titreOrPublisher?queryparam=${encodeURIComponent(queryparam)}`;
  return this.http.get<Revue[]>(url);
} 

searchConference(query: string): Observable<Conference[]> {
  const url = `${this.api_url}/conferences?titre=${encodeURIComponent(query)}`;
  return this.http.get<Conference[]>(url);
}

searchLivre(query: string): Observable<Livre[]> {
  const url = `${this.api_url}/livres?titre=${encodeURIComponent(query)}`;
  return this.http.get<Livre[]>(url);
}


getBestQuartilesByRevue(id: number): Observable<any[]> {
  const url = `${this.api_url}/quartiles/bestbyrevue/${id}`;
  return this.http.get<any[]>(url);
}



getArticlesByYear(year: number, year2: number): Observable<Article[]> {
  const url = `${this.api_url}/articles/year?date1=${year}&date2=${year2}`;
  return this.http.get<Article[]>(url);
}

getConferencesByYear(year: number, year2:number): Observable<Conference[]> {
  const url = `${this.api_url}/conferences/year?date1=${year}&date2=${year2}`;
  return this.http.get<Conference[]>(url);
}

getLivresByYear(year: number, year2 : number): Observable<Livre[]> {
  const url = `${this.api_url}/livres/year?date1=${year}&date2=${year2}`;
  return this.http.get<Livre[]>(url);
}

getRevuesByYear(year: number): Observable<Revue[]> {
  const url = `${this.api_url}/revues/year?year=${year}`;
  return this.http.get<Revue[]>(url);
}

getcountArticlesByYearAndCountry(year: number | null ,year2:number| null , country: string): Observable<number> {
  const url = `${this.api_url}/articles/cpt?date1=${year}&date2=${year2}&country=${country}`;
  return this.http.get<number>(url);
}

getcountLivresByYearAndCountry(year: number | null ,year2:number| null , country: string): Observable<number> {
  const url = `${this.api_url}/livres/cpt?date1=${year}&date2=${year2}&country=${country}`;
  return this.http.get<number>(url);
}

getcountConferencesByYearAndCountry(year: number | null ,year2:number| null , country: string): Observable<number> {
  const url = `${this.api_url}/conferences/cpt?date1=${year}&date2=${year2}&country=${country}`;
  return this.http.get<number>(url);
}

getArticlesByYearAndCountry(year: number | null ,year2:number| null , country: string): Observable<Article[]> {
  const url = `${this.api_url}/articles/yearAndCountry?date1=${year}&date2=${year2}&country=${country}`;
  return this.http.get<Article[]>(url);
}

getConferencesByYearAndCountry(year: number | null ,year2:number| null , country: string): Observable<Conference[]> {
  const url = `${this.api_url}/conferences/yearAndCountry?date1=${year}&date2=${year2}&country=${country}`;
  return this.http.get<Conference[]>(url);
}
getLivresByYearAndCountry(year: number | null ,year2:number| null , country: string): Observable<Livre[]> {
  const url = `${this.api_url}/livres/yearAndCountry?date1=${year}&date2=${year2}&country=${country}`;
  return this.http.get<Livre[]>(url);
}

countPubliByTeam(equipe: string): Observable<number> {
  const url = `${this.api_url}/personnels/publiByTeam?team=${encodeURIComponent(equipe)}`;  
  return this.http.get<number>(url);
}
countArticlesByTeam(equipe: string): Observable<number> {
  const url = `${this.api_url}/personnels/articleByTeam?team=${encodeURIComponent(equipe)}`;
  return this.http.get<number>(url);
}
countConferencesByTeam(equipe: string): Observable<number> {
  const url = `${this.api_url}/personnels/conferenceByTeam?team=${encodeURIComponent(equipe)}`;
  return this.http.get<number>(url);
}
countLivresByTeam(equipe: string): Observable<number> {
  const url = `${this.api_url}/personnels/livreByTeam?team=${encodeURIComponent(equipe)}`;
  return this.http.get<number>(url);
}

countRevuesByTeam(equipe: string): Observable<number> {
  const url = `${this.api_url}/personnels/revueByTeam?team=${encodeURIComponent(equipe)}`;
  return this.http.get<number>(url);
}

getRevueByEquipe(equipe: string): Observable<Revue[]> {
  const url = `${this.api_url}/revues/equipe?equipe=${encodeURIComponent(equipe)}`;
  return this.http.get<Revue[]>(url);
}

getArticleByEquipe(equipe: string): Observable<Article[]> {
  const url = `${this.api_url}/articles/team?team=${encodeURIComponent(equipe)}`;
  return this.http.get<Article[]>(url);
}
getConferenceByEquipe(equipe: string): Observable<Conference[]> {
  const url = `${this.api_url}/conferences/team?team=${encodeURIComponent(equipe)}`;
  return this.http.get<Conference[]>(url);
}
getLivreByEquipe(equipe: string): Observable<Livre[]> {
  const url = `${this.api_url}/livres/team?team=${encodeURIComponent(equipe)}`;
  return this.http.get<Livre[]>(url);
}

}
