import { Personnel } from './personnel.model';

export class Projet {
    constructor(
        public id: number,
        public nom: string,
        public idContributeurProjet: number,
        public dateDebut: Date,
        public dateFin: Date,
        public type: string,
        public budget: number,
        public partenariat: boolean,
        public personnels: Personnel[]
    ) {}
}
