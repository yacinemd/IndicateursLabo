-- Table personne
INSERT INTO personne (pe_nom, pe_prenom, pe_hdr) VALUES
('Dupont', 'Jean', TRUE),
('Martin', 'Claire', FALSE),
('Bernard', 'Alice', TRUE),
('Durand', 'Paul', FALSE),
('Leroy', 'Marie', TRUE);

-- Table personnel
INSERT INTO personnel (pr_id_personnel, pr_statut, pr_equipe, pr_titulaire, pr_date_debut, pr_date_fin) VALUES
(1, 'Chercheur', 'Equipe A', TRUE, '2015-01-01', NULL),
(2, 'Doctorant', 'Equipe B', FALSE, '2020-09-01', '2023-09-01'),
(3, 'Autre', 'Equipe C', TRUE, '2018-05-15', NULL),
(4, 'Chercheur', 'Equipe A', FALSE, '2016-02-01', NULL),
(5, 'Doctorant', 'Equipe B', FALSE, '2021-10-01', '2024-10-01');

-- Table chercheur
INSERT INTO chercheur (ch_id_chercheur) VALUES (1);
INSERT INTO chercheur (ch_id_chercheur) VALUES (2);
INSERT INTO chercheur (ch_id_chercheur) VALUES (3);
INSERT INTO chercheur (ch_id_chercheur) VALUES (4);
INSERT INTO chercheur (ch_id_chercheur) VALUES (5);

-- Table doctorant
INSERT INTO doctorant (do_id_doctorant, do_encadrant, do_date_de_soutenance) VALUES
(1, 2, '2023-09-01'),
(2, 1, '2024-10-01'),
(3, 1, '2025-06-01'),
(4, 1, '2025-09-01'),
(5, 1, '2026-12-15');

-- Table autre_poste
INSERT INTO autre_poste (ap_id_poste, ap_titre) VALUES
(3, 'Consultant'),
(5, 'Chargé de mission'),
(2, 'Assistant de recherche'),
(4, 'Conférencier invité'),
(1, 'Coordinateur de projet');

-- Table projet
INSERT INTO projet (pj_nom, pj_id_contributeurprojet, pj_date_debut, pj_date_fin, pj_type, pj_budget, pj_partenariat) VALUES
('Projet Alpha', 1, '2020-01-01', '2022-12-31', 'Recherche', 50000.00, TRUE),
('Projet Beta', 2, '2019-06-01', '2021-05-31', 'Développement', 75000.00, FALSE),
('Projet Gamma', 3, '2021-01-01', '2023-01-01', 'Innovation', 100000.00, TRUE),
('Projet Delta', 4, '2020-09-15', '2022-03-30', 'Recherche', 45000.00, FALSE),
('Projet Epsilon', 5, '2021-06-01', NULL, 'Développement', 60000.00, TRUE);

-- Table liaison_personnel_projet
INSERT INTO liaison_personnel_projet (lc_id_projet, lc_id_personnel) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- Table article
INSERT INTO article (at_titre, at_date) VALUES
('Article A', '2020-05-10'),
('Article B', '2021-06-15'),
('Article C', '2019-12-20'),
('Article D', '2022-08-30'),
('Article E', '2023-01-01');

-- Table liaison_personne_article
INSERT INTO liaison_personne_article (au_id_personne, au_id_article, au_isprincipal) VALUES
(1, 1, TRUE),
(2, 2, FALSE),
(3, 3, TRUE),
(4, 4, FALSE),
(5, 5, TRUE);

-- Table conference
INSERT INTO conference (cf_titre, cf_classe, cf_annee) VALUES
('Conférence Internationale sur l IA', 'A', 2021),
('Colloque sur la Science des Données', 'B', 2020),
('Symposium sur la Robotique', 'C', 2022),
('Congrès sur les Systèmes d Information', 'B', 2023),
('Conférence sur les Réseaux Neuronaux', 'A', 2022);

-- Table revue
INSERT INTO revue (re_annee, re_titre, re_classement, re_domaine) VALUES
(2020, 'Revue des Sciences Informatiques', 'A', 'Informatique'),
(2021, 'Journal de Recherche', 'B', 'Biologie'),
(2022, 'Revue Européenne de Robotique', 'A', 'Robotique'),
(2019, 'Annales de Mathématiques Appliquées', 'B', 'Mathématiques'),
(2023, 'Journal des Systèmes Complexes', 'C', 'Systèmes');

-- Table liaison_article_conference
INSERT INTO liaison_article_conference (ac_id_article, ac_id_conference) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- Table liaison_article_revue
INSERT INTO liaison_article_revue (ar_id_article, ar_id_revue) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- Table livre
INSERT INTO livre (li_id_article, li_annee, li_titre) VALUES
(1, 2018, 'Livre sur l IA'),
(2, 2019, 'Techniques de Data Science'),
(3, 2020, 'La Robotique Moderne'),
(4, 2021, 'Systèmes d Information'),
(5, 2022, 'Réseaux Neuronaux Avancés');

-- Table liaison_livre_article
INSERT INTO liaison_livre_article (la_id_livre, la_id_article) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);