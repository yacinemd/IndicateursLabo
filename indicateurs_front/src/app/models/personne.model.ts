import { Article } from './article.model';

export class Personne {
    constructor(
        public id: number,
        public nom: string,
        public prenom: string,
        public hdr: boolean,
        public articles: Article[]
    ) {}
}