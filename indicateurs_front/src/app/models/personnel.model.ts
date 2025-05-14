import { Personne } from './personne.model';
import { Projet } from './projet.model';
import { Article } from './article.model';

export class Personnel {
    constructor(
        public id: number,
        public personne: Personne,
        public statut: string,
        public equipe: string,
        public titulaire: boolean,
        public dateDebut: Date,
        public dateFin: Date,
        public projets: Projet[],
        public articles: Article[]
    ) {}
}