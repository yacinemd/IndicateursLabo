package groupe7.indicateurs.application.revue.mapper;

import org.springframework.stereotype.Component;

import groupe7.indicateurs.application.revue.dto.RevueDTO;
import groupe7.indicateurs.domain.revue.model.Revue;

@Component
public class RevueMapper {

    public RevueDTO toDto(Revue revue) {
        if (revue == null) {
            return null;
        }

        RevueDTO dto = new RevueDTO();
        dto.setId(revue.getId());
        dto.setTitre(revue.getTitre());
        dto.setAnnee(revue.getAnnee());
        dto.setPublisher(revue.getPublisher());

        return dto;
    }

    public Revue toEntity(RevueDTO dto) {
        if (dto == null) {
            return null;
        }

        Revue revue = new Revue();
        revue.setId(dto.getId());
        revue.setTitre(dto.getTitre());
        revue.setAnnee(dto.getAnnee());
        revue.setPublisher(dto.getPublisher());

        return revue;
    }
}
