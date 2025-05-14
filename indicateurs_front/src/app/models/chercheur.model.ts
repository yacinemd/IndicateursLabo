import { Article } from './article.model';
import { Projet } from './projet.model';


export class Chercheur {
    constructor(
        public id: number,
        public prenom : string,
        public nom : string,
        public statut: string,
        public equipe: string,
        public titulaire: boolean,
        public dateDebut: Date,
        public dateFin: Date,
        public projets: Projet[],
        public hdr: boolean,
        public articles: Article[]
    ) {}
}