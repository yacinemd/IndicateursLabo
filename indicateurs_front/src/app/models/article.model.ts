import { Livre } from './livre.model';
import { Personne } from './personne.model';
import { Conference } from './conference.model';
import { Revue } from './revue.model';

export class Article {
    constructor(
        public id: number,
        public titre: string,
        public date: Date,
        public livres: Livre[],
        public pays: string,
        public personnes: Personne[],
        public conferences: Conference[],
        public revues: Revue[]
    ) {}
}