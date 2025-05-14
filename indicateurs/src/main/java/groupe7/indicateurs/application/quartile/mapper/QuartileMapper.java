package groupe7.indicateurs.application.quartile.mapper;

import org.springframework.stereotype.Component;

import groupe7.indicateurs.application.quartile.dto.QuartileDTO;
import groupe7.indicateurs.domain.quartile.model.Quartile;

@Component
public class QuartileMapper {

    public QuartileDTO toDto(Quartile quartile) {
        if (quartile == null) {
            return null;
        }

        QuartileDTO dto = new QuartileDTO();
        dto.setId(quartile.getId());
        dto.setAnnee(quartile.getAnnee());
        dto.setQuartile(quartile.getQuartile());
        dto.setDomaine(quartile.getDomaine());
        // dto.setRevue(RevueDTO.toDto(quartile.getRevue())); // à activer si RevueDTO est géré
        return dto;
    }

    public Quartile toEntity(QuartileDTO dto) {
        if (dto == null) {
            return null;
        }

        Quartile quartile = new Quartile();
        quartile.setId(dto.getId());
        quartile.setAnnee(dto.getAnnee());
        quartile.setQuartile(dto.getQuartile());
        quartile.setDomaine(dto.getDomaine());
        // quartile.setRevue(RevueDTO.toEntity(dto.getRevue())); // idem ci-dessus
        return quartile;
    }
}
