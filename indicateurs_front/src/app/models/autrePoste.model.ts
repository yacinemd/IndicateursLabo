import { Article } from './article.model';
import { Projet } from './projet.model';


export class AutrePoste {
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
        public titre: string,
        public articles: Article[]
    ) {}
}