package groupe7.indicateurs.domain.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import groupe7.indicateurs.domain.projet.model.Projet;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long> {
    
}
