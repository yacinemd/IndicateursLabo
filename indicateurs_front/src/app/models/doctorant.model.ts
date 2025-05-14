import { Article } from './article.model';
import { Projet } from './projet.model';


export class Doctorant {
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
        public idEncadrant: number,
        public dateSoutenance: Date,
        public articles: Article[]
    ) {}
}