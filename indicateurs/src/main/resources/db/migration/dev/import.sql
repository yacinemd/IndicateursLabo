-- Table personne
CREATE TABLE personne (
  pe_id_personne INT PRIMARY KEY AUTO_INCREMENT,
  pe_nom VARCHAR(255),
  pe_prenom VARCHAR(255),
  pe_hdr BOOLEAN
);

-- Table personnel
CREATE TABLE personnel (
  pr_id_personnel INT PRIMARY KEY,
  pr_statut VARCHAR(255),
  pr_equipe VARCHAR(255),
  pr_titulaire BOOLEAN,
  pr_date_debut DATE,
  pr_date_fin DATE
);

-- Table chercheur
CREATE TABLE chercheur (
  ch_id_chercheur INT PRIMARY KEY
);

-- Table doctorant
CREATE TABLE doctorant (
  do_id_doctorant INT PRIMARY KEY,
  do_encadrant VARCHAR(255),
  do_date_de_soutenance DATE
);

-- Table autre_poste
CREATE TABLE autre_poste (
  ap_id_poste INT PRIMARY KEY,
  ap_titre VARCHAR(255)
);

-- Table projet
CREATE TABLE projet (
  pj_id_projet INT PRIMARY KEY AUTO_INCREMENT,
  pj_nom VARCHAR(255),
  pj_id_contributeurprojet INT,
  pj_date_debut DATE,
  pj_date_fin DATE,
  pj_type VARCHAR(255),
  pj_budget DECIMAL(15, 2),
  pj_partenariat BOOLEAN
);

-- Table liaison_personnel_projet
CREATE TABLE liaison_personnel_projet (
  lc_id_projet INT,
  lc_id_personnel INT,
  PRIMARY KEY (lc_id_projet, lc_id_personnel)
);

-- Table article
CREATE TABLE article (
  at_id_article INT PRIMARY KEY AUTO_INCREMENT,
  at_id_revue INT,
  at_titre VARCHAR(255),
  at_pays VARCHAR(255),
  at_date DATE
);

-- Table liaison_personne_article
CREATE TABLE liaison_personne_article (
  au_id_ap INT PRIMARY KEY AUTO_INCREMENT,
  au_id_personne INT,
  au_id_article INT
);

-- Table conference
CREATE TABLE conference (
  cf_id_conf INT PRIMARY KEY AUTO_INCREMENT,
  cf_titre VARCHAR(255),
  cf_pays VARCHAR(255),
  cf_annee INT
);

-- Table liaison_personne_conference
CREATE TABLE liaison_personne_conference (
  pc_id_pc INT PRIMARY KEY AUTO_INCREMENT,
  pc_id_personne INT,
  pc_id_conference INT
);

-- Table revue
CREATE TABLE revue (
  re_id_revue INT PRIMARY KEY AUTO_INCREMENT,
  re_annee INT,
  re_titre VARCHAR(255),
  re_publisher VARCHAR(255)
);


-- Table livre
CREATE TABLE livre (
  li_id_livre INT PRIMARY KEY AUTO_INCREMENT,
  li_annee INT,
  li_titre VARCHAR(255)
);

-- Table liaison_livre_article
CREATE TABLE liaison_personne_livre (
  pl_id_pl INT PRIMARY KEY AUTO_INCREMENT,
  pl_id_livre INT,
  pl_id_personne INT,
);


-- Table Quartile
CREATE TABLE quartile (
  ql_id_quartile INT PRIMARY KEY AUTO_INCREMENT,
  ql_annee INT,
  ql_domaine VARCHAR(255),
  ql_quartile VARCHAR(255),
  ql_id_revue INT,
)

-- Foreign key references
ALTER TABLE personnel ADD CONSTRAINT fk_personnel_personne FOREIGN KEY (pr_id_personnel) REFERENCES personne(pe_id_personne);
ALTER TABLE chercheur ADD CONSTRAINT fk_chercheur_personnel FOREIGN KEY (ch_id_chercheur) REFERENCES personnel(pr_id_personnel);
ALTER TABLE doctorant ADD CONSTRAINT fk_doctorant_personnel FOREIGN KEY (do_id_doctorant) REFERENCES personnel(pr_id_personnel);
ALTER TABLE autre_poste ADD CONSTRAINT fk_autre_poste_personnel FOREIGN KEY (ap_id_poste) REFERENCES personnel(pr_id_personnel);

ALTER TABLE livre ADD CONSTRAINT fk_li_article FOREIGN KEY (li_id_article) REFERENCES article(at_id_article);
ALTER TABLE article ADD CONSTRAINT fk_id_revue FOREIGN KEY (at_id_revue) REFERENCES revue(re_id_revue);

ALTER TABLE liaison_personnel_projet ADD CONSTRAINT fk_lc_projet FOREIGN KEY (lc_id_projet) REFERENCES projet(pj_id_projet);
ALTER TABLE liaison_personnel_projet ADD CONSTRAINT fk_lc_personnel FOREIGN KEY (lc_id_personnel) REFERENCES personnel(pr_id_personnel);

ALTER TABLE liaison_personne_article ADD CONSTRAINT fk_au_article FOREIGN KEY (au_id_article) REFERENCES article(at_id_article);
ALTER TABLE liaison_personne_article ADD CONSTRAINT fk_au_personne FOREIGN KEY (au_id_personne) REFERENCES personne(pe_id_personne);

ALTER TABLE liaison_personne_conference ADD CONSTRAINT fk_pc_personne FOREIGN KEY (pc_id_personne) REFERENCES personne(pe_id_personne);
ALTER TABLE liaison_personne_conference ADD CONSTRAINT fk_pc_conference FOREIGN KEY (pc_id_conference) REFERENCES conference(cf_id_conf);

ALTER TABLE liaison_personne_livre ADD CONSTRAINT fk_pl_personne FOREIGN KEY (pl_id_personne) REFERENCES personne(pe_id_personne);
ALTER TABLE liaison_personne_livre ADD CONSTRAINT fk_pl_livre FOREIGN KEY (pl_id_livre) REFERENCES livre(li_id_livre);

ALTER TABLE quartile ADD CONSTRAINT fk_ql_revue FOREIGN KEY (ql_id_revue) REFERENCES revue(re_id_revue);