package groupe7.indicateurs.domain.doctorant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import groupe7.indicateurs.domain.doctorant.model.Doctorant;

@Repository
public interface DoctorantRepository extends JpaRepository<Doctorant, Long>{

    @Query("SELECT d FROM Doctorant d WHERE d.id = :id")
    @Override
    Optional<Doctorant> findById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Doctorant d SET d.encadrant = :Encadrant WHERE d.id = :id")
    void updateEncadrant(@Param("id") Long id, @Param("Encadrant") String Encadrant);

    
    @Query("SELECT d FROM Doctorant d JOIN Personne p ON d.id = p.id WHERE p.nom = :nom")
    List<Doctorant> findByNom(@Param("nom") String nom);

    @Query("SELECT d FROM Doctorant d JOIN d.personnel p JOIN p.personne pe WHERE pe.nom LIKE %:nom% OR pe.prenom LIKE %:prenom%")
    List<Doctorant> findByNomOrPrenom(@Param("nom") String nom, @Param("prenom") String prenom);

   
    


}
