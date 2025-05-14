package groupe7.indicateurs.domain.personne.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import groupe7.indicateurs.domain.personne.model.Personne;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long>{

    @Query("SELECT p FROM Personne p WHERE p.nom = :nom")
    List<Personne> findByNom(@Param("nom") String nom);

    @Query("SELECT p.id FROM Personne p WHERE p.nom = :nom ORDER BY p.id ASC")
    List<Long> findIdsByNom(@Param("nom") String nom);

    @Query("SELECT p.id FROM Personne p WHERE LOWER(TRIM(p.nom)) = LOWER(TRIM(:nom)) AND LOWER(TRIM(p.prenom)) = LOWER(TRIM(:prenom))")
    List<Long> findIdByNomAndPrenom(@Param("nom") String nom, @Param("prenom") String prenom);

    @Query("SELECT p FROM Personne p WHERE p.nom = :nom AND p.prenom = :prenom")
    List<Personne> findByNomAndPrenom(@Param("nom") String nom, @Param("prenom") String prenom);


}
