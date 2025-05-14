package groupe7.indicateurs.application.conference.mapper;

import org.springframework.stereotype.Component;

import groupe7.indicateurs.application.conference.dto.ConferenceDTO;
import groupe7.indicateurs.domain.conference.model.Conference;

@Component
public class ConferenceMapper {

    public ConferenceDTO toDto(Conference conference) {
        if (conference == null) {
            return null;
        }

        ConferenceDTO dto = new ConferenceDTO();
        dto.setId(conference.getId());
        dto.setTitre(conference.getTitre());
        dto.setAnnee(conference.getAnnee());
        dto.setPays(conference.getPays());
    

        return dto;
    }

    public Conference toEntity(ConferenceDTO dto) {
        if (dto == null) {
            return null;
        }

        Conference conference = new Conference();
        conference.setId(dto.getId());
        conference.setTitre(dto.getTitre());
        conference.setAnnee(dto.getAnnee());
        conference.setPays(dto.getPays());
        

        return conference;
    }
}
