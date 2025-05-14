import { Article } from './article.model';

export class Conference {
    constructor(
        public id: number,
        public titre: string,
        public classe: string,
        public annee: string,
        public pays: string,
        public articles: Article[]
    ) {}
}