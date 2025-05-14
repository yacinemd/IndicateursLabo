package groupe7.indicateurs.domain.liaison_quartile_revue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import groupe7.indicateurs.domain.liaison_quartile_revue.model.LiaisonQuartileRevue;

@Repository
public interface LiaisonQuartileRevueRepository extends JpaRepository<LiaisonQuartileRevue, Long> {

    @Query("SELECT l FROM LiaisonQuartileRevue l WHERE l.quartile.id = :quartileId AND l.revue.id = :revueId")
    LiaisonQuartileRevue findByQuartileIdAndRevueId(Long quartileId, Long revueId);
}
