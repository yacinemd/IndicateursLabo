package groupe7.indicateurs.domain.personnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import groupe7.indicateurs.domain.personnel.model.Personnel;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long>{
    
    @Query(value = "SELECT " +
                   "(SELECT COUNT(*) FROM chercheur) AS chercheurs, " +
                   "(SELECT COUNT(*) FROM doctorant) AS doctorants, " +
                   "(SELECT COUNT(*) FROM autre_poste) AS autres_postes", 
           nativeQuery = true)
    Object[] countPersonnelTypes();

    @Query(value = """
    SELECT COUNT(DISTINCT la.au_id_article)
    FROM personnel pr
    JOIN personne pe ON pr.pr_id_personnel = pe.pe_id_personne
    JOIN liaison_personne_article la ON la.au_id_personne = pe.pe_id_personne
    WHERE pr.pr_equipe LIKE %:equipe%
    """, nativeQuery = true)
    int countArticlesByEquipe(@Param("equipe") String equipe);

    @Query(value = """
    SELECT COUNT(DISTINCT pc.pc_id_conference)
    FROM personnel pr
    JOIN personne pe ON pr.pr_id_personnel = pe.pe_id_personne
    JOIN liaison_personne_conference pc ON pc.pc_id_personne = pe.pe_id_personne
    WHERE pr.pr_equipe LIKE %:equipe%
    """, nativeQuery = true)
    int countConferencesByEquipe(@Param("equipe") String equipe);

    @Query(value = """
    SELECT COUNT(DISTINCT pl.pl_id_livre)
    FROM personnel pr
    JOIN personne pe ON pr.pr_id_personnel = pe.pe_id_personne
    JOIN liaison_personne_livre pl ON pl.pl_id_personne = pe.pe_id_personne
    WHERE pr.pr_equipe LIKE %:equipe%
    """, nativeQuery = true)
    int countLivresByEquipe(@Param("equipe") String equipe);


    @Query(value = """
    SELECT COUNT(DISTINCT a.at_id_revue)
    FROM personnel pr
    JOIN personne pe ON pr.pr_id_personnel = pe.pe_id_personne
    JOIN liaison_personne_article la ON la.au_id_personne = pe.pe_id_personne
    JOIN article a ON la.au_id_article = a.at_id_article
    WHERE pr.pr_equipe LIKE %:equipe%
    """, nativeQuery = true)
    int countRevuesByEquipe(@Param("equipe") String equipe);



}
